$(function() {
	
	//Sidebar Accordion Menu:
		
	$("#main-nav li ul").hide();
	$("#main-nav li a").click(function() {
			$(this).parent().siblings().find("ul").slideUp("normal");
			$(this).next().slideToggle("normal");
			if (!$(this).hasClass('current')) {
				$(this).parent().siblings().find(".current").removeClass("current");
				$(this).addClass("current");
				if ($(this).parent().find("ul li").length > 0) {
					$(this).parent().find("ul li:first-child a").addClass("current");
					$("iframe").attr("src", $(this).parent().find("ul li:first-child a").attr("href"));
					$("iframe").reload();
				}
			}
		}
	);

    // Sidebar Accordion Menu Hover Effect:
		
	$("#main-nav li .nav-top-item").hover(function() {
			$(this).stop().animate({ paddingRight: "25px" }, 200);
		}, function() {
			$(this).stop().animate({ paddingRight: "15px" });
		}
	);

    //Minimize Content Box
		
	$(".content-box-header h3").css({ "cursor":"s-resize" });
	$(".closed-box .content-box-content").hide();
	$(".closed-box .content-box-tabs").hide();
	
	$(".content-box-header h3").click(function() {
		  $(this).parent().next().toggle();
		  $(this).parent().parent().toggleClass("closed-box");
		  $(this).parent().find(".content-box-tabs").toggle();
		}
	);

    // Content box tabs:
		
	$('.content-box .content-box-content div.tab-content').hide();
	$('ul.content-box-tabs li a.default-tab').addClass('current');
	$('.content-box-content div.default-tab').show();
	
	$('.content-box ul.content-box-tabs li a').click(function() { 
			$(this).parent().siblings().find("a").removeClass('current');
			$(this).addClass('current');
			var currentTab = $(this).attr('href');
			$(currentTab).siblings().hide();
			$(currentTab).show();
			return false; 
		}
	);

    // Alternating table rows:
		
	$('tbody tr:even').addClass("alt-row");

    // Check all checkboxes when the one in a table head is checked:
		
	$('.check-all').click(function() {
			$(this).parentsUntil(".content-box-content").find("input[type='checkbox']").prop('checked', $(this).is(':checked'));   
		}
	);
	
	$('input[type="checkbox"][name="index"]').click(function() {
		var checkboxNo = $('input[type="checkbox"][name="index"]').length;
		var checkedNo = $('input[type="checkbox"][name="index"]:checked').length;
		if (checkboxNo == checkedNo) {
			$('.check-all').prop('checked', true);
		} else {
			$('.check-all').prop('checked', false);
		}
	});
});  