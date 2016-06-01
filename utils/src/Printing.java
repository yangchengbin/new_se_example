import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 打印机打印pdf文件
 */
public class Printing {

    public static void main(String[] args) {
        try {
            String filename = "f:\\test.pdf";
            PrintRequestAttributeSet requestAttributeSet = new HashPrintRequestAttributeSet();
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, requestAttributeSet);// 用户可选用的PrintService实例数组。
            PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService(); // 默认的PrintService
            PrintService service = ServiceUI.printDialog(null, 200, 200, printService, defaultService, flavor, requestAttributeSet);
            if (service != null) {
                DocPrintJob job = service.createPrintJob(); // 创建打印任务
                FileInputStream fis;
                fis = new FileInputStream(filename);
                DocAttributeSet das = new HashDocAttributeSet();
                /** 定义要打印的文档*/
                Doc doc = new SimpleDoc(fis, flavor, das);
                job.print(doc, requestAttributeSet);
                Thread.sleep(10000);
                fis.close();
            }
            System.exit(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (PrintException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}