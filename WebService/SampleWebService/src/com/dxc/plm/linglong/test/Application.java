/*
 * HPE Confidential
 * Copyright © 2017 HPE, Inc.
 * 
 * Created By Liu Yuhong - 2017年6月8日
 */
package com.dxc.plm.linglong.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.xml.ws.Endpoint;

import com.dxc.plm.linglong.test.service.TestServiceImpl;

public class Application {

    /**
     * Publish the WebService
     * 
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        String wsUrl = "http://127.0.0.1:7001/test";
        String filePath = "C:/Temp/ws_test_data.csv";
        if (args != null && args.length > 1) {
            filePath = args[0];
            wsUrl = args[1];
        }
//            System.out.println("Must input two args, first for data file path, second for web service url. ");
//            return;
//        }
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(filePath);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String line = br.readLine();
            StringBuffer result = new StringBuffer();
            while (line != null) {
                result.append("\n").append(line);
                line = br.readLine();
            }
            TestServiceImpl.setResult(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        Endpoint.publish(wsUrl, new TestServiceImpl());
        System.out.println("TestService is published to " + wsUrl);
    }

}
