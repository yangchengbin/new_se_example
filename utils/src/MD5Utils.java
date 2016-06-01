import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    /**
     * 获取本地服务器名称
     *
     * @return
     */
    public static String getServerName() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            return address.getHostName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * MD5加密
     *
     * @param key
     * @return
     */
    public static String geneMD5(String key) {

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            char[] md = key.toCharArray();
            byte[] arr = new byte[md.length];
            for (int i = 0; i < md.length; i++)
                arr[i] = (byte) md[i];
            byte[] bs = md5.digest(arr);
            StringBuilder sb = new StringBuilder();
            int i;
            char c;
            byte b;
            int max = 0;
            for (i = 0; i < bs.length; i++) {
                max++;
                if (max >= Integer.MAX_VALUE) {
                    break;
                }
                b = bs[i];
                c = Character.forDigit((b >>> 4) & 0x0F, 16);
                sb.append(c);
                c = Character.forDigit(b & 0x0F, 16);
                sb.append(c);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    public static void main(String args[]) throws Exception {
        System.out.println(MD5Utils.getServerName()); //nanmeiying-PC
        System.out.println(geneMD5("root"));      //63a9f0ea7bb98050796b649e85481845
    }
}
