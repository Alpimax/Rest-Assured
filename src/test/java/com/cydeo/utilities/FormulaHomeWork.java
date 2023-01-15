package com.cydeo.utilities;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;


import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FormulaHomeWork extends FormulaAPITestBase {

    //- Given accept type is json
//- And path param driverId is alonso
//- When user send request /drivers/{driverId}.json
//- Then verify status code is 200
//- And content type is application/json; charset=utf-8
//- And total is 1
//- And givenName is Fernando
//- And familyName is Alonso
//- And nationality is Spanish
    @DisplayName("Verify Formula with JsonPath")
    @Test
    public void jsonPathTask() {
        Response response = given().accept(ContentType.JSON).and().pathParam("driverId", "alonso").
                when().get("drivers/{driverId}.json");


        JsonPath js = response.jsonPath();
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());
        assertEquals("1", js.getString("MRData.total"));
        assertEquals("Fernando", js.getString("MRData.DriverTable.Drivers[0].givenName"));
        assertEquals("Alonso", js.getString("MRData.DriverTable.Drivers[0].familyName"));
        assertEquals("Spanish", js.getString("MRData.DriverTable.Drivers[0].nationality"));
    }

    @DisplayName("Verify Formula with hamCrest")
    @Test
    public void hamCrestTask() {

        given().accept(ContentType.JSON)
                .pathParam("driverId", "alonso").when()
                .get("drivers/{driverId}.json")
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .contentType("application/json; charset=utf-8")
                .body("MRData.total", is("1"))
                .body("MRData.DriverTable.Drivers[0].givenName", equalTo("Fernando"))
                .body("MRData.DriverTable.Drivers[0].familyName", equalTo("Alonso"))
                .body("MRData.DriverTable.Drivers[0].nationality", is("Spanish"));

    }

    @DisplayName("Verify Formula with Java Structure")
    @Test
    public void javaStructureTask() {

        Response response = given().accept(ContentType.JSON).and().pathParam("driverId", "alonso").when()
                .get("drivers/{driverId}.json");

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());

        JsonPath js = response.jsonPath();

        Map<String, Map<String, String>> mrdate = js.getMap("MRData");
        //noinspection AssertBetweenInconvertibleTypes
        assertEquals("1", mrdate.get("total"));
        List<Map<String, String>> driver = js.getList("MRData.DriverTable.Drivers");
        assertEquals("Fernando", driver.get(0).get("givenName"));
        assertEquals("Alonso", driver.get(0).get("familyName"));
        assertEquals("Spanish", driver.get(0).get("nationality"));

    }

    @DisplayName("Verify Formula with Pojo")
    @Test
    public void Pojo() {
        Response response = given().accept(ContentType.JSON).when()
                .get("drivers.json");

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());

        JsonPath js = response.jsonPath();


//        List<Driver> driver = js.getList("MRData.DriverTable.Drivers",Driver.class);
//
//        for (Driver each :driver)
//        {
//            if (    each.getGivenName().equals("Fernando") &&
//                    each.getFamilyName().equals("Alonso") &&
//                    each.getNationality().equals("Spanish")){
//                System.out.println(each);
//            }
//        }


    }

//- Given accept type is json
//- When user send request /constructorStandings/1/constructors.json
// - Then verify status code is 200
//- And content type is application/json; charset=utf-8
//- And total is 17
//- And limit is 30
//- And each constructor has constructorId
//- And constructor has name
//- And size of constructor is 17 (using with hasSize)
//- And constructor IDs has “benetton”, “mercedes”,”team_lotus”
//- And names are in same order by following list

    @DisplayName("Verify Formula Task 2 with JsonPath")
    @Test
    public void jsonPathTask2() {

        var response = given().accept(ContentType.JSON)
                .when().get("constructorStandings/1/constructors.json");
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());
        JsonPath js = response.jsonPath();
        assertEquals(js.getInt("MRData.total"), 17);
        assertEquals(js.getInt("MRData.limit"), 30);
        assertThat(js.getList("MRData.ConstructorTable.Constructors.constructorId"), everyItem(notNullValue()));
        assertThat(js.getList("MRData.ConstructorTable.Constructors.name"), everyItem(notNullValue()));
        assertThat(js.getList("MRData.ConstructorTable.Constructors"), hasSize(17));
        assertThat(js.getList("MRData.ConstructorTable.Constructors.constructorId"), hasItems("benetton", "mercedes", "team_lotus"));


    }

    @DisplayName("Verify Formula Task 2 with HamCrest")
    @Test
    public void hamCrestTaskTask2() {

        given().accept(ContentType.JSON)
                .get("constructorStandings/1/constructors.json")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType("application/json; charset=utf-8")
                .body("MRData.total", is("17"))
                .body("MRData.limit", is("30"))
                .body("MRData.ConstructorTable.Constructors.constructorId", everyItem(notNullValue()))
                .body("MRData.ConstructorTable.Constructors.name", everyItem(notNullValue()))
                .body("MRData.ConstructorTable.Constructors", hasSize(17))
                .body("MRData.ConstructorTable.Constructors.constructorId", hasItems("benetton", "mercedes", "team_lotus"))
                .body("MRData.ConstructorTable.Constructors.name", containsInRelativeOrder("Benetton", "Brabham-Repco", "Brawn", "BRM", "Cooper-Climax", "Ferrari", "Lotus-Climax", "Lotus-Ford", "Matra-Ford", "McLaren", "Mercedes", "Red Bull", "Renault", "Team Lotus", "Tyrrell", "Vanwall", "Williams"));

    }


    @DisplayName("Verify Formula Task 2 with java Structure")
    @Test
    public void javaTaskTask2() {

        var responce = given().accept(ContentType.JSON)
                .when().get("constructorStandings/1/constructors.json");

        assertEquals(HttpStatus.SC_OK, responce.statusCode());
        assertEquals("application/json; charset=utf-8", responce.contentType());


    }

    @DisplayName("Verify Formula Task 2 with java Structure")
    @Test
    public void pojo() {


    }


}

