import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReplaceWordFile {

    public static void main(String[] args) {
        String destinationFile = "e:\\test.doc";
        Map<String, String> map = new HashMap<String, String>();

        map.put("name", "mark");
        map.put("sex", "男");
        map.put("idCard", "200010");
        map.put("year1", "2000");
        map.put("month1", "07");
        map.put("year2", "2008");
        map.put("month2", "07");
        map.put("gap", "2");
        map.put("major", "计算机科学与技术");
        map.put("type", "研究生");
        map.put("number", "2011020301");
        map.put("year", "2011");
        map.put("month", "01");
        map.put("date", "20220301");
        String templateFilePath = ReplaceWordFile.class.getClassLoader().getResource("template.doc").getPath();
        HWPFDocument document = new ReplaceWordFile().replaceDoc(templateFilePath, map);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            document.write(byteArrayOutputStream);
            OutputStream outs = new FileOutputStream(destinationFile);
            outs.write(byteArrayOutputStream.toByteArray());
            outs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取word模板并替换变量
     *
     * @param srcPath
     * @param map
     * @return
     */
    public HWPFDocument replaceDoc(String srcPath, Map<String, String> map) {
        try {
            FileInputStream fis = new FileInputStream(new File(srcPath));
            HWPFDocument doc = new HWPFDocument(fis);
            Range bodyRange = doc.getRange();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                bodyRange.replaceText("${" + entry.getKey() + "}", entry.getValue());
            }
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}