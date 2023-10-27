Feature: Printing environment variables

  @Test
  Scenario: Printing environment variables
    Given I print env variable "ONE"
    Given I print env variable "TWO"