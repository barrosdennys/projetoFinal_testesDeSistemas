@test
Feature: Video search

  Scenario: Search for a video that will not return any result
    Given I open the youtube main page
    And I login with email "testdesistemas.dipr@gmail.com" and password "r3m3mb3r"
    When I search for a video called "ççççççéééééé"
    Then I should see the No results found messages

  @videos
  Scenario: Like a video and check the Liked Videos list
    Given I open the youtube main page
    And I login with email "testdesistemas.dipr@gmail.com" and password "r3m3mb3r"
    And I search for a video called "Rebecca Black - Friday"
    And I click on the video called "Rebecca Black - Friday"
    When I like the video
    And I select the option "Liked videos" in the lateral menu
    Then I should see the video "Rebecca Black - Friday" in the Liked videos list

  Scenario: Add video to be watched in the miniplayer
    Given I open the youtube main page
    And I search for a video called "Flying Colors - Geronimo (Third Degree)"
    When I select "Add to queue" menu option from the video "Flying Colors - Geronimo (Third Degree)"
    Then I should see the video title "Flying Colors - Geronimo (Third Degree)" in the miniplayer


