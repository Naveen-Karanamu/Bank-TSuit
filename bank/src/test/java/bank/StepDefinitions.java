package bank;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {

    private int checkingAccountBalance;
    private int depositAmount;
    private int actualBalance;
    private int overDraftFee;
    private int availableBalance;
    private int withdrawalAmount;
    private int creditInterest;

    @Given("my checking account has a balance of ${int}")
    public void myCheckingAccountHasABalanceOf$(int balance) {
        checkingAccountBalance = balance;
    }

    @And("I have recently made a deposit of ${int}")
    public void iHaveRecentlyMadeADepositOf$(int deposit) {
        depositAmount = deposit;
    }

    @When("I check my account balance")
    public void iCheckMyAccountBalance() {
        actualBalance = checkingAccountBalance + depositAmount - withdrawalAmount;
    }

    @Then("I should see ${int} as the balance")
    public void iShouldSee$AsTheBalance(int expectedBalance) {
        assertEquals(expectedBalance, actualBalance);
    }

    @But("there is an overdraft fee of ${int}")
    public void thereIsAnOverdraftFeeOf$(int fee) {
        overDraftFee = fee;
    }

    @And("the available balance should be ${int}")
    public void theAvailableBalanceShouldBe$(int expectedAvailableBalance) {
        if (withdrawalAmount > 0
        && withdrawalAmount > checkingAccountBalance + depositAmount + creditInterest - overDraftFee){
            availableBalance = checkingAccountBalance + depositAmount - overDraftFee + creditInterest;
        }
        else {
        availableBalance = checkingAccountBalance + depositAmount - overDraftFee + creditInterest - withdrawalAmount;
        }
        assertEquals(expectedAvailableBalance, availableBalance);
    }

    @And("I have recently made a withdrawal of ${int}")
    public void iHaveRecentlyMadeAWithdrawalOf$(int withdrawal) {
        withdrawalAmount = withdrawal;
    }

    @But("there is a credit interest of ${int}")
    public void thereIsACreditInterestOf$(int interest) {
        creditInterest = interest;
    }

    @When("I request to withdraw ${int}")
    public void iRequestToWithdraw$(int withdrawal) {
        withdrawalAmount = withdrawal;
    }

    @Then("I should see an Error")
    public void iShouldSeeAnError() {
        String errorMessage = ">> Error: Withdrawal not allowed due to insufficient funds. <<";
        System.out.println(errorMessage);
    }

}
