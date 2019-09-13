package com.lib.equipment.manager.utils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.lib.equipment.manager.model.Material;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExcelUtils{

    public static void exportExcel(String fileName,String sheetName,HttpServletResponse response,List<?extends BaseRowModel>data){

        ExcelWriter writer = null;
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
//            String fileName = "器材汇总表格";
            Sheet sheet = new Sheet(1, 0, Material.class);
            sheet.setSheetName(sheetName);
            writer.write(data, sheet);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xlsx").getBytes(), "ISO8859-1"));
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (writer != null) {
                writer.finish();
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public static void writeExcel(String pathName, List<? extends BaseRowModel>dataInfo)throws Exception{
        OutputStream out = new FileOutputStream(pathName);
        ExcelWriter writer = EasyExcelFactory.getWriter(out);
        Sheet sheet = new Sheet(1,0, Material.class);
        sheet.setSheetName("material");
        writer.write(dataInfo,sheet);
        writer.finish();
        out.close();
    }

    public static void cooperation(HttpServletResponse response, String fileName, String sheetName, List<? extends BaseRowModel> dataInfo, Class<? extends BaseRowModel> clazz) throws IOException {
        ExcelWriter writer = null;
        OutputStream out = null;
        out = response.getOutputStream();
        writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
        Sheet sheet1 = new Sheet(1, 0,clazz);
        sheet1.setSheetName("第一个sheet");
        writer.write(dataInfo, sheet1);
        writer.finish();
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename="+fileName+".xlsx");
        response.setHeader("FileName", fileName+".xlsx");
        out.flush();
        out.close();
    }
    public static void cooperation1(HttpServletResponse response,String fileName,String sheetName,List<List<Object>> dataInfo,List<List<String>> params) throws IOException {
        ExcelWriter writer = null;
        OutputStream out = null;
        out = response.getOutputStream();
        writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
        Sheet sheet1 = new Sheet(1, 0);
        sheet1.setSheetName("第一个sheet");
        sheet1.setHead(params);
        writer.write1(dataInfo, sheet1);
        writer.finish();
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename="+fileName+".xlsx");
        response.setHeader("FileName", fileName+".xlsx");
        out.flush();
    }
}


