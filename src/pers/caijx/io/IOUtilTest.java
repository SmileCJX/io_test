package pers.caijx.io;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017/11/29/029.
 */
public class IOUtilTest {

    public static void main(String[] args) throws IOException {
//        IOUtil.printHex(System.getProperty("user.dir") ,"/src/test.txt");
//        IOUtil.printHexByByteArray(System.getProperty("user.dir") ,"/src/test.txt");

        long start = System.currentTimeMillis();
//        IOUtil.copyFileByByte(new File(System.getProperty("user.dir") + "/demo/残酷月光-林宥嘉.mp3"),
//                new File(System.getProperty("user.dir") + "/copy/残酷月光-林宥嘉.mp3")); //49519ms
//        IOUtil.copyFile(new File(System.getProperty("user.dir") + "/demo/残酷月光-林宥嘉.mp3"),
//                new File(System.getProperty("user.dir") + "/copy/残酷月光-林宥嘉.mp3")); //46ms
        IOUtil.copyFileByBuffer(new File(System.getProperty("user.dir") + "/demo/残酷月光-林宥嘉.mp3"),
                new File(System.getProperty("user.dir") + "/copy/残酷月光-林宥嘉.mp3"));  //23ms
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
