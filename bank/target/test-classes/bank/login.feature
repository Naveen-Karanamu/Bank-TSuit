Feature: Login feature

  Scenario Outline: Login with different credentials
    Given I open the login page
    When I enter the username as "<username>" and password as "<password>"
    And I click the login button
    Then I should see the "<message>" message

    Examples:
      | username | password | message               |
      | admin    | password | Login successful.     |
      | admins   | password | Invalid username or password. |
      | anyone   | passwords | Invalid username or password. |
