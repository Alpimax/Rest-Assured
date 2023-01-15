package com.cydeo.utilities;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HomeWorkFlow extends SpartanTestBase {

    static Map<String, Object> spartanMap = new HashMap<>();

    @Order(value = 1)
    @Test()
    public void testPost() {

        spartanMap.put("name","API flow POST");
        spartanMap.put("gender","Male");
        spartanMap.put("phone",1231231231L);

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartanMap)
                .when().post("/api/spartans")
                .then().statusCode(201).extract().jsonPath();

        spartanMap.put("id", jsonPath.getInt("data.id"));
        String expectedMsg = "A Spartan is Born!";
        assertEquals(expectedMsg,jsonPath.getString("success"));

    }
    @Order(value = 2)
    @Test
    public void testGet() {

        JsonPath jsonPath = given()
                .and().pathParam("id", spartanMap.get("id"))
                .get("/api/spartans/{id}")
                .then().statusCode(200).extract().jsonPath();
        assertEquals("API flow POST",jsonPath.getString("name"));

    }
    @Order(value = 3)
    @Test

    public void testPUT() {

        spartanMap.put("name","API PUT Flow");
        spartanMap.put("gender","Female");
        spartanMap.put("phone",3213213213L);
        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("id",spartanMap.get("id"))
                .body(spartanMap)
                .when().put("/api/spartans/{id}")
                .then().statusCode(204);

    }
    @Order(value = 4)
    @Test
    public void testGetForPut() {

        JsonPath jsonPath = given()
                .and().pathParam("id", spartanMap.get("id"))
                .get("/api/spartans/{id}")
                .then().statusCode(200).extract().jsonPath();
        assertEquals("API PUT Flow",jsonPath.getString("name"));
    }
    @Order(value = 5)
    @Test
    void testDelete() {

        given()
                .pathParam("id",spartanMap.get("id"))
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);

    }

    @Order(value = 6)
    @Test
    public void testGetFor(){

        given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().pathParam("id",spartanMap.get("id"))
                .and().get("/api/spartans/{id}")
                .then().statusCode(404);

    }
}
