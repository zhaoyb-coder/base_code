$(document).ready(init);


function init(){
	$('#menuTree').tree({    
	    url: getPath('gz/right/rightAction/query_right'),
	    onClick: function(node){
			//alert(node.url);  // 在用户点击的时候提示
			$("#id").val(node.id);
			$("#pid").val(node.pid);
			$("#level").val(node.level);
			$("#name").val(node.text);
			$("#icon").val(node.icon);
			
			//只有三级菜单有url属性
			if(node.level == '3'){
				$("#url").val(node.url);
				$("#addChild").hide();
				$("#url").removeAttr("readonly");
			}else{
				$("#addChild").show();
				$("#url").val('');
				$("#url").attr("readonly","readonly");
			}
		}
	});
}


function addChild(title,url,w,h){
	
	var lev = $("#level").val();
	var pid = $("#id").val();
	if(lev == null || lev ==""){
		layer.msg('请先选择一个需要操作的按钮',{icon: 5,time:1500});
		return;
	}
	lev=parseInt(lev)+1;
	url = url+'type='+lev+'&_menu='+pid;
	layer_show(title,url,w,h);
}
function addMenu(title,url,w,h){
	var lev = $("#level").val();
	var pid = $("#pid").val();
	if(lev == null || lev ==""){
		layer.msg('请先选择一个需要操作的按钮',{icon: 5,time:1500});
		return;
	}
	url = url+'type='+lev+'&_menu='+pid;
	layer_show(title,url,w,h);
}
 