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
        <title>Account Edit</title>
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/gijgo.css">


        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/nice-select.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
        <style>
            .form-control, .dataTable-input {
                display: block;
                width: 100%;
                padding: 0.875rem 1.125rem;
                font-size: 0.875rem;
                font-weight: 400;
                line-height: 1;
                color: #69707a;
                background-color: #fff;
                background-clip: padding-box;
                border: 1px solid #c5ccd6;
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                border-radius: 0.35rem;
                transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
            }
        </style>
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
        <div class="section-tittle text-center">
            <h2 style="margin-top: 20px;">Edit Account</h2>
        </div>
        <div class="container">
            <div class="row">
                <!-- Your Profile Views Chart -->
                <div class="col-lg-12 m-b30">
                    <div class="widget-box">
                        <div class="widget-inner">
                            <form class="edit-profile m-b30" action="accountEdit" method="POST">
                                <div class="row">
                                    <p style="color: red;">${messFail}</p>
                                    <div class="form-group col-4">
                                        <label class="col-form-label">Full name <span style="color: red">*</span></label>
                                        <div>
                                            <input class="form-control" name="fullname" type="text" placeholder="Enter fullname" value="${fullname != null ? fullname : acc.getFullName()}" required=""
                                                   pattern="^[a-zA-ZÀ-?\\s].{10,100}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must only contain 6 to 100 letters.' : '')">
                                        </div>
                                        <label class="col-form-label">Phone Number <span style="color: red">*</span></label>
                                        <div>
                                            <input class="form-control" name="phone" type="text" placeholder="Enter phone number" value="${phone != null ? phone : acc.getMobile()}"
                                                   pattern="[+]{0,1}[0-9]{10,12}" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must enter correct phone number form.' : '')">
                                        </div>
                                        <label class="col-form-label">Company / Organization</label>
                                        <div>
                                            <input class="form-control" name="company" type="text" placeholder="Enter company" value="${company != null ? company : acc.getCompany()}"
                                                   pattern="^.{0,100}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must contain at most 100 characters.' : '')">
                                        </div>
                                    </div>
                                    <div class="form-group col-4">
                                        <label class="col-form-label">User Name <span style="color: red">*</span></label>
                                        <div>
                                            <input class="form-control" name="username" type="text" placeholder="Enter username" value="${username != null ? username : acc.getUserName()}"
                                                   pattern="^.{5,100}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must only contain 5 to 100 characters.' : '')">
                                        </div>
                                        <label class="col-form-label">Email Address <span style="color: red">*</span></label>
                                        <div>
                                            <input class="form-control" name="email" type="text" placeholder="Enter email address" value="${email != null ? email : acc.getEmail()}"
                                                   pattern="^[\\w.-]+@[a-zA-Z_-]+?(?:\\.[a-zA-Z]{2,6})+$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must enter correct email form.' : '')">
                                        </div>
                                        <label class="col-form-label">Title</label>
                                        <select class="form-control" name="title">
                                            <option value="mr" ${acc.getTitle() eq 'mr' ? "selected" : ""}>Mr</option>
                                            <option value="mrs" ${acc.getTitle() eq 'mrs' ? "selected" : ""}>Mrs</option>
                                            <option value="ms" ${acc.getTitle() eq 'ms' ? "selected" : ""}>Ms</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-2">
                                        <label class="col-form-label">Roles (if not choose roles then keep the old ones)</label><br>
                                        <c:forEach items="${rList}" var="r">
                                            <input name="role" type="checkbox" value="${r.getId()}"> ${r.getName()}<br>
                                        </c:forEach>
                                    </div>
                                    <div class="form-group col-2">
                                        <label class="col-form-label">Old Roles</label><br>
                                        <c:forEach items="${rList}" var="r">
                                            <c:forEach items="${acc.getRoles()}" var="role">
                                                <c:if test="${r.getId() == role.getId()}">
                                                    ${r.getName()}<br>
                                                </c:if>
                                            </c:forEach>
                                        </c:forEach>
                                    </div>   
                                    <div class="col-12">
                                        <div class="text-end">
                                            <button type="submit" name="save" class="btn btn-primary" style="margin-bottom: 5px;">Save changes</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <div class="text-end">
                                <a href="account">
                                    <button class="btn btn-primary" type="submit" style="margin-bottom: 20px;">Back</button> 
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

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
