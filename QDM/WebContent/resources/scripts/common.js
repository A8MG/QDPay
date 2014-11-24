//delete
function del(ids, validate, check, action) {
	if (validate) {
		delAjax(ids, check, action);
	} else {
		submit(ids, action);
	}
}

function dels(validate, check, action) {
	if ($("input[name='index']:checkbox:checked").length > 0) {
		var ids = "";
		$("input[name='index']:checkbox:checked").each(function() {
			ids += "," + $(this).val();
		});
		ids = ids.substring(1, ids.length);
		if (validate) {
			delAjax(ids, check, action);
		} else {
			submit(ids, action);
		}
	} else {
		alert("至少选择一条记录！");
	}
}

function delAjax(ids, check, action) {
	$.ajax({
		url : check,
		data : {
			ids : ids
		},
		success : function(result) {
			if (result == 'true') {
				alert("记录已被绑定，请解绑后再执行此操作！");
			} else {
				submit(ids, action);
			}
		}
	});
}

function submit(ids, action) {
	if (confirm("确认删除此记录？")) {
		location.href = action + "?ids=" + ids;
	}
}

// validate
var requiredError = false;
var existError = false;
var sameError = false;

function removeTip(id) {
	$("#" + id).next("span").remove();
	$("#" + id).css("border-color", "#d5d5d5");
}

function checkRequired(id) {
	if ($("#" + id).val() != "") {
		$("#" + id).after("<span class='input-notification success'></span>");
		requiredError = false;
		return true;
	} else {
		$("#" + id).after("<span class='input-notification error'>必填项</span>");
		$("#" + id).css("border-color", "red");
		requiredError = true;
		return false;
	}
}

function checkExist(id, check) {
	if (checkRequired(id)) {
		removeTip(id);
		$.ajax({
			url : check,
			data : {
				value : $("#" + id).val()
			},
			success : function(result) {
				if (result == 'true') {
					$("#" + id).after("<span class='input-notification success'></span>");
					existError = false;
				} else {
					$("#" + id).after("<span class='input-notification error'>已存在</span>");
					$("#" + id).css("border-color", "red");
					existError = true;
				}
			}
		});
	}
}


function checkPasswordSame(fid, sid) {
	if (checkRequired(fid)) {
		if ($("#" + sid).val() != "" && $("#" + fid).val() != $("#" + sid).val()) {
			removeTip(fid);
			$("#" + fid).after("<span class='input-notification error'>密码不一致</span>");
			$("#" + fid).css("border-color", "red");
			sameError = true;
		} else {
			sameError = false;
		}
	}
}

$(function() {
	$(document).keypress(function(event) {
		if (event.keyCode == 13) {
			return false;
		}
	});
	
	$("#edit input").not("[readonly='readonly']").each(function() {
		if ($(this).is(":visible")) {
			$(this).focus().val($(this).val());
			return false;
		}
	});
	
	$("#edit input:text[readonly]").each(function() {
		$(this).focus(function() {
			$(this).blur();
		});
	});
	
	$("#edit form").submit(function() {
		$("#edit form input[onblur]").each(function() {
			if ($(this).val() == "") {
				var id = $(this).attr("id");
				removeTip(id);
				checkRequired(id);
			}
		});
		
		if (requiredError || existError || sameError) {
			return false;
		}
	});
	
	// page
	var currentPage = $("#currentPage");
	var pageCount = $("#pageCount");
	var form = $("#search form");
	
	if (pageCount.val() * 1 > 5) {
		var beginPage = 0;
		if (pageCount.val() * 1 % 5 == 0) {
			beginPage = (pageCount.val() * 1 / 5 - 1) * 5 + 1;
		} else {
			beginPage = (pageCount.val() * 1 - pageCount.val() * 1 % 5) / 5 * 5 + 1;
		}
		if (currentPage.val() * 1 < beginPage) {
			$(".pagination .number:last").after("<a style='color:#57a000;'>&hellip;</a>");
		}
		if (currentPage.val() * 1 > 5) {
			$(".pagination .number:first").before("<a style='color:#57a000;'>&hellip;</a>");
		}
	}
	
	$("#searchButton").click(function() {
		currentPage.attr("value", 1);
		form.submit();
	});

	$(".pagination #first").click(function() {
		if (currentPage.val() != 1) {
			currentPage.attr("value", 1);
			form.submit();
		}
	});

	$(".pagination #last").click(function() {
		if (currentPage.val() * 1 < pageCount.val() * 1) {
			currentPage.attr("value", pageCount.val());
			form.submit();
		}
	});

	$(".pagination #previous").click(function() {
		if (currentPage.val() > 1) {
			currentPage.attr("value", currentPage.val() * 1 - 1);
			form.submit();
		}
	});

	$(".pagination #next").click(function() {
		if (currentPage.val() * 1 < pageCount.val() * 1) {
			currentPage.attr("value", currentPage.val() * 1 + 1);
			form.submit();
		}
	});

	$(".pagination .number").click(function() {
		if (currentPage.val() != $(this).html()) {
			currentPage.attr("value", $(this).html());
			form.submit();
		}
	});
	
	// maxLength
	$(".content-box tbody td").each(function() {
		var maxLength = 10;
		var limit = $(this).attr("limit");
		if (limit != null && limit != "") {
			maxLength = limit;
		}
		if ($(this).text().trim().replace(/[^\x00-\xff]/g, "xx").length > maxLength) {
			if (/[^\x00-\xff]/g.test($(this).text())) {
				$(this).text($(this).text().substring(0, maxLength / 2) + "...");
			} else {
				$(this).text($(this).text().substring(0, maxLength) + "...");
			}
		}
	});
});