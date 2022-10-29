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
        Path path = Paths.get(fileName);
        try (BufferedWriter writer =
                     Files.newBufferedWriter(path, StandardCharsets.UTF_8,
                             StandardOpenOption.APPEND)) {
            writer.write(target+"\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        FileUtils.writeFile("src/main/resources/file/error.txt","");
    }
}
