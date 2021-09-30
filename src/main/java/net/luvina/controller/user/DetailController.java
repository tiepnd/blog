package net.luvina.controller.user;

import net.luvina.service.post.PostService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/chi-tiet-bai-viet")
public class DetailController extends HttpServlet {
    @Inject
    private PostService postService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        req.setAttribute("post", postService.findById(id));
        req.getRequestDispatcher("/WEB-INF/view/user/detail.jsp").forward(req, resp);
    }
}
