<%-- 
    Document   : register.jsp
    Created on : May 22, 2023, 2:20:32 AM
    Author     : LanChau
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="util.Constants"%>
<!doctype html>
<html class="no-js" lang="zxx">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Curriculum Overview</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="manifest" href="site.webmanifest">
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">

        <!-- CSS here -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/owl.carousel.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/slicknav.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/flaticon.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/progressbar_barfiller.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fontawesome-all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/themify-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/slick.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/nice-select.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">

    </head>

    <body>
        <!-- Preloader Start -->
        <jsp:include page="/user/navigator/header.jsp"/>  

 
        <main >
            <section class="slider-area slider-area2">
                <div class="slider-active">
                    <!-- Single Slider -->
                    <div style="height:100px; " >   
                        <jsp:include page="/view/navSubHeaderCurriculum.jsp"/> 
                    </div>
                </div>
            </section>
 
            <div class="courses-area section-padding40 fix">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-xl-7 col-lg-8">
                            <div class="section-tittle text-center mb-55">
                                <h2>Curriculum Overview</h2>
                            </div>
                        </div>

                    </div>
                    <c:if test="${user.roles.get(0).name eq 'System Admin' or user.roles.get(0).name  eq 'CRDD Head' or user.roles.get(0).name eq 'CRDD Staff' }">
                        <a class="btn btn-primary" href="curriculumedit?action=edit&curId=${curriculum.curriculumID}" style="color: white;margin: 10px" >Edit This Curriculum</a>

                    </c:if>
                    <form action="curriculaoverview" method="post"> 
                        <!-- message  -->
                        <c:choose>
                            <c:when test="${status eq 'update_fail'}">
                                <div class="alert alert-warning" >
                                    <strong>Fail!</strong> Fail update curriculum.
                                </div>
                            </c:when>
                            <c:when test="${status eq null}">

                            </c:when>
                            <c:otherwise >
                                <div class="alert alert-success" >
                                    <strong>Success!</strong> Successful update curriculum
                                </div>
                            </c:otherwise>
                        </c:choose>

                        <!<!-- end message  -->
                        <table class="table table-bordered">
                            <tbody>
                                <tr class="">
                                    <td style="text-align: right;">CurriculumCode:</td>
                                    <td>${curriculum.code}</td>

                                </tr>
                                <tr>
                                    <td style="text-align: right;">Name:</td>
                                    <td>${curriculum.name}</td>
                                </tr>
                                <tr> 
                                    <td style="text-align: right;">Description:</td>
                                    <td>
                                        ${curriculum.description}
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right;">Decision No <br>yyyy-MM-dd:</td>
                                    <td>${curriculum.decision.decision_no}<br> ${curriculum.decision.decision_date} </td>

                                </tr> 
                            </tbody>
                        </table>
                    </form>
                    <a href="curriculum">
                        <button class="btn btn-primary" type="submit">Back</button> 
                    </a>
                </div>
            </div>

        </main>
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