<%-- 
    Document   : subjectList
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
        <!-- Preloader Start -->
        <jsp:include page="/user/navigator/header.jsp"/>  
        <section class="slider-area slider-area2">
            <div class="slider-active">
                <!-- Single Slider -->
                <div style="height:100px; " >   
                    <jsp:include page="/view/navSubHeaderCurriculum.jsp"/> 
                </div>
            </div>
        </section>
        <main>
            <div class="courses-area section-padding40 fix">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-xl-7 col-lg-8">
                            <div class="section-tittle text-center mb-55">
                                <h2>Subject List</h2>
                            </div>
                        </div>

                    </div>
                    <div class="p-2 w-100 bd-highlight" style="display: flex">
                        <form class="row g-3" action="${pageContext.request.contextPath}${Constants.URL_SUBJECTLIST}" method="get">
                            <input hidden name="idCurrent" value="${idCurrent}"/>
                            <div class="col-auto">
                                <select name="searchBy" >  
                                    <option ${searchBy == 1 ? 'selected':''}  value="1">Code</option>
                                    <option ${searchBy == 0 ? 'selected':''} value="0">Name</option>
                                </select>
                            </div>
                            <div class="col-auto">
                                <input type="text" value="${inputSearch}" class="form-control" id="inputSearch" name="inputSearch" placeholder="">
                            </div>
                            <div class="col-auto">
                                <button type="submit" class="btn btn-primary mb-3">Search</button>
                            </div>
                        </form>
                        <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                            <form class="row g-3" action="${pageContext.request.contextPath}${Constants.URL_SUBJECTIMPORT }" enctype="multipart/form-data" method="POST" style="text-align: right; padding-left: 240px">
                                <div class="col-auto">
                                    <B>File:<input type="file" name="file" value=""></b>
                                </div>
                                <div class="col-auto">
                                    <button type="submit" class="btn btn-primary mb-3">Import</button>
                                </div>
                                <H2 style="color: greenyellow">${message}</H2>
                                <H2 style="color: red ">${error}</H2>
                            </form>
                        </c:if>
                    </div>
                    <div class="row">
                        <div class="col-sm-9 float-start">
                            <c:if test="${user.getRoleFunction() == 2 }">
                                <span class="input-group-btn">
                                    <a href="${pageContext.request.contextPath}${Constants.URL_ADDSUBJECT}?idCurrent=${idCurrent} " class="btn btn-sm btn-success ">Add new subject list</a>
                                </span> 
                            </c:if>
                        </div> 
                    </div>
                    <div>
                        <table class="table table-striped" cellspacing="0" rules="all" border="1" id="gvSubs"
                               style="border-collapse:collapse;">
                            <tbody>
                                <tr class="bg-warning">
                                    <th class="text-white" scope="col">Subject Code</th>
                                    <th class="text-white" scope="col">Subject Name</th>
                                    <th class="text-white" scope="col">Semester</th>
                                    <th class="text-white" scope="col">NoCredit</th>
                                    <th class="text-white" scope="col">PreRequisite</th>
                                        <c:if test="${sessionScope.user.getRoleFunction() == 2 }">
                                        <th class="text-white" scope="col">Function</th>
                                        </c:if>
                                </tr>
                                <c:forEach var="iteam" items="${listSubjects}">
                                    <c:choose>
                                        <c:when test="${sessionScope.user.getRoles().get(0).getId() == 6  or sessionScope.user.getRoles().get(0).getId() == 7}">
                                            <tr > 
                                            </c:when>
                                            <c:otherwise>
                                            <tr data-bs-toggle="modal" data-bs-target="#myModal" id="sb" onclick="uploadFile(${iteam.id})">

                                            </c:otherwise>
                                        </c:choose>


                                        <td>${iteam.code}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}${Constants.URL_SUBJECTDETAIL}?subCode=${iteam.id}&curriculumID=${idCurrent}"
                                               class="text-dark"> ${iteam.name}</a></td>
                                        <td>${iteam.semester}</td>
                                        <td>${iteam.no_credit}</td>
                                        <td>${iteam.pre_condition}</td>
                                        <c:if test="${sessionScope.user.getRoleFunction() == 2 }">
                                            <td class="text-white" scope="col">

                                                <a href="${pageContext.request.contextPath}${Constants.URL_EDITSUBJECT}?id=${iteam.id}&curriculumID=${idCurrent}">
                                                    <i style="color:  blue" class="fa fa-edit"></i>
                                                    </button>
                                                </a>
                                            </td>
                                        </c:if>
                                    </tr> 
                                </c:forEach> 
                            </tbody>
                        </table>

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
