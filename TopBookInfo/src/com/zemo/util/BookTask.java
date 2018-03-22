package com.zemo.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Liam
 * @version 1.0 2018/3/20
 */
public class BookTask extends Thread {
    public String tagName;

    @Override
    public void run() {
        try {
            CopyOnWriteArrayList<String> urls = PageUtil.getUrls(tagName);
            PageUtil.getBook(urls);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入本地文件
     *
     * @param writeFolder
     * @param title
     * @param point
     * @throws Exception
     */
    public void writeToFile(String writeFolder, String title, Double point) throws Exception {
        File folder = new File(writeFolder);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File file = new File(writeFolder + "/" + tagName + ".txt");
        String line = title + "\t" + point + "\n";
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        bw.write(line);
        bw.flush();
        bw.close();
    }


    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

}
