package com.example.lab_01;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Compulsory", value = "/compulsory")
public class Compulsory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String queryString = request.getParameter("word");

        char[] letters = new char[0];

        if (queryString != null) {
            letters = queryString.toCharArray();
        }

        StringBuilder htmlList = new StringBuilder();
        htmlList.append("<ol>");

        for (char letter : letters) {
            htmlList.append(String.format("<li>%c</li>", letter));
        }

        htmlList.append("</ol>");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println(htmlList);
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
