package com.example.spider.service;

import com.example.spider.mapper.SpiderProductMapper;
import com.example.spider.pojo.SpiderRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SpiderRulesService {
    @Autowired
    SpiderProductMapper spiderProductMapper;

    public int insertRules(String site_url,String classes,String supplier){
            String siteSource = "1688淘宝批发网";
            int status = 0;
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            String createDate = dateFormat.format(date);
            return spiderProductMapper.insertRules(site_url,classes,supplier,createDate,siteSource,status);
    }

    public int deleteSpiderRule(int ruleId) {
        return spiderProductMapper.deleteSpiderRule(ruleId);
    }

    public List<SpiderRules> getAllSpiderRule() {
        return spiderProductMapper.getAllSpiderRule();
    }
}
