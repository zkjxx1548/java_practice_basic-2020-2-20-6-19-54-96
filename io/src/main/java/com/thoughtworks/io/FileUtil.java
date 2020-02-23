package com.thoughtworks.io;

import java.io.*;
import java.nio.channels.FileChannel;

public class FileUtil {

    /**
     * 完成复制文件夹方法:
     * 1. 把给定文件夹from下的所有文件(包括子文件夹)复制到to文件夹下
     * 2. 保证to文件夹为空文件夹，如果to文件夹不存在则自动创建
     * <p>
     * 例如把a文件夹(a文件夹下有1.txt和一个空文件夹c)复制到b文件夹，复制完成以后b文件夹下也有一个1.txt和空文件夹c
     */
    public static boolean copyDirectory(File from, File to) throws IOException {
        emptyFile(to);
        if (!from.exists()) {
            return false;
        }
        if (from.isFile()) {
            copyFile(from, to);
        } else {
            for (File file : from.listFiles()) {
                File toFile = new File(to + "\\" + file.getName());
                if (file.isDirectory()) {
                    toFile.mkdirs();
                }
                copyDirectory(file, toFile);
            }
        }
        return true;
    }

    /*
     *清空文件夹
     *
     *
     */
    public static boolean emptyFile(File file) {
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            file.delete();
        } else {
            for (File f : file.listFiles()) {
                emptyFile(f);
            }
        }
        return true;
    }
    /*
     *文件复制
     *
     *
     */
    public static boolean copyFile(File from, File to) throws IOException {
        if (!from.exists() || from.isDirectory()) {
            return false;
        }
        //使用Channel的transferFrom方法
        try {
            FileChannel fileIn = new FileInputStream(from).getChannel();
            FileChannel fileOut = new FileOutputStream(to).getChannel();
            fileIn.transferTo(0, fileIn.size(), fileOut);
            fileIn.close();
            fileOut.close();
        } catch (IOException e) {
            return false;
        }
        return true;

        //使用FileStreams复制
        /*InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(from);
            output = new FileOutputStream(to);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
        }*/

    }
}
