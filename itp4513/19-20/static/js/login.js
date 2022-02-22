
  var defaultForm;
  var defaultButton;

  function login(){
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    if(username == "" || password == ""){
      document.getElementByClassName("message").innerHTML = "error";
    }else{
      document.getElementById("loginform").submit();
    }
  }

  function signupForm(){
    var form = document.getElementById("loginform");
    var btn = document.getElementsByClassName("signup")[0];
    defaultForm = form.innerHTML;
    defaultButton = btn.innerHTML;
    form.id = "signupform";
    form.innerHTML = '<input type="email" name="email" id="email" value="" placeholder="Email"/>';
    form.innerHTML += '<input type="text" name="fname" id="fname" value="" placeholder="First Name"/>';
    form.innerHTML += '<input type="text" name="lname" id="lname" value="" placeholder="Last Name"/>';
    form.innerHTML += '<input type="tel" name="tel" id="tel" value="" placeholder="Phone Number"/>';
    form.innerHTML += '<input type="password" name="password" id="password" value="" placeholder="Password"/>';
    form.innerHTML += '<input type="button" onClick="signup()" value="Sign up"/>';
    form.action = "";
    btn.innerHTML = '<p>Ready have account? <a id="btnLogin" onclick="loginForm()">Login</a></p>';
    window.history.pushState("", "", 'signup');
  }

  function loginForm(){
    document.getElementById("signupform").id = "loginform";
    document.getElementById("loginform").innerHTML = defaultForm;
    document.getElementsByClassName("signup")[0].innerHTML = defaultButton;
    window.history.pushState("", "", 'index');
  }

  function signup(){
    console.log("sign")
  }
