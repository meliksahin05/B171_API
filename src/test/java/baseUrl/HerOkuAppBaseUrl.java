package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerOkuAppBaseUrl {

    protected RequestSpecification spec;


    @Before
    public void setUpHerOkuBaseUrl(){

        String baseUrl = "https://restful-booker.herokuapp.com";

        spec = new RequestSpecBuilder().
                setBaseUri(baseUrl).
                build();
    }
}
