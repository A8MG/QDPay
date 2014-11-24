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
					<form action="addChannel" method="post">
						<label>渠道ID</label> <input type="text" id="channelId"
							name="channel.channelId" onfocus="removeTip('channelId')"
							onblur="checkExist('channelId', 'checkChannelId')" /> <label>渠道名称</label>
						<input type="text" id="channelName" name="channel.channelName"
							onfocus="removeTip('channelName')"
							onblur="checkRequired('channelName')" />
						<div class="clear"></div>
						<input type="submit" value="提交" /> <a class="button button-space"
							href="queryChannelList">返回</a>
					</form>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
