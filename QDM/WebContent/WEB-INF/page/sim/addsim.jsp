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
<script>
	$(document).ready(function() {
		$("#simType").change(function() {
			if ($(this).val() == 0) {
				$("#applyType").show();
				$("#cmcc").removeAttr("disabled");
				$("#common").attr("disabled", "disabled");
			} else {
				$("#applyType").hide();
				$("#cmcc").attr("disabled", "disabled");
				$("#common").removeAttr("disabled");
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
					<form action="addSim" method="post">
						<label>运营商</label> <select id="simType" name="sim.simType">
							<option value="0">移动</option>
							<option value="1">电信</option>
						</select>
						<div id="applyType">
							<label>适用类型</label> <select id="cmcc" name="sim.applyType">
								<option value="0">无网络</option>
								<option value="1">有网络</option>
							</select> <input id="common" name="sim.applyType" type="hidden" value="2"
								disabled="disabled" />
						</div>
						<label>指令</label> <input type="text" id="command"
							name="sim.command" onfocus="removeTip('command')"
							onblur="checkRequired('command')" /> <label>端口</label> <input
							type="text" id="simServer" name="sim.simServer"
							onfocus="removeTip('simServer')"
							onblur="checkRequired('simServer')" /> <label>金额（单位：分）</label> <input
							type="text" id="price" name="sim.price"
							onfocus="removeTip('price')" onblur="checkRequired('price')" />
						<div class="clear"></div>
						<input type="submit" value="提交" /> <a class="button button-space"
							href="querySimList">返回</a>
					</form>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
