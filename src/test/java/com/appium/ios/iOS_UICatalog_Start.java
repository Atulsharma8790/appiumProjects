package com.appium.ios;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class iOS_UICatalog_Start {

	IOSDriver<IOSElement> driver;
	public static DesiredCapabilities cap;
	WebDriverWait wait;


	@BeforeTest
	public void startUp() throws MalformedURLException {
		cap = new DesiredCapabilities();
		cap.setCapability("platformName", "iOS");
		cap.setCapability("platformVersion", "10.3");
		cap.setCapability("deviceName", "iPhone 6");
		cap.setCapability("noReset","true");
		cap.setCapability(MobileCapabilityType.APP, "/Users/atulsharma/Desktop/UIKitCatalog.app");

		driver = new IOSDriver<IOSElement>(new URL("http://0.0.0.0:4723/wd/hub"),cap);
	}

	@Test
	public void TestCase_001() {

		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementsByAccessibilityId("Back").get(0))).click();
		System.out.println("First test");

	}

	//	@Test
	public void TestCase_DatePicker() throws InterruptedException {
		driver.findElement(By.name("Date Picker")).click();
		Thread.sleep(2000);

		List<IOSElement> wheels = (List<IOSElement>)driver.findElements(By.className("XCUIElementTypePickerWheel"));
		wheels.get(0).sendKeys("Aug 28");
		wheels.get(1).setValue("11");
		wheels.get(2).setValue("06");
		wheels.get(3).setValue("AM");

		System.out.println(wheels.get(0).getAttribute("value"));

		System.out.println(wheels.get(0).getAttribute("value"));
		System.out.println(wheels.get(1).getAttribute("value"));
		System.out.println(wheels.get(2).getAttribute("value"));
		System.out.println(wheels.get(3).getAttribute("value"));

		Thread.sleep(1000);

		System.out.println(driver.findElements(By.className("XCUIElementTypeStaticText")).get(1).getAttribute("value"));
		TestCase_001();
	}

	//	@Test
	public void TestCase_PickerView() throws InterruptedException {
		driver.findElement(By.name("Picker View")).click();

		Thread.sleep(1000);
		driver.findElementsByAccessibilityId("Red color component value").get(0).sendKeys("150");
		driver.findElementsByAccessibilityId("Green color component value").get(0).sendKeys("50");
		driver.findElementsByAccessibilityId("Blue color component value").get(0).sendKeys("250");
		Thread.sleep(1000);

		TestCase_001();


	}

	//@Test
	public void TestCase_Switches() throws InterruptedException {
		driver.findElement(By.name("Switches")).click();

		System.out.println(driver.findElements(By.className("XCUIElementTypeSwitch")).size());
		String value = (driver.findElements(By.className("XCUIElementTypeSwitch")).get(0).getAttribute("value"));

		if (value=="true") {
			driver.findElements(By.className("XCUIElementTypeSwitch")).get(0).click();
			System.out.println(driver.findElements(By.className("XCUIElementTypeSwitch")).get(0).getAttribute("value"));
			driver.findElements(By.className("XCUIElementTypeSwitch")).get(1).click();
			System.out.println(driver.findElements(By.className("XCUIElementTypeSwitch")).get(1).getAttribute("value"));

		}

		driver.findElements(By.className("XCUIElementTypeSwitch")).get(2).click();
		System.out.println(driver.findElements(By.className("XCUIElementTypeSwitch")).get(0).getAttribute("value"));
		driver.findElements(By.className("XCUIElementTypeSwitch")).get(3).click();
		System.out.println(driver.findElements(By.className("XCUIElementTypeSwitch")).get(1).getAttribute("value"));
		Thread.sleep(2000);
		TestCase_001();


	}

	//@Test
	public void TestCase_Sliders() {
		driver.findElement(By.name("Sliders")).click();

		List<IOSElement> sliders = (List<IOSElement>)driver.findElements(By.className("XCUIElementTypeSlider"));

		//SLIDER 1
		System.out.println(sliders.get(0).getAttribute("value"));
		String Value = sliders.get(0).getAttribute("value");
		Value = Value.substring(0,Value.length()-1);
		int curr_percentage = Integer.parseInt(Value);

		int width = sliders.get(0).getSize().getWidth();
		int curr_y = sliders.get(0).getLocation().getY();

		int curr_x = Math.round((width*curr_percentage)/100);
		System.out.println(curr_percentage + " "+curr_x+" "+width+" "+" "+(curr_x-50));
		driver.swipe(curr_x, curr_y, curr_x-50, curr_y, 1);
		System.out.println(sliders.get(0).getAttribute("value"));


		//SLIDER 2
		Value = sliders.get(1).getAttribute("value");
		Value = Value.substring(0,Value.length()-1);
		curr_percentage = Integer.parseInt(Value);

		width = sliders.get(1).getSize().getWidth();
		curr_y = sliders.get(1).getLocation().getY();

		curr_x = Math.round((width*curr_percentage)/100);
		System.out.println(curr_percentage + " "+curr_x+" "+width+" "+" "+(curr_x+150));
		driver.swipe(curr_x, curr_y, curr_x+150, curr_y, 1);
		System.out.println(sliders.get(1).getAttribute("value"));
		TestCase_001();
	}

	//@Test
	public void TestCase_Steppers() throws InterruptedException {
		driver.findElement(By.name("Steppers")).click();
		List<IOSElement> steppers_increment = (List<IOSElement>)driver.findElements(By.name("Increment"));
		List<IOSElement> steppers_decrement = (List<IOSElement>)driver.findElements(By.name("Decrement"));
		List<IOSElement> values = (List<IOSElement>)driver.findElements(By.className("XCUIElementTypeStaticText"));

		System.out.println("value of 1st is : "+values.get(0).getAttribute("value"));
		System.out.println("value of 2nd is : "+values.get(1).getAttribute("value"));
		System.out.println("value of 3rd is : "+values.get(2).getAttribute("value"));

		for(int i=0;i<5;i++)
		{
			steppers_increment.get(0).click();
			steppers_increment.get(1).click();
			steppers_increment.get(2).click();
		}
		System.out.println("value of 1st is : "+values.get(0).getAttribute("value"));
		System.out.println("value of 2nd is : "+values.get(1).getAttribute("value"));
		System.out.println("value of 3rd is : "+values.get(2).getAttribute("value"));

		for(int i=5;i>0;i--)
		{
			steppers_decrement.get(0).click();
			steppers_decrement.get(1).click();
			steppers_decrement.get(2).click();
		}
		System.out.println("value of 1st is : "+values.get(0).getAttribute("value"));
		System.out.println("value of 2nd is : "+values.get(1).getAttribute("value"));
		System.out.println("value of 3rd is : "+values.get(2).getAttribute("value"));

		Thread.sleep(2000);
		TestCase_001();
	}

	//@Test
	public void alert_controllers() throws InterruptedException {

		driver.findElement(By.name("Alert Controller")).click();
		driver.findElement(By.name("Simple")).click();
		Thread.sleep(1000);
		driver.findElementsByAccessibilityId("OK").get(0).click();

		driver.findElements(By.name("Okay / Cancel")).get(0).click();
		Thread.sleep(1000);
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());

		driver.findElementsByAccessibilityId("OK").get(0).click();


		Thread.sleep(1000);
		driver.findElement(By.name("Other")).click();
		Thread.sleep(1000);
		driver.switchTo().alert();
		System.out.println(alert.getText());
		driver.findElementsByAccessibilityId("Choice Two").get(0).click();

		Thread.sleep(1000);

		driver.findElement(By.name("Text Entry")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("XCUIElementTypeTextField")).sendKeys("Test Message Text");
		System.out.println(driver.findElement(By.className("XCUIElementTypeTextField")).getText());
		driver.findElementsByAccessibilityId("OK").get(0).click();

		Thread.sleep(1000);

		driver.findElements(By.name("Okay / Cancel")).get(1).click();
		Thread.sleep(1000);
		driver.findElementsByAccessibilityId("OK").get(0).click();
		Thread.sleep(2000);

		TestCase_001();
	}

	//@Test
	public void stackViews() throws InterruptedException {
		driver.findElement(By.name("Stack Views")).click();

		driver.findElements(By.name("stepper increment")).get(1).click();
		driver.findElements(By.name("stepper increment")).get(1).click();
		driver.findElements(By.name("stepper increment")).get(1).click();
		driver.findElements(By.name("stepper increment")).get(0).click();

		driver.findElements(By.name("stepper decrement")).get(1).click();
		driver.findElements(By.name("stepper decrement")).get(0).click();

		System.out.println(driver.findElement(By.name("Footer Label")).getText());

		Thread.sleep(2000);
		TestCase_001();
	}

	@Test
	public void Text_Fields() throws InterruptedException {
		
		driver.findElement(By.name("Text Fields")).click();
		driver.findElements(By.className("XCUIElementTypeTextField")).get(0).sendKeys("Text Message 1 in Text Box 1");
		driver.findElements(By.className("XCUIElementTypeTextField")).get(1).sendKeys("Text Message 2 in Text Box 2");

		driver.findElement(By.className("XCUIElementTypeSecureTextField")).sendKeys("Password");
		System.out.println(driver.findElement(By.className("XCUIElementTypeSecureTextField")).getText());
		Thread.sleep(2000);
		TestCase_001();
	}

	//@Test
	public void webView() throws InterruptedException {

		driver.swipe(200, 600, 200, 250, 1);
		Thread.sleep(2000);
		driver.findElement(By.name("Web View")).click();
		Thread.sleep(2000);
		driver.findElement(By.className("XCUIElementTypeTextField")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("Clear text")))).click();
		driver.findElement(By.className("XCUIElementTypeTextField")).sendKeys("http://www.google.co.in");
		driver.getKeyboard().pressKey(Keys.ENTER);
		Thread.sleep(5000);
		TestCase_001();

	}

	@Test
	public void results_in_search() throws InterruptedException {

		driver.swipe(200, 600, 200, 250, 1);
		Thread.sleep(2000);
		driver.findElement(By.name("Search")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("Results in Searched Controller")).click();
		driver.findElements(By.name("Search")).get(1).click();
		driver.findElements(By.name("Search")).get(1).sendKeys("P");
		
		List<IOSElement> search_results = (List<IOSElement>)driver.findElements(By.className("XCUIElementTypeStaticText"));

		for (IOSElement list : search_results) {
			System.out.println(list.getText());
		}
	}



}
