/**
 * 
 */

$(document).ready(function () {
	$.ajax({
		type: "post",
		url: "/ajax",
		contentType: 'application/json',
		data: JSON.stringify({
			"k1":"k1",
			"k2":"k2"
		}),
		dataType: "json",
		success: function (response) {
			if (response.code == 200) {
				$('h1').text(response.data);
			}
		}
	});
});