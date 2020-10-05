<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid">
    <h1 class="mt-4">Create Question</h1>
    <ol class="breadcrumb mb-4">
        <li class="breadcrumb-item"><a href="index.html">Working place</a></li>
        <li class="breadcrumb-item active">Create Question</li>
        <form:form action="teacher-save-category" method="GET" modelAttribute="newCategory" class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
            <div class="input-group">
                <form:input path="categoryName" class="form-control" type="text" placeholder="Add category" />
                <div class="input-group-append">
                    <form:button class="btn btn-dark" type="button"><i class="fas fa-plus-circle"></i></form:button>
                </div>
            </div>
        </form:form>  
    </ol>
    <form:form action="teacher-save-question" method="POST" modelAttribute="newQuestion">
        <div class="contact-form">
            <div class="form-group">
                <label class="control-label col-sm-2" for="content">Question's Content</label>
                <div class="col-sm-10">
                    <form:textarea path="content" class="form-control" rows="5" id="content"></form:textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="category">Category</label>
                    <div class="col-sm-10">
                    <form:select path="category.categoryId" class="form-control" id="category" >
                        <form:option value="0" disabled="TRUE" selected="TRUE" hidden="TRUE">Select</form:option>
                        <c:forEach var="item" items="${listCategoryDB}">
                            <form:option value="${item.categoryId}">${item.categoryName}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="correctAnswer">Correct Answer</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="correctAnswer" placeholder="Enter correct answer" name="correctAnswer"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="anotherAnswer">Another Answer  <i class='fas fa-plus-circle' style="font-size: 20px;color: lightgrey" onMouseOver="this.style.color = 'grey'"
                                                                                             onMouseOut="this.style.color = 'lightgrey'" onclick="addInputfield()"></i></label>

                <div id="inputField" class="col-sm-10">
                    <input style="margin-bottom:4px" class="form-control" type="text" id="anotherAnswer" placeholder="Enter another answer" name="anotherAnswer">
                </div>
            </div>
            <script>
                function addInputfield() {
                    var x = document.createElement("INPUT");
                    x.setAttribute("type", "text");
                    x.setAttribute("name", "anotherAnswer");
                    x.setAttribute("placeholder", "Enter another answer");
                    x.setAttribute("class", "form-control");
                    x.setAttribute("style", "margin-bottom:4px");

                    //                                var classAtt = document.createAttribute("class");
                    //                                classAtt.value = "form-control";
                    //                                x.setAttributeNode(classAtt);
                    document.getElementById("inputField").appendChild(x);
                }
            </script>
            <div class="form-group">
                <label class="control-label col-sm-2" for="hinte">Hinte</label>
                <div class="col-sm-10">
                    <form:input path="hinte" type="text" class="form-control" id='hinte' placeholder="Enter hinte" name="hinte"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default" 
                            style="background-color: #343a40; color:white ; width: 100%" onMouseOver="this.style.backgroundColor = '#062c33'"
                                                                                         onMouseOut="this.style.backgroundColor = '#343a40'" >Submit</button>
                </div>
            </div>
        </div>
    </form:form>
    <div class="col-md-9">
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
                        <tfoot>
                            <tr>
                                <th>Category</th>
                                <th>Content</th>
                                <th>Hinte</th>
                                <th>Details</th> 
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="item" items="${listQuestionDB}">
                                <tr>
                                    <td>${item.category.categoryName}</td>
                                    <td>${item.content}</td>
                                    <td>${item.hinte}</td>
                                    <td><a href="teacher-question-details?questionId=${item.questionId}">Details</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>