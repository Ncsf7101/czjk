package com.rqiang.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class POITest {
    //读取POIexcel数据
    //使用XSSF加载指定文件，创建Excel对象（工具簿）
    @Test
    public void test1() throws Exception {
        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(new File("E:" + File.separator + "poi.xlsx")));
        //读取工作簿里面第一个标签页
        XSSFSheet sheetAt = excel.getSheetAt(0);
        //遍历sheet标签页，获得每一行
        for (Row row:sheetAt){
            //遍历行，获得每个单元格对象
            for(Cell cell:row){
                String value = cell.getStringCellValue();
                System.out.println(value);
            }
        }
        excel.close();
    }

    @Test
    public void test2() throws Exception {
        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(new File("E:" + File.separator + "poi.xlsx")));
        //读取工作簿里面第一个标签页
        XSSFSheet sheet = excel.getSheetAt(0);
        //获得工作表最后一个行号 从0~L 0第一行
        int lastRowNum = sheet.getLastRowNum();
        //遍历sheet标签页，获得每一行
        for(int i = 0; i<= lastRowNum; i++){
            XSSFRow row = sheet.getRow(i);
            //获得当前行最后一个单元格索引
            short lastCellNum = row.getLastCellNum();
            for(int j = 0; j<lastCellNum; j++){
                //根据单元格索引获得单元格对象
                XSSFCell cell = row.getCell(j);
                System.out.println(cell.getStringCellValue());
            }
        }
        excel.close();
    }

    @Test
    public void test3() throws Exception{
        //内存中创建，未在磁盘上创建具体文件
        XSSFWorkbook sheets = new XSSFWorkbook();
        //创建一个工作表对象
        XSSFSheet sheet = sheets.createSheet("rqiang");
        //在工作表中创建行对象
        XSSFRow title = sheet.createRow(0);
        //在行中创建单元格对象
        title.createCell(0).setCellValue("姓名");
        title.createCell(1).setCellValue("地址");        title.createCell(0).setCellValue("姓名");
        title.createCell(2).setCellValue("年龄");

        XSSFRow row = sheet.createRow(1);
        row.createCell(0).setCellValue("小明");
        row.createCell(1).setCellValue("北京");        title.createCell(0).setCellValue("姓名");
        row.createCell(2).setCellValue("20");

        //创建输出流，将内存中的excel文件写到磁盘
        FileOutputStream out = new FileOutputStream(new File("E:" +
            File.separator + "hello.xlsx"));
        sheets.write(out);
        out.flush();
        sheets.close();
    }
}
