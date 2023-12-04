package get_request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01 {

/*
          Given
              https://restful-booker.herokuapp.com/booking/10
          When
              User sends a GET Request to the url
          Then
              HTTP Status Code should be 200
          And
              Content Type should be application/json
          And
              Status Line should be HTTP/1.1 200 OK
 */

    @Test
    public void get(){

       String url = "https://restful-booker.herokuapp.com/booking/10";

       given()
               .when()
               .get(url)
               .then()
               .statusCode(200)
               .contentType(ContentType.JSON)
               .statusLine("HTTP/1.1 200 OK");

    }

    @Test   //ikinci yontem
    public void get01(){

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "/booking/10";

        Response response = given().when().get();
//        response.prettyPrint();

        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");

    }

}
