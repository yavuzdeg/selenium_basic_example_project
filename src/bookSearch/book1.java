package bookSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.List;


public class book1 {

    /*

    Open the kitapyurdu website and confirm and the title of the page contains "kitapyurdu".
    Then, search the word "software requirements" and verify that the result stated below the page is same with the book list.

     */

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\yavuz\\SeleniumDependencies\\drivers\\geckodriver.exe");

        WebDriver driver = new FirefoxDriver();

        driver.get("https://www.kitapyurdu.com/");

        driver.manage().window().maximize();

        String actualTitle = driver.getTitle();

        if(actualTitle.toLowerCase().contains("kitapyurdu")){
            System.out.println("Title - PASS");
        }else{
            System.out.println("Title - FAIL");
            System.out.println("Actual Title: " + actualTitle);
        }

        WebElement languages = driver.findElement(By.id("language-form"));
        languages.click();

        Thread.sleep(1000);

        WebElement englishLanguage = driver.findElement(By.xpath("//*[@class='common-sprite icon-lang-en lang-list-link']"));
        englishLanguage.click();

        WebElement searchTextbox = driver.findElement(By.id("search-input"));
        searchTextbox.sendKeys("software requirements" + Keys.ENTER);

        Thread.sleep(1000);

        WebElement searchResult = driver.findElement(By.className("results"));
        int searchResultTotal = Integer.parseInt(searchResult.getText().split(" ")[3]);

        List<WebElement> bookTitles = driver.findElements(By.className("name"));

        int counter = 0;

        for(WebElement title: bookTitles){
            if(title.getText().toLowerCase().contains("software requirements")){
                counter++;
            }
        }

        if(searchResultTotal==counter){
            System.out.println("Search - PASS");
        }else{
            System.out.println("Search - FAIL");
            System.out.println("Total result written down: " + searchResultTotal);
            System.out.println("Books with keywords: " + counter);
        }

        driver.close();
    }
}
