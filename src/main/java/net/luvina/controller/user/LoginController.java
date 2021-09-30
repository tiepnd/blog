package net.luvina.controller.user;

import net.luvina.model.User;
import net.luvina.service.user.UserService;
import net.luvina.util.Constant;
import net.luvina.util.MessageUtil;
import net.luvina.util.RequestUtil;
import net.luvina.util.SessionUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/login")
public class LoginController extends HttpServlet {
    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", MessageUtil.getErrorMessage(req.getParameter("message")));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/user/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User model = RequestUtil.toModel(User.class, req);
        User user = userService.authenticate(model);
        if (user != null) {
            SessionUtil.getInstance().putValue(req, Constant.USER, user);
            if ("ADMIN".equals(user.getRole().getCode())) {
                    resp.sendRedirect(req.getContextPath() + "/admin-post?currentPage=1&sortBy=&limit=5&type=list");
            }
            if ("USER".equals(user.getRole().getCode())) {
                    resp.sendRedirect(req.getContextPath() + "/home");
            }
        } else {
            req.setAttribute("message", MessageUtil.getErrorMessage("authenticate"));
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/user/login.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
