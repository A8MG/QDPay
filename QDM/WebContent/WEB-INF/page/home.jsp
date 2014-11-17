<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="resources/css/reset.css" />
<link rel="stylesheet" href="resources/css/content.css" />
<link rel="stylesheet" href="resources/css/invalid.css" />
<script src="resources/scripts/jquery.min.js"></script>
<script src="resources/scripts/configuration.js"></script>
<script src="resources/scripts/highcharts/highcharts.js"></script>
<script src="resources/scripts/highcharts/modules/exporting.js"></script>
<script src="resources/scripts/highcharts/modules/export-csv.js"></script>
<script>
	$(function() {
		$.getJSON("homeAjax", function(data) {
			$('#container').highcharts(
					{
						chart : {
							style : {fontFamily : '"Hiragino Sans GB", "Microsoft YaHei", "WenQuanYi Micro Hei"'}
						},
						title : {
							text : '上一个月成交额'
						},
						xAxis : {
							categories : [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
									12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22,
									23, 24, 25, 26, 27, 28, 29, 30, 31 ]
						},
						yAxis : {
							title : {
								text : '金额（单位/元）'
							},
							min : 0
						},
						credits : {
							enabled : false
						},
						plotOptions : {
				            line : {
				                dataLabels : {
				                    enabled : true
				                },
				                enableMouseTracking : true
				            }
				        },
						series : data
					});
		});
	});
</script>
</head>
<body>
	<div id="container"></div>
</body>
</html>
