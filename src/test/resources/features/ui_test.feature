@UI
Feature: bla bla

  @Test
  Scenario Outline: bla bla <n>
    Given I open url "https://google.com"
    Then Validate title is "Google"
    Then Validate button "Google Search" displayed
    Examples: Example
      | n |
      | 1 |
      | 2 |
      | 3 |

  @Test
  Scenario: bla bla 4
    Given I open url "https://google.com"
    Then Validate title is "Google"
    Then Validate button "Google Search" displayed