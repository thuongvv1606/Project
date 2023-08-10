<%-- 
    Document   : poList
    Created on : Jun 8, 2023, 6:31:46 PM
    Author     : LanChau
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="util.Constants"%>
<!doctype html>
<html class="no-js" lang="zxx">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <meta http-equiv="Content-Language" content="vi">
        <title></title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="manifest" href="site.webmanifest">
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
        
        
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
        
        <% if (session.getAttribute("messSuccess") != null) {%>
        <script>
            $(document).ready(function () {
                // T?o toast thông báo và tùy ch?nh màu s?c
                toastr.options = {
                    "positionClass": "toast-top-right",
                    "closeButton": true,
                    "progressBar": true,
                    "timeOut": 5000, // Th?i gian hi?n th? toast (tính b?ng mili giây)
                    "extendedTimeOut": 2000, // Th?i gian hi?n th? ti?p t?c n?u ng??i dùng di chu?t qua toast (tính b?ng mili giây)
                    "tapToDismiss": false, // Ng?n ng??i dùng t?t toast b?ng cách nh?p chu?t
                    "preventDuplicates": true, // Ng?n toast trùng l?p
                    "progressBarColor": "#FF3366", // Màu c?a thanh ti?n trình
                    "toastClass": "custom-toast" // L?p CSS tùy ch?nh cho toast
                };

                // L?y thông báo t? ??i t??ng request
                var message = '<%= session.getAttribute("messSuccess")%>';

                // Hi?n th? toast thành công
                toastr.success(message);

            <%-- Xóa thông báo trong ??i t??ng request ?? ??m b?o ch? hi?n th? m?t l?n --%>
            <% session.removeAttribute("messSuccess"); %>
            });
            
           
        </script>
        <% }%>
        
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
            <jsp:include page="/view/navSubHeaderCurriculum.jsp"/>  
            <div class="courses-area section-padding40 fix">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-xl-7 col-lg-8">
                            <div class="section-tittle text-center mb-55">
                                <h2>PO List</h2>
                            </div>
                        </div>

                    </div>
                    <div class="p-2 w-100 bd-highlight">
                        <form class="row g-3" action="${pageContext.request.contextPath}${Constants.URL_POLIST}" method="get">
                            <input hidden name="idCurrent" value="${idCurrent}"/>
                            <div class="col-auto">
                                <input type="text"  value="${name}" 
                                       class="form-input" style="padding: 5px 20px"   id="inputSearch" 
                                       name="name" placeholder="Input name ...">
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
                                    <input hidden name="idCurrent" value="${id}"/>
                                    <a href="${pageContext.request.contextPath}${Constants.URL_ADDPO}?idCurrent=${idCurrent}" class="btn btn-sm btn-success ">Add new Po</a>
                                    <a href="#confirmImportModal" data-toggle="modal">
                                        <input class="action btn" type="submit" value="Import File" name="import">
                                    </a>
                                    <!--import file-->



<!--<a href="${pageContext.request.contextPath}${Constants.URL_ADDPO}?idCurrent=${idCurrent}" >Import Po</a>-->

                                </span>

                                <c:if test="${listPOFail != null}">


                                    <div class="modal fade  in show " id="confirmDeactiveModal0" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteMo2l"  style="display: block; padding-right: 0px;">
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

                                                            <a href="${pageContext.request.contextPath}${Constants.URL_POLIST}?idCurrent=${idCurrent}"><button type="button" class="btn btn-danger">Cancle</button></a>

                                                        </div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div class="text-dark modal-body">
                                                            <p class="text-success" >${countOk} PO add success</p>
                                                            <p class="text-danger"> ${countFaillFormat} wrong format or Name Duplicated</p>
                                                            <p class="text-warning">${listClosFail.size()} Duplicated ( List Duplicated )</p>


                                                        </div>
                                                        <div class="modal-footer">
                                                            <a href="#confirmImportModal" data-toggle="modal">
                                        <input class="action btn" type="submit" value="Import Again File" name="import">
                                    </a>

                                                            <a href="${pageContext.request.contextPath}${Constants.URL_POLIST}?idCurrent=${idCurrent}"><button type="button" class="btn btn-danger">Cancle</button></a>

                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>



                                            </div>
                                        </div>
                                    </div>
                                </c:if> 
                            </c:if>
                        </div> 
                    </div>
                    <div>
                        <table class="table table-striped" cellspacing="0" rules="all" border="1" id="gvSubs"
                               style="border-collapse:collapse;">
                            <tbody>
                                <tr class="bg-warning">
                                    <th class="text-white" scope="col">ID</th>
                                    <th class="text-white" scope="col">Name</th>

                                    <c:if test="${sessionScope.user.getRoleFunction() == 2 }">
                                        <th class="text-white" scope="col">Active</th>
                                        <th class="text-white" scope="col">Function</th>
                                        </c:if>
                                </tr>
                                <c:forEach var="item" items="${listPo}" varStatus="loop">

                                    <c:if test="${sessionScope.user.getRoleFunction() != 2 && item.isActive == true }">
                                    <td>${item.poId}</td>
                                    <td>
                                        <a style="color: black" href="${pageContext.request.contextPath}${Constants.URL_DETAILPO}?idPo=${item.poId}&idCurrent=${idCurrent}"> ${item.poName}</a>
                                    </td>
                                </c:if>
                                <c:if test="${sessionScope.user.getRoleFunction() == 2 }">
                                    <td>${item.poId}</td>
                                    <td>
                                        <a style="color: black" href="${pageContext.request.contextPath}${Constants.URL_DETAILPO}?idPo=${item.poId}&idCurrent=${idCurrent}"> ${item.poName}</a>
                                    </td> 
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.isActive}" >
                                                <span style="color: green">Active</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span style="color: red">Block</span>
                                            </c:otherwise>
                                        </c:choose>

                                    </td>
                                    <td class="text-white" scope="col">

                                        <a href="${pageContext.request.contextPath}${Constants.URL_EDITPO}?idPo=${item.poId}&idCurrent=${idCurrent}">

                                            <i style="color:  blue" class="fa fa-edit"></i>
                                            </button>
                                        </a>
                                        <a href="#confirmDeleteModal${loop.index}"  data-toggle="modal">
                                            <i style="color:  red" class="fa fa-trash"></i>
                                        </a>
                                        <form action="${pageContext.request.contextPath}${Constants.URL_UPDATEPO}" method="post">
                                            <div class="modal fade " id="confirmDeleteModal${loop.index}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="confirmDeleteModalLabel">Delete Confirmation</h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="text-dark modal-body">
                                                            Are you sure you want to block?
                                                        </div>
                                                        <input type="text" hidden value="${idCurrent}" name="idCurrent" />
                                                        <input type="text" hidden value="${item.poId}" name="idPo" />
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
                <div class="modal fade " id="confirmImportModal" tabindex="-1" role="dialog" aria-hidden="true" enctype="multipart/form-data">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="confirmDeleteModalLabel">Import PO</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <!-- Modal body -->
                            <div class="text-dark modal-body">
                                <form action="${pageContext.request.contextPath}${Constants.URL_IMPORTEXCELPO}" id="fExcel" enctype="multipart/form-data" method="POST" >
                                    <div class="d-flex align-items-center">
                                        <a href="assets/excel/template-POimport.xlsx" download style="text-decoration: none;color: #00a8ff">Click here to download template</a><br>
                                        <input type="file" name="file" id="file"/>
                                        <button type="submit" class="btn btn-primary mb-3" name="importBtn">Import</button>

                                        <input   name="idCurrent" hidden value="${idCurrent}"/>
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