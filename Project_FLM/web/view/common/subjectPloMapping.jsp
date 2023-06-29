<%-- 
    Document   : subjectPloMapping
    Created on : Jun 8, 2023, 3:12:52 AM
    Author     : DungNT
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
            .overlay {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                display: flex;
                justify-content: center;
                align-items: center;
                z-index: 9999;
            }

            .success-message {
                background-color: #ffffff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
                text-align: center;
            }
        </style>
        <script>
            function displaySuccessMessage() {
                var overlay = document.createElement("div");
                overlay.classList.add("overlay");
                var successMessage = document.createElement("div");
                successMessage.classList.add("success-message");
                successMessage.innerHTML = "Save successfully!";
                document.body.appendChild(successMessage);
                overlay.appendChild(successMessage);
                document.body.appendChild(overlay);
                setTimeout(function () {
                    document.body.removeChild(successMessage);
                }, 1000);
            }
        </script>
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
        <main >
            <section class="slider-area slider-area2">
                <div class="slider-active">
                    <!-- Single Slider -->
                    <div style="height:100px; " >   
                        <jsp:include page="/view/navSubHeaderCurriculum.jsp"/> 
                    </div>
                </div>
            </section>
            <div class="courses-area section-padding40 fix">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-xl-7 col-lg-8">
                            <div class="section-tittle text-center mb-55">
                                <h2>Mapping Management</h2>
                            </div>
                        </div>

                    </div>


                    <div>

                        <form action="${pageContext.request.contextPath}${Constants.URL_SUBJECTPLOMAPPING}"
                              method="post">                   
                            <input hidden name="idCurrent" value="${idCurrent}" />
                            <c:choose>
                                <c:when test="${listSubjects == null || listPlo == null || listSubjects.size() == 0 || listPlo.size() == 0}">
                                    None
                                </c:when>
                                <c:otherwise>

                                    <table style="text-align: center" class="table table-bordered table-striped table-hover" ${hide ? 'style="display:none;"   ' : ''}>
                                        <thead  class="table-warning"> 

                                            <tr>
                                                <th>PLO(s)</th>
                                                    <c:forEach items="${listPlo}" var="a">
                                                    <th>${a.name}</th>
                                                    </c:forEach>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${listSubjects}" var="b">
                                                <tr>
                                                    <td>${b.name}</td>
                                                    <c:forEach items="${listPlo}" var="a">

                                                        <c:set var="checked" value="false" />
                                                        <c:forEach items="${listMappingPLOSubject}" var="p">
                                                            <c:if test="${p.plo == a.id && p.subjectId == b.id}">
                                                                <c:set var="checked" value="true" />
                                                            </c:if>
                                                        </c:forEach>
                                                        <td style="text-align: center">
                                                            <c:choose>
                                                                <c:when test="${sessionScope.user.getRoleFunction() == 2 }">
                                                                    <input type="checkbox" name="mapping${b.id}_${a.id}" value="0" <c:if test="${checked}">checked</c:if>/>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <c:if test="${checked}">X</c:if>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                    </c:forEach>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <div class="col-12">
                                        <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                                            <button onclick="displaySuccessMessage()"  type="submit" class="btn btn-secondary btn-number" style="background-color: orange; border-color: orange;">
                                                Save change
                                            </button>
                                        </c:if>

                                    </div>
                                </c:otherwise>
                            </c:choose>



                        </form>

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