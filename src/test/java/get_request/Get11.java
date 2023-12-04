package get_request;

import baseUrl.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestBaseUrl {

      /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Kannan Ahluwalia", "The Hon. Tara Chaturvedi" and "Damayanti Dubashi" are among the users
        And
            The male users are less than or equals to female users
            (Kadın kullanıcı sayısı erkek kullanıcı sayısından büyük yada eşit olmalı)
     */


    @Test
    public void get(){

        //Set the Url
        spec.pathParam("first","users");

        //Expected Data

        //Send the request and get the response
        Response response = given(spec).when().get("{first}");

        //Do Assertions
        JsonPath json = response.jsonPath();
        response.then().statusCode(200).
                body("meta.pagination.limit",equalTo(10),
                        "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data",hasSize(10),
                        "data.status", hasItem("active"),
                        "data.name", hasItems("Karthik Sethi", "Gautam Pilla", "Bankimchandra Kaur"));

        List<Object> numberOfFemale = json.getList("data.findAll{it.gender=='female'}");
        List<Object> numberOfMale = json.getList("data.findAll{it.gender=='male'}");
        System.out.println("numberOfMale = " + numberOfMale);
        System.out.println("numberOfFemale = " + numberOfFemale);
        System.out.println("numberOfMale.size() = " + numberOfMale.size());
        System.out.println("numberOfFemale.size() = " + numberOfFemale.size());

        assertTrue(numberOfMale.size() <=numberOfFemale.size());

        // Email bilgisi ile ID datasını çekme
        List<Integer> idList = json.getList("data.findAll{it.email=='nayar_anjushree@damore.example'}.id");
        System.out.println(idList);
        int id = idList.get(0);
        System.out.println(id);

        //ornek olarak gosterme amacli alttakini yapiyorum. Bu id yi su tarz yerlerde kullanabilirsin
//        spec.pathParams("first","todos","second",id);









    }
}
