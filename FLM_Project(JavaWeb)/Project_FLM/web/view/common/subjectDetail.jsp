<%-- 
    Document   : subjectDetail
    Created on : Jun 10, 2023, 10:21:30 PM
    Author     : DungNT
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="util.Constants"%>
<!DOCTYPE html>
<html class="no-js" lang="zxx">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Subject Detail</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="manifest" href="site.webmanifest">
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">

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
    </head>
    <body>
        <!-- Preloader Start -->
        <jsp:include page="/user/navigator/header.jsp"/>  
        <jsp:include page="/view/navSubHeaderCurriculum.jsp"/> 
        <main >
            <div class="courses-area section-padding40 fix">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-xl-7 col-lg-8">
                            <div class="section-tittle text-center mb-55">
                                <h2>Detail Subject</h2>
                            </div>
                        </div> 
                    </div>
                    <div class="row">
                        <!-- Your Profile Views Chart -->
                        <div class="col-lg-12 m-b30">
                            <div class="widget-box">
                                <div class="widget-inner">
                                    <form class="edit-profile m-b30" action="${pageContext.request.contextPath}${Constants.URL_EDITSUBJECT}" method="post">
                                        <div class="row">

                                            <div class="form-group col-12">
                                                <tr>
                                                    <td>Subject: </td>
                                                    <td>
                                                        <b>${subject.code} -- ${subject.name}</b>
                                                    </td>
                                                </tr>
                                            </div>
                                            <div class="form-group col-12">
                                                <tr>
                                                    <td>Curriculum: </td>
                                                    <td>
                                                        <b>${curriculum.code} -- ${curriculum.name}
                                                        </b>
                                                    </td>
                                                </tr>

                                            </div>
                                            <div class="form-group col-12">
                                                <tr>
                                                    <td>Semester: </td>
                                                    <td>
                                                        <b>${subject.semester}</b>
                                                    </td>
                                                </tr>
                                            </div> 
                                            <div class="form-group col-12">
                                                <tr>
                                                    <td>No_Credit: </td>
                                                    <td>
                                                        <b>${subject.no_credit}</b>
                                                    </td>
                                                </tr>
                                            </div>         
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!-- Your Profile Views Chart END-->
                    </div>
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
