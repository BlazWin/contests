<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/snarknews.css">
</head>
<body>
<table class="standings">
    <tbody>
    <tr>
        <th class="stnd">Name</th>
    </tr>
    <c:forEach var="contest" items="${contests}">
        <tr><th class="stnd">
            <a href="/contest/${contest.id}"><c:out value="${contest.name}"/></a>
        </th></tr>
    </c:forEach>
    </tbody>
    <br>
    <a href="/contest/new">generate new</a>
</table>
</body>
</html>
