package net.luvina.controller.admin;

import net.luvina.model.Post;
import net.luvina.paging.PageRequest;
import net.luvina.paging.Pageable;
import net.luvina.paging.SortRequest;
import net.luvina.paging.Sortable;
import net.luvina.service.category.CategoryService;
import net.luvina.service.post.PostService;
import net.luvina.util.RequestUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/admin-post")
public class PostController extends HttpServlet {
    @Inject
    PostService postService;

    @Inject
    CategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post modelInput = RequestUtil.toModel(Post.class, req);
        Post modelOutput = new Post();
        RequestDispatcher requestDispatcher = null;

        try {
            switch (modelInput.getType()) {
                case "list":
                    modelOutput = getModelList(modelInput);
                    requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/admin/home.jsp");
                    break;
                case "update":
                    modelOutput = getModelUpdate(modelInput);
                    requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/admin/post/update.jsp");
                    break;
            }
        } catch (Exception exception) {
            System.out.println("PostController:doGet: " + exception.getMessage());
            requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/admin/500.jsp");
        }
        req.setAttribute("model", modelOutput);
        requestDispatcher.forward(req, resp);
    }

    private Post getModelList(Post modelInput) {
        Long count = postService.count();
        if (count == 0l) {
            return modelInput;
        }
        Pageable pageRequest = new PageRequest(modelInput.getCurrentPage(), modelInput.getLimit());
        Sortable sortRequest = new SortRequest(modelInput.getSortBy());

        List<Post> posts = postService.findAll(pageRequest, sortRequest);

        modelInput.setListModel(posts);
        modelInput.setTotalPages((int) Math.ceil((double) count / modelInput.getLimit()));
        return modelInput;
    }

    private Post getModelUpdate(Post modelInput) {
        Post modelOutput = new Post();
        if (modelInput.getId() != null) {
            modelOutput = postService.findById(modelInput.getId());
        }
        modelOutput.setListCategory(categoryService.findAll());
        return modelOutput;
    }
}
