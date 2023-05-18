package com.arabam.test_automation.tests;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.arabam.test_automation.pages.HomePage;

public class HomePageTest {
	private static WebDriver driver;
	private static HomePage homePage;

	@BeforeClass
    public static void setUp() {
    	// WebDriver'ı başlat
    	System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        driver = new ChromeDriver();
    	//driver = new SafariDriver();
        
        // arabam.com sayfasını aç
        driver.get("https://www.arabam.com");
        
        homePage = new HomePage(driver);
    }
	

    @AfterClass
    public static void tearDown() {
    	// WebDriver'ı kapat
    	driver.quit();
    }
	
    // Case 1, Sayfanın başlık kısmında arabam.com logosu, arama kutusu, arama butonu, “Giriş Yap / Üye Ol”
    // bağlantısı ve “Ücretsiz İlan Ver” tuşunun görünür olduğunu doğrulayın.
	@Test
	public void testHomePageSection() {
		// Sayfanın doğru şekilde yüklendiğini doğrula
		boolean isPageLoaded = homePage.verifyPageLoaded();
		Assert.assertTrue("HomePage yüklenemedi.", isPageLoaded);
	}
	// Case 2, Sayfanın orta kısmında “Vitrin” başlığının ve altında 18 tane resimli ilan bağlantısının görünür
	// olduğunu doğrulayın. (18 adet <div> tag olduğunu doğrulamakta yeterlidir)
	@Test
	public void testHomePageShowcaseSection() {
		// Vitrinde yeterince ilan olduğunu doğrula
		int elements = homePage.getShowCaseElements();
		Assert.assertTrue("Element sayısı 18'den büyük değil", elements > 18);
	}
	
	// Case 3, Sayfanın sol kısmındaki menüde “Son 24 Saat / 48 Saat”, “Acil İlanlar” ve “Fiyatı Düşenler”
	// başlıklarının görünür olduğunu doğrulayın.
	@Test
	public void testHomeCategorySection() {
		// Kategorinin doğru bir şekilde yüklendiğini doğrula
		boolean isCategoryLoaded = homePage.verifyCategoryElements();
		Assert.assertTrue("HomePage yüklenemedi.", isCategoryLoaded);
	}
}
