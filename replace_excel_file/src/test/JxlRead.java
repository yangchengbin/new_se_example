package test;

import jxl.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

public class JxlRead {
    private static void readExcel(String filePath) throws Exception {
        InputStream is = null;
        Workbook workbook = null;
        try {
            is = new FileInputStream(filePath);
            workbook = Workbook.getWorkbook(is);
            Sheet sheet = workbook.getSheet(0);
            int column = sheet.getColumns();
            int row = sheet.getRows();
            System.out.println("共有" + row + "行，" + column + "列数据");
            Cell cellA1 = sheet.getCell(0, 0);
            System.out.println("A1 type:" + cellA1.getType());
            if (cellA1.getType().equals(CellType.LABEL)) {
                System.out.println("A1 content:" + ((LabelCell) cellA1).getString());
            }
            Cell cellB1 = sheet.getCell(1, 0);
            System.out.println("B1 type:" + cellB1.getType());
            if (cellB1.getType().equals(CellType.NUMBER)) {
                NumberCell numberCell = (NumberCell) cellB1;
                double doubleVal = numberCell.getValue();
                System.out.println("B1 value:" + doubleVal);
            }
            Cell cellC1 = sheet.getCell(2, 0);
            System.out.println("C1 type:" + cellC1.getType());
            if (cellC1.getType().equals(CellType.DATE)) {
                DateCell dateCell = (DateCell) cellC1;
                Date date = dateCell.getDate();
                System.out.println("C1 date:" + date);
            }
            workbook.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            if (is != null) {
                is.close();
            }
            if (workbook != null) {
                workbook.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String path = WriteImageToExcel.class.getClassLoader().getResource("jxl.xls").getPath();
        readExcel(path);
    }
}