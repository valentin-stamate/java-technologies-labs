<%@ page import="com.example.lab_02.services.service.UtilService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String wordQuery = request.getParameter("word");
    String sizeQuery = request.getParameter("size");

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
