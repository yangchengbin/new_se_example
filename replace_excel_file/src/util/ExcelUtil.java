package util;

import bean.Fruit;
import net.sf.jxls.transformer.XLSTransformer;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel生成类.
 */
public class ExcelUtil {
    /**
     * 根据模板生成Excel文件.
     *
     * @param templateFileName 模板文件.
     * @param fruits           模板中存放的数据.
     * @param resultFileName   生成的文件.
     */
    public void createExcel(String templateFileName, List<Fruit> fruits, String resultFileName) {
        XLSTransformer transformer = new XLSTransformer();
        URL url = this.getClass().getClassLoader().getResource("");
        String srcFilePath = url.getPath() + templateFileName;
        Map<String, Object> beanParams = new HashMap<String, Object>();
        beanParams.put("fruits", fruits);
        try {
            transformer.transformXLS(srcFilePath, beanParams, resultFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
