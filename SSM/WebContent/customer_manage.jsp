<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>客户管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- 导入easyui的资源文件 -->
<script type="text/javascript" src="easyui/jquery.min.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<link id="themeLink" rel="stylesheet" type="text/css"
	href="easyui/themes/default/easyui.css">
</head>

<body>

	<table id="list"></table>

	<div id="tb">
		<a id="addBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">添加</a> 
		<a id="editBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a> 
		<a id="deleteBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	</div>

	<div id="win" class="easyui-window" title="客户数据编辑"
		style="width: 600px; height: 400px"
		data-options="iconCls:'icon-save',modal:true,closed:true">
		<form id="editForm" action="" method="post">
			<%--提供id隐藏域 --%>
			<input type="hidden" name="id">
			客户姓名:<input type="text" name="name" class="easyui-validatebox" data-options="required:true" /><br /> 
				客户性别: <input type="radio" name="gender" value="男" />男 <input type="radio" name="gender" value="女" />女 <br /> 
				客户手机:<input type="text" name="telephone" class="easyui-validatebox" data-options="required:true" /><br />
				客户住址：<input type="text" name="address" class="easyui-validatebox" data-options="required:true" /><br /> 
		<a id="saveBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true">保存</a>
		</form>
	</div>

	<script type="text/javascript">
  		$(function(){
  			$("#list").datagrid({
  				url:"customer/listByPage.action",
  				//columns 填充列数据
  				//field:后台列数据的属性
  				//title:列标题
  				columns:[[
  					{
  						field:"id",
  						title:"客户编号",
  						width:100,
  						checkbox:true
  					},
  					{
  						field:"name",
  						title:"客户姓名",
  						width:200
  					},
  					{
  						field:"gender",
  						title:"客户性别",
  						width:200
  					},
  					{
  						field:"telephone",
  						title:"客户手机",
  						width:200
  					},
  					{
  						field:"address",
  						title:"客户地址",
  						width:200
  					}
  				]],
  				pagination:true,
  				//工具条   在datagrid中实现toolbar的绑定
  				toolbar:"#tb"
  			});	
  		
  		
  		
  		//打开编辑窗口
  		$("#addBtn").click(function(){
  			//打开一个新的窗口前，清空表单数据
  			$("#editForm").form("clear");  			
  			//调用window窗口的open方法
  			$('#win').window('open');
  		});
  		
  	//保存数据
  	$('#saveBtn').click(function(){
  		$('#editForm').form("submit",{
  			//url:提交后台的地址
  			url:"customer/save.action",
  			//onsubmit:表单提交前的回调函数，true:提交表单  false：不提交表单
  			onSubmit:function(){
  				//判断表单的验证是否都通过了   否则验证不通过，也可以实现数据的提交
  				return $("#editForm").form("validate");
  				
  			},
  			//提交到服务器执行完毕之后的函数
  			success:function(data){   //data:服务器返回的数据，类型字符串类
  				//要求Controller返回的数据格式
  				//成功:{success:true}  失败:{success:false,msg:错误信息}
  				
  			
  			//1、把data字符串类型转换为对象类型  eval语法
  			data = eval("(" +data+ ")");
  				
  			if(data.success){
  				//关闭保存窗口
  				$('#win').window('close');
  				//刷新dtagrid
  				$("#list").datagrid("reload");
  				$.messager.alert("提示","保存成功","info");
  			}else{
  				$.messager.alert("提示","保存失败:" +data.msg,"info");
  			}
  				
  			}
  		})	
  	})
  	
  	
  	$("#editBtn").click(function(){
  		//判断只能选择一行数据
  		var rows = $("#list").datagrid("getSelections");
  		if(rows.length != 1){
  			$.messager.alert("提示","修改操作只能选择一行","warning");
  			return;
  		}
  		
  		//表单回显
  		$("#editForm").form("load","customer/findById.action?id="+rows[0].id);
  		
  		
  		$("#win").window("open");
  	});
  	
  	
  	//删除功能
  	$("#deleteBtn").click(function(){
  	
  		var rows = $("#list").datagrid("getSelections");
  		if(rows.length == 0){
  			$.messager.alert("提示","删除操作至少选择一行","warning");
  			return;
  		}
  		//格式: id=1&id=2&id=3   拼接
  		$.messager.confirm("提示","确认删除数据吗？",function(value){
  			if(value){
  				var idStr = "";
  				//遍历数据
  				$(rows).each(function(i){
  					idStr += ("id="+rows[i].id+"&");
  				});
  				idStr = idStr.substring(0,idStr.length-1);

  				//传递到后台
  				$.post("customer/delete.action",idStr,function(data){
  					if(data.success){
  		  				//刷新dtagrid
  		  				$("#list").datagrid("reload");
  		  				$.messager.alert("提示","删除成功","info");
  		  			}else{
  		  				$.messager.alert("提示","删除失败:" +data.msg,"error");
  		  			}
  				},"json")
  				
  			}
  		});	
  	});
  	
  	
  	
  	
  	
  	
  	
  	
  });
  	
  	
  	
  			
  	
  	</script>





</body>
</html>
