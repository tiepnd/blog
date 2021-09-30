package net.luvina.controller.user;

import net.luvina.model.Category;
import net.luvina.model.Post;
import net.luvina.service.category.CategoryService;
import net.luvina.service.post.PostService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/home")
public class HomeController extends HttpServlet {
    @Inject
    private CategoryService categoryService;
    @Inject
    private PostService postService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categoryList = categoryService.findAll();
        List<Post> postList = postService.findAll();
        req.setAttribute("categoryList", categoryList);
        req.setAttribute("postList", postList);
        req.setAttribute("featuredPost", postList.get(2));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/user/home.jsp");
        requestDispatcher.forward(req, resp);
    }
}
