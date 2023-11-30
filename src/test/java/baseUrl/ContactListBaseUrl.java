package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import static utils.AuthenticationContactList.generateToken;

public class ContactListBaseUrl {


    protected RequestSpecification spec;
    @Before
    public void setUpUrl(){

        String baseUrl = "https://thinking-tester-contact-list.herokuapp.com";

        spec = new RequestSpecBuilder().
                setBaseUri(baseUrl).
                addHeader("Authorization","Bearer " + generateToken()).
                build();
    }
}
