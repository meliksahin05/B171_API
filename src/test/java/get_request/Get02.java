package get_request;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get02 {

//    Given
//    https://petstore.swagger.io/v2/pet/0
//    When
//    User send a GET Request to the url
//    Then
//    HTTP Status code should be 404
//    And
//    Status Line should be HTTP/1.1 404 Not Found
//    And
//    Response body contains "Not Found"
//    And
//    Response body does not contain "TechProEd"
//    And
//    Server is "Jetty(9.2.9.v20150224"

    @Test
    public void get(){


        //Set the url
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured.basePath ="/pet/0";

        //Expected Data

        //Send the request and get the response
        Response response = given().when().get();
        response.prettyPrint();

        //Do assertions
        response
                .then()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
                .body(containsString("Pet not found"))
                .body(not("TechProEd"))
                .header("Server","Jetty(9.2.9.v20150224)");

    }
}
