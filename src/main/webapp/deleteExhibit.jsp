<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static java.lang.System.out" %>
<%@ page import="com.softserve.academy.entity.ExhibitEntity" %>

<html>
<head>
    <title>Exhibits list</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Super app!</h1>
</div>

<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2>Exhibits</h2>
        </div>
        <%
            List<ExhibitEntity> names = (List<ExhibitEntity>) request.getAttribute("exhibits");

            if (names != null && !names.isEmpty()) {
                out.println("<form action=\"\" method=\"get\">" +
                        "<table class=\"table\">\n" +
                        "  <thead>\n" +
                        "    <tr>\n" +
                        "      <th scope=\"col\">Exhibit</th>\n" +
                        "      <th scope=\"col\">Author</th>\n" +
                        "      <th scope=\"col\">Technique</th>\n" +
                        "      <th scope=\"col\">Material</th>\n" +
                        "      <th scope=\"col\">Hall</th>\n" +
                        "      <th scope=\"col\">Delete</th>\n" +
                        "    </tr>\n" +
                        "  </thead>");
                out.println("<tbody>");
                for (ExhibitEntity exhibit : names) {
                    out.println("<tr class=\"w3-hover-sand\">");
                    out.println("<th scope=\"row\">"
                            + exhibit.getExhibit_name()
                            + "</th><td>" + exhibit.getFirstName() + " "
                            + exhibit.getLastName() + "</td>"
                            + "<td>" + exhibit.getTechnique_name() + "</td>"
                            + "<td>" + exhibit.getMaterial_name() + "</td>"
                            + "<td>" + exhibit.getHall_name() + "</td>"
                            + "<td><input type=\"checkbox\" name=\"toDelete\" value=\"" + exhibit.getId_exhibit() + "\"></td>"); //checkbox to submit delete by id

                }
                out.println("</tbody></table>");
                out.println("<input class=\"btn btn-danger btn-lg\"type=\"submit\"value=\"Delete\"></form>"); //button to submit checkboxes
            } else out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                    +
                    "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                    "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">=)</span>\n" +
                    "   <h5>There are no exhibits yet!</h5>\n" +
                    "</div>");
        %>
    </div>
</div>
<%
    if (request.getAttribute("deleted") != null && !request.getAttribute("deleted").equals(0)) {
        out.println("<div class=\"w3-panel w3-green w3-display-container w3-card-4 w3-round\">\n" +
                "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-green w3-border w3-border-green w3-hover-border-grey\">=)</span>\n" +
                "   <h5>exhibits deleted!</h5>\n" +
                "</div>");
    } else if (request.getAttribute("result") != null && request.getAttribute("result").equals(0))
        out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                +
                "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">=)</span>\n" +
                "   <h5>Nothing was deleted!</h5>\n" +
                "</div>");
%>
<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/'">Back to main</button>
</div>
</body>
</html>