<%@page import="util.Constants"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="zxx">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>FLM Homepage</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="manifest" href="site.webmanifest">
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">

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

    </head>

    <body>
        <!-- ? Preloader Start -->
        <div id="preloader-active">
            <div class="preloader d-flex align-items-center justify-content-center">
                <div class="preloader-inner position-relative">
                    <div class="preloader-circle"></div>
                    <div class="preloader-img pere-text">
                        <img src="${pageContext.request.contextPath}/assets/img/logo/loder.png" alt="">
                    </div>
                </div>
            </div>
        </div>
        <!-- Preloader Start -->
        <jsp:include page="/user/navigator/header.jsp"/> 
        <main>
            <!--? slider Area Start-->
            <section class="slider-area ">
                <div class="slider-active">
                    <!-- Single Slider -->
                    <div class="single-slider slider-height d-flex align-items-center">
                        <div class="container">
                            <div class="row">
                                <c:if test="${sessionScope.user == null}">
                                    <div class="col-xl-6 col-lg-7 col-md-12">
                                        <div class="hero__caption">
                                            <h1 data-animation="fadeInLeft" data-delay="0.2s">FPT Education Learning Materials</h1>
                                            <p data-animation="fadeInLeft" data-delay="0.4s"></p>
                                            <a href="login" class="btn hero-btn" data-animation="fadeInLeft" data-delay="0.7s">Join for Free</a>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${sessionScope.user != null}">
                                    <div class="container d-flex flex-row align-items-flex-start p-4">
                                        <c:if test="${sessionScope.user.roles == null}">
                                            <div class="content-wraper features">
                                                <h4>Guest features</h4>
                                                <a href="curriculum">
                                                    <p>View Curriculum</p></a>
                                                <a href="${pageContext.request.contextPath}${Constants.URL_SUBJECTPREDECESSOER}"> <p>Subject Predecessors</p></a>
                                                <a href="${pageContext.request.contextPath}${Constants.URL_SUBJECTSUCCESSOER}"> <p>Subject Successors</p></a>
                                            </div>
                                        </c:if>
                                        <c:forEach var="item" items="${sessionScope.user.roles}">
                                            <c:choose>
                                                <c:when test="${item.id == 7}">
                                                    <div class="content-wraper features">
                                                        <h4>Student features</h4>
                                                        <a href="curriculum"><p>View Curriculum</p></a>
                                                        <a href="#"><p>View Training Syllabus</p></a>
                                                        <a href="${pageContext.request.contextPath}${Constants.URL_SUBJECTPREDECESSOER}"><p>Subject Predecessors</p></a>
                                                        <a href="${pageContext.request.contextPath}${Constants.URL_SUBJECTSUCCESSOER}"><p>Subject Successors</p></a>
                                                    </div> 
                                                </c:when> 
                                                <c:when test="${item.id == 6}">
                                                    <div class="content-wraper features">
                                                        <h4>Teacher features</h4>
                                                        <a href="curriculum"><p>View Curriculum</p></a>
                                                        <a href="#"><p>View Training Syllabus</p></a>
                                                        <a href="${pageContext.request.contextPath}${Constants.URL_SUBJECTPREDECESSOER}"><p>Subject Predecessors</p></a>
                                                        <a href="${pageContext.request.contextPath}${Constants.URL_SUBJECTSUCCESSOER}"><p>Subject Successors</p></a>
                                                    </div>
                                                </c:when> 
                                                <c:when test="${item.id == 1}">
                                                    <div class="content-wraper features">
                                                        <h4>Admin features</h4>
                                                        <a href="${pageContext.request.contextPath}/settingList"><p>System settings</p></a>
                                                        <a href="${pageContext.request.contextPath}/permission"><p>Role Permissions</p></a>
                                                        <a href="account"><p>User management</p></a>
                                                    </div>
                                                </c:when> 
                                                <c:when test="${item.id == 5}">
                                                    <div class="content-wraper features">
                                                        <h4>Reviewer features</h4>
                                                        <a href="#"><p>Review Syslabus</p></a>
                                                    </div>
                                                </c:when> 
                                                <c:when test="${item.id == 4}">
                                                    <div class="content-wraper features">
                                                        <h4>Designer features</h4>
                                                        <a href="#"><p>Design Syslabus</p></a>
                                                    </div>
                                                </c:when>
                                                <c:when test="${item.id == 3}">
                                                    <div class="content-wraper features">
                                                        <h4>CRDD staff features</h4>
                                                        <a href="curriculum"><p>Manage Curricula</p></a>
                                                        <a href="${pageContext.request.contextPath}${Constants.URL_SUBJECTLIST}"><p>Manage Subjects</p></a>
                                                        <a href="#"><p>Manage Syllabus</p></a>
                                                        <a href="${pageContext.request.contextPath}${Constants.URL_DECISIONCONTROLLER}"><p>Material Decisions</p></a>
                                                    </div>
                                                </c:when>
                                                <c:when test="${item.id == 2}">
                                                    <div class="content-wraper features">
                                                        <h4>CRDD head features</h4>
                                                        <a href="curriculum"><p>Assign, Approve, Disapprove Curricula</p></a>
                                                        <a href="#"><p>Approve,Disapprove Syllabi</p></a> 
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                </c:otherwise>
                                            </c:choose> 
                                        </c:forEach>  
                                    </div>
                                </c:if>
                            </div>
                        </div>          
                    </div>
                </div>
            </section>
            <!-- ? services-area -->


            <!-- Courses area End -->
            <!--? About Area-1 Start -->

        </section>
        <!-- About Area End -->
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