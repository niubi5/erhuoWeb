<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>新增用户</title>
<link href="/secondHandShop/erhuo/css/style.css" rel="stylesheet"
	type="text/css">
<script language="JavaScript" type="text/JavaScript"
	src="/secondHandShop/erhuo/js/common.js"></script>
<script type="text/javascript">
	function isFirst() {
		var flag = document.getElementById("isfirst").value;
		//alert(flag);
		if (flag == "true") {
			alert("添加成功!")
		}
	}
	function confirm() {
		//alert("ok");
		var identity = document.getElementById("textIdentity").value;
		var name = document.getElementById("textName").value;
		var pwd = document.getElementById("textPwd").value;
		var pwdConfirm = document.getElementById("textPwdConfirm").value;
		var oForm = document.getElementsByName("frmAction")[0];
		//alert(name);
		if (identity.length == 0) {
			alert("账号不能为空!");
		} else if (!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i.test(identity)) {
			alert("请输入正确的手机号码!");
		} else if (name.length == 0) {
			alert("昵称不能为空!")
		} else if (pwd.length == 0) {
			alert("密码不能为空!");
		} else if (pwd != pwdConfirm) {
			alert("两次输入的密码不一致!");
		} else {
			oForm.action = "/secondHandShop/AddUserServlet?identity="
					+ identity + "&name=" + name + "&pwd=" + pwd;
			oForm.submit();
		}
	}
</script>
</head>

<body leftmargin="0" topmargin="0" onload="isFirst()"
	onLoad="MM_preloadImages('/secondHandShop/erhuo/images/login_10.gif','/secondHandShop/erhuo/images/login_12.gif','/secondHandShop/erhuo/images/login_09.gif','/secondHandShop/erhuo/images/login_11.gif')">
	<form name="frmAction" method="post" action="">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="32" align="left" valign="top" class="text006"><table
						width="98%" border="0" cellspacing="0" cellpadding="0">
						<tr valign="top">
							<td>【 用户管理 】<input id="isfirst" type="hidden"
								value="${tag}"></td>
							<td align="right">&nbsp;</td>

						</tr>
					</table></td>
			</tr>
			<tr>
				<td height="215" valign="top"><table width="98%" border="0"
						cellspacing="0" cellpadding="0">
						<tr align="left" class="bg03">
							<td width="100%" height="29"><span class="text001">&nbsp;&nbsp;新增用户</span></td>
						</tr>

					</table>
					<table width="98%" border="0" cellpadding="0" cellspacing="0"
						class="text008">
						<tr align="center">
							<td width="12%" height="35" align="right">账号</td>
							<td width="88%" align="left"><input id="textIdentity"
								name="textfield" type="text" value="${identity}" class="inp001">
								<font color="#ff0000"> <c:if test="${identity != null}">
										<c:out value="该账号已存在!"></c:out>
									</c:if>
							</font></td>
						</tr>
						<tr align="center" class="bg04">
							<td width="12%" height="35" align="right">昵称</td>
							<td width="88%" align="left"><input id="textName"
								name="textfield" type="text" value="${name}" class="inp001"></td>
						</tr>
						<tr align="center">
							<td height="35" align="right">密码</td>
							<td align="left"><input id="textPwd" name="textfield"
								type="password" value="${pwd}" class="inp001"></td>
						</tr>
						<tr align="center" class="bg04">
							<td height="35" align="right">确认密码</td>
							<td align="left"><input id="textPwdConfirm" name="textfield"
								type="password" value="${pwd}" class="inp001"></td>
						</tr>
					</table>
					<table width="98%" border="0" cellpadding="0" cellspacing="0"
						class="bor001">
						<tr>
							<td width="22%" height="40" align="center"><a
								onclick="confirm()" href="#" target="mainframe"
								onMouseOver="MM_swapImage('Image1','','/secondHandShop/erhuo/images/index_12_1.gif',1)"
								onMouseOut="MM_swapImgRestore()"
								onMouseDown="MM_swapImage('Image1','','/secondHandShop/erhuo/images/index_12_2.gif',1)"
								onMouseUp="MM_swapImage('Image1','','/secondHandShop/erhuo/images/index_12_1.gif',1)"><img
									src="/secondHandShop/erhuo/images/index_12_0.gif" name="Image1"
									width="75" height="24" border="0" id="Image1"></a>&nbsp;&nbsp;&nbsp;&nbsp;<a
								onclick="cancle()" href="#" target="mainframe"
								onMouseOver="MM_swapImage('Image2','','/secondHandShop/erhuo/images/login_09.gif',1)"
								onMouseOut="MM_swapImgRestore()"
								onMouseDown="MM_swapImage('Image2','','/secondHandShop/erhuo/images/login_11.gif',1)"
								onMouseUp="MM_swapImage('Image2','','/secondHandShop/erhuo/images/login_09.gif',1)"><img
									src="/secondHandShop/erhuo/images/login_07.gif" name="Image2"
									width="75" height="24" border="0" id="Image2"></a></td>
							<td width="78%" align="right">&nbsp;</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</form>
</body>
</html>
