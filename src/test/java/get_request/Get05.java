package get_request;

import baseUrl.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get05 extends PetStoreBaseUrl {

        /*
        Given
            https://petstore.swagger.io/v2/pet/findByStatus?status=available
        When
            Kullanıcı URL'e bir GET request gönderir
        Then
            HTTP Status Code 200 olmalı
        And
            Content Type "application/json" olmalı
        And
            Listede id değeri 9898 olan bir eleman olmalı
        And
            Listede name değeri "Evlat" olan bir eleman olmalı
        And
            Listede name değerleri "Pamuk", "doggie", "fish" olan elemanlar olmalı
        And
            Listede en az 200 tane eleman olmalı
        And
            Listede 500'den az eleman olmalı
        And
            Listenin ilk elemanının category - id değeri 0 olmalı
        And
            Listenin ilk elemanının photoUrls değeri "string" olmalı
        And
            Listenin ilk elemanının tag - id değeri 0 olmalı
     */

    @Test
    public void get(){

//    i) Set the Url
        spec.pathParams("first","pet","second","findByStatus")
                .queryParam("status","available");
//    ii) Set the Expected Data
//    iii) Send Request And Get Response
        Response response = given(spec).when().get("{first}/{second}");
//        response.prettyPrint();
//    iv)  Do Assertions
        response.
                then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id" ,hasItem(9898)
                        ,"name",hasItem("Evlat"),
                        "name",hasItems("Evlat","doggie","fish"),
                        "id",hasSize(greaterThan(200)),
                        "id",hasSize(lessThan(558)),
                        "[2].category.id",equalTo(0),
                        "[2].photoUrls[0]",equalTo("string"),
                        "[2].tags[0]. id",equalTo(0));
    }
}
