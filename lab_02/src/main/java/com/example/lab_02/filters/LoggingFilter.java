package com.example.lab_02.filters;
import com.example.lab_02.models.Log;
import com.example.lab_02.service.LoggingService;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter(filterName = "LoggingFilter")
public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        Log log = new Log(request.getRemoteAddr(), request.getLocale().toString());

        LoggingService loggingService = new LoggingService();
        loggingService.writeLog(log);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
