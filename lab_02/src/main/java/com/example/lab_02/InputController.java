package com.example.lab_02;

import com.example.lab_02.service.UtilService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "InputController", value = "/input_controller")
public class InputController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String randomImageName = UtilService.getRandomResource("images");

        request.setAttribute("captcha", randomImageName);

        getServletContext()
                .getRequestDispatcher("/input_compulsory.jsp")
                .forward(request, response);
    }

}
