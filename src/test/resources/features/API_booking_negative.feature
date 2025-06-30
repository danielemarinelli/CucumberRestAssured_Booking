
@bookingFeature
Feature: Test the booking functionality
  Background:
    Given I have a valid token
@get1
  Scenario: Send a request with invalid token
    Given I have an invalid token
    When I send a request to view the bookings with an invalid token
    Then I should receive an error unauthorized with code 401

@get2
  Scenario: Send a request without roomID
    When I have an valid token
    Then I send a request to view the bookings without the roomid
    Then I should receive an error badrequest with code 400



