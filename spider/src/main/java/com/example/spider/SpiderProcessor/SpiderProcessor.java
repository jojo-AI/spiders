package com.example.spider.SpiderProcessor;

import com.example.spider.controller.spiderController;
import com.example.spider.pojo.SpiderParam;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class SpiderProcessor implements PageProcessor {

    private Site site = Site.me()
            .setRetryTimes(3)
            .setRetrySleepTime(3000)
            .setSleepTime(3)
            .setTimeOut(10000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

    @Override
    public void process(Page page) {

        String url =page.getRequest().getUrl();
        String site_url = URLDecoder.decode(url);
        String[] arr = site_url.split("/");
        String url_label = arr[2].trim();

        //调取Firefox浏览器后台打开
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Mozilla Firefox\\geckodriver.exe");

//        System.setProperty("webdriver.gecko.driver", "//home//admin//geckodriver");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
        driver.get(site_url);


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement elements;
        if(url_label.equals("p4psearch.1688.com")){
            elements = driver.findElement(By.className("offer_list"));
            String htmlStr = elements.getAttribute("innerHTML");
            Html html1 = new Html(htmlStr);
            page.putField("ProductTitleName",html1.xpath("//div/a/div[@class='title']").all());
            page.putField("ProductImage",html1.xpath("//div/a/div[@class='img']/img").css("img","src").all());
            page.putField("ProductDetail",html1.xpath("//div/a/div[@class='tag_warp']").all());
            page.putField("ProductPrice",html1.css("div.price_warp").css("span.price").all());
            //去重
//            List list_1 = removeDuplicate(html1.css("div.price_warp").css("a","href").all());
            page.putField("ProductLinkHref",html1.xpath("//div[@class='offer_item']/a").css("a","href").all());


        }else if(url_label.equals("show.1688.com")){
            elements = driver.findElement(By.className("list"));
            String htmlStr = elements.getAttribute("innerHTML");
            Html html1 = new Html(htmlStr);
            page.putField("ProductTitleName",html1.xpath("//div/a/div/div/div[@class='offer-title']").all());
            page.putField("ProductImage",html1.xpath("//div/a/div[@class='offer-img']").css("img","src").all());
            page.putField("ProductDetail",html1.xpath("//div[@class='clearfix']/div[@class='alife-bc-uc-textLine']").css("div","title").all());
            page.putField("ProductPrice",html1.xpath("//div/a/div[@class='offer-desc']/div/p/span/span/span").all());
            page.putField("ProductLinkHref",html1.xpath("//div").css("a","href").all());
        }else{
            page.putField("Warning: 暂时无法爬取该页面","");
        }

        driver.close();
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static List removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

}
