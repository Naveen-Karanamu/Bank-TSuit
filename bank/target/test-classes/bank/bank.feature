@balance_check
Feature: Bank Check Balance Special

  @deposit @example
  Scenario Outline: Checking Account Balance after Deposit
    Given my checking account has a balance of $<balance>
    And I have recently made a deposit of $<deposit>
    When I check my account balance
    Then I should see $<expectedBalance> as the balance
    But there is an overdraft fee of $<fee>
    And the available balance should be $<expectedAvailableBalance>
    @ex1
    Examples:
      | balance | deposit | expectedBalance | fee | expectedAvailableBalance |
      | 500     | 100     | 600             | 50  | 550                      |
      | 5000    | 1000    | 6000            | 500 | 5500                     |
      | 500     | 100     | 600             | 50  | 550                      |
      | 500     | 100     | 600             | 50  | 550                      |

    @ex2
    Examples:
      | balance | deposit | expectedBalance | fee | expectedAvailableBalance |
      | 500     | 100     | 600             | 50  | 550                      |
      | 5000    | 1000    | 6000            | 500 | 5500                     |
      | 500     | 100     | 600             | 50  | 550                      |
      | 500     | 100     | 600             | 50  | 550                      |

    @csv
    Scenario: Checking Account Balance after Deposit CSV
      Given I have the following account details from CSV file "C:\Users\617404611\OneDrive - BT Plc\Desktop\My Learnings\Gherkin\bank\testData.csv"

  @withdrawal
  Scenario: Checking Account Balance after Withdrawal
    Given my checking account has a balance of $500
    And I have recently made a withdrawal of $100
    When I check my account balance
    Then I should see $400 as the balance
    But there is a credit interest of $50
    And the available balance should be $450

  Scenario: Checking Account Balance after Withdrawal In-Sufficient Balance
    Given my checking account has a balance of $500
    When I request to withdraw $700
    Then I should see an Error
    And the available balance should be $500