package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.junit.Before;

public class PetStoreBaseUrl {

    protected RequestSpecification spec;
    @Before
    public void setUpUrl(){

        String baseUrl = "https://petstore.swagger.io/v2";

        spec = new RequestSpecBuilder().
                setBaseUri("https://petstore.swagger.io/v2").
                setContentType(ContentType.JSON).build();
    }
}
