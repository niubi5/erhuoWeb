<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>二货铺子管理系统</title>
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">   
<script type="text/javascript" src="easyui/jquery.min.js"></script>   
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script> 
</head>
<body background="images/bg1.gif">
<div id="cc" class="easyui-layout" style="width:1150px;height:635px; margin:auto">   
    <div data-options="region:'north',collapsed:false",title:'North Title',split:true" style="height:100px;">
    <img src="easyui/themes/icons/title.png" width="1148" height="98" />
    </div>   
    <div data-options="region:'west',collapsed:false",title:'West',split:true" style="width:200px;">
    <div id="aa" class="easyui-accordion" style="width:197px;height:517px;">   
    <div title="管理员登录" data-options="iconCls:'icon-man'" style="overflow:auto;padding:10px;">   
       <p>欢迎您:admin</p>
       <a id="btn" href="javascript:window.open('','_self','');
window.close();" class="easyui-linkbutton" data-options="iconCls:'icon-no'">退出系统</a>
    </div>   
    <div title="用户管理" data-options="iconCls:'icon-lock',selected:true" style="padding:10px;">
      <a id="btn" href="/secondHandShop/ListUserServlet?curPage=1&pageSize=10" target="mainframe" class="easyui-linkbutton" data-options="iconCls:'icon-leibie'">用户列表</a><br /><br />
      <a id="btn" href="/secondHandShop/AddUserServlet" target="mainframe" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增用户</a><br /><br />
      <a id="btn" href="user/find.html" target="mainframe" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查找用户</a> 
    </div>
    <div title="集市管理" data-options="iconCls:'icon-jishi'" style="padding:10px;">  
      <a id="btn" href="jishi/list.html" target="mainframe" class="easyui-linkbutton" data-options="iconCls:'icon-leibie'">集市列表</a><br /><br />
      <a id="btn" href="jishi/add.html" target="mainframe" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增集市</a>   
    </div>
    <div title="分类类目" data-options="iconCls:'icon-leibie'" style="padding:10px;">  
      <a id="btn" href="fenlei/list.html" target="mainframe" class="easyui-linkbutton" data-options="iconCls:'icon-leibie'">类目列表</a><br /><br />
      <a id="btn" href="fenlei/add.html" target="mainframe" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增类目</a> 
    </div>
    <div title="商品管理" data-options="iconCls:'icon-sp'" style="padding:10px;">  
      <a id="btn" href="goods/list.html" target="mainframe" class="easyui-linkbutton" data-options="iconCls:'icon-leibie'">商品列表</a><br /><br />
      <a id="btn" href="goods/find.html" target="mainframe" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查找商品</a>
    </div>
    <div title="捐赠管理" data-options="iconCls:'icon-jz'" style="padding:10px;">  
      <a id="btn" href="juanzeng/list.html" target="mainframe" class="easyui-linkbutton" data-options="iconCls:'icon-qjz'">求捐赠列表</a><br /><br />
      <a id="btn" href="juanzeng/find.html" target="mainframe" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查找求捐赠信息</a>
    </div>
    <div title="积分商品" data-options="iconCls:'icon-jifen'" style="padding:10px;">  
      <a id="btn" href="jifengoods/list.html" target="mainframe" class="easyui-linkbutton" data-options="iconCls:'icon-leibie'">积分商品列表</a><br /><br />
      <a id="btn" href="jifengoods/add.html" target="mainframe" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加积分商品</a>  
    </div>
    <div title="举报处理" data-options="iconCls:'icon-tip'" style="padding:10px;">  
      <a id="btn" href="jubao/list.html" target="mainframe" class="easyui-linkbutton" data-options="iconCls:'icon-weichuli'">未处理的违规操作</a><br /><br />
      <a id="btn" href="jubao/find.html" target="mainframe" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查找未处理违规操作</a>
    </div>
</div>
    </div>   
    <div data-options="region:'center',title:'Content'" style="padding:5px;background:#eee;">
    <iframe  name="mainframe" src="goods/list.html" width="100%" height="100%" frameborder="0" scrolling="no"></iframe>
    </div>   
</div>
</body>
</html>
