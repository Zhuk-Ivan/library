package com.github.DonBirnam.library.web;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {

//    private String encoding = "utf-8";
//
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//        request.setCharacterEncoding(encoding);
//        filterChain.doFilter(request, response);
//    }
//
//    public void init(FilterConfig filterConfig) throws ServletException {
//        String encodingParam = filterConfig.getInitParameter("encoding");
//        if (encodingParam != null) {
//            encoding = encodingParam;
//        }
//    }

    private static final String UTF_8 = "UTF-8";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        request.setCharacterEncoding(UTF_8);
        response.setCharacterEncoding(UTF_8);
        filterChain.doFilter(request, response);
    }
}



