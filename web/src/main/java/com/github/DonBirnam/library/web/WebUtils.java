package com.github.DonBirnam.library.web;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {
    public static Integer getPage(HttpServletRequest req) {
        String fromRequest;
        if ((fromRequest = req.getParameter("pageNum")) != null) {
            return Integer.valueOf(fromRequest);
        } else {
            return 0;
        }
    }
}
