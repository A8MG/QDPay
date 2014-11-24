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
					<form action="updateSim" method="post">
						<input type="hidden" id="simId" name="sim.simId"
							value="${sim.simId}" /> <label>运营商</label> <input type="text"
							id="simType" value="${sim.simType eq '0' ? '移动' : '电信'}"
							readonly="readonly" />
						<c:if test="${sim.simType eq '0'}">
							<label>适用类型</label>
							<input type="text" id="cmcc"
								value="${sim.applyType eq '0' ? '无网络' : '有网络'}"
								readonly="readonly" />
						</c:if>
						<label>指令</label> <input type="text" id="command"
							name="sim.command" value="${sim.command}"
							onfocus="removeTip('command')" onblur="checkRequired('command')" />
						<label>端口</label> <input type="text" id="simServer"
							name="sim.simServer" value="${sim.simServer}"
							onfocus="removeTip('simServer')"
							onblur="checkRequired('simServer')" /> <label>金额（单位：分）</label> <input
							type="text" id="price" name="sim.price" value="${sim.price}"
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
