package com.example.lab_02.listeners;

import com.example.lab_02.beans.InitParameters;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationWebListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Context initialized");

        ServletContext servletContext = servletContextEvent.getServletContext();
        InitParameters.setLanguage(servletContext.getInitParameter("language"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Context destroyed");
    }


}
