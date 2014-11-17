<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<body>
	<div id="sidebar">
		<div id="sidebar-wrapper">
			<span id="sidebar-title" style="font-size: 50px; font-weight: bold;">
				<a href="welcome">QD·M</a>
			</span>
			<div id="profile-links">
				您好, <span id="username">${session.user.username}</span> | <a
					href="logout">退出</a>
			</div>
			<ul id="main-nav">
				<li><a href="home" class="nav-top-item no-submenu current"
					target="iframepage">欢迎页</a></li>
				<li><a class="nav-top-item">统计分析</a>
					<ul>
						<li><a href="queryOrderList" target="iframepage">订单</a></li>
						<li><a href="queryActionList" target="iframepage">访问数据</a></li>
					</ul></li>
				<c:if test="${session.user.authority eq '0'}">
					<li><a class="nav-top-item">运营管理</a>
						<ul>
							<li><a href="queryUserList" target="iframepage">用户</a></li>
							<li><a href="queryCpList" target="iframepage">合作方</a></li>
							<li><a href="queryGameList" target="iframepage">产品</a></li>
							<li><a href="querySimList" target="iframepage">短信指令</a></li>
							<li><a href="queryPayList" target="iframepage">支付方式</a></li>
						</ul></li>
				</c:if>
			</ul>
		</div>
	</div>
</body>
</html>
