package utilities;

import bsh.StringUtil;
import com.myproject.cucumber.exceptions.TestingException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

@Component
public class ExcelUtils {
    private static Logger logger = LogManager.getLogger(ExcelUtils.class);

    public ExcelUtils() {
    }

    public static ArrayList<ArrayList<String>> getDataBysql(String filePath, String sql, boolean includeHeaders) {
        ArrayList<ArrayList<String>> ret = new ArrayList();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        if (Paths.get(filePath).toFile().exists()) {
            try {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                String conStr = "jdbc:odbc:Driver ={MicroSoft Excel Driver(*.xls)};DBQ=" + filePath + ";";
                connection = DriverManager.getConnection(conStr);
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                int numCols = resultSet.getMetaData().getColumnCount();
                int i;
                if (includeHeaders) {
                    ret.add(new ArrayList());
                    for (i = 1; i <= numCols; ++i) {
                        ((ArrayList) ret.get(ret.size() - 1)).add(resultSet.getMetaData().getColumnName(i));
                    }
                }

                while (resultSet.next()) {
                    ret.add(new ArrayList());
                    for (i = 1; i <= numCols; ++i) {
                        ((ArrayList) ret.get(ret.size() - 1)).add(resultSet.getString(i));
                    }
                }
            } catch (Exception var18) {
                CucumberLogUtils.logError(var18.getMessage());
                logger.error(var18.getMessage(), var18);
            } finally {
                try {
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (SQLException var17) {
                    logger.error(var17.getMessage(), var17);
                }
            }
        }
        return ret;
    }

    public static Map<String, String> getRowDataByRowNum(File excelFile, int rowNum) throws InvalidFormatException, Exception {
        new HashMap();
        Map<String, String> columnNameValuePair = new HashMap();
        columnNameValuePair.put("id", Integer.valueOf(rowNum).toString());
        Map<String, String> dataHash = getDataValues(excelFile, columnNameValuePair);
        return dataHash;
    }

    public static Map<String, String> getRowDataByRowValue(File excelFile, String columnName, String columnValue) throws Exception {
        new HashMap();
        Map<String, String> columnNameValuePair = new HashMap();
        columnNameValuePair.put(columnName, columnValue);
        Map<String, String> dataHash = getDataValues(excelFile, columnNameValuePair);
        return dataHash;
    }

    private static Map<String, String> getDataValues(File excelFile, Map<String, String> columnNameValuePair) throws InvalidFormatException, IOException{
        Sheet sheet = getFirstSheet(excelFile);
        Map<String, String> dataValues = getValuesFormSheet(sheet,columnNameValuePair);
        return dataValues;
    }
    private static Map<String, String> getValuesFormSheet(Sheet sheet, Map<String, String> value){
        Map.Entry<String, String> nameValue = (Map.Entry)value.entrySet().iterator().next();
        String columnName = ((String)nameValue.getKey()).trim();
        String columnValue = ((String)nameValue.getValue()).trim();
        Row headerRow = sheet.getRow(sheet.getFirstRowNum());
        int cellNumForColumnName = getCorrespondingCellNum(headerRow, columnName);
        Row valueRow = getRowForvalue(sheet, cellNumForColumnName, columnValue);
        return getCellValues(headerRow, valueRow);
    }

    private static Row getRowForvalue(Sheet sheet, int releventCellNum, String columnValue){
        Row releventRow = null;
        int firstRowNum = sheet.getFirstRowNum()+1;
        int lastRowNum = sheet.getLastRowNum();
        Row currentRow = null;

        for(int rowNum = firstRowNum; rowNum <= lastRowNum; ++ rowNum){
            currentRow = sheet.getRow(rowNum);
            if(currentRow != null){
                Cell releventCell = currentRow.getCell(releventCellNum);
                String cellValue = getCellValue(releventCell);
                if(StringUtils.isNotBlank(cellValue) && cellValue.equals(columnValue)){
                    releventRow = currentRow;
                    break;
                }
            }
        }
        return releventRow;
    }

    static int getCorrespondingCellNum(Row headerRow, String columnName){
        int releventCellNum = -1;
        if(headerRow != null && StringUtils.isNotBlank(columnName)){
            int firstCellNum = headerRow.getFirstCellNum();
            int lastCellNum = headerRow.getLastCellNum();
            for(int cellNum = firstCellNum; cellNum <= lastCellNum; ++cellNum){
                Cell cell = headerRow.getCell(cellNum);
                String cellValue = getCellValue(cell);
                if(StringUtils.isNotBlank(cellValue) && cellValue.equals(columnName)){
                    releventCellNum = cellNum;
                    break;
                }
            }
        }
        return releventCellNum;
    }
    static Map<String, String> getCellValues(Row headerRow, Row valueRow){
        Map<String, String> cellValues = new HashMap();
        if(headerRow != null && valueRow != null){
            int firstCellNum = headerRow.getFirstCellNum();
            int lastCellNum = headerRow.getLastCellNum();
            for(int cellNum = firstCellNum; cellNum <= lastCellNum; ++cellNum){
                String key = getCellValue(headerRow.getCell(cellNum));
                String value = getCellValue(valueRow.getCell(cellNum));
                cellValues.put(key, value);
            }
        }
        return cellValues;
    }

    static Sheet getFirstSheet(File excelFile)throws InvalidFormatException, IOException{
        InputStream inp = new FileInputStream(excelFile);
        Workbook workbook = WorkbookFactory.create(inp);
        Sheet sheet = workbook.getSheetAt(0);
        return sheet;
    }

    private static String getCellValue(Cell valueCell) {
        String cellVal = null;
        if (valueCell != null) {
            switch (valueCell.getCellType()) {
                case BLANK:
                    cellVal = "";
                    break;
                case BOOLEAN:
                    ;
                    Boolean val = valueCell.getBooleanCellValue();
                    cellVal = val.toString();
                    break;
                case NUMERIC:
                    Integer intVal = (int) valueCell.getNumericCellValue();
                    cellVal = intVal.toString();
                    break;
                case STRING:
                    cellVal = valueCell.getStringCellValue();
            }
        }
        return cellVal;
    }


    public static void csvToXslx(String csvPath, String xlsxPath) {
        try {
            XSSFWorkbook workBook = new XSSFWorkbook();
            XSSFSheet sheet = workBook.createSheet("sheet1");
            String currentLine = null;
            int rowNum = 0;
            BufferedReader br = new BufferedReader(new FileReader(csvPath));
            while ((currentLine = br.readLine()) != null) {
                String[] str = splitLineIgnoringCommasInQuotes(currentLine);
                XSSFRow currentRow = sheet.createRow(rowNum);
                ++rowNum;
                for (int i = 0; i < str.length; ++i) {
                    currentRow.createCell(i).setCellValue(str[i]);
                }
            }
            br.close();
            FileOutputStream fos = new FileOutputStream(xlsxPath);
            workBook.write(fos);
            fos.close();
            logger.info(String.format("successfully created xlsx at %s", xlsxPath));
        } catch (Exception var10) {
            logger.error((String.format("creation of xmslx at %s failed", xlsxPath)));
        }

    }

    public static String[] splitLineIgnoringCommasInQuotes(String line) {
        String otherThanQuote = "[^\"]";
        String quotedString = String.format(" \" %s* \" ", otherThanQuote);
        String regex = String.format("(?x), (?=( %s* %s)* %s* $) ", otherThanQuote, quotedString, otherThanQuote);
        return line.split(regex, -1);
    }

    public static void setRowFontToBold(String filePath, String sheetName, int rowNum) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            XSSFWorkbook workBook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workBook.getSheet(sheetName);
            XSSFRow currentRow = sheet.getRow(rowNum);
            CellStyle cellStyle = getBoldTextCellStyle(workBook);
            for (int i = 0; i < currentRow.getLastCellNum(); ++i) {
                XSSFCell currentCell = currentRow.getCell(i);
                currentCell.setCellStyle(cellStyle);
            }

            FileOutputStream fos = new FileOutputStream(filePath);
            workBook.write(fos);
            fos.close();
        } catch (Exception var10) {
            var10.printStackTrace();
        }
    }


    public static CellStyle getRedTextCellStyle(XSSFWorkbook workBook) {
        CellStyle cellstyle = workBook.createCellStyle();
        Font font = workBook.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        cellstyle.setFont(font);
        return cellstyle;
    }

    public static CellStyle getBoldTextCellStyle(XSSFWorkbook workBook) {
        CellStyle cellstyle = workBook.createCellStyle();
        Font font = workBook.createFont();
        cellstyle.setFont(font);
        return cellstyle;
    }

    public static CellStyle getGreenTextCellStyle(XSSFWorkbook workBook) {
        CellStyle cellstyle = workBook.createCellStyle();
        Font font = workBook.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
        cellstyle.setFont(font);
        return cellstyle;
    }

    public static XSSFWorkbook loadXSSFWorkBook(String filePath) {
        XSSFWorkbook workBook = null;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            workBook = new XSSFWorkbook(fis);
        } catch (Exception var3) {
            var3.printStackTrace();
        }
        return workBook;
    }

    public static ArrayList<ArrayList<String>> xlsToArrayList(XSSFWorkbook workBook, String sheetName) throws TestingException {
        ArrayList<ArrayList<String>> ret = new ArrayList();
        ArrayList<String> row = null;
        XSSFSheet sheet = workBook.getSheet(sheetName);
        for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); ++i) {
            XSSFRow currentRow = sheet.getRow(i);
            row = new ArrayList();
            for (int j = currentRow.getFirstCellNum(); j < currentRow.getLastCellNum(); ++j) {
                try {
                    currentRow.getCell(j).setCellType(CellType.STRING);
                    row.add(currentRow.getCell(j).getStringCellValue());
                } catch (NullPointerException var10) {
                    TestingException tetException = new TestingException((var10.getMessage() + "\nNULL Value at row = " + (i + 1) + "and  Col = " + (j + 1)), var10);
                    throw tetException;
                }
                ret.add(row);
            }

        }
        return ret;
    }


    public static ArrayList<ArrayList<String>> xlsToArrayList(String filePath, String sheetName)
        {
            ArrayList<ArrayList<String>> ret = new ArrayList();
            ArrayList row = null;
            try {
                FileInputStream fis = new FileInputStream(filePath);
                XSSFWorkbook workBook = new XSSFWorkbook(fis);
                XSSFSheet sheet = workBook.getSheet(sheetName);
                for (int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum(); ++i) {
                    XSSFRow currentRow = sheet.getRow(i);
                    row = new ArrayList();
                    for (int j = currentRow.getFirstCellNum(); j < currentRow.getLastCellNum(); ++j) {
                        currentRow.getCell(j).setCellType(CellType.STRING);
                        row.add(currentRow.getCell(j).getStringCellValue());
                    }
                    ret.add(row);
                }
            } catch (Exception var10) {
                var10.printStackTrace();
            }
            return ret;
        }


     public static void setColColorByResult(String filePath, String sheetName,int colNumber) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            XSSFWorkbook workBook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workBook.getSheet(sheetName);
            CellStyle redCellStyle = getRedTextCellStyle(workBook);
            CellStyle greenCellStyle = getGreenTextCellStyle(workBook);
            for (int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum(); ++i) {
                XSSFRow currentRow = sheet.getRow(i);
                XSSFCell currentCell = currentRow.getCell(colNumber);
                String currentCellText = "";
                try {
                    currentCellText = currentCell.getStringCellValue();
                } catch (NullPointerException var13) {
                    currentCellText = "";
                }
                if (currentCellText.equalsIgnoreCase("fail")) {
                    currentCell.setCellStyle(redCellStyle);
                }
                if (currentCellText.equalsIgnoreCase("pass")) {
                    currentCell.setCellStyle(greenCellStyle);
                }
            }
            FileOutputStream fos = new FileOutputStream(filePath);
            workBook.write(fos);
            fos.close();
        } catch (Exception var14) {
            var14.printStackTrace();
        }
   }



    public static void setValueinExcel(String filePath, String sheetName, List<String> outputValue) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            XSSFWorkbook workBook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workBook.getSheet((sheetName));
            CellStyle redCellStyle = getRedTextCellStyle(workBook);
            CellStyle greenCellStyle = getGreenTextCellStyle(workBook);
            Iterator var8 = outputValue.iterator();
            while (var8.hasNext()) {
                String rowOutput = (String) var8.next();
                String[] splitVal = rowOutput.split("#");
                int rowNumber = Integer.parseInt(splitVal[0].trim());
                int colNumber = Integer.parseInt(splitVal[0].trim());
                String value = "No Edits Fired";
                if (splitVal.length == 3) {
                    value = splitVal[2] != null ? splitVal[2].trim() : "";
                }
                XSSFRow currentRow = sheet.getRow(rowNumber);
                XSSFCell currentCell = currentRow.getCell(colNumber);
                currentCell.setCellValue(value);
                if (value.equalsIgnoreCase("Failed")) {
                    currentCell.setCellStyle(redCellStyle);
                }
                if (value.equalsIgnoreCase("Passed")) {
                    currentCell.setCellStyle(greenCellStyle);
                }
            }
            FileOutputStream fos = new FileOutputStream(filePath);
            workBook.write(fos);
            fos.close();
        } catch (Exception var16) {
            var16.printStackTrace();
        }
    }


    }


