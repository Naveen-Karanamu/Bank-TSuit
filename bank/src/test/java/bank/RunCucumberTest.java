//This file can't read the sub cofig files while the TestRunner file can read it automatically due to version differences
//Hence we declare them specifically here

package bank;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("bank/bank.feature")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, " +
        "html:target/cucumber-report.html, "
        + "json:target/cucumber-report.json, " +
        "junit:target/cucumber-reports.xml, " +
        "timeline:target/cucumber-reports, " +
        "usage:target/usage.json, " +
        "rerun:target/rerun.txt")
@ConfigurationParameter(key =  "cucumber.publish.enabled", value = "true")
public class RunCucumberTest {
}
