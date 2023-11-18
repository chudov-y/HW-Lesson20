
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class RestApiTest extends TestBase {

    @Test
    void listUsersTest() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("page", is(2))
                .body("total", is(12));
                 }

                 @Test
                 void singleUsersTest() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .assertThat().body("data.id", equalTo(2), "data.email", is("janet.weaver@reqres.in"))
                .body("support.url", is("https://reqres.in/#support-heading"));
                 }


                @Test
                void createUsersTest() {
                String authBody = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";
        given()
                .log().uri()
                .log().method()
                .log().body()
                .body(authBody)
                .contentType(JSON)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
                 }


                 @Test
                void registerSuccessfulTest() {
                String authBody = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";
        given()
                .log().uri()
                .log().method()
                .log().body()
                .body(authBody)
                .contentType(JSON)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
                 }


                 @Test
                void registerUnSuccessfulTest() {
                String authBody = "{ \"email\": \"sydney@fife\" }";
        given()
                .log().uri()
                .log().method()
                .log().body()
                .body(authBody)
                .contentType(JSON)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
                 }
}
