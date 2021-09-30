<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Page header with logo and tagline-->
<header class="py-5 bg-light border-bottom mb-4">
    <div class="container">
        <div class="text-center my-5">
            <h1 class="fw-bolder">Welcome to ${user.fullName}'blog !</h1>
            <p class="lead mb-0">A Bootstrap 5 starter layout for your next blog homepage</p>
        </div>
    </div>
</header>
<!-- Page content-->
<div class="container">
    <div class="row">
        <!-- Blog entries-->
        <div class="col-lg-8">
            <!-- Featured blog post-->
            <div class="card mb-4">
                <a href="#"><img class="card-img-top" src="https://dummyimage.com/850x350/dee2e6/6c757d.jpg" alt="..."/></a>
                <div class="card-body">
                    <div class="small text-muted">January 1, 2021</div>
                    <h2 class="card-title"><c:out value="${featuredPost.getTitle()}"/></h2>
                    <p class="card-text"><c:out value="${featuredPost.getShortDescription()}"/></p>
                    <a class="btn btn-primary" href="chi-tiet-bai-viet?id=${featuredPost.getId()}">Read more →</a>
                </div>
            </div>
            <!-- Nested row for non-featured blog posts-->
            <div class="row">
                <div class="col-lg-6">
                    <!-- Blog post-->
                    <c:forEach items="${postList}" end="${postList.size()/2 - 1}" var="item">
                        <!-- Blog post-->
                        <div class="card mb-4">
                            <a href="#"><img class="card-img-top" src="https://dummyimage.com/700x350/dee2e6/6c757d.jpg"
                                             alt="..."/></a>
                            <div class="card-body">
                                <div class="small text-muted">January 1, 2021</div>
                                <h2 class="card-title h4"><c:out value="${item.getTitle()}"/></h2>
                                <p class="card-text"><c:out value="${item.getShortDescription()}"/></p>
                                <a class="btn btn-primary" href="chi-tiet-bai-viet?id=${item.getId()}">Read more →</a>
                            </div>
                        </div>
                    </c:forEach>

                </div>
                <div class="col-lg-6">
                    <!--List Blog post-->
                    <c:forEach items="${postList}" begin="${postList.size()/2}" var="item">
                        <!-- Blog post-->
                        <div class="card mb-4">
                            <a href="#"><img class="card-img-top" src="https://dummyimage.com/700x350/dee2e6/6c757d.jpg"
                                             alt="..."/></a>
                            <div class="card-body">
                                <div class="small text-muted">January 1, 2021</div>
                                <h2 class="card-title h4"><c:out value="${item.getTitle()}"/></h2>
                                <p class="card-text"><c:out value="${item.getShortDescription()}"/></p>
                                <a class="btn btn-primary" href="chi-tiet-bai-viet?id=${item.getId()}">Read more →</a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <!-- Pagination-->
            <nav aria-label="Pagination">
                <hr class="my-0"/>
                <ul class="pagination justify-content-center my-4">
                    <li class="page-item disabled"><a class="page-link" href="#" tabindex="-1" aria-disabled="true">Newer</a>
                    </li>
                    <li class="page-item active" aria-current="page"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item disabled"><a class="page-link" href="#">...</a></li>
                    <li class="page-item"><a class="page-link" href="#">15</a></li>
                    <li class="page-item"><a class="page-link" href="#">Older</a></li>
                </ul>
            </nav>
        </div>
        <!-- Side widgets-->
        <div class="col-lg-4">
            <!-- Search widget-->
            <div class="card mb-4">
                <div class="card-header">Search</div>
                <div class="card-body">
                    <div class="input-group">
                        <input class="form-control" type="text" placeholder="Enter search term..."
                               aria-label="Enter search term..." aria-describedby="button-search"/>
                        <button class="btn btn-primary" id="button-search" type="button">Go!</button>
                    </div>
                </div>
            </div>
            <!-- Categories widget-->
            <div class="card mb-4">
                <div class="card-header">Categories</div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm-6">
                            <ul class="list-unstyled mb-0">
                                <c:forEach items="${categoryList}" end="${categoryList.size()/2 - 1}" var="item">
                                    <li><a href="#"><c:out value="${item.getName()}"/></a></li>
                                </c:forEach>

                            </ul>
                        </div>
                        <div class="col-sm-6">
                            <ul class="list-unstyled mb-0">
                                <c:forEach items="${categoryList}" begin="${categoryList.size()/2}" var="item">
                                    <li><a href="#"><c:out value="${item.getName()}"/></a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Side widget-->
            <div class="card mb-4">
                <div class="card-header">Side Widget</div>
                <div class="card-body">You can put anything you want inside of these side widgets. They are easy to use,
                    and feature the Bootstrap 5 card component!
                </div>
            </div>
        </div>
    </div>
</div>