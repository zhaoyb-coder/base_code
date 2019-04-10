$(function(){
	
	$.ajax({
		type: 'POST',
		url: getPath('gz/right/rightAction/query_right'),
		dataType: 'json',
		async: false,
		success: function(datas){
			//按钮标签 level1 总div
			var left_menu = $("#left_menu");
			var level1ul = $("#level1ul");
			
			var li1='';
			var left_ ='';
			for(var i = 0;i<datas.length;i++){
				var maps = datas[i];
				//循环一次 一个li
				var div_left='<div class="menu_dropdown bk_2" style="background-color: #cee6f2">';
				li1 = li1+'<li class="navbar-levelone current"><a href="javascript:;"><i class="Hui-iconfont">&#xe'+maps['icon']+'&nbsp;</i>'+maps['name']+'</a></li>';

				var children1 = maps['children'];
				for(var j = 0;j<children1.length;j++){
					var maps2 = children1[j];
					//一次循环一个dl
					div_left = div_left+'<dl id="menu-picture"><dt><i class="Hui-iconfont">&#xe'+maps2['icon']+'&nbsp;</i> '+maps2['name']+'<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>'
					
					
					var children2 = maps2['children'];
					
					var dd3 = '<dd><ul>';
					for(var k = 0;k<children2.length;k++){
						var maps3 = children2[k];
						dd3 = dd3+'<li><a data-href="'+getPath(maps3['url'])+'" data-title="'+maps3['name']+'" href="javascript:void(0)"><i class="Hui-iconfont">&#xe'+maps3['icon']+'&nbsp;</i>'+maps3['name']+'</a></li>';
					}
					div_left=div_left+ dd3+'</ul></dd></dl>';
				}
				div_left =div_left+'</div>';
				left_=left_+div_left;
			}
			//填充level1菜单
			level1ul.html(li1);
			//填充左侧菜单
			left_menu.html(left_);
			
			//为动态菜单初始化事件
			$(".Hui-aside").Huifold({
				titCell:'.menu_dropdown dl dt',
				mainCell:'.menu_dropdown dl dd',
			});
			pop_init("公告信息","<ul><li>这是一个公告</li></ul>");
			pop_show();
		},
		error:function(data) {
			layer.msg('初始化失败',{icon: 5,time:1000});
		},
	});	
	
	
	
})

function pop_init(title,content){
	//取当前浏览器窗口大小
	var windowWidth=$(document).width();
	var windowHeight=$(document).height();
	//弹窗的大小
	var weight=320;
	var height=240;
	$("body").append(
	"<div id='pop_div'style='display:none;position:fixed; _position:absolute;z-index:1000;background:#27a1ce;bottom:0; right:0; _right:17px; height:55px; background:#ebf1f6; width:300px; height:200px;border: 1px solid #8db6d9;'>"+
		"<div style='line-height:28px;background:#27a1ce;border-bottom:2px solid #e0e0e0;font-size:10px;padding:0 0 0 10px;color:#fff'>" +
			"<div style='float:left;'><b  >"+title+"</b></div><div style='float:right;cursor:pointer;'><b style='margin:0 2' onclick='pop_close()' ;color:#ffffff>关闭</b></div>" +
			"<div style='clear:both'></div>"+
		"</div>" +
		"<div id='content' style='font-size:10px;'>" +
			 content+
		"</div>"+
	"</div>"
	);
}
function pop_show(){
	$('#pop_div').fadeIn(2000);
}
function pop_close(){
	$('#pop_div').fadeOut(400);
}