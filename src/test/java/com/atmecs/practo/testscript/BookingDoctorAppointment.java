package com.atmecs.practo.testscript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.atmecs.practo.constant.Constant;
import com.atmecs.practo.testbase.Base;
import com.atmecs.practo.utils.ReadExcel;

public class BookingDoctorAppointment extends Base {

	@DataProvider
	public Object[][] bookingDetails() throws Exception {
		Object data[][] = ReadExcel.getExcel(Constant.inputFile_path, "Sheet1");
		return data;
	}

	@Test(dataProvider = "bookingDetails")
	public void findAndBooking(String city, String specialist) throws IOException, InterruptedException {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Properties property1 = new Properties();
		FileInputStream stream1;
		stream1 = new FileInputStream(Constant.identifier_path);

		property1.load(stream1);

		driver.findElement(By.xpath(property1.getProperty("loc_doctor_xpath_slt"))).click();
		driver.findElement(By.xpath(property1.getProperty("loc_city_xpath_txt"))).clear();

		driver.findElement(By.xpath(property1.getProperty("loc_city_xpath_txt"))).sendKeys(city);
		driver.findElement(By.xpath(property1.getProperty("loc_chennai_xpath_slt"))).click();

		driver.findElement(By.xpath(property1.getProperty("loc_speciality_xpath_txt"))).sendKeys(specialist);
		WebDriverWait wait = new WebDriverWait(driver, 30);

		WebElement find = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(property1.getProperty("loc_dentist_xpath_slt"))));
		find.click();
		
		/*
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("window.scrollBy(0,1000)");//Scroll vertically down by 1000
		 * pixels
		 */ WebDriverWait wait1 = new WebDriverWait(driver, 30);
		WebElement find1 = wait1.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(property1.getProperty("loc_book_xpath_btn"))));
		find1.click();
		
		String text4 = driver.findElement(By.xpath(property1.getProperty("loc_feedetails_xpath_txt"))).getText();
		System.out.println("fees:"+ text4);
		
		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		WebElement find2 = wait2.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(property1.getProperty("loc_date_xpath_slt"))));
		find2.click();
		
		
		String text = driver.findElement(By.xpath(property1.getProperty("loc_tslot_xpath_txt"))).getText();
		System.out.println("no of time slot:"+text);
		driver.findElement(By.xpath(property1.getProperty("loc_time_xpath_btn"))).click();

		String text1 = driver.findElement(By.xpath(property1.getProperty("loc_docnamedata_xpath_txt"))).getText();

		System.out.println("doctor name:"+text1);
		String text2 = driver.findElement(By.xpath(property1.getProperty("loc_spldata_xpath_txt"))).getText();
		System.out.println("special in:"+text2);
		
		String text5 = driver.findElement(By.xpath(property1.getProperty("loc_appdate_xpath_txt"))).getText();
		System.out.println("appointment date:"+text5);
		
		String text3 = driver.findElement(By.xpath(property1.getProperty("loc_apptime_xpath_txt"))).getText();

		System.out.println("appointment time:"+text3);
		
		
		String text6 = driver.findElement(By.xpath(property1.getProperty("loc_clinicname_xpath_txt"))).getText();
		System.out.println("clinic name:"+text6);
		
		String text7 = driver.findElement(By.xpath(property1.getProperty("loc_address_xpath_txt"))).getText();
		System.out.println("address :"+text7);
		Thread.sleep(4000);
		
		//changing date and time
		driver.findElement(By.xpath(property1.getProperty("loc_changedateandtime_xpath_link"))).click();
		
		
		WebDriverWait wait3 = new WebDriverWait(driver, 30);
		WebElement find3 = wait3.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(property1.getProperty("loc_redate_xpath_slt"))));
		find3.click();
		find3.click();
		find3.click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath(property1.getProperty("loc_retime_xpath_slt"))).click();
		Thread.sleep(2000);
		String text8 = driver.findElement(By.xpath(property1.getProperty("loc_redatedata_xpath_txt"))).getText();
		System.out.println("reappointment date"+text8);
		Thread.sleep(2000);
		
		String text9 = driver.findElement(By.xpath(property1.getProperty("loc_retimedata_xpath_txt"))).getText();
		System.out.println("reappointment time:"+text9);
		
		String text10 = driver.findElement(By.xpath(property1.getProperty("loc_redocname_xpath_txt"))).getText();
		System.out.println("after changing date and time doctor name :"+text10);
		
		
		
			Assert.assertEquals(text10,text1);
			System.out.println("true");
		
		try {
			Assert.assertEquals(text5,text8);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("fail");
		}
		try {
			Assert.assertEquals(text3,text9);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("fail");
		}
	}

}
