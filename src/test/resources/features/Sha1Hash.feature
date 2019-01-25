Feature: Calculate Sha1 hash

  Scenario: Calculate Sha1 hash
    When I call /sha1 with parameter = a
    Then status code for /sha1 should be 200
    And result for /sha1 should be 86f7e437faa5a7fce15d1ddcb9eaeaea377667b8
