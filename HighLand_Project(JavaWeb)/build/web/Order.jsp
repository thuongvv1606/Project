<%-- 
    Document   : Order
    Created on : Mar 12, 2023, 4:22:42 PM
    Author     : hp
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="left_body_up">
            <table border="0">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th style="width: 215px">Name</th>
                        <th style="width: 70px">Quantity</th>
                        <th>Price</th>
                        <th>money</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:set value="${sessionScope.cart}" var="o"/>
                    <c:set var="t" value="0"/>
                    <c:forEach items="${o.items}" var="i">
                        <c:set var="t" value="${t+1}"/>



                        <tr>
                            <td style="font-size: 16px">${t}</td>
                            <td style="font-size: 16px">${i.getProduct().getProductName()}</td>
                            <td>
                                <button><a href="process?num=-1&id=${i.getProduct().getProductID()}">-</a></button>
                                <input style="size: 10px; width: 10px" type="text" readonly value="${i.getQuantity()}">
                                <button><a href="process?num=1&id=${i.getProduct().getProductID()}">+</a></button>
                            </td>
                            <td><fmt:formatNumber pattern="##.#" value="${i.getPrice()}"/></td>
                            <td><fmt:formatNumber pattern="##.#" value="${i.getQuantity()*i.getPrice()}"/></td>
                            <td>
                                <form action="process" method="post">
                                    <input type="hidden" name="id" value="${i.getProduct().getProductID()}">
                                    <input type="submit" value="X" style="background: red">
                                </form>
                            </td>
                        </tr>

                    </c:forEach>
                </tbody>
            </table>
        </div>
        <!--                <form action="checkout" method="post">
                            <input type="submit" value="check out">
                        </form>-->

        <div class="left_body_down" >
            <B>Total:${o.getTotalMoney()*1}$</B><BR>
            <B>VAT:${o.getTotalMoney() *0.02}$</B><BR>
            <c:set value="${sessionScope.size}" var="size"></c:set>
            <p style="font-size: 30px; margin: 0px; text-align: left;margin-top: 10px">Order iterm :(${size*1})&nbsp;&nbsp;&nbsp;&nbsp;Total:${(o.getTotalMoney() + (o.getTotalMoney()*0.02))}0$</p>
        </div>
        <!--        
                số sản phẩm mua: ($size})
                <a href="home">về mua tiếpa</a>-->
    </body>
</html>
