<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<div id="edit">
		<table>
			<tr>
				<td></td>
				<td>
					<form action="updateUser" method="post">
						<label>用户名</label> <input type="text" id="userName"
							name="user.username" value="${user.username}" readonly="readonly" />
						<label>新密码</label> <input type="password" id="newPassword"
							name="user.password" onfocus="removeTip('newPassword')"
							onblur="checkPasswordSame('newPassword', 'confirmPassword')" />
						<label>确认密码</label> <input type="password" id="confirmPassword"
							onfocus="removeTip('confirmPassword')"
							onblur="checkPasswordSame('confirmPassword', 'newPassword')" />
						<div class="clear"></div>
						<input type="submit" value="提交" /> <a class="button button-space"
							href="queryUserList">返回</a>
					</form>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
