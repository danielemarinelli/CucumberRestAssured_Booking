package com.booking.stepdefinitions;
import com.api.URL;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
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



    @When("I send a request to view the bookings without roomid")
    public void i_send_a_request_to_view_the_bookings_without_roomid() {
        String tokenValue = context.getToken();
        res= given()
                .cookie("token", tokenValue)
                .log().all()
                .get(URL.get_url_to_retrive_booking_room);
    }
    @Then("I should receive an error badrequest with code {int}")
    public void i_should_receive_an_error_badrequest_with_code(Integer int1) {
        res.then().statusCode(400)
                .body("error", equalTo("Room ID is required"))
                .log().all();

        String errormsg = res.jsonPath().get("error").toString();
        Assert.assertEquals(errormsg,"Room ID is required");
    }

    @When("I send a request to view the bookings with invalid roomid")
    public void i_send_a_request_to_view_the_bookings_with_invalid_roomid() {
        String tokenValue = context.getToken();
        res= given()
                .cookie("token", tokenValue)
                .queryParam("roomid",9999)
                .log().all()
                .get(URL.get_url_to_retrive_booking_room);
    }

    @Then("I should receive an empty list with success request")
    public void iShouldReceiveAnEmptyListWithSuccessRequest() {
        res.then().statusCode(200)
                .log().all();

        String bodySize = res.jsonPath().get("bookings.size()").toString();
        Assert.assertEquals("0",bodySize);
        System.out.println("Body response is EMPTY, as expected");
    }
}
