package com.zxs.ssm.filter;


import com.zxs.ssm.utils.LoggerUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 编码：UTF-8
 * @author asen
 */
public class EncodingFilter implements Filter {

    private FilterConfig config = null;
    private String ENCODING = "iso-8859-1";

    public void init(FilterConfig config) throws ServletException {
        this.setConfig(config);
        String temp = config.getInitParameter("encoding");
        if(temp != null && !temp.equals("")) {
            ENCODING = temp;
        }
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filter)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String ip = request.getHeader("X-real-ip");
        if(ip == null) {
            ip = request.getRemoteAddr();
        }

        //获取客户端IP需通过Nginx配置
        LoggerUtil.logger.info("请求URL: " + request.getScheme() + "://" + request.getServerName() +
                ":" + request.getServerPort() + request.getContextPath() + request.getServletPath() +
                " 请求来自" + ip);
        LoggerUtil.logger.info("设置字符编码:" + ENCODING);
        LoggerUtil.logger.info("user-agent:" + request.getHeader("user-agent"));
        if("GET".equalsIgnoreCase(request.getMethod())) {
            LoggerUtil.logger.info("Query String: " + request.getQueryString());
        } else {
            String data = null;
            StringBuilder sb = new StringBuilder("");
            Enumeration params = request.getParameterNames();
            while(params.hasMoreElements()) {
                String name = (String) params.nextElement();
                sb.append(name + "=" + request.getParameter(name) + "&");
            }
            if(sb.length() != 0) {
                data = sb.deleteCharAt(sb.length() -1).toString();
            }
            LoggerUtil.logger.info("Query String: " + data);
        }

        request.setCharacterEncoding(ENCODING);
        response.setCharacterEncoding(ENCODING);

        HttpSession session = request.getSession(false);
        if(session != null) {
            LoggerUtil.logger.info("Session Id: " + session.getId());
        } else {
            LoggerUtil.logger.info("Session is null.");
        }

        filter.doFilter(request, response);

        //去除请求头中的冗余数据
        response.setHeader("Server", null);
        response.setHeader("Content-Length", null);
        response.setHeader("Date", null);
    }

    public void destroy() {
        this.setConfig(null);
    }

    public FilterConfig getConfig() {
        return config;
    }

    public void setConfig(FilterConfig config) {
        this.config = config;
    }
}
