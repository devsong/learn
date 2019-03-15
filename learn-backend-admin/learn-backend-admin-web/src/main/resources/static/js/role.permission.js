$(function () {
	// 加载 permission tree data
	var load = layer.load();
	$.post("/api/role/getPermissionTree", {}, function (data) {
		layer.close(load);
		if (data && !data.length) {
			return $("#getPermissionTree").html(
				'<code>您没有任何权限。只有默认的个人中心。</code>'), !1;
		}
		$('#getPermissionTree').treeview({
			levels: 1, // 层级
			color: "#428bca",
			nodeIcon: "glyphicon glyphicon-user",
			showTags: true, // 显示数量
			data: data
			// 数据
		});
	}, 'json');
});