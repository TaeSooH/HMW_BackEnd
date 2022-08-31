package hmw.hmwServer.controller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;

@Component
@RestController
public class SearchController {
//    private static WebDriver driver;
//    private static final String url = "https://en.dict.naver.com/#/search?range=all&query=coffee";

    @RequestMapping("/search")
    public List<String> process(@RequestParam("word") String word) throws Exception {
        String url = "https://dic.daum.net/search.do?q=";
        List<String> result = new ArrayList<String>();
        url = url + word + "&dic=eng&search_first=Y";
        System.setProperty("webdriver.chrome.driver","chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");       //팝업안띄움
        options.addArguments("headless");                       //브라우저 안띄움
        options.addArguments("--disable-gpu");			//gpu 비활성화
        options.setExperimentalOption("w3c", true);
        options.addArguments("--blink-settings=imagesEnabled=false"); //이미지 다운 안받음
        WebDriver driver = new ChromeDriver(options);

        try {
            result = getDataList(url, driver);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.close();
        driver.quit();
        return result;
    }
    private static List<String> getDataList(String url, WebDriver driver) throws InterruptedException {
        List<String> list = new ArrayList<>();
        Duration duration = Duration.of(1 , SECONDS) ;
        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);
        driver.get(url);


        List<WebElement> elements = driver.findElements(By.cssSelector(".box_example"));
        List<WebElement> elements2 = driver.findElements(By.cssSelector(".list_search"));
        list.add(elements.get(0).getText());
        list.add(elements2.get(0).getText());
        return list;
    }
}