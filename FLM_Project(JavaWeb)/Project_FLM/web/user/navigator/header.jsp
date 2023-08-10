<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="util.Constants"%>
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/script.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
        jquery 
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <link rel='stylesheet' href='${pageContext.request.contextPath}/assets/css/profle.css' type='text/css' media='all' />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
-->
<header>
    <!-- Header Start -->
    <div style="background: #bb71ff;">
        <div class="main-header ">
            <div class="header-bottom  header-sticky">
                <div class="container-fluid">
                    <div class="row align-items-center">
                        <!-- Logo -->
                        <div class="col-xl-2 col-lg-2">
                            <div class="logo">
                                <a href="home"><img src="${pageContext.request.contextPath}/assets/img/logo/logo1.jpg" alt=""></a>
                            </div>
                        </div>
                        <c:if test="${sessionScope.user != null}">
                        </c:if>
                        <div class="col-xl-10 col-lg-10">
                            <div class="menu-wrapper d-flex align-items-center justify-content-end">
                                <!-- Main-menu -->
                                <div class="main-menu d-none d-lg-block">
                                    <nav>
                                        <ul id="navigation">
                                            <li><a href="curriculum">Curriculum</a></li>
                                                <c:if test="${sessionScope.user != null}">
                                                <li><a href="syllabus">Syllabus</a></li>
                                                </c:if>
                                            <li><a href="${pageContext.request.contextPath}${Constants.URL_SUBJECTPREDECESSOER}">Subject Predecessors</a></li> 
                                            <li><a href="${pageContext.request.contextPath}${Constants.URL_SUBJECTSUCCESSOER}">Subject Successors</a>
                                                <c:if test="${sessionScope.user != null}">
                                                    <c:if test="${sessionScope.user.getRoleFunction() != null}"> 
                                                        <!--syllabus designer , reviewer navbar-->
                                                        <c:if test="${sessionScope.user.getRoleFunction() == 1}">
                                                        <li><a href="#">Dashbroad</a>
                                                            <ul class="submenu">
                                                                <li><a href="syllabus">Syllabus Designing</a></li>
                                                            </ul>
                                                        </li>
                                                    </c:if>

                                                    <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                                                        <li><a href="#">Dashbroad</a>
                                                            <ul class="submenu">
                                                                <c:forEach items="${sessionScope.user.getRoles()}" var="r">
                                                                    <c:if test="${r.getId() == 1}">
                                                                        <li><a href="settingList">System Settings</a></li>
                                                                        <li><a href="#">Role Permissions</a></li>
                                                                        <li><a href="account">User Management</a></li>
                                                                        </c:if>
                                                                        <c:forEach items="${sessionScope.user.getRoles()}" var="r">
                                                                            <c:if test="${r.getId() == 2 || r.getId() == 3}">
                                                                            <li><a href="#">Training Curriculums</a></li>
                                                                            <li><a href="#">Subject Management</a></li>
                                                                            <li><a href="${pageContext.request.contextPath}${Constants.URL_DECISIONCONTROLLER}">Material Decisions</a></li>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </c:forEach>
                                                            </ul>
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${sessionScope.user != null}">
                                                    <li><img alt='avatar' width="35" height="35" src="${pageContext.request.contextPath}/assets/img/user-1.jpg" class="gravatar">
                                                        <ul class="submenu" style="left: -250%;">
                                                            <li><a href="userprofile">User Profile</a></li>
                                                            <li><a href="changepass">Change Password</a></li>
                                                            <li><a href="logOut">Logout</a></li>
                                                        </ul>
                                                    </li>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${sessionScope.user == null}">
                                                <li class="button-header  "><a href="login" class="btn">Login</a></li>
                                                <li class="button-header  "><a href="register" class="btn">Register</a></li>
                                                </c:if>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div> 
                        <!-- Mobile Menu -->
                        <div class="col-12">
                            <div class="mobile_menu d-block d-lg-none"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" name="curId" value="${sessionScope.curId}">

</header>
