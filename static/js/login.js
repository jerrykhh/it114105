
  var defaultForm;
  var defaultButton;
  var fname = getParameterByName("fname");
  var lname = getParameterByName("lname");
  var tel = getParameterByName("tel");

  if(fname != null && lname != null && tel != null){
    document.getElementById("btnSignUp").click();
    document.getElementsByClassName("message")[0].innerHTML = '<div class="bs-callout bs-callout-danger"><h4>Sign up Failed</h4>Email is duplicate</div>';
  }


  function login(){
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    if(username == "" || password == ""){
      document.getElementsByClassName("message")[0].innerHTML = '<div class="bs-callout bs-callout-danger"><h4>login Failed</h4>Username or password incorrect</div>';
    }else{
      document.getElementById("loginform").submit();
    }
  }

  function signupForm(){
    var form = document.getElementById("loginform");
    var btn = document.getElementsByClassName("signup")[0];
    var fname = getParameterByName("fname")!=null? getParameterByName("fname"): "";
    var lname = getParameterByName("lname")!=null? getParameterByName("lname"): "";
    var tel = getParameterByName("tel")!=null? getParameterByName("tel"): "";


    document.getElementsByClassName("message")[0].innerHTML = "";
    defaultForm = form.innerHTML;
    defaultButton = btn.innerHTML;
    form.id = "signupform";
    form.innerHTML = '<input type="email" name="email" id="email" value="" placeholder="Email"/>';
    form.innerHTML += '<input type="text" name="fname" id="fname" value="' + fname +'" placeholder="First Name"/>';
    form.innerHTML += '<input type="text" name="lname" id="lname" value="' + lname +'" placeholder="Last Name"/>';
    form.innerHTML += '<input type="tel" name="tel" id="tel" value="' + tel +'" placeholder="Phone Number"/>';
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
    var email = document.getElementById("email").value;
    var fname = document.getElementById("fname").value;
    var lname = document.getElementById("lname").value;
    var tel = document.getElementById("tel").value;
    var pwd = document.getElementById("password").value;

    if(email == "" || fname == "" || lname == "" || tel == "" || pwd == ""){
      document.getElementsByClassName("message")[0].innerHTML = '<div class="bs-callout bs-callout-danger"><h4>Sign Up Failed</h4>All fields is required</div>';
    }else{
      document.getElementById("signupform").submit();
    }
  }

  function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, '\\$&');
    var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
  }
