package com.example.spider.service;

import com.example.spider.mapper.SpiderProductMapper;
import com.example.spider.pojo.SpiderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpiderProductService {

    @Autowired
    SpiderProductMapper spiderProductMapper;

    public List<SpiderProduct> getUncheckSpiderProduct(String id){
        return spiderProductMapper.getUncheckSpiderProduct(id);
    }

    public int putUncheckSpiderProduct(String img, String name, String stuffid, String price){
        return spiderProductMapper.putUncheckSpiderProduct(img,name,stuffid,price);
    }


    public int updateSpiderProductMessage(String productId, String sku, String pcode) {
        return spiderProductMapper.updateSpiderProductMessage(productId,sku,pcode);
    }

    public int AuditingSpiderProduct(String productId, String result, String manageId) {
        return spiderProductMapper.AuditingSpiderProduct(productId,result,manageId);
    }

    public int deleteUncheckSpiderProduct(int productId) {
        return spiderProductMapper.deleteUncheckSpiderProduct(productId);
    }
}
