<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<div id="edit">
		<table>
			<tr>
				<td></td>
				<td>
					<form action="updateCp" method="post">
						<label>合作方ID</label> <input type="text" id="cpId" name="cp.cpId"
							value="${cp.cpId}" readonly="readonly" /> <label>合作方名称</label> <input
							type="text" id="cpName" name="cp.cpName" value="${cp.cpName}"
							onfocus="removeTip('cpName')" onblur="checkRequired('cpName')" />
						<label>请求地址</label> <input type="text" id="httpUrl"
							name="cp.httpUrl" value="${cp.httpUrl}" />
						<div class="clear"></div>
						<input type="submit" value="提交" /> <a class="button button-space"
							href="queryCpList">返回</a>
					</form>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
