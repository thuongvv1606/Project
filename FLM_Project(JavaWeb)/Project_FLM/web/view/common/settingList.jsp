<%-- 
    Document   : settingList.jsp
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/gijgo.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/animate.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/animated-headline.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/magnific-popup.css">
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
        <main >

            <div class="courses-area section-padding40 fix">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-xl-7 col-lg-8">
                            <div class="section-tittle text-center mb-55">
                                <h2>Setting List</h2>
                            </div>
                        </div>

                    </div>
                    <div class="p-2 w-100 bd-highlight">
                        <form class="row g-3" action="${pageContext.request.contextPath}${Constants.URL_SETTINGLIST}" method="get">
                            <input hidden name="idCurrent" value="${idCurrent}"/>
                            <div class="col-auto">
                                <input type="text"  value="${title}"
                                       class="form-input" style="padding: 5px 20px"   id="inputSearch" 
                                       name="title" placeholder="Input title ...">
                                <input type="text"  value="${type}" 
                                       class="form-input" style="padding: 5px 20px"   id="inputSearch" 
                                       name="type" placeholder="Input type ...">
                            </div>
                            <div class="col-auto">
                                <button type="submit" class="btn btn-primary btn-sm">Search</button>
                            </div>
                        </form>
                    </div>
                    <div class="row">
                        <div class="col-sm-9 float-start">
                            <c:if test="${user.getRoleFunction() == 2 }">
                                <span class="input-group-btn">
                                    <a href="${pageContext.request.contextPath}${Constants.URL_ADDSETTING}" class="btn btn-sm btn-success ">Add new setting list</a>
                                </span> 
                            </c:if>
                        </div> 
                    </div>
                    <div>
                        <table class="table table-striped" cellspacing="0" rules="all" border="1" id="gvSubs"
                               style="border-collapse:collapse;">
                            <tbody>
                                <tr class="bg-warning">
                                    <th class="text-white" scope="col">Title</th>
                                    <th class="text-white" scope="col">Type</th>
                                    <th class="text-white" scope="col">Value</th>

                                    <c:if test="${sessionScope.user.getRoleFunction() == 2 }">
                                        <th class="text-white" scope="col">Display Order</th>
                                        <th class="text-white" scope="col">Active</th>
                                        <th class="text-white" scope="col">Function</th>
                                        </c:if>
                                </tr>
                                <c:forEach var="iteamS" items="${listSettingss}" varStatus="loop">
                                    <c:if test="${sessionScope.user.getRoleFunction() != 2 && iteamS.status == 1 }">
                                    <td>
                                        <a style="color: black" href="${pageContext.request.contextPath}${Constants.URL_DETAILSETTING}?id=${iteamS.id}"> ${iteamS.title}</a>
                                    </td>
                                    <td>${iteamS.type}</td>

                                    <td>${iteamS.value}</td> 
                                </c:if>
                                <c:if test="${sessionScope.user.getRoleFunction() == 2 }">
                 
                                    <td>
                                        <a style="color: black" href="${pageContext.request.contextPath}${Constants.URL_DETAILSETTING}?id=${iteamS.id}"> ${iteamS.title}</a>
                                    </td>
                                    <td>${iteamS.type}</td>

                                    <td>${iteamS.value}</td>
                                     <td>${iteamS.displayOrder}</td>

                                    <td>
                                        <c:choose>
                                            <c:when test="${iteamS.status == 1}" >
                                                <span style="color: green">Active</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span style="color: red">Block</span>
                                            </c:otherwise>
                                        </c:choose>

                                    </td>
                                    <td class="text-white" scope="col">

                                        <a href="${pageContext.request.contextPath}${Constants.URL_EDITSETTING}?id=${iteamS.id}">

                                            <i style="color:  blue" class="fa fa-edit"></i>
                                            </button>
                                        </a>
                                        <a href="#confirmDeleteModal${loop.index}"  data-toggle="modal">
                                            <i style="color:  red" class="fa fa-trash"></i>
                                        </a>
                                        <form action="${pageContext.request.contextPath}${Constants.URL_DELETESETTING}" method="post">
                                            <div class="modal fade " id="confirmDeleteModal${loop.index}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h3 class="modal-title" id="confirmDeleteModalLabel">Delete Confirmation</h3>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="text-dark modal-body">
                                                            Are you sure you want to block?
                                                        </div>
                                                        <input type="text" hidden value="${iteamS.id}" name="idSetting" />
                                                        <div class="modal-footer">
                                                            <button type="button" style="margin-right: 5px" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                            <button type="submit" class="btn btn-danger">Block</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
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
