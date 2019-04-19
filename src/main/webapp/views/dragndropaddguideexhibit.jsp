<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.softserve.academy.entity.ExhibitEntity" %>
<%@ page import="java.util.List" %>
<%@ page
        import="com.softserve.academy.entity.GuideEntity" %><%--<%@ page contentType="index/html;charset=UTF-8" language="java" %>--%>
<html>
<head>
    <title>Add new user</title>
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="js/jquery-ui-git.js"></script>
    <script src="js/buttonToUpdateRelations.js"></script>

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

<body class="w3-light-grey" id="<%out.println(request.getAttribute("idOldExhibit"));%>">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Super app!</h1>
</div>

<div class="w3-container w3-padding">
    <%
        if (request.getAttribute("success") != null && request.getAttribute("success").equals(1)) {
            out.println("<div class=\"w3-panel w3-green w3-display-container w3-card-4 w3-round\">\n" +
                    "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                    "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-green w3-border w3-border-green w3-hover-border-grey\">=)</span>\n" +
                    "   <h5> Successfully updated!</h5>\n" +
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
    <div class="w3-card-4 container">
        <div class="w3-container w3-center w3-green">
            <h2>Add exhibit</h2>
        </div>
        <table class="table table-bordered pagin-table">

            <tbody class="connectedSortable">
            <%
                ExhibitEntity exhibit = (ExhibitEntity) request.getAttribute("exhibit");
                List<GuideEntity> currentGuides = (List<GuideEntity>) request.getAttribute("currentGuides");
                List<GuideEntity> guidesInDatabase = (List<GuideEntity>) request.getAttribute("guidesInDatabase");
                if (exhibit != null) {
                    out.println("<table class=\"table connectedSortable\">\n <thead>\n" +
                            "            <tr>\n" +
                            "                <th scope=\\\"col\\\">Exhibit</th>\n" +
                            "                <th scope=\\\"col\\\">Author</th>\n" +
                            "                <th scope=\\\"col\\\">Technique</th>\n" +
                            "                <th scope=\\\"col\\\">Material</th>\n" +
                            "                <th scope=\\\"col\\\">Hall</th>\n" +
                            "            </tr>\n" +
                            "            </thead>");
                    out.println("<tbody class=\"connectedSortable\">");

                    out.println("<tr class=\"w3-hover-sand\">");
                    out.println("<th scope=\"row\">"
                            + exhibit.getExhibit_name()
                            + "</th><td>" + exhibit.getFirstName() + " "
                            + exhibit.getLastName() + "</td>"
                            + "<td>" + exhibit.getTechnique_name() + "</td>"
                            + "<td>" + exhibit.getMaterial_name() + "</td>"
                            + "<td>" + exhibit.getHall_name() + "</td>");

                    out.println("</tbody>" + "</table><br><br>");
                    out.println("<table id=\"in\" class=\"table\">\n" +
                            "  <thead>\n" +
                            "    <tr><th scope=\"col\">Guides For This Exhibit</th></tr>\n" +
                            "  </thead>");

                    out.println("<tbody  class=\"connectedSortable\">");
                    if(currentGuides!=null&&!currentGuides.isEmpty()){
                        for(GuideEntity entity:currentGuides){
                            out.println("<tr class=\"w3-hover-sand table-success\" id="+entity.getId()+">");
                            out.println("<th scope=\"row\">"
                                    + "</th><td>" + entity.getFirstname() + " "
                                    + entity.getLastname() + "</td>");
                        }
                    }else{
                        out.println("<tr class=\"w3-hover-sand\">");
                        out.println("<th scope=\"row\">"
                                + "</th><td>Empty!</td>");
                    }
                    out.println("</tbody>" + "</table>");
                    out.println("<table  class=\"table \">\n" +
                            "  <thead>\n" +
                            "    <tr>\n" +
                            "      <th scope=\"col\">All other Guides</th>\n" +
                            "    </tr>\n" +
                            "  </thead>");
                    out.println("<tbody class=\"connectedSortable out\">");
                    if(!guidesInDatabase.isEmpty()){
                    for(GuideEntity entity:guidesInDatabase){
                        out.println("<tr class=\"w3-hover-sand table-danger\" id="+entity.getId()+">");
                        out.println("<th scope=\"row\">"
                                + "</th><td>" + entity.getFirstname() + " "
                                + entity.getLastname() + "</td>");
                    }
                }else{
                    out.println("Empty");
                }
                } else out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                        +
                        "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                        "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">=)</span>\n" +
                        "   <h5>There are no exhibits yet!</h5>\n" +
                        "</div>");
            %>


            </tbody>

        </table>



        <div class="col text-center">
            <button type="button"class="btn btn-primary btn-lg w3-center " onclick="proceed();">Update</button>
        </div>

</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/'">Back to main</button>
</div>
</body>
</html>