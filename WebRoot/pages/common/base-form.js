var BaseForm = function(opt){
	this.option = opt;
};
BaseForm.prototype = {
		constructor:BaseForm,
		
		//删除请求
		del:function(options){
		layer.confirm('确认删除？',function(index){
			$.ajax({
				type: 'POST',
				url: options.url,
				dataType: 'json',
				success: function(data){
					//$(obj).parent().parent().remove();
					layer.msg('删除成功',{icon: 6,time:1000});
					
					//重新加载页面数据 
					$('#dataGrid').datagrid('reload');
				},
				error:function(data) {
					console.log(data.msg);
					layer.msg('删除失败，请询问管理员',{icon: 5,time:1000});
				},
			});		
		});
		},

		//表单校验
        validateForm:function(options){
        	return{
        	rules:options.rules,
        	onkeyup:false,
    		focusCleanup:true,
    		success:"valid",
    		submitHandler:function(form){
    			//加载动画
    			var load = layer.load();
    			//禁用提交按钮
    			$("#submit").attr("disabled","disabled");
    			
    			$(form).ajaxSubmit(function(result){
    	            	layer.close(load);
     					if(result === 'success'){
     				    	var index = parent.layer.getFrameIndex(window.name);
     						//parent.$('.i-refresh').click(); 
     				    	parent.$('#dataGrid').datagrid('reload');//刷新父页面
     						parent.layer.close(index);      //关闭子页面
     						parent.successMsg("保存成功！");
     				    }else{
     				    	errorMsg("保存失败！");
     				    }
    	        });
    	   }}	
        }
		
		
		
}