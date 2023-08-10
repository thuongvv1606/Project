<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<!doctypehtml>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="CSS\sign.css">
    </head>
    <body>
    <body>
        <section class="vh-150 bg-image"
                 style="background-image: url('images/banner.png');">
            <div class="mask d-flex align-items-center h-150 gradient-custom-3">
                <div class="container h-150">
                    <div class="row d-flex justify-content-center align-items-center h-150">
                        <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                            <div class="card" style="border-radius: 15px;">
                                <div class="card-body p-5">
                                    <h2 class="text-uppercase text-center mb-5">Forget Password</h2>
                                    <p class="text-danger" style="text-align: center"><label class="user" style="color: red">${check1}</label></p>
                                    <form action="forget" method="post">

                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="form3Example1cg">Account</label>
                                            <input type="text" id="form3Example1cg" class="form-control form-control-lg" name="account" value="${s1.getAccount()}"/>
                                        </div>

                                        <!--                                        <div class="form-outline mb-4">
                                                                                    <label class="form-label" for="form3Example4cg">Password</label>
                                                                                    <input type="password"  name="password" value="$s1.getPassword()}" id="form3Example4cg" class="form-control form-control-lg" />
                                        
                                                                                </div>
                                        
                                                                                <div class="form-outline mb-4">
                                                                                    <label class="form-label" for="form3Example4cdg">Repeat your password</label>
                                                                                    <input type="password" name="repassword" value="$s1.getPassword()}" id="form3Example4cdg" class="form-control form-control-lg" />
                                        
                                                                                </div>-->
                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="form3Example4cdg">Name</label>
                                            <input type="text" name="name" value="${s1.getName()}" id="form3Example4cdg" class="form-control form-control-lg" />

                                        </div>
                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="form3Example4cdg">Phone number</label>
                                            <input type="text" name="phone" value="${s1.getPhone()}" id="form3Example4cdg" class="form-control form-control-lg" />

                                        </div>
                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="form3Example4cdg">Gender</label>
                                            <input type="text" name="gender" value="${s1.isGender()}" id="form3Example4cdg" class="form-control form-control-lg" />

                                        </div>
                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="form3Example4cdg">Address</label>
                                            <input type="text" name="address"  value="${s1.getAddress()}" id="form3Example4cdg" class="form-control form-control-lg" />

                                        </div>
                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="form3Example4cdg">Birth of day</label>
                                            <input type="Date" name="dob" value="${s1.getDob()}" id="form3Example4cdg" class="form-control form-control-lg" />

                                        </div>
                                        <c:if test="${s1.getPassword() != null}" >
                                            <div class="alert alert-success" role="alert">
                                                Password: ${s1.getPassword()}
                                            </div>
                                        </c:if>

                                        <div class="d-flex justify-content-center">
                                            <button type="submit"
                                                    class="btn btn-success btn-block btn-lg gradient-custom-4 text-body" name="forgot">PASSWORD </button>
                                        </div>

                                        <p class="text-center text-muted mt-5 mb-0">Have already an account? <a href="Login.jsp"
                                                                                                                class="fw-bold text-body"><u>Login here</u></a></p>
                                    </form>


                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
