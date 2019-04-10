$(document).ready(init);

function init(){
	
	if(type == 'edit' || type == 'view'){
		queryById(eventid);
		initDrowForm(eventid);//初始化页面表单
	}else{
		initDrowForm();//初始化页面表单
	}
	
	
	//初始化表单验证
	$("#form-member-add").validate(base.form.validateForm({
		"rules":{
			login_name:{
				required:true,
				minlength:2,
				maxlength:16
			},
			name:{
				required:true,
			},
			password:{
				required:true,
			},
			sex:{
				required:true,
			},
			phone:{
				required:false,
				isMobile:true,
			},
			uploadFile:{
				required:false,
			},
		},
	}))
}

function initDrowForm(roleid){
	$.ajax({
		type: 'POST',
		url: getPath('/gz/right/rightAction/query_right?roleId='+roleid),
		dataType: 'json',
		success: function(datas){
			//按钮标签 总div
			var menuDiv = $("div .menu-div");
			var dldiv='';
			for(var i = 0;i<datas.length;i++){
				
				//循环一次 一个dl
				var dl = '<dl class="permission-list">';
				var maps = datas[i];
				
				var dt ='<dt><label><input type="checkbox" value="'+maps['id']+'" name="user-Character-0" id="user-Character-0">'+maps['text']+'</label></dt>';
				var dd1 = '<dd>';
				var children1 = maps['children'];
				for(var j = 0;j<children1.length;j++){
					var maps2 = children1[j];
					dd1 = dd1+'<dl class="cl permission-list2"><dt><label><input type="checkbox" value="'+maps2['id']+'" name="user-Character-0-0" id="user-Character-0-0">'+maps2['text']+'</label></dt>';
					var children2 = maps2['children'];
					
					var dd3 = '<dd>';
					for(var k = 0;k<children2.length;k++){
						var maps3 = children2[k];
						dd3 = dd3+'<label class=""><input type="checkbox" value="'+maps3['id']+'" name="user-Character-0-0-0" id="user-Character-0-0-0">'+maps3['text']+'</label>';
					}
					dd1=dd1+dd3+'</dd></dl>';
				}
				dd1=dd1+'</dd>';
				dt=dt+dd1;
				dl=dl+dt+'</dl>';
				dldiv=dldiv+dl;
			}
			menuDiv.html(dldiv);
			checkInitBind();
			if(roleid){
				queryMenu(roleid);
			}
		},
		error:function(data) {
			layer.msg('初始化失败',{icon: 5,time:1000});
		},
	});	
}


function queryMenu(roleid){
	$.ajax({
		type: 'POST',
		url: getPath('/gz/sys/role/roleAction/queryMenu?roleId='+roleid),
		dataType: 'json',
		success: function(datas){
			for(var i = 0;i<datas.length;i++){
				var maps = datas[i];
				var checkboxs = $("input[type='checkbox']");;
			    for(var j = 0; j < checkboxs.length; j++){
			    	if(checkboxs[j].value == maps['resource_id']){
			    		checkboxs[j].checked = true;
			    	}
			    }
			}
		},
		error:function(data) {
			layer.msg('初始化失败',{icon: 5,time:1000});
		},
	});	
}

function queryById(id){
	$.ajax({
		type: 'POST',
		url: getPath('gz/sys/user/userAction/queryById?userid='+id),
		dataType: 'json',
		success: function(datas){
			if(!datas)return;
			$("#form-member-add input").each(function(index, domEle){
				var $ele = $(domEle);
				var name = $ele.attr("name");
				var value = datas[name];
				var types = $ele.attr("type");
				if(value == null || value == undefined)return;
				
				$ele.val(value);
				if(type == 'view'){
					$ele.attr("readonly","readonly");
				}
				/*switch (type) {
					case "text":
						$ele.val(value);
						break;
					//其余格式再添加...
					default:
						break;
				}*/
		})},
		error:function(data) {
			layer.msg('初始化失败',{icon: 5,time:1000});
		},
	});		
}

function getIdsChecked(){
	var id = $("input[type='checkbox']");;
    var value = [];
    for(var i = 0; i < id.length; i++){
     if(id[i].checked)
     value.push(id[i].value);
    }
    alert(value);
}

//checkbox绑定事件
function checkInitBind(){
	$(".permission-list dt input:checkbox").click(function(){
		$(this).closest("dl").find("dd input:checkbox").prop("checked",$(this).prop("checked"));
	});
	$(".permission-list2 dt input:checkbox").click(function(){
		var l1 =$(this).parent().parent().parent().parent().find(".permission-list2 dt input:checked").length;
		if($(this).prop("checked")){
			$(this).closest("dl").find("dt input:checkbox").prop("checked",true);
			$(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked",true);
		}else{
			if(l1==0){
				$(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked",false);
			}
		}
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
}