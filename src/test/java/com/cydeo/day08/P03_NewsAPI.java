package com.cydeo.day08;

import com.cydeo.utilities.NewsAPITestBase;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class P03_NewsAPI extends NewsAPITestBase {

    /**    QUERY PARAM
     * - Given query param is q=“bitcoin”
     * - And query param is apiKey=“yourKey”
     * - When user sent request / everything endpoint - Then status code should be 200
     * - And each articles contains “bitcoin”
     */

    @Test
    public void test1() {

     //d76cdd6edd704269a89f9742c91c75c4

        given().log().uri().queryParam("q","bitcoin")
                .queryParam("apiKey","d76cdd6edd704269a89f9742c91c75c4").
        when().get("/everything").prettyPeek().then().statusCode(200);


    }
}
