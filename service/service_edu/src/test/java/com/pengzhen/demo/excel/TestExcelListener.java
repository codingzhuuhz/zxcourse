package com.pengzhen.demo.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestExcelListener {
    @Test
    public void TestReaderExcel(){
        String fileName = "E:\\demo.xlsx";
        EasyExcel.read(new File(fileName),DemoData.class,new ExcelListener()).sheet().doRead();
    }
    @Test
    public void TestWriteExcel(){
        String fileName = "E:\\demo.xlsx" ;
        EasyExcel.write(fileName,DemoData.class).sheet("学生列表").doWrite(Data());
    }
    public static List<DemoData> Data(){
        List<DemoData> list = new ArrayList<>();
        for (int i=0 ;i<=10;i++) {
            DemoData dd = new DemoData();
            dd.setName("朋震"+i);
            dd.setAge(18);
            list.add(dd);
        }
        return list;
    }

}
