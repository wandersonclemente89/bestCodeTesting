package br.com.geopostelight.companies.utils;

import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest{

   private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

   public static final Integer DEFAULT_WAIT_TIMEOUT = 40;

   @Managed
   WebDriver driver;

   WebDriverWait wait =  new WebDriverWait(driver,DEFAULT_WAIT_TIMEOUT);

   public boolean waitForVisibilityOf(WebElement element) {
      try {
         wait.until(ExpectedConditions.visibilityOf(element));
      } catch (Throwable t) {
         return false;
      }
      return true;
   }

   public boolean waitForElementToBeClickable(WebElement element) {
      try {
         wait.until(ExpectedConditions.elementToBeClickable(element));
      } catch (Throwable t) {
         return false;
      }
      return true;
   }

   public void click(WebElement element){
      waitForVisibilityOf(element);
      waitForElementToBeClickable(element);
      try {
         element.click();
      } catch (Throwable t) {
         logger.info("WebElement.click didn't work:" + t.getMessage());
      }
   }

   public String getText(WebElement element) {
      waitForVisibilityOf(element);
      return element.getText().trim();
   }
}
