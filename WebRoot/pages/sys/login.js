$(function(){
	
	$("#form-member-login").validate({
		rules:{
			login_name:{
				required:true,
				//minlength:2,
				//maxlength:16
			},
			password:{
				required:true,
			},
			verity:{
				required:true,
			},
			/*email:{
				required:true,
				email:true,
				isMobile:true,
			},
			uploadfile:{
				required:true,
			},*/
			
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			var index = layer.load();
			$(form).ajaxSubmit(function(data){
				layer.close(index);
			    if(data === 'success'){
			    	//跳转到登录页面
			    	window.location.href=getPath("pages/sys/home.jsp");
			    }else{
			    	alert(data);
			    }
			});
		}
	});
});

function changeImg() {        
    var imgSrc = $("#imgObj");    
    var src = imgSrc.attr("src");        
    imgSrc.attr("src", chgUrl(src));
}

//时间戳
// 为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
function chgUrl(url) {
    var timestamp = (new Date()).valueOf();
    url = url.substring(0, 38);
    if ((url.indexOf("&") >= 0)) {
        url = url + "×tamp=" + timestamp;
    } else {
        url = url + "?timestamp=" + timestamp;
    }
    return url;
}