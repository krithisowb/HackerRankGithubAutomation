package com.mypackage.automation;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AppWithSQLChallenge {
	public static void main(String[] args) throws InterruptedException, AWTException, IOException {

		String root = "/Users/krithickasivarajan/Desktop/HackerRankJavaTrack/";

		System.setProperty("webdriver.chrome.driver",
				"/Users/krithickasivarajan/Desktop/HackerRankGithubAutomation/drivers/chromedriver");
		ChromeOptions o = new ChromeOptions();
		o.addArguments("disable-extensions");
		o.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(o);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		try {
			driver.get("https://www.hackerrank.com/domains/java/java-data-structure/2");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/div[1]/nav/div/div[2]/ul[2]/li[1]/button"))
					.click();

			// Login
			List<WebElement> wbs;
			loginFromHomepage(driver);
			// refresh page after directly logging in from Challenge page
			driver.navigate().refresh();
			
			wbs = driver.findElements(By.xpath("//*[@id=\"contest-challenges-problem\"]/div/header/div/div/h4/a"));
			System.out.println(wbs.size());
			for (WebElement link : wbs) {

				System.out.println(link.getText());
			}
			List<String> challengeNames = new ArrayList<String>();

			List<String> href_links = new ArrayList<String>();
			for (WebElement link : wbs) {
				href_links.add(link.getAttribute("href"));
				challengeNames.add(link.getText());

			}
			for (int y = 0; y < href_links.size(); y++) {
				
				driver.get(href_links.get(y));
				// submission tab
				driver.findElement(By.xpath("//*[@id=\"submissionsTab\"]/a")).click();

				// Accepted code view results
				List<WebElement> webele=driver.findElements(By.xpath(
						"//*[@id=\"content\"]/div/div[2]/div/div[4]/div/div[2]/section/div/div/div[1]/div/div/div[6]/p/a"));
				if(webele.size()!=0) {
					driver.findElement(By.xpath(
							"//*[@id=\"content\"]/div/div[2]/div/div[4]/div/div[2]/section/div/div/div[1]/div/div/div[6]/p/a")).click();
				String code = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/div[4]/div/div[2]/section/div[3]/div[2]/div[2]/div/div[6]/div[1]/div/div"))
						.getText();
				String challenge = challengeNames.get(y);
				File f = new File(root + challenge);
				if (!f.exists())
					f.mkdir();
				PrintWriter out = new PrintWriter(root + challenge + "/Solution.java");
				String s[] = code.split("\\d");
				for (String p : s)
					out.println(p);
				out.close();
				}
			}

			driver.close();

		} catch (Exception e) {
			e.printStackTrace();
			driver.close();
			//KillProcess.killProcess();
		}
	}

	/**
	 * @param driver
	 */
	private static void loginFromHomepage(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"legacy-login\"]/div[1]/div[2]")).click();
		List<WebElement> wbs = driver.findElements(By.xpath("//*[@id=\"login\"]"));
		wbs.get(1).clear();
		wbs.get(1).sendKeys("krithisowb@gmail.com");
		driver.findElement(By.xpath("//*[@id=\"legacy-login\"]/div/div[3]")).click();
		wbs = driver.findElements(By.xpath("//*[@id=\"password\"]"));
		wbs.get(0).clear();
		wbs.get(0).sendKeys("Iam@8056053914");
		driver.findElement(By.xpath("//*[@id=\"legacy-login\"]/div/button")).click();
	}
}
