package pers.caijx.io;

import java.io.*;

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
                //如果它的最高位是1的话，它始终都是负数，所以要加上0xff只取低8位
                /**
                 * 举个简单的例子:
                 byte[]  b = new byte[5];
                 b[0] = -12;
                 byte   8位二进制   =   1个字节    char   2个字节   short (2个字节)    int（4个字节） long（8个字节） float  （4个字节） double（8个字节）
                 计算机存储数据机制：正数存储的二进制原码,负数存储的是二进制的补码。  补码是负数的绝对值反码加1。
                 比如-12，-12 的绝对值原码是：0000 1100  取反： 1111 0011  加1：  1111 0100
                 byte --> int   就是由8位变 32 位 高24位全部补1： 1111 1111 1111 1111 1111 1111 1111 0100 ;
                 0xFF 是计算机十六进制的表示： 0x就是代表十六进制，A B C D E F  分别代表10 11 12 13 14 15   F就是15  一个F 代表4位二进制：可以看做 是   8  4  2  1。
                 0xFF的二进制表示就是：1111 1111。   高24位补0：0000 0000 0000 0000 0000 0000 1111 1111;
                 -12的补码与0xFF 进行与（&）操作  最后就是0000 0000 0000 0000 0000 0000 1111 0100
                 转换为十进制就是 244。
                 byte类型的数字要&0xff再赋值给int类型，其本质原因就是想保持二进制补码的一致性。

                 当byte要转化为int的时候，高的24位必然会补1，这样，其二进制补码其实已经不一致了，&0xff可以将高的24位置为0，低8位保持原样。这样做的目的就是为了保证二进制数据的一致性。

                 有人问为什么上面的式子中b[0]不是8位而是32位，因为当系统检测到byte可能会转化成int或者说byte与int类型进行运算的时候，就会将byte的内存空间高位补1（也就是按符号位补位）扩充到32位，再参与运算。
                 */
//                System.out.println(buf[i] & 0xff);
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

    /**
     * 文件拷贝，字节批量读取
     * @param srcFile
     * @param destFile
     * @throws IOException
     */
    public static void copyFile(File srcFile,File destFile) throws IOException {
        if (!srcFile.exists()) {
            throw new IllegalArgumentException("文件：" + srcFile + "不存在");
        }
        if (!srcFile.isFile()) {
            throw new IllegalArgumentException("不是一个文件");
        }
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);
        byte[] bytes = new byte[8 * 1024];
        int b;
        while ((b = fis.read(bytes,0,bytes.length)) != -1) {
            fos.write(bytes,0,b);
            fos.flush();
        }
        fos.close();
        fis.close();
    }

    /**
     * 进行文件拷贝，使用带缓冲的字节流，最快的一种方式
     * @param srcFile
     * @param destFile
     */
    public static void copyFileByBuffer(File srcFile,File destFile) {
        if (!srcFile.exists()) {
            throw new IllegalArgumentException("文件：" + srcFile + "不存在");
        }
        if (!srcFile.isFile()) {
            throw new IllegalArgumentException("不是一个文件");
        }
        try {
            try (BufferedInputStream bis =
                         new BufferedInputStream(new FileInputStream(srcFile));
                BufferedOutputStream bos =
                        new BufferedOutputStream(new FileOutputStream(destFile))){
                int c;
                byte[] bytes = new byte[15 * 1024];
                /**
                 * 使用缓冲字节流复制确实是最快的方式，
                 * 对于小文件10M以下的文件体现不出优势，对于百兆文件正确使用，时间可以控制到50ms内。
                 * 复制文件最快的做法是将批量读取到的字节数组使用缓冲写入到文件，在机器性能范围内字节数组越大越快。在循环写入的过程中不需要使用flush
                 */
                while ((c = bis.read(bytes,0,bytes.length)) != -1) {
                    bos.write(bytes,0,c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单字节，不带缓冲进行文件拷贝
     * @param srcFile
     * @param descFile
     */
    public static void copyFileByByte(File srcFile,File descFile) {
        if (!srcFile.exists()) {
            throw new IllegalArgumentException("文件：" + srcFile + "不存在");
        }
        if (!srcFile.isFile()) {
            throw new IllegalArgumentException("不是一个文件");
        }
        try (FileInputStream fis = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(descFile)){
            int c;
            while ((c = fis.read()) != -1) {
                fos.write(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
