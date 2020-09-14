<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>



<html>
    <head>
           <title>客户录入页面</title>
    </head>
    <body>
        	<form action="${pageContext.request.contextPath}/customer/save.action" method="post">
        		客户姓名:<input type="text" name="name"/><br/>
        		客户性别:
        		<input type="radio" name="gender" value="男"/>男
        		<input type="radio" name="gender" value="女"/>女
        		<br/>
        		客户手机:<input type="text" name="phone"/><br/>
        		客户住址：<input type="text" name="address"/><br/>
        		<input type="submit" value="保存">
        	</form>
    </body>
</html>