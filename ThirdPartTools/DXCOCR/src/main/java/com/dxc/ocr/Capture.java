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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.abbyy.FCEngine.Engine;
import com.abbyy.FCEngine.IDocument;
import com.abbyy.FCEngine.IEngine;
import com.abbyy.FCEngine.IField;
import com.abbyy.FCEngine.IFieldInstances;
import com.abbyy.FCEngine.IFields;
import com.abbyy.FCEngine.IFlexiCaptureProcessor;

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
     * 识别错误标识
     */
    private static String OCR_ERROR = "error";

    /**
     * 结果写入模式，true=追加记录，false=删除新建
     */
    private static boolean IS_APPEND_TO_FILE = false;

    /**
     * 文件流编码
     */
    private static final String ENCODING_CODE = "UTF-8";

    /**
     * 信息类型：文本
     */
    @SuppressWarnings("unused")
    private static final String INFO_TYPE_TEXT = "text";

    /**
     * 信息类型：数字
     */
    private static final String INFO_TYPE_NUMBER = "number";

    /**
     * 发票OCR处理状态，0=OCR完成
     */
    private static final String STATUS_OCR_COMPLETE = "0";

    /**
     * 发票OCR处理状态，0=OCR完成
     */
    private static final String STATUS_OCR_COMPLETE_DESC = "发票识别完成";

    /**
     * 发票OCR处理状态，-1=发票号码错误，未读取到或不一致
     */
    private static final String STATUS_OCR_WRONG_INVOICE_NO = "-1";

    /**
     * 发票OCR处理状态，-1=发票号码错误，未读取到或不一致
     */
    private static final String STATUS_OCR_WRONG_INVOICE_DESC = "发票识别错误";

    /**
     * 发票OCR处理状态，-2=发票信息错误，金额非数字
     */
    private static final String STATUS_OCR_WRONG_INVOICE_INFO = "-2";

    /**
     * 发票OCR处理状态，-2=发票信息错误，金额非数字
     */
    private static final String STATUS_OCR_WRONG_INVOICE_INFO_DESC = "票面金额识别错误";

    /**
     * 发票详情表列名
     */
    private static final String[] INVOICE_INFO_DETAIL_COLUMN_NAMES = new String[] { "发票号1", "发票号2", "开票日期", "开票公司名称", "电量", "单价（不含税）", "不含税电费金额", "税金",
            "含税电费总额", "不含税电费总额", "税金总额", "文件名", "文件创建时间", "状态", "状态描述" };

    /**
     * 发票信息类型定义，类型为两类：text=文本，number=数字，按OCR文档定义里的读取和存放顺序排列
     */
    private static final String[] INVOICE_INFO_DETAIL_VALUE_TYPES = new String[] { "number", "number", "text", "text", "text", "text", "text", "text", "number",
            "number", "number", "text", "text", "text", "text" };

    /**
     * 发票汇总表列名
     */
    private static final String[] INVOICE_SUMMARY_COLUMN_NAMES = new String[] { "单位", "含税电费总额", "不含税电费金额", "税金总额", "发票张数", "状态", "状态描述" };

    /**<pre>
     * OCR处理启动类
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月6日
     * @param args
     */
    public static void main(String[] args) {
        logger.info("OCR处理程序启动");
        logger.info("");

        if (args.length > 0 && args.length == 2) {
            CONFIG_PATH = args[0];
            if ("true".equalsIgnoreCase(args[1])) {
                IS_APPEND_TO_FILE = true;
            } else {
                IS_APPEND_TO_FILE = false;
            }
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
            IEngine engine = null;
            try {
                engine = Engine.Load(ddlPath, serialNumber, "");
            } catch (Exception e) {
                e.printStackTrace();
            }

            logger.info("查找扫描件");
            File[] images = readImageFiles(imagePath);
            logger.info("找到【{}】个扫描件", images.length);
            logger.info("");

            if (images.length == 0) {
                logger.info("没有需要处理的文件，程序结束");
                return;
            }

            Map<String, List<List<String>>> fullInvoiceInfo = new HashMap<String, List<List<String>>>();
            List<String> invoiceNos = new ArrayList<String>();
            Map<String, List<List<String>>> fullCompanyInvoiceInfo = new HashMap<String, List<List<String>>>();
            Map<String, List<List<String>>> resultAllDetailInfo = new HashMap<String, List<List<String>>>();
            List<String> invalidCompany = new ArrayList<String>();

            // 预处理
            String currentMonthFolderPath = getCurrentMonthFolderPath(rootResultPath);
            if (!IS_APPEND_TO_FILE) {
                deleteAllResultFilesAndDirs(currentMonthFolderPath);
            }
            createDir(currentMonthFolderPath);

            List<String> columnNames = new ArrayList<String>();
            StringBuffer line = new StringBuffer();
            // 取得发票详情表列名
            for (int j = 0; j < INVOICE_INFO_DETAIL_COLUMN_NAMES.length; j++) {
                columnNames.add(INVOICE_INFO_DETAIL_COLUMN_NAMES[j]);
                line.append(INVOICE_INFO_DETAIL_COLUMN_NAMES[j]).append(",");
            }
            logger.info("读取到的信息：");
            logger.info(line.toString());

            for (int i = 0; i < images.length; i++) {
                // 将遍历到的扫描件复制到汇总结果文件夹
                StringBuffer valueLine = new StringBuffer();
                File image = images[i];
                File newImage = new File(currentMonthFolderPath + image.getName());
                deleteFile(newImage);
                //                copyFileUsingStream(image, newImage);
                image.renameTo(newImage);

                // 读取发票信息
                logger.info("----------------------------------------------------------------------");
                logger.info("识别扫描件：{}", image.getName());
                logger.info("读取发票文件信息");
                String fileCreateDate = getFileLastModifyDateString(image);
                try {
                    logger.info("加载OCR模板文档定义");
                    IFlexiCaptureProcessor processor = engine.CreateFlexiCaptureProcessor();
                    processor.AddDocumentDefinitionFile(fcdot);
                    processor.AddImageFile(image.getAbsolutePath());

                    IDocument document = processor.RecognizeNextDocument();
                    IFields fields = document.getSections().Item(0).getChildren();

                    // 取得属性值
                    logger.info("获取已识别的文本值");
                    List<String> values = getFieldValues(fields);
                    String companyName = values.get(3).replace(",", "");

                    logger.info("按供应商进行分组处理");
                    // 将供应商发票扫描件复制到供应商文件夹下
                    String companyFolderPath = getCompanyFolderPath(rootResultPath, companyName);
                    createDir(companyFolderPath);
                    newImage = new File(companyFolderPath + image.getName());
                    deleteFile(newImage);
                    //                copyFileUsingStream(image, newImage);
                    image.renameTo(newImage);

                    // 校验发票，如某供应商发票校验失败，则该供应商不进入汇总表，且该张发票在供应商信息表里的状态为-1
                    values.add(image.getName());
                    values.add(fileCreateDate);
                    values.add(STATUS_OCR_COMPLETE);
                    values.add(STATUS_OCR_COMPLETE_DESC);

                    // 对发票信息里的金额数字进行校验，确保为数字
                    logger.info("发票金额信息校验");
                    if (validateNumber(values.get(1))) {
                        values = validateValues(values);
                        if (!STATUS_OCR_COMPLETE.equals(values.get(values.size() - 2))) {
                            invalidCompany.add(companyName);
                        }
                        for (int j = 0; j < values.size(); j++) {
                            valueLine.append(values.get(j)).append(",");
                        }
                        logger.info(valueLine.toString());

                        if (!fullInvoiceInfo.containsKey(companyName)) {
                            fullInvoiceInfo.put(companyName, new ArrayList<List<String>>());
                        }
                        // 处理重复的发票号
                        if (!invoiceNos.contains(values.get(1))) {
                            invoiceNos.add(values.get(1));
                            fullInvoiceInfo.get(companyName).add(values);
                        }
                    } else {
                        if (STATUS_OCR_COMPLETE.equals(values.get(values.size() - 2))) {
                            values.set(values.size() - 2, STATUS_OCR_WRONG_INVOICE_NO);
                            values.set(values.size() - 1, STATUS_OCR_WRONG_INVOICE_DESC);
                        }
                        valueLine.append("\t");
                        for (int j = 0; j < values.size(); j++) {
                            valueLine.append(values.get(j)).append(",");
                        }
                        invalidCompany.add(companyName);
                        logger.info(valueLine.toString());
                    }

                    // 生成供应商发票信息
                    if (!fullCompanyInvoiceInfo.containsKey(companyName)) {
                        fullCompanyInvoiceInfo.put(companyName, new ArrayList<List<String>>());
                        resultAllDetailInfo.put(companyName, new ArrayList<List<String>>());
                    }
                    fullCompanyInvoiceInfo.get(companyName).add(values);
                    resultAllDetailInfo.get(companyName).add(values);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    logger.info(image.getName() + "识别错误");
                    String[] errorArray = new String[] { "", "", "", "", "", "", "", "", "", "", "", image.getName(), fileCreateDate,
                            STATUS_OCR_WRONG_INVOICE_NO, STATUS_OCR_WRONG_INVOICE_DESC };
                    List<String> values = new ArrayList<String>();
                    for (String value : errorArray) {
                        values.add(value);
                    }
                    if (!resultAllDetailInfo.containsKey(OCR_ERROR)) {
                        resultAllDetailInfo.put(OCR_ERROR, new ArrayList<List<String>>());
                    }
                    resultAllDetailInfo.get(OCR_ERROR).add(values);
                }
            }

            // 将供应商发票信息写入供应商的文件
            writeCompanyResultFile(rootResultPath, resultCompanyFile, columnNames, fullCompanyInvoiceInfo);

            // 从汇总供应商信息里移除无效的供应商
            for (String invalidCompanyName : invalidCompany) {
                fullInvoiceInfo.remove(invalidCompanyName);
            }

            logger.info("");
            logger.info("保存到文件");
            // 将供应商发票信息写入供应商的文件
            writeResultAllDetailFile(rootResultPath, resultAllDetailFile, columnNames, resultAllDetailInfo);
            // 将汇总信息写入汇总发票信息表
            List<String> summaryColumnNames = Arrays.asList(INVOICE_SUMMARY_COLUMN_NAMES);
            writeSummaryResultFile(rootResultPath, resultSummaryFile, summaryColumnNames, fullInvoiceInfo);

            logger.info("");
            logger.info("OCR处理完成.");
        } catch (Exception ex) {
            logger.info("处理报错，错误信息：{}", ex.getMessage());
            ex.printStackTrace();
        } finally {
            Engine.Unload();
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
     * 删除厂商目录下全部文件
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月17日
     * @param currentMonthFolderPath
     */
    private static void deleteAllResultFilesAndDirs(String currentMonthFolderPath) {
        File dir = new File(currentMonthFolderPath);
        if (dir.exists()) {
            File[] rootFiles = dir.listFiles();
            for (File file : rootFiles) {
                if (file.isDirectory()) {
                    File[] corpFiles = file.listFiles();
                    for (File corpFile : corpFiles) {
                        corpFile.delete();
                    }
                } else {
                    file.delete();
                }
            }

            rootFiles = dir.listFiles();
            for (File file : rootFiles) {
                file.delete();
            }
        }
    }

    /**
     * <pre>
     * 取得属性名
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月6日
     * @param fields
     * @return
     */
    @SuppressWarnings("unused")
    private static List<String> getFieldNames(IFields fields) {
        List<String> columnNames = new ArrayList<String>();
        for (int j = 0; j < fields.getCount(); j++) {
            IField field = fields.getElement(j);
            if (j == 0) {
                String name = field.getName().replace("\n", "");
                columnNames.add(name);
            } else {
                String name = field.getName().replace("\n", "");
                columnNames.add(name);
            }
        }
        return columnNames;
    }

    /**
     * <pre>
     * 取得发票属性值
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月6日
     * @param fields
     * @return
     */
    private static List<String> getFieldValues(IFields fields) {
        List<String> invoiceInfos = new ArrayList<String>();

        for (int i = 0; i < fields.getCount(); i++) {
            IField field = fields.getElement(i);
            String value = "";
            if (i == 0) {
                // 发票号优化处理
                value = fields.getElement(1).getValue().getAsString();
                value = value.replace("\n", "").replace(",", ".").replace("*", ".");
                if (!value.contains(".") && value.trim().contains(" ")) {
                    value = value.replace(" ", ".");
                }
                value = value.replace(" ", "").replace("..", ".").replace("..", ".");
            } else {
                // 金额数值优化处理
                value = field.getValue().getAsString();
                // 数值处理，去除无效的回车换行符，并将错误时别的逗号和星号转换回小数点
                value = value.replace("\n", "").replace(",", ".").replace("，", ".").replace("*", ".");
                // 数值处理，当小数点被识别为空格，将空格转换回小数点
                if (!value.contains(".") && value.trim().contains(" ")) {
                    value = value.replace(" ", ".");
                }
                if (value.contains("-") && value.contains(".")) {
                    value = value.replace("-", "");
                }
                if (value.contains("-") && !value.contains(".")) {
                    value = value.replace("-", ".");
                }
                value = value.replace(" ", "").replace("..", ".").replace("..", ".");
                // 处理起始两个字符不是数字的金额数字
                if (value.length() > 1 && !validateNumber(value.substring(0, 1)) && !validateNumber(value.substring(1, 2))
                        && validateNumber(value.substring(2, value.length()))) {
                    value = value.substring(1, value.length());
                }
                // 处理首个字符不是数字的金额数字
                if (value.length() > 1 && !validateNumber(value.substring(0, 1)) && validateNumber(value.substring(1, value.length()))) {
                    value = value.substring(1, value.length());
                }
                value = value.replace(",", "");
            }
            invoiceInfos.add(value.trim());
        }

        return invoiceInfos;
    }

    /**
     * <pre>
     * 发票信息金额数值检查
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月7日
     * @param values
     * @return
     */
    private static List<String> validateValues(List<String> values) {
        boolean isValid = true;
        for (int i = 0; i < INVOICE_INFO_DETAIL_VALUE_TYPES.length; i++) {
            String type = INVOICE_INFO_DETAIL_VALUE_TYPES[i];
            if (INFO_TYPE_NUMBER.equals(type)) {
                if (values.get(i).trim().length() > 0 && !validateNumber(values.get(i))) {
                    isValid = false;
                }
            }
        }

        if (!isValid) {
            values.set(values.size() - 2, STATUS_OCR_WRONG_INVOICE_INFO);
            values.set(values.size() - 1, STATUS_OCR_WRONG_INVOICE_INFO_DESC);
        }

        return values;
    }

    /**
     * <pre>
     * 取得表格里每列的值
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月6日
     * @param tableField
     * @return
     */
    @SuppressWarnings("unused")
    private static List<String> getTableColumnValues(IField tableField) {
        List<String> columnValues = new ArrayList<String>();
        IFieldInstances colFields = tableField.getInstances();
        System.out.println(tableField.getBlocks().getElement(0).getField().getChildren().getCount());
        for (int k = 0; k < colFields.getCount(); k++) {
            IField colField = colFields.getElement(k);
            System.out.println(colField.getName() + " : " + colField.getValue());
        }

        return columnValues;
    }

    /**
     * <pre>
     * 取得文件最后修改时间
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月6日
     * @param file
     * @return
     */
    private static String getFileLastModifyDateString(File file) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(file.lastModified());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(cal.getTime());
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
     * 写入汇总结果文件
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月6日
     * @param rootResultPath
     * @param summaryFileName
     * @param fullInvoiceInfo
     * @param isAppendToFile
     */
    private static void writeSummaryResultFile(String rootResultPath, String summaryFileName, List<String> columnNames,
            Map<String, List<List<String>>> fullInvoiceInfo) {
        List<List<String>> valueList = getSummaryInvoiceInfo(fullInvoiceInfo);
        try {
            String resultPath = getCurrentMonthFolderPath(rootResultPath);
            String resultFilePath = resultPath + getCurrentDateByFormat("yyyyMM") + "_" + summaryFileName;
            createDir(resultPath);

            File file = new File(resultFilePath);
            boolean isExists = file.exists();
            if (!IS_APPEND_TO_FILE) {
                isExists = false;
                deleteFile(file);
            }

            writeToExcel(resultFilePath, columnNames, valueList, isExists);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <pre>
     * 写入各公司结果文件
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月6日
     * @param rootResultPath
     * @param companyName
     * @param companyFileName
     * @param 
     */
    private static void writeCompanyResultFile(String rootResultPath, String companyFileName, List<String> columnNames,
            Map<String, List<List<String>>> fullCompanyInfo) {

        Iterator<String> it = fullCompanyInfo.keySet().iterator();
        while (it.hasNext()) {
            try {
                String companyName = it.next();
                List<List<String>> companyInvoiceLines = fullCompanyInfo.get(companyName);
                String companyFolderPath = getCompanyFolderPath(rootResultPath, companyName);
                StringBuffer resultFilePath = new StringBuffer();
                resultFilePath.append(companyFolderPath).append(getCurrentDateByFormat("yyyyMM")).append("_").append(companyName).append(companyFileName);
                createDir(companyFolderPath);

                File file = new File(resultFilePath.toString());
                boolean isExists = file.exists();
                if (!IS_APPEND_TO_FILE) {
                    isExists = false;
                    deleteFile(file);
                }

                writeToExcel(resultFilePath.toString(), columnNames, companyInvoiceLines, isExists);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * <pre>
     * 写入处理结果明细文件
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月6日
     * @param rootResultPath
     * @param resultAllDetailFileName
     * @param fullResultDetailInfo
     * @param 
     */
    private static void writeResultAllDetailFile(String rootResultPath, String resultAllDetailFileName, List<String> columnNames,
            Map<String, List<List<String>>> fullResultDetailInfo) {

        Iterator<String> it = fullResultDetailInfo.keySet().iterator();
        List<List<String>> valueList = new ArrayList<List<String>>();
        while (it.hasNext()) {
            String companyName = it.next();
            valueList.addAll(fullResultDetailInfo.get(companyName));
        }
        try {
            String resultAllDetailFolderPath = getCurrentMonthFolderPath(rootResultPath);
            StringBuffer resultFilePath = new StringBuffer();
            resultFilePath.append(resultAllDetailFolderPath).append(getCurrentDateByFormat("yyyyMM")).append("_").append(resultAllDetailFileName);
            createDir(resultAllDetailFolderPath);
            File file = new File(resultFilePath.toString());
            boolean isExists = file.exists();
            if (!IS_APPEND_TO_FILE) {
                isExists = false;
                deleteFile(file);
            }

            writeToExcel(resultFilePath.toString(), columnNames, valueList, isExists);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <pre>
     * 检查记录在汇总表是否存在
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月6日
     * @return
     */
    @SuppressWarnings("unused")
    private static boolean isSummaryRecordExists(String filePath) {

        return true;
    }

    /**
     * <pre>
     * 检查发票信息在供应商详细表是否存在
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月6日
     * @return
     */
    @SuppressWarnings("unused")
    private static boolean isDetailRecordExists(String filePath) {

        return true;
    }

    /**
     * <pre>
     * 将按供应商分组的发票信息处理为汇总信息
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月6日
     * @param fullInvoiceInfoMap
     * @return
     */
    @SuppressWarnings("unused")
    private static Map<String, List<String>> getSummaryResult(Map<String, List<String>> fullInvoiceInfoMap) {
        Map<String, List<String>> summaryResult = new HashMap<String, List<String>>();
        Iterator<String> it = fullInvoiceInfoMap.keySet().iterator();
        while (it.hasNext()) {
            String companyName = it.next();
            if (!summaryResult.containsKey(companyName)) {
                summaryResult.put(companyName, new ArrayList<String>());
            }
            List<String> fullInvoiceInfoList = fullInvoiceInfoMap.get(companyName);
            List<String> invoiceInfoList = summaryResult.get(companyName);

        }

        return summaryResult;
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
     * 取得本月的提供商文件夹路径
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月6日
     * @param rootResultPath
     * @param companyName
     * @return
     */
    private static String getCompanyFolderPath(String rootResultPath, String companyName) {
        StringBuffer comPath = new StringBuffer();
        return comPath.append(getCurrentMonthFolderPath(rootResultPath)).append(companyName).append(File.separator).toString();
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

    /**
     * <pre>
     * 验证是否是数字
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月6日
     * @param content
     * @return
     */
    private static boolean validateNumber(String content) {
        String pattern = "^\\d+(\\.\\d+)?";
        return Pattern.matches(pattern, content);
    }

    /**
     * <pre>
     * 取得汇总发票信息
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月17日
     * @param invoiceInfo
     * @return
     */
    private static List<List<String>> getSummaryInvoiceInfo(Map<String, List<List<String>>> invoiceInfo) {
        List<List<String>> summaryInvoices = new ArrayList<List<String>>();
        Iterator<String> it = invoiceInfo.keySet().iterator();
        while (it.hasNext()) {
            String companyName = it.next();
            List<List<String>> companyInvoices = invoiceInfo.get(companyName);
            List<String> summaryValues = new ArrayList<String>();

            BigDecimal number1 = new BigDecimal("0");
            BigDecimal number2 = new BigDecimal("0");
            BigDecimal number3 = new BigDecimal("0");
            for (int i = 0; i < companyInvoices.size(); i++) {
                List<String> values = companyInvoices.get(i);
                try {
                    number1 = number1.add(new BigDecimal(values.get(8).replace("\t", "")));
                    number2 = number2.add(new BigDecimal(values.get(9).replace("\t", "")));
                    number3 = number3.add(new BigDecimal(values.get(10).replace("\t", "")));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            summaryValues.add(companyName);
            summaryValues.add(number1.toString());
            summaryValues.add(number2.toString());
            summaryValues.add(number3.toString());
            summaryValues.add(String.valueOf(companyInvoices.size()));
            summaryValues.add(STATUS_OCR_COMPLETE);
            summaryValues.add(STATUS_OCR_COMPLETE_DESC);
            summaryInvoices.add(summaryValues);
        }

        return summaryInvoices;
    }

    /**
     * <pre>
     * 写Excel文件
     * </pre>
     * @author Liu, Yuhong
     * @version 1.0
     * @since 2017年12月17日
     * @param filePath
     * @param columnNames
     * @param valueList
     * @param isAppendToFile
     * @throws Exception
     */
    private static void writeToExcel(String filePath, List<String> columnNames, List<List<String>> valueList, boolean isAppendToFile) throws Exception {
        Workbook wb = null;
        Sheet sheet1 = null;

        if (isAppendToFile) {
            wb = new HSSFWorkbook(new FileInputStream(filePath));
            new File(filePath).delete();
            sheet1 = wb.getSheetAt(0);
        } else {
            wb = new HSSFWorkbook();
            sheet1 = wb.createSheet("流程处理结果");
            Row titleRow = sheet1.createRow(0);
            for (int i = 0; i < columnNames.size(); i++) {
                Cell cell = titleRow.createCell(i);
                cell.setCellValue(columnNames.get(i));
            }
        }

        int lastRowNum = sheet1.getLastRowNum();
        for (int i = 0; i < valueList.size(); i++) {
            List<String> values = valueList.get(i);
            Row row = sheet1.createRow(i + lastRowNum + 1);
            for (int j = 0; j < values.size(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(values.get(j));
            }
        }

        wb.write(new FileOutputStream(filePath));
        wb.close();
    }
}
