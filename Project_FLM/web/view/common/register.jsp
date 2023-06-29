<%-- 
    Document   : register.jsp
    Created on : May 22, 2023, 1:20:30 AM
    Author     : LanChau
--%>
<%@page import="util.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html class="no-js" lang="zxx">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Register</title>
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
        <!--Start Header-->
        <jsp:include page="/user/navigator/header.jsp"/>   
        <!--End Header-->

        <!-- Register -->

        <main class="login-body" data-vide-bg="${pageContext.request.contextPath}/assets/img/img_6385-min.jpg">
            <!-- Login Admin -->
            <form class="form-default" action="${pageContext.request.contextPath}${Constants.URL_REGISTER}"
                  method="Post" onsubmit="return validateForm()">

                <div class="login-form">
                    <!-- logo-login -->
                    <div class="logo-login">
                        <a href="${pageContext.request.contextPath}${Constants.URL_HOMEPAGE}"><img src="${pageContext.request.contextPath}/assets/img/logo/loder.png" alt=""></a>
                    </div>
                    <h2>Registration Here</h2>
                    <h2 style="color: green; text-align: center;">${messOK}</h2>
                    <h2 style="color: red; text-align: center;">${messFail}</h2>
                    <div class="form-input">
                        <p id="FNameError" style="color: red;"></p>
                        <label for="firstName">Full name</label>
                        <input name="FName" type="text" required id="firstName"
                               pattern="${Constants.HTML_REGEX__FULLNAME}" 

                               title="${Constants.HTML_TITLE_OF_REGEX__FULLNAME}" 
                               value="${user.fullName}" placeholder="nguyen van a"
                               class="form-control">
                    </div>
                    <div class="form-input">
                        <label for="UName">User Name</label>
                        <input name="UName" type="text" required id="UName"
                               pattern="${Constants.HTML_REGEX_USERNAME}" 
                               placeholder="abc2002"
                               title="${Constants.HTML_TITLE_OF_REGEX__USERNAME}"  
                               value="${user.userName}"
                               class="form-control">
                    </div>
                    <div class="form-input">
                        <label for="emailf">Email</label>
                        <input name="Email" type="email" required id="emailf"
                               pattern="${Constants.HTML_REGEX_EMAIL}" 
                               title="${Constants.HTML_TITLE_OF_REGEX__EMAIL}" 
                               value="${user.email}"
                               placeholder="nguyena@gmail.com"
                               class="form-control">
                    </div>
                    <div class="form-input">
                        <label for="phone">Phone</label>
                        <input name="Phone" type="text" required id="phone"
                               pattern="${Constants.HTML_REGEX_MOBILE}" 
                               title="${Constants.HTML_TITLE_OF_REGEX__MOBILE}" 
                               value="${user.mobile}"
                               placeholder="+84123456789 OR 0123456789"
                               class="form-control">
                    </div>
                    <div class="form-input">
                        <label for="password">Password</label>
                        <input id="password" name="Password" type="password" 
                               pattern="${Constants.HTML_REGEX_PASSWORD}" 
                               title="${Constants.HTML_TITLE_OF_REGEX__PASSWORD}" 
                               value="${user.password}"
                               placeholder="JKL987@@"
                               class="form-control" required="">
                    </div>
                    <div class="form-input">
                        <p id="passwordError" style="color: red;"></p>
                        <label for="confirmPassword">Confirm Password</label>
                        <input id="confirmPassword" name="Password" type="password" 
                               pattern="${Constants.HTML_REGEX_PASSWORD}" 
                               title="${Constants.HTML_TITLE_OF_REGEX__PASSWORD}" 
                               value="${user.password}"
                               placeholder="JKL987@@"
                               class="form-control" required="">

                    </div>
                    <div class="form-input pt-30">
                        <input type="submit" name="submit" value="Registration">
                    </div>
                    <!-- Forget Password -->
                    <a href="${pageContext.request.contextPath}${Constants.URL_LOGIN}" class="registration">login</a>
                </div>
            </form>
            <!-- /end login form -->
        </main>
        <!--Start Footer-->
        <jsp:include page="/user/navigator/footer.jsp"/>   
        <!--End Footer-->
        <script>
            function validateForm() {
                var firstNameInput = document.getElementById('firstName').value;
                var password = document.getElementById("password").value;
                var confirmPassword = document.getElementById("confirmPassword").value;
                var passwordError = document.getElementById("passwordError");
                var FNameError = document.getElementById("FNameError");

                var check = true;
                if (firstNameInput.includes('  ')) {
                    // Nếu có, ngăn không cho form được submit
                    check = false; // Ngăn form được gửi đi
                    FNameError.textContent = "Do not enter space duplicate";
                    // Hiển thị thông báo lỗi cho người dùng
                    //alert('Không được nhập khoảng trắng 2 lần.');
                }
                if (password !== confirmPassword) {
                    passwordError.textContent = "Confirmation password does not match";
                    check = false; // Ngăn form được gửi đi
                } else {
                    passwordError.textContent = ""; // Xóa thông báo lỗi (nếu có)
                    check = true; // Cho phép form được gửi đi
                }

                return check;
            }
        </script>

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

