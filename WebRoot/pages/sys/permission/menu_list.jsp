<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>后台管理系统</title>
<%@include file="/pages/common/common.jsp"%>
<style type="text/css">
</style>
<script type="text/javascript">
	
</script>
</head>
<body>
	<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
	权限管理 <span class="c-gray en">&gt;</span> 菜单管理<a
		class="btn btn-success radius r btn-refresh"
		style="line-height:1.6em;margin-top:3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont i-refresh">&#xe68f;</i>
	</a></nav>
	<div class="page-container">
		<div style="float: left;width: 30%;height: 80% ">
			<div id="p" class="easyui-panel" title="菜单组"
				style="width:100%;height:100%; ">
					<ul class="easyui-tree" id="menuTree"></ul>
			</div>
		</div>
		<div
			style="float: left;width: 65%; height: 80%">
			<div id="p" class="easyui-panel" title="操作"
				style="width:100%;height:100%; ">
<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">
<a href="javascript:;" onclick="addMenu('添加菜单','menu_edit.jsp?','','510')" class="btn btn-primary radius">
<i class="Hui-iconfont">&#xe600;</i> 添加同级菜单</a> 
<a href="javascript:;" id="addChild" onclick="addChild('添加菜单','menu_edit.jsp?','','510')" class="btn btn-primary radius">
<i class="Hui-iconfont">&#xe600;</i> 添加子菜单</a>
</span> </div>
	
<article class="page-container" style="margin-left:-100px;">
	<form action="${pageContext.request.contextPath }/gz/right/rightAction/updateMenu" method="post" class="form form-horizontal" id="form-member-add" >
		<!-- 隐藏数据 -->
		<input type="hidden"   id="id" name="id">
		<input type="hidden"   id="pid" name="pid">
		<input type="hidden"  id="level" name="level">
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>菜单名：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text radius" value="" placeholder="菜单名" id="name" name="name">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>菜单图标：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<select class="select select-box radius Hui-iconfont" name="icon" id="icon" >
				    <option value="616;" class="Hui-iconfont" selected>&#xe616;</option>
				    <option value="612;" class="Hui-iconfont">&#xe612;</i></option>
				    <option value="667;" class="Hui-iconfont">&#xe667;</i></option>
				    <option value="62e;" class="Hui-iconfont">&#xe62e;</i></option>
				    <option value="633;" class="Hui-iconfont">&#xe633;</i></option>
				    <option value="63c;" class="Hui-iconfont">&#xe63c;</i></option>
				    <option value="681;" class="Hui-iconfont">&#xe681;</i></option>
				    <option value="637;" class="Hui-iconfont">&#xe637;</i></option>
				    <option value="692;" class="Hui-iconfont">&#xe692;</i></option>
				    <option value="62c;" class="Hui-iconfont">&#xe62c;</i></option>
				    <option value="62b;" class="Hui-iconfont">&#xe62b;</i></option>
				    <option value="6cc;" class="Hui-iconfont">&#xe6cc;</i></option>
				    <option value="60a;" class="Hui-iconfont">&#xe60a;</i></option>
				    <option value="602;" class="Hui-iconfont">&#xe602;</i></option>
				</select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>菜单链接：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text radius" value="" placeholder="URL" id="url" name="url">
			</div>
		</div>
		
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input id="submit" class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
	</article>			
				
			</div>
		</div>

	</div>

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/pages/sys/permission/menu_list.js"></script>

	<%@include file="/pages/common/footer.jsp"%>
	<script type="text/javascript">
		
	</script>
</body>
</html>