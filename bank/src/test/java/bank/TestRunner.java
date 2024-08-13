package bank;

import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
//        tags = "@csv",
        features = {"src/test/resources/bank/bank.feature"}
)
public class TestRunner {

}
