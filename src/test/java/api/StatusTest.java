package api;

import base.TestBase;
import io.qameta.allure.*;
import models.CreateUserRequest;
import models.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static base.RoutesPatern.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class StatusTest extends TestBase {
    @Epic("Управление пользователями")                  // Группировка верхнего уровня (Бизнес-контейнер)
    @Feature("Создание профиля")                        // Какая именно функция тестируется
    @Story("Успешное создание нового сотрудника")        // Конкретный сценарий / User Story
    @Description("Тест проверяет, что при отправке валидных данных пользователя, сервер успешно создает запись и возвращает ID с временной меткой")
    @Severity(SeverityLevel.BLOCKER)                    // Уровень критичности теста
    @DisplayName("Успешное создание пользователя (POST)")

    @Test
    void GetUsersList() {
        given()
                .spec(requestSpec)
                .queryParam("page", 2)
                .log().all()
                .when()
                .get(GET_USERS)
                .then()
                .log().all()
                .statusCode(200)
                .body("page", equalTo(2))
                .body("data", not(empty()))
                .body("data[0].email", containsString("@"));
    }

    @Test
    void PostCreateUser() {
        Allure.step("Подготовка тестовых данных");
        CreateUserRequest user = new CreateUserRequest("morpheus", "leader");

        UserResponse response = given()
                .spec(requestSpec)
                .body(user)
                .log().all()
                .when()
                .post(POST_USERS)
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
    void DeleteUser() {
        CreateUserRequest testUser = new CreateUserRequest("test", "tester");

        UserResponse createdUser = given()
                .spec(requestSpec)
                .body(testUser)
                .when()
                .post(POST_USERS)
                .then()
                .statusCode(201)
                .extract()
                .as(UserResponse.class);
        // Шаг 2: Удаляем ИМЕННО ЭТОГО только что созданного пользователя
        String dynamicId = createdUser.getId();
        given()
                .spec(requestSpec)
                .pathParams("id", dynamicId)
                .log().all()
                .when()
                .delete(DELETE_USER)
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
                .post(REGISTER)
                .then()
                .log().all()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }

    @Test
    void PutUpdateUser() {
        CreateUserRequest updateData = new CreateUserRequest("morpheus", "zion resident");
        int userId = 2;
        UserResponse updateResponse = given()
                .spec(requestSpec)
                .pathParams("id", userId)
                .body(updateData)
                .log().all()
                .when()
                .put(PUT_USER, 2)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(UserResponse.class); // <-- Вот тут происходит десериализация!

        assertEquals("morpheus", updateResponse.getName());
        assertEquals("zion resident", updateResponse.getJob());
        assertNotNull(updateResponse.getUpdatedAt());
    }
}

