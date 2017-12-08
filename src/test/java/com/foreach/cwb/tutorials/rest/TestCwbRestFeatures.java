package com.foreach.cwb.tutorials.rest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * This class serves as the JUnit runner for the Cucumber/CWB features but is also responsible for bootstrapping
 * the embedded tomcat for the integration tests.  The application is configured to us a dynamic port, this
 * class ensures the port is detected and the base url for all rest calls is configured with the right port.
 *
 * @author Arne Vandamme
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		features = { "classpath:features/" },
		glue = "com.foreach.cuke",
		format = {
				"json:target/cucumber/cucumber-report.json",
				"html:target/cucumber/plain-html-reports",
				"com.foreach.cuke.core.formatter.ConsoleReporter"
		}
)
public class TestCwbRestFeatures
{
	@Autowired
	@Qualifier("dataProperties")
	private Properties dataProperties;

	@PostConstruct
	public void bootApplication() {
		EmbeddedWebApplicationContext webApplicationContext
				= (EmbeddedWebApplicationContext) SpringApplication.run( CwbRestTutorialApplication.class );

		// retrieve the actual webserver port
		int webServerPort = webApplicationContext.getEmbeddedServletContainer().getPort();

		// modify the rest.base.url, replace the default port by the actual
		String restBaseUrl = dataProperties.get( "rest.base.url" ).toString();
		dataProperties.put( "rest.base.url", StringUtils.replace( restBaseUrl, "8080", "" + webServerPort ) );
	}
}
