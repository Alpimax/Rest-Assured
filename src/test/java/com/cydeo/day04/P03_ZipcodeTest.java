package com.cydeo.day04;

public class P03_ZipcodeTest {

    /**
     * Create TestBase for zipcode
     * website ----> https://www.zippopotam.us/#
     *
     */

    /**
     Given accept type is json
     and country path param value is "us"
     and postal code path param value is 22102
     When I send get request to http://api.zippopotam.us/{country}/{postal-code}
     Then status code is 200
     Then "post code" is "22102"
     And  "country" is "United States"
     And "place name" is "Mc Lean"
     And  "state" is "Virginia"
     */

}

