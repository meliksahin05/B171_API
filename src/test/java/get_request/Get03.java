package get_request;

import com.fasterxml.jackson.databind.util.JSONPObject;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Get03 {

//    Given
//    https://petstore.swagger.io/v2/pet/9920
//    When
//    User send a GET Request to the url
//    Then
//    HTTP Status code should be 200
//    And
//    Content Type "application/json" olmali
//    And
//    "name" su metni icermeli "Pamuk",
//    And
//    "status" degeri "available" olmali
//    And
//    "category" altindaki "name" degeri "Kopek" olmali
//    And
//    "tags" altindaki "name" degeri "Sibirya Kurdu" olmali

    @Test
    public void get(){

//        i) Set the Url
          String url = "https://petstore.swagger.io/v2/pet/9920";
//        ii) Set the Expected Data
        Map<String, Object> expData = new HashMap<>();
        expData.put("name", "Pamuk");
        expData.put("category.name","Kopek");
        expData.put("tags[0].name","Sibirya Kurdu");
        expData.put("status","available");

//        iii) Send Request And Get Response
        Response response = given().when().get(url);
//        response.prettyPrint();
//        iv)  Do Assertions
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("name",equalTo("Pamuk"),
                        "category.name",equalTo("Kopek"),
                        "tags[0].name",equalTo("Sibirya Kurdu"),
                        "status",equalTo("available"));




        JsonPath actualBody = response.jsonPath();
        assertEquals("Pamuk", actualBody.getString("name"));
        assertEquals("Kopek", actualBody.getString("category.name"));
        assertEquals("Sibirya Kurdu", actualBody.getString("tags[0].name"));
        assertEquals("available", actualBody.getString("status"));

        assertEquals(expData.get("name"), actualBody.getString("name"));
        assertEquals(expData.get("category.name"), actualBody.getString("category.name"));
        assertEquals(expData.get("tags[0].name"), actualBody.getString("tags[0].name"));
        assertEquals(expData.get("status"), actualBody.getString("status"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(expData.get("name"), actualBody.getString("name"));
        softAssert.assertEquals(expData.get("category.name"), actualBody.getString("category.name"));
        softAssert.assertEquals(expData.get("tags[0].name"), actualBody.getString("tags[0].name"));
        softAssert.assertEquals(expData.get("status"), actualBody.getString("status"));

        softAssert.assertAll();
    }




}
