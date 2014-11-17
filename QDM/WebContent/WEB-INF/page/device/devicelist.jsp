<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="resources/css/reset.css" type="text/css"
	media="screen" />
<link rel="stylesheet" href="resources/css/style.css" type="text/css"
	media="screen" />
<link rel="stylesheet" href="resources/css/invalid.css" type="text/css"
	media="screen" />
<script type="text/javascript"
	src="resources/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
	src="resources/scripts/simpla.jquery.configuration.js"></script>
<script type="text/javascript" src="resources/scripts/facebox.js"></script>
<script type="text/javascript" src="resources/scripts/jquery.wysiwyg.js"></script>
<script type="text/javascript"
	src="resources/scripts/jquery.datePicker.js"></script>
<script type="text/javascript" src="resources/scripts/jquery.date.js"></script>
</head>
<body>
	<div id="main-content">
		<form action="#" method="post">
			<label>用户名</label>
			<input class="text-input small-input" type="text" id="username"
				name="username" />
			<a class="button" style="margin-left: 30px" href="#">查询</a>
		</form>

		<div class="content-box">
			<div class="content-box-header">
				<h3>用户信息</h3>
			</div>
			<div class="content-box-content">
				<table>
					<thead>
						<tr>
							<th><input class="check-all" type="checkbox" /></th>
							<th>用户名</th>
							<th>密码</th>
							<th>权限</th>
							<th>操作</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="bulk-actions align-left">
									<a class="button" href="#">添加</a>
								</div>
								<div class="pagination">
									<a href="#" title="First Page">&laquo; First</a><a href="#"
										title="Previous Page">&laquo; Previous</a> <a href="#"
										class="number" title="1">1</a> <a href="#" class="number"
										title="2">2</a> <a href="#" class="number current" title="3">3</a>
									<a href="#" class="number" title="4">4</a> <a href="#"
										title="Next Page">Next &raquo;</a><a href="#"
										title="Last Page">Last &raquo;</a>
								</div>
								<div class="clear"></div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Lorem ipsum dolor</td>
							<td><a href="#" title="title">Sit amet</a></td>
							<td>Consectetur adipiscing</td>
							<td><a href="#" title="Edit"><img
									src="resources/images/icons/pencil.png" alt="Edit" /></a>
								&nbsp;&nbsp; <a href="#" title="Delete"><img
									src="resources/images/icons/cross.png" alt="Delete" /></a>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
