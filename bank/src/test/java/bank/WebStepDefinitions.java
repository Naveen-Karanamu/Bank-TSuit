package bank;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.OutputStream;
import java.io.PrintStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebStepDefinitions {

    @Before
    public void setup() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\617404611\\Downloads\\chromedriver-win64\\chromedriver.exe");

        // Silence log messages by redirecting System.err to a dummy OutputStream
        System.setErr(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                // Do nothing
            }
        }));
    }

    WebDriver driver;

    @Given("I open the login page")
    public void iOpenTheLoginPage() {
        driver = new ChromeDriver();
        driver.get("http://localhost/php_prog/");
    }

    @When("I enter the username as {string} and password as {string}")
    public void iEnterTheUsernameAsAndPasswordAs(String username, String password) throws InterruptedException {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        Thread.sleep(5000);
    }

    @When("I click the login button")
    public void iClickTheLoginButton() {
        driver.findElement(By.id("loginBtn")).click();
    }

    @Then("I should see the {string} message")
    public void iShouldSeeTheMessage(String successMsg) {
        String text = driver.findElement(By.tagName("h2")).getText();
        assert text.equals("Login successful");
    }

    @Then("I should see the Invalid username or password. message")
    public void seeInvalidLoginMessage(){
        String text = driver.findElement(By.tagName("h2")).getText();
        assert  text.equals("Invalid username or password");
    }

    @After()
    public  void tearDown() throws InterruptedException{
        Thread.sleep(5000);
        driver.close();
    }
}
