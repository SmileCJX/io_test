package pers.caijx.io;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/11/29/029.
 */
public class IOUtil {

    /**
     * 读取指定文件内容，按照16进制输出到控制台
     * 并且每输出10个byte换行
     * @param fileName
     */
    public static void printHex(String dir,String fileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(dir + fileName);
        int b = 0;
        int i = 1;
        while ((b = fileInputStream.read()) != -1) {
            if (b <= 0xf) {
                System.out.print("0");
            }
            System.out.print(Integer.toHexString(b) + " "); //将整型b转换成为16进制表示的字符串
            if (i++ % 10 == 0) {
                System.out.println();
            }
        }
        fileInputStream.close();
    }

    public static void printHexByByteArray(String dir,String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(dir + fileName);
        byte[] buf = new byte[20 * 1024];

        //从int中批量读取字节，放入到buf这个字节数组中，
        // 从第0个位置开始放，最多放buf.length个
        //返回的是读到的字节的个数
//        int bytes = fis.read(buf,0,buf.length); //一次性读完，说明字节足够大
//        int j = 1;
//        for (int i = 0; i < bytes; i++) {
//            if ((buf[i] & 0xff) <= 0xf) {
//                System.out.print("0");
//            }
//            System.out.print(Integer.toHexString(buf[i] & 0xff) + " "); //取低8位
//            if (j++ % 10 == 0) {
//                System.out.println();
//            }
//        }

        int bytes = 0;
        int j = 1;
        while ((bytes = fis.read(buf,0,buf.length)) != -1) {
            for(int i = 0; i < bytes; i++) {
                //如果它的最高位是1的话，它始终都是负数，所以要加上0xff排除掉负数的情况
                if ((buf[i] & 0xff) <= 0xf) { //比较的时候会自动变成int
                    System.out.print("0");
                }
                System.out.print(Integer.toHexString(buf[i] & 0xff) + " ");
                if (j++ % 10 == 0) {
                    System.out.println();
                }
            }
        }
        fis.close();
    }
}
