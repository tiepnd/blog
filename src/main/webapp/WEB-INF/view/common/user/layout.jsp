<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title><decorator:title default="Blog Home"/></title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="/static/user/assets/favicon.ico" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="<c:url  value="/static/user/css/styles.css"/> " rel="stylesheet" />
</head>
<body>
<%@include file="header.jsp"%>
<div id="content">
    <decorator:body />
</div>
<%@include file="footer.jsp"%>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="/static/user/js/scripts.js"></script>
</body>
</html>
