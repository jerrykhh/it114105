
function profilePassword(){
  var password = document.getElementById("password").innerHTML;
  var cfmpassword = document.getElementById("cfmPassword").innerHTML;

  if(password != cfmPassword){
    document.getElementByClassName("message")[1].innerHTML = "";
  }else if(password == ""){
    document.getElementByClassName("message")[1].innerHTML = "";
  }else{
    document.getElementById("updatePasswordForm").submit();
  }
}
