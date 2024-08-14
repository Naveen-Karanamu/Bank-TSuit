package bank;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.*;
import java.io.*;

import java.io.IOException;

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

    @Given("I have the following account details from CSV file {string}")
    public void iHaveTheFollowingAccountDetailsFromCSVFile(String csvFilePath) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csvFilePath));
            String line;
            boolean firstLineSkipped = false;

            while ((line = br.readLine()) != null) {
                if (!firstLineSkipped) {
                    firstLineSkipped = true;
                    continue; // Skip the first line (column headers)
                }

                String[] values = line.split(",");
                int balance = Integer.parseInt(values[0].trim());
                int deposit = Integer.parseInt(values[1].trim());
                int expectedBalance = Integer.parseInt(values[2].trim());
                int fee = Integer.parseInt(values[3].trim());
                int expectedAvailableBalance = Integer.parseInt(values[4].trim());

                myCheckingAccountHasABalanceOf$(balance);
                iHaveRecentlyMadeADepositOf$(deposit);
                iCheckMyAccountBalance();
                iShouldSee$AsTheBalance(expectedBalance);
                thereIsAnOverdraftFeeOf$(fee);
                theAvailableBalanceShouldBe$(expectedAvailableBalance);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    @And("i do nothing")
    public void iDoNothing() {
    }
/*
    @Before
    public void before(){
        System.out.println("*** Before Scenario");
    }

    @After
    public void after(){
        System.out.println("After Scenario ***");
    }

    @Before(order = 1)
    public void before11(){
        System.out.println("*** Before Scenario");
    }

    @After(order = 2)
    public void after22(){
        System.out.println("After Scenario ***");
    }

    @BeforeStep
    public void beforeStep(){
        System.out.println("*** Before Step Scenario");
    }

    @AfterStep
    public void afterStep(){
        System.out.println("*** Before Step Scenario");
    }
    */
}
