package com.example.demo.sample.utils;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author 2hu0
 */
public class FileUtils {

    public static void writeFile(String fileName,String target) throws IOException {
        String path = "D:\\dsProjectDesign\\springboot-javafx-demo\\src\\main\\resources\\file\\" + fileName + ".txt";
        File file = new File(path);

        // 返回true表示文件成功
        // false 表示文件已经存在
        if (file.createNewFile()) {
            Files.write(Paths.get(path),
                    target.getBytes(StandardCharsets.UTF_8));
        } else {
            // 追加写模式
            Files.write(
                    Paths.get(path),
                    target.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.APPEND);
        }
    }
    public static void main(String[] args) throws IOException {
        FileUtils.writeFile("src/main/resources/file/error.txt","");
    }
}
