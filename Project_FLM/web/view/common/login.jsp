<%@page import="util.Constants"%>
<!doctype html>
<html class="no-js" lang="zxx">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Login</title>
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
            <!-- Login Admin -->
               <form class="form-default" action="${pageContext.request.contextPath}${Constants.URL_LOGIN}" method="POST">

                <div class="login-form">
                    <!-- logo-login -->
                    <div class="logo-login">
                        <a href="home"><img src="${pageContext.request.contextPath}/assets/img/logo/loder.png" alt=""></a>
                    </div>
                    <h2 class="title-head">Login to your <span>Account</span></h2>
                    <h2 style="color:red; text-align: center;">${mess}</h2>
                    <div class="form-input">
                        <label>User Name</label>
                        <input name="userName" type="text" required=""
                               placeholder="abc2002"
                               class="form-control" value="${userName}">
                    </div>
                    <div class="form-input">
                        <label>Your Password</label>
                        <input name="password" type="password" class="form-control" 
                               pattern="${Constants.HTML_REGEX_PASSWORD}" 
                               placeholder=""
                               title="${Constants.HTML_TITLE_OF_REGEX__PASSWORD}"  
                               class="form-control"
                               required="" value="${password}">
                    </div>
                    <div class="form-input pt-30">
                        <input type="submit" name="submit" value="login">
                    </div>
                    <a href="${pageContext.request.contextPath}${Constants.URL_RESETPASSWORDG}" class="ml-auto">Forgot Password?</a>
                    <!-- Forget Password -->
                    <a href="${pageContext.request.contextPath}${Constants.URL_REGISTER}" class="ml-auto">Register Now</a>

                    <div class="form-input pt-30" style="text-align: center">
                        <a class="btn flex-fill m-l5 google-plus" 
                           href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=${Constants.GOOGLE_REDIRECT_URI}&response_type=code
                           &client_id=${Constants.GOOGLE_CLIENT_ID}&approval_prompt=force">
                            <i class="fa fa-google-plus"></i>Google Plus</a>

                    </div>

                </div>
            </form>
            <!-- /end login form -->
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

    </body>
</html>