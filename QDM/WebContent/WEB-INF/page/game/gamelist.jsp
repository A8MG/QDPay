<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="resources/css/reset.css" />
<link rel="stylesheet" href="resources/css/content.css" />
<link rel="stylesheet" href="resources/css/invalid.css" />
<script src="resources/scripts/jquery.min.js"></script>
<script src="resources/scripts/configuration.js"></script>
<script src="resources/scripts/common.js"></script>
</head>
<body>
	<div id="search">
		<form action="queryGameList">
			<input id="currentPage" name="page.currentPage" type="hidden"
				value="${page.currentPage}" /> <input id="pageCount" type="hidden"
				value="${page.pageCount}" />
			<table>
				<thead>
					<tr>
						<th><label>产品ID</label></th>
						<th><label>产品名称</label></th>
						<th><label>合作方</label></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="text" id="gameId" name="search.gameId"
							value="${search.gameId}" /></td>
						<td><input type="text" id="gameName" name="search.gameName"
							value="${search.gameName}" /></td>
						<td><input type="text" id="cpName" name="search.cpName"
							value="${search.cpName}" /></td>
						<td><input type="submit" value="查询" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<div class="content-box">
		<div class="content-box-header">
			<h3>产品信息</h3>
		</div>
		<div class="content-box-content">
			<table>
				<thead>
					<tr>
						<th id="checkbox"><input class="check-all" type="checkbox" /></th>
						<th>产品ID</th>
						<th>产品名称</th>
						<th>通知概率</th>
						<th>合作方</th>
						<th>渠道</th>
						<th>支付方式</th>
						<th>时间</th>
						<th id="operation">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${gameWebBeanList}" var="gameWebBean">
						<tr>
							<td><input type="checkbox" name="index"
								value="${gameWebBean.gameId}" /></td>
							<td>${gameWebBean.gameId}</td>
							<td title="${gameWebBean.gameName}">${gameWebBean.gameName}</td>
							<td title="${gameWebBean.chance}">${gameWebBean.chance}</td>
							<td title="${gameWebBean.cpName}">${gameWebBean.cpName}</td>
							<td limit="32" title="${gameWebBean.channelName}">${gameWebBean.channelName}</td>
							<td limit="32" title="${gameWebBean.payName}">${gameWebBean.payName}</td>
							<td limit="30">${gameWebBean.modifyTime}</td>
							<td><a href="toUpdateGame?gameId=${gameWebBean.gameId}"
								title="修改"><img src="resources/images/icons/pencil.png"
									alt="修改" /></a> &nbsp;&nbsp; <a href="#"
								onclick="del('${gameWebBean.gameId}', false, null, 'deleteGame')"
								title="删除"><img src="resources/images/icons/cross.png"
									alt="删除" /></a></td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2"><a class="button bulk-actions" href="#"
							onclick="dels(false, null, 'deleteGame')">删除选中</a><a
							class="button" href="toAddGame">添加</a></td>
						<td colspan="7">
							<div class="pagination">
								<a style='color: #57a000;'>总：${page.totalCount} 条 /
									${page.pageCount} 页</a> <a id="first" href="#">&laquo;首页</a> <a
									id="previous" href="#">&laquo;上一页</a>
								<c:forEach begin="${begin}" end="${end}" var="pageNo">
									<c:choose>
										<c:when test="${pageNo eq page.currentPage}">
											<a href="#" class="number current">${pageNo}</a>
										</c:when>
										<c:otherwise>
											<a href="#" class="number">${pageNo}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<a id="next" href="#">下一页&raquo;</a> <a id="last" href="#">尾页&raquo;</a>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</body>
</html>
