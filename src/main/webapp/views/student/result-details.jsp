<%-- 
    Document   : home
    Created on : Sep 30, 2020, 9:33:27 AM
    Author     : ADMIN
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/tablibrary.jsp"%>
<c:choose>
    <c:when test="${message == ''}">
        <div class="container">
            <div class="p-3 my-3 bg-primary text-white border">
                <h3>${currentExamtitle.exam.content}</h3><p class="display-3">${point}/${numberOfquestionOfExamtitle}</p>
                <p>${(currentExamtitle.exam.timeEnd.getTime() - currentExamtitle.exam.timeStart.getTime())/3600} hours</p>
            </div>
            <div class="column">
                <c:set value="${questionOfExamtitleList}" var="questionOfExamtitleList" />
                <ol>
                    <c:forEach items="${questionOfExamtitleList.pageList}" var="questionOfExamtitle">
                        <h5><b>* ${questionOfExamtitle.question.content}</b></h5>
                        <c:set value="${questionOfExamtitle.resultAnswerId}" var="resultId"/>
                        <c:forEach var="item" items="${questionOfExamtitle.question.listAnswer}">
                            <c:choose>
                                <c:when test="${resultId == item.answerId}">
                                    <c:choose>
                                        <c:when test="${resultId == questionOfExamtitle.question.correctAnswerId}">
                                            <div>
                                                <label class="form-check-label" >
                                                    <input class="form-check-input" type="checkbox" value="" checked="true" disabled="true">${item.answer}
                                                    <i class="fa fa-check" aria-hidden="true" style="color: #34ce57"></i>
                                                </label>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div>
                                                <label class="form-check-label" >
                                                    <input class="form-check-input" type="checkbox" value="" checked="true" disabled="true">${item.answer}
                                                    <i class="fa fa-times" aria-hidden="true" style="color: red"></i>
                                                </label>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${item.answerId == questionOfExamtitle.question.correctAnswerId}">
                                            <div>
                                                <label class="form-check-label" >
                                                    <input class="form-check-input" type="checkbox" value="" disabled="true">${item.answer}
                                                    <i class="fa fa-check" aria-hidden="true" style="color: #34ce57"></i>
                                                </label>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div>
                                                <label class="form-check-label" >
                                                    <input class="form-check-input" type="checkbox" value="" disabled="true">${item.answer}
                                                </label>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:forEach>
                </ol>
            </div>

            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center bg-light">
                    <c:set value="${examtitleId}" var="examtitleId"></c:set>
                    <c:choose>
                        <%-- Show Prev as link if not on first page --%>
                        <c:when test="${questionOfExamtitleList.firstPage}">
                            <li class="page-item">
                                <a class="page-link" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <c:url value="/student-result/previous?examtitleId=${examtitleId}" var="url" />   
                                <a class="page-link" href='<c:out value="${url}" />'>
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach begin="1" end="${questionOfExamtitleList.pageCount}" step="1"  varStatus="tagStatus">
                        <c:choose>
                            <%-- In PagedListHolder page count starts from 0 --%>
                            <c:when test="${(questionOfExamtitleList.page + 1) == tagStatus.index}">
                                <li class="page-item">
                                    <a class="page-link">${tagStatus.index}</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <c:url value="/student-result/${tagStatus.index}?examtitleId=${examtitleId}" var="url" />  
                                <li class="page-item">
                                    <a class="page-link" href='<c:out value="${url}" />'>${tagStatus.index}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <%-- Show Next as link if not on last page --%>
                        <c:when test="${questionOfExamtitleList.lastPage}">
                            <li class="page-item">
                                <a class="page-link" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <c:url value="/student-result/next?examtitleId=${examtitleId}" var="url" />                  
                                <a class="page-link" href='<c:out value="${url}" />'>
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </nav>
        </div>
    </c:when>
    <c:otherwise>
        <div id="notfound">
            <div class="notfound">
                <div class="notfound-404">
                    <h1>Oops!</h1>
                    <h2><c:out value="${message}"/></h2>
                </div>
                <a href="http://localhost:8080/NationalTestOnline">Go TO Homepage</a>
            </div>
        </div>
    </c:otherwise>
</c:choose>


