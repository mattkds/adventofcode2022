package com.mattkds.advofcode2022.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Utils class to handle input files
 */
public class FileUtils {

    /**
     * Return the content of an input file as a String Object
     * @param path the complete path of files
     * @return a String which reprensent the file content
     */
    public static String getFileContentAsString(String path) throws IOException {
        Path filePath = Path.of(path);
        return Files.readString(filePath);
    }

}
