package com.cydeo.day06;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class P02_SpartanDeserilizationPOJO extends SpartanTestBase {

    @DisplayName("GET Single Spartan for deserialization to POJO ")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15).
                when().get("/api/spartans/{id}").prettyPeek().
                then()
                .statusCode(200).extract().response();

        /*
            {
                "id": 15,
                "name": "Meta",
                "gender": "Female",
                "phone": 1938695106
            }
         */
        // RESPONSE
        System.out.println(" ----- RESPONSE -----");

        Spartan spartan = response.as(Spartan.class);
        System.out.println("spartan = " + spartan);
        System.out.println(spartan.getId());
        System.out.println(spartan.getName());
        System.out.println(spartan.getGender());
        System.out.println(spartan.getPhone());

        // JSONPATH
        System.out.println(" ----- JSON -----");
        JsonPath jsonPath = response.jsonPath();
        Spartan sp = jsonPath.getObject("", Spartan.class);
        System.out.println("sp = " + sp);
        System.out.println(sp.getId());
        System.out.println(sp.getName());
        System.out.println(sp.getGender());
        System.out.println(sp.getPhone());


    }
}
