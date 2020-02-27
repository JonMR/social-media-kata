Feature: Publishing
  Scenario: Alice publishes messages to her personal timeline.
    Given Alice has published "I love the weather today."
    When Alice views her timeline
    Then Alice sees: "I love the weather today. (5 seconds ago)"
