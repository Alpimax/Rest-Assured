package com.cydeo.day07;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class P02_SpartanPOST extends SpartanTestBase {

    /**
     Given accept type is JSON
     And Content type is JSON
     And request json body is:
     {
     "gender":"Male",
     "name":"John Doe",
     "phone":8877445596
     }
     When user sends POST request to '/api/spartans'
     Then status code 201
     And content type should be application/json
     And json payload/response/body should contain:
     verify the success value is A Spartan is Born!
     "name": "John Doe",
     "gender": "Male",
     "phone": 1231231231
     */
    @DisplayName("POST spartan with String body")
    @Test
    public void test1() {

        String requestBody="{\n" +
                "     \"gender\":\"Male\",\n" +
                "     \"name\":\"John Doe\",\n" +
                "     \"phone\":8877445596\n" +
                "     }";

        String expectedMessage="A Spartan is Born!";

        JsonPath jsonPath = given().accept(ContentType.JSON).log().body()// API send me response in JSON format
                .contentType(ContentType.JSON) // API I am sending body in JSON format
                .body(requestBody).  // This is the that we wnna POST in API
                        when().post("/api/spartans").prettyPeek().
                then().statusCode(201)
                .contentType("application/json").extract().jsonPath();


        assertEquals(expectedMessage,jsonPath.getString("success"));
        assertEquals("John Doe",jsonPath.getString("data.name"));
        assertEquals("Male",jsonPath.getString("data.gender"));
        assertEquals(8877445596l,jsonPath.getLong("data.phone"));

        // What if I want to get id
        int id = jsonPath.getInt("data.id");
        System.out.println("id = " + id);

        // if you wanna do assertion with GET Request we can use it as Path param to specify same Spartans


        /*
        {
            "success": "A Spartan is Born!",
            "data": {
                 "id": 174,
                 "name": "John Doe",
                 "gender": "Male",
                 "phone": 8877445596l
            }
        }
         */
        //what happens if we run again? --> it will create new spartan with different ID



    }
}
