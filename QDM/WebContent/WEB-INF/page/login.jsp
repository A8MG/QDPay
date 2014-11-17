<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>QDM</title>
<link rel="icon" href="resources/images/QDlogo.png" />
<link rel="stylesheet" href="resources/css/reset.css" />
<link rel="stylesheet" href="resources/css/style.css" />
<link rel="stylesheet" href="resources/css/invalid.css" />
<script src="resources/scripts/jquery.min.js"></script>
<script>
	$(function() {
		if (window != top) {
			top.location.href = location.href;
		}

		$("#username").focus();

		$("#loginbutton").click(function() {
			check();
		});

		$(document).keypress(function(event) {
			if (event.keyCode == 13) {
				check();
			}
		});

		function check() {
			var username = $("#username").val();
			var password = $("#password").val();
			var isEmpty = true;

			if (username == "" || password == "") {
				$("#errorMsg").html("用户名或密码不能为空！");
				$("#errorMsg").show();
				isEmpty = false;
			}

			if (isEmpty) {
				$.ajax({
					url : "index",
					data : {
						username : username,
						password : password
					},
					success : function(result) {
						if (result == "true") {
							location.href = "welcome";
						} else {
							$("#errorMsg").html("用户名或密码有误！");
							$("#errorMsg").show();
						}
					}
				});
			}
		}
	});
</script>
</head>
<body id="login">
	<div id="login-wrapper" class="png_bg">
		<div id="login-top">
			<span style="font-size: 60px; font-weight: bold;">QD·M</span>
		</div>
		<div id="login-content">
			<form>
				<div class="notification information png_bg">
					<div id="errorMsg" style="display: none;"></div>
				</div>
				<p>
					<label>用户名</label><input id="username" class="text-input"
						type="text" />
				</p>
				<div class="clear"></div>
				<p>
					<label>密码</label><input id="password" class="text-input"
						type="password" />
				</p>
				<div class="clear"></div>
				<p>
					<input id="loginbutton" class="button" type="button" value="登录" />
				</p>
			</form>
		</div>
	</div>
</body>
</html>
