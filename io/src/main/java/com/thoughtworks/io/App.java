package com.thoughtworks.io;

import java.io.*;

public class App {
    public static void main(String[] args) throws IOException {
        File f1 = new File("S:\\java");
        File f2 = new File("S:\\java\\from.txt");
        File f3 = new File("S:\\java\\to.txt");
        System.out.println(f1.isFile());
        System.out.println(f1.isDirectory());
        System.out.println(f1.getName());
        System.out.println(f2.isFile());
        System.out.println(f2.isDirectory());
        System.out.println(f2.getName());
        File f4 = new File("S:\\java\\test\\from");
        File f5 = new File("S:\\java\\test\\to");
        FileUtil.copyDirectory(f4, f5);

        /*try {

            File file = new File(f6 + "\\" + f2.getName());

            //创建一个新文件

            boolean fvar = file.createNewFile();

            if (fvar){

                System.out.println("File has been created successfully");

            }

            else{

                System.out.println("File already present at the specified location");

            }

        } catch (IOException e) {

            System.out.println("Exception Occurred:");

            e.printStackTrace();

        }*/
        //printFiles(f1.listFiles());
        /*byte[] data = { 72, 101, 108, 108, 111, 33 };
        try (InputStream input = new ByteArrayInputStream(data)) {
            int n;
            while ((n = input.read()) != -1) {
                System.out.println((char)n);
            }
        }*/

        /*OutputStream output = new FileOutputStream(f2);
        output.write("Hello".getBytes("UTF-8"));
        output.close();*/


        /*readFile(f2);
        readFile(f3);
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(f2);
            output = new FileOutputStream(f3);
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
        readFile(f3);*/
    }

    static void printFiles(File[] files) {
        System.out.println("==========");
        if (files != null) {
            for (File f : files) {
                System.out.println(f);
            }
        }
        System.out.println("==========");
    }

    public static void readFile(File file) throws IOException {
        /*InputStream input = null;
        try {
            input = new FileInputStream("S:\\java");
            int n;
            while ((n = input.read()) != -1) { // 利用while同时读取并判断
                System.out.println(n);
            }
        } finally {
            if (input != null) { input.close(); }
        }*/
        try(InputStream input = new FileInputStream(file)) {
            int n;
            System.out.println(file.getAbsoluteFile());
            while ((n = input.read()) != -1) {
                System.out.println((char) n);
            }
        }
    }
}
