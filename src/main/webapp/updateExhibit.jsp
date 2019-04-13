<%@ page
        import="static java.lang.System.out" %>
<%@ page import="com.softserve.academy.entity.ExhibitEntity" %>
<%@ page import="java.util.List" %><%--<%@ page contentType="index/html;charset=UTF-8" language="java" %>--%>
<html>
<head>
    <title>Update new user</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Super app!</h1>
</div>

<div class="w3-container w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2>Update exhibit</h2>
        </div>
        <div>
            <%
                if (request.getAttribute("updated") != null && !request.getAttribute("updated").equals(0)) {
                    out.println("<div class=\"w3-panel w3-green w3-display-container w3-card-4 w3-round\">\n" +
                            "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                            "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-green w3-border w3-border-green w3-hover-border-grey\">=)</span>\n" +
                            "   <h5>Exhibit '" + request.getParameter("exhibitName") + "' was updated!</h5>\n" +
                            "</div>");
                }
            %>
            <%
                if (request.getAttribute("updated") != null && request.getAttribute("updated").equals(0)){
                    out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                            +
                            "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                            "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">=(</span>\n" +
                            "   <h5>Nothing was updated! You have entered empty fields!</h5>\n" +
                            "</div>");
                }
            %>
            <form method="post" class="w3-selection w3-light-grey w3-padding">
                <label>Exhibit name :</label> <input type="text" name="exhibitName" placeholder="Exhibit Name" value="<%if(request.getParameter("exhibitName")!=null){
                    out.println(request.getParameter("exhibitName"));
                }
                else {
                    out.println(((ExhibitEntity)request.getAttribute("exhibitToUpdate")).getExhibit_name());
                } %>"
                                                     class="w3-input w3-animate-input w3-border w3-round-large"
                                                     style="width: 30%"><br/>
                <label>Author name :</label> <input type="text" name="firstname" placeholder="Author First Name"
                                                     value="<%if(request.getParameter("firstname")!=null){
                    out.println(request.getParameter("firstname"));
                }
                else {
                    out.println(((ExhibitEntity)request.getAttribute("exhibitToUpdate")).getFirstName());
                } %>"
                                                     class="w3-input w3-animate-input w3-border w3-round-large"
                                                     style="width: 30%"><br/>
                <label>Author last name :</label> <input type="text" name="lastname" placeholder="Author Last Name" value="<%if(request.getParameter("lastname")!=null){
                    out.println(request.getParameter("lastname"));
                }
                else {
                    out.println(((ExhibitEntity)request.getAttribute("exhibitToUpdate")).getLastName());
                } %>"
                                                     class="w3-input w3-animate-input w3-border w3-round-large"
                                                     style="width: 30%"><br/>
                <label>Hall :</label> <input type="text" name="hall" placeholder="Hall" value="<%if(request.getParameter("hall")!=null){
                    out.println(request.getParameter("hall"));
                }
                else {
                    out.println(((ExhibitEntity)request.getAttribute("exhibitToUpdate")).getHall_name());
                } %>"
                                                     class="w3-input w3-animate-input w3-border w3-round-large"
                                                     style="width: 30%"><br/>
                <label>Material :</label> <input type="text" name="material" placeholder="Material Name" value="<%if(request.getParameter("material")!=null){
                    out.println(request.getParameter("material"));
                }
                else {
                    out.println(((ExhibitEntity)request.getAttribute("exhibitToUpdate")).getMaterial_name());
                } %>"
                                                     class="w3-input w3-animate-input w3-border w3-round-large"
                                                     style="width: 30%"><br/>
                <label>Technique :</label> <input type="text" name="technique" placeholder="Technique Name" value="<%if(request.getParameter("technique")!=null){
                    out.println(request.getParameter("technique"));
                }
                else {
                    out.println(((ExhibitEntity)request.getAttribute("exhibitToUpdate")).getTechnique_name());
                } %>"
                                                     class="w3-input w3-animate-input w3-border w3-round-large"
                                                     style="width: 30%"><br/>
                <input type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom" value="Update"></input>
            </form>
        </div>

    </div>

</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/'">Back to main</button>
</div>
</body>
</html>