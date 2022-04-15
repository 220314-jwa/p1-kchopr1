package com.revature.gluecode;



import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.revature.pages.PetAppHomePage;

import io.cucumber.java.BeforeAll;


public class LoginStep {
	
		public void setUp() {
			File file = new File("C:\Users\konar\Downloads\chromedriver_win32 (1).zip");
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			
			 WebDriver driver = new ChromeDriver();
			static 
			
		
}

	}
