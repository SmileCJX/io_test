package pers.caijx.io;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/3/003.
 */
public class DisDemo {

    public static void main(String[] args) throws IOException {
        String file = System.getProperty("user.dir") + "/demo/dos.bat";
        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        int i = dis.readInt();
        System.out.println(i);
        i = dis.readInt();
        System.out.println(i);
        long l = dis.readLong();
        System.out.println(l);
        double d = dis.readDouble();
        System.out.println(d);
        String s = dis.readUTF();
        System.out.println(s);

    }
}
