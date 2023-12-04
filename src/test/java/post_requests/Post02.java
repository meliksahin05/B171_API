package post_requests;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Post02 extends JsonPlaceHolderBaseUrl {
    /*
    Given
       1) https://jsonplaceholder.typicode.com/todos
       2)  {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false
           }
        When
            Kullanıcı URL'e bir POST request gönderir
        Then
            Status code 201 olmalı
        And
            Response şu şekilde olmalı:
            {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false,
                "id": 201
            }
     */


    @Test
    public void post(){

        //Set the url
        specJsonPlaceHolder.pathParam("first", "todos");

        // Set the expected data (payLoad)
        // Payload: Body içerisinde POST Request ile gönderdiğimiz data
        // Expected Data: Request sonucunda response'tan gelmesini beklediğimiz data
        Map<String , Object> payLoad = new HashMap<>();
        payLoad.put("userId",55);
        payLoad.put("title","Tidy your room");
        payLoad.put("completed", false);


        // Send the request and get the response
        // Serialization: Java datalarının Json datalarına dönüştürülmesi.
        Response response = given(specJsonPlaceHolder).body(payLoad).when().post("{first}");
        response.prettyPrint();

        //Do assertions
        //1st way
        JsonPath json = response.jsonPath();

        assertEquals(201,response.statusCode());
        assertEquals(payLoad.get("userId"),json.getInt("userId"));
        assertEquals(payLoad.get("title"),json.getString("title"));
        assertFalse(json.getBoolean("completed"));


        //2nd way
        // De-Serialization: Json datanın Java datasına dönüştürülmesi.
        Map<String , Object> actualData = response.as(HashMap.class);
        assertEquals(201 , response.statusCode());

        assertEquals(payLoad.get("userId"), actualData.get("userId"));
        assertEquals(payLoad.get("title"), actualData.get("title"));
        assertEquals(payLoad.get("completed"), actualData.get("completed"));







    }

}
