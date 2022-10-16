package com.example.lab_02.filters;

import com.example.lab_02.beans.InitParameters;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "LanguageFilter")
public class LanguageFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        boolean foundLanguageCookie = false;

        for (Cookie cookie : List.of(httpServletRequest.getCookies())) {
            if (cookie == null) {
                continue;
            }

            String cookieName = cookie.getName();
            String cookieValue = cookie.getValue();

            foundLanguageCookie = foundLanguageCookie || (cookieName.equals("language") && !cookieValue.equals(""));
        }

        if (!foundLanguageCookie) {
            Cookie languageCookie = new Cookie("language", InitParameters.getLanguage());
            httpServletResponse.addCookie(languageCookie);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
