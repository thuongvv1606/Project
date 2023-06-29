
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="util.Constants"%>
<!doctype html>
<html class="no-js" lang="zxx">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Session Details</title>
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
        <style>
            .table-bordered td:first-child {
                background-color: #20c997;
                color: white;
            }
        </style>
    </head>

    <body>
        <!-- Preloader Start -->
        <jsp:include page="/user/navigator/header.jsp"/>  

 
        <main >
            <section class="slider-area slider-area2">
                <div class="slider-active">
                    <!-- Single Slider -->
                    <div style="height:60px; " >   
                        <jsp:include page="/user/navigator/navSubHeaderSyllabus.jsp"/> 
                    </div>
                </div>
            </section>
 
            <div class="courses-area section-padding40 fix">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-xl-7 col-lg-8">
                            <div class="section-tittle text-center mb-55">
                                <h2>Session Details</h2>
                            </div>
                        </div>

                    </div>
                    <c:if test="${user.roles.get(0).name eq 'System Admin' or user.roles.get(0).name  eq 'CRDD Head' or user.roles.get(0).name eq 'CRDD Staff' }">
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}${Constants.URL_SESSIONEDIT}?sessionId=${listSession.getSession_id()}" style="color: white;margin: 10px" >Edit This Syllabus</a>

                    </c:if>
                    <form action="${pageContext.request.contextPath}${Constants.URL_SESSIONDETAIL}" method="GET"> 
                        <!<!-- end message  -->
                        <table class="table table-bordered">
                            <tbody>
                                <tr class="">
                                    <td style="text-align: right;">Session ID:</td>
                                    <td>${listSession.getSession_id()}</td>

                                </tr>
                                <tr>
                                    <td style="text-align: right;">Name:</td>
                                    <td>${listSession.getName()}</td>
                                </tr>
                                <tr> 
                                    <td style="text-align: right;">Details</td>
                                    <td>
                                        ${listSession.getDetails()}
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right;">Learning type</td>
                                    <td>${listSession.getLearning_type()} </td>
                                </tr> 
                                <tr> 
                                    <td style="text-align: right;">Student materials</td>
                                    <td>
                                        ${listSession.getStudent_materials()}
                                    </td>
                                </tr>
                                <tr> 
                                    <td style="text-align: right;">Student task</td>
                                    <td>
                                        ${listSession.getStudent_task()}
                                    </td>
                                </tr>
                                <tr> 
                                    <td style="text-align: right;">Syllabus id</td>
                                    <td>
                                        ${listSession.getSyllabus_id()}
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                    <a href="syllabus">
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