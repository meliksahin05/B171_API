package put_requests;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static test_data.JsonPlaceHolderTestData.getPayLoad;

public class Put01 extends JsonPlaceHolderBaseUrl {

        /*
        Given
            1) https://jsonplaceholder.typicode.com/todos/198
            2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
            Kullanıcı URL'e bir PUT request gönderir
        Then
           Status code 200 olmalı
           Response şu şekilde olmalı:
           {
                "userId": 21,
                "title": "Wash the dishes",
                "completed": false
                "id": 198
           }
     */

    @Test
    public void put01(){

        //Set the url
        specJsonPlaceHolder.pathParams("first", "todos","second", 198);

        //Payload Data
        Map<String ,Object> payLoad = new HashMap<>();
        payLoad.put("userId",21);
        payLoad.put("title","Wash the dishes");
        payLoad.put("completed",false);

//        System.out.println("payLoad = " + payLoad);

        //Send the request and get the response
        Response response = given(specJsonPlaceHolder).body(payLoad).when().put("{first}/{second}");
//        response.prettyPrint();


        //Do Assertions
        Map<String, Object> actualData = response.as(HashMap.class);

        assertEquals(200,response.statusCode());
        assertEquals(payLoad.get("userId"),actualData.get("userId"));
        assertEquals(payLoad.get("title"),actualData.get("title"));
        assertEquals(payLoad.get("completed"),actualData.get("completed"));



    }

    @Test
    public void put02MapInTestData() {

        //Set the url
        specJsonPlaceHolder.pathParams("first", "todos","second", 198);

        //Payload Data
        Map<String, Object> payLoad = getPayLoad(21,"Wash the dishes",false);

        //send the request and get the response
        Response response = given(specJsonPlaceHolder).body(payLoad).when().put("{first}/{second}");
//        response.prettyPrint();


        //Do assertions
        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(200, response.statusCode());
        assertEquals(payLoad.get("userId"), actualData.get("userId"));
        assertEquals(payLoad.get("title"), actualData.get("title"));
        assertEquals(payLoad.get("completed"), actualData.get("completed"));



    }
}
