package hh.yarkinsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    private static HashMap<String, ArrayList<Integer>> m_cache = new HashMap<>();

    private static final int TREAD_NUMBER = 1;

    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();

        List<Long> files = new ArrayList<>();

        for (int i = 1; i < args.length; i++ ) {
            m_cache.put(args[i], new ArrayList<>());
            files.add(new File(args[i]).length());
        }



        ExecutorService executorService = Executors.newFixedThreadPool(TREAD_NUMBER);
        List<Callable<Object>> tasks = new ArrayList<>();

        for (String fileName : m_cache.keySet()) {
            //tasks.add(() -> {
                m_cache.get(fileName).addAll(SearchStringInFile(fileName, args[0]));
            //    return null;
            //});
        }
/*
        try {
            executorService.invokeAll(tasks);
            executorService.shutdown();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

*/

        for (int i = 1; i < args.length; i++) {
            if (m_cache.containsKey(args[i])) {
                for (Integer x : m_cache.get(args[i])) {
                    System.out.printf("%s: %d\n", args[i], x);
                }
            }
        }

        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) );
    }

    public static ArrayList<Integer> SearchStringInFile(String fileName, String searchString) {
        ArrayList<Integer> result = new ArrayList<>();

        try (FileReader reader = new FileReader(fileName);
             BufferedReader buffreader = new BufferedReader(reader)) {
            String s;
            int lineNumber = 0;
            while ((s = buffreader.readLine()) != null) {
                lineNumber++;
                if (s.contains(searchString)) {
                    result.add(lineNumber);
                }
            }
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

        return result;
    }
}
