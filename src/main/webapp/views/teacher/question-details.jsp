<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1 class="text-dark">Question Details</h1>
<div class="table-responsive-xl">
    <table class="table">
        <tbody>
            <tr>
                <th>Category</th>
                <td>${currentQuesion.category.categoryName}</td>
            </tr>
            <tr>
                <th>Content</th>
                <td>${currentQuesion.content}</td>
            </tr>
            <c:forEach var="item" items="${listAnswers}">
                <c:choose>
                    <c:when test="${item.answerId == currentQuesion.correctAnswerId}">
                        <tr>
                            <th>Correct Answer</th>
                            <td>${item.answer}</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <th>Another Answer</th>
                            <td>${item.answer}</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <tr>
                <th>Hinte</th>
                <td>${currentQuesion.hinte}</td>
            </tr>
        </tbody>
    </table>
</div>
