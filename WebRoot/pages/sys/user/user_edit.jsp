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
<article class="page-container" style="margin-left:-100px;  
    
" >
	<form action="${pageContext.request.contextPath }/gz/sys/user/userAction/add" method="post" class="form form-horizontal" id="form-member-add"  enctype="multipart/form-data">
		<!-- 隐藏数据 -->
		<input type="hidden"   id="id" name="id">
		<input type="hidden"  id="file" name="file">
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>登录名：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text radius" value="" placeholder="登录名" id="login_name" name="login_name">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户名：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text radius" value="" placeholder="用户名" id="name" name="name">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>密码：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text radius" value="" placeholder="密码" id="password" name="password">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>性别：</label>
			<div class="formControls col-xs-8 col-sm-9 skin-minimal">
				<div class="radio-box">
					<input name="sex" value="1" type="radio" id="sex-1" checked>
					<label for="sex-1">男</label>
				</div>
				<div class="radio-box">
					<input type="radio" value="2" id="sex-2" name="sex">
					<label for="sex-2">女</label>
				</div>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户类型：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<select class="select select-box radius  "  size="1" name="demo1">
				    <option value="0" selected>普通用户</option>
				    <option value="1">管理员</option>
				</select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>年龄：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text radius" value="" placeholder="年龄" id="age" name="age">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>手机：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text radius" value="" placeholder="" id="phone" name="phone">
			</div>
		</div>
		<!-- <div class="row cl" id="uploadDiv">
			<label class="form-label col-xs-4 col-sm-3">附件：</label>
			<div class="formControls col-xs-8 col-sm-9"> <span class="btn-upload form-group">
				<input class="input-text upload-url radius" type="text"   id="uploadpath" name="uploadpath" readonly   style="width:200px">
				<a href="javascript:void();" class="btn btn-primary radius upload-btn"><i class="Hui-iconfont">&#xe642;</i> 浏览文件</a>
				<a href="javascript:void();" class="btn btn-primary radius download-btn"><i class="Hui-iconfont">&#xe640;</i> 下载文件</a>
				<input type="file" multiple name="uploadFile"   class="input-file">
				</span> </div>
		</div>
		<div class="row cl" id="downloadDiv" style="display: none;">
			<label class="form-label col-xs-4 col-sm-3">附件：</label>
			<div class="formControls col-xs-8 col-sm-9"> <span class="btn-upload form-group">
				<input class="input-text upload-url radius" type="text"   id="uploadpath" name="uploadpath" readonly   style="width:200px">
				<a href="javascript:void();" class="btn btn-primary radius download-btn"><i class="Hui-iconfont">&#xe640;</i> 下载文件</a>
				</span> </div>
		</div> -->
		
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input id="submit" class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</article>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/sys/user/user_edit.js"></script>


<script type="text/javascript">

</script> 
</body>
</html>