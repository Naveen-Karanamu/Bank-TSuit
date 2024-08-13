package bank;

import io.cucumber.java.en.*;

import org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {

    private int accountNumber;
    private double balance;

    @Given("a user with account number {int}")
    public void a_user_with_account_number(Integer accountNumber){
        this.accountNumber=accountNumber;
    }

    @When("they check their balance")
    public void they_check_their_balance(){
        this.balance=retrieveBalance(accountNumber);
    }

    @Then("they should see a balance of ${double}")
    public void they_should_see_a_balance_of_$(Double expectedBalance){
        assertEquals(expectedBalance, balance);
    }

    private double retrieveBalance(int accountNumber){
        return 1000;
    }

}
