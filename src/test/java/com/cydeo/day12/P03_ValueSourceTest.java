package com.cydeo.day12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class P03_ValueSourceTest {

    @ParameterizedTest
    @ValueSource(ints = {10,20,30,40,50} )
    public void test1(int number) {

        System.out.println(number);
        Assertions.assertTrue(number<40);

    }


    @ParameterizedTest(name = "{index} name is {0}")
    @ValueSource(strings = {"Mike","Rose","Caberly","Kimberly","TJ","King"})
    public void test2(String name) {

        System.out.println("name = " + name);
        Assertions.assertTrue(name.length()>4);

    // {index} --> it wiill print out in console as test name with their index
    //  {0}    --> will get data from provided set of data.O point first set of data

    }

    // SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
    // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
    // check status code 200







}
