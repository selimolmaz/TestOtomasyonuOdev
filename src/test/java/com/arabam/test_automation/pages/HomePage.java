package com.arabam.test_automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
/**
 * Arabam.com ana sayfasını temsil eden sayfa sınıfı.
 * Bu sınıf, ana sayfadaki öğelerin bulunması ve işlenmesi için kullanılır.
 * Ana sayfa üzerinde logo, arama kutusu, arama butonu, giriş bağlantısı,
 * ücretsiz ilan verme butonu gibi öğeleri bulma ve erişme işlemlerini sağlar.
 * Ayrıca vitrin öğelerinin doğru şekilde yüklendiğini, kategorilerin doğru şekilde
 * yüklendiğini doğrulama gibi işlemleri gerçekleştirebilir.
 * 
 * Sınıfın yazarı: Selim OLMAZ
 * Email: selimolmaz3.1415@gmail.com
 */
public class HomePage {
	private WebDriver driver;
	
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	/**
     * Logo öğesini bulur ve döndürür.
     * Asıl eleman "a.header__logo img" CSS seçici ile bulunur.
     * 
     * @return Logo öğesi
     */
	public WebElement getLogoElement() {
		return driver.findElement(By.cssSelector("a.header__logo img"));
	}

	/**
     * Arama kutusu öğesini bulur ve döndürür.
     * Asıl eleman "div.search-input__container" CSS seçici ile bulunur.
     * 
     * @return Arama kutusu öğesi
     */
	public WebElement getSearchBoxElement() {
		return driver.findElement(By.cssSelector("div.search-input__container"));
	}

	/**
     * Arama butonu öğesini bulur ve döndürür.
     * Asıl eleman "button.search-button" CSS seçici ile bulunur.
     * 
     * @return Arama butonu öğesi
     */
	public WebElement getSearchButtonElement() {
		return driver.findElement(By.cssSelector("button.search-button"));
	}

	/**
     * "Giriş Yap / Üye Ol" bağlantısı öğesini bulur ve döndürür.
     * Asıl eleman "div.login-register-wrapper" CSS seçici ile bulunur.
     * 
     * @return "Giriş Yap / Üye Ol" bağlantısı öğesi
     */
	public WebElement getLoginLinkElement() {
		
		return driver.findElement(By.cssSelector("div.login-register-wrapper"));
	}

	/**
     * "Ücretsiz İlan Ver" butonu öğesini bulur ve döndürür.
     * Asıl eleman "button.btn-create-advert" CSS seçici ile bulunur.
     * 
     * @return "Ücretsiz İlan Ver" butonu öğesi
     */
	public WebElement getFreeAdButtonElement() {
		return driver.findElement(By.cssSelector("button.btn-create-advert"));
	}

	/**
	 * "Son 24 Saat / 48 Saat" kategori öğesini bulur ve kontrol eder.
	 * Asıl elemanları içeren liste elemanları isimleriyle "category-turbo-section" sınıfında 
	 * ayrı ayrı ele alınmıştır. İlgili her bir öğenin varlığını denetler.
	 * 
	 * @return "Son 24 Saat / 48 Saat" kategori öğesinin görünürlük durumu
	 */
	public boolean getCategory24_48Element() {
	    // "category-turbo-section" sınıfına sahip ana öğeyi bulur
	    WebElement turboSection = driver.findElement(By.className("category-turbo-section"));

	    // "cateogry-name" sınıfına sahip alt öğeleri bulur
	    List<WebElement> titles = turboSection.findElements(By.className("cateogry-name"));

	    boolean is24TitleVisible = false;
	    boolean is48TitleVisible = false;

	    // Alt öğelerin her birini döngüyle kontrol eder
	    for (WebElement title : titles) {
	        String titleText = title.getText();
	        // Eğer alt öğenin metni "Son 24 Saat" ise 24 saat başlığı görünür olarak işaretlenir
	        if (titleText.equals("Son 24 Saat")) {
	            is24TitleVisible = true;
	        }
	        // Eğer alt öğenin metni "48 Saat" ise 48 saat başlığı görünür olarak işaretlenir
	        else if (titleText.equals("48 Saat")) {
	            is48TitleVisible = true;
	        }
	        
	        // Hem 24 saat başlığı hem de 48 saat başlığı görünür olarak işaretlendiğinde döngüden çıkılır
	        if (is24TitleVisible && is48TitleVisible) {
	            break;
	        }
	    }

	    // Hem 24 saat başlığı hem de 48 saat başlığı görünür olarak işaretlenmişse true döndürülür, aksi halde false döndürülür
	    return is24TitleVisible && is48TitleVisible;
	}


	/**
	 * "Acil İlanlar" elementini bulur ve döndürür.
	 * Asıl eleman "category-turbo-section" sınıfı içindeki "a[href*='tag=Acil']" seçicisiyle bulunur.
	 *
	 * @return "Acil İlanlar" elementi
	 */
	public WebElement getCategoryImmediate() {
	    // "category-turbo-section" sınıfına sahip ana öğeyi bulur
	    WebElement turboSection = driver.findElement(By.className("category-turbo-section"));

	    // "a[href*='tag=Acil']" seçicisiyle "Acil İlanlar" linkini bulur
	    WebElement acilIlanlarLink = turboSection.findElement(By.cssSelector("a[href*='tag=Acil']"));

	    // "Acil İlanlar" elementini döndürür
	    return acilIlanlarLink;
	}

	/**
	 * "Fiyatı Düşen" elementini bulur ve döndürür.
	 * Asıl eleman "li a.category-link span.cateogry-name" seçicisiyle bulunur.
	 *
	 * @return "Fiyatı Düşen" elementi
	 */
	public WebElement getCategoryWeak() {
	    // "li a.category-link span.cateogry-name" seçicisiyle "Fiyatı Düşen" elementini bulur
	    return driver.findElement(By.cssSelector("li a.category-link span.cateogry-name"));
	}


	// Case 1
	/**
     * Sayfanın doğru şekilde yüklendiğini doğrular.
     * Ana sayfa öğelerinin doğru şekilde yüklendiğini kontrol eder.
     * 
     * @return Sayfanın doğru şekilde yüklendiği durumu
     */
	public boolean verifyPageLoaded() {
		return getLogoElement().isDisplayed() && getSearchBoxElement().isDisplayed()
				&& getSearchButtonElement().isDisplayed() && getLoginLinkElement().isDisplayed()
				&& getFreeAdButtonElement().isDisplayed();
	}

	// Case 2
	/**
     * Sayfanın vitrin kısmının doğru şekilde yüklendiğini doğrular.
     * Vitrin kısmındaki ilan öğelerinin doğru şekilde yüklendiğini kontrol eder.
     * 
     * @return Görünür ilan öğelerinin sayısı
     */
	public int getShowCaseElements() {
		WebElement showcaseElement = driver.findElement(By.cssSelector("div.showcase.row"));
		List<WebElement> adElements = showcaseElement.findElements(By.cssSelector("div.row > div"));

		int visiableElemtsNumber = 0;
		for (WebElement ilan : adElements) {
			if (ilan.isDisplayed()) {
				visiableElemtsNumber++;
			}
		}

		return visiableElemtsNumber;
	}

	// Case 3
	/**
     * Kategorilerin doğru şekilde yüklendiğini doğrular.
     * Sayfa üzerindeki kategori öğelerinin doğru şekilde yüklendiğini kontrol eder.
     * 
     * @return Kategorilerin doğru şekilde yüklendiği durumu
     */
	public boolean verifyCategoryElements() {
		return getCategory24_48Element() && getCategoryImmediate().isDisplayed() && getCategoryWeak().isDisplayed();
	}
}
