package com.Edureka.Flipkart.Mi;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


import static org.testng.Assert.assertTrue;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class FlipkartTest {
	static WebDriver driver;
  
   @BeforeClass
  public void beforeMethod() {
	  //setup the driver
	  WebDriverManager.firefoxdriver().setup();
	  driver=new FirefoxDriver();
	  //Maximize the window
	  driver.manage().window().maximize();
	  driver.manage().deleteAllCookies();
	  //Launch the Browser
	  driver.get("https://www.flipkart.com");
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	  String expectedUrl="https://www.flipkart.com/";
	  String actualUrl=driver.getCurrentUrl();
	  System.out.println(actualUrl);
	  Assert.assertEquals(actualUrl,expectedUrl);
	  
  }
  @Test
  public void login() {
	  //close the login
	driver.findElement(By.xpath("//a[@title='Login']")).click();
  }
  @Test(dependsOnMethods="login")
  public void menu() {
	  WebElement el=driver.findElement(By.xpath("//span[text()='Electronics']"));
	  Actions actions=new Actions(driver);
		//Hovering on Electronic
		actions.click(el).perform();
		//locating MObiles
		WebElement mo=driver.findElement(By.linkText("Mobiles"));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
	    wait.until(ExpectedConditions.visibilityOf(mo));
	    System.out.println("Mobile element identified");
		//To mouse hover MI
		actions.moveToElement(mo);
		//locating MI
		WebElement mi=driver.findElement(By.linkText("Mi"));
		actions.click(mi).build().perform();
	 WebElement slider=driver.findElement(By.xpath("//div[@class='_31Kbhn WC_zGJ']"));
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	  assertTrue(slider.isDisplayed());
	 Actions action=new Actions(driver);
     action.moveToElement(slider).click().dragAndDropBy(slider, 0, -50).build().perform();
      Select se=new Select(driver.findElement(By.xpath("//select[@class='_2YxCDZ']")));
      WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(20));
	    wait1.until(ExpectedConditions.visibilityOf(slider));
	 se.selectByValue("20000");
      System.out.println("Selected value is 20000");
      driver.findElement(By.xpath("//div[text()='Popularity']")).click();
	  Boolean t=driver.findElement(By.xpath("//div[@class='_4rR01T']")).isDisplayed();
	 if(t) {
		 System.out.println("Phone is displayed");
	  }
	  	 
  }
  @Test(dependsOnMethods="menu")
  public void Search() {
	  WebElement search=driver.findElement(By.name("q"));
      search.sendKeys("redmi note 12 pro 5g");
      search.submit();
      WebElement mob=driver.findElement(By.xpath("//div[text()='REDMI Note 12 Pro 5G (Glacier Blue, 128 GB)']"));
      WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(20));
	    wait1.until(ExpectedConditions.visibilityOf(mob));
	    System.out.println("Phone Found");
	    mob.click();
	  
  }
 @Test(dependsOnMethods="Search")
 public void display() {
	  ArrayList<String> newtab=new ArrayList<String>(driver.getWindowHandles());
      driver.switchTo().window(newtab.get(1));
      System.out.println("Page Title of newtab "+driver.getTitle());
      String  pr=driver.findElement(By.xpath("//div[@class='_30jeq3 _16Jk6d']")).getText();
      String re=pr.replace("₹","");
      System.out.println(re);
      Assert.assertTrue(driver.findElement(By.xpath("//div[@class='_30jeq3 _16Jk6d']")).isDisplayed(),"Price is displayed as"+re);
       
 }
 @Test(dependsOnMethods="display")
 public void video() {
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	 driver.findElement(By.xpath("//div[@class='_1AuMiq P9aMAP']")).click();
	
 }
 
 @Test(dependsOnMethods="video")
 public void pincode() {
	 driver.findElement(By.id("pincodeInputId"));
	 Actions actions=new Actions(driver);
	 actions.sendKeys("600011").perform();
	 System.out.println("Entered the pincode");
	 driver.findElement(By.xpath("//span[@class='YxlyDn']")).click();
	 System.out.println("clicked on viewdetails");
	 driver.findElement(By.xpath("//button[@class='_2KpZ6l _1KAjNd']")).click();
	 System.out.println("click on button icon");
	 
 }
 @Test(dependsOnMethods="pincode")
 public void Add() {
	 driver.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']")).click();
	 System.out.println("click on Add Cart");
 }
  @AfterClass
  public void afterMethod() {
	  
  }

}
