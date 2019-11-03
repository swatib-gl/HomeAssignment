package com.discovery.homePageTestCases;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.discovery.controller.BaseDriver;
import com.discovery.pageObjects.HomePage;
public class PopularShow extends BaseDriver{
	public HomePage homepage;
	
	@BeforeClass
	public void setUp() throws IOException {		
		init();		
		homepage=new HomePage(driver);		
	}
	
	@Test
	public void popularshow() throws InterruptedException, AWTException  {	
		    waitForPageLoad(driver);
			homepage.scrollPage();
			homepage.popularshowsDisplayed();
			homepage.hasNextTileClick();
			homepage.clickExploreButton();
			homepage.scrolltoShowMoreButtonandclick();
			homepage.getTileTitle();
			homepage.getTileDuration();
		}
	
	@AfterClass
	public void teardown() {
		closeBrowser();
	}
	
}
