function loginSubmitCheck() {
	var usernameInput = document.getElementById("input-user");
	var passwordInput = document.getElementById("input-pwd");
	var errorMsg = document.getElementById("login-error-msg");
	var username = usernameInput.value;
	var password = passwordInput.value;
	if(username == null || username == '') {
		errorMsg.innerHTML = "用户不能为空！";
		usernameInput.focus();
		return false;
	}
	if(password == null || password == '') {
		errorMsg.innerHTML = "密码不能为空！";
		passwordInput.focus();
		return false;
	}
}