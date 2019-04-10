$(document).ready(init);

var params = "";
//普通列
var columns =  [
   				[
   				    {field:'name',title:'姓名', width:100, align:'center', sortable:false,formatter:base.datagrid.format_long_char},
   				    {field:'sex',title:'性别', width:100, align:'center', sortable:false},
   				    {field:'phone',title:'手机', width:100, align:'center', sortable:false},
   				    {field:'create_time',title:'创建时间', width:100, align:'center', sortable:false,formatter:base.datagrid.format_date_ymd},
   				    {field:'user_type',title:'类型', width:100, align:'center', sortable:false},
   				    {field:'user_type',title:'类型', width:100, align:'center', sortable:false},
   				    {field:'user_type',title:'类型', width:100, align:'center', sortable:false},
   				    {field:'user_type',title:'类型', width:100, align:'center', sortable:false},
   				    {field:'user_type',title:'类型', width:100, align:'center', sortable:false},
   				]
            ];            
//冻结列
var frozenColumns = [[
                    {field:'ck',checkbox:true}, 
                    {field:'seq',title:'序号', width:50, align:'center', sortable:false,formatter:base.datagrid.format_seq},
   				 	{field:'operate',title:'操作列', width:120, align:'center', sortable:false,formatter:base.datagrid.format_base},
   				    {field:'login_name',title:'登录名', width:100, align:'center', sortable:false},
   				 	]
                 ];   

             

function init(){
	queryDataTable(params);  //查询表格数据
}
function queryDataTable(param){
	$('#dataGrid').datagrid(base.datagrid.getDataGrid({ 
		id:'dataGrid',
        title:'用户信息列表',
        url:getPath('gz/sys/user/userAction/user_list?1'+param),
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
 