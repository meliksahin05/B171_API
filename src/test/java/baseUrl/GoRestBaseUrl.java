package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import static utils.AuthenticationContactList.generateToken;

public class GoRestBaseUrl {


    protected RequestSpecification spec;
    @Before
    public void setUpUrl(){

        String baseUrl = "https://gorest.co.in/public/v1";

        spec = new RequestSpecBuilder().
                setBaseUri(baseUrl).
                build();
    }
}
