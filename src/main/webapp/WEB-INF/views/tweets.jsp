<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>
<div >
    <div >

        <form action="#" th:th:action="@{/tweets}"  method="post">
            <textarea name="text" ></textarea><br/>
            <button  type="submit" name="addTweet">add</button>
            <br/><br/>
        </form>
    </div>



    <div >
        <h3>My tweets</h3>
        <div  th:each="t : ${allTweets}">
            <p th:text="${t.text}"></p>
            <p  th:text=" ${t.date}"></p>
        </div>
    </div>
</div>
</body>
</html>
