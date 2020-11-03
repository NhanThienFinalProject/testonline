<%-- 
    Document   : index
    Created on : Oct 30, 2020, 2:47:20 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/tablibrary.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container">
    <main>
        <div class="container-fluid">
            <h1 class="mt-4">Working space</h1>
            <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item active">Statistics</li>
            </ol>
            <c:set value="${fn:length(listFinishedExam)}" var="length"/>
            <div class="row">
                <div class="col-xl-3 col-md-6">
                    <div class="card bg-primary text-white mb-4">
                        <c:if test="${length <= 0}">
                            <div class="card-body">Recent exam 1</div>
                            <div class="card-footer d-flex align-items-center justify-content-between">
                                <a class="small text-white stretched-link" href="#">View Details</a>
                                <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                            </div>
                        </c:if>
                        <c:if test="${length >= 1}">
                            <div class="card-body"><c:out value="${listFinishedExam.get(length-1).content}"/></div>
                            <div class="card-footer d-flex align-items-center justify-content-between">
                                <a class="small text-white stretched-link" target="_blank" href="teacher-result-exam?examId=${listFinishedExam.get(length-1).examId}">View Details</a>
                                <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                            </div>
                        </c:if>
                    </div>
                </div>
                <div class="col-xl-3 col-md-6">
                    <div class="card bg-warning text-white mb-4">
                        <c:if test="${length <= 1}">
                            <div class="card-body">Recent exam 2</div>
                            <div class="card-footer d-flex align-items-center justify-content-between">
                                <a class="small text-white stretched-link" href="#">View Details</a>
                                <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                            </div>
                        </c:if>
                        <c:if test="${length >= 2}">
                            <div class="card-body"><c:out value="${listFinishedExam.get(length-2).content}"/></div>
                            <div class="card-footer d-flex align-items-center justify-content-between">
                                <a class="small text-white stretched-link" target="_blank" href="teacher-result-exam?examId=${listFinishedExam.get(length-2).examId}">View Details</a>
                                <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                            </div>
                        </c:if>                        
                    </div>
                </div>
                <div class="col-xl-3 col-md-6">
                    <div class="card bg-success text-white mb-4">
                        <c:if test="${length <= 2}">
                            <div class="card-body">Recent exam 3</div>
                            <div class="card-footer d-flex align-items-center justify-content-between">
                                <a class="small text-white stretched-link" href="#">View Details</a>
                                <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                            </div>
                        </c:if>
                        <c:if test="${length >= 3}">
                            <div class="card-body"><c:out value="${listFinishedExam.get(length-3).content}"/></div>
                            <div class="card-footer d-flex align-items-center justify-content-between">
                                <a class="small text-white stretched-link" target="_blank" href="teacher-result-exam?examId=${listFinishedExam.get(length-3).examId}">View Details</a>
                                <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                            </div>
                        </c:if>                        
                    </div>
                </div>
                <div class="col-xl-3 col-md-6">
                    <div class="card bg-danger text-white mb-4">
                        <c:if test="${length <= 3}">
                            <div class="card-body">Recent exam 4</div>
                            <div class="card-footer d-flex align-items-center justify-content-between">
                                <a class="small text-white stretched-link" href="#">View Details</a>
                                <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                            </div>
                        </c:if>
                        <c:if test="${length >= 4}">
                            <div class="card-body"><c:out value="${listFinishedExam.get(length-4).content}"/></div>
                            <div class="card-footer d-flex align-items-center justify-content-between">
                                <a class="small text-white stretched-link" target="_blank" href="teacher-result-exam?examId=${listFinishedExam.get(length-4).examId}">View Details</a>
                                <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                            </div>
                        </c:if>                        
                    </div>
                </div>
            </div>
            <hr>
            <div class="card mb-4">
                <div class="card-header">
                    <i class="fas fa-table mr-1"></i>
                    List of questions
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>Category</th>
                                    <th>Content</th>
                                    <th>Hinte</th>
                                    <th>Details</th>        
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${listQuestionDB}">
                                    <tr>
                                        <td>${item.category.categoryName}</td>
                                        <td>${item.content}</td>
                                        <td>${item.hinte}</td>
                                        <td><a target="_blank" href="teacher-question-details?questionId=${item.questionId}">Details</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>
