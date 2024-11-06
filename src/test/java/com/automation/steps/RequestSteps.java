package com.automation.steps;

import com.automation.pojo.CreateBookingRequestPojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.Constants;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.lang.reflect.Field;

public class RequestSteps {


    @Given("user wants to call {string} end point")
    public void user_wants_to_call_end_point(String endPoint) {
        RestAssuredUtils.clear();
        if (endPoint.contains("@id")) {
            endPoint = endPoint.replace("@id", ConfigReader.getProperty("booking.id"));
        }
        RestAssuredUtils.setEndPoint(endPoint);
    }

    @Given("set header {string} to {string}")
    public void set_header_to(String key, String value) {
        if (value.contains("@token")) {
            value = value.replace("@token", ConfigReader.getProperty("auth.token"));
        }
        RestAssuredUtils.setHeader(key, value);
    }

    @Given("set request body from file {string}")
    public void set_request_body_from_file(String filePath) throws Exception {
        RestAssuredUtils.setBody(filePath);
    }



    @When("user performs post call")
    public void userPerformsPostCall() {
        RestAssuredUtils.post();
    }

    @When("user performs delete call")
    public void userPerformsDeleteCall() {
        RestAssuredUtils.delete();
    }

    @When("user performs put call")
    public void userPerformsPutCall() {
        RestAssuredUtils.put();
    }

    @When("user performs get call")
    public void userPerformsGetCall() {
        RestAssuredUtils.get();
    }

    @And("set request body from file {string} with {string} value {string}")
    public void setRequestBodyFromFileWithValue(String filePath, String fieldName, String value) throws Exception {
        String content = RestAssuredUtils.getDataFromJsonFile(filePath);
        ObjectMapper objectMapper = new ObjectMapper();
        CreateBookingRequestPojo requestPojo = objectMapper.readValue(content, CreateBookingRequestPojo.class);
        Field field = CreateBookingRequestPojo.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        if (Constants.createBookingIntFields.contains(fieldName)) {
            field.set(requestPojo, Integer.valueOf(value));
        } else {
            field.set(requestPojo, value);
        }
        RestAssuredUtils.setBody(requestPojo);
        ConfigReader.setObject("request_pojo", requestPojo);

}
