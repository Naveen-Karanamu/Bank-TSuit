Feature: Bank Check Balance

  Scenario: Check balance for a valid account
    Given a user with account number 12345
    When they check their balance
    Then they should see a balance of $1000.00
