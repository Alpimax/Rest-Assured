package com.cydeo.utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public class homework extends CydeoTrainingTestBase {


    @DisplayName("homework one ")
    @Test
    public void test() {
        Response response = RestAssured.given().accept(ContentType.JSON).
                pathParam("country_id", "US").get("/countries/{country_id}");
//        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();

        jsonPath.prettyPrint();
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        Assertions.assertEquals(ContentType.JSON.toString(), response.getContentType());
        Assertions.assertEquals(ContentType.JSON.toString(), response.getContentType());

    }

    //    Given accept type is application/json
//    And query param limit is 200
//    When user send request / employees


    @DisplayName("Get All employees?limit 200 ")
    @Test
    public void test01() {
        var response = RestAssured.given().accept(ContentType.JSON)
                .queryParam("limit", 200).when().get("/employees");
        JsonPath jsonPath = response.jsonPath();
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        var email = jsonPath.getList("items.email");
        var set = new HashSet<>(email);

        System.out.println(email.size());
        System.out.println(set.size());

        var emailsIt = jsonPath.getList("items.findAll {it.job_id == 'IT_PROG'}.email");
        System.out.println(emailsIt);

        var allEmployeeSalary = jsonPath.getList("items.findAll{it.salary>=10000}.first_name");
        System.out.println(allEmployeeSalary);


        // get me firstname from response  who has max salary
        System.out.println(jsonPath.getString("items.max{it.salary}.first_name"));
        // get me firstname from response  who has min salary

        System.out.println(jsonPath.getString("items.min{it.salary}.first_name"));


    }

    @DisplayName("Get All employees?limit 200 ")
    @Test
    public void test02() {
//  TASK
//    Given
//             accept type is application/json
//     When
//             user sends get request to /locations
//     Then
//             response status code must be 200
//             content type equals to application/json
//             get the second city with JsonPath
//             get the last city with JsonPath
//             get all country ids
//             get all city where their country id is UK
//
//      */
    }

    @DisplayName("Test2")
    @Test
    public void test03() {
//        RestAssured.baseURI = "http;//44.211.223.224:8000";
//        var response = RestAssured.given().accept(ContentType.JSON).
//                and().pathParam("")





    }


}
