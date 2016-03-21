<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>用户列表</title>
<link href="/secondHandShop/erhuo/css/style.css" rel="stylesheet"
	type="text/css">
<script language="JavaScript" type="text/JavaScript"
	src="/secondHandShop/erhuo/js/common.js"></script>
<script>
	function chkAll() {
		var flag = document.getElementsByName("checkAll")[0];
		var list = document.getElementsByName("checkUser");
		for (var i = 0; i < list.length; i++) {
			list[i].checked = flag.checked;
		}
	}
	function deleteUser() {
		var list = document.getElementsByName("checkUser");
		var k = 0;
		for (var i = 0; i < list.length; i++) {
			if (list[i].checked) {
				k++;
			}
		}
		if (k == 0) {
			alert("至少选中一个需要删除的用户");
		} else {
			var choice = confirm("确认要删除这些用户吗?", function() {
			}, null);
			if (choice) {
				var oForm = document.getElementsByName("frmAction")[0];
				oForm.action = "/secondHandShop/DeleteUserServlet";
				alert("删除成功!")
				oForm.submit();
			}
		}
	}
	function changecount(num) {
		var oForm = document.getElementsByName("frmAction")[0];
		oForm.action = "/secondHandShop/ListUserServlet?curPage=1&pageSize="
				+ num;
		oForm.submit();
	}
	function goPage() {
		//alert("ok");
		var oForm = document.getElementsByName("frmAction")[0];
		var pageSize = document.getElementById("pagesize").value;
		var maxPage = document.getElementById("pages").value;
		var pageNum = document.getElementById("pagego").value;
		if (isNaN(pageNum) || pageNum < 1 || pageNum > maxPage) {
			alert("请输入正确的页数!");
		}else{
			oForm.action = "/secondHandShop/ListUserServlet?curPage=" + pageNum
					+ "&pageSize=" + pageSize;
			oForm.submit();			
		}
	}
</script>
</head>
<body topmargin="0" leftmargin="0">
	<form name="frmAction" method="post" action="list.jsp">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="32" align="left" valign="top" class="text006"><table
						width="98%" border="0" cellspacing="0" cellpadding="0">
						<tr valign="top">
							<td>【 用户管理 】</td>
							<td align="right"><a onclick="deleteUser()" href="#"
								onMouseOver="MM_swapImage('Image3','','/secondHandShop/erhuo/images/index_13_1.gif',1)"
								onMouseOut="MM_swapImgRestore()"
								onMouseDown="MM_swapImage('Image3','','/secondHandShop/erhuo/images/index_13_2.gif',1)"
								onMouseUp="MM_swapImage('Image3','','/secondHandShop/erhuo/images/index_13_1.gif',1)"><img
									src="/secondHandShop/erhuo/images/index_13_0.gif" id="Image3"
									width="75" height="24" border="0"></a></td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td height="215" valign="top">
					<table width="98%" border="0" cellspacing="0" cellpadding="0">
						<tr align="center" class="bg03">
							<td height="29"><input type="checkbox" name="checkAll"
								value="checkbox" onclick="chkAll()"> <span
								class="text007">全选</span></td>
							<td class="text007">用户账号</td>
							<td class="text007">昵称</td>
							<td class="text007">性别</td>
							<td class="text007">积分</td>

						</tr>
						<c:forEach var="s" items="${listuser}">
							<tr align="center">
								<td height="35"><input type="checkbox" name="checkUser"
									value='${s.id}'></td>
								<td>${s.identity}</td>
								<td align="center">${s.name}</td>
								<td><c:choose>
										<c:when test="${s.sex == 0}">男</c:when>
										<c:otherwise>女</c:otherwise>
									</c:choose></td>
								<td>${s.jifen}</td>
							</tr>
						</c:forEach>
					</table>
					<table width="98%" border="0" cellpadding="0" cellspacing="0"
						class="bor001">
						<tr>
							<td height="40" align="left">&nbsp;</td>
							<td align="right">每页显示 <select id="menu1" name="menu1"
								onchange="changecount(this.options[this.selectedIndex].value);">
									<option value="10" <c:if test="${pageSize==10}">selected</c:if>>10</option>
									<option value="15" <c:if test="${pageSize==15}">selected</c:if>>15</option>
									<option value="20">20</option>
									<option value="30">30</option>
									<option value="50">50</option>
							</select> 条&nbsp;&nbsp;<c:out value="${page}"></c:out>/<c:out
									value="${pages}"></c:out>页&nbsp;&nbsp;<a
								href="/secondHandShop/ListUserServlet?curPage=1&pageSize=${pageSize}">首页</a>
								| <a
								href='/secondHandShop/ListUserServlet?curPage=<c:choose><c:when test="${page > 1}">${page-1}</c:when><c:otherwise>1</c:otherwise></c:choose>&pageSize=${pageSize}'
								class="tex04">上一页</a> | <a
								href='/secondHandShop/ListUserServlet?curPage=<c:choose><c:when test="${page < pages}">${page+1}</c:when><c:otherwise>${pages}</c:otherwise></c:choose>&pageSize=${pageSize}'
								class="tex04">下一页</a> | <a
								href='/secondHandShop/ListUserServlet?curPage=${pages}&pageSize=${pageSize}'>末页</a>
								<input class="InputText" type="text" id="pagego"
								style="width: 30px;" /><input id="pagesize" type="hidden"
								name="field＿name" value="${pageSize}"> <input
								id="pages" type="hidden" name="field＿name"
								value="${pages}"><input type="button" name="go"
								id="go" value="GO" onclick="goPage()" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
