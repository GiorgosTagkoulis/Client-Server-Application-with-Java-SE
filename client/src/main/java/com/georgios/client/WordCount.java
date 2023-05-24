package com.georgios.client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

class WordCounter {

    public static void main(String[] args) {
        System.out.println("WordCounter");
        WordCounter wordCounter = new WordCounter();
        try {
            wordCounter.countWords("client/src/main/resources/islands_in_the_stream.txt");
        } catch (IOException e) {
            System.out.println("WordCounter:main:IOException:" + e.getMessage());
        }
    }

    void countWords(String fileName) throws IOException {
        try {
            Path filePath = Paths.get(fileName);
            Map<String, Integer> wordCounts = new HashMap<>();

            // Read all lines from file and remove empty lines
            List<String> lines = Files.readAllLines(filePath);
            lines.removeIf(line -> line.isEmpty());

            for (String line : lines) {
                // Split line into words, removing non-alphabetical characters
                String[] words = line.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+");

                for (String word : words) {
                    wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                }
            }

            // Sort wordCounts by value
            List<Map.Entry<String, Integer>> entries = new ArrayList<>(wordCounts.entrySet());
            entries.sort((a, b) -> b.getValue().compareTo(a.getValue()));

            // print top 10 words
            System.out.println("Top 10 words:");
            for (int i = 0; i < 10; i++) {
                System.out.printf("%10s: %3d%n", entries.get(i).getKey(), entries.get(i).getValue());
            }
        } catch (IOException e) {
            System.out.println("WordCounter:countWords:" + e.getMessage());
        }
    }
}
