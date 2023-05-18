package com.arabam.test_automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.arabam.test_automation.utils.Helper;

/**
 * Arabam.com üzerinde arama işlemlerini gerçekleştiren sayfa sınıfı.
 * Bu sınıf, arama çubuğuna metin girme, arama butonuna tıklama,
 * kategoriye göre arama yapma gibi işlemleri sağlar.
 * Ayrıca filtreleme işlemlerini de gerçekleştirebilir.
 * Yardımcı metotlar için {@link Helper} sınıfını kullanır.
 * 
 * Sınıfın yazarı: Selim OLMAZ
 * Email: selimolmaz3.1415@gmail.com
 */
public class SearchPage {
	private WebDriver driver;
	// Arama çubuğu
	private By searchBoxLocator = By.xpath("//input[@type='text']");
	// Arama butonu
	private By searchButtonLocator = By.cssSelector("button.search-button");
	// Kategori arama butonu
	private By categorySearchButtonLocator = By.className("btn-search");
	// Ana Sayfa butonu
	private By homePageButton = By.cssSelector("div.header__container a[href='/");
	// Kategori
	private By category = By.className("category-section");
	// Filtre Yıl buton
	private By filterYearButton = By.xpath("//span[text()='Yıl']/ancestor::button");
	// Filtre Yıl min girdi
	private By filterYearMin = By.cssSelector("div#yil input[placeholder='Min ']");
	// Filtre Yıl max girdi
	private By filterYearMax = By.cssSelector("div#yil input[placeholder='Max ']");
	private Helper helper;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		helper = new Helper(driver);
	}
	/**
	 * Sayfayı 250 piksel aşağı doğru kaydırır.
	 */
	public void scrollPage() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		// Sayfayı 250 piksel aşağı doğru scroll etmek için JavaScript kodu
		executor.executeScript("window.scrollBy(0, 250);");

	}
	
	/**
	 * Ana Sayfa butonuna tıklar.
	 *
	 * @return tıklama başarılı ise true, aksi halde false
	 */
	public boolean clickHomeButtun() {
		WebElement homeButton = driver.findElement(homePageButton);
		try {
			helper.clickElement(homeButton);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	/**
	 * Arama butonuna tıklar.
	 */
	public void clickSearchButton() {
		WebElement searchButton = driver.findElement(searchButtonLocator);
		try {
			helper.clickElement(searchButton);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Kategori arama butonuna tıklar.
	 */
	public void clickCategorySearchButton() {
		WebElement categorySearchButton = driver.findElement(categorySearchButtonLocator);
		try {
			helper.clickElement(categorySearchButton);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Belirtilen kategorinin üzerine tıklar.
	 *
	 * @param tag                etiket
	 * @param className          sınıf adı
	 * @param href               href değeri
	 * @param isHyrefHasPrefix   href değeri öneki (/ikinci-el/{hyref} gibi.) var mı?
	 * @return tıklama başarılı ise true, aksi halde false
	 */
	public boolean clickCategory(String tag, String className, String href, boolean isHyrefHasPrefix) {
		WebElement element = helper.getElementInCategory(tag, className, href, isHyrefHasPrefix);
		try {
			helper.clickElement(element);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	/**
	 * Belirli bir metin için arama yapar.
	 *
	 * @param textForSearch aranacak metin
	 * @return arama başarılı ise true, aksi halde false
	 */
	public boolean search(String textForSearch) {
		try {
			setSearchQuery(textForSearch);
			clickSearchButton();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}
	
	/**
	 * Belirli bir metin için arama yapar.
	 *
	 * @param textForSearch aranacak metin
	 * @return arama başarılı ise true, aksi halde false
	 */
	public void setSearchQuery(String textForSearch) {
		WebElement searchBox = driver.findElement(searchBoxLocator);
		helper.sendKeysToElement(searchBox, textForSearch);
	}
	
	/**
	 * Bir filtreye belirli bir değeri atar.
	 *
	 * @param query   filtre değeri
	 * @param element değiştirilecek element
	 */
	public void setFilterQuery(int query, WebElement element) {
		helper.sendKeysToElement(element, String.valueOf(query));
	}
	
	/**
	 * Yıl aralığını belirler.
	 *
	 * @param min en düşük yıl değeri
	 * @param max en yüksek yıl değeri
	 * @return ayarlama başarılı ise true, aksi halde false
	 */
	public boolean setYearsRange(int min, int max) {
		WebElement yearsFilterbutton = driver.findElement(filterYearButton);
		helper.clickElement(yearsFilterbutton);
		
		WebElement minInputElement = driver.findElement(filterYearMin);
		WebElement maxInputElement = driver.findElement(filterYearMax);
		
		try {
			setFilterQuery(min, minInputElement);
			setFilterQuery(max, maxInputElement);
			return true;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}

}
