package get_request;

import baseUrl.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static test_data.HerokuAppTestData.getBookingDates;
import static test_data.HerokuAppTestData.getExpectedData;

public class Get10 extends HerOkuAppBaseUrl {

       /*
    Given
        https://restful-booker.herokuapp.com/booking/13
    When
        I send GET Request to the url
    Then
        Response body should be like that;
            {
                "firstname": "Jane",
                "lastname": "Doe",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Extra pillows please"
            }
     */

    @Test
    public void get(){

        //Set the url
        spec.pathParams("first", "booking" , "second" , 18);

        //expected Data
        Map<String, String> bookingDates = getBookingDates("2018-01-01", "2019-01-01");
        Map<String, Object> expectedData = getExpectedData("Jane", "Doe",111,
                true,bookingDates,"Extra pillows please");

        System.out.println("bookingDates = " + bookingDates);
        System.out.println(expectedData);

        //Send the Request and get the response
        Response response = given(spec).when().get("{first}/{second}");
//        response.prettyPrint();


        //Do assertions
        Map<String, Object> actuaData = response.as(HashMap.class);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"),actuaData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actuaData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actuaData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actuaData.get("depositpaid"));

//        1nd way (Bunu kullanmak yerine json i kullanmak daha mantikli
        assertEquals(bookingDates.get("checkin"),((Map)actuaData.get("bookingdates")).get("checkin"));
        assertEquals(bookingDates.get("checkout"),((Map)actuaData.get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"),actuaData.get("additionalneeds"));



        //2nd way(json uzerinden yapmak daha mantikli
        JsonPath json = response.jsonPath();

        assertEquals(bookingDates.get("checkin"),json.getString("bookingdates.checkin"));
        assertEquals(bookingDates.get("checkout"),json.getString("bookingdates.checkout"));
        assertEquals(expectedData.get("additionalneeds"),actuaData.get("additionalneeds"));

        //3rd way
        response.then().body("bookingdates.checkin", is("2018-01-01"));
        response.then().body("bookingdates.checkout", is("2019-01-01"));


    }
}
