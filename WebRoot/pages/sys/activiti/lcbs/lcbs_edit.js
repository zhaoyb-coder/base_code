$(document).ready(init);



//检验文件上传类型 
$(document).ready(function() {
	$('input[type="file"]').change(function(){
		checkEXT($(this));
	});
	
});

function checkEXT(element){
    var file = element;
	var ext = file.val().substr(file.val().lastIndexOf(".")).toLowerCase();
	if(ext !=".zip" && ext !=".rar"){
		layer.msg('只能部署zip文件',{icon: 5,time:1000});
		file.after(file.clone());  
		file.remove();
		$('input[type="file"]').change(function(){
			checkEXT($(this));
		});
	}
}

function init(){
	//初始化编辑页面
	if(type == 'edit'){
		queryById(eventid);
		//改变表单提交url
		$("#form-member-add").attr("action",getPath('gz/sys/user/userAction/update'));
	}else if(type == 'add'){
		$("#form-member-add").attr("action",getPath('gz/sys/activiti/lcbsAction/addDeploy'));
	}else{
		//隐藏按钮
		$("#submit").hide();
		$("#uploadDiv").hide();
		$("#downloadDiv").show();
		
		queryById(eventid);
	}
	
	//初始化表单验证
	$("#form-member-add").validate(base.form.validateForm({
		"rules":{
			uploadFile:{
				required:true
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