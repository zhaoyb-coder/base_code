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

<div class="page-containe ">

  <!-- 搜索栏 -->
  <ul id="search_div" class="Huifold">
   <li class="item"> 
	   <h4 class="search_click"></h4> 
		<div class="text-a" > 
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
			<tr class="text-c">
			    <th width="8%" style="text-align:center;"> 创建时间: </th>
				<th width="30%"><input type="text" readonly="readonly" onfocus="WdatePicker({ maxDate:'2020-1-1' })" id="datemax" class="input-text Wdate" style="width:250px;"></th>
				<th width="8%" style="text-align:center;"> 状态: </th>
				<th width="30%"><input type="text" class="input-text" style="width:250px" placeholder="性别" id="" name=""></th>
				<th width="8%" style="text-align:center;"></th>
				<th width="30%"> </th>
			
			</tr>
			<tr class="text-c">
				<th width="8%"></th><th width="30%"></th><th width="8%"></th><th width="30%"></th><th width="8%"></th>
				<th text-align="right" width="30%"><span style="float:right"   class="btn btn-success radius" type="button"  onclick="search()"><i class="Hui-iconfont">&#xe665;</i>搜索 </span></th>
			</tr>
		</table>
		</form>
   </li>
  </ul> 
  
  	<!-- 通用工具栏 -->
    <div id="selectColumnBox" class="selectColumnBox">
		<ul class="tool-btns-container">
			<li><a href="#"><span class="excel" title="导出excel"></span></a></li>
			<li><a href="#"><span class="pdf" title="导出pdf"></span></a></li>
		</ul>
		<ul class="dropdown-container">
			<li><input name="select_column" class="select-column-input" readonly="readonly" value="定制可见列..." type="text"></li>
			<li><ul class="column-items-container" style="display: none;">
				<li class="columnItem"><input id="ck_DEPNAME" name="DEPNAME" value="DEPNAME" type="checkbox"><label class="colName" for="ck_DEPNAME">单位名称 </label></li>
				</ul></li>
			<li>
				<ul class="dropdown-btns-container" style="display: none;"><li><input id="ck_select_all_dropdown" type="checkbox"><label for="ck_select_all_dropdown">全选/反选</label></li><li><input class="select-column-btn" value="确认" type="button"><input class="select-column-btn" value="保存" title="保存定制可见列" type="button"><input class="select-column-btn" value="关闭" type="button"></li></ul>
			</li>
		</ul>
	</div>
   <!-- 通用按钮栏 -->
   <div class="toolBar">
		<input class="delete" value="删除" type="button" id="del_button" onclick="batchDel()">
		<input class="add" value="新增" type="button" onclick="member_add('添加用户','user_edit.jsp?type=add','','510')">
   </div>
	
	<!-- 顶部分页栏  -->
    <div id="top_pagination"></div> 
	<!-- 列表展示信息 -->
  	<div id="divDg" >
	    <table id="dataGrid" width="100%" cellspacing="0" cellpadding="0">  
		</table> 
	</div>
</div>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/sys/user/user_list.js"></script>

<%@include file="/pages/common/footer.jsp"%>
<script type="text/javascript">
 </script>
</body>
</html>