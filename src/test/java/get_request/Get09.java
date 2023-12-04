package get_request;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.getPayLoad;

public class Get09 extends JsonPlaceHolderBaseUrl {

      /*
        Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
    */

    @Test
    public void get09() {


        //set the url
        specJsonPlaceHolder.pathParams("first","todos","second",2);

        //expected data
        Map<String , Object> expectedData = getPayLoad(1,"quis ut nam facilis et officia qui",false);

        expectedData.put("Via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");


        //Send the request and get the response
        Response response = given(specJsonPlaceHolder).when().get("{first}/{second}");
        //Serilization olmuyor burda olmasi cunku get metodu kullaniyoruz o yuzden serilization yapilan bir durum yok
        // Postlarda ve putlarda karsi tarafa java objesi yolladigimizdan dolayi
//       onu json a donusturmek istedigimizden kaynakli serilization oluyor


        //Do assertions
        Map<String , Object> actualData = response.as(HashMap.class); //De-serilization


        assertEquals(200 , response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));

        //1st way
        assertEquals("1.1 vegur",response.header("Via"));
        assertEquals("cloudflare",response.header("Server"));

        //2nd way
        assertEquals(expectedData.get("Via"),response.header("Via"));
        assertEquals(expectedData.get("Server"),response.header("Server"));

    }
}
