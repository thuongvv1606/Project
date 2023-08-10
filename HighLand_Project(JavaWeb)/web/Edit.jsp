
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="CSS/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
    <body>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Edit <b>Product</b></h2>
                        </div>
                        <div class="col-sm-6">
                        </div>
                    </div>
                </div>
            </div>
            <div id="editEmployeeModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="edit" method="post">
                            <div class="modal-header">						
                                <h4 class="modal-title">Add Product</h4>
                                <button type="submit" class="close" name="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>Product ID</label>
                                    <input value="${product.getProductID()}" name="productID" type="text" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>Product Name</label>
                                    <input value="${product.getProductName()}" name="productName" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Price</label>
                                    <input <input value="${product.getUnitPrice()}" name="price" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Stock</label>
                                    <input <input value="${product.getUnitsInStock()}" name="stock" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Image</label>
                                    <input <input value="${product.getImage()}" name="image" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Discontinued</label>
                                    <input <input value="${product.isDiscontinued() }" name="discontinued" type="text" class="form-control" required>
                                </div>
                                <!--                                <div class="form-group">
                                                                    <label>Category</label>
                                                                    <input <input value="$product.getCategoryID()}" name="categoryID" type="text" class="form-control"  readonly required>
                                                                </div>-->
                                <div class="form-group">
                                    <label>Category</label>
                                    <select name="categoryID" class="form-select" aria-label="Default select example">
                                        <c:forEach items="${listC}" var="o">
                                            <c:if test="${product.getCategoryID() eq o.getCategoryID()}">
                                                <!--                                                <input type="text" name="" value="$o.getCategoryName()}">-->
                                                <option value="${o.getCategoryID()}">${o.getCategoryName()}</option>
                                            </c:if>

                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-success" name="edit" value="Edit">
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>


        <script src="js/manager.js" type="text/javascript"></script>
    </body>
</html>