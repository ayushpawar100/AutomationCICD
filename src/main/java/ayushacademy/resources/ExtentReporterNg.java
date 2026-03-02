package ayushacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNg {

	public static ExtentReports getReportObject () {
			String path = System.getProperty("user.dir")+"\\reports\\index.html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(path) ;
			reporter.config ().setReportName("Test Result"); 
			reporter.config().setDocumentTitle("Web Automation Results");
			
			ExtentReports extent = new ExtentReports() ;
			extent.attachReporter(reporter) ;
			extent.setSystemInfo("Tester", "ayush pawar");
			return extent;
			}

}
