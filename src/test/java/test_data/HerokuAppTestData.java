package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerokuAppTestData {

    public static Map<String, String> getBookingDates (String checkin, String checkout){

        Map<String, String> bookingdatesData = new HashMap<>();
        bookingdatesData.put("checkin" , checkin);
        bookingdatesData.put("checkout" , checkout);

        return bookingdatesData;
    }




    public static Map<String, Object> getExpectedData (String firstname,String lastname,
                                                    Integer totalprice,Boolean depositpaid,
                                                    Map<String ,String> bookingdatesDates,
                                                    String additionalneeds){
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", firstname);
        expectedData.put("lastname", lastname);
        expectedData.put("totalprice", totalprice);
        expectedData.put("depositpaid", depositpaid);
        expectedData.put("bookingdatesDates", bookingdatesDates);
        expectedData.put("additionalneeds", additionalneeds);


        return expectedData;
    }









//    {
//        "firstname": "Jane",
//            "lastname": "Doe",
//            "totalprice": 111,
//            "depositpaid": true,
//            "bookingdates": {
//        "checkin": "2018-01-01",
//                "checkout": "2019-01-01"
//    },
//        "additionalneeds": "Extra pillows please"
//    }
}
