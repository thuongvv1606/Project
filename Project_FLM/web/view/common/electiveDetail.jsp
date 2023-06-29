<%-- 
    Document   : electiveAdd
    Created on : Jun 5, 2023, 11:35:57 PM
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
        <title>Elective Detail</title>
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
            <div class="card-header" style="background-color: #ffc107; color: white;">Elective Detail</div>
            <div class="card-body" style="border: 1px solid gray; border-radius: 5px; box-shadow: 10px 10px 5px #aaaaaa;">
                <form action="electiveDetail" method="POST">
                    <div class="row gx-3 mb-3">
                        <div class="col-md-6">
                            <label class="small mb-1">Elective ID</label>
                            <input class="form-control" name="eid" type="text" value="${e.getId()}" readonly="">
                            <label class="small mb-1">Elective Code</label>
                            <input class="form-control" name="ename" type="text" value="${e.getName()}" readonly="">
                            <label class="small mb-1">Elective Name</label><br>
                            <input class="form-control" name="edes" type="text" value="${e.getDescription()}" readonly="">
                            <label class="small mb-1">Curriculum ID</label>
                            <input class="form-control" name="cid" type="text" value="${sessionScope.curId}" readonly="">
                            <label class="small mb-1">Status</label>
                            <input class="form-control" name="status" type="text" value="${e.isActive() ? "Active" : "Dormant"}" readonly="">
                        </div>
                        <div class="col-md-6">
                            <label class="small mb-1">Subject Code</label><br>
                            <c:if test="${sList.size() != 0}">
                                <c:forEach items="${sList}" var="s">
                                    ${s.getCode()}<br>
                                </c:forEach>
                                </c:if>
                                    <c:if test="${sList.size() == 0}">None</c:if>
                                    </div>
                                </div>
                            </form>
                        <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                            <a href="elective?action=edit&&electiveId=${e.getId()}">
                                <input class="btn btn-primary" type="submit" name="btnEdit" value="Edit Elective">
                            </a> 
                        </c:if>
                        <a href="elective?action=list&&curId=${sessionScope.curId}">
                            <button class="btn btn-primary" type="submit">Back</button> 
                        </a>
                    </div>
                </div>
                <jsp:include page="/user/navigator/footer.jsp"/>      
                <!-- Scroll Up -->
                <div id="back-top" >
                    <a title="Go to Top" href="#"> <i class="fas fa-level-up-alt"></i></a>
                </div>

                <!-- JS here -->
                <script src="${pageContext.request.contextPath}/assets/js/vendor/modernizr-3.5.0.min.js"></script>
                <!-- Jquery, Popper, Bootstrap -->
                <script src="${pageContext.request.contextPath}/assets/js/vendor/jquery-1.12.4.min.js"></script>
                <script src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
                <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
                <!-- Jquery Mobile Menu -->
                <script src="${pageContext.request.contextPath}/assets/js/jquery.slicknav.min.js"></script>

                <!-- Jquery Slick , Owl-Carousel Plugins -->
                <script src="${pageContext.request.contextPath}/assets/js/owl.carousel.min.js"></script>
                <script src="${pageContext.request.contextPath}/assets/js/slick.min.js"></script>
                <!-- One Page, Animated-HeadLin -->
                <script src="${pageContext.request.contextPath}/assets/js/wow.min.js"></script>
                <script src="${pageContext.request.contextPath}/assets/js/animated.headline.js"></script>
                <script src="${pageContext.request.contextPath}/assets/js/jquery.magnific-popup.js"></script>

                <!-- Date Picker -->
                <script src="${pageContext.request.contextPath}/assets/js/gijgo.min.js"></script>
                <!-- Nice-select, sticky -->
                <script src="${pageContext.request.contextPath}/assets/js/jquery.nice-select.min.js"></script>
                <script src="${pageContext.request.contextPath}/assets/js/jquery.sticky.js"></script>
                <!-- Progress -->
                <script src="${pageContext.request.contextPath}/assets/js/jquery.barfiller.js"></script>

                <!-- counter , waypoint,Hover Direction -->
                <script src="${pageContext.request.contextPath}/assets/js/jquery.counterup.min.js"></script>
                <script src="${pageContext.request.contextPath}/assets/js/waypoints.min.js"></script>
                <script src="${pageContext.request.contextPath}/assets/js/jquery.countdown.min.js"></script>
                <script src="${pageContext.request.contextPath}/assets/js/hover-direction-snake.min.js"></script>

                <!-- contact js -->
                <script src="${pageContext.request.contextPath}/assets/js/contact.js"></script>
                <script src="${pageContext.request.contextPath}/assets/js/jquery.form.js"></script>
                <script src="${pageContext.request.contextPath}/assets/js/jquery.validate.min.js"></script>
                <script src="${pageContext.request.contextPath}/assets/js/mail-script.js"></script>
                <script src="${pageContext.request.contextPath}/assets/js/jquery.ajaxchimp.min.js"></script>

                <!-- Jquery Plugins, main Jquery -->	
                <script src="${pageContext.request.contextPath}/assets/js/plugins.js"></script>
                <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
            </body>
        </html>
