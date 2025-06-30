
@bookingFeature
Feature: Test the booking functionality
  Background:
    Given I have a valid token
@get
  Scenario: Successful list of a room
    When I want to read the bookings for a room
    Then I should receive all existing bookings
@create
  Scenario Outline: Create a booking
    When I make a reservation with <roomid>, <lastname>, <firstname>, <depositpaid>, <checkin>, <checkout>
    Then I receive the reservation and the process ends successfully
    Then The JSON schema is as expected and validated
    Examples:
      | roomid | lastname | firstname | depositpaid | checkin    | checkout   |  |
      | 1000   | Marino   | Daniele   | false       | 2025-07-02 | 2025-07-07 |  |
      | 1001   | Kelly    | Jim       | true        | 2025-08-07 | 2025-08-16 |  |

  @update
  Scenario: Update a booking
    Given I have the bookingid for the roomid <1000>
    When I make a request to update first name and Last name
    Then I should get the success status code

  @delete
  Scenario: Delete a booking
    Given I have the bookingid for the roomid <1000>
    When I make a request to delete it
    Then The reservation should be successfully deleted
