package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class FormulaAPITestBase {
    @BeforeAll
    public static void setUpAll() {
        RestAssured.baseURI = "http://ergast.com/api/f1/";
    }


}
