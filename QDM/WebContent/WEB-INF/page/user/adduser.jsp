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
<script>
	$(document).ready(function() {
		$("#authority").change(function() {
			if ($(this).val() == 0) {
				$("#cp").hide();
				$("#cpId").attr("disabled", "disabled");
			} else {
				$("#cp").show();
				$("#cpId").removeAttr("disabled");
			}
		});
	});
</script>
</head>
<body>
	<div id="edit">
		<table>
			<tr>
				<td></td>
				<td>
					<form action="addUser" method="post">
						<label>用户名</label> <input type="text" id="userName"
							name="user.username" onfocus="removeTip('userName')"
							onblur="checkExist('userName', 'checkUserName')" /> <label>密码</label>
						<input type="password" id="password" name="user.password"
							onfocus="removeTip('password')"
							onblur="checkPasswordSame('password', 'confirmPassword')" /> <label>确认密码</label>
						<input type="password" id="confirmPassword"
							onfocus="removeTip('confirmPassword')"
							onblur="checkPasswordSame('confirmPassword', 'password')" /> <label>权限</label>
						<select id="authority" name="user.authority">
							<option value="1">合作方</option>
							<option value="0">管理员</option>
						</select>
						<div id="cp">
							<label>合作方</label>
							<s:select id="cpId" name="cpId" list="cpList" listKey="cpId"
								listValue="cpName" theme="simple" />
						</div>
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
