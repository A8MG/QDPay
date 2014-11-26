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
		getOtherPay();
	});

	function getOtherPay() {
		var firstPayId = $("#firstPay").val();
		$.ajax({
			url : "ajaxPay",
			data : {
				firstPayId : firstPayId,
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
					<form action="addGame" method="post">
						<label>产品ID</label> <input type="text" id="gameId"
							name="game.gameId" onfocus="removeTip('gameId')"
							onblur="checkExist('gameId', 'checkGameId')" /> <label>产品名称</label>
						<input type="text" id="gameName" name="game.gameName"
							onfocus="removeTip('gameName')"
							onblur="checkRequired('gameName')" /> <label>通知概率</label>
						<s:select id="chance" name="game.chance"
							list="{'100%', '98%', '96%', '94%', '92%', '90%'}" theme="simple" />
						<label>合作方</label>
						<s:select id="cpId" name="cpId" list="cpList" listKey="cpId"
							listValue="cpName" theme="simple" />
						<label>渠道</label>
						<div class="checkbox">
							<s:checkboxlist id="channelId" name="channelIdList"
								list="channelList" listKey="channelId" listValue="channelName"
								theme="simple" />
						</div>
						<label>首选支付方式</label>
						<s:select id="firstPay" name="firstPayId" list="payList"
							listKey="payId" listValue="payName" onchange="getOtherPay()"
							theme="simple" />
						<label>其它支付方式</label>
						<div id="otherPay" class="checkbox"></div>
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
