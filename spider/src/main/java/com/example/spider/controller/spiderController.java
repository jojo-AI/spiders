package com.example.spider.controller;

import com.example.spider.SpiderPipeline.resultPipeline;
import com.example.spider.SpiderProcessor.SpiderDetailProcessor;
import com.example.spider.SpiderProcessor.SpiderProcessor;
import com.example.spider.mapper.SpiderProductMapper;
import com.example.spider.pojo.*;
import com.example.spider.service.SpiderProductService;
import com.example.spider.service.SpiderRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.codecraft.webmagic.Spider;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class spiderController {

    @Autowired
    private resultPipeline resultPipeline;
    @Autowired
    private SpiderProcessor spiderProcessor;
    @Autowired
    private SpiderDetailProcessor spiderDetailProcessorProcessor;
    @Autowired
    static SpiderParam param;
    @Autowired
    private static String url;
    @Autowired
    SpiderProductMapper spiderProductMapper;
    @Autowired
    SpiderRulesService spiderRulesService;
    @Autowired
    SpiderProductService spiderProductService;

    @ResponseBody
    @RequestMapping("/getContentByUrl")
    public Map<String, Object> getContentByUrl(HttpServletRequest request) {
        //获取到post或者get方法传过来的参数
        url = request.getParameter("url");
        String site_url = URLDecoder.decode(url);
        String classes = request.getParameter("classes");
        String supplier = request.getParameter("supplier");
        Spider.create(spiderProcessor)
                .addUrl(url)
                .thread(10)
                .addPipeline(resultPipeline)
                .run();
        System.out.println("爬取完成，现在将爬取任务规则入库");
        int isSussecced =spiderRulesService.insertRules(site_url,classes,supplier);
        List<String> a = resultPipeline.setResult().get("ProductTitleName");
        List<String> b = resultPipeline.setResult().get("ProductImage");
        List<String> c = resultPipeline.setResult().get("ProductDetail");
        List<String> d = resultPipeline.setResult().get("ProductPrice");
        List<String> e = resultPipeline.setResult().get("ProductLinkHref");

        return resultPipeline.setResult().getAll();
    }

    @ResponseBody
    @RequestMapping("/putUncheckSpiderProduct")
    public int putUncheckSpiderProduct(HttpServletRequest request) {
        String img = request.getParameter("Image");
        String name = request.getParameter("Pname");
        String stuffid=request.getParameter("StuffId");
        String price = request.getParameter("Price");
        int insertSuccess = spiderProductService.putUncheckSpiderProduct(img,name,stuffid,price);
        return insertSuccess;
    }

    @ResponseBody
    @RequestMapping("/putUncheckSpiderProductList")
    public StringBuffer putUncheckSpiderProductList(@RequestBody List<SpiderProduct> spiderProductList) {
      StringBuffer resultString = new StringBuffer();
        for(SpiderProduct spiderProduct : spiderProductList){
            String img = spiderProduct.getImage();
            String name = spiderProduct.getPname();
            String stuffid=spiderProduct.getStuffId();
            String price = spiderProduct.getPrice();
            int insertSuccess = spiderProductService.putUncheckSpiderProduct(img,name,stuffid,price);
            if(insertSuccess>0){
                resultString.append("商品"+name+"提交成功；");
            }else if(insertSuccess==0){
                resultString.append("商品"+name+"提交失败；");
            }
        }
        return resultString;
    }

    @ResponseBody
    @RequestMapping("/getUncheckSpiderProduct")
    public List<SpiderProduct> getUncheckSpiderProduct(HttpServletRequest request) {
        String stuffId = request.getParameter("stuffId");
        List<SpiderProduct> list = spiderProductService.getUncheckSpiderProduct(stuffId);
        return list;
    }

    @ResponseBody
    @RequestMapping("/deleteSpiderRule")
    public int deleteSpiderRule(HttpServletRequest request) {
        String id = request.getParameter("id");
        int ruleId = Integer.parseInt(id);
        int succeed = spiderRulesService.deleteSpiderRule(ruleId);
        return succeed;
    }

    @ResponseBody
    @RequestMapping("/deleteUncheckSpiderProduct")
    public int deleteUncheckSpiderProduct(HttpServletRequest request) {
        String id = request.getParameter("id");
        int productId = Integer.parseInt(id);
        int succeed = spiderProductService.deleteUncheckSpiderProduct(productId);
        return succeed;
    }

    @ResponseBody
    @RequestMapping("/getAllSpiderRule")
    public List<SpiderRules> getAllSpiderRule(HttpServletRequest request) {
        List<SpiderRules> list = spiderRulesService.getAllSpiderRule();
        return list;
    }

    @ResponseBody
    @RequestMapping("/updateSpiderProductMessage")
    public int updateSpiderProductMessage(HttpServletRequest request) {
        String sku = request.getParameter("sku");
        String Pcode = request.getParameter("Pcode");
        String productId = request.getParameter("id");
        int insertSuccess = spiderProductService.updateSpiderProductMessage(productId,sku,Pcode);
        return insertSuccess;
    }

    @ResponseBody
    @RequestMapping("/AuditingSpiderProduct")
    public int AuditingSpiderProduct(HttpServletRequest request) {
        String productId = request.getParameter("productId");
        String result = request.getParameter("result");
        String manageId = request.getParameter("manageId");
        int audit = spiderProductService.AuditingSpiderProduct(productId,result,manageId);
        return audit;
    }

}
