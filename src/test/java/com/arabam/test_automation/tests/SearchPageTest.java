package com.arabam.test_automation.tests;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.arabam.test_automation.pages.SearchPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SearchPageTest {
	private static WebDriver driver;
	private static SearchPage searchPage;

	@BeforeClass
	public static void setUp() {
		// WebDriver'ı başlat
		// Bu bölüm test yapacak kişinin yerelinde olan driver'ı göstermeli
		System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
		driver = new ChromeDriver();

		// arabam.com sayfasını aç
		driver.get("https://www.arabam.com");

		// Yeni Arama Sayfası
		searchPage = new SearchPage(driver);
	}

	@AfterClass
	public static void tearDown() {
		// WebDriver'ı kapat
		driver.quit();
	}

	// Case 1, Sol taraftan Motosiklet linki tıklayın.
	@Test
	public void a_clickMotocycleSection() {
		boolean isClicked = searchPage.clickCategory("ul", "category-section", "motosiklet", true);
		Assert.assertTrue("Motosiklet bölümü yüklenemedi.", isClicked);
	}

	// Case 2, Açılan ekranda arama ekranına “Suzuki” yazılır ve arama sonucu çıkan
	// “Suzuki (adet)” linkine tıklayın.
	@Test
	public void b_clickMotocycleSectionSearch() {
		// Önceki testte yapılan tıklamayı kullanmak için searchPage nesnesini yeniden
		// oluşturuyoruz.
		// searchPage = new SearchPage(driver);

		boolean isSerached = searchPage.search("BMW");
		Assert.assertTrue("Arama bölümüne tıklanamadı.", isSerached);

		boolean isClicked = searchPage.clickCategory("ul", "inner-list", "motosiklet", false);
		Assert.assertTrue("Motosiklet bölümü yüklenemedi.", isClicked);

		// Bu case'i burada sonlandırıyoruz çünkü ana arama bölümünden Suzuki ya da
		// başka bir marka aradığımızda kategori
		// kısmından sadece motorsikleti ve markayı seçebiliyoruz. Modeli de seçmek için
		// case 3 ile katalogdan devam edeceğiz.
		// Ana Sayfa > Motosiklet > Suzuki > Burgman AN 650 modeli > 2000 - 2001
		// şeklinde ilerleyeceğiz.
	}

	// Case 3, Ana Sayfa > Motosiklet > Suzuki
	@Test
	public void c_selectMotocycleBrandSection() {

		// Ana Sayfaya Git
		boolean isClickkedHomeButton = searchPage.clickHomeButtun();
		Assert.assertTrue("Ana sayfaya gidilemedi.", isClickkedHomeButton);

		// Kategoriden Motosikleti Seç
		boolean isCategoryClicked = searchPage.clickCategory("ul", "category-section", "motosiklet", true);
		Assert.assertTrue("Motosiklet bölümü yüklenemedi.", isCategoryClicked);

		// Kategoriden Motosikleti Seç
		// Burada Reklam yüzünden Ara butonu ve seçtiğimiz marka üst üste geliyor o
		// yüzden aşağıya 250px scrolluyoruz
		searchPage.scrollPage();
		boolean isCarBrandClicked = searchPage.clickCategory("ul", "inner-list", "motosiklet/suzuki", false);
		Assert.assertTrue("Motosiklet marka bölümü yüklenemedi.", isCarBrandClicked);

	}

	// Case 4, Burgman AN 650 modeli seçin/tıklayın.
	@Test
	public void d_selectMotocycleModelSection() {

		// Burada Reklam yüzünden Ara butonu ve seçtiğimiz marka üst üste geliyor o
		// yüzden aşağıya 250px scrolluyoruz
		searchPage.scrollPage();
		// Model Seç
		boolean isCarBrandClicked = searchPage.clickCategory("ul", "inner-list", "motosiklet/suzuki-burgman-uh-200",
				false);
		Assert.assertTrue("Motosiklet Model bölümü yüklenemedi.", isCarBrandClicked);
	}

	// Case 5, Yıl olarak 2000 - 2001 aralığı girilir ve hemen yandaki “Ara”
	// butonuna tıklayın.
	@Test
	public void e_selectMotocycleModelsYearsRangeSection() {
		// Seçilen modelde belirlenen yıl filtresine ulaşmak için iki kere scrolla
		searchPage.scrollPage();
		searchPage.scrollPage();
		boolean isCarYearsRangeSet = searchPage.setYearsRange(2000, 2001);
		Assert.assertTrue("Motosiklet yıl aralığı ayarlanamadı.", isCarYearsRangeSet);
		searchPage.clickCategorySearchButton();
	}
}
