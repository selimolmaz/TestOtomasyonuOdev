package com.arabam.test_automation.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

/**
 * Helper sınıfı, web elementleriyle etkileşim için ortak yardımcı metodları
 * sağlar.
 */
public class Helper {
	private WebDriver driver;

	public Helper(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Verilen web elementine tıklar. Eğer element görüntülenmiyorsa, elemente doğru
	 * kaydırır ve tıklar. Eğer element tıklanabilir değilse, başka bir tıklanabilir
	 * element bulur ve ona tıklar.
	 *
	 * @param element tıklanacak web elementi
	 */
	public void clickElement(WebElement element) {
		if (!element.isDisplayed()) {
			scrollToElement(element);
		}
		if (isElementClickable(element)) {
			element.click();
		} else {
			WebElement clickableElement = findClickableElement(element);
			clickableElement.click();
		}
	}

	/**
	 * Verilen web elementinin tıklanabilir olup olmadığını kontrol eder.
	 *
	 * @param element kontrol edilecek web elementi
	 * @return element tıklanabilirse true, değilse false döner
	 */
	public boolean isElementClickable(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Verilen web elementinin tıklanabilir bir element bulur.
	 *
	 * @param element hedef element
	 * @return tıklanabilir element
	 * @throws NoSuchElementException tıklanabilir element bulunamazsa
	 */
	public WebElement findClickableElement(WebElement element) {
		List<WebElement> clickableElements = driver.findElements(By.cssSelector("button.btn-search"));
		for (WebElement clickableElement : clickableElements) {
			if (clickableElement.isDisplayed()) {
				return clickableElement;
			}
		}
		throw new NoSuchElementException("Clickable element not found");
	}

	/**
	 * Verilen web elementine doğru kaydırma işlemi yapar.
	 *
	 * @param element kaydırılacak web elementi
	 */
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * Verilen web elementine metin gönderir.
	 *
	 * @param element hedef element
	 * @param text    gönderilecek metin
	 */
	public void sendKeysToElement(WebElement element, String text) {
		element.sendKeys(text);
	}

	/**
	 * Sayfanın solunda bulunan kategorideki web elementini alır.
	 *
	 * @param tag              etiket
	 * @param className        sınıf adı
	 * @param href             href değeri
	 * @param isHyrefHasPrefix href değeri öneki (/ikinci-el/{hyref} gibi.) var mı?
	 * @return belirtilen kategorideki web elementi
	 */
	public WebElement getElementInCategory(String tag, String className, String href, boolean isHyrefHasPrefix) {
		WebElement element;
		if (isHyrefHasPrefix) {
			element = driver.findElement(
					By.cssSelector(("" + tag + "." + className + " li a[href='/ikinci-el/" + href + "']")));
		} else {
			element = driver.findElement(By.cssSelector(("" + tag + "." + className + " li a[href='" + href + "']")));
		}

		return element;
	}

}
