<%-- 
    Document   : contentgroupDetail
    Created on : Jun 8, 2023, 3:01:05 PM
    Author     : TT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Content Group Detail</title>
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- CSS here -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/owl.carousel.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/slicknav.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/flaticon.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/progressbar_barfiller.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/gijgo.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/animate.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/animated-headline.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/magnific-popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fontawesome-all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/themify-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/slick.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/nice-select.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
        <style>
            .table-bordered td:first-child {
                text-align: right;
                width: 150px;
                background-color: #20c997;
                color: white;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/user/navigator/header.jsp"/>  
        <div class="row" style="margin: 20px;">
            <div class="card-header" style="background-color: #ffc107; color: white;">Content Group Detail</div>
            <div class="card-body" style="border: 1px solid gray; border-radius: 5px; box-shadow: 10px 10px 5px #aaaaaa;">
                <form action="contentgroupDetail" method="POST">
                    <div class="row gx-3 mb-3">
                        <div class="col-md-6">
                            <label class="small mb-1">Content Group Name</label>
                            <input class="form-control" name="cid" type="text" value="${sessionScope.cg.getId()}" readonly="">
                            <label class="small mb-1">Content Group Name</label>
                            <input class="form-control" name="cname" type="text" value="${sessionScope.cg.getName()}" readonly="">
                            <label class="small mb-1">Content Group Type</label><br>
                            <input class="form-control" name="ctype" type="text" value="${sessionScope.cg.getType()}" readonly="">
                            <label class="small mb-1">Curriculum</label><br>
                            <input class="form-control" name="cur_id" type="text" value="${sessionScope.curId}" readonly="">
                            <p style="color: red;">${messFail}</p> 
                            <p style="color: green;">${messSuccess}</p>
                        </div>
                        <div class="col-md-6">
                            <label class="small mb-1">Subject Code</label><br>
                            <c:forEach items="${sessionScope.cg.getsList()}" var="s">
                                ${s.getCode()}<br>
                            </c:forEach>
                        </div>
                    </div>
                </form>
                <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                    <a href="contentgroup?action=edit&&contentgroupId=${sessionScope.cg.getId()}">
                        <button class="btn btn-primary" name="save" type="submit" style="margin-bottom: 5px;">Edit Elective</button>
                    </a>
                </c:if>
                <a href="contentgroup?action=list&&curId=${sessionScope.curId}">
                    <button class="btn btn-primary" type="submit">Back</button> 
                </a>
            </div>
        </div>
        <jsp:include page="/user/navigator/footer.jsp"/>      
        <!-- Scroll Up -->
        <div id="back-top" >
            <a title="Go to Top" href="#"> <i class="fas fa-level-up-alt"></i></a>
        </div>
    </body>
</html>
