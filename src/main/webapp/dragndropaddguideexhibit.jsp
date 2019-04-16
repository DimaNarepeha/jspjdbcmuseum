<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.softserve.academy.entity.ExhibitEntity" %>
<%@ page import="java.util.List" %><%--<%@ page contentType="index/html;charset=UTF-8" language="java" %>--%>
<html>
<head>
    <title>Add new user</title>
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="js/jquery-ui-git.js"></script>

    <script src="js/dragStuff.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Super app!</h1>
</div>

<div class="w3-container w3-padding">
    <%
        if (request.getAttribute("success") != null && request.getAttribute("success").equals(1)) {
            out.println("<div class=\"w3-panel w3-green w3-display-container w3-card-4 w3-round\">\n" +
                    "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                    "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-green w3-border w3-border-green w3-hover-border-grey\">=)</span>\n" +
                    "   <h5>Exhibit '" + request.getParameter("exhibitName") + "' was added!</h5>\n" +
                    "</div>");
        }
    %>
    <%
        if (request.getAttribute("success") != null && request.getAttribute("success").equals(0)) {
            out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                    +
                    "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                    "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">=(</span>\n" +
                    "   <h5>Nothing was added! You have entered empty fields!</h5>\n" +
                    "</div>");
        }
    %>
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2>Add exhibit</h2>
        </div>
        <table class="table table-bordered pagin-table">
            <thead>
            <tr>
                <th scope=\"col\">Exhibit</th>
                <th scope=\"col\">Author</th>
                <th scope=\"col\">Technique</th>
                <th scope=\"col\">Material</th>
                <th scope=\"col\">Hall</th>
            </tr>
            </thead>
            <tbody class="connectedSortable">
            <c:forEach items="${exhibit}" var="name">
                <tr>
                    <td>${exhibit.getExhibit_name()}</td>
                    <td>${exhibit.getFirstName()+" "+exhibit.getLastName()}</td>
                    <td>${exhibit.getTechnique_name()}</td>
                    <td>${exhibit.exhibit.getMaterial_name()}</td>
                    <td>${exhibit.getHall_name()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div>
            <%
                List<ExhibitEntity> names = (List<ExhibitEntity>) request.getAttribute("exhibits");

                if (names != null && !names.isEmpty()) {


                }
                out.println("</tbody>" + "</table>");
                }
                else
                out
                .
                println
                (
                "<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                +
                "<h5>There are no exhibits yet!</h5>\n"
                +
                "</div>"
                )
                ;
            %>
        </div>


    </div>

</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/'">Back to main</button>
</div>
</body>
</html>