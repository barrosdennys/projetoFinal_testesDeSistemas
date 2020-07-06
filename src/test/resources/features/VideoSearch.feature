@test
Feature: Video search

  Scenario: Search for a video that will not return any result
    Given I open the youtube main page
    And I login with email "testdesistemas.dipr@gmail.com" and password "r3m3mb3r"
    When I search for a video called "ççççççéééééé"
    Then I should see the No results found messages