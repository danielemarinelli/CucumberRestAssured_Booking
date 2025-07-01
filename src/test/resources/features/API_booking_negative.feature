
@bookingFeature
Feature: Test the booking functionality for negative scenarios
  Background:
    Given I have a valid token
@get1
  Scenario: Send a request with invalid token
    Given I have an invalid token
    When I send a request to view the bookings with an invalid token
    Then I should receive an error unauthorized

@get2
  Scenario: Send a request without roomID
    When I send a request to view the bookings without roomid
    Then I should receive an error badrequest with code 400

@get3
  Scenario: Send a request with non existing roomID

    When I send a request to view the bookings with invalid roomid
    Then I should receive an empty list with success request

