<!DOCTYPE html>
<html lang="zh-cn">

<head>
	<meta charset="utf-8" />
	<title>权限分配 - 权限管理</title>
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
	<link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="${basePath}/favicon.ico" />
	<link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet" />
	<link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet" />
</head>

<body data-target="#one" data-spy="scroll">
	<@_top.top 3 />
	<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
		<div class="row">
			<@_left.role 4 />
			<div class="col-md-10">
				<h2>权限分配</h2>
				<hr>
				<form method="post" action="" id="formId" class="form-inline">
					<div clss="well">
						<div class="form-group">
							<input type="text" class="form-control" style="width: 300px;" value="${findContent?default('')}" name="findContent"
							 id="findContent" placeholder="输入角色名称 / 角色类型">
						</div>
						<span class="">
							<button type="submit" class="btn btn-primary">查询</button>
							<@shiro.hasPermission name="/api/permission/clearPermissionByRoleIds">
								<button type="button" id="deleteAll" class="btn  btn-danger">清空角色权限</button>
							</@shiro.hasPermission>
						</span>
					</div>
					<hr>
					<table class="table table-bordered">
						<input type="hidden" id="selectRoleId">
						<tr>
							<th width="5%"><input type="checkbox" id="checkAll" /></th>
							<th width="10%">角色名称</th>
							<th width="10%">角色类型</th>
							<th width="60%">拥有的权限</th>
							<th width="15%">操作</th>
						</tr>
						<#if page?exists && page.list?size gt 0>
							<#list page.list as it>
								<tr>
									<td><input value="${it.id}" check='box' type="checkbox" /></td>
									<td>${it.name}</td>
									<td>${it.type}</td>
									<td permissionIds="${it.permissionIds?default('')}">${it.permissionNames?default('-')}</td>
									<td>
										<@shiro.hasPermission name="/api/permission/addPermission2Role">
											<i class="glyphicon glyphicon-share-alt"></i><a href="javascript:selectPermissionById(${it.id});">选择权限</a>
										</@shiro.hasPermission>
									</td>
								</tr>
							</#list>
							<#else>
								<tr>
									<td class="text-center danger" colspan="4">没有找到角色</td>
								</tr>
						</#if>
					</table>
					<#if page?exists>
						<div class="pagination pull-right">
							${page.pageHtml}
						</div>
					</#if>
				</form>
			</div>
		</div>

		<div class="modal fade bs-example-modal-sm" id="selectPermission" tabindex="-1" role="dialog" aria-labelledby="selectPermissionLabel">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="selectPermissionLabel">添加权限</h4>
					</div>
					<div class="modal-body">
						<form id="boxRoleForm">
							loading...
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" onclick="selectPermission();" class="btn btn-primary">Save</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
	<script src="${basePath}/js/common/layer/layer.js"></script>
	<script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="${basePath}/js/shiro.demo.js"></script>
	<script src="${basePath}/js/permission.allocation.js"></script>
</body>

</html>