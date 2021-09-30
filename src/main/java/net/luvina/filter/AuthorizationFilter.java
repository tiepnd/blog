package net.luvina.filter;

import net.luvina.model.User;
import net.luvina.util.Constant;
import net.luvina.util.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        if (!url.contains("/admin")) {
            filterChain.doFilter(request, response);
            return;
        }
        User user = (User) SessionUtil.getInstance().getValue(request, Constant.USER);
        if (user != null && Constant.ADMIN.equals(user.getRole().getCode())) {
            filterChain.doFilter(request, response);
            return;
        }
        response.sendRedirect(request.getContextPath() + "/login?message=authorize");
    }

    @Override
    public void destroy() {

    }
}
