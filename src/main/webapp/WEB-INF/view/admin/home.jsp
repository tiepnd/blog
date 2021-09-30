<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-fluid px-4">
    <h1 class="mt-4">Dashboard</h1>
    <div class="card mb-4">
        <div class="card-header">
            <div class="d-flex justify-content-between">
                <div><i class="fas fa-table me-1"></i>
                    List post
                </div>

                <div class="d-flex justify-content-end">
                    <a class="text-light text-decoration-none" href="admin-post?type=update">
                        <button class="btn btn-primary btn-sm rounded-0 mx-3" type="button" data-toggle="tooltip"
                                data-placement="top" title="Add"><i
                                class="fa fa-table"></i></button>
                    </a>
                    <button class="btn btn-danger btn-sm rounded-0" type="button" data-toggle="tooltip"
                            data-placement="top"
                            title="Delete" id="btnDelete"><i class="fa fa-trash"></i></button>
                </div>
            </div>

        </div>
        <div class="card-body" style="height: 68.8vh; overflow: scroll">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th><input class="form-check-input" type="checkbox" value=""
                               id="checkAll"></th>
                    <th>Title</th>
                    <th>Thumbnail</th>
                    <th>Short Description</th>
                    <th>Content</th>
                    <th>Category Id</th>
                    <th>Created Date</th>
                    <th>Created By</th>
                    <th>
                        Edit
                    </th>
                </tr>
                </thead>
                <tbody>
                <!-- Blog post-->
                <c:forEach items="${model.listModel}" var="item">
                    <!-- Blog post-->
                    <tr>
                        <td><input class="form-check-input" type="checkbox"
                                   value="${item.id}"></td>
                        <td><c:out value="${item.title}"/></td>
                        <td><c:out value="${item.thumbnail}"/></td>
                        <td><c:out value="${item.shortDescription}"/></td>
                        <td><c:out value="${item.content}"/></td>
                        <td><c:out value="${item.categoryId}"/></td>
                        <td><c:out value="${item.createdDate}"/></td>
                        <td><c:out value="${item.createdBy}"/></td>
                        <td>
                            <a class="text-light text-decoration-none"
                               href="admin-post?type=update&id=${item.id}">
                                <button class="btn btn-success btn-sm rounded-0" type="button" data-toggle="tooltip"
                                        data-placement="top" title="Edit"><i
                                        class="fa fa-edit"></i></button>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="d-flex justify-content-between">
                <div>
                    <form action="<c:url value = "/admin-post" />" method="get" name="postsForm" id="postsForm">
                        <input type="hidden" name="currentPage" id="currentPage"/>
                        <input type="hidden" name="sortBy" id="sortBy"/>
                        <input type="hidden" name="type" value="list"/>
                        <label for="limit">Row per page: </label>
                        <select class="form-select d-inline w-auto" id="limit" name="limit"
                                onchange="onSubmitSelectBox()">
                            <option selected value="5">5
                            </option>
                            <option <c:if test="${model.limit == 20}">
                                selected
                            </c:if> value="20">20
                            </option>
                            <option <c:if test="${model.limit == 50}">
                                selected
                            </c:if> value="50">50
                            </option>
                            <option <c:if test="${model.limit == 100}">
                                selected
                            </c:if> value="100">100
                            </option>
                        </select>
                    </form>
                </div>
                <div>
                    <nav aria-label="Page navigation">
                        <ul class="pagination" id="pagination"></ul>
                    </nav>
                </div>

            </div>
            <script type="text/javascript">
                var currentPage = ${model.currentPage};
                var sortBy = '${model.sortBy}';

                var onSubmitSelectBox = () => {
                    $('#currentPage').val(currentPage);
                    $('#sortBy').val(sortBy);
                    $('#postsForm').submit();
                }

                var onSubmit = (event, page) => {
                    if (currentPage != page) {
                        $('#currentPage').val(page);
                        $('#sortBy').val(sortBy);
                        $('#postsForm').submit();
                    }
                }

                $(function () {
                    window.pagObj = $('#pagination').twbsPagination({
                        totalPages: ${model.totalPages},
                        visiblePages: 5,
                        startPage: currentPage,
                        onPageClick: function (event, page) {
                            onSubmit(event, page);
                        }
                    });
                    $("#checkAll").click(function () {
                        $('input:checkbox').not(this).prop('checked', this.checked);
                    });

                    $('#btnDelete').click(async function (e) {
                        e.preventDefault();
                        var ids = $('input:checkbox:checked').not($("#checkAll")).map(function () {
                            return $(this).val();
                        }).get();
                        console.log(ids)
                        const response = await fetch('http://localhost:8080/blog_war/api/admin/posts', {
                            method: 'DELETE',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify({
                                "ids": ids,
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
                    });
                });
            </script>
        </div>
    </div>
</div>