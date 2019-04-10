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
<article class="page-container" style="margin-left:-100px;">
	<form action="${pageContext.request.contextPath }/gz/right/rightAction/addMenu" method="post" class="form form-horizontal" id="form-member-add" >
		<!-- 隐藏数据 -->
		<input type="hidden"   id="id" name="id">
		<input type="hidden"   id="pid" name="pid">
		<input type="hidden" class="input-text radius"  id="level" name="level" value="<%=type %>">
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="roleName" name="roleName">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">备注：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="" name="">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">资源列表：</label>
			<div class="menu-div formControls col-xs-8 col-sm-9">
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
			
			<button   class="btn btn-success radius" onclick="getIdsChecked()" id="admin-role-save" name="admin-role-save"><i class="icon-ok"></i> 确定</button>
				<button type="submit" class="btn btn-success radius" id="admin-role-save" name="admin-role-save"><i class="icon-ok"></i> 确定</button>
			</div>
		</div>
	</form>
</article>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/sys/permission/role/role_edit.js"></script>


<script type="text/javascript">
$(".permission-list dt input:checkbox").click(function(){
		$(this).closest("dl").find("dd input:checkbox").prop("checked",$(this).prop("checked"));
	});
$(".permission-list2 dd input:checkbox").click(function(){
		var l =$(this).parent().parent().find("input:checked").length;
		var l2=$(this).parents(".permission-list").find(".permission-list2 dd").find("input:checked").length;
		if($(this).prop("checked")){
			$(this).closest("dl").find("dt input:checkbox").prop("checked",true);
			$(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked",true);
		}
		else{
			if(l==0){
				$(this).closest("dl").find("dt input:checkbox").prop("checked",false);
			}
			if(l2==0){
				$(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked",false);
			}
		}
	});
</script> 
</body>
</html>