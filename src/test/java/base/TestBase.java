package base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    public static RequestSpecification requestSpec;

    @BeforeAll
    public static void setUp() {

        System.setProperty("allure.results.directory", "allure-results");
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in")
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured()) // Allure теперь сам перехватывает все логи!
                .build();
    }
}
