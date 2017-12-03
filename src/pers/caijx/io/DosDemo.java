package pers.caijx.io;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/3/003.
 */
public class DosDemo {

    public static void main(String[] args) throws IOException {
        String file = System.getProperty("user.dir") + "/demo/dos.bat";
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
        dos.writeInt(10);
        dos.writeInt(-10);
        dos.writeLong(10L);
        dos.writeDouble(10.5);
        //采用UTF-8格式写出
        dos.writeUTF("中国");
        //采用UTF-16BE编码写出
        dos.writeChars("中国");
        dos.close();
        IOUtil.printHex(System.getProperty("user.dir"),"/demo/dos.bat");
    }
}
