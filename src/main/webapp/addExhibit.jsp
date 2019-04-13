<%@ page
        import="static java.lang.System.out" %>
<%@ page import="com.softserve.academy.entity.ExhibitEntity" %>
<%@ page import="java.util.List" %><%--<%@ page contentType="index/html;charset=UTF-8" language="java" %>--%>
<html>
<head>
    <title>Add new user</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
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
        if (request.getAttribute("success") != null && request.getAttribute("success").equals(0)){
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
        <div>
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
                        out.println("<tr class=\"w3-hover-sand\">");
                        out.println("<th scope=\"row\">"
                                + exhibit.getExhibit_name()
                                + "</th><td>" + exhibit.getFirstName() + " "
                                + exhibit.getLastName() + "</td>"
                                + "<td>" + exhibit.getTechnique_name() + "</td>"
                                + "<td>" + exhibit.getMaterial_name() + "</td>"
                                + "<td>" + exhibit.getHall_name() + "</td>");

                    }
                    out.println("</tbody>" + "</table>");
                } else out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                        + "<h5>There are no exhibits yet!</h5>\n" +
                        "</div>");
            %>
        </div>
        <div>
            <form method="post" class="w3-selection w3-light-grey w3-padding">
                <input type="text" name="exhibitName" placeholder="Exhibit Name"
                       class="w3-input w3-animate-input w3-border w3-round-large"
                       style="width: 30%"><br/>
                <input type="text" name="firstname" placeholder="Author First Name"
                       class="w3-input w3-animate-input w3-border w3-round-large"
                       style="width: 30%"><br/>
                <input type="text" name="lastname" placeholder="Author Last Name"
                       class="w3-input w3-animate-input w3-border w3-round-large"
                       style="width: 30%"><br/>
                <input type="text" name="hall" placeholder="Hall Name"
                       class="w3-input w3-animate-input w3-border w3-round-large"
                       style="width: 30%"><br/>
                <input type="text" name="material" placeholder="Material Name"
                       class="w3-input w3-animate-input w3-border w3-round-large"
                       style="width: 30%"><br/>
                <input type="text" name="technique" placeholder="Technique Name"
                       class="w3-input w3-animate-input w3-border w3-round-large"
                       style="width: 30%"><br/>
                <button class="w3-btn w3-green w3-round-large w3-margin-bottom">Save</button>
            </form>
        </div>

    </div>

</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/'">Back to main</button>
</div>
</body>
</html>