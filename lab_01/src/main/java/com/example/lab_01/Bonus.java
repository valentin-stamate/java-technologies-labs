package com.example.lab_01;

import com.example.lab_01.services.UtilService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(name = "Bonus", value = "/bonus")
public class Bonus extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String logString = String.format("[%s] %s %s %s %s\n",
                request.getMethod(),
                request.getRemoteAddr(),
                request.getHeader("User-Agent"),
                request.getLocale().toString(),
                request.getQueryString());

        System.out.print(logString);

        UtilService.appendToFile(logString, "logs.txt");

        String wordQuery = request.getParameter("word");
        String sizeQuery = request.getParameter("size");

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

        response.setContentType("text/json");
        PrintWriter out = response.getWriter();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(permutations);

        out.println(jsonResponse);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
