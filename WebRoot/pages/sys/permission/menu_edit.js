$(document).ready(init);

function init(){
	alert(_menu);
	if(type == '3'){
		$("#level").val("3");
		$("#url").removeAttr("readonly");
	}else{
		$("#url").val('');
		$("#level").attr("value",type);
		$("#url").attr("readonly","readonly");
	}
	if(_menu){
		$("#pid").val(_menu);
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