<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>layout</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript" src="../js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css" href="../js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css" href="../css/default.css">
<script type="text/javascript" src="../js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript" src="../js/easyui/ext/jquery.cookie.js"></script>
</head>
<script type="text/javascript">
	$(function() {
		var index;
		$("#tb").datagrid({
			url: 'datagrid.json',
			columns:[[
				{field:'checkbox',checkbox:true},
				{field:'code',title:'ID',width:100,editor:{type:'numberbox',option:''}},
		        {field:'name',title:'姓名',width:100,editor:{type:'text',option:''}},
		        {field:'age',title:'年龄',width:100,align:'right',editor:{type:'numberbox',option:''}}
			]],
			striped: true,//斑马线
			rownumbers: true, //行号
			pagination: true, //分页
			toolbar:[
				{text:'添加',iconCls:'icon-add',handler:function() {
					$('#tb').datagrid('insertRow',{
						index: 0,	// 索引从0开始
						row: {}
					});
					$('#tb').datagrid('beginEdit',0);

				}},
				{text:'编辑',iconCls:'icon-edit',handler:function() {
					var rows = $('#tb').datagrid('getSelections');
					if(rows.length==1) {
						index = $('#tb').datagrid('getRowIndex',rows[0]);
						$('#tb').datagrid('beginEdit',index);
					}
				}},
				<shiro:hasPermission name="area">
				{text:'删除',iconCls:'icon-cancle',handler:function() {
					var rows = $('#tb').datagrid('getSelections');
					if(rows.length==1) {
						index = $('#tb').datagrid('getRowIndex',rows[0]);
						$('#tb').datagrid('deleteRow',index);
					}
				}},
				</shiro:hasPermission>
				{text:'保存',iconCls:'icon-save',handler:function() {
					$('#tb').datagrid('endEdit',index);
				}}
			],
			onAfterEdit:function(index,row,changes) {
				$.post("xxx.action",row)
			}
		})
	});
</script>

<body>
	<table id="tb">
	</table>
</body>
</html>