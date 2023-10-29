Feature: Printing environment variables

  @Test
  Scenario: Printing environment variables
    Given I print env variable "ONE"
    Given I print env variable "TWO"
    Given I print env variable "THREE"
    Given I print env variable "FOUR"
    Given I print env variable "FIVE"
    Given I print env variable "SIX"
    Given I print env variable "ENV"


  @Test
  Scenario: Get all env variables
    Given I print all env variables