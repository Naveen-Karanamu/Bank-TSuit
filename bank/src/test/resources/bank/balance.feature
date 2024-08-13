#Feature: Bank Check Balance
#
#  Scenario: Check balance for a valid account
#    Given a user with account number 12345
#    When they check their balance
#    Then they should see a balance of $1000.00
#
#  Scenario: Checking Account Balance after Deposit
#    Given my checking account has a balance of $500
#    And I have recently made a deposit of $100
#    When I check my account balance
#    Then I should see $600 as the balance
#    But there is an overdraft fee of $50
#    And the available balance should be $550
#
#  Scenario: Checking Account Balance after Withdrawal
#    Given my checking account has a balance of $500
#    And I have recently made a withdrawal of $100
#    When I check my account balance
#    Then I should see $400 as the balance
#    But there is a credit interest of $50
#    And the available balance should be $450
#
#  Scenario: Checking Account Balance after Withdrawal In-Sufficient Balance
#    Given my checking account has a balance of $500
#    When I request to withdraw $700
#    Then I should see an Error
#    And the available balance should be $500
