package get_request;

import baseUrl.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Get07 extends HerOkuAppBaseUrl {


        /*
        Given
            https://restful-booker.herokuapp.com/booking/36
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
      {
            "firstname": "Bradley",
            "lastname": "Pearson",
            "totalprice": 132,
            "depositpaid": false,
            "bookingdates": {
                "checkin": "2022-10-27",
                "checkout": "2022-11-07"
            },
            "additionalneeds": "None"
        }
     */

    @Test
    public void get(){

        //Set the Url
        spec.pathParams("first", "booking", "second",36);

        //Expected Data

        //Send a request and get the response
        Response response = given(spec).when().get("{first}/{second}");
//        response.prettyPrint();

        //Do Assertion
        //1st way
        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("John"),
                        "lastname", equalTo("Smith"),
                        "totalprice", equalTo(111),"depositpaid" ,equalTo(true),
                        "bookingdates.checkin",equalTo("2018-01-01"),
                        "bookingdates.checkout" ,equalTo("2019-01-01"),
                        "additionalneeds",equalTo("Breakfast"));

        //2nd Way
        JsonPath jsonPath = response.jsonPath();

        String firstname = jsonPath.getString("firstname");
        System.out.println("Firstname: " + firstname); // Josh

        int totalprice = jsonPath.getInt("totalprice");
        System.out.println("Total Price: " + totalprice);

        String checkIn = jsonPath.getString("bookingdates.checkin");
        System.out.println("CheckIn: " + checkIn);

        assertEquals(200,response.statusCode());
        assertTrue(response.contentType().contains("application/json"));
        assertEquals("John",jsonPath.getString("firstname"));
        assertEquals("Smith",jsonPath.getString("lastname"));
        assertEquals(111,jsonPath.getInt("totalprice"));
        assertEquals(true,jsonPath.getBoolean("depositpaid"));
        assertTrue(jsonPath.getBoolean("depositpaid"));
        assertEquals("2018-01-01",jsonPath.getString("bookingdates.checkin"));
        assertEquals("2019-01-01",jsonPath.getString("bookingdates.checkout"));
        assertEquals("Breakfast",jsonPath.getString("additionalneeds"));








    }
}
