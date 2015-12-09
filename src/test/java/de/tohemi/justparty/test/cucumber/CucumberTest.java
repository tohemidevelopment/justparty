package de.tohemi.justparty.test.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty", glue = "de.tohemi.justparty.test.cucumber.stepdefs", features = "classpath:features")
public class CucumberTest {
}