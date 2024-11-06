package com.automation.steps;

import com.automation.utils.ConfigReader;
import io.restassured.RestAssured;
import org.junit.Before;

public class Hooks {

    @Before
    public void setUp(){
        ConfigReader.initConfig();
        RestAssured.baseURI = "";

    }

}
