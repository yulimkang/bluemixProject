package com.ibmMeeting.excel;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

@SuppressWarnings("deprecation")
public class MyExcelView extends AbstractExcelView
{
    @SuppressWarnings("unchecked")
    protected void buildExcelDocument(Map<String, Object> model,
            HSSFWorkbook workbook,
            HttpServletRequest request,
            HttpServletResponse response)
    {
    	String sheetName = (String)model.get("rsvSheetName");
        List<String> headers = (List<String>)model.get("rsvHeaders");
        List<List<String>> results = (List<List<String>>)model.get("rsvResults");
        List<String> numericColumns = new ArrayList<String>();
        if (model.containsKey("numericcolumns"))
            numericColumns = (List<String>)model.get("numericcolumns");
        //BUILD DOC
        HSSFSheet sheet = workbook.createSheet(sheetName);
        sheet.setDefaultColumnWidth((short) 12);
        int currentRow = 0;
        short currentColumn = 0;
        //CREATE STYLE FOR HEADER
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        HSSFFont headerFont = workbook.createFont();
        headerStyle.setFont(headerFont); 
        //POPULATE HEADER COLUMNS
        HSSFRow headerRow = sheet.createRow(currentRow);
        for(String header:headers){
            HSSFRichTextString text = new HSSFRichTextString(header);
            HSSFCell cell = headerRow.createCell(currentColumn); 
            cell.setCellStyle(headerStyle);
            cell.setCellValue(text);
            currentColumn++;
        }
        //POPULATE VALUE ROWS/COLUMNS
        currentRow++;//exclude header
        for(List<String> result: results){
            currentColumn = 0;
            HSSFRow row = sheet.createRow(currentRow);
            for(String value : result){//used to count number of columns
                HSSFCell cell = row.createCell(currentColumn);
                    HSSFRichTextString text = new HSSFRichTextString(value);                
                    cell.setCellValue(text);                    
                currentColumn++;
            }
            currentRow++;
        }
        
        
        
        // --------------------------------------------------------------------
        
        sheetName = (String)model.get("hstSheetName");
        headers = (List<String>)model.get("hstHeaders");
        results = (List<List<String>>)model.get("hstResults");
        numericColumns = new ArrayList<String>();
        if (model.containsKey("numericcolumns"))
            numericColumns = (List<String>)model.get("numericcolumns");
        //BUILD DOC
        sheet = workbook.createSheet(sheetName);
        sheet.setDefaultColumnWidth((short) 12);
        currentRow = 0;
        currentColumn = 0;
        //CREATE STYLE FOR HEADER
        headerStyle = workbook.createCellStyle();
        headerFont = workbook.createFont();
        headerStyle.setFont(headerFont); 
        //POPULATE HEADER COLUMNS
        headerRow = sheet.createRow(currentRow);
        for(String header:headers){
            HSSFRichTextString text = new HSSFRichTextString(header);
            HSSFCell cell = headerRow.createCell(currentColumn); 
            cell.setCellStyle(headerStyle);
            cell.setCellValue(text);
            currentColumn++;
        }
        //POPULATE VALUE ROWS/COLUMNS
        currentRow++;//exclude header
        for(List<String> result: results){
            currentColumn = 0;
            HSSFRow row = sheet.createRow(currentRow);
            for(String value : result){//used to count number of columns
                HSSFCell cell = row.createCell(currentColumn);
                    HSSFRichTextString text = new HSSFRichTextString(value);                
                    cell.setCellValue(text);                    
                currentColumn++;
            }
            currentRow++;
        }

    }

}