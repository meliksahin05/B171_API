package post_requests;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.jsonplaceholder.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post03 extends JsonPlaceHolderBaseUrl {

        /*
    Given
       1) https://jsonplaceholder.typicode.com/todos
       2)  {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false
           }
    When
        I send POST Request to the Url
    Then
        Status code is 201
    And
        response body is like
        {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false,
            "id": 201
        }
     */

    @Test
    public void post(){

        // Set the URL
        specJsonPlaceHolder.pathParam("first", "todos");

        // Set the expected data
        JsonPlaceHolderPojo payLoad = new JsonPlaceHolderPojo(55, "Tidy your room", false);

        // Send the request and get the response
        Response response = given(specJsonPlaceHolder).body(payLoad).when().post("{first}"); // Serialization
        response.prettyPrint();


        //Do assertions
        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class); //De-Serialization
        //Yukarida payLoad datasi Pojo veri tipinde oldugundan dolayi biz burda response icin de-serialization uyguladik
        assertEquals(201, response.statusCode());
        assertEquals(payLoad.getUserId(), actualData.getUserId());
        assertEquals(payLoad.getTitle(), actualData.getTitle());
        assertEquals(payLoad.getCompleted(), actualData.getCompleted());

    }
}
