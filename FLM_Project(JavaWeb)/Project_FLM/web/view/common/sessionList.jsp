<%-- 
    Document   : curriculumList
    Created on : May 25, 2023, 9:26:03 PM
    Author     : TT
--%>
<%@page import="util.Constants"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Session List</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="manifest" href="site.webmanifest">
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">

       

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
         
        <style>
            .custom-toast {
                top: 100px !important; /* Điều chỉnh khoảng cách từ top */
            }
        </style><!-- comment -->
    </head>
    <body>
         <% if (session.getAttribute("messSuccess") != null) {%>
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
                var message = '<%= session.getAttribute("messSuccess")%>';

                // Hiển thị toast thành công
                toastr.success(message);

            <%-- Xóa thông báo trong đối tượng request để đảm bảo chỉ hiển thị một lần --%>
            <% session.removeAttribute("messSuccess"); %>
            });
            
           
        </script>
        <% }%>
        
        <% if (session.getAttribute("messFail") != null) {%>
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
                var message = '<%= session.getAttribute("messFail")%>';

                // Hiển thị toast thành công
                toastr.error(message);

            <%-- Xóa thông báo trong đối tượng request để đảm bảo chỉ hiển thị một lần --%>
            <% session.removeAttribute("messFail"); %>
            });
            
           
        </script>
        <% }%>
        <jsp:include page="/user/navigator/header.jsp"/>  
        <main>
            <jsp:include page="/user/navigator/navSubHeaderSyllabus.jsp"/> 
            <div class="section-tittle text-center">
                <h2 style="margin-top: 20px;">Session List</h2>
            </div>
            <div class="container">
                <form action="${pageContext.request.contextPath}${Constants.URL_SESSIONLIST}" method="GET">
                    <input hidden name="syllabusId" value="${id}"/>
                    <div class="row" style="display: flex;">
                        <div style="margin: auto;">
                            <input type="text" name="key" class="btn btn-success" style="background-color: white; color: black; height: 30px; border: 2px solid #c86fff;">
                            <input type="submit" name="search_btn" class="btn" style="padding: 5px; letter-spacing: unset;" value="Search">
                        </div>
                    </div>
                    <div class="row" style="padding: 20px 20px 0px 20px; display: flex;">
                        <div style="margin: auto;">

                        </div>
                    </div>
                </form>
                <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                    <div class="">
                        <a href="${pageContext.request.contextPath}${Constants.URL_SESSIONADD}?syllabusId=${id}">
                            <input class="action btn" type="submit" value="Add Session" name="add" style="margin-left: 30px;">
                        </a>
                            <a href="#confirmImportModal" data-toggle="modal">
                            <input class="action btn" type="submit" value="Import File" name="import">
                        </a>
                        
                    </div>

                    <c:if test="${listClosFail != null}">
                        <div class="modal fade  in show " id="confirmDeactiveModal0" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" >
                            <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="confirmDeleteModalLabel">Confirmation</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">

                                        </button>
                                    </div>

                                    <c:choose>
                                        <c:when test="${checkSheet}">

                                            <div class="text-dark modal-body">
                                                <p class="text-danger">You need have Sheet SessionList in file excel</p>
                                            </div>
                                            <div class="modal-footer">
                                                <a href="#confirmImportModal" data-toggle="modal">
                            <input class="action btn" type="submit" value="Import Again File" name="import">
                        </a>

                                                <a href="${pageContext.request.contextPath}${Constants.URL_SESSIONLIST}?syllabusId=${id}"><button type="button" class="btn btn-danger">Cancle</button></a>

                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="text-dark modal-body">
                                                <p class="text-success" >${countOk} Session add success</p>
                                                <p class="text-danger"> ${countFaillFormat} wrong format or Topic Duplicated</p>
                                                <p class="text-warning">${listClosFail.size()} Duplicated ( List Duplicated )</p>
                                                

                                            </div>
                                            <div class="modal-footer">
                                                <a href="#confirmImportModal" data-toggle="modal">
                            <input class="action btn" type="submit" value="Import Again File" name="import">
                        </a>

                                                <a href="${pageContext.request.contextPath}${Constants.URL_SESSIONLIST}?syllabusId=${id}"><button type="button" class="btn btn-danger">Cancle</button></a>

                                            </div>
                                        </c:otherwise>
                                    </c:choose>



                                </div>
                            </div>
                        </div>
                    </c:if> 
                </c:if>
                <c:if test="${listSession.size() == 0}">
                    <h2 style="color: red; text-align: center;">Cannot found any Session!!!</h2>
                    <div class="row" style="padding: 20px; display: flex;">
                        <div style="margin: auto;">
                            <img src="assets/img/list.jpg" alt="alt" width="300px"/>
                        </div>
                    </div>
                </c:if>
                <c:if test="${listSession.size() != 0}">
                    <br>
                    <div class="hint-text" style="margin-left: 20px; color: green;">Found <b>${count}</b> Session(s)</div>
                    <br>
                    <div class="progress-table-wrap" style="margin: 0 10px;">
                        <table class="table table-striped table-hover table-sortable" style="background-color: #f9f9ff">
                            <thead>
                                <tr>
                                    <th>Session</th>
                                    <th>Topic</th>
                                    <th>Learning type</th>
                                    <th>LO</th><!-- <th>Learning type</th> -->
                                    <th>ITU</th>
                                    <th>Student materials</th>
                                    <th>Student's Task</th>
                                    <th>URL</th>
                                        <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                                        <th>Actions</th>
                                        </c:if>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listSession}" var="c">
                                    <tr>
                                        <td>${c.getSession_id()}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}${Constants.URL_SESSIONDETAIL}?sessionId=${c.getSession_id()}" style="color: blue;">
                                                ${c.getTopic()}
                                            </a>
                                        </td>
                                        <td>${c.getLearning_type()}</td><!-- comment -->
                                        <td>${c.getLo()}</td>
                                        <td>${c.getItu()}</td>
                                        <td>${c.getStudent_materials()}</td>
                                        <td>${c.getStudent_task()} </td>
                                        <td>${c.getUrl()} </td>
                                        <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                                            <td>
                                                <a href="${pageContext.request.contextPath}${Constants.URL_SESSIONEDIT}?sessionId=${c.getSession_id()}&syllabusId=${c.getSyllabus_id()}">
                                                    <i style="color: blue" class="fa fa-edit"></i>
                                                </a>

                                            </td>
                                        </c:if>
                                        </div>
                                        </div>
                                        </div>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="clearfix" style="margin: 0 10px;">
                        <ul class="pagination">
                            <c:forEach begin="1" end="${end}" var="i">
                                <li class="page-item"><a href="${pageContext.request.contextPath}${Constants.URL_SESSIONLIST}?syllabusId=${id}&index=${i}" class="page-link">${i}</a></li>
                                </c:forEach>
                        </ul>
                    </div>
                </c:if>
            </div>
            
        </main>
       
                    <div class="modal fade " id="confirmImportModal" tabindex="-1" role="dialog" aria-hidden="true" enctype="multipart/form-data">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="confirmDeleteModalLabel">Import Session</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <!-- Modal body -->
                    <div class="text-dark modal-body">
                        <form action="${pageContext.request.contextPath}${Constants.URL_UPLOADEXCELSESSION}" id="fExcel" enctype="multipart/form-data" method="POST" >
                            <div class="d-flex align-items-center">
                                <a href="assets/excel/template-session.xlsx" download style="text-decoration: none;color: #00a8ff">Click here to download template</a><br>
                                <input type="file" name="file" id="file"/>
                                <button type="submit" class="btn btn-primary mb-3" name="importBtn">Import</button>
                            <input hidden type="file" id="file" name="file" accept=".xlsx, .xls">
                            <input hidden name="syllabusId" value="${id}"/>
                            <script>
                            const importButton = document.getElementById('importButton');
                            const fileInput = document.querySelector('#fExcel input[type="file"]');
                            const form = document.getElementById('fExcel');

                            importButton.addEventListener('click', () => {
                                fileInput.click();
                            });
                            fileInput.addEventListener('change', () => {
                                form.submit();
                            });
                        </script>
                            </div>
                        </form>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" style="margin-right: 5px" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    </div>
                </div>
            </div>
        </div>
        <!--Start Footer-->
        <jsp:include page="/user/navigator/footer.jsp"/>   
        <!--End Footer-->
        <!--Scroll Up--> 
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
