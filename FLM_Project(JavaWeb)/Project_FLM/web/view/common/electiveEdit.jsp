<%-- 
    Document   : electiveAdd
    Created on : Jun 5, 2023, 11:35:57 PM
    Author     : TT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Elective Edit</title>
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!--toast-->
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
        <!-- CSS here -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
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
            .input {
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
            <form action="elective" method="post">
                <input type="hidden" name="action" value="edit">
                <input type="hidden" name="curId" value="${id}">
                <input type="hidden" name="electiveId" value="${e.getId()}">
                <div class="section-tittle text-center">
                    <h2 style="margin-top: 20px;">Elective Edit</h2>
                </div>
                <p style="color: red;">${messFail}</p> 
                <table class="table table-bordered">
                    <tbody>
                        <tr>
                            <td style="width: 200px" >Curriculum Code:</td>
                            <td><b>${curriculum.getCode()}</b>
                                <input class="form-control" name="curId" id="curId" type="text" hidden="" value="${curId}" "></td>
                        </tr>
                        <tr>
                            <td style="width: 200px">Elective ID:</td>
                            <td>${e.getId()}</td>
                        </tr>
                        <tr>
                            <td style="width: 200px">Elective Code:</td>
                            <td><select name="ecode">
                                    <c:if test="${e.getCode() != null}">
                                        <option value="${e.getCode()}">${e.getCode()}</option>
                                    </c:if>
                                    <c:forEach items="${sList}" var="s">
                                        <option value="${s.getCode()}" ${ecode != null ? (ecode eq s.getCode() ? "selected" : "") : (s.getCode() eq ecode ? "selected" : "")} required>${s.getCode()}</option>
                                    </c:forEach>
                                </select></td>
                        </tr>
                        <tr>
                            <td style="width: 200px">Name:</td>
                            <td><input class="input" name="ename" type="text" placeholder="Enter elective name" value="${ename != null ? ename : e.getName()}" 
                                       pattern="^.{1,100}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Can only enter maximum 100 charactes.' : '')" required=""></td>
                        </tr>
                        <tr>
                            <td style="width: 200px">Description:</td>
                            <td><input class="input" name="edes" type="text" placeholder="Enter elective description" value="${edes != null ? edes : e.getDescription()}" 
                                       pattern="^.{0,300}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Can only enter maximum 300 charactes.' : '')"></td>
                        </tr>
                        <tr>
                            <td style="width: 200px">Status:</td>
                            <td>
                                <input name="status" type="radio" value="1" ${e.isActive() ? "checked" : ""}>Active
                                <input name="status" type="radio" value="0" ${!e.isActive() ? "checked" : ""}>Dormant
                            </td>
                        </tr>
                    </tbody>                        
                </table>
                <c:if test="${user.getRoleFunction() == 2}">
                    <div class="text-end">
                        <button type="submit" class="btn btn-primary" style="margin-bottom: 5px;" name="save">Save changes</button>
                    </div>
                </c:if>
            </form>
            <hr style="border: 1px solid gray; margin-top: 20px">
            <div class="section-tittle text-center">
                <h2 style="margin-top: 20px;">List Subject Elective</h2>
            </div>
            <c:if test="${e.getSubject().size() == 0}">
                <h4 style="color: red; text-align: center; margin: 8px;">Cannot found any subjects of this elective!!!</h4>
                <div class="row" style="padding: 20px; display: flex;">
                    <div style="margin: auto;">
                        <img src="assets/img/list.jpg" alt="alt" width="300px"/>
                    </div>
                </div>
            </c:if>
            <c:if test="${e.getSubject().size() != 0}">
                <div class="progress-table-wrap" style="margin: 0 10px;">
                    <table class="table table-striped table-hover table-sortable" style="background-color: #f9f9ff">
                        <thead>
                            <tr>
                                <th>Subject Id</th>
                                <th>Subject Code</th>
                                <th>Subject Name</th>
                                <th>Semester</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${e.getSubject()}" var="s">
                                <tr>
                                    <th>${s.getId()}</th>
                                    <td><a href="subjectsDetail?subCode=${s.getId()}&curriculumID=${curriculum.curriculumID}" style="color: blue">
                                            ${s.getCode()}
                                        </a></td>
                                    <td>
                                        ${s.getName()}
                                    </td>

                                    <td>${s.getSemester()}</td>
                                    <td><a href="#confirmDeleteModal${e.getId()}${s.getId()}"  data-toggle="modal">
                                            <i style="color:  red" class="fa fa-trash"></i>
                                        </a>
                                    </td>
                            <div class="modal fade " id="confirmDeleteModal${e.getId()}${s.getId()}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
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
                                            <a href="elective?action=remove&&electiveId=${e.getId()}&&subjectId=${s.getId()}"><button type="submit" class="btn btn-danger">Delete</button></a>
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
            <a href="elective?action=list&&curId=${curId}">
                <button class="btn btn-primary" type="submit">Back</button> 
            </a>
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
