<%-- 
    Document   : contentgroupAdd
    Created on : Jun 8, 2023, 8:34:00 PM
    Author     : TT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Content Group Add</title>
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
    </head>
    <body>
        <jsp:include page="/user/navigator/header.jsp"/>  
        <jsp:include page="/view/navSubHeaderCurriculum.jsp"/> 
        <div class="section-tittle text-center">
            <h2 style="margin-top: 20px;">Add Content Group</h2>
        </div>
        <div class="container">
            <div class="row">
                <!-- Your Profile Views Chart -->
                <div class="col-lg-12 m-b30">
                    <div class="widget-box">
                        <div class="widget-inner">
                            <form class="edit-profile m-b30" action="contentgroupAdd" method="POST">
                                <div class="row">
                                    <p style="color: red">${messFail}</p>
                                    <div class="form-group col-4">
                                        <label class="col-form-label">Content Group Name<i style="color: red">*</i></label>
                                        <div>
                                            <input class="form-control" name="cname" type="text" placeholder="Enter content group name" value="${cname}" 
                                                   pattern="^.{1,100}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Can only enter maximum 100 charactes.' : '')" required="">
                                        </div>
                                    </div> 
                                    <div class="form-group col-4">
                                        <label class="col-form-label">Content Group English Name<i style="color: red">*</i></label>
                                        <div>
                                            <input class="form-control" name="ename" type="text" placeholder="Enter content group english name" value="${ename}" 
                                                   pattern="^.{1,100}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Can only enter maximum 100 charactes.' : '')" required="">
                                        </div>
                                    </div> 
                                    <div class="form-group col-4">
                                        <label class="col-form-label">Display Order<i style="color: red">*</i></label>
                                        <div>
                                            <input class="form-control" name="order" type="number" placeholder="Enter display number" value="${order == null ? 1 : order}" min="1" required="">
                                        </div>
                                    </div>
                                    <div class="col-12" style="margin-top: 10px">
                                        <div class="text-end">
                                            <button type="submit" class="btn btn-primary" style="margin-bottom: 5px;" name="save">Save changes</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <div class="text-end" style="margin-bottom: 10px">
                                <a href="contentgroup?action=list&&curId=${sessionScope.curId}">
                                    <button class="btn btn-primary" type="submit">Back</button> 
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
    </body>
</html>
