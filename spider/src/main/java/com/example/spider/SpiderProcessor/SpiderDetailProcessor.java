package com.example.spider.SpiderProcessor;

import com.example.spider.controller.spiderController;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import javax.swing.text.html.HTML;
import java.net.URLDecoder;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class SpiderDetailProcessor implements PageProcessor {

    @Autowired
    private spiderController spidercontroller;

    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

    @Override
    public void process(Page page) {

        String url =page.getRequest().getUrl();
        String[] arr = url.split("/");
        String url_label = arr[2].trim();

            //调取Firefox浏览器后台打开
            FirefoxBinary firefoxBinary = new FirefoxBinary();
//        firefoxBinary.addCommandLineOptions("--headless");
            System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Mozilla Firefox\\geckodriver.exe");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setBinary(firefoxBinary);
            FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
            driver.get(url);

//        driver.switchTo().frame(0);
////        浏览器显示最大化,跳到账号密码登陆方式开始填入
//        driver.manage().window().maximize();
//        WebElement ele = driver.findElement(By.id("J_LoginBox"));
//        String sD = ele.getAttribute("innerHTML");
//        Html s = new Html(sD);
//        page.putField("aaa",s.xpath("//div").toString());
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        try {
//            driver.findElement(By.id("J_Quick2Static")).click();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            driver.findElement(By.id("TPL_username_1")).sendKeys("aaaaaaaa");
//            driver.findElement(By.id("TPL_password_1")).sendKeys("aaaaaaaa");
//            driver.findElement(By.id("J_SubmitStatic")).sendKeys("J_SubmitStatic");
//        }
//        //获取cookie
//        Set<Cookie> cookies = driver.manage().getCookies();
//        String cookieStr = "";
//        for (Cookie cookie : cookies) { cookieStr += cookie.getName() + "=" + cookie.getValue() + "; "; }


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(url_label.equals("detail.1688.com")){
            WebElement elements = driver.findElement(By.id("mod-detail-bd"));
            WebElement elementss = driver.findElement(By.id("mod-detail-title"));
            WebElement element = driver.findElement(By.className("offerdetail_ditto_attributes"));
            String htmlA = element.getAttribute("innerHTML");
            String htmlB = elementss.getAttribute("innerHTML");
            String htmlS = elements.getAttribute("innerHTML");
            Html html2 = new Html(htmlB);
            Html html1 = new Html(htmlS);
            Html html = new Html(htmlA);
            //规则
            page.putField("ProductTitleName",html2.xpath("//h1").toString());
            page.putField("ProductImage",html1.xpath("//ul[@class='nav']/li/div/a/img").css("img","src").all());
            page.putField("ProductDetail",html.xpath("//div[@class='obj-content']/table/tbody/").all());
            page.putField("ProductPrice",html1.xpath("//tr[@class='price']/td/span").all());
        }else{
            page.putField("ProductPrice","暂时未开放该页面的爬取");
        }

        driver.close();
    }

    @Override
    public Site getSite() {
        return site;
    }
}
