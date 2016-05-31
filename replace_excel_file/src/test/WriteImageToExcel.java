package test;

import jxl.Workbook;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;

public class WriteImageToExcel {
    public static void main(String[] args) throws Exception {
        String imageXlsFilePath = WriteImageToExcel.class.getClassLoader().getResource("imageExcel.xls").getPath();
        WritableWorkbook wwb = Workbook.createWorkbook(new File(imageXlsFilePath));
        WritableSheet ws = wwb.createSheet("Test Sheet 1", 0);
        String imageFilePath = WriteImageToExcel.class.getClassLoader().getResource("a.png").getPath();
        File file = new File(imageFilePath);
        WritableImage image = new WritableImage(1, 4, 6, 18, file);
        ws.addImage(image);
        wwb.write();
        wwb.close();
    }
}