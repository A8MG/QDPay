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
	$(function() {
		var choosedPay = "${choosedPayList}".substring(1,
				"${choosedPayList}".length - 1).replace(/\s+/g, "");
		ajaxPay(choosedPay);
	});

	function getOtherPay() {
		ajaxPay(null);
	}

	function ajaxPay(choosedPay) {
		var firstPayId = $("#firstPay").val();
		$.ajax({
			url : "ajaxPay",
			data : {
				firstPayId : firstPayId,
				choosedPay : choosedPay
			},
			success : function(result) {
				$("#otherPay").html(result);
			}
		});
	}
</script>
</head>
<body>
	<div id="edit">
		<table>
			<tr>
				<td></td>
				<td>
					<form action="updateGame" method="post">
						<label>产品ID</label> <input type="text" id="gameId"
							name="game.gameId" value="${gameWebBean.gameId}"
							readonly="readonly" /> <label>产品名称</label> <input type="text"
							id="gameName" name="game.gameName"
							value="${gameWebBean.gameName}" onfocus="removeTip('gameName')"
							onblur="checkRequired('gameName')" /> <label>通知概率</label>
						<s:select id="chance" name="gameWebBean.chance"
							list="{'100%', '98%', '96%', '94%', '92%', '90%'}" theme="simple" />
						<label>合作方</label> <input type="text" id="cpName"
							value="${gameWebBean.cpName}" readonly="readonly" /> <label>首选支付方式</label>
						<s:select id="firstPay" name="firstPayId" list="payList"
							listKey="payId" listValue="payName" onchange="getOtherPay()"
							theme="simple" />
						<label>其它支付方式</label>
						<div id="otherPay"></div>
						<div class="clear"></div>
						<input type="submit" value="提交" /> <a class="button button-space"
							href="queryGameList">返回</a>
					</form>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
