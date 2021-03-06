<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="header">
	<div id="title">
	    <h1><a href="${ctx}">砖石榜</a><small></small>
	    <shiro:user>
			<div class="btn-group pull-right">
				<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
					<i class="icon-user"></i> <shiro:principal property="name"/>
					<span class="caret"></span>
				</a>
			
				<ul class="dropdown-menu">
					<shiro:hasAnyRoles name="sysadmin,companyadmin,deptadmin">
						<li><a href="${ctx}/admin/user">用户管理</a></li>
						<li><a href="${ctx}/dept">部门管理</a></li>
						<li><a href="${ctx}/position">职位管理</a></li>
						<li class="divider"></li>
					</shiro:hasAnyRoles>
					
					<li><a href="${ctx}/profile">编辑资料</a></li>
					<li><a href="${ctx}/task/votepage">查看投票信息</a></li>
					<li><a href="${ctx}/task/weeknotepage">查看每周总结</a></li>
					<li><a href="${ctx}/logout">退出</a></li>
				</ul>
			</div>
		</shiro:user>
		</h1>
	</div>
</div>