package com.example.lab_02.services.service;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UtilService {

    public static void generatePermutations(int[] x, int n, int p, int k, char[] letters, List<String> permutationList){
        for(int i = 0 ; i < n ; i++) {
            x[k] = i;

            if(isOk(x, k)) {
                if(k == p) {
                    String permutation = getPermutation(x, p, letters);
                    permutationList.add(permutation);

                    return;
                }

                generatePermutations(x, n, p, k + 1, letters, permutationList);
            }
        }
    }

    private static boolean isOk(int[] x, int k){
        for(int i = 0 ; i < k ; i++) {
            if(x[k] == x[i]) {
                return false;
            }
        }

        return true;
    }

    private static String getPermutation(int[] x, int p, char[] letters) {
        StringBuilder permutation = new StringBuilder();

        for (int i = 0; i < p; i++) {
            permutation.append(letters[x[i]]);
        }

        return permutation.toString();
    }

    public static boolean fileExists(String filePath) {
        Path path = Paths.get(filePath);
        return Files.exists(path);
    }

    public static List<String> readFileFromResources(String filePath) {
        List<String> lines = new ArrayList<>();

        URL resourcePath = UtilService.class.getClassLoader().getResource("");

        if (resourcePath == null) {
            return lines;
        }

        String resourcesPath = resourcePath.getPath().substring(1).replace("%20", " ");
        String fullPath = resourcesPath + filePath;

        try {
            lines = Files.readAllLines(Paths.get(fullPath));
        } catch (IOException e) { }

        return lines;
    }

    public static void appendToFile(String text, String filePath) {
        URL resourcePath = UtilService.class.getClassLoader().getResource("");

        try {
            String resourcesPath = resourcePath.getPath().substring(1).replace("%20", " ");
            String fullPath = resourcesPath + filePath;

            Files.write(Paths.get(fullPath), text.getBytes(), StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getPermutations(String wordQuery, String sizeQuery) {
        char[] letters = new char[0];
        int size = 0;

        if (wordQuery != null) {
            letters = wordQuery.toCharArray();
        }

        if (sizeQuery != null) {
            size = Integer.parseInt(sizeQuery);
        }

        List<String> permutations = new ArrayList<>();

        int n = letters.length;

        int[] x = new int[n + 1];

        if (size == 0) {
            for (int i = 1; i <= n; i++) {
                UtilService.generatePermutations(x, n, i, 0, letters, permutations);
            }
        } else {
            UtilService.generatePermutations(x, n, size, 0, letters, permutations);
        }

        List<String> fileLines = UtilService.readFileFromResources("dictionary.txt");

        Set<String> acceptableWords = new HashSet<>(fileLines);

        boolean areWordsInDictionary = fileLines.size() != 0;

        if (areWordsInDictionary) {
            permutations = permutations.stream()
                    .filter(acceptableWords::contains)
                    .collect(Collectors.toList());
        }

        return permutations;
    }

}
