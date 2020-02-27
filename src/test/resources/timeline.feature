Feature: Timeline
  Scenario: Alice views Bob's timeline.
    Given Bob has published "Darn! We lost!"
    And Bob has published "Good game though."
    When Alice views Bob's timeline
    Then Alice sees:
      Good game though. (1 minute ago)
      Darn! We lost! (2 minute ago)
