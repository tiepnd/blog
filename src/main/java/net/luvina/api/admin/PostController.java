package net.luvina.api.admin;

import net.luvina.model.Post;
import net.luvina.model.User;
import net.luvina.service.post.PostService;
import net.luvina.util.Constant;
import net.luvina.util.JsonUtil;
import net.luvina.util.SessionUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/api/admin/posts")
public class PostController extends HttpServlet {
    @Inject
    private PostService postService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        Post input = JsonUtil.readValue(req.getReader(), Post.class);
        User user = (User) SessionUtil.getInstance().getValue(req, Constant.USER);
        input.setCreatedBy(user == null ? "tiepnd" : user.getUserName());
        Post output = postService.insert(input);
        JsonUtil.writeValue(resp.getOutputStream(), output);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        Post input = JsonUtil.readValue(req.getReader(), Post.class);
        User user = (User) SessionUtil.getInstance().getValue(req, Constant.USER);
        input.setModifiedBy(user == null ? "tiepnd" : user.getUserName());
        boolean output = postService.update(input);
        JsonUtil.writeValue(resp.getOutputStream(), output);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        Post input = JsonUtil.readValue(req.getReader(), Post.class);
        boolean result = postService.delete(input.getIds());
        JsonUtil.writeValue(resp.getOutputStream(), result);
    }
}

