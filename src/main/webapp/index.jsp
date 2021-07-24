<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>hello</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="description" content="Write an awesome description for your new site here. You can edit this line in _config.yml. It will appear in your document head meta (for Google search results) and in your feed.xml site description.
">
</head>
<body>
<h1>Hello shiro</h1>
<h3>受限资源</h3>
<ul>
    <li>以下不可访问资源</li>
    <%--基于角色权限--%>
    <%-- 可以添加多个用户--%>
    <shiro:hasAnyRoles name="root,admin">
        <li>root用户和admin用户都可以看见</li>
    </shiro:hasAnyRoles>

    <%--只能添加一个用户--%>
    <shiro:hasRole name="root">
        <li>只有root用户才可以看见</li>
    </shiro:hasRole>

    <shiro:hasRole name="admin">
        <li>只有admin用户才可以看见</li>
    </shiro:hasRole>
    <shiro:hasRole name="user">
        <li>只有user用户才可以看见</li>
    </shiro:hasRole>

    <shiro:hasRole name="root">
        <li>用户管理/root权限</li>
    </shiro:hasRole>

    <ul>
        <%--基于权限字符串--%>
        <shiro:hasPermission name="user:add:*">
            <li>用户添加</li>
        </shiro:hasPermission>
        <shiro:hasPermission name="user:updata:*">
            <li>用户修改</li>
        </shiro:hasPermission>
        <shiro:hasPermission name="user:delect:*">

            <li>用户删除</li>
        </shiro:hasPermission>
        <shiro:hasPermission name="user:select:*">

            <li>用户查询</li>
        </shiro:hasPermission>
    </ul>
</ul>
<br>
<br>
<a href="${pageContext.request.contextPath}/user/out">退出登录</a>
</body>
</html>
