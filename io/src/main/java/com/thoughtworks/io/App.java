package com.thoughtworks.io;

import java.io.*;

public class App {
    public static void main(String[] args) throws IOException {
        String sep = File.separator;
        File file = new File("E:" + sep + "TW" + sep + "test");
        FileUtil.emptyFile(file);
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
