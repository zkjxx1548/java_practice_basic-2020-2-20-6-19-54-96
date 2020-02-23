package com.thoughtworks.io;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {

    /**
     * 完成复制文件夹方法:
     * 1. 把给定文件夹from下的所有文件(包括子文件夹)复制到to文件夹下
     * 2. 保证to文件夹为空文件夹，如果to文件夹不存在则自动创建
     * <p>
     * 例如把a文件夹(a文件夹下有1.txt和一个空文件夹c)复制到b文件夹，复制完成以后b文件夹下也有一个1.txt和空文件夹c
     */
    public static boolean copyDirectory(File from, File to) throws IOException {
        if (!from.exists()) {
            return false;
        }
        if (from.isFile()) {
            copyFile(from, to);
        } else {
            /*String oldName = from.getName();
            System.out.println("oldName:" + from.getName());
            int fromNumber = 0;
            File newFromDir = new File(to, oldName);
            System.out.println("newFromDir:" + newFromDir.getName());
            while (newFromDir.exists()) {
                fromNumber++;
                String newName = oldName + "-copy" + fromNumber;
                newFromDir = new File(to, newName);
            }*/
            //newFromDir.mkdirs();
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
     *文件复制
     *
     *
     */
    public static boolean copyFile(File from, File to) throws IOException {
        if (!from.exists() || from.isDirectory()) {
            return false;
        }
        /*String oldName = from.getName();
        Pattern suffixPattern = Pattern.compile("\\.\\w+");
        Matcher matcher = suffixPattern.matcher(oldName);
        String nameBody;
        String suffix;
        if (matcher.find()) {
            nameBody = oldName.substring(0, matcher.start());
            suffix = oldName.substring(matcher.start());
        } else {
            nameBody = oldName;
            suffix = "";
        }
        int fileNumber = 0;
        File newFile = new File(to, oldName);
        while (newFile.exists()) {
            fileNumber++;
            String newName = nameBody + "-copy" + fileNumber + suffix;
            newFile = new File(to, newName);
        }*/
        //使用FileStreams复制
        InputStream input = null;
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
        }

        /*try {
            FileChannel fileIn = new FileInputStream(from).getChannel();
            FileChannel fileOut = new FileOutputStream(to).getChannel();
            fileIn.transferTo(0, fileIn.size(), fileOut);
            fileIn.close();
            fileOut.close();
        } catch (IOException e) {
            return false;
        }*/
        return true;

        //使用Channel的transferFrom方法
        /*FileChannel input = null;
        FileChannel output = null;
        try {
            input = new FileInputStream(from).getChannel();
            output = new FileOutputStream(to).getChannel();
            output.transferFrom(input, 0, input.size());
        } finally {
            input.close();
            output.close();
        }*/



        //使用Commons IO复制
        /*Files.copy(from.toPath(), to.toPath());*/

        //
        /*try {
            InputStream in = new FileInputStream(from);
            OutputStream out = new FileOutputStream(to);

            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = in.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //
        /*if(!from.exists()){
            System.out.println(from.getName()+"文件不存在");
        }else{
            System.out.println("存在");
        }
        byte[] b = new byte[(int) from.length()];
        if(from.isFile()){
            try {
                FileInputStream is= new FileInputStream(from);
                FileOutputStream ps= new FileOutputStream(to);
                is.read(b);
                ps.write(b);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(from.isDirectory()){
            if(!from.exists())
                from.mkdir();
            String[] list = from.list();
            for(int i=0;i<list.length;i++){
                this.copyDirectory(list[i], list[i]);
            }
        }*/

        //
        /*FileInputStream is= new FileInputStream(from);
        FileOutputStream ps= new FileOutputStream(to);
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = is.read(buf)) != -1) {
            ps.write(buf, 0, len);
        }
        is.close();
        ps.close();*/
    }
}
