package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        // Run the scenarios specified by tags
        tags = {"@test1"},

        // Define the resources.features files location
        features = {"src/test/resources/features"},

        // Define the steps files location
        glue = {"steps"},

        // Skip undefined steps from execution
        strict = true,

        // Readable format of the console output from Cucumber
        monochrome = true
)
public class CucumberRunner {

}







