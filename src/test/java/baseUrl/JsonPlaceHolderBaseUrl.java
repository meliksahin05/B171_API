package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseUrl {

   protected RequestSpecification specJsonPlaceHolder;

    @Before
    public void setUpJsonPlaceHolder(){

        String baseUrl = "https://jsonplaceholder.typicode.com";

      specJsonPlaceHolder = new RequestSpecBuilder().
                setBaseUri(baseUrl).
                setContentType(ContentType.JSON).
                build();
    }
}
