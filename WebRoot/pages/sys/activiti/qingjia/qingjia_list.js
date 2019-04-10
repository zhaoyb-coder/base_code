$(document).ready(init);

var params = "";
//普通列
var columns =  [
   				[
   				    {field:'start_time',title:'开始时间', width:100, align:'center', sortable:false,formatter:base.datagrid.format_date_ymd},
   				    {field:'task_time',title:'请假时长', width:200, align:'center', sortable:false},
   				    {field:'task_resonse',title:'请假原因', width:200, align:'center', sortable:false},
   				    {field:'task_type',title:'请假类型', width:200, align:'center', sortable:false},
   				    {field:'task_state',title:'任务状态', width:200, align:'center', sortable:false},
   				]
            ];            
//冻结列
var frozenColumns = [[
                    {field:'ck',checkbox:true}, 
                    {field:'seq',title:'序号', width:50, align:'center', sortable:false,formatter:base.datagrid.format_seq},
   				 	{field:'operate',title:'操作列', width:120, align:'center', sortable:false,formatter:base.datagrid.format_base},
   				    {field:'userId',title:'请假人', width:100, align:'center', sortable:false},
   				 	]
                 ];

             

function init(){
	/* setTimeout(function(){
	    Push();
	//  alert("setTimeout called");
	},200);*/

	/*setInterval(function(){
		、、$('#pop_div').fadeOut(400);
	    //alert("setInterval called");
	},3000); */
	pop_init("公告信息","<ul><li>这是一个公告</li></ul>");
	
	
	$('#pop_div').fadeIn(2000);
	
	
	queryDataTable(params);  //查询表格数据
}
function queryDataTable(param){
	$('#dataGrid').datagrid(base.datagrid.getDataGrid({ 
		id:'dataGrid',
        title:'请假列表',
        url:getPath('gz/sys/activiti/qjAction/qj_list?1'+param),
        columns:columns,
        frozenColumns:frozenColumns
    }));
}
//添加
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}
//编辑
function member_edit(title,id,w,h){
	layer_show(title,'user_edit.jsp?type=view&eventid='+id,w,h);
} 
//查看
function member_show(title,id,w,h){
	layer_show(title,'user_edit.jsp?type=edit&eventid='+id,w,h);
}
//删除
function member_del(obj,id){base.form.del({
	"url":getPath('gz/sys/user/userAction/user_del?userid='+id),
})}	

/* 批量删除 */
$('#del_button').click(function() {
	var eventids = base.datagrid.getSelectField();
	if(eventids){
		member_del(null,eventids);
	}else{
		layer.msg('请选择需要删除的数据',{icon: 5,time:1500});
        return;
	}
})

function search(){
	var d = {};
	var t = $('#search_form').serializeArray();
 
	$.each(t, function() {
		d[this.name] = this.value;
	});
	params = "&strquery=" +encodeURIComponent(encodeURIComponent(JSON.stringify(d)));
	queryDataTable(params);
}





function pop_init(title,content) {
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
function pop_close(){
	$('#pop_div').fadeOut(400);
}
function pop_show(){
	$('#pop_div').fadeIn(2000);
}

 