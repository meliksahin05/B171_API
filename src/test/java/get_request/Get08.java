package get_request;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get08 extends JsonPlaceHolderBaseUrl {

        /*
        Given
           https://jsonplaceholder.typicode.com/todos
        When
           Kullanıcı URL'e bir GET request gönderir
        Then
           1) Status code 200 olmalı
           2) "Id"leri 190 dan büyük olanları konsola yazdırın
              "Id"si 190 dan büyük 10 adet veri olduğunu doğrulayın
           3) "Id"si 5 den küçük olan tüm kullanıcıların "userid"lerini yazdırın
              "Id"si 5 den küçük olan 4 kullanıcı olduğunu doğrulayın
           4) "Id" si 5 ten küçük tüm kullanıcıların "title" larını yazdırın
              "delectus aut autem" başlığının, id numarası 5 den küçük bir kullanıcıya ait olduğunu doğrulayın
     */

    @Test
    public void get(){

        //Set the Url
        specJsonPlaceHolder.pathParam("first", "todos");

        // Set the expected data

        // Send the request and get the response
        Response response = given(specJsonPlaceHolder).when().get("{first}");
//        response.prettyPrint();

        //Do assertions
        assertEquals(200,response.statusCode());

        JsonPath json = response.jsonPath();



        List<Object> idList = json.getList("id");
        System.out.println("idList = " + idList);

//        2) "Id"leri 190 dan büyük olanları konsola yazdırın

        List<Object> idListHigherThan190 = json.getList("findAll{it.id>190}.id");
        System.out.println("idListHigherThan190 = " + idListHigherThan190);

        assertEquals(10,idListHigherThan190.size());

//        3) "Id"si 5 den küçük olan tüm kullanıcıların "userid"lerini yazdırın
        List<Integer> userIdList = json.getList("findAll{it.id<5}.userId");
        System.out.println("userIdList = " + userIdList);

//        "Id"si 5 den küçük olan 4 kullanıcı olduğunu doğrulayın
        assertEquals(4,userIdList.size());

//        4) "Id" si 5 ten küçük tüm kullanıcıların "title" larını yazdırın
        List<String> titleList = json.getList("findAll{it.id<5}.title");
        System.out.println("title = " + titleList);

//        5)"delectus aut autem" başlığının, id numarası 5 den küçük bir kullanıcıya ait olduğunu doğrulayın
        assertTrue(titleList.contains("delectus aut autem"));

        // Unique Değerler: TC No, Okul No, Tel No, Mail, Username
        List<Integer> id = json.getList("findAll{it.title=='delectus aut autem'}.id");
        System.out.println("ID: " + id);
        int idcik = id.get(0);
        System.out.println(idcik);

    }





}