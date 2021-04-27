/**
 * Created By Liu Yuhong - 2017年11月29日
 */
package com.dxc.ws;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

import com.dxc.ws.util.HttpURLConnectionUtil;
import com.dxc.ws.util.XMLUtil;

/**<pre>
 * 调用映像系统接口
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年11月29日
 */
public class CallBusiDataTrans {

    /**<pre>
     * 调用映像系统接口
     * 1.读取web service请求配置xml，并检查和删除临时文件temp.xml
     * 2.调用web service接口并获取返回结果
     * 3.将返回结果保存为temp.xml文件
     * 4.文件处理
     *      1) 删除前一次的旧结果文件【result_old.xml】，并将前一次的结果文件【result.xml】，重命名为旧结果文件【result_old.xml】
     *      2) 删除前一次的结果文件【result.xml】，并将本次的结果临时文件【temp.xml】，重命名为结果文件【result.xml】
     *      3) 删除临时文件【temp.xml】
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年11月29日
     * @param args
     */
    public static void main(String[] args) {

        boolean isLoopRun = false;
        if (args.length > 0 && args[0].trim().length() > 0 && "true".equalsIgnoreCase(args[0])) {
            isLoopRun = true;
        } else {
            isLoopRun = false;
        }
        try {
            while (isLoopRun) {

                System.out.println("");
                System.out.println(" -> CallBusiDataTrans, startTime: " + Calendar.getInstance().getTime());
                System.out.println("");

                StringBuffer request = new StringBuffer();
                File resultOld = null;
                File result = null;
                File resultTemp = null;
                FileInputStream fis = null;
                InputStreamReader isr = null;
                BufferedReader br = null;

                // 1，读取web service请求配置xml，并检查和删除临时文件temp.xml
                try {
                    fis = new FileInputStream("request.xml");
                    isr = new InputStreamReader(fis);
                    br = new BufferedReader(isr);
                    String line = br.readLine();
                    if (line == null || line.trim().length() == 0) {
                        System.out.println("读取传参文件错误，程序退出");
                        return;
                    }
                    while (line != null) {
                        request.append(line.trim());
                        line = br.readLine();
                    }
                    System.out.println("接口调用参数为：" + request.toString());

                    resultTemp = new File("temp.xml");
                    deleteFile(resultTemp);
                } catch (Exception e) {
                    deleteFile(new File("result.xml"));
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    System.out.println("读取传参文件错误，程序退出");
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    return;
                } finally {
                    try {
                        br.close();
                        isr.close();
                        fis.close();
                    } catch (Exception ex) {
                        // do nothing
                    }
                }

                // 2，调用web service接口并获取返回结果
                System.out.println("");
                System.out.println("开始接口调用...");
                System.out.println("");
                String wsUrl = "http://127.0.0.1:7001/test";
                if (args != null && args.length == 1) {
                    wsUrl = args[0];
                }

                try {
                    // 3，将返回结果保存为temp.xml文件
                    String s = HttpURLConnectionUtil.postRequest(wsUrl, request.toString());
                    s = formatXML(s);
                    XMLUtil.writeStringToXmlFile(s, "temp.xml", "UTF-8");

                    // 4，文件处理
                    // 1) 删除前一次的旧结果文件（result_old.xml），并将前一次的结果文件（result.xml），重命名为旧结果文件（result_old.xml）
                    // 2) 删除前一次的结果文件（result.xml），并将本次的结果临时文件（temp.xml），重命名为结果文件（result.xml）
                    // 3) 删除临时文件（temp.xml）
                    resultOld = new File("result_old.xml");
                    resultTemp = new File("temp.xml");
                    result = new File("result.xml");
                    deleteFile(resultOld);
                    renameFile(result, resultOld);
                    renameFile(resultTemp, result);
                } catch (Exception e) {
                    deleteFile(result);
                    e.printStackTrace();
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    System.out.println("接口调用错误，请联系管理员检查");
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    return;
                }
                try {
                    Thread.sleep(60000l);
                } catch (InterruptedException e) {
                    deleteFile(result);
                    return;
                }
            }
        } catch (Exception ex) {
            deleteFile(new File("result.xml"));
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("接口调用错误，请联系管理员检查");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }

    /**
     * <pre>
     * 删除存在的文件
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月5日
     * @param file
     */
    private static void deleteFile(File file) {
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * <pre>
     * 重命名文件：
     * 1，检查源文件，源文件存在才能执行操作
     * 2，检查目标文件，目标文件不存在才能执行操作
     * 3，将源文件重命名为目标文件
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月5日
     * @param source
     * @param target
     */
    private static void renameFile(File source, File target) {
        if (source.exists() && !target.exists()) {
            source.renameTo(target);
            source.delete();
        }
    }

    /**
     * <pre>
     * 对XML文件进行格式化
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月7日
     * @param xmlStr
     * @return
     */
    private static String formatXML(String xmlString) {
        StringBuffer formatXmlString = new StringBuffer();
        xmlString = xmlString.replace("&lt;", "<");
        xmlString = xmlString.replace("&gt;", ">");
        String[] tagLines = xmlString.split("><");
        for (int i = 0; i < tagLines.length; i++) {
            String line = tagLines[i];
            if (i == (tagLines.length - 1)) {
                formatXmlString.append(line);
            } else {
                formatXmlString.append(line).append(">\n<");
            }
        }

        System.out.println(formatXmlString.toString());
        return formatXmlString.toString();
    }
}
