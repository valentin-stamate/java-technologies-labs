package com.example.lab_02.filters;

import com.example.lab_02.wrappers.ResponseWrapper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "DecoratorFilter")
public class DecoratorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        ResponseWrapper wrapper = new ResponseWrapper((HttpServletResponse) response);

        chain.doFilter(request, wrapper);

        String content = wrapper.toString();
        String prelude = "<div><b>Hello Laboratory 2!</b></div><br>";
        String coda = "<div><b>Made by Stamate Valentin.</b></div>";

        String finalContent = String.format("%s %s %s", prelude, content, coda);

        PrintWriter out = response.getWriter();
        out.write(finalContent);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
