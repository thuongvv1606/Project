<%-- 
    Document   : editPo
    Created on : Jun 8, 2023, 9:05:10 PM
    Author     : LanChau
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="util.Constants"%>
<!doctype html>
<html class="no-js" lang="zxx">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title></title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="manifest" href="site.webmanifest">
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">

        <!-- CSS here -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/owl.carousel.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/slicknav.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/flaticon.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/progressbar_barfiller.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/gijgo.css">


        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/nice-select.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
        <style>

        </style>
    </head>

    <body>
        
        <% if (session.getAttribute("messFail") != null) {%>
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
                var message = '<%= session.getAttribute("messFail")%>';

                // Hi?n th? toast thành công
                toastr.error(message);

            <%-- Xóa thông báo trong ??i t??ng request ?? ??m b?o ch? hi?n th? m?t l?n --%>
            <% session.removeAttribute("messFail"); %>
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
        <!-- Preloader Start -->
        <jsp:include page="/user/navigator/header.jsp"/>  
        <main >
            <jsp:include page="/view/navSubHeaderCurriculum.jsp"/> 
            <div class="courses-area section-padding40 fix">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-xl-7 col-lg-8">
                            <div class="section-tittle text-center mb-55">
                                <h2>Edit Po</h2>
                            </div>
                        </div> 
                    </div>
                    <div class="row">
                        <!-- Your Profile Views Chart -->
                        <div class="col-lg-12 m-b30">
                            <div class="widget-box">
                                <div class="widget-inner">
                                    <form class="edit-profile m-b30" action="${pageContext.request.contextPath}${Constants.URL_EDITPO}" method="post">
                                        <div class="row">
                                            <input type="hidden" name="idPo" value="${po.poId}">
                                            <input type="hidden" name="idCurrent" value="${requestScope.idCurrent}">
                                            <span style="color: red">${error}</span>
                                            <span style="color: green">${okSuccc}</span> 
                                            <div class="form-group col-6">
                                                <label class="col-form-label">Name<i style="color: red">*</i></label>
                                                <div>
                                                    <input class="form-control"  type="text"
                                                           name="name"  required value="${po.poName}"
                                                           placeholder="Enter Name...">

                                                </div>
                                            </div>
                                            <div class="form-group col-6">
                                                <label class="col-form-label">Description<i style="color: red">*</i></label>
                                                <div>
                                                    <textarea class="form-control" id="id" name="description"
                                                              placeholder
                                                              rows="5" cols="15">${po.poDescription}</textarea> 
                                                </div>
                                            </div>
                                            <div class="form-group col-6">
                                                <label class="col-form-label">Status<i style="color: red">*</i></label>
                                                <div >
                                                    <select class="form-control" name="status">
                                                        <option ${po.isActive == true?'selected':''}  value="1">Active</option>
                                                        <option ${po.isActive == false?'selected':''}  value="0">Block</option>
                                                    </select>
                                                </div>
                                            </div>   

                                            <div class="col-12" style="margin-top: 10px" >
                                                <div class="text-end">
                                                    <a href="${pageContext.request.contextPath}${Constants.URL_POLIST}?idCurrent=${idCurrent}">
                                                        <button type="button" class="btn btn-dark">Back</button>
                                                    </a> 

                                                    <button type="submit" class="btn btn-primary">Save changes</button>
                                                </div>
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
