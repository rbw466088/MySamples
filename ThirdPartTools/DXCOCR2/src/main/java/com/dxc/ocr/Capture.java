/**
 * Created By Liu Yuhong - 2017年12月6日
 */
package com.dxc.ocr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**<pre>
 * OCR处理启动类
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年12月6日
 */
public class Capture {

    private static Logger logger = LoggerFactory.getLogger(Capture.class);

    /**
     * 默认配置文件完整路径
     */
    private static String CONFIG_PATH = "ocr.config";

    /**
     * 文件流编码
     */
    private static final String ENCODING_CODE = "UTF-8";

    /**<pre>
     * OCR处理启动类
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月6日
     * @param args
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        logger.info("OCR处理程序启动");
        logger.info("");

        if (args.length > 0) {
            CONFIG_PATH = args[0];
        }

        String[] configs = loadConfig(CONFIG_PATH);
        if (configs == null) {
            return;
        }

        // OCR SDK的ddl文件路径
        String ddlPath = configs[0];
        // OCR SDK的序列号
        String serialNumber = configs[1];
        // OCR SDK的定义配置文件路径
        String fcdot = configs[2];
        // 发票扫描件文件夹路径
        String imagePath = configs[3];
        // 结果文件夹路径
        String rootResultPath = configs[4];
        // 供应商结果文件名称
        String resultCompanyFile = configs[5];
        // 发票汇总结果文件名称
        String resultSummaryFile = configs[6];
        // 发票结果明细文件名称
        String resultAllDetailFile = configs[7];

        try {
            logger.info("加载OCR引擎...");
            logger.info("");

            logger.info("查找扫描件");
            File[] images = readImageFiles(imagePath);
            logger.info("找到【{}】个扫描件", images.length);
            logger.info("");

            if (images.length == 0) {
                logger.info("没有需要处理的文件，程序结束");
                return;
            }

            String currentMonthFolderPath = getCurrentMonthFolderPath(rootResultPath);
            createDir(currentMonthFolderPath);

            for (int i = 0; i < images.length; i++) {
                // 将遍历到的扫描件复制到汇总结果文件夹
                StringBuffer valueLine = new StringBuffer();
                File image = images[i];
                File newImage = new File(currentMonthFolderPath + image.getName());
                deleteFile(newImage);
//                copyFileUsingStream(image, newImage);
                deleteFile(image);
            }
            logger.info("OCR处理完成.");
        } catch (Exception ex) {
            logger.info("处理报错，错误信息：{}", ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * <pre>
     * 加载配置文件，配置信息包含如下5部分：
     * 1，SDK的ddl文件夹路径（SDK安装路径）
     * 2，SDK的序列号
     * 3，ocr文档定义文件的完整路径
     * 4，发票扫描件的文件夹路径
     * 5，处理结果的文件夹路径
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月6日
     * @param configPath
     * @return
     */
    private static String[] loadConfig(String configPath) {
        logger.info("加载配置文件，读取如下配置信息：");
        logger.info("1，ddlPath = SDK的ddl文件夹路径（SDK安装路径）");
        logger.info("2，serialNumber = SDK的序列号");
        logger.info("3，fcdot = ocr文档定义文件的完整路径");
        logger.info("4，imagePath = 发票扫描件的文件夹路径");
        logger.info("5，resultPath = 处理结果文件夹路径");
        logger.info("6，resultCompanyFile = 供应商发票信息的文件名");
        logger.info("7，resultSummaryFile = 发票信息汇总的文件名");
        logger.info("8，resultAllDetailFile = 发票处理结果的文件名");
        logger.info("");

        String[] configArr = new String[] { "", "", "", "", "", "", "", "" };
        FileInputStream fis = null;
        BufferedReader br = null;

        try {
            fis = new FileInputStream(configPath);
            br = new BufferedReader(new InputStreamReader(fis, ENCODING_CODE));

            String line = br.readLine();
            List<String> configs = new ArrayList<String>();
            while (line != null && line.trim().length() > 0) {
                configs.add(line);
                line = br.readLine();
            }

            if (configs.size() == 8) {
                for (String config : configs) {
                    if (config.contains("ddlPath")) {
                        configArr[0] = config.split("ddlPath=")[1];
                    }
                    if (config.contains("serialNumber")) {
                        configArr[1] = config.split("serialNumber=")[1];
                    }
                    if (config.contains("fcdot")) {
                        configArr[2] = config.split("fcdot=")[1];
                    }
                    if (config.contains("imagePath")) {
                        configArr[3] = config.split("imagePath=")[1];
                    }
                    if (config.contains("resultPath")) {
                        configArr[4] = config.split("resultPath=")[1];
                    }
                    if (config.contains("resultCompanyFile")) {
                        configArr[5] = config.split("resultCompanyFile=")[1];
                    }
                    if (config.contains("resultSummaryFile")) {
                        configArr[6] = config.split("resultSummaryFile=")[1];
                    }
                    if (config.contains("resultAllDetailFile")) {
                        configArr[7] = config.split("resultAllDetailFile=")[1];
                    }
                }

                logger.info("配置信息：");
                logger.info("ddlPath: \t\t{}", configArr[0]);
                logger.info("serialNumber: \t\t{}", configArr[1]);
                logger.info("fcdot: \t\t\t{}", configArr[2]);
                logger.info("imagePath: \t\t{}", configArr[3]);
                logger.info("resultPath: \t\t{}", configArr[4]);
                logger.info("resultCompanyFile: \t{}", configArr[5]);
                logger.info("resultSummaryFile: \t{}", configArr[6]);
                logger.info("resultAllDetailFile: \t{}", configArr[7]);
            } else {
                logger.info("加载配置文件错误，处理终止");
                logger.info("错误信息：配置信息不完整");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("加载配置文件错误，处理终止");
            logger.info("错误信息：{}", e.getMessage());
            return null;
        } finally {
            try {
                br.close();
                fis.close();
            } catch (IOException e) {
                // do nothing
            }
        }
        return configArr;
    }

    /**
     * <pre>
     * 读取扫描件文件夹里的全部扫描件
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月6日
     * @param imageDirPath
     * @return
     */
    private static File[] readImageFiles(String imageDirPath) {
        File imageDir = new File(imageDirPath);
        if (imageDir.exists()) {
            return imageDir.listFiles();
        } else {
            return new File[] {};
        }
    }

    /**
     * <pre>
     * 取得本月的文件夹路径: rootPath/yyyy/MM/
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月6日
     * @param rootResultPath
     * @return
     */
    private static String getCurrentMonthFolderPath(String rootResultPath) {
        StringBuffer currentMonthPath = new StringBuffer();
        return currentMonthPath.append(rootResultPath).append(File.separator).append(getCurrentDateByFormat("yyyy")).append(File.separator)
                .append(getCurrentDateByFormat("MM")).append(File.separator).toString();
    }

    /**
     * <pre>
     * 取得指定格式的当前日期
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月6日
     * @return
     */
    private static String getCurrentDateByFormat(String format) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(cal.getTime());
    }

    /**
     * <pre>
     * 删除文件
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月17日
     * @param file
     */
    private static void deleteFile(File file) {
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * <pre>
     * 复制文件
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月6日
     * @param source
     * @param dest
     * @throws IOException
     */
    @SuppressWarnings("unused")
    private static void copyFileUsingStream(File source, File dest) {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * <pre>
     * 创建文件夹
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月6日
     * @param dirPath
     */
    private static void createDir(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

}
