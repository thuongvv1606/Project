<%-- 
    Document   : changepassword
    Created on : 25-05-2023, 22:03:15
    Author     : trinh
--%>
<%@page import="util.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .pass_show{
                position: relative
            }

            .pass_show .ptxt {

                position: absolute;

                top: 50%;

                right: 10px;

                z-index: 1;

                color: #f36c01;

                margin-top: -10px;

                cursor: pointer;

                transition: .3s ease all;

            }

            .pass_show .ptxt:hover{
                color: #333333;
            }
        </style>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        
        <!--toast-->
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
        
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
        
          <body>
         <% if (session.getAttribute("messSuccess") != null) {%>
        <script>
            $(document).ready(function () {
                // Tạo toast thông báo và tùy chỉnh màu sắc
                toastr.options = {
                    "positionClass": "toast-top-right",
                    "closeButton": true,
                    "progressBar": true,
                    "timeOut": 5000, // Thời gian hiển thị toast (tính bằng mili giây)
                    "extendedTimeOut": 2000, // Thời gian hiển thị tiếp tục nếu người dùng di chuột qua toast (tính bằng mili giây)
                    "tapToDismiss": false, // Ngăn người dùng tắt toast bằng cách nhấp chuột
                    "preventDuplicates": true, // Ngăn toast trùng lặp
                    "progressBarColor": "#FF3366", // Màu của thanh tiến trình
                    "toastClass": "custom-toast" // Lớp CSS tùy chỉnh cho toast
                };

                // Lấy thông báo từ đối tượng request
                var message = '<%= session.getAttribute("messSuccess")%>';

                // Hiển thị toast thành công
                toastr.success(message);
                setTimeout(function() {
            window.location.href = "${pageContext.request.contextPath}/logOut"; // Thay đổi thành URL của Servlet/logout page để xử lý logout
        }, 5000);

            <%-- Xóa thông báo trong đối tượng request để đảm bảo chỉ hiển thị một lần --%>
            <% session.removeAttribute("messSuccess"); %>
            });
            
           
        </script>
        <% }%>
        
        <% if (session.getAttribute("messFail") != null) {%>
        <script>
            $(document).ready(function () {
                // Tạo toast thông báo và tùy chỉnh màu sắc
                toastr.options = {
                    "positionClass": "toast-top-right",
                    "closeButton": true,
                    "progressBar": true,
                    "timeOut": 5000, // Thời gian hiển thị toast (tính bằng mili giây)
                    "extendedTimeOut": 2000, // Thời gian hiển thị tiếp tục nếu người dùng di chuột qua toast (tính bằng mili giây)
                    "tapToDismiss": false, // Ngăn người dùng tắt toast bằng cách nhấp chuột
                    "preventDuplicates": true, // Ngăn toast trùng lặp
                    "progressBarColor": "#FF3366", // Màu của thanh tiến trình
                    "toastClass": "custom-toast" // Lớp CSS tùy chỉnh cho toast
                };

                // Lấy thông báo từ đối tượng request
                var message = '<%= session.getAttribute("messFail")%>';

                // Hiển thị toast thành công
                toastr.error(message);

            <%-- Xóa thông báo trong đối tượng request để đảm bảo chỉ hiển thị một lần --%>
            <% session.removeAttribute("messFail"); %>
            });
            
           
        </script>
        <% }%>
        <jsp:include page="/user/navigator/header.jsp"/> 
        <form class="form-default" action="${pageContext.request.contextPath}${Constants.URL_CHANGEPASS}" method="POST" onsubmit="return validateForm()">
            <div class="container">
                <div class="row">
                    <div class="col-sm-4">
                        <label>Current Password</label>
                        <div class="form-group pass_show"> 
                            <input type="password" value="old pass" pattern="${Constants.HTML_REGEX_PASSWORD}" 
                               title="${Constants.HTML_TITLE_OF_REGEX__PASSWORD}" 
                                   name="oldpass" class="form-control" placeholder="Current Password"> 
                        </div> 
                        <label>New Password</label>
                        <div class="form-group pass_show"> 
                            <input id="password" name="Password" type="password" 
                               pattern="${Constants.HTML_REGEX_PASSWORD}" 
                               title="${Constants.HTML_TITLE_OF_REGEX__PASSWORD}" 
                               value="new pass"
                               placeholder="JKL987@@"
                               class="form-control" required=""> 
                        </div> 
                        <label>Confirm Password</label>
                        <div class="form-group pass_show"> 
                            <p id="passwordError" style="color: red;"></p>
                            <input id="confirmPassword" name="rePassword" type="password" 
                               pattern="${Constants.HTML_REGEX_PASSWORD}" 
                               title="${Constants.HTML_TITLE_OF_REGEX__PASSWORD}" 
                               value="new pass"
                               placeholder="JKL987@@"
                               class="form-control" required=""> 
                        </div> 
                        <button class="btn btn-primary" name="change"  type="submit">Save changes</button><p>${messs}</p>
                    </div>  
                </div>
            </div>
        </form>
        <script>$(document).ready(function () {
                $('.pass_show').append('<span class="ptxt">Show</span>');
            });


            $(document).on('click', '.pass_show .ptxt', function () {

                $(this).text($(this).text() == "Show" ? "Hide" : "Show");

                $(this).prev().attr('type', function (index, attr) {
                    return attr == 'password' ? 'text' : 'password';
                });

            });
        </script>
        
        <script>
            function validateForm() {
                var password = document.getElementById("password").value;
                var confirmPassword = document.getElementById("confirmPassword").value;
                var passwordError = document.getElementById("passwordError");

                var check = true;
                
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
        
        <jsp:include page="/user/navigator/footer.jsp"/>  
    </body>
</html>
