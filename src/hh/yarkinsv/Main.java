package hh.yarkinsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        for (int i = 1; i < args.length; i++) {
            SearchStringInFile(args[i], args[0]);
        }
    }

    public static void SearchStringInFile(String fileName, String searchString) {
        try (FileReader reader = new FileReader(fileName);
             BufferedReader buffreader = new BufferedReader(reader)) {
            String s;
            int lineNumber = 0;
            while ((s = buffreader.readLine()) != null) {
                lineNumber++;
                if (s.contains(searchString)) {
                    System.out.printf("%s: %d\n", fileName, lineNumber);
                };
            }
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
