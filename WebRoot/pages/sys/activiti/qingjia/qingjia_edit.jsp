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
<article class="page-container" style="margin-left:-100px;">
	<form action="${pageContext.request.contextPath }/gz/sys/activiti/qjAction/add" method="post" class="form form-horizontal" id="form-member-add"  >
		<!-- 隐藏数据 -->
		<input type="hidden"   id="eventid" name="eventid">
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>请假时长：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text radius" value="" placeholder="请假时长" id="task_time" name="task_time">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>请假开始时间：</label>
			<div class="formControls col-xs-4 col-sm-3">
				<input class="easyui-datebox radius" input-options="type:datebox" id="tcrq" type="text" name="tcrq" style="width:98%;height:30px" data-options="editable:false,required:true,missingMessage:'该项为必填项,选择联合站的投产日期'"/>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>请假类别：</label>
			<div class="formControls col-xs-8 col-sm-9 skin-minimal">
				<div class="radio-box">
					<input value="1" type="radio" id="sex-1" checked name="task_type" >
					<label for="sex-1">事假</label>
				</div>
				<div class="radio-box">
					<input type="radio" value="2" id="sex-2" name="task_type">
					<label for="sex-2">病假</label>
				</div>
				<div class="radio-box">
					<input type="radio" value="3" id="sex-3" name="task_type">
					<label for="sex-3">婚假</label>
				</div>
				<div class="radio-box">
					<input type="radio" value="4" id="sex-4" name="task_type">
					<label for="sex-4">其他</label>
				</div>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>请假原因：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea cols="" rows="" class="textarea valid" name="task_resonse" id="task_resonse" placeholder="说点什么...最少输入10个字符"></textarea>
			</div>
		</div>
		<!-- <div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>联系方式：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text radius" value="" placeholder="" id="phone" name="phone">
			</div>
		</div> -->
		
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input id="submit" class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</article>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/sys/activiti/qingjia/qingjia_edit.js"></script>


<script type="text/javascript">

</script> 
</body>
</html>