<%-- 
    Document   : contentgroupEdit
    Created on : Jun 8, 2023, 3:01:05 PM
    Author     : TT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Content Group Edit</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="manifest" href="site.webmanifest">
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">

        <!--toast-->
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

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
            input {
                width: 100%;
                border: 1px solid gainsboro;
                border-radius: 5px;
            }

            .table-bordered td:first-child {
                text-align: right;
                background-color: #20c997;
                color: white;
            }

            .table-sortable th {
                cursor: pointer;
            }

            .table-sortable .th-sort-asc::after {
                content: "\25b4";
            }

            .table-sortable .th-sort-desc::after {
                content: "\25be";
            }

            .table-sortable .th-sort-asc::after,
            .table-sortable .th-sort-desc::after {
                margin-left: 5px;
            }

            .table-sortable .th-sort-asc,
            .table-sortable .th-sort-desc {
                background: rgba(0, 0, 0, 0.1);
            }

            .custom-toast {
                top: 100px !important; /* Điều chỉnh khoảng cách từ top */
            }
        </style>
    </head>
    <body>
        <!--toast-->
        <% if (request.getAttribute("messSuccess") != null && !request.getAttribute("messSuccess").equals("")) {%>
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
                var message = '<%= request.getAttribute("messSuccess")%>';

                // Hiển thị toast thành công
                toastr.success(message);

            <%-- Xóa thông báo trong đối tượng request để đảm bảo chỉ hiển thị một lần --%>
            <% request.removeAttribute("messSuccess"); %>
            });
        </script>
        <% }%>
        <jsp:include page="/user/navigator/header.jsp"/> 
        <jsp:include page="/view/navSubHeaderCurriculum.jsp"/> 
        <div class="row" style="margin: 20px;">
            <div class="section-tittle text-center">
                <h2 style="margin-top: 20px;">Edit Content Group</h2>
            </div>
            <form class="edit-profile m-b30" action="contentgroup" method="post">
                <input hidden="" name="contentgroupId" value="${contentgroupId}">
                <input hidden="" name="action" value="edit">
                <p style="color: red">${messFail}</p>
                <table class="table table-bordered">
                    <tbody>
                        <tr>
                            <td style="width: 200px" >Curriculum Code:</td>
                            <td><b>${curriculum.getCode()}</b>
                                <input class="form-control" name="curId" id="curId" type="text" hidden="" value="${curId}" "></td>
                        </tr>
                        <tr>
                            <td style="width: 200px">Content Group ID:</td>
                            <td>${cg.getId()} </td>
                        </tr>
                        <tr>
                            <td style="width: 200px">Name:</td>
                            <td><input name="cname" type="text" placeholder="Enter content group name" value="${cname != null ? cname : cg.getName()}" 
                                       pattern="^.{1,100}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Can only enter maximum 100 charactes.' : '')" required=""></td>
                        </tr>
                        <tr>
                            <td style="width: 200px">English Name:</td>
                            <td><input name="ename" type="text" placeholder="Enter content group english name" value="${ename != null ? ename : cg.getEname()}" 
                                       pattern="^.{1,100}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Can only enter maximum 100 charactes.' : '')" required=""></td>
                        </tr>
                        <tr>
                            <td style="width: 200px">Display Order:</td>
                            <td><input name="order" type="number" placeholder="Enter display number" value="${order != null ? order : cg.getOrder()}" required=""></td>
                        </tr>
                    </tbody>                        
                </table>
                <div class="text-end">
                    <button type="submit" class="btn btn-primary" style="margin-bottom: 5px;" name="save">Save changes</button>
                </div>
            </form>
            <hr style="border: 1px solid gray; margin-top: 20px">
            <div class="section-tittle text-center">
                <h2 style="margin-top: 20px;">List Subject Content Group</h2>
            </div>
            <c:if test="${cg.getsList().size() == 0}">
                <h4 style="color: red; text-align: center; margin: 8px;">Cannot found any subjects of this content group!!!</h4>
                <div class="row" style="padding: 20px; display: flex;">
                    <div style="margin: auto;">
                        <img src="assets/img/list.jpg" alt="alt" width="300px"/>
                    </div>
                </div>
            </c:if>
            <c:if test="${cg.getsList().size() != 0}">
                <div class="progress-table-wrap" style="margin: 0 10px;">
                    <table class="table table-striped table-hover table-sortable" style="background-color: #f9f9ff">
                        <thead>
                            <tr>
                                <th>Subject Id</th>
                                <th>Subject Code</th>
                                <th>Subject Name</th>
                                <th>Semester</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${cg.getsList()}" var="s">
                                <tr>
                                    <td>${s.getId()}</td>
                                    <td><a href="subjectsDetail?subCode=${s.getId()}" style="color: blue">
                                            ${s.getCode()}
                                        </a></td>
                                    <td>
                                        ${s.getName()}
                                    </td>

                                    <td>${s.getSemester()}</td>
                                    <td>
                                        <a href="#confirmDeleteModal${contentgroupId}${s.getId()}"  data-toggle="modal">
                                            <i style="color:  red" class="fa fa-trash"></i>
                                        </a>
                                    </td>
                            <div class="modal fade " id="confirmDeleteModal${contentgroupId}${s.getId()}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="confirmDeleteModalLabel">Delete Confirmation</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="text-dark modal-body">
                                            Are you sure you want to delete?
                                        </div>
                                        <div class="modal-footer">
                                            <a href="contentgroup?action=remove&&contentgroupId=${contentgroupId}&&subjectId=${s.getId()}"><button type="submit" class="btn btn-danger">Delete</button></a>
                                            <button type="button" style="margin-right: 5px" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <div class="text-end">
                <a href="contentgroup?action=list&&curId=${curId}">
                    <button class="btn btn-primary" type="submit">Back</button>
                </a>
            </div>
        </div>
        <jsp:include page="/user/navigator/footer.jsp"/>      
        <!-- Scroll Up -->
        <div id="back-top" >
            <a title="Go to Top" href="#"> <i class="fas fa-level-up-alt"></i></a>
        </div>
        <!--Start Footer-->
        <jsp:include page="/user/navigator/footer.jsp"/>   
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
        <script>
    </body>
</html>
