<%@ page contentType="text/html;charset=UTF-8" %>

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
<div class="page-containe">
  <ul id="search_div" class="Huifold">
   <li class="item"> <h4 class="search_click"></h4> 
		<div class="text-a" style="display:none; border: 3px dashed rgb(245, 245, 245);"> 
			<form id="search_form">
			<table>
			<tr class="text-c">
			    <th width="8%" style="text-align:center;"> 登录名: </th>
				<th width="30%"><input type="text" class="input-text" style="width:250px" placeholder="登录名" id="login_name" name="login_name"></th>
				<th width="8%" style="text-align:center;"> 姓名: </th>
				<th width="30%"><input type="text" class="input-text" style="width:250px" placeholder="姓名" id="name" name="name"></th>
				<th width="8%" style="text-align:center;"> 性别: </th>
				<th width="30%"><input type="text" class="input-text" style="width:250px" placeholder="性别" id="name" name="sex"></th>
			</tr>
			
			<!-- 搜索按钮 -->
			<tr class="text-c">
				<th width="8%"></th><th width="30%"></th><th width="8%"></th><th width="30%"></th><th width="8%"></th>
				<th text-align="right" width="30%"><span style="float:right"   class="btn btn-success radius" type="button"  onclick="search()"><i class="Hui-iconfont">&#xe665;</i>搜索 </span></th>
			</tr>
		</table>
		</form>
   </li>
  </ul>
	<div class="cl pd-5 bg-1 bk-gray div-btn "> <span class="l"><a href="javascript:;" id="del_button"   class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a href="javascript:;" onclick="member_add('添加角色','role_edit.jsp?type=add','','510')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加用户</a></span> 
	<a class="btn btn-success radius r btn-refresh" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont i-refresh">&#xe68f;</i></a>
	</div>
	
	
	<!-- 顶部分页栏  -->
    <div id="top_pagination"></div> 
	<!-- 列表展示信息 -->
  	<div id="divDg" style="height: 91%;">
	    <table id="dataGrid" width="100%" cellspacing="0" cellpadding="0">  
		</table> 
	</div>
</div>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/sys/permission/role/role_list.js"></script>

<%@include file="/pages/common/footer.jsp"%>
<script type="text/javascript">
 </script>
</body>
</html>