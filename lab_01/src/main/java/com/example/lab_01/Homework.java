package com.example.lab_01;

import com.example.lab_01.services.UtilService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(name = "Homework", value = "/homework")
public class Homework extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        UtilService.generatePermutations(x, n, size != 0 ? size : n, 0, letters, permutations);

        List<String> fileLines = UtilService.readFileFromResources("dictionary.txt");

        Set<String> acceptableWords = new HashSet<>(fileLines);

        boolean areWordsInDictionary = fileLines.size() != 0;

        if (areWordsInDictionary) {
            permutations = permutations.stream()
                    .filter(acceptableWords::contains)
                    .collect(Collectors.toList());
        }

        StringBuilder htmlContent = new StringBuilder();

        htmlContent.append(String.format("<form action=\"homework\">\n" +
                "  <label for=\"word\">Word:</label><br>\n" +
                "  <input type=\"text\" id=\"word\" name=\"word\" value=\"%s\"><br>\n" +
                "  <label for=\"size\">Size:</label><br>\n" +
                "  <input type=\"number\" id=\"size\" name=\"size\" value=\"%s\"><br><br>\n" +
                "  <input type=\"submit\" value=\"Submit\">\n" +
                "</form><br>", wordQuery, sizeQuery));

        htmlContent.append(String.format("<div>Word: %s</div>", wordQuery));
        htmlContent.append(String.format("<div>Size: %s</div><br>", sizeQuery));
        htmlContent.append(String.format("<div>Dictionary: %s</div><br>", areWordsInDictionary ? "true" : "false"));

        htmlContent.append("Permutations:");
        htmlContent.append("<ol>");

        permutations.forEach(permutation -> {
            htmlContent.append(String.format("<li>%s</li>", permutation));
        });

        htmlContent.append("</ol>");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println(htmlContent);
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
