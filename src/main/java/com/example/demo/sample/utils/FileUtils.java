package com.example.demo.sample.utils;

import com.example.demo.sample.model.User;

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
        User user = new User();
        user.setUsername("2hu0");
        user.setPassword("123456");
        user.setId(1);
        user.setCompletedExercises(3);
        user.setErrorExercises(3);
        Path path = Paths.get(fileName);
        try (BufferedWriter writer =
                     Files.newBufferedWriter(path, StandardCharsets.UTF_8,
                             StandardOpenOption.APPEND)) {
            writer.write(user.toString()+"\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        FileUtils.writeFile("src/main/resources/file/a.txt","");
    }
}
