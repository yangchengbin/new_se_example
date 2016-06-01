import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZipStream {
    public static void main(String[] args) throws IOException {
        System.out.println(new String(zip("root")));
    }

    public static byte[] zip(String str) throws IOException {
        if (str != null) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            GZIPOutputStream os = new GZIPOutputStream(bos);
            os.write(str.getBytes());
            os.close();
            return bos.toByteArray();
        }
        return null;
    }

    public static String unZip(byte[] zipBuf) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(zipBuf);
        GZIPInputStream gin = new GZIPInputStream(bis);
        byte[] buff = new byte[1024];
        int len;
        StringBuffer sb = new StringBuffer();
        while ((len = gin.read(buff)) > 0) {
            sb.append(new String(buff, 0, len));
        }
        return sb.toString();
    }
}
