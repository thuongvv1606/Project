<%-- 
    Document   : userprofile
    Created on : 23-05-2023, 14:05:21
    Author     : trinh
--%>
<%@page import="util.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Profile</title>
        
        <!--toast-->
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
        <style>
            body{margin-top:20px;
background-color:#f2f6fc;
color:#69707a;
}
.img-account-profile {
    height: 10rem;
}
.rounded-circle {
    border-radius: 50% !important;
}
.card {
    box-shadow: 0 0.15rem 1.75rem 0 rgba(33 40 50 / 15%);
    
}
.card .card-header {
    font-weight: 500;
}
.card-header:first-child {
    border-radius: 0.35rem 0.35rem 0 0;
}
.card-header {
    padding: 1rem 1.35rem;
    margin-bottom: 0;
    background-color: rgba(33, 40, 50, 0.03);
    border-bottom: 1px solid rgba(33, 40, 50, 0.125);
}
.form-control, .dataTable-input {
    display: block;
    width: 100%;
    padding: 0.875rem 1.125rem;
    font-size: 0.875rem;
    font-weight: 400;
    line-height: 1;
    color: #69707a;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #c5ccd6;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    border-radius: 0.35rem;
    transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

.nav-borders .nav-link.active {
    color: #0061f2;
    border-bottom-color: #0061f2;
}
.nav-borders .nav-link {
    color: #69707a;
    border-bottom-width: 0.125rem;
    border-bottom-style: solid;
    border-bottom-color: transparent;
    padding-top: 0.5rem;
    padding-bottom: 0.5rem;
    padding-left: 0;
    padding-right: 0;
    margin-left: 1rem;
    margin-right: 1rem;
}
        </style>
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
    </head>
    <body>
        <jsp:include page="/user/navigator/header.jsp"/> 
        <div class="container-xl px-4 mt-4">
            
            
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
    <!-- Account page navigation-->
    
    <hr class="mt-0 mb-4">
    <div class="row">
        <div class="col-xl-4">
            <!-- Profile picture card-->
            <div class="card mb-4 mb-xl-0">
                <div class="card-header">Profile Picture</div>
                <div class="card-body text-center">
                    <!-- Profile picture image-->
                    <img class="img-account-profile rounded-circle mb-2" src="http://bootdey.com/img/Content/avatar/avatar1.png" alt="">
                    <!-- Profile picture help block-->
                    <div class="small font-italic text-muted mb-4">JPG or PNG no larger than 5 MB</div>
                    <!-- Profile picture upload button-->
                    <button class="btn btn-primary" type="submit">Upload new image</button>
                </div>
            </div>
        </div>
        <div class="col-xl-8">
            <!-- Account details card-->
            <div class="card mb-4">
                <div class="card-header">Account Details</div>
                <div class="card-body">
                    <form class="form-default" action="${pageContext.request.contextPath}${Constants.URL_UPDATEUSERPROFILE}" method="POST">
                        <!-- Form Group (username)-->
                        <div class="mb-3">
                            <label class="small mb-1" for="inputUsername">Full name</label>
                            <p id="FNameError" style="color: red;"></p>
                            <input class="form-control" name="fullname" pattern="${Constants.HTML_REGEX__FULLNAME}" 
                                   required id="firstName"
                                   title="${Constants.HTML_TITLE_OF_REGEX__FULLNAME}" maxlength="30" type="text" value="${user.getFullName()}" placeholder="Enter your full name" >
                        </div>
                        <!-- Form Row-->
                        <div class="row gx-3 mb-3">
                            <!-- Form Group (first name)-->
                            <div class="col-md-6">
                                <label class="small mb-1" for="inputFirstName">User name</label>
                                <input class="form-control" name="username" pattern="${Constants.HTML_REGEX_USERNAME}"  type="text" 
                                       title="${Constants.HTML_TITLE_OF_REGEX__USERNAME}" value="${user.getUserName()}" 
                                       maxlength="25" placeholder="Enter your user name" >
                            </div>
                            <!-- Form Group (last name)-->
                            <div class="col-md-6">
                                
                            </div>
                        </div>
                        <!-- Form Row        -->
                        <div class="row gx-3 mb-3">
                            <!-- Form Group (organization name)-->
                            <div class="col-md-6">
                                <label class="small mb-1" for="inputOrgName">Company / Organization*</label>
                                <input class="form-control" maxlength="30" name="company" type="text" value="${user.getCompany()}" placeholder="Enter your Company" >
                            </div>
                            <!-- Form Group (location)-->
                            <div class="col-md-6">
                                <label class="small mb-1" for="inputLocation">Title*</label><br>
                                <input type="radio" name="title" id="radioMr" value="Mr"><label for="radioMr">Mr</label>  
                                <input type="radio" name="title" id="radioMrs" value="Mrs"><label for="radioMrs"> Mrs </label> 
                                <input type="radio" name="title" id="radioMs" value="Ms"><label for="radioMs"> Ms </label> 
                            </div>
                        </div>
                        <!-- Form Group (email address)-->
                        <div class="mb-1">
                            <label class="small mb-1" for="inputEmailAddress">Email address</label>
                            <input class="form-control" name="email" type="email" pattern="${Constants.HTML_REGEX_EMAIL}" 
                               title="${Constants.HTML_TITLE_OF_REGEX__EMAIL}"
                                   placeholder="Enter your email address" maxlength="30" value="${user.getEmail()}">
                        </div>
                        <!-- Form Row-->
                        <div class="row gx-3 mb-3">
                            <!-- Form Group (phone number)-->
                            <div class="col-md-6">
                                <label class="small mb-1" for="inputPhone">Phone number</label>
                                <input class="form-control" name="phone" type="tel" value="${user.getMobile()}"  pattern="${Constants.HTML_REGEX_MOBILE}" 
                               title="${Constants.HTML_TITLE_OF_REGEX__MOBILE}"
                                     maxlength="15"  placeholder="Enter your phone number">
                            </div>
                            <!-- Form Group (birthday)-->
                            
                        </div>
                        <!-- Save changes button-->
                        <button class="btn btn-primary" name="save"  type="submit">Save changes</button> <p>${mess}</p>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
        <jsp:include page="/user/navigator/footer.jsp"/>  
        
        <script>
  // Lấy dữ liệu từ nguồn nào đó, ví dụ từ API hoặc từ input khác trên trang web
  var titleData = "${user.getTitle()}"; // Giả sử dữ liệu là "Mrs"

  // Kiểm tra và tích vào radio button tương ứng với dữ liệu
  if (titleData === "Mr") {
    document.getElementById("radioMr").checked = true;
  } else if (titleData === "Mrs") {
    document.getElementById("radioMrs").checked = true;
  } else if (titleData === "Ms") {
    document.getElementById("radioMs").checked = true;
  }
</script>
        <script>
            function validateForm() {
                var firstNameInput = document.getElementById('firstName').value;
                var password = document.getElementById("password").value;
                var confirmPassword = document.getElementById("confirmPassword").value;
                var passwordError = document.getElementById("passwordError");
                var FNameError = document.getElementById("FNameError");

                var check = true;
                if (firstNameInput.includes('  ')) {
                    // Nếu có, ngăn không cho form được submit
                    check = false; // Ngăn form được gửi đi
                    FNameError.textContent = "Do not enter space duplicate";
                    // Hiển thị thông báo lỗi cho người dùng
                    //alert('Không được nhập khoảng trắng 2 lần.');
                }
                if (password !== confirmPassword) {
                    passwordError.textContent = "Confirmation password does not match";
                    check = false; // Ngăn form được gửi đi
                } else {
                    passwordError.textContent = ""; // Xóa thông báo lỗi (nếu có)
                    check = true; // Cho phép form được gửi đi
                }

                return check;
            }
        </script>
    </body>
</html>
