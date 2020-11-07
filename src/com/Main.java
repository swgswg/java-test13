package com;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

/**
 * 编写一段程序，判断d:/study/io目录是否存在，存在则遍历输出这个目录的所有文件名称，不存在则创建这个目录。
 *
 * 编写一段程序，删除d:/study/io这个目录下的大于20Kb的文件。
 *
 * 编写一段程序，循环从键盘输入一行字符，把这行字符首尾颠倒后按行输出到一个文件中，遇到quit后退出。
 *
 * 编写一个文件复制函数，copyFile(String from, String to)，from为原文件名，to为复制后的新文件名，复制结束打印所复制的文件大小和用时（毫秒），执行结果如：copy xxx use xxx ms, file size is xxx.
 */
public class Main {

    public static void main(String[] args) throws IOException {
//        listFile("D:/study/io");
//        deleteFileTest("D:/study/io");
//        scannerTest();
        copyFile("D:\\study\\io\\1.txt", "D:\\study\\io\\2.txt");
    }

    public static void listFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        } else {
            String[] list = file.list();
            if (list == null || list.length == 0) {
                return;
            }
            for (String item : list) {
                String itemPath = path + File.separator + item;
                File itemFile = new File(itemPath);
                if (itemFile.isFile()) {
                    System.out.println(item);
                } else {
                    listFile(itemPath);
                }
            }
        }
    }


    public static void deleteFileTest(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }

        String[] list = file.list();
        if (list == null || list.length == 0) {
            return;
        }
        for (String item : list) {
            String itemPath = path + File.separator + item;
            File itemFile = new File(itemPath);
            if (itemFile.isFile() && itemFile.length() > 20 * 1024) {
                itemFile.delete();
            } else {
                deleteFileTest(itemPath);
            }
        }
    }


    public static void scannerTest() {
        Scanner scanner = new Scanner(System.in);

        try {
            String s = "";
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\study\\io\\1.txt"));

            while(true){
                s = scanner.nextLine();
                if(s.equals("quit")){
                    break;
                }
                StringBuffer str = new StringBuffer(s);
                bw.write(String.valueOf(str.reverse()));
                bw.flush();
            }
            bw.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void copyFile(String from, String to) throws IOException {
        long startTime = System.currentTimeMillis();

        FileInputStream fin = new FileInputStream(from);
        FileOutputStream fout = new FileOutputStream(to);

        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bout = new BufferedOutputStream(fout);

        byte [] b = new byte[2048];

        int len = bin.read(b);
        int size = 0;
        while(len != -1){
            size += len;
            bout.write(b,0,len);
            len = bin.read(b);
        }

        bout.close();
        fout.close();
        bin.close();
        fin.close();

        long endTime = System.currentTimeMillis();

        System.out.println("copy " + from + " use " + (endTime - startTime) + " ms, file size is " + size + "字节.");
    }

}
