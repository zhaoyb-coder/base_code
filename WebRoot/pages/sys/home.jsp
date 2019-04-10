<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>后台管理系统</title>
<%@include file="/pages/common/common.jsp"%>  


<script type="text/javascript"	src="${pageContext.request.contextPath }/pages/sys/home.js"></script>

</head>
<body>
	<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top" style="/* background-color:#0574B8  */background: url(/mvn_demo/style/images/bg_top.png) ;height: 44px; ">
		<div class="container-fluid cl" style="border-bottom: 1px solid #073f8c">
			<a style="margin-left: 10px;" class="logo navbar-logo f-l mr-10 hidden-xs"
				href="/mvn_demo/pages/sys/home.jsp">BASE后台管理系统</a> <a
				class="logo navbar-logo-m f-l mr-10 visible-xs"></a> <span
				class="logo navbar-slogan f-l mr-10 hidden-xs">V1.0</span> <a
				aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs"
				href="javascript:;">&#xe667;</a>
			<nav class="nav navbar-nav">
			
			<!-- <!--  动态加载头部菜单 
			<ul class="cl" id="level1ul"></ul>-->

			
			</nav>
			<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
			<ul class="cl  " >
				<li  style="border-right:1px solid #073f8c; ">
				 <a style=" border-left:1px solid #073f8c ;color: #ffea7f;line-height:26px;font-family:微软雅黑;font-size:12" href="javascript:;" title="用户" onClick="myselfinfo()"><i   class="Hui-iconfont" style="font-size:22px;color:#5cd9ff">&#xe62c;</i>&nbsp;开发部：admin</a>
				</li>
				<li id="Hui-msg" style=" border-left: 1px solid #269ad4;border-right:1px solid #073f8c;"><a style="color: #5cd9ff" onClick="Hui_admin_tab(this)"  data-href="picture-list.html" data-title="消息列表" id="msg" title ="消息" href="javascript:void(0)"><span
						class="badge badge-danger">1</span><i class="Hui-iconfont"
						style="font-size:18px">&#xe68a;</i>
				</a></li>
				
				<li  id="Hui-msg" style="border-left: 1px solid #269ad4;"><a  href="javascript:;" onClick="logout()" title="退出" style="color: #5cd9ff;width: 93% ;border-right:1px solid #073f8c; margin-right: -15px; "><i style="font-size:18;"  class="Hui-iconfont Hui-iconfont-power" ></i></a></li>
				
			</ul>
			</nav>
		</div>
		<div class="container-fluid cl" style="background: url(/mvn_demo/style/images/bg_menu.png) ;"><!--  动态加载头部菜单 -->
			<nav class="nav navbar-nav"><ul class="cl border-li" id="level1ul"></ul></nav></div>
	</div>
	</header>
	
	<!-- 左侧菜单 动态载入-->
	<aside class="Hui-aside" style="background-color: /* #dfecf2*/  #d3ecf8"id="left_menu">
	
	</aside>
	
	
	<div class="dislpayArrow hidden-xs">
		<div style="background-color: #a1cfe5;width:7px;height:100%; ">
			<a class="pngfix" href="javascript:void(0);"
				onClick="displaynavbar(this)"></a>
		</div>
	</div>
	<section class="Hui-article-box" >
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs" style="margin-top: -1px;background: url(/mvn_demo/style/images/bg_tab.png) ">
		<div class="Hui-tabNav-wp" style=" ">
			<ul id="min_title_list" class="acrossTab cl" >
				<li class="active"><span title="欢迎页" data-href="welcome.html">欢迎页</span>
					<em></em>
				</li>
			</ul>
		</div>
		<div class="Hui-tabNav-more btn-group">
			<a id="js-tabNav-prev" class="btn radius btn-default size-S"
				href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i>
			</a><a id="js-tabNav-next" class="btn radius btn-default size-S"
				href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i>
			</a>
		</div>
	</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="welcome.html"></iframe>
		</div>
	</div>
	</section>

	<div class="contextMenu" id="Huiadminmenu">
		<ul>
			<li id="closethis">关闭当前</li>
			<li id="closeall">关闭全部</li>
		</ul>
	</div>

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript">
		$(function() {
			 $("#min_title_list li").contextMenu('Huiadminmenu', {
				bindings: {
					'closethis': function(t) {
						console.log("11111");
						if(t.find("i")){
							t.find("i").trigger("click");
						}		
					},
					'closeall': function(t) {
						alert('Trigger was '+t.id+'\nAction was Email');
					},
				}
			});

			$("body").Huitab({
				tabBar : ".navbar-wrapper .navbar-levelone",
				tabCon : ".Hui-aside .menu_dropdown",
				className : "current",
				index : 0,
			}); 
			/* $("#msg").on("click",function(){
				Hui_admin_tab($("#msg"));
			}); */
		});
		/*个人信息*/
		function myselfinfo() {
			layer.open({
				type : 1,
				area : [ '300px', '200px' ],
				fix : false, //不固定
				maxmin : true,
				shade : 0.4,
				title : '查看信息',
				content : '<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;管理员信息</div>'
			});
		}

		/*资讯-添加*/
		function article_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*图片-添加*/
		function picture_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*产品-添加*/
		function product_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*用户-添加*/
		function member_add(title, url, w, h) {
			layer_show(title, url, w, h);
		}

		//退出登录
		function logout() {
			$.ajax({
				type : "POST",
				url : getPath("gz/login/loginAction/logout"),
				dataType : "json",
				success : function(data) {
					//跳转到登录页
					window.location.href = "pages/sys/login.jsp";
				}
			});
		}
	</script>

</body>
</html>