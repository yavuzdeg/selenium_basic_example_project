package bookSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookSearch2 {

    /*
    Open the kitapyurdu website, change website language to English.
    Then verify that the authors of the top 20 best seller books of the last 10 years contains following names:
    Stefan Zweig, Sabahattin Ali, Lev Tolstoy, John Steinbeck, Gabriel Garcia Marquez, Amin Maalouf.
    Verify that the first book of the list has been sold more than the second one and the last one, separately.
     */

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\yavuz\\SeleniumDependencies\\drivers\\geckodriver.exe");

        WebDriver driver = new FirefoxDriver();

        driver.get("https://www.kitapyurdu.com/");

        WebElement languages = driver.findElement(By.id("language-form"));
        languages.click();

        Thread.sleep(1000);

        WebElement englishLanguage = driver.findElement(By.xpath("//*[@class='common-sprite icon-lang-en lang-list-link']"));
        englishLanguage.click();

        Actions actions = new Actions(driver);

        actions.moveToElement(driver.findElement(By.xpath("(//*[@class='js-ajaxCt js-bookCt']//*[@class='has-open-menu'])[1]"))).build().perform();

        Thread.sleep(500);

        WebElement bestSeller10Years = driver.findElement(By.linkText("Best Seller Books of 10 Years"));
        bestSeller10Years.click();

        List<WebElement> authors = driver.findElements(By.className("author"));
        Set<String> authorsExist = new HashSet<>();

        for (WebElement element: authors) {

            if(element.getText().contains("Stefan Zweig")){
                authorsExist.add("Stefan Zweig");

            }if(element.getText().contains("Sabahattin Ali")){
                authorsExist.add("Sabahattin Ali");

            }if(element.getText().contains("Lev Tolstoy")){
                authorsExist.add("Lev Tolstoy");

            }if(element.getText().contains("John Steinbeck")){
                authorsExist.add("John Steinbeck");

            }if(element.getText().contains("Gabriel Garcia Marquez")){
                authorsExist.add("Gabriel Garcia Marquez");

            }if(element.getText().contains("Amin Maalouf")){
                authorsExist.add("Amin Maalouf");

            }

        }
        if(authorsExist.isEmpty()){
            System.out.println("FAIL: There is not any book from the authors");
        }else{
            for (String author: authorsExist) {
                System.out.println(author + " is one of the authors of the top 20 best seller book list");
            }
        }

        driver.quit();

    }


}
