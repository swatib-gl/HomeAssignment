package com.discovery.pageObjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.discovery.controller.BaseDriver;


public class HomePage extends BaseDriver {
	public WebDriver driver;
//	List<WebElement> tilestitle1=null;
//	List<WebElement> tilesduration1=null;
	
	@FindBy(xpath="//h2[contains(text(),'Popular Shows')]")
	By popularshowtile;	
	@FindBy(xpath="//*[@aria-hidden=\"false\"]//section//*[contains(text(),'Explore the Show')]")
	WebElement exploretheshowbtn;
	@FindBy(xpath="//*[contains(@class,'showMore')]//*")
	WebElement showmorebtn;
	@FindBy(xpath="//*[contains(@class,'middle')]//*[@class='episodeTitle']")
	List<WebElement> tilestitles;
	@FindBy(xpath="//*[contains(@class,'middle')]//*[@class='minutes']")
	List<WebElement> tilesduration;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}	
	public boolean popularshowsDisplayed() {
		try {
			((WebElement) popularshowtile).isDisplayed();
			log.info("popular shows displayed");
			return true;
		}catch(Exception e) {
			return false;
		}
	}	 
	public void scrollPage() throws InterruptedException {
		log.info("scrolling page down");
		JavascriptExecutor js = (JavascriptExecutor) driver;		
		js.executeScript("window.scrollBy(0,4000)");
	
	}		
	public void hasNextTileClick() throws AWTException {
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		 
			for (int i=0;i<15;i++) {
			Robot robot = new Robot();	
            robot.mouseMove(1310, 240);
            robot.delay(1000);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            log.info("Clicking "+i+" indexed popular shows tile");
            }        
		}		
		public void clickExploreButton() {
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			exploretheshowbtn.click();
			log.info("clicked explore the show button");
		}
		public void scrolltoShowMoreButtonandclick() {
			JavascriptExecutor js = (JavascriptExecutor) driver;		
			js.executeScript("window.scrollBy(0,1000)");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			showmorebtn.click();
			log.info("clicked on show more button");
				
		}
		public void getTileTitle( ) {
			System.out.println("count of titles under popular show tiles size :"+tilestitles.size());
			for(int j=0;j<tilestitles.size();j++) {
				String tiletit=tilestitles.get(j).getText();
				System.out.println("popular show tiles title:"+tiletit);
		}	      
			
		}
		public void getTileDuration() {
			System.out.println("count of titles duration under popular show tiles  :"+tilesduration.size());
			for(int j=0;j<tilesduration.size();j++) {
				String tilesiz=tilesduration.get(j).getText();				
				System.out.println("popular show tiles duration:"+tilesiz);
			}
			
		}		

}

