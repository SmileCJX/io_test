package pers.caijx.io;

import java.io.IOException;

/**
 * Created by Administrator on 2017/11/29/029.
 */
public class IOUtilTest {

    public static void main(String[] args) throws IOException {
//        IOUtil.printHex(System.getProperty("user.dir") ,"/src/test.txt");
        IOUtil.printHexByByteArray(System.getProperty("user.dir") ,"/src/test.txt");
    }
}
