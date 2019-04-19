<%@ page import="java.util.List" %>
<%@ page import="com.softserve.academy.entity.GuideEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static java.lang.System.out" %>
<%@ page import="java.util.Map" %>

<html>
<head>
    <title>Exhibit-Guide list</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Super app!</h1>
</div>

<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2>Exhibit Guide List</h2>
        </div>

        <%
            Map<String,List<String>> names = (Map<String,List<String>>) request.getAttribute("relations");

            if (names != null && !names.isEmpty()) {
                out.println("<ul class=\"w3-ul\">");
                for (String s : names.keySet()) {
                    out.println("<ul class=\" w3-red \">" +"Exhibit: "+s+ "</ul>");
                    out.println("<li class=\"w3-hover-khaki w3-animate-left\">"+"Responsible:");
                        for(String g : names.get(s)){
                            out.print("  "+g+ "; ");
                        }
                    out.println( "</li>");

                }
                out.println("</ul>");

            } else out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                    +
                    "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                    "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">=)</span>\n" +
                    "   <h5>There are no users yet!</h5>\n" +
                    "</div>");
        %>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/'">Back to main</button>
</div>
</body>
