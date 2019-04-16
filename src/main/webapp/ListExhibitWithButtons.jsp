<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.softserve.academy.entity.ExhibitEntity" %>

<html>
<head>
    <title>Exhibits list</title>
    <script src="js/onClickExhibits.js"></script>
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
                out.println("<table class=\"table\">\n" +
                        "  <thead>\n" +
                        "    <tr>\n" +
                        "      <th scope=\"col\">Exhibit</th>\n" +
                        "      <th scope=\"col\">Author</th>\n" +
                        "      <th scope=\"col\">Technique</th>\n" +
                        "      <th scope=\"col\">Material</th>\n" +
                        "      <th scope=\"col\">Hall</th>\n" +
                        "    </tr>\n" +
                        "  </thead>");
                out.println("<tbody>");
                for (ExhibitEntity exhibit : names) {
                    out.println("<tr class=\"w3-hover-sand\" id="+exhibit.getId_exhibit()+">");
                    out.println("<th scope=\"row\" >"
                            + exhibit.getExhibit_name()
                            + "</th><td>" + exhibit.getFirstName() + " "
                            + exhibit.getLastName() + "</td>"
                            + "<td>" + exhibit.getTechnique_name() + "</td>"
                            + "<td>" + exhibit.getMaterial_name() + "</td>"
                            + "<td>" + exhibit.getHall_name() + "</td>");
                    out.println("<td><button id=\"" + exhibit.getId_exhibit() + "\" type=\"button\"class=\"btn btn-primary\" onclick=\"proceed(this.id);\">Add authors</button></td>");
                }
                out.println("</tbody>" + "</table>");

            } else out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                    +
                    "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                    "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">=)</span>\n" +
                    "   <h5>There are no exhibits yet!</h5>\n" +
                    "</div>");
        %>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/'">Back to main</button>
</div>
</body>
</html>