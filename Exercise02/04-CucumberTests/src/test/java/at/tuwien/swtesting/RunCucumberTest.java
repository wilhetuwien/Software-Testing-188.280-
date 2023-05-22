/*
 * William Hedlund
 * 12233006
 * exercise 2 assignment 4
 */
package at.tuwien.swtesting;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber-reports/report.html"})
public class RunCucumberTest {

}


