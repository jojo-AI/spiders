package com.example.spider.mapper;

import com.example.spider.pojo.SpiderProduct;
import com.example.spider.pojo.SpiderRules;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpiderProductMapper {

    @Select("select * from SpiderProduct sp where sp.StuffId = #{stuffId} AND sp.status !=3 ORDER BY sp.id DESC")
    List<SpiderProduct> getUncheckSpiderProduct(@Param("stuffId")String id);


    @Insert("insert into SpiderProduct(Pname,Price,Image,StuffId,status) values(#{name}, #{price}, #{img}," +
            "#{stuffid},0)")
    int putUncheckSpiderProduct(@Param("img")String img, @Param("name")String name,
                                @Param("stuffid")String stuffid, @Param("price")String price);


    @Insert("insert into SpiderRules(url,classes,supplier,createTime,status,siteSource) values(#{site_url}, #{classes}, #{supplier}," +
            "#{createDate},#{status},#{siteSource})")
    int insertRules(@Param("site_url")String site_url, @Param("classes")String classes, @Param("supplier")String supplier
    ,@Param("createDate")String createDate,@Param("siteSource")String siteSource,@Param("status")int status);

    @Update("update SpiderRules sr set sr.status = 1 where sr.id=#{ruleId}")
    int deleteSpiderRule(@Param("ruleId")int ruleId);

    @Select("select * from SpiderRules sr where sr.status = 0")
    List<SpiderRules> getAllSpiderRule();

    @Update("update SpiderProduct sp set sp.Pcode = #{pcode},sp.sku= #{sku} where sp.id=#{productId}")
    int updateSpiderProductMessage(@Param("productId")String productId, @Param("sku")String sku, @Param("pcode")String pcode);

    @Update("update SpiderProduct sp set sp.status = #{status},sp.manageId= #{manageId} where sp.id=#{productId}")
    int AuditingSpiderProduct(@Param("productId")String productId, @Param("status")String result, @Param("manageId")String manageId);

    @Update("update SpiderProduct sp set sp.status = 3 where sp.id=#{id}")
    int deleteUncheckSpiderProduct(@Param("id")int productId);
}
