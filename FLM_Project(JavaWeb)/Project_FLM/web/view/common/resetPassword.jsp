<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="util.Constants"%>
<!doctype html>
<html class="no-js" lang="zxx">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Reset Password</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="manifest" href="site.webmanifest">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">

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
        <!-- Preloader Start-->
        <jsp:include page="/user/navigator/header.jsp"/>

        <main class="login-body" data-vide-bg="${pageContext.request.contextPath}/assets/img/img_6385-min.jpg">
            <div class="form-default">
                <div class="login-form">
                    <div class="logo-login">
                        <a href="index.html"><img src="${pageContext.request.contextPath}/assets/img/logo/loder.png" alt=""></a>
                    </div>
                    <h2 class="title-head">Reset <span>Password</span></h2>
                    <h2 style="color:red; text-align: center;">${mess}</h2>

                    <%-- Form g?i email --%>
                    <form id="emailForm" action="${pageContext.request.contextPath}${Constants.URL_RESETPASSWORDG}" method="POST">
                        <div class="form-input">
                            <label>Input Email</label>
                            <input name="email" type="email" required="" value="${email}">
                        </div>
                        <div class="form-input pt-30" style="text-align: center">
                            <input type="submit" name="submit" value="Send">
                        </div>
                    </form>

                    <%-- Form g?i phone --%>
                    <form id="phoneForm" action="${pageContext.request.contextPath}${Constants.URL_RESETPASSWORDP}" method="POST" style="display:none;">
                        <div class="form-input">
                            <label>Input Phone Number</label>
                            <input name="phone" type="text" required="" value="${email}">
                        </div>
                        <div class="form-input pt-30" style="text-align: center">
                            <input type="submit" name="submit" value="Send">
                        </div>
                    </form>
                    <c:if test="${email != null || phone != null}">
                        <form action="${pageContext.request.contextPath}${Constants.URL_VERIFY}" method="POST">
                            <input name="email" type="text" hidden="" value="${email}">
                            <div class="form-input">
                                <label>Input verify</label>
                                <input name="code" type="number" pattern="${Constants.HTML_REGEX_VERIFY}" 
                                       title="${Constants.HTML_TITLE_OF_REGEX__VERIFY}" required="" 
                                       value="${code}">
                            </div>
                            <div class="form-input pt-30" style="text-align: center">
                                <input type="submit" name="submit" value="Submit">
                            </div>
                        </form>
                    </c:if>
                    <div style="display: flex">
                        <%-- Button chuy?n sang form g?i phone --%>
                        <div class="form-input pt-30" style="text-align: center">
                            <button onclick="showPhoneForm()" style="color: black">Send Phone</button>
                        </div>

                        <%-- Button chuy?n sang form g?i email --%>
                        <div class="form-input pt-30" style="text-align: center">
                            <button onclick="showEmailForm()" style="color: black">Send Email</button>
                        </div>
                    </div>
                </div>
            </div>
        </main>


        <jsp:include page="/user/navigator/footer.jsp"/>  

        <script src="${pageContext.request.contextPath}/assets/js/vendor/modernizr-3.5.0.min.js"></script>
        <!-- Jquery, Popper, Bootstrap -->
        <script src="${pageContext.request.contextPath}/assets/js/vendor/jquery-1.12.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
        <!-- Jquery Mobile Menu -->
        <script src="${pageContext.request.contextPath}/assets/js/jquery.slicknav.min.js"></script>

        <!-- Video bg -->
        <script src="${pageContext.request.contextPath}/assets/js/jquery.vide.js"></script>

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
        <script type="text/javascript">
                                function showPhoneForm() {
                                    document.getElementById("emailForm").style.display = "none";
                                    document.getElementById("phoneForm").style.display = "block";
                                }

                                function showEmailForm() {
                                    document.getElementById("phoneForm").style.display = "none";
                                    document.getElementById("emailForm").style.display = "block";
                                }
        </script>
    </body>
</html>