package com.example.spider.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class CompanyProcessor implements PageProcessor {

    private final static String site_url="https://p4psearch.1688.com/p4p114/p4psearch/offer.htm?spm=a2609.11209760.it2i6j8a.31.67b62de13QXpVN&cosite=&keywords=%E7%9F%AD%E8%A2%96t%E6%81%A4%E5%A5%B3&trackid=&location=";

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");


    public void process(Page page) {
//        String u =page.getRequest().getUrl();
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Mozilla Firefox\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get(site_url);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement webElement = driver.findElement(By.className("mt20"));
        String str = webElement.getAttribute("outerHTML");
        System.out.println("scrapping website......:"+str);

        Html html = new Html(str);
        List<String> list = html.xpath("//div").all();
        for (String lists : list){
            System.out.println(lists);
        }
        page.putField("img", html.xpath("//div").all());
//        System.out.println(html.xpath("//p").all());
        String companyCode = html.xpath("//tbody/tr[1]/td/text()").get();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(companyCode);
        driver.close();

    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider.create(new CompanyProcessor())
                .addUrl(site_url)
                .thread(5)
                .addPipeline(new FilePipeline("D:\\1688888888888888888\\"))
                .run();
    }

}
