<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <%
String _menu = request.getParameter("_menu"); 
%>   
<title>后台管理系统</title>
<%@include file="/pages/common/common.jsp"%>
<style type="text/css">

</style>
<script type="text/javascript">
 var _menu = '<%= _menu%>';
</script>
</head>
<body><%=type %>
<article class="page-container" style="margin-left:-100px;">
	<form action="${pageContext.request.contextPath }/gz/right/rightAction/addMenu" method="post" class="form form-horizontal" id="form-member-add" >
		<!-- 隐藏数据 -->
		<input type="hidden"   id="id" name="id">
		<input type="hidden"   id="pid" name="pid">
		<input type="text" class="input-text radius"  id="level" name="level" value="<%=type %>">
		
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
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/sys/permission/menu_edit.js"></script>


<script type="text/javascript">

</script> 
</body>
</html>