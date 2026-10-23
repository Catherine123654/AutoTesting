package api;

import base.TestBase;
import models.CreateUserRequest;
import models.UserResponse;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class StatusTest extends TestBase {

    @Test
    void GetUsersList(){
        given()
                .spec(requestSpec)
                .log().all()
        .when()
                .get("/api/users?page=2")
        .then()
                .log().all()
                .statusCode(200)
                .body("page" , equalTo(2))
                .body("data",not(empty()))
                .body("data[0].email" ,containsString("@"));
    }

    @Test
    void PostCreateUser(){
        CreateUserRequest user = new CreateUserRequest("morpheus","leader");

        UserResponse response = given()
                .spec(requestSpec)
                .body(user)
                .log().all()
        .when()
                .post("/api/users")
        .then()
                .log().all()
                .statusCode(201)

//    если не использовать USerResponse
//                .body("id",notNullValue())
//                .body("createdAt",notNullValue())
//                .body("name" , equalTo("morpheus"))
//                .body("job", equalTo("leader"));
                .extract()
                .as(UserResponse.class);

        assertEquals("morpheus", response.getName());
        assertEquals("leader", response.getJob());
        assertNotNull(response.getId());
        assertNotNull(response.getCreatedAt());
    }

    @Test
    void DeleteUser(){
        given()
                .spec(requestSpec)
                .log().all()
        .when()
                .delete("/api/users/2")
        .then()
                .log().all()
                .statusCode(204)
                .body(is(emptyString()));
    }

    @Test
    void NegativeTest() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "sydney@fiscus");
        given()
                .spec(requestSpec)
                .body(requestBody)
                .log().all()
        .when()
                .post("/api/register")
        .then()
                .log().all()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }
}

