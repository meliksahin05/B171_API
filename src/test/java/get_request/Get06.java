package get_request;

import baseUrl.ContactListBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get06 extends ContactListBaseUrl {

        /*
        Given https://thinking-tester-contact-list.herokuapp.com/contacts

        When
            Kullanıcı URL'e bir GET request gönderir
        Then
            HTTP Status Code 200 olmalı
        And
            Content Type "application/json" olmalı
     */
//    i) Set the Url
//    ii) Set the Expected Data
//    iii) Send Request And Get Response
//    iv)  Do Assertions

    @Test
    public void get(){

//        i) Set the Url
        spec.pathParam("first","contacts");
//        ii) Set the Expected Data
//        iii) Send Request And Get Response
        Response response = given(spec).when().get("{first}");
        response.prettyPrint();
//        iv)  Do Assertions
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON);

    }

}
