package com.revature.gluecode;



import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.revature.pages.PetAppHomePage;

import io.cucumber.java.BeforeAll;


public class LoginStep {
	public class LoginStepImpl {
		static WebDriver driver;
		static PetAppHomePage petAppHome;
		
		@BeforeAll
		public static void setUp() {
			File file = new File("src/test/resources/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
}
