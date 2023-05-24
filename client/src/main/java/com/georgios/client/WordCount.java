package com.georgios.client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

class WordCounter {

    void countWords(String fileName) throws IOException {
        try {
            Path filePath = Paths.get(fileName);
            Map<String, Integer> wordCounts = new HashMap<>();

            // Read all lines from file
            List<String> lines = Files.readAllLines(filePath);

            for (String line : lines) {
                // Split line into words, removing non-alphabetical characters
                String[] words = line.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+");

                for (String word : words) {
                    wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                }
            }

            List<Map.Entry<String, Integer>> entries = new ArrayList<>(wordCounts.entrySet());
            entries.sort((a, b) -> b.getValue().compareTo(a.getValue()));

            for (int i = 0; i < 10; i++) {
                System.out.println(entries.get(i).getKey() + ": " + entries.get(i).getValue());
            }
        } catch (IOException e) {
            System.out.println("WordCounter:countWords:" + e.getMessage());
        }
    }
}
