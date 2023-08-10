<%-- 
    Document   : register.jsp
    Created on : May 22, 2023, 2:20:32 AM
    Author     : TT
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="util.Constants"%>
<!doctype html>
<html class="no-js" lang="zxx">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Syllabus Overview</title>
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
            .table-bordered td:first-child {
                background-color: #20c997;
                color: white;
            }
        </style>
    </head>

    <body>
        <!-- Preloader Start -->
        <jsp:include page="/user/navigator/header.jsp"/>  

 
        <main >
            <jsp:include page="/user/navigator/navSubHeaderSyllabus.jsp"/> 
 
            <div class="courses-area section-padding40 fix">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-xl-7 col-lg-8">
                            <div class="section-tittle text-center mb-55">
                                <h2>Syllabus Overview</h2>
                            </div>
                        </div>
                    </div>
                    <c:if test="${sessionScope.user.getRoleFunction() == 2 || sessionScope.user.getRoleFunction() == 1}">
                        <a class="btn btn-primary" href="syllabus?action=edit&sylId=${sylId}" style="color: white;margin: 10px" >Edit This Syllabus</a>
                    </c:if>
                    <form action="syllabus" method="post">
                        <input type="hidden" name="action" value="detail">
                        <input type="hidden" name="sylId" value="${syllabus.getSyllabusId()}">
                        <!<!-- end message  -->
                        <table class="table table-bordered">
                            <tbody>
                                <tr class="">
                                    <td style="text-align: right; width: 200px">Syllabus ID:</td>
                                    <td>${syllabus.getSyllabusId()}</td>

                                </tr>
                                <tr>
                                    <td style="text-align: right;">Name:</td>
                                    <td>${syllabus.getName()}</td>
                                </tr>
                                <tr>
                                    <td style="text-align: right;">English Name:</td>
                                    <td>${syllabus.getEnglish_name()}</td>
                                </tr>
                                <tr>
                                    <td style="text-align: right;">Subject Code:</td>
                                    <td>${syllabus.getSubject().getCode()}</td>
                                </tr>
                                <tr>
                                    <td style="text-align: right;">Credit:</td>
                                    <td>${syllabus.getCredit()}</td>
                                </tr>
                                <tr> 
                                    <td style="text-align: right;">Degree level:</td>
                                    <td>
                                        ${syllabus.getDegree_level()}
                                    </td>
                                </tr>
                                <tr> 
                                    <td style="text-align: right;">Time Allocation:</td>
                                    <td>
                                        ${syllabus.getTime() == null ? "None" : syllabus.getTime() == null}
                                    </td>
                                </tr>
                                <tr> 
                                    <td style="text-align: right;">Pre-Requisite:</td>
                                    <td>
                                        <c:if test="${syllabus.getSubject_pre().size() == 0}">
                                            None
                                        </c:if>
                                        <c:if test="${syllabus.getSubject_pre().size() != 0}">
                                            <c:forEach items="${syllabus.getSubject_pre()}" var="a">
                                                ${a.getSubject_pre().getCode()} &
                                            </c:forEach>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr> 
                                    <td style="text-align: right;">Description:</td>
                                    <td>
                                        ${syllabus.getDescription()}
                                    </td>
                                </tr>
                                <tr> 
                                    <td style="text-align: right;">Student Tasks:</td>
                                    <td>
                                        ${syllabus.getStudent_task() == null ? "None" : syllabus.getStudent_task()}
                                    </td>
                                </tr>
                                <tr> 
                                    <td style="text-align: right;">Tools:</td>
                                    <td>
                                        ${syllabus.getTool() == null ? "None" : syllabus.getTool()}
                                    </td>
                                </tr>
                                <tr> 
                                    <td style="text-align: right;">Scoring Scale:</td>
                                    <td>
                                        ${syllabus.getScore()}
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right;">Decision No <br>(dd-MM-yyyy):</td>
                                    <td>${syllabus.getDecision().getDecision_no()}<br> (${syllabus.getDecision().getDate()}) </td>
                                </tr> 
                                <tr> 
                                    <td style="text-align: right;">Is Approved:</td>
                                    <td>
                                        ${syllabus.isIs_approved() ? "True" : "False"}
                                    </td>
                                </tr>
                                <tr> 
                                    <td style="text-align: right;">Note:</td>
                                    <td>
                                        ${syllabus.getNote()}
                                    </td>
                                </tr>
                                <tr> 
                                    <td style="text-align: right;">Min Mark to Pass:</td>
                                    <td>
                                        ${syllabus.getMin_mark()}
                                    </td>
                                </tr>
                                <tr> 
                                    <td style="text-align: right;">Is Active:</td>
                                    <td>
                                        ${syllabus.getStatus() ? "True" : "False"}
                                    </td>
                                </tr>
                                <c:if test="${sessionScope.user.getRoleFunction() == 2 || sessionScope.user.getRoleFunction() == 1}">
                                <tr> 
                                    <td style="text-align: right;">Designer:</td>
                                    <td>
                                        ${syllabus.getDesigner_id().getUserName()}
                                    </td>
                                </tr>
                                <tr> 
                                    <td style="text-align: right;">Reviewer:</td>
                                    <td>
                                        ${syllabus.getReviewer().getUserName()}
                                    </td>
                                </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </form>
                    <a href="syllabus">
                        <button class="btn btn-primary" type="submit">Back to Syllabus List</button> 
                    </a>
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