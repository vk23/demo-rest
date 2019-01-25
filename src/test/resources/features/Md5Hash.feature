Feature: Calculate md5 hash

  Scenario: Calculate md5 hash
    Given test
    When I call /md5 with parameter = a
    Then status code for /md5 should be 200
    And result for /md5 should be 0cc175b9c0f1b6a831c399e269772661
