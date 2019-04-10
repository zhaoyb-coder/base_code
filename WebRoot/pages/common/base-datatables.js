var BaseDataGrid = function(opt){
	this.option = opt;
	this.dgPageSize = 30;  //表格的分页大小
	this.dgPageList = [5,10,15,20,25,30,50,100];  //可以设置每页记录条数的列表  
	this.dgPageConfig = {  
			pageSize: this.dgPageSize,//每页显示的记录条数，默认为30  
	        pageList: this.dgPageList,//可以设置每页记录条数的列表  
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页    共 {pages} 页',  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'  
	    };
	this.id = "";  //id
	this.type = "base";  //表格类型  默认基础表格
	this.isSelectAll = false;  //复选框默认不全选
};
BaseDataGrid.prototype = {
	constructor:BaseDataGrid,
	
	//基础列表
	getDataGrid:function(options){
		this.id = options.id;
		if(options.type)this.type = options.type;
		if(options.pageSize)this.dgPageSize = options.pageSize;
		if(page_size)this.dgPageSize = page_size;
		var _this = this;
		var pageNumber = page_index?page_index:1;
		return { 
			loadMsg:options.loadMsg?options.loadMsg:'正在查询,请稍后...',
	        title:options.title,  
	        width: '100%',  
	        height: 'auto',  
	        nowrap: true,  
	        striped: true,  
	        border: true,  
	        checkOnSelect:false, //选中时，复选框是否进行勾选
	        collapsible:false,//是否可折叠的 
	        fit:typeof(options.fit) == 'boolean'?options.fit:true,
	        fitColumns:typeof(options.fitColumns) == 'boolean'?options.fitColumns:false,//自动大小  
	        showFooter:typeof(options.showFooter) == 'boolean'?options.showFooter:false,//是否显示页脚
	        scrollbarSize:0,
	        url:options.url,  
	        remoteSort:false,   
	        singleSelect:options.singleSelect?options.singleSelect:false,//是否单选  
	        pagination:typeof(options.pagination) == 'boolean'?options.pagination:true,//分页控件  
	        rownumbers:typeof(options.rownumbers) == 'boolean'?options.rownumbers:false,//行号  
	        rowStyler:function(rowIndex,rowData){
	        	if((rowIndex%2)==0){
	        		return 'background-color:#EDF7FA;';
	        	}else{
	        		return 'background-color:#DFECF2;';
	        	}
	        },
	        onLoadSuccess:function(data){
	        	_this.format_ckbox(data, options);
	        },  //每次刷新，或者点击下一页，都会调用
	        columns:options.columns,
	        frozenColumns:options.frozenColumns?options.frozenColumns:[[{field:'ck',checkbox:true}]],
	        pageNumber:parseInt(pageNumber),
	        pageSize: this.dgPageSize,//每页显示的记录条数，默认为30
			pageList: this.dgPageList//可以设置每页记录条数的列表 
	    };
	},
	getDataGridType:function() {  //表格类型
		return this.type;
	},
	//获取选择行数据
	getSelectRows:function(dgId){
		var dataGrid = (dgId ? dgId : "dataGrid");
		var rows = $('#' + dataGrid).datagrid('getChecked');
		return this.filterSelectAll(rows, dgId);
	},
	//清空表格数据
	clearDataGrid:function(dgId){
		var dataGrid = (dgId ? dgId : "dataGrid");
		$('#' + dataGrid).datagrid('loadData',{total:0,rows:[]});
	},
	//获取选择行的eventid数据,也可以传其他字段名称
	getSelectField:function(dgId, fieldName){
		var rows = this.getSelectRows(dgId);
		var field = (fieldName ? fieldName : "id");
		var ids = '';
	    for (var i = 0; i < rows.length; i++) {
	    	if(i != 0) ids += ",";
	    	ids += rows[i][field];
	    }
	    return ids;
	},
	selectAll:function(){  //供外边按钮使用
		if(this.isSelectAll){
			this.isSelectAll = false;
			$("#" + this.id).datagrid('clearChecked');
		}else{
			this.isSelectAll = true;
			$("#" + this.id).datagrid('checkAll');
		}
	},
	filterSelectAll:function(rows, dgId){  //过滤掉那些，被隐藏或禁止的checkbox(触发全选以后，默认将执行该方法)
		var _this = this;
		if(!_this.type || _this.type == 'base'){
			return _this.filterSelectAll_base(rows, dgId);
		}
	},
	filterSelectAll_base:function(rows, dataGridId){
		var arrs = new Array();
		var fn = {
			fn0:function(rowData, i){arrs.push(rowData);},
			
		};
		for (var i = 0; i < rows.length; i++) {
			BaseDataGridHelper.baselb(rows[i], i, fn);
		}
		return arrs;
	},
	//头部分页条
	setTopPager:function(dataGridId){
		var _this = this;
		var id = '#' + (dataGridId?dataGridId:'dataGrid');
		if(!$(id).datagrid('getPager').data("pagination")){
			return;
		}
		var options = $(id).datagrid('getPager').data("pagination").options;  
		var total = options.total;
		var pageSize = options.pageSize;
		var pageNumber = options.pageNumber;
		$("#top_pagination").pagination(getPageConfig({
			total:total,
			pageSize:pageSize,
			pageNumber:pageNumber
		}));
		//函数式获取    
		function getPageConfig(options){
			return {  
				total: options.total,
				pageSize: options.pageSize,//每页显示的记录条数
				pageNumber: options.pageNumber,  //当前显示页数
		        pageList: _this.dgPageList,//可以设置每页记录条数的列表  
		        beforePageText: '第',//页数文本框前显示的汉字  
		        afterPageText: '页    共 {pages} 页',  
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		        onSelectPage: function(pageNumber, pageSize){
					$(id).datagrid('getPager').pagination('refresh',{
						pageSize:pageSize,
						pageNumber:pageNumber
					}).pagination('select',pageNumber);
				}
		    };
		}
	},
	format_ckbox:function(data,opt){
		var _this = this;
		if(!opt.type || opt.type == 'base'){
			_this.format_ckbox_base(data, opt.id);
		}
	},
	//对checkbox渲染，维护列表
	format_ckbox_base:function(data, dataGridId) {
		if (data.rows.length > 0){
			var rowData;
			var $ckbox = $('.datagrid-cell-check input:checkbox');
			var fn = {
				fn0:function(rowData, i){},
				fn1:function(rowData, i){$ckbox.eq(i).css('display','none').attr("disabled", "disabled");},
				fn2:function(rowData, i){$ckbox.eq(i).css('display','none').attr("disabled", "disabled");},
				fn3:function(rowData, i){}
			};
			for (var i = 0; i < data.rows.length; i++) {
				rowData = data.rows[i];
				BaseDataGridHelper.baselb(rowData, i, fn);
			}
		}
		this.setTopPager(dataGridId);  //设置头部分页
	},
	//序号
	format_seq:function(val,row,index,dataGridId) {
		var id = "#dataGrid";
		if(dataGridId)id = "#" + dataGridId;
		if(!$(id).datagrid('getPager').data("pagination")){
			return index+1;
		}
		var options = $(id).datagrid('getPager').data("pagination").options; 
	    var currentPage = options.pageNumber;
	    var pageSize = options.pageSize;
	    return (pageSize * (currentPage -1))+(index+1);
	},
	//不分页的序号格式化
	format_nopage_seq:function(val,row,index) {
		return index+1;
	},
	//格式化太长的字符
	format_long_char:function(value,row,index){  
		if(value == null || value == 'null')value = '';
		return '<span title=' + value + '>' + value + '</span>';
	}, 
	//格式化日期,yyyy-mm-dd
	format_date_ymd:function(value,row,index) {
		if(!value)return "";
		value = (value + "").replace(/-/g,'/');
		return new Date(value).format('yyyy-MM-dd');
	},
	//维护列表中的,新增编辑删除操作
	format_base:function(value,rowData,rowIndex) {
		var fn = {
			fn0:function(rowData, rowIndex){
				return '<div class="format-operate"><a href="javascript:;" onclick="member_edit(\'编辑用户\',\''+rowData.id+'\',\'\',\'510\')">编辑</a>&nbsp;' +
				'<a href="javascript:;" onclick="member_show(\'查看用户\',\''+rowData.id+'\',\'\',\'510\')">查看</a>&nbsp;' +
				'<a href="javascript:;" onclick="member_del(this,\''+rowData.id+'\')">删除</a>&nbsp;</div>';
			}
		};
		return BaseDataGridHelper.baselb(rowData, rowIndex, fn);
	},
	//合并字段
	mergeCellsByField:function(tableID, mainField, subFieldStr) {
		var $table = $("#" + tableID);
	    var rowCount = $table.datagrid("getRows").length;
	    var tmpA = 1;
	    var tmpB = 0;
	    var preTxt = "";
	    var curTxt = "";
	    for (var i = 0; i <= rowCount; i++) {
	        if (i == rowCount) {
	            curTxt = "";
	        } else {
	            curTxt = $table.datagrid("getRows")[i][mainField];
	        }
	        if (preTxt == curTxt) {
	            tmpA += 1;
	        } else {
	            tmpB += tmpA;
	            $table.datagrid("mergeCells", {
	                index: i - tmpA,
	                field: mainField,  // 合并主字段
	                rowspan: tmpA,
	                colspan: null
	            });
	            //合并从字段
	        	if(subFieldStr){
	        		var subFieldList = subFieldStr.split(",");
	        		for(var f = 0; subFieldList && f < subFieldList.length; f++){
	        			$table.datagrid("mergeCells", {
	        				index: i - tmpA,
	        				field: subFieldList[f],  // 合并从字段
	        				rowspan: tmpA,
	        				colspan: null
	        			});
	        		}
	        	}
	            tmpA = 1;
	        }
	        preTxt = curTxt;
	    }
	}
}
var BaseDataGridHelper = {
		baselb:function(rowData, rowIndex, baseFn){
			return baseFn.fn0(rowData, rowIndex);
		},
}

var CONSTANT = {
		// datatables常量
		DATA_TABLES : {
			DEFAULT_OPTION : { // DataTables初始化选项
				LANGUAGE : {
					sProcessing : "处理中...",
					sLengthMenu : "显示 _MENU_ 项结果",
					sZeroRecords : "没有匹配结果",
					sInfo : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
					sInfoEmpty : "显示第 0 至 0 项结果，共 0 项",
					sInfoFiltered : "(由 _MAX_ 项结果过滤)",
					sInfoPostFix : "",
					sSearch : "搜索:",
					searchPlaceholder : "关键字搜索",
					sUrl : "",
					sEmptyTable : "表中数据为空",
					sLoadingRecords : "载入中...",
					sInfoThousands : ",",
					oPaginate : {
						sFirst : "首页",
						sPrevious : "上页",
						sNext : "下页",
						sLast : "末页"
					},
					oAria : {
						sSortAscending : ": 以升序排列此列",
						sSortDescending : ": 以降序排列此列"
					}
				},
				// 禁用自动调整列宽
				autoWidth : false,
				// 为奇偶行加上样式，兼容不支持CSS伪类的场合
				stripeClasses : [ "odd", "even" ],
				// 取消默认排序查询,否则复选框一列会出现小箭头
				order : [],
				// 隐藏加载提示,自行处理
				processing : false,
				// 启用服务器端分页
				serverSide : true,
				// 禁用原生搜索
				searching : false
			},
			COLUMN : {
				// 复选框单元格
				CHECKBOX : {
					className: "td-checkbox",
					orderable : false,
					bSortable : false,
					data : "id",
					width : '30px',
					render : function(data, type, row, meta) {
						var content = '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">';
						content += '	<input type="checkbox" class="group-checkable" value="' + data + '" />';
						content += '	<span></span>';
						content += '</label>';
						return content;
					}
				}
			},
			// 回调
			CALLBACKS : {
				// 表格绘制前的回调函数
				PREDRAWCALLBACK : function(settings) {
					if (settings.oInit.scrollX == '100%') {
						// 给表格添加css类，处理scrollX : true出现边框问题
						$(settings.nTableWrapper).addClass('dataTables_DTS');
					}
				},
				INITCOMPLETE : function(settings, json) {
					if (settings.oInit.scrollX == '100%' && $(settings.nTable).parent().innerWidth() - $(settings.nTable).outerWidth() > 5) {
						$(settings.nScrollHead).children().width('100%');
						$(settings.nTHead).parent().width('100%');
						$(settings.nTable).width('100%');
					}
				},
				// 表格每次重绘回调函数
				DRAWCALLBACK : function(settings) {
					if ($(settings.aoHeader[0][0].cell).find(':checkbox').length > 0) {
						// 取消全选
						$(settings.aoHeader[0][0].cell).find(':checkbox').prop('checked', false);
					}
					// 高亮显示当前行
					$(settings.nTable).find("tbody tr").click(function(e) {
						$(e.target).parents('table').find('tr').removeClass('warning');
						$(e.target).parents('tr').addClass('warning');
					});
				}
			},
			// 常用render可以抽取出来，如日期时间、头像等
			RENDER : {
				ELLIPSIS : function(data, type, row, meta) {
					data = data || "";
					return '<span title="' + data + '">' + data + '</span>';
				}
			}
			
		}


 
};
 
if ($.fn.dataTable) {
	$.extend($.fn.dataTable.defaults, {
		processing : true,
		order: [],
		paging : true,
		searching: true,
		language : CONSTANT.DATA_TABLES.DEFAULT_OPTION.LANGUAGE,
		preDrawCallback : CONSTANT.DATA_TABLES.CALLBACKS.PREDRAWCALLBACK,
		initComplete : CONSTANT.DATA_TABLES.CALLBACKS.INITCOMPLETE,
		drawCallback : CONSTANT.DATA_TABLES.CALLBACKS.DRAWCALLBACK
	});
}
