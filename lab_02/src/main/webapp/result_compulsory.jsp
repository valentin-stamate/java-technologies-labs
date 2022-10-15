<%@ page import="com.example.lab_02.service.UtilService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.lab_02.beans.UserForm" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    UserForm userForm = (UserForm) request.getAttribute("userForm");

    String wordQuery = userForm.getWord();
    String sizeQuery = "" + userForm.getSize();

    boolean dictionaryExists = UtilService.fileExists("dictionary.txt");

    List<String> permutations = UtilService.getPermutations(wordQuery, sizeQuery);
%>

<html>
<head>
    <title>Compulsory</title>
</head>
<body>

<div>Word: <%= wordQuery %></div>
<div>Size: <%= sizeQuery %></div>
<div>Dictionary: <%= dictionaryExists %></div><br>

<ol>
    <%
        for (String permutation : permutations) {
            out.print(String.format("<li>%s</li>", permutation));
        }
    %>
</ol>

</body>
</html>
