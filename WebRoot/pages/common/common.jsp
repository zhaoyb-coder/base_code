
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
String type = request.getParameter("type"); 
String eventid = request.getParameter("eventid");

//流程实例key
String proInskey = request.getParameter("processInstanceKey");
%>

<link rel="stylesheet" type="text/css" href="<%=basePath%>style/static/h-ui/css/H-ui.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>style/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>style/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>style/static/h-ui.admin/skin/green/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>style/static/h-ui.admin/css/style.css" /> 
<link rel="stylesheet" href="<%=basePath%>style/lib/layer/2.4/skin/layer.css" type="text/css"></link>

<link rel="stylesheet" href="<%=basePath%>style/lib/common.css" type="text/css"></link>
<!--easyui-->
<link rel="stylesheet" type="text/css" href="<%=basePath%>style/lib/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>pages/common/common.css" />

<script type="text/javascript" src="<%=basePath%>style/lib/jquery/1.9.1/jquery.min.js"></script>

<script type="text/javascript" src="<%=basePath%>style/lib/jquery.easyui.min.js"></script>		
<script type="text/javascript" src="<%=basePath%>style/lib/easyui-lang-zh_CN.js"></script>

<!--_footer 作为公共模版分离出去-->
 
<script type="text/javascript" src="<%=basePath%>style/lib/jquery/1.9.1/jquery_form.js"></script> 
<script type="text/javascript" src="<%=basePath%>style/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>style/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>style/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
 <script type="text/javascript">
 $(function(){
 
 	//搜索框 初始化
 	$("#search_div").Huifold({
		titCell:".item h4",    //点击区域
		mainCell:".item div",  //执行区域
		type:"1",
		trigger:"click",
		className:"selected",
		speed:"normal",
	});
	//美化checkbox
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	function successMsg(msg){
		var success = layer.msg(msg, {
		  offset: 'rb',//右下角显示信息
		  area:['300px', '20px'] ,
		  anim: 5
		});
		layer.style(success, {
		background: 'green',
		height:'20px',
		top:'93%'
		}); 
	}
	
	function errorMsg(msg){
		var error = layer.msg(msg, {
		  offset: 'rb',//右下角显示信息
		  area:['300px', '20px'] ,
		  anim: 6
		});
		layer.style(error, {
		background: 'red',
		height:'20px',
		top:'93%'
		});
	}
 });
 
 </script>


<script type="text/javascript" src="<%=basePath%>style/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript" src="<%=basePath%>style/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<%-- <script type="text/javascript" src="<%=basePath%>style/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>  --%>
<script type="text/javascript" src="<%=basePath%>style/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="<%=basePath%>style/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="<%=basePath%>style/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="<%=basePath%>style/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="<%=basePath%>pages/common/base-dateFormat.js"></script>
<script type="text/javascript" src="<%=basePath%>pages/common/base-datatables.js"></script>
<script type="text/javascript" src="<%=basePath%>pages/common/base-form.js"></script>
<style type="text/css">

</style>
  <script type="text/javascript">
 var basePath = '<%= basePath%>';
 var type = '<%= type%>';
 var eventid = '<%= eventid%>';
 var proInskey = '<%= proInskey%>';
 //分页传递
var page_index = '${param.page_index}';
var page_size = '${param.page_size}';
if(!page_index) page_index = 1;
 function getPath(url) {
	return basePath + url;
 }
 
 var base = {
    datagrid:new BaseDataGrid(),
    form:new BaseForm()
 };
 
 </script>