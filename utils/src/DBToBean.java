import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 直接从数据表映射到bean
 */
public class DBToBean {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "yuexin", "yuexin");
            genDaoFromDB("PEOPLE", con);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将从数据库中查出的记录根据字段名和字段类型转换成相对应的类
     *
     * @param clazz 要转换成的类
     * @param rs    数据集
     * @return 返回封装成的类集合
     * @throws IntrospectionException
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    public static List getBeanFromDB(Class clazz, ResultSet rs) throws IntrospectionException, SQLException, IllegalAccessException, InstantiationException, InvocationTargetException {
        if (rs == null) {
            throw new RuntimeException("数据集不能为空！");
        }
        List list = new ArrayList();
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        String columnName;
        while (rs.next()) {
            Object obj = clazz.newInstance();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnName = metaData.getColumnName(i);
                String jp, sp;
                for (PropertyDescriptor pd : pds) {
                    jp = pd.getPropertyType().getName();
                    sp = metaData.getColumnClassName(i);
                    if (pd.getName().equalsIgnoreCase(columnName)) {
                        if (jp.equals(sp) || sp.equals("java.sql.Timestamp")) {
                            pd.getWriteMethod().invoke(obj, rs.getObject(pd.getName()));
                        } else if (sp.equals("java.math.BigDecimal")) {
                            if (pd.getPropertyType().getName().equals("int")) {
                                pd.getWriteMethod().invoke(obj, rs.getInt(pd.getName()));
                            } else if (pd.getPropertyType().getName().equals("double")) {
                                pd.getWriteMethod().invoke(obj, rs.getDouble(pd.getName()));
                            } else if (pd.getPropertyType().getName().equals("long")) {
                                pd.getWriteMethod().invoke(obj, rs.getLong(pd.getName()));
                            }
                        }
                        break;
                    }
                }
            }
            list.add(obj);
        }
        return list;
    }


    public static void genDaoFromDB(String tableName, Connection conn) throws ClassNotFoundException, SQLException, IOException {
        PreparedStatement statement;
        ResultSet rs;
        String destinationFilePath = "commontest/src/model/";
        String enterStr = "\r\n";
        File destinationFile = new File(destinationFilePath);
        if (!destinationFile.exists()) {
            destinationFile.mkdirs();
        }
        FileWriter fw = new FileWriter(destinationFile.getAbsolutePath() + "/" + tableName.toUpperCase() + ".java");
        fw.write("package model;" + enterStr + "import java.util.Date;" + enterStr + enterStr);
        fw.write("public class " + tableName.toUpperCase() + " {" + enterStr + enterStr);

        String columnSql = "select t.COLUMN_NAME,t.DATA_TYPE,t.DATA_SCALE from user_tab_columns t where table_name='" + tableName.toUpperCase() + "'";
        statement = conn.prepareStatement(columnSql);
        rs = statement.executeQuery();
        while (rs.next()) {
            String columnName = rs.getString("COLUMN_NAME").toLowerCase();
            String columnClassName = rs.getString("DATA_TYPE");
            int dataScale = rs.getInt("DATA_SCALE");

            //生成字段
            if (columnClassName.equals("CHAR") || columnClassName.equals("VARCHAR2")) {
                fw.write("    private String " + columnName + ";" + enterStr);
            } else if (columnClassName.equals("NUMBER")) {
                if (dataScale == 0) {
                    fw.write("    private int " + columnName + ";" + enterStr);
                } else if (dataScale > 0) {
                    fw.write("    private double " + columnName + ";" + enterStr);
                }
            } else if (columnClassName.equals("DATE")) {
                fw.write("    private Date " + columnName + ";" + enterStr);
            }
        }

        fw.write(enterStr);
        rs = statement.executeQuery();
        while (rs.next()) {
            String columnName = rs.getString("COLUMN_NAME").toLowerCase();
            String columnClassName = rs.getString("DATA_TYPE");
            int dataScale = rs.getInt("DATA_SCALE");

            //生成get、set方法
            if (columnClassName.equals("CHAR") || columnClassName.equals("VARCHAR2")) {
                fw.write("    public void set" + firstToUpperCase(columnName) + "(String " + columnName + ") {" + enterStr);
                fw.write("        this." + columnName + " = " + columnName + ";" + enterStr + "    }" + enterStr);
                fw.write("    public String get" + firstToUpperCase(columnName) + "() {" + enterStr);
                fw.write("        return " + columnName + ";" + enterStr + "    }" + enterStr + enterStr);
            } else if (columnClassName.equals("NUMBER")) {
                if (dataScale == 0) {
                    fw.write("    public void set" + firstToUpperCase(columnName) + "(int " + columnName + ") {" + enterStr);
                    fw.write("        this." + columnName + " = " + columnName + ";" + enterStr + "    }" + enterStr);
                    fw.write("    public int get" + firstToUpperCase(columnName) + "() {" + enterStr);
                    fw.write("        return " + columnName + ";" + enterStr + "    }" + enterStr);
                } else if (dataScale > 0) {
                    fw.write("    public void set" + firstToUpperCase(columnName) + "(double " + columnName + ") {" + enterStr);
                    fw.write("        this." + columnName + " = " + columnName + ";" + enterStr + "    }" + enterStr + enterStr);
                    fw.write("    public double get" + firstToUpperCase(columnName) + "() {" + enterStr);
                    fw.write("        return " + columnName + ";" + enterStr + "    }" + enterStr + enterStr);
                }
            } else if (columnClassName.equals("DATE")) {
                fw.write("    public void set" + firstToUpperCase(columnName) + "(Date " + columnName + ") {" + enterStr);
                fw.write("        this." + columnName + " = " + columnName + ";" + enterStr + "    }" + enterStr + enterStr);
                fw.write("    public Date get" + firstToUpperCase(columnName) + "() {" + enterStr);
                fw.write("        return " + columnName + ";" + enterStr + "    }" + enterStr + enterStr);
            }
        }
        fw.write("}");
        fw.close();
    }

    public static String firstToUpperCase(String str) {
        String firstLetter = str.charAt(0) + "";
        str = firstLetter.toUpperCase() + str.substring(1);
        return str;
    }
}
