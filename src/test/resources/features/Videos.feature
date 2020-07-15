@test
Feature: Video search


  Scenario: Search for a video that will not return any result
    Given I open the youtube main page
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

  @videos
  Scenario: Add video to Watch Later and check the Watch Later list
    Given I open the youtube main page
    And I login with email "testdesistemas.dipr@gmail.com" and password "r3m3mb3r"
    And I search for a video called "Rebecca Black - Friday"
    And I click on the video called "Rebecca Black - Friday"
    When I add video to playlist "Watch later"
    And I select the option "Watch later" in the lateral menu
    Then I should see the video "Rebecca Black - Friday" in the Watch later videos list

  @videos
  Scenario: Watch a video and check the History list
    Given I open the youtube main page
    And I login with email "testdesistemas.dipr@gmail.com" and password "r3m3mb3r"
    And I search for a video called "Rebecca Black - Friday"
    And I click on the video called "Rebecca Black - Friday"
    And I select the option "History" in the lateral menu
    Then I should see the video "Rebecca Black - Friday" in the History list
  @playlist
  Scenario: Add video to a new playlist and the new playlist
    Given I open the youtube main page
    And I login with email "testdesistemas.dipr@gmail.com" and password "r3m3mb3r"
    And I search for a video called "Rebecca Black - Friday"
    And I click on the video called "Rebecca Black - Friday"
    And I save the video in a new playlist named "playlist - test"
    When I select the option "playlist - test" in the lateral menu
    Then I should see the video "Rebecca Black - Friday" in the playlist


