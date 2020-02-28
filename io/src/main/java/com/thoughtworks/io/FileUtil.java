package com.thoughtworks.io;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Objects;

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
            String separator = File.separator;
            for (File file : Objects.requireNonNull(from.listFiles())) {
                File toFile = new File(to + separator + file.getName());
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
            for (File f : Objects.requireNonNull(file.listFiles())) {
                emptyFile(f);
            }
        }
        return file.delete();
    }
    /*
     *文件复制
     *使用Channel的transferFrom方法
     *
     */
    public static void copyFile(File from, File to) {
        if (!from.exists() || from.isDirectory()) {
            return;
        }
        try (FileChannel fileIn = new FileInputStream(from).getChannel();
             FileChannel fileOut = new FileOutputStream(to).getChannel())
        {
            fileIn.transferTo(0, fileIn.size(), fileOut);
        } catch (IOException e) {
        }
    }
}
