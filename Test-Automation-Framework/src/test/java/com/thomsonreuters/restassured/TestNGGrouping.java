package com.thomsonreuters.restassured;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;

public class TestNGGrouping {

    @BeforeTest
    public void setupTest() {
        //Print console that code is in @BeforeMethod!
        System.out.println("@BeforeTest has started.");
    }

    //-----------------------------------Tests-----------------------------------
    @Test(groups = {"Regression", "Smoke"})
    public void firstTest() {
        System.out.println("1st Test is Started.");
        assertEquals(1,1);
    }

    @Test(groups = {"Regression", "Smoke"})
    public void secondTest() {
        System.out.println("2nd Test is Started.");
    }

    @Test(groups = {"Regression"})
    public void thirdTest() {
        System.out.println("3rd Test is Started.");
    }

    @Test(groups = {"Medium"})
    public void fourthTest() {
        System.out.println("4th Test is Started.");
    }

    @Test(groups = {"Regression"})
    public void fifthTest() {

        System.out.println("5th Test is Started.");
    }

    @Test(groups = {"Medium"})
    public void sixthTest() {
        System.out.println("6th Test is Started.");
    }

    //-----------------------------------Test TearDown-----------------------------------
    @AfterTest
    public void teardownTest() {
        System.out.println("@AfterTest has started.");
    }
}
