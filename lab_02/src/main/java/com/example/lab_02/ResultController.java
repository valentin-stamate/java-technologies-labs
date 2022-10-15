package com.example.lab_02;

import com.example.lab_02.beans.UserForm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ResultController", value = "/result_controller")
public class ResultController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String wordQuery = request.getParameter("word");
        String sizeQuery = request.getParameter("size");

        if (wordQuery == null) {
            wordQuery = "";
        }

        int size = 0;
        if (sizeQuery != null) {
            size = Integer.parseInt(sizeQuery);
        }

        UserForm userForm = new UserForm(wordQuery, size);

        request.setAttribute("userForm", userForm);

        getServletContext()
                .getRequestDispatcher("/result_compulsory.jsp")
                .forward(request, response);
    }

}
