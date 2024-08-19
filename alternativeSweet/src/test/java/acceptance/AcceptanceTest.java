package acceptance;

import org.junit.runner.RunWith;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(

        features = "Features/user/ExplorationAndPurchase.feature",
        plugin = {"html:target/cucumber-report/report.html"},
        monochrome = true,
        glue = "acceptance"
        
)

public class AcceptanceTest {
}
