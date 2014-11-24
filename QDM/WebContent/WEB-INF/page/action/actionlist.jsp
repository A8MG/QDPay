<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
		<form action="queryActionList">
			<input id="currentPage" name="page.currentPage" type="hidden"
				value="${page.currentPage}" /> <input id="pageCount" type="hidden"
				value="${page.pageCount}" />
			<table>
				<thead>
					<tr>
						<th><label>产品</label></th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><s:select id="gameId" name="search" list="gameList"
								listKey="gameId" listValue="gameName" headerKey=""
								headerValue="" theme="simple" /></td>
						<td><input id="searchButton" type="button" value="查询" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<div class="content-box">
		<div class="content-box-header">
			<h3>访问信息</h3>
		</div>
		<div class="content-box-content">
			<table>
				<thead>
					<tr>
						<th>产品</th>
						<th>使用次数</th>
						<th>交易次数</th>
						<th>交易率</th>
						<th style="width: 14%">时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${actionList}" var="action">
						<tr>
							<td title="${action.gameName}" limit="30">${action.gameName}</td>
							<td>${action.openTimes}</td>
							<td>${action.confirmTimes}</td>
							<td>${action.payRate}</td>
							<td limit="30">${action.modifyTime}</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="5">
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