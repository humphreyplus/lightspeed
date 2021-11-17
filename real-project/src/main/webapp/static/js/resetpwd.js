function registerSubmitCheck() {
	var passwordInput = document.getElementById("input-pwd");
	var passwordInput2 = document.getElementById("input-pwd2");
	
	var password = passwordInput.value;
	var password2 = passwordInput2.value;
	
	var msgPwd = document.getElementById("pwd-error-msg");
	var msgPwd2 = document.getElementById("pwd2-error-msg");
	
	errorMsgClear(msgPwd, msgPwd2);
	
	if(password == null || password == '') {
		msgPwd.innerHTML = "新密码不能为空！";
		passwordInput.focus();
		return false;
	}
	if(password2 == null || password2 == '') {
		msgPwd2.innerHTML = "确认密码不能为空！";
		passwordInput2.focus();
		return false;
	}
	
	if(!password.match(/^[A-Za-z0-9]{6,20}$/)) {
		msgPwd.innerHTML = "密码格式不正确！";
		passwordInput.focus();
		return false;
	}
	
	if(password !== password2) {
		msgPwd2.innerHTML = "两次输入的密码不一致！";
		passwordInput2.focus();
		return false;
	}
}

function errorMsgClear(msgPwd, msgPwd2) {
	msgPwd.innerHTML = "";
	msgPwd2.innerHTML = "";
}