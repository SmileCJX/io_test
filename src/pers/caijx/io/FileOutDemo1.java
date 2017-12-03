package pers.caijx.io;

import java.io.FileOutputStream;

/**
 * Created by Administrator on 2017/12/3/003.
 */
public class FileOutDemo1 {

    public static void main(String[] args) throws Exception {
        //如果该文件不存在，则直接创建，如果存在，删除后创建，如果参数为File类的情况下，则必须要保证该文件存在
        FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/demo/fos.bat");
        fos.write('A'); //写出了A的低8位
        fos.write('B'); //写出来B的低8位
        int num = 10; //write只能写8位，写一个int需要4次8位
        fos.write(num >>> 24);
        fos.write(num >>> 16);
        fos.write(num >>> 8);
        fos.write(num);
        byte[] gbk = "中国".getBytes("UTF-8");
        fos.write(gbk);
        fos.close();
        IOUtil.printHex(System.getProperty("user.dir"),"/demo/fos.bat");
    }
}
