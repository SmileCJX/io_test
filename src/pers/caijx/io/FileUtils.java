package pers.caijx.io;

import java.io.File;

/**
 * 列出File的一些常用操作比如过滤、遍历等操作
 * Created by Administrator on 2017/11/28/028.
 */
public class FileUtils {

    /**
     * 列出指定目录下（包括其子目录）的所有文件
     * @param dir
     */
    public static void listDirectory(File dir) {
        if (!dir.exists()) {
            throw new IllegalArgumentException("目录：" + dir + "不存在.");
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(dir + "不是目录");
        }
//        String[] fileNames = dir.list(); //返回的是字符串数组，直接子的名称，不包含子目录下的内容
//        for (String s : fileNames) {
//            System.out.println(s);
//        }
        File[] files = dir.listFiles(); //返回的是直接子目录的抽象
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listDirectory(file);
                } else {
                    System.out.println(file);
                }
            }
        }
    }
}
