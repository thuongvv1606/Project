<%-- 
    Document   : curriculumList
    Created on : May 25, 2023, 9:26:03 PM
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
        <title>Decision Detail</title>

        <!--toast-->
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>


        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
            .action {
                background-color: black;
                border-radius: 5px;
            }
            input {
                background-color: black;
                border-radius: 5px;
            }

            .table-bordered td:first-child {
                text-align: left;
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
        </style>

        <!--toast-->
        <style>
            .custom-toast {
                top: 100px !important; /* Điều chỉnh khoảng cách từ top */
            }
        </style>

    </head>
    <body>
        <!--toast-->
        <% if (request.getAttribute("messSuccess") != null) {%>
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

        <!--Start Header-->
        <jsp:include page="/user/navigator/header.jsp"/>  
        <main>
            <!--End Header-->
            <div class="section-tittle text-center">
                <h2 style="margin-top: 20px;">Decision Detail</h2>
            </div>
            <div class="container" style="">
                <form class="form-contact contact_form" action="decisionDetail" method="POST" >
                    <div style="width: 100%; height: auto">
                        <h2 style="color:green; text-align: center;">${message}</h2>
                        <h2 style="color:red; text-align: center;">${error}</h2>
                        <input name="decisionId" type="text" value="${decision.getId()}" hidden="">
                        <div class="col-12">
                            <div class="form-group">
                                <label>Decision No*</label>
                                <input class="form-control" readonly="" name="decisionNo" id="decisionNo" type="text" value="${decision.getDecision_no()}" >
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-group">
                                <label>Decision Name</label>
                                <input class="form-control" name="decisionName" id="name" type="text" value="${decision.getDecision_name()}" >
                            </div>
                        </div>

                        <div class="col-12">
                            <div class="form-group">
                                <label>Decision Date</label>
                                <input class="form-control" name="decisionDate" id="decisionDate" type="Date" value="${decision.getDecision_date()}" >
                            </div>
                        </div>
                        <div>
                            <div style="margin: 10px">
                                <div class="form-group">
                                    <input type="radio" name="active" value="1"${decision.isIs_active() eq true ? "checked" : ""}/> <label>Active </label>
                                    <input type="radio" name="active" value="0"${decision.isIs_active() eq false ? "checked" : ""}/> <label>De-Active </label>
                                </div>
                            </div>


                            <div style="display: flex; justify-content: space-between; margin: 50px 100px">
                                <div>
                                    <button type="submit" name="oki" value="oki" class="button button-contactForm boxed-btn">Edit</button>
                                </div>
                                <div>
                                    <a href="decisionController"><b>Decision List</b></a>
                                </div>
                            </div>

                        </div>
                    </div>

                </form>
            </div>
            <hr style="border: 1px solid gray;">
            <div class="section-tittle text-center">
                <h2>List Curriculum of Decision</h2>
            </div>
            <c:if test="${curriculumList.size() == 0}">
                <h4 style="color: red; text-align: center; margin: 8px;">Cannot found any Curriculum of this Decision!!!</h4>
                <div class="row" style="padding: 20px; display: flex;">
                    <div style="margin: auto;">
                        <img src="assets/img/list.jpg" alt="alt" width="300px"/>
                    </div>
                </div>
            </c:if>
            <c:if test="${curriculumList.size() != 0}">
                <div class="progress-table-wrap" style="margin: 0 10px;">
                    <table class="table table-striped table-hover table-sortable" style="background-color: #f9f9ff">
                        <thead>
                            <tr>
                                <th>Curriculum Code</th>
                                <th>Curriculum Name</th>
                                    <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                                    <th>Actions</th>
                                    </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${curriculumList}" var="c">
                                <tr>
                                    <td><a href="${pageContext.request.contextPath}${Constants.URL_CURRICULAOVERVIEW}?action=detail&curId=${c.getCurriculumID()}" style="color: blue">
                                            ${c.getCode()}
                                        </a></td>
                                    <td>
                                        ${c.getName()}
                                    </td>

                                    <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                                        <td>
                                            <a href="#confirmDeleteModal${c.getCurriculumID()}"  data-toggle="modal">
                                                <i style="color:  red" class="fa fa-trash"></i>
                                            </a>
                                        </td>
                                    </c:if>
                            <div class="modal fade " id="confirmDeleteModal${c.getCurriculumID()}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
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
                                            <a href="decisionDetail?decisionId=${decision.getId()}&curId=${c.getCurriculumID()}"><button type="submit" class="btn btn-danger">Delete</button></a>
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
            <hr style="border: 1px solid gray;">
            <div class="section-tittle text-center">
                <h2>List Syllabus of Decision</h2>
            </div>
            <c:if test="${syllabusList.size() == 0}">
                <h4 style="color: red; text-align: center; margin: 8px;">Cannot found any Syllabus of this Decision!!!</h4>
                <div class="row" style="padding: 20px; display: flex;">
                    <div style="margin: auto;">
                        <img src="assets/img/list.jpg" alt="alt" width="300px"/>
                    </div>
                </div>
            </c:if>
            <c:if test="${syllabusList.size() != 0}">
                <div class="progress-table-wrap" style="margin: 0 10px;">
                    <table class="table table-striped table-hover table-sortable" style="background-color: #f9f9ff">
                        <thead>
                            <tr>
                                <th>Syllabus Code</th>
                                <th>Syllabus Name</th>
                                    <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                                    <th>Actions</th>
                                    </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${syllabusList}" var="s">
                                <tr>
                                    <td>
                                        ${s.getSyllabusId()}
                                    </td>
                                    <td><a href="syllabus?action=detail&sylId=${s.getSyllabusId()}" style="color: blue">
                                            ${s.getName()}
                                        </a>

                                    </td>

                                    <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                                        <td>
                                            <a href="#confirmDeleteModal${s.getSyllabusId()}"  data-toggle="modal">
                                                <i style="color:  red" class="fa fa-trash"></i>
                                            </a>
                                        </td>
                                    </c:if>
                            <div class="modal fade " id="confirmDeleteModal${s.getSyllabusId()}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
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
                                            <a href="decisionDetail?decisionId=${decision.getId()}&syllabusId=${s.getSyllabusId()}"><button type="submit" class="btn btn-danger">Delete</button></a>
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
        </main>
        <!--Start Footer-->
        <jsp:include page="/user/navigator/footer.jsp"/>   
        <!--End Footer-->
        <script src="js/manager.js" type="text/javascript"></script>
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
            function sortTableByColumn(table, column, asc = true) {
                const dirModifier = asc ? 1 : -1;
                const tBody = table.tBodies[0];
                const rows = Array.from(tBody.querySelectorAll("tr"));

                // Sort each row
                const sortedRows = rows.sort((a, b) => {
                    const aColText = a.querySelector(`td:nth-child(${column + 1})`);
                    const bColText = b.querySelector(`td:nth-child(${column + 1})`);

                    return aColText > bColText ? (1 * dirModifier) : (-1 * dirModifier);
                });

                // Remove all existing TRs from the table
                while (tBody.firstChild) {
                    tBody.removeChild(tBody.firstChild);
                }

                // Re-add the newly sorted rows
                tBody.append(...sortedRows);

                // Remember how the column is currently sorted
                table.querySelectorAll("th").forEach(th => th.classList.remove("th-sort-asc", "th-sort-desc"));
                table.querySelector(`th:nth-child(${column + 1})`).classList.toggle("th-sort-asc", asc);
                table.querySelector(`th:nth-child(${column + 1})`).classList.toggle("th-sort-desc", !asc);
            }

            document.querySelectorAll(".table-sortable th").forEach(headerCell => {
                headerCell.addEventListener("click", () => {
                    const tableElement = headerCell.parentElement.parentElement.parentElement;
                    const headerIndex = Array.prototype.indexOf.call(headerCell.parentElement.children, headerCell);
                    const currentIsAscending = headerCell.classList.contains("th-sort-asc");

                    sortTableByColumn(tableElement, headerIndex, !currentIsAscending);
                });
            });
        </script>
    </body>
</html>
