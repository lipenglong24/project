<%--
  Created by IntelliJ IDEA.
  User: cofco-petric
  Date: 2018/4/9
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>welcome</title>

    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <h3 class="alert alert-success">welcome, you are already login</h3>
    <h3>欢迎 <strong class="text-success">${sessionScope.get("userInfo").username}</strong></h3>
    <div class="list-group col-xs-6">
        <a href="http://web1.sso.local/page" target="_blank" class="list-group-item">web1项目page页面</a>
        <a href="http://web2.sso.local/page" target="_blank" class="list-group-item">web2项目page页面</a>
    </div>
    <div class="col-xs-12">
        <a class="btn btn-danger" href="/logout">退出登录</a>
    </div>
</div>
</body>
</html>
