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
	<form action="${pageContext.request.contextPath }/gz/sys/user/userAction/add" method="post" class="form form-horizontal" id="form-member-add"  enctype="multipart/form-data">
		<!-- 隐藏数据 -->
		<input type="hidden"   id="id" name="id">
		<input type="hidden"  id="file" name="file">
		
		
		<div class="row cl" id="uploadDiv">
			<label class="form-label col-xs-4 col-sm-3">流程文件：</label>
			<div class="formControls col-xs-8 col-sm-9"> <span class="btn-upload form-group">
				<input class="input-text upload-url radius" type="text"   id="uploadpath" name="uploadpath" readonly   style="width:200px">
				<a href="javascript:void();" class="btn btn-primary radius upload-btn"><i class="Hui-iconfont">&#xe642;</i> 浏览文件(只允许上传流程zip文件)</a>
				<input type="file" multiple name="uploadFile"   class="input-file {required:true}" >
				</span> </div>
		</div>
		
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input id="submit" class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</article>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/sys/activiti/lcbs/lcbs_edit.js"></script>


<script type="text/javascript">

</script> 
</body>
</html>