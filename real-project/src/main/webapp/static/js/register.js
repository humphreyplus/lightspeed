function registerSubmitCheck() {
	var usernameInput = document.getElementById("input-username");
	var passwordInput = document.getElementById("input-pwd");
	var passwordInput2 = document.getElementById("input-pwd2");
	var emailInput = document.getElementById("input-email");
	
	var username = usernameInput.value;
	var password = passwordInput.value;
	var password2 = passwordInput2.value;
	var email = emailInput.value;
	
	var msgUsername = document.getElementById("username-error-msg");
	var msgPwd = document.getElementById("pwd-error-msg");
	var msgPwd2 = document.getElementById("pwd2-error-msg");
	var msgEmail = document.getElementById("email-error-msg");
	
	errorMsgClear(msgUsername, msgPwd, msgPwd2, msgEmail);
	
	if(username == null || username == '') {
		msgUsername.innerHTML = "用户名不能为空！";
		usernameInput.focus();
		return false;
	}
	if(password == null || password == '') {
		msgPwd.innerHTML = "密码不能为空！";
		passwordInput.focus();
		return false;
	}
	if(password2 == null || password2 == '') {
		msgPwd2.innerHTML = "确认密码不能为空！";
		passwordInput2.focus();
		return false;
	}
	if(email == null || email == '') {
		msgEmail.innerHTML = "电子邮箱不能为空！";
		emailInput.focus();
		return false;
	}
	if(!username.match(/^[A-Za-z0-9\u4E00-\u9FA5]{1,50}$/)) {
		msgUsername.innerHTML = "用户名格式不正确！";
		usernameInput.focus();
		return false;
	}
	if(!password.match(/^[A-Za-z0-9]{1,50}$/)) {
		msgPwd.innerHTML = "密码格式不正确！";
		passwordInput.focus();
		return false;
	}
	if(email.length < 1 || email.length > 50 || email.indexOf("@") < 0) {
		msgEmail.innerHTML = "电子邮箱格式不正确！";
		emailInput.focus();
		return false;
	}
	if(password !== password2) {
		msgPwd2.innerHTML = "两次输入的密码不一致！";
		passwordInput2.focus();
		return false;
	}
}

function errorMsgClear(msgUsername, msgPwd, msgPwd2, msgEmail) {
	msgUsername.innerHTML = "";
	msgPwd.innerHTML = "";
	msgPwd2.innerHTML = "";
	msgEmail.innerHTML = "";
}