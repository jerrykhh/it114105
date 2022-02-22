
function profilePassword(){
  var currentPassword = document.getElementById("currentPassword").value;
  var password = document.getElementById("password").value;
  var cfmPassword = document.getElementById("cfmPassword").value;
  var vaild = true;
  if(currentPassword != "" ){

    if(password != cfmPassword){
      document.getElementsByClassName("message")[0].innerHTML = '<div class="bs-callout bs-callout-danger"><h4>Password Incorrect</h4>Confirm password and new password not match</div>';
      vaild = false;
    }else if(password == ""){
      document.getElementsByClassName("message")[0].innerHTML = '<div class="bs-callout bs-callout-danger"><h4>Password Incorrect</h4>You need to input new password</div>';
      vaild = false;
    }
  }
  if(currentPassword == "" && (password != "" || cfmPassword != "")){
      document.getElementsByClassName("message")[0].innerHTML = '<div class="bs-callout bs-callout-danger"><h4>Input Password</h4>Please input the current password</div>';
      vaild = false;
  }

  var fname = document.getElementById("fname").value;
  var lname = document.getElementById("lname").value;
  var tel = document.getElementById("tel").value;

  if(fname == "" || lname == "" || tel == ""){
    document.getElementsByClassName("message")[0].innerHTML += '<div class="bs-callout bs-callout-danger"><h4>Information Missing</h4>Please input all required information</div>';
  }else if (vaild){
    document.getElementById("updateprofile").submit();
  }
}

function deleteAccount(){
  var deletePwd = document.getElementById("deletePassword").value;
  var xhr = new XMLHttpRequest();
  var url = "checking";
  xhr.open("POST", url, true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.onreadystatechange = function () {
      if (xhr.readyState === 4 && xhr.status === 200) {
          var json = JSON.parse(xhr.responseText);
          if(json.statue == true){
            location.replace("logout");
          }else{
            console.log("test");
            document.getElementsByClassName("modal-message")[0].innerHTML = '<div class="bs-callout bs-callout-danger"><h4>Password Incorrect</h4></div>';
          }
      }
  };
  var data = JSON.stringify({"password": deletePwd});
  xhr.send(data);
}
