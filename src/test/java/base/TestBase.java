package base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    public static RequestSpecification requestSpec;

     @BeforeAll
    public static void setUp(){
         requestSpec = new RequestSpecBuilder()
                 .setBaseUri("https://reqres.in")
                 .setContentType(ContentType.JSON)
                 .build();
     }
}
