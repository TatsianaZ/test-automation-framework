package com.thomsonreuters.restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimplePostTest {

   /* @Test(groups = {"Rest API POST"})
    public void testResponseBody() {
        RestAssured.baseURI = "http://restapi.demoqa.com/customer";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("FirstName", "1"); // Cast
        requestParams.put("LastName", "1");
        requestParams.put("UserName", "jfjgdggggggggggggggggggggggfffffffffff");
        requestParams.put("Password", "1");

        requestParams.put("Email", "1@gmail.com");
        request.body(requestParams.toString());
        Response response = request.post("/register");

        System.out.println("response: " + response.getBody().asString());

        int statusCode = response.getStatusCode();
        assertThat(statusCode).isEqualTo(200);
        String successCode = response.jsonPath().get("SuccessCode");

        assertThat(successCode).as("Correct Success code was returned").isEqualTo("OPERATION_SUCCESS");
    }*/


}
