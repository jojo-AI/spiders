package com.example.spider.SpiderPipeline;

import com.example.spider.controller.spiderController;
import com.example.spider.pojo.SpiderResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.Map;

@Component
public class resultPipeline implements Pipeline {

    @Autowired
    private static SpiderResult result;

    @Autowired
    private static ResultItems resultContent;

    @Override
    public void process(ResultItems resultItems, Task task) {
        resultContent = resultItems;
        setResult();
        }

        public ResultItems setResult(){
        return resultContent;
        }

    }
