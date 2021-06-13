package test;

import framework.ErrorModel;
import framework.userModels.UserModel;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.testng.annotations.Test;

import static framework.model.endPoint.UserPoint.GET_USER_BY_ID;
import static framework.model.endPoint.UserPoint.GET_USER_BY_NAME;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTest extends BaseTest {

    @Test
    public void chekStatusCode() {
        String expectedName = "user1";

        given()
                .pathParams("userName", expectedName)
                .when()
                .get(GET_USER_BY_NAME)
                .then()
                .statusCode(200);
    }

    @Test
    public void getMessage() {
        String expectedName = "user8877";

        ErrorModel errorModel = given()
                .pathParam("userName", expectedName)
                .when()
                .get(GET_USER_BY_NAME)
                .then()
                .statusCode(404)
                .extract()
                .body()
                .as(ErrorModel.class);

        assertThat(errorModel.getMessage()).isEqualTo("User not found");
    }
}
