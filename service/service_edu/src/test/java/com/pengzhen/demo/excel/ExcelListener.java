package com.pengzhen.demo.excel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<DemoData> {
    //invokeHeadMap读取数据中表头中内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println(headMap);
    }

    //invoke对execl中的数据中进行一行一行的读取
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println(demoData);
    }
//读取完成之后的操作
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
