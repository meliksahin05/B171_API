package get_request;

import baseUrl.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get04 extends PetStoreBaseUrl {


/*
          Given
              https://petstore.swagger.io/v2/pet/9920
          When
              User sends a GET Request to the url
          Then
              HTTP Status Code should be 200
          And
              Content Type should be application/json
          And
              Status Line should be HTTP/1.1 200 OK
 */
//    i) Set the Url
//    ii) Set the Expected Data
//    iii) Send Request And Get Response
//    iv)  Do Assertions

     @Test
    public void get(){

//    i) Set the Url
       spec.pathParams("first","pet","second",9920);
//    ii) Set the Expected Data

//    iii) Send Request And Get Response
         Response response = given(spec).when().get("{first}/{second}");
//         response.prettyPrint();
//    iv)  Do Assertions
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .statusLine("HTTP/1.1 200 OK");




     }
}
