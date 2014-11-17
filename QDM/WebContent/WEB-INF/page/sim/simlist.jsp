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
		<form action="querySimList">
			<input id="currentPage" name="page.currentPage" type="hidden"
				value="${page.currentPage}" /> <input id="pageCount" type="hidden"
				value="${page.pageCount}" />
			<table>
				<thead>
					<tr>
						<th><label>运营商</label></th>
						<th><label>指令</label></th>
						<th><label>金额（单位：分）</label></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><select id="simType" name="search.simType">
								<option value=""></option>
								<option value="0"
									<c:if test="${search.simType eq '0'}">selected="true"</c:if>>移动</option>
								<option value="1"
									<c:if test="${search.simType eq '1'}">selected="true"</c:if>>电信</option>
						</select></td>
						<td><input type="text" id="command" name="search.command"
							value="${search.command}" /></td>
						<td><input type="text" id="price" name="search.price"
							value="${search.price}" /></td>
						<td><input type="submit" value="查询" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<div class="content-box">
		<div class="content-box-header">
			<h3>指令信息</h3>
		</div>
		<div class="content-box-content">
			<table>
				<thead>
					<tr>
						<th id="checkbox"><input class="check-all" type="checkbox" /></th>
						<th>运营商</th>
						<th>指令</th>
						<th>端口</th>
						<th>金额（单位：分）</th>
						<th>适用类型</th>
						<th>时间</th>
						<th id="operation">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${simList}" var="sim">
						<tr>
							<td><input type="checkbox" name="index" value="${sim.simId}" /></td>
							<td>${sim.simType eq '0' ? '移动' : '电信'}</td>
							<td limit="35" title="${sim.command}">${sim.command}</td>
							<td limit="30" title="${sim.simServer}">${sim.simServer}</td>
							<td>${sim.price}</td>
							<td><c:choose>
									<c:when test="${sim.applyType eq '0'}">无网络</c:when>
									<c:when test="${sim.applyType eq '1'}">有网络</c:when>
									<c:when test="${sim.applyType eq '2'}">通用</c:when>
								</c:choose></td>
							<td limit="30">${sim.modifyTime}</td>
							<td><a href="toUpdateSim?simId=${sim.simId}" title="修改"><img
									src="resources/images/icons/pencil.png" alt="修改" /></a>
								&nbsp;&nbsp; <a href="#"
								onclick="del('${sim.simId}', false, null, 'deleteSim')"
								title="删除"><img src="resources/images/icons/cross.png"
									alt="删除" /></a></td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2"><a class="button bulk-actions" href="#"
							onclick="dels(false, null, 'deleteSim')">删除选中</a><a
							class="button" href="toAddSim">添加</a></td>
						<td colspan="6">
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
