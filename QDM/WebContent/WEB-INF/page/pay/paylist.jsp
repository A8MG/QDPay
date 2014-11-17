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
		<form action="queryPayList">
			<input id="currentPage" name="page.currentPage" type="hidden"
				value="${page.currentPage}" /> <input id="pageCount" type="hidden"
				value="${page.pageCount}" />
			<table>
				<thead>
					<tr>
						<th><label>支付ID</label></th>
						<th><label>支付名称</label></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="text" id="payId" name="search.payId"
							value="${search.payId}" /></td>
						<td><input type="text" id="payName" name="search.payName"
							value="${search.payName}" /></td>
						<td><input type="submit" value="查询" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<div class="content-box">
		<div class="content-box-header">
			<h3>支付信息</h3>
		</div>
		<div class="content-box-content">
			<table>
				<thead>
					<tr>
						<th id="checkbox"><input class="check-all" type="checkbox" /></th>
						<th>支付ID</th>
						<th>支付名称</th>
						<th>时间</th>
						<th id="operation">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${payList}" var="pay">
						<tr>
							<td><input type="checkbox" name="index" value="${pay.payId}" /></td>
							<td>${pay.payId}</td>
							<td title="${pay.payName}">${pay.payName}</td>
							<td limit="30">${pay.modifyTime}</td>
							<td><a href="toUpdatePay?payId=${pay.payId}" title="修改"><img
									src="resources/images/icons/pencil.png" alt="修改" /></a>
								&nbsp;&nbsp; <a href="#"
								onclick="del('${pay.payId}', true, 'payBindGame', 'deletePay')"
								title="删除"><img src="resources/images/icons/cross.png"
									alt="删除" /></a></td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2"><a class="button bulk-actions" href="#"
							onclick="dels(true, 'payBindGame', 'deletePay')">删除选中</a><a
							class="button" href="toAddPay">添加</a></td>
						<td colspan="3">
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
