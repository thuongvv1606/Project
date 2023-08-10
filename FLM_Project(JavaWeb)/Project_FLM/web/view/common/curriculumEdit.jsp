<%-- 
    Document   : curriculumAdd
    Created on : May 25, 2023, 10:31:08 PM
    Author     : TT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="util.Constants"%>
<!doctype html>
<html class="no-js" lang="zxx">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Curriculum Edit</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="manifest" href="site.webmanifest">
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

        <!-- CSS here -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/owl.carousel.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/slicknav.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/flaticon.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/progressbar_barfiller.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/gijgo.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/nice-select.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">

    </head>
    <body>
        <% if (request.getAttribute("messfail") != null) {%>
        <script>
            $(document).ready(function () {
                // T?o toast thông báo và tùy ch?nh màu s?c
                toastr.options = {
                    "positionClass": "toast-top-right",
                    "closeButton": true,
                    "progressBar": true,
                    "timeOut": 5000, // Th?i gian hi?n th? toast (tính b?ng mili giây)
                    "extendedTimeOut": 2000, // Th?i gian hi?n th? ti?p t?c n?u ng??i dùng di chu?t qua toast (tính b?ng mili giây)
                    "tapToDismiss": false, // Ng?n ng??i dùng t?t toast b?ng cách nh?p chu?t
                    "preventDuplicates": true, // Ng?n toast trùng l?p
                    "progressBarColor": "#FF3366", // Màu c?a thanh ti?n trình
                    "toastClass": "custom-toast" // L?p CSS tùy ch?nh cho toast
                };

                // L?y thông báo t? ??i t??ng request
                var message = '<%= request.getAttribute("messSuccess")%>';

                // Hi?n th? toast thành công
                toastr.error(message);

            <%-- Xóa thông báo trong ??i t??ng request ?? ??m b?o ch? hi?n th? m?t l?n --%>
            <% request.removeAttribute("messfail"); %>
            });
        </script>
        <% }%>
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
        <div class="section-tittle text-center">
            <h2 style="margin-top: 20px;">Curriculum Edit</h2>
        </div>
        <div class="container">
            <div class="row">
                <!-- Your Profile Views Chart -->
                <form class="form-default" action="${pageContext.request.contextPath}${Constants.URL_CURRICULUMEDIT}" method="POST">
                    <div class="col-lg-12 m-b30">
                        <div class="widget-box">
                            <div class="widget-inner">
                                <div class="row">
                                    <p style="color: red;">${mess}</p>
                                    <div class="form-group col-4">
                                        <label class="col-form-label">Code<i style="color: red">*</i></label>
                                        <div>
                                            <input class="form-control" value="${code == null ? curriculum.code : code}" maxlength="50" type="text" class="form-control" id="uname" placeholder="Enter code" name="code" required="">
                                        </div>
                                    </div>
                                    <div class="form-group col-4">
                                        <label class="col-form-label">Name<i style="color: red">*</i></label>
                                        <div>
                                            <input value="${name == null ? curriculum.name.split("[(]")[1] : name}" type="text" class="form-control" id="uname" placeholder="Enter name" name="name" required="">
                                        </div>
                                    </div>
                                    <div class="form-group col-4">
                                        <label class="col-form-label">English Name<i style="color: red">*</i></label>
                                        <div>
                                            <input value="${engname == null ? curriculum.name.split("[(]")[0] : engname}" maxlength="100" type="text" class="form-control" id="uname" placeholder="Enter english name" name="engname" required="">
                                        </div>
                                    </div>
                                    <div class="form-group col-12">
                                        <label class="col-form-label">Description</label><br>
                                        <textarea
                                            id="description"
                                            name="description"
                                            class="form-control" maxlength="500"
                                            rows="8">${description == null ? curriculum.description : description}</textarea>
                                        <p id="validdes" hidden style="color: red">Fill in description</p>
                                    </div>
                                    <div class="text-end">
                                        <input type="text" hidden name="idSetting" value="${a}"/>
                                        <input class="btn btn-primary" style="color: white;margin: 10px" type="submit" name="update" value="update"/>
                                        <input type="text" hidden name="checkasa"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
                <div class="text-end">
                    <a href="curriculum">
                        <button style="color: white;margin: 10px" class="btn btn-primary" type="submit">Back</button> 
                    </a>
                </div>
            </div>
        </div>
        <jsp:include page="/user/navigator/footer.jsp"/>    
        <!-- Scroll Up -->
        <div id="back-top" >
            <a title="Go to Top" href="#"> <i class="fas fa-level-up-alt"></i></a>
        </div>
        <!--         <script>
                $(document).ready(function() {
                    var updateStatus = "<%= request.getAttribute("mess")%>";
        
                    if (updateStatus === "mess") {
                        toastr.success("Update fail!");
                    }
                });
            </script>-->


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
