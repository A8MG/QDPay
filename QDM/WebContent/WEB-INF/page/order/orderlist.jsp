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
		$("#startTime").datepicker({
			changeMonth : true,
			changeYear : true,
			maxDate : new Date(),
			onClose : function(selectedDate) {
				$("#endTime").datepicker("option", "minDate", selectedDate);
			}
		});

		$("#endTime").datepicker({
			changeMonth : true,
			changeYear : true,
			maxDate : new Date(),
			onClose : function(selectedDate) {
				if (selectedDate == "") {
					selectedDate = new Date();
				}
				$("#startTime").datepicker("option", "maxDate", selectedDate);
			}
		});
	});
</script>
</head>
<body>
	<div id="search">
		<form action="queryOrderList">
			<input id="currentPage" name="page.currentPage" type="hidden"
				value="${page.currentPage}" /> <input id="pageCount" type="hidden"
				value="${page.pageCount}" />
			<table>
				<thead>
					<tr>
						<th><label>订单号</label></th>
						<th><label>产品</label></th>
						<th><label>时间</label></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="text" id="orderNo" name="search.orderNo"
							value="${search.orderNo}" /></td>
						<td><s:select id="gameId" name="search.gameId"
								list="gameList" listKey="gameId" listValue="gameName"
								headerKey="" headerValue="" theme="simple" /></td>
						<td><input type="text" id="startTime" class="start-time"
							name="search.startTime" value="${search.startTime}" /><input
							type="text" id="endTime" class="end-time" name="search.endTime"
							value="${search.endTime}" /></td>
						<td><input id="searchButton" type="button" value="查询" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<div class="content-box">
		<div class="content-box-header">
			<h3>订单信息</h3>
		</div>
		<div class="content-box-content">
			<table>
				<thead>
					<tr>
						<c:if test="${session.user.authority eq '0'}">
							<th id="checkbox"><input class="check-all" type="checkbox" /></th>
						</c:if>
						<th>订单号</th>
						<th>自定义信息</th>
						<th>金额（单位：分）</th>
						<th>订单状态</th>
						<th>短信状态</th>
						<th>应答状态</th>
						<th>产品</th>
						<th>支付方式</th>
						<th style="width: 14%">时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${orderWebBeanList}" var="order">
						<tr>
							<c:if test="${session.user.authority eq '0'}">
								<td><input type="checkbox" name="index"
									value="${order.orderNo}" /></td>
							</c:if>
							<td limit="30">${order.orderNo}</td>
							<td title="${order.prop}">${order.prop}</td>
							<td>${order.price}</td>
							<td>${order.state eq '1' ? '已付款' : '未付款'}</td>
							<td><c:choose>
									<c:when test="${order.sms eq '1'}">已发送</c:when>
									<c:when test="${order.sms eq '0'}">未发送</c:when>
									<c:otherwise>不支持</c:otherwise>
								</c:choose></td>
							<td><c:choose>
									<c:when test="${order.reply eq '1'}">已回复</c:when>
									<c:when test="${order.reply eq '0'}">未回复</c:when>
									<c:otherwise>预留</c:otherwise>
								</c:choose></td>
							<td title="${order.gameName}">${order.gameName}</td>
							<td>${order.payName}</td>
							<td limit="30">${order.modifyTime}</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<c:if test="${session.user.authority eq '0'}">
							<td colspan="2"><a class="button" href="#"
								onclick="dels(false, null, 'deleteOrder')">删除选中</a></td>
						</c:if>
						<td colspan="9">
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
