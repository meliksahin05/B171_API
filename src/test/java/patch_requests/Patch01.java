package patch_requests;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.getPayLoad;

public class Patch01 extends JsonPlaceHolderBaseUrl {

    /*
    Given
        1) https://jsonplaceholder.typicode.com/todos/198
        2) {
              "title": "Wash the dishes"
           }
    When
      I send PATCH Request to the Url
    Then
          Status code is 200
          And response body is like
              {
                "userId": 10,
                "title": "Wash the dishes",
                "completed": true,
                "id": 198
              }
     */

    @Test
    public void patch01() {

        //Set the url
        specJsonPlaceHolder.pathParams("first", "todos", "second",198);

        //Expected Data

        Map<String, Object> payLoad = getPayLoad(null,"Wash the dishes",null);
        Map<String, Object> expectedData = getPayLoad(10,"Wash the dishes",true);

        //send the request and get the response

        Response response = given(specJsonPlaceHolder).
                body(payLoad).
                when().
                patch("{first}/{second}");//Serilization yapti burda Java objesi olan map i json dataya donusturdu
        response.prettyPrint();


//        payLoad.put("userId",10);      //bunlari requesten sonra eklemen lazim.Bunlari dedigim kisim patch yaparken degistirmedigin veriler.Veya bunun yerine expectedData mapi olusturcaksin
//        payLoad.put("completed",true);  //bunlari requesten sonra eklemen lazim.Bunlari dedigim kisim patch yaparken degistirmedigin veriler.Veya bunun yerine expectedData mapi olusturcaksin
//        Sonrasinda da payLoad.get diyip butun datalari actual datayla karsilastirabilirsin.....

        //Do Assertions
        Map<String , Object> actualData = response.as(HashMap.class);//De-Serilization
        assertEquals(200,response.statusCode());

        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));





    }
}
