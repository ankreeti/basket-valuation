package com.sgn.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for file operations.
 */
public class FileUtils {
    /**
     * Helper method which reads lines from a file (absolute path) and saves them in a List<String>
     *
     * Same feature is available in apache-commons FileUtils class.
     *
     * @param fileName
     * @return List<String>
     */
    public static List<String> readFile(String fileName){
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (null != line) {
                lines.add(line);
                line = br.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Exception while reading file :: ", e);
        }
        return lines;
    }

    /**
     * Helper method which reads lines from a file (absolute path) and saves them in a List<String>
     *
     * Same feature is available in apache-commons FileUtils class.
     *
     * @param inputStream
     * @return List<String>
     */
    public static List<String> readFile(InputStream inputStream){
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line = br.readLine();
            while (null != line) {
                lines.add(line);
                line = br.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Exception while reading file :: ", e);
        }
        return lines;
    }



}
