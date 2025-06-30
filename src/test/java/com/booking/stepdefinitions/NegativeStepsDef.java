package com.booking.stepdefinitions;
import com.api.URL;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;


public class NegativeStepsDef {

    TestContext context;
    public NegativeStepsDef(TestContext context){
        this.context=context;
    }
    Response res;

    @Given("I have an invalid token")
    public void i_have_an_invalid_token() {
        String tokenValue = context.getToken()+"XYZABC";
        System.out.println("########### invalid token is::>> "+tokenValue);
        given()
                .queryParam("roomid",2)
                .cookie("token", tokenValue)
                .log().all();


    }
    @When("I send a request to view the bookings with an invalid token")
    public void i_send_a_request_to_view_the_bookings_with_an_invalid_token() {
        res=when()
                .get(URL.get_url_to_retrive_booking_room);
    }

    @Then("I should receive an error unauthorized")
    public void iShouldReceiveAnErrorUnauthorized() {
        res.then().statusCode(401)
                .body("error", equalTo("Authentication required"))
                .log().all();

        String errormsg = res.jsonPath().get("error").toString();
        Assert.assertEquals(errormsg,"Authentication required");
    }



    @When("I send a request to view the bookings without the roomid")
    public void i_send_a_request_to_view_the_bookings_without_the_roomid() {

    }
    @Then("I should receive an error badrequest with code {int}")
    public void i_should_receive_an_error_badrequest_with_code(Integer int1) {

    }



}
