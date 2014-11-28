<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="resources/css/reset.css" />
<link rel="stylesheet" href="resources/css/content.css" />
<link rel="stylesheet" href="resources/css/invalid.css" />
<link rel="stylesheet"
	href="resources/scripts/jquery-ui/jquery-ui.min.css" />
<script src="resources/scripts/jquery.min.js"></script>
<script src="resources/scripts/jquery-ui/jquery-ui.min.js"></script>
<script src="resources/scripts/jquery-ui/datepicker-zh-CN.js"></script>
<script src="resources/scripts/configuration.js"></script>
<script src="resources/scripts/common.js"></script>
<script>
	$(function() {
		$("#dayTime").datepicker({
			changeMonth : true,
			changeYear : true,
			maxDate : new Date()
		});

		$("#channelId").change(function() {
			$.ajax({
				url : "ajaxGameList",
				data : {
					channelId : $(this).val()
				},
				success : function(result) {
					$("#gameId").html(result);
				}
			});
		});
	});
</script>
</head>
<body>
	<div id="search">
		<form action="queryActiveList">
			<table>
				<thead>
					<tr>
						<th><label>渠道</label></th>
						<th><label>产品</label></th>
						<th><label>时间</label></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><s:select id="channelId" name="channelId"
								list="channelList" listKey="channelId" listValue="channelName"
								theme="simple" /></td>
						<td><s:select id="gameId" name="gameId" list="gameList"
								listKey="gameId" listValue="gameName" theme="simple" /></td>
						<td><input type="text" id="dayTime" name="dayTime"
							value="${dayTime}" readonly="readonly" /></td>
						<td><input id="searchButton" type="button" value="查询" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<div class="content-box">
		<div class="content-box-header">
			<h3>用户活跃信息</h3>
		</div>
		<div class="content-box-content">
			<table>
				<thead>
					<tr>
						<th>日新增用户数</th>
						<th>日活跃用户数</th>
						<th>周活跃用户数</th>
						<th>日付费意愿用户数</th>
						<th>流失用户数</th>
					</tr>
				</thead>
				<tbody style="border-bottom: 0px;">
					<tr>
						<td>${activeWebBean.addNo}</td>
						<td>${activeWebBean.dayActiveNo}</td>
						<td>${activeWebBean.weekActiveNo}</td>
						<td>${activeWebBean.prePayNo}</td>
						<td>${activeWebBean.loseNo}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>