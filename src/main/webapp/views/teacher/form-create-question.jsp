<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid">
    <h1 class="mt-4">Create Question</h1>
    <ol class="breadcrumb mb-4">
        <li class="breadcrumb-item"><a href="index.html">Working place</a></li>
        <li class="breadcrumb-item active">Create Question</li>
            <form:form action="teacher-save-category" method="POST" modelAttribute="newCategory" onsubmit="return checkAddCategoryField()" class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
            <div class="input-group">
                <form:input path="categoryName" class="form-control" id="categoryadding" type="text" placeholder="Add category" />
                <div class="input-group-append">
                    <form:button class="btn btn-dark" type="submit"><i class="fas fa-plus-circle"></i></form:button>
                    </div>
                </div>
        </form:form>  
    </ol>
    <form:form action="teacher-save-question" method="POST" modelAttribute="newQuestion" onsubmit="return checkEmptyField()">
        <div class="contact-form">
            <div class="form-group">
                <label class="control-label col-sm-2" for="content">Question's Content</label>
                <div class="col-sm-10">
                    <form:textarea path="content" class="form-control" rows="5" id="content"></form:textarea>
                    </div>
                    <div id="notice1" class="col-sm-10">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="category">Category</label>
                    <div class="col-sm-10">
                    <form:select path="category.categoryId" class="form-control" id="category" >
                        <form:option id="subcategory" value="0" disabled="TRUE" selected="TRUE" hidden="TRUE">Select</form:option>
                        <c:forEach var="item" items="${listCategoryDB}">
                            <form:option id="subcategory" value="${item.categoryId}">${item.categoryName}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
                <div id="notice2" class="col-sm-10">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="correctAnswer">Correct Answer</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="correctAnswer" placeholder="Enter correct answer" name="correctAnswer"/>
                </div>
                <div id="notice" class="col-sm-10">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="anotherAnswer">Another Answer  <i class='fas fa-plus-circle' style="font-size: 20px;color: lightgrey" onMouseOver="this.style.color = 'grey'"
                                                                                             onMouseOut="this.style.color = 'lightgrey'" onclick="addInputfield()"></i></label>

                <div id="inputField" class="col-sm-10">
                    <input style="margin-bottom:4px" class="form-control" type="text" id="anotherAnswer" placeholder="Enter another answer" name="anotherAnswer">
                </div>
                <div id="notice3" class="col-sm-10">
                </div>
            </div>
            <script>
                function checkAddCategoryField() {
                    var n = document.getElementById("categoryadding").value;
                    if (n === null || n === "") {
                        document.getElementById("categoryadding").style.borderColor = "#FF0000";
                        return false;
                    }
                    return true;
                }
                function addInputfield() {
                    var x = document.createElement("INPUT");
                    x.setAttribute("type", "text");
                    x.setAttribute("name", "anotherAnswer");
                    x.setAttribute("placeholder", "Enter another answer");
                    x.setAttribute("class", "form-control");
                    x.setAttribute("style", "margin-bottom:4px");
                    document.getElementById("inputField").appendChild(x);
                }
                function checkEmptyField() {
//                    question's content
                    var y = document.getElementById("content").value;
                    if (y === null || y === "") {
                        document.getElementById("content").style.borderColor = "#FF0000";
                        document.getElementById("notice1").innerHTML = "<sup style='color:red'><i>* Field must be filled out!</i></sup>";
                        return false;
                    } else {
                        document.getElementById("content").style.borderColor = "#D8D8D8";
                        document.getElementById("notice1").innerHTML = "";
                    }
//                    category
                    var z = document.getElementById("category").value;
                    if (z === "0") {
                        document.getElementById("category").style.borderColor = "#FF0000";
                        document.getElementById("notice2").innerHTML = "<sup style='color:red'><i>* Field must be filled out!</i></sup>";
                        return false;
                    } else {
                        document.getElementById("category").style.borderColor = "#D8D8D8";
                        document.getElementById("notice2").innerHTML = "";
                    }

//                  correct answer
                    var x = document.getElementById("correctAnswer").value;
                    if (x === null || x === "") {
                        document.getElementById("correctAnswer").style.borderColor = "#FF0000";
                        document.getElementById("notice").innerHTML = "<sup style='color:red'><i>* Field must be filled out!</i></sup>";
                        return false;
                    } else {
                        document.getElementById("correctAnswer").style.borderColor = "#D8D8D8";
                        document.getElementById("notice").innerHTML = "";

                    }

                    //                  another answer
                    var m = document.getElementById("anotherAnswer").value;
                    if (m === null || m === "") {
                        document.getElementById("anotherAnswer").style.borderColor = "#FF0000";
                        document.getElementById("notice3").innerHTML = "<sup style='color:red'><i>* Field must be filled out!</i></sup>";
                        return false;
                    } else {
                        document.getElementById("anotherAnswer").style.borderColor = "#D8D8D8";
                        document.getElementById("notice3").innerHTML = "";
                    }
                    return true;
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
