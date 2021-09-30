<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <c:if test="${not empty model.id}">
        <title>Edit</title>
    </c:if>
    <c:if test="${empty model.id}">
        <title>Add</title>
    </c:if>
</head>
<div>
    <form class="p-5">
        <div class="row mb-3">
            <div class="form-group col-md-6">
                <label for="title">Title</label>
                <input name="title" value="${model.title}" type="text" class="form-control mt-2" id="title"
                       placeholder="Title">
            </div>
            <div class="form-group col-md-6">
                <label for="categoryId">Category</label>
                <select id="categoryId" class="form-control mt-2">
                    <option selected>Choose...</option>
                    <c:forEach items="${model.listCategory}" var="category">
                        <option value="${category.id}"
                                <c:if test="${category.id == model.categoryId}">selected</c:if> >${category.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group mb-3">
            <label for="thumbnail">Avatar</label>
            <input name="thumbnail" value="<c:out value="${model.thumbnail}"/>" type="text" class="form-control mt-2"
                   id="thumbnail" placeholder="Avatar">
        </div>
        <div class="form-group mb-3">
            <label for="shortDescription">Short description</label>
            <input name="shortDescription" value="<c:out value="${model.shortDescription}"/>" type="text"
                   class="form-control mt-2" id="shortDescription" placeholder="Short description">
        </div>
        <div class="form-group mb-3">
            <label for="editor">Content</label>
            <textarea name="editor" class="form-control mt-2" id="editor" placeholder="Content"><c:out
                    value="${model.content}"/></textarea>


        </div>
        <button type="button" class="btn btn-success" id="btnUpdate">
            <c:if test="${not empty model.id}">
                Edit
            </c:if>
            <c:if test="${empty model.id}">
                Add
            </c:if>
        </button>
        <button type="button" class="btn btn-info" id="btnBack">
            <a class="text-decoration-none text-light"
               href="admin-post?currentPage=1&sortBy=&limit=5&type=list">Back</a>
        </button>
    </form>
</div>

<script>
    var id = '${model.id}';
    let editor;
    $(document).ready(function () {
        editor = CKEDITOR.replace('editor');
        $("#btnUpdate").click((e) => {
            e.preventDefault();
            if (id != '') {
                edit(e);
            } else {
                add(e);
            }
        });
    });
    const add = async (e) => {
        console.log(editor.getData());
        e.preventDefault();
        const response = await fetch('http://localhost:8080/blog_war/api/admin/posts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                "title": $('#title').val(),
                "thumbnail": $('#thumbnail').val(),
                "shortDescription": $('#shortDescription').val(),
                "content": editor.getData(),
                "categoryId": $('#categoryId').val()
            })
        }).then(function (response) {
            // const post = await response.json();
            if (response.ok) {
                window.location.href = "admin-post?currentPage=1&sortBy=&limit=5&type=list";
            } else {
                throw new Error('Something went wrong');
            }
        }).catch(function (error) {
            console.log("error" + error);
        });
    }
    const edit = async (e) => {
        console.log(editor.getData());
        e.preventDefault();
        const response = await fetch('http://localhost:8080/blog_war/api/admin/posts', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                "id": id,
                "title": $('#title').val(),
                "thumbnail": $('#thumbnail').val(),
                "shortDescription": $('#shortDescription').val(),
                "content": editor.getData(),
                "categoryId": $('#categoryId').val()
            })
        }).then(function (response) {
            // const post = await response.json();
            if (response.ok) {
                window.location.href = "admin-post?currentPage=1&sortBy=&limit=5&type=list";
            } else {
                throw new Error('Something went wrong');
            }
        }).catch(function (error) {
            console.log("error" + error);
        });
    }

</script>