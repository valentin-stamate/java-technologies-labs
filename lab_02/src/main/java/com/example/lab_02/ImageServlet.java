package com.example.lab_02;

import com.example.lab_02.service.UtilService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(urlPatterns = "/image")
public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String imageName = request.getParameter("name");

        ServletOutputStream out = response.getOutputStream();

        FileInputStream randoImageInputStream = UtilService.getFileInputStream(String.format("%s/%s", "images/", imageName));
        BufferedInputStream bin = new BufferedInputStream(randoImageInputStream);
        BufferedOutputStream bout = new BufferedOutputStream(out);

        int ch = 0;
        while((ch = bin.read()) != -1){
            bout.write(ch);
        }

        response.setContentType("image/jpeg");

        bin.close();
        randoImageInputStream.close();
        bout.close();
        out.close();
    }

}
