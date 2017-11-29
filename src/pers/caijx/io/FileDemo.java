package pers.caijx.io;

import java.io.File;

/**
 * Created by Administrator on 2017/11/28/028.
 */
public class FileDemo {

    public static void main(String[] args) {
        File file = new File(System.getProperty("user.dir") ,"/src/test.txt");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.exists());
        //是否是一个目录  如果是目录返回true,如果不是目录or目录不存在返回的是false
        System.out.println(file.isDirectory());
        //是否是一个文件
        System.out.println(file.isFile());
        //常用的File对象的API
        System.out.println(file);//file.toString()的内容
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.getParentFile().getAbsolutePath());
    }
}
