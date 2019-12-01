$(document).ready(function() {
  $.ajaxSetup({ cache: false });
  var time =4;
    $.LoadFav = function(){
        $("#navBookMark").click(function(){
        $("#favModal").html("");
        $("#modal_box").html("");
        $("#modal_box").html("<div class='modal-content' id='favModal'><h4>Favorite Restaurant</h4></div><div class='modal-footer'><a href='#!' class='modal-action modal-close waves-effect waves-red btn'>Close</a></div>");
        $.getJSON("jsonDB/"+sessionStorage.getItem("username")+".json", function(obj){
				try{
						$.getJSON("jsonDB/restaurants.json", function(result){
                            for(var i=0; i<obj.favorite.length; i++)
							 $.each(result.restaurants, function(index,name){
								if(obj.favorite[i] == name.id){
									$("#favModal").append("<ul class='collection' id='delcollFav"+name.id+"'><li class='collection-item avatar'><img src='"+name.image+"' id='favImg'class='circle'><span class='title' id='favTitle'>"+name.name+"</span><p id='favTag"+obj.favorite[i] +"'></p><a href='#' class='secondary-content deleteFav' id='Delfav"+name.id+"' ><i class='material-icons red-text' >delete</i></a></li></ul>");
                                    $.deleteFav();
									$.each(name.tag, function(tagIndex, tagName){
										$("#favTag"+obj.favorite[i]).append("<div class='chip'>" + tagName + "</div>");
                					});
								}
							});
						});
					//});
				}catch(err){
					console.log("NOT Thing here");
				}
			});
    });
    }

    $.deleteFav = function(){
                $(".deleteFav").click(function(){
                var favVal = $(this).attr("id").substring(6,7);
               $.getJSON("jsonDB/"+sessionStorage.getItem("username")+".json", function(obj){
                     $.each(obj.favorite, function(favIndex, favName){
                             if(favVal == favName){

                                obj.favorite.splice(favIndex, 1);
                                 console.log(obj);
                                 $.ajax({
                                    url: "jsonDB/update.php",
                                    type: "POST",
                                    async: false,
                                    cache: false,
                                    dataType: "json",
                                    data: {dataCus: JSON.stringify(obj), dataEmail: sessionStorage.getItem("username"), fav: 1}
                                 });
                                 $("#delcollFav"+favVal).effect("slide");
                                 $("#delcollFav"+favVal).html("");

                             }
                            console.log()

                                $("#fav"+favVal).html("<i class='material-icons'>favorite</i></a>");
                         });
               });
            });
    }


    $.windowsChecksize = function() {
        var winWidth = $(window).width();
        console.log(sessionStorage.getItem("rule"));
        if (winWidth < 943) {
			if(sessionStorage.getItem("username")){
				$("nav .right").html("<li><a id='btnLoginNav' data-target='slide-out' class='sidenav-trigger'><i class='material-icons lighten-3 icon-brand' >account_circle</i></a></li>");
			}else{
            	$("nav .right").html("<li id='btnLoginNavli'><a id='btnLogin'><i class='material-icons lighten-3 icon-brand' >account_circle</i></a></li>");
			}
        } else {
			if(sessionStorage.getItem("username")){
				 $("nav .right").html("<li><div id='btnLoginNav' data-target='slide-out' class='sidenav-trigger'><a class='waves-effect waves-light btn'><i class='material-icons left' >menu</i>Menu</a></div></li>");
			}else{
	           	 $("nav .right").html("<li id='btnLoginNavli'><a class='waves-effect waves-light btn' id='btnLogin'><i class='material-icons left center' >account_circle</i>Login</a></li>");
			}
        }
		if(sessionStorage.getItem("username")){
			$("#slide-out").html("");
			$("#modal_box").addClass("modal");
			$("#modal_box").html("");
			$("#favModal").html("");
			$('.sidenav').sidenav();
            if(sessionStorage.getItem("rule")=="operator"){
              $("#slide-out").append("<li><div class='user-view'><div class='background'><img src='images/office-1.jpg'></div><a href='#user'><img class='circle' id='navIcon' src='images/icon/yuna.jpg'></a><a href='#name'><span class='white-text name' id='navName'></span></a><a href='#email'><span class='white-text email' id='navEmail'></span></a> </div></li><li><a href='operatorIndex.html'><i class='material-icons'>people</i>Restaurant Management</a></li><li><a href='Info.html'><i class='material-icons'>settings</i>Personal Information</a></li><li><a href='#!' id='navLogout'>Log Out</a></li>");
            }else if (sessionStorage.getItem("rule")=="admin"){
                $("#slide-out").append("<li><div class='user-view'><div class='background'><img src='images/office-1.jpg'></div><a href='#user'><img class='circle' id='navIcon' src='images/icon/yuna.jpg'></a><a href='#name'><span class='white-text name' id='navName'></span></a><a href='#email'><span class='white-text email' id='navEmail'></span></a> </div></li><li><a href='admin.html'><i class='material-icons'>people</i>User Management</a></li><li><a href='Info.html'><i class='material-icons'>settings</i>Personal Information</a></li><li><a href='#!' id='navLogout'>Log Out</a></li>");
            }else{
                $("#slide-out").append("<li><div class='user-view'><div class='background'><img src='images/office-1.jpg'></div><a href='#user'><img class='circle' id='navIcon' src='images/icon/yuna.jpg'></a><a href='#name'><span class='white-text name' id='navName'></span></a><a href='#email'><span class='white-text email' id='navEmail'></span></a> </div></li><li><a href='#modal_box' id='navBookMark' class='modal-trigger'><i class='material-icons'>bookmark</i>favorite restaurant</a></li><li><a href='Info.html'><i class='material-icons'>settings</i>Personal Information</a></li><li><a href='#!' id='navLogout'>Log Out</a></li>");
            }

			$("#navName").append(sessionStorage.getItem("name"));
			$("#navEmail").append(sessionStorage.getItem("username"));
			$("#navLogout").click(function(){
							sessionStorage.removeItem("username");
							sessionStorage.removeItem("name");
							window.location.replace("index.html");
			});
			$("#modal_box").html("<div class='modal-content' id='favModal'><h4>Favorite Restaurant</h4></div><div class='modal-footer'><a href='#!' class='modal-action modal-close waves-effect waves-red btn'>Close</a></div>");
            $.LoadFav();

		}


        $("#btnLogin").click(function() {
            $('#modal1').modal('open');
        });
    }

    $.getRestanauent = function(){
         $.getJSON("jsonDB/restaurants.json", function(result) {
            $.each(result.restaurants, function(index, name) {
                var count = 0,
                    rate = 0;
                $.each(result.commRestaurants, function(comIndex, comName) {
                    if (name.id == comName.resID) {
                        rate += comName.rate;
                        count++;
                    }
                });
                rate /= count;
                if (isNaN(rate))
                    rate = "Not a comment";
                else
                    rate = "Rate: " + rate + "/5.0";
                $("#menu").append("<a href='menu/foodRestaurant"+name.id+".html'><div class='col s12 m4 searchRest'><div class='card'><div class='card-image'><img src='" + name.image + "'><a class='btn-floating halfway-fab waves-effect waves-light red fav' id='fav" + name.id + "'></a></div><div class='card-content'><span class='card-title'>" + name.name + "</span><p>" + rate + "</p></div><div class='card-action' id='" + name.id + "'></a>");
                $.each(name.tag, function(tagIndex, tagName) {
                    $("#" + name.id).append("<div class='chip'>" + tagName + "</div></div></div></div>");
                });
                if(sessionStorage.getItem("username")){
                $.getJSON("jsonDB/"+sessionStorage.getItem("username")+".json", function(obj){
                    var seaVaild = false;
                   $.each(obj.favorite, function(seaFavIndex, seaFavName){

                      if(seaFavName == name.id){
                          seaVaild = true;
                          $("#fav"+name.id).append("<i class='material-icons'>delete</i></a>");
                      }
                   });
                    if(!seaVaild)
                        $("#fav"+name.id).append("<i class='material-icons'>favorite</i></a>");
                });
                }else{
                     $("#fav"+name.id).append("<i class='material-icons'>favorite</i></a>");
                }

            });
             var foodText = GetURLParameter('food');
             if(typeof foodText!="undefined"){
                $(".searchRest").hide().filter(":contains('" + foodText + "')").show();
		            if($(".searchRest:hidden").length >= $(".searchRest").length){
                $("#searchMessage").html("<h4>Sorry, not found about "+ foodText+ " Restanauent. <a href='search.html'>Click Here to see All Restanauent</a></h4>'");
            }
             }
             if(!sessionStorage.getItem("username")){
                 $(".searchRest a").attr("href", "#");
                 $(".searchRest a").click(function(){
                     $("#btnLoginNavli a").click();
                 });
             }else{
                 $.clickFav();
             }
        });
    }

    $.clickFav = function(){
        $(".fav").click(function(){
				if(sessionStorage.getItem("username")){
					 var favVal = $(this).attr("id").substring(3,4);
                     var vaild = false;
                    $.getJSON("jsonDB/"+sessionStorage.getItem("username")+".json", function(obj){
                         $.each(obj.favorite, function(favIndex, favName){
                             if(favVal == favName){
                                 vaild = true;
                                obj.favorite.splice(favIndex, 1);
                                 $("#fav"+favVal).html("<i class='material-icons'>favorite</i>");
                             }
                         });
                        if(!vaild){
                         obj.favorite.push(favVal);
                         console.log(obj);
                        $("#fav"+favVal).html("<i class='material-icons'>delete</i>");
                        }
                         $.ajax({
                            url: "jsonDB/update.php",
                            type: "POST",
                            async: false,
                            cache: false,
                            dataType: "json",
                            data: {dataCus: JSON.stringify(obj), dataEmail: sessionStorage.getItem("username"), fav: 1},
				         });
                     });
				}else{
					$("#btnLoginNavli a").click();
				}

			});
    }



    $("#createInfo").hide();
    $("#createAccount").click(function() {
        $("#loginInfo").hide();
        $("#createInfo").show();
    });
    $("#loginAccount").click(function() {
        $("#createInfo").hide();
        $("#loginInfo").show();
    });

    $(window).resize(function() {
        $.windowsChecksize();
    });

    //$("#btnFBLogin").click(function(){
    //
    //});

    $("#btnSubmitLogin").click(function() {
		$("#loginAlert").html("");
		var loginPwd = $("#loginPwd").val();
		var loginEmail = $("#loginEmail").val();
		if(loginEmail=="" && loginPwd==""){
			$("#loginAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>The Email and password is required!</font></div></div></div>");
		}else if(loginEmail==""){
			$("#loginAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>Missing the Email Address</font></div></div></div>");
		}else if(loginPwd==""){
			$("#loginAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>Missing the Password</font></div></div></div>");
		}else{
			var valid = true;
			$.getJSON("jsonDB/adminCustomer.json",function(result){
				$.each(result.customers, function(index, name){

					if(name.email==loginEmail && name.pwd!=loginPwd){
						valid = false;
						$("#loginAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>Your Password is Worng.</font></div></div></div>");
					}else if(name.email==loginEmail && name.pwd==loginPwd){
						//$("#navIcon").attr("src", name.icon);
						$("#loginAlert").html("");
						valid = false;
						sessionStorage.setItem("username", name.email);
						sessionStorage.setItem("name", name.fname+ " " + name.lname);
                        sessionStorage.setItem("rule", name.rule);

                        if(sessionStorage.getItem("rule")=="operator")
                            window.location.replace("operatorIndex.html");
                        else if(sessionStorage.getItem("rule")=="admin")
                             window.location.replace("admin.html");
                        else{
						                location.reload();
                        }
						valid = false;
					}
				});
				if(valid)
				$("#loginAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>Your Email or Password is Worng!</font></div></div></div>");
				$("#loginAlert").effect("shake");
			});
		}
    });
	$("#btnSubmitRegister").click(function(){
		$("#pwdAlert").html("");
		var pwd = $("#regPassword").val();
		var pwdCheck = $("#checkPassword").val();
		var email = $("#regEmail").val();
		var fname = $("#first_name").val();
		var lname = $("#last_name").val();
    var btnVal = $("#btnSubmitRegister").text();
		var valid = false;
		if(pwd=="" && pwdCheck=="" && email=="" && fname=="" && lname==""){
			$("#pwdAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>You need input the all field!</font></div></div></div>");
			valid = true;
			$("#pwdAlert").effect("shake");
			return 0;
		}
    if(email==""){
      $("#pwdAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>You need input the Email address</font></div></div></div>");
      valid = true;
      $("#pwdAlert").effect("shake");
    } else if(!IsEmail(email)){
      $("#pwdAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>Please check your email format</font></div></div></div>");
      valid = true;
      $("#pwdAlert").effect("shake");
    }
    if(pwd==""||pwdCheck==""){
      $("#pwdAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>Missing Password</font></div></div></div>");
      valid = true;
      $("#pwdAlert").effect("shake");
    }
    if(fname=="" || lname==""){
      $("#pwdAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>Name is required</font></div></div></div>");
      valid = true;
      $("#pwdAlert").effect("shake");
    }
		$.getJSON("jsonDB/adminCustomer.json", function(obj){
			$.each(obj.customers, function(index,name){
				if(name.email == email){
					$("#pwdAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>Your Email Registed!</font></div></div></div>");
					valid = true;
					$("#pwdAlert").effect("shake");
				}
			});
		});
		if(pwd != pwdCheck){
			$("#pwdAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>Your Comfirm Password Not Match!</font></div></div></div>");
			$("#pwdAlert").effect("shake");
			valid = true;
		}
		if(!valid){
			$.getJSON("jsonDB/adminCustomer.json", function(obj){
				var getID = 0;
				$.each(obj.customers, function(index, name){
					if(getID<name.userID)
						getID = name.userID;
				});
				getID++;
				var customer = {
						userID: getID,
						email: email,
						fname: fname,
						lname: lname,
						pwd: pwd,
						rule: "user",
            favorite:[]
					}
				obj.customers.push(customer);
				$.ajax({
					url: "jsonDB/update.php",
					type: "POST",
          async: false,
          cache: false,
					dataType: "json",
					data: {dataAdmin: JSON.stringify(obj), dataCus: JSON.stringify(customer), dataEmail: email}
				});
        $("#regPassword").val("");
        $("#checkPassword").val("");
        $("#regEmail").val("");
        $("#first_name").val("");
        $("#last_name").val("");
				$("#changeLogin a").click();
				$("#loginAlert").append("<div class='row'><div class='col s12'><div class='card-panel green lighten-1' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>Your Account Created.</font></div></div></div>");
				$("#loginAlert").effect("slide");
        if(btnVal == "Add User"){
          $(".modal").modal("close");
          $("#adminUserTable").html("");
          $.getAdminUserInf();
        }
			});
		}
	});
    $.checkRestaurantInf = function(){
            var name = $("#createRestaurantName").val();
            var tel = $("#createRestaureantTel").val();
            var address = $("#createRestaureantAdde").val();
            var area = $("#createRestaurantSelect").val();
            var opTime = $("#createRestaureantOPTime").val();
            var clTime = $("#createRestaureantCLTime").val();
            var valid = true;
            $("#stepOneAlert").html("");
            if(name=="" && tel=="" && address=="" && area=="" && opTime=="" && clTime==""){
                $("#stepOneAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>You need input the all field!</font></div></div></div>");
                valid=false;
            }
            if(name==""){
                $("#stepOneAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>Missing the Restaurant Name</font></div></div></div>");
                valid=false;
            }
            if(tel==""){
                $("#stepOneAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>Missing the Restaurant Name</font></div></div></div>");
                valid=false;
            }
            if(address==""){
                $("#stepOneAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>Missing the Restaurant Address</font></div></div></div>");
                valid=false;
            }


            if(clTime==""||opTime==""){
                $("#stepOneAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>Missing the Restaurant opening hours</font></div></div></div>");
                valid=false;
            }
            if(!valid){
                $("#firstStep").addClass("red-text");
            }else{
              $("#firstStep").removeClass("red-text");
            }
    }

    $("#btnSubmitUpdate").click(function(){
      $("#pwdAlert").html("");
      var pwd = $("#regPassword").val();
      var pwdCheck = $("#checkPassword").val();
      var oldPassword = $("#oldPassword").val();
      var email = $("#regEmail").val();
      var fname = $("#first_name").val();
      var lname = $("#last_name").val();
      var btnVal = $("#btnSubmitRegister").text();
      var valid = false;

      if(pwd=="" && pwdCheck=="" && email=="" && fname=="" && lname==""){
  			$("#pwdAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>You need input the all field!</font></div></div></div>");
  			valid = true;
  			$("#pwdAlert").effect("shake");
  			return 0;
  		}
      if(email==""){
        $("#pwdAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>You need input the Email address</font></div></div></div>");
        valid = true;
        $("#pwdAlert").effect("shake");
      } else if(!IsEmail(email)){
        $("#pwdAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>Please check your email format</font></div></div></div>");
        valid = true;
        $("#pwdAlert").effect("shake");
      }


      if(fname=="" || lname==""){
        $("#pwdAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>Name is required</font></div></div></div>");
        valid = true;
        $("#pwdAlert").effect("shake");
      }

      if(pwd==""||pwdCheck==""){
        $("#pwdAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>Missing Password</font></div></div></div>");
        valid = true;
        $("#pwdAlert").effect("shake");
      }
      if(pwd==pwdCheck){
      $.getJSON("jsonDB/"+sessionStorage.getItem("username")+".json", function(obj){

        if(obj.pwd!=oldPassword){
          console.log(obj.pwd+ "  "+ oldPassword);
          $("#pwdAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>Old password not match</font></div></div></div>");
          valid = true;
          $("#pwdAlert").effect("shake");
        }
      });
    }else{
      $("#pwdAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>Password not match</font></div></div></div>");
      valid = true;
      $("#pwdAlert").effect("shake");
    }
    if(!valid){
        $.getJSON("jsonDB/"+sessionStorage.getItem("username")+".json", function(obj){
          var info = {
            "userID": obj.userID,
            "email": email,
            "fname": fname,
            "lname": lname,
            "pwd": pwdCheck,
            "user": obj.rule,
            "favorite":[]
          }
          $.each(obj.favorite, function(index, name){
            info.favorite.push(name);
          });

          console.log(info);
          $.ajax({
             url: "jsonDB/update.php",
             type: "POST",
             async: false,
             cache: false,
             dataType: "json",
             data: {dataCus: JSON.stringify(info), dataEmail: sessionStorage.getItem("username"), fav: 1}
          });
            $("#pwdAlert").html("<div class='row'><div class='col s12'><div class='card-panel green lighten-1' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>Your Information updated.</font></div></div></div>");
            $("#pwdAlert").effect("slide");
            $("#regPassword").val("");
            $("#checkPassword").val("");
            $("#oldPassword").val("");
        });
    }
    $("#regPassword").val("");
    $("#checkPassword").val("");
    $("#oldPassword").val("");


    });

    $("#btnAddCatalog").click(function(){
            $("#menuCatAlert").html("");
            var cataHidd = $("#catalogHidden").prop("checked");
            var catalog = $("#catalog").val();
            var cataAllDay = $("#catalogAllDay").prop("checked");
            var invalid = true;
            if(!cataAllDay){
                var startTime = $("#catalogTo").val();
                var closeTime = $("#catalogCl").val();

            }else{
                var startTime = "NA";
                var closeTime = "NA";
            }

            if(catalog==""){
                $("#menuCatAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>Please input your catalog</font></div></div></div>");
                invalid=false;
            }
            if(!cataAllDay && startTime=="" && closeTime==""){
                $("#menuCatAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>Missing the Catalog Time</font></div></div></div>");
                invalid=false;
            }
            if(!invalid){
                $("#secondStep i").addClass("red-text");
            }
            if(invalid){
                $("#catalogTable").append("<tr><td>"+catalog+"</td><td>"+cataAllDay+"</td><td>"+startTime+"</td><td>"+closeTime+"</td><td class='catalogHide'>"+cataHidd+"</td><td><a class='btn-floating btn-small waves-effect waves-light red btnCataDelete'><i class='material-icons'>delete</i></a> <a class='btn-floating btn-small waves-effect waves-light teal lighten-2 btnCataChange'><i class='material-icons'>repeat</i></a></td></tr>");
                $("#catalog").val("");
                $("#secondStep i").removeClass("red-text");
                $(".btnCataChange").click(function(){
                    var disVal = $(this).parents("tr").children("td:eq(4)").html();
                    if(disVal=="false"){
                        $(this).parents("tr").children("td:eq(4)").html("true") ;
                    }else{
                        $(this).parents("tr").children("td:eq(4)").html("false") ;
                    }
                });
                $(".btnCataDelete").click(function(){
                   $(this).parents("tr").remove();
                });

            }
    });


    $("#secondStep").click(function(){
        $.checkRestaurantInf();
    });

    $("#adminAddUser").click(function(){
      $("#adminReg").modal("open");
    });

    $("#filterApply").click(function(){
      var filterArray = []
      if($("#filterNoodles").prop("checked")){
        filterArray.push($("#filterNoodles").val());
      }
      if($("#filterKoeran").prop("checked")){
        filterArray.push($("#filterKoeran").val());
      }
      if($("#filterTaiwanese").prop("checked")){
        filterArray.push($("#filterTaiwanese").val());
      }
      if($("#filterRice").prop("checked")){
        filterArray.push($("#filterRice").val());
      }
      if($("#filterChinese").prop("checked")){
        filterArray.push($("#filterChinese").val());
      }
      if($("#filterWestern").prop("checked")){
        filterArray.push($("#filterWestern").val());
      }
      if($("#filterDimSum").prop("checked")){
        filterArray.push($("#filterDimSum").val());
      }
      if(filterArray.length>0){
        var filterText = ":contains('";
        for (var i = filterArray.length; i >0; i--) {
            filterText += filterArray.pop()+"')";
            if(filterArray.length>0)
              filterText+=", :contains('";
        }
        $(".searchRest").hide().filter(filterText).show();
      }else{
        $(".searchRest").show();
      }
    });

    $("#filterClear").click(function(){
      var checkboxes = document.getElementsByTagName('input');

      for (var i=0; i<checkboxes.length; i++)  {
        if (checkboxes[i].type == 'checkbox')   {
          checkboxes[i].checked = false;
        }
      }
      $(".searchRest").show();
    });



    $.getAdminUserInf = function(){
      if(sessionStorage.getItem("rule")=="admin"){
      $.getJSON("jsonDB/adminCustomer.json", function(obj){
        $.each(obj.customers, function(index, name){
          if(name.rule == "user")
            $("#adminUserTable").append("<tr><td>"+name.userID+"</td><td>"+name.email+"</td><td>"+name.rule+"</td><td><a class='btn-floating btn-small waves-effect waves-light red btnAdminDelete'><i class='material-icons'>delete</i></a> <a class='btn-floating btn-small waves-effect waves-light teal lighten-2 btnAdminRest'><i class='material-icons'>restaurant</i></a></td></tr>");
          else if(name.rule == "operator"){
            $("#adminUserTable").append("<tr><td>"+name.userID+"</td><td>"+name.email+"</td><td>"+name.rule+"</td><td><a class='btn-floating btn-small waves-effect waves-light red btnAdminDelete'><i class='material-icons'>delete</i></a> <a class='btn-floating btn-small waves-effect waves-light green lighten-3 btnAdminRest'><i class='material-icons'>person</i></a></td></tr>");
          }else if(name.rule == "admin"){
              $("#adminUserTable").append("<tr><td>"+name.userID+"</td><td>"+name.email+"</td><td>"+name.rule+"</td><td><a class='btn-floating btn-small waves-effect waves-light red btnAdminDelete'><i class='material-icons'>delete</i></a></tr>");
          }
        });
        $(".btnAdminDelete").click(function(){
          var valThis = $(this);
          $("#modalDelAlertContentMsg").html("<p>Do you want to delete <b>"+ valThis.parents("tr").children("td:eq(1)").text() +"</b></p>")
          $('#modalDelAlert').modal('open');
          $(".modalDelconfirm").click(function(){
            valThis.parents("tr").hide();
            $("#adminWellcomMesg").html("<div class='card-panel red' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text' >Account deleted</font></div></div>");
            $("#adminWellcomMesg").effect("slide");
          });
          $(".modalDelClose").click(function(){
            $('#modalDelAlert').modal('close');
          });
        });

        $(".btnAdminRest").click(function(){
          var value = $(this).parents().children("td:eq(2)").text();
          if(value == "user"){
            $(this).html("<i class='material-icons'>person</i></a>");
            $(this).removeClass("lighten-2");
            $(this).removeClass("teal");
            $(this).addClass("green");
            $(this).addClass("lighten-3");
            $(this).parents().children("td:eq(2)").html("operator");
            $("#adminWellcomMesg").html("<div class='card-panel green lighten-2' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text' >Updated the "+$(this).parents().children("td:eq(1)").text()+" to Operator.</font></div></div>");
            $("#adminWellcomMesg").effect("slide");
          }else if(value== "operator"){
            $(this).html("<i class='material-icons'>restaurant</i></a>");
            $(this).addClass("lighten-2");
            $(this).addClass("teal");
            $(this).removeClass("green");
            $(this).removeClass("lighten-3");
            $(this).parents().children("td:eq(2)").html("user");
            $("#adminWellcomMesg").html("<div class='card-panel lighten-2 teal' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text' >Updated the "+$(this).parents().children("td:eq(1)").text()+" to User.</font></div></div>");
            $("#adminWellcomMesg").effect("slide");
          }

        });
        $("#adminSearch").keyup(function(){
          var adminSearch = $("#adminSearch").val();
          $("tr:gt(0)").hide().filter(":contains('" + adminSearch + "')").show();
        });
      });
    }
    }
    $.getUserInf = function(){
      $.getJSON("jsonDB/"+sessionStorage.getItem("username")+".json", function(obj){
        $("#regEmaillable").addClass("active");
        $("#last_namelable").addClass("active");
        $("#first_namelable").addClass("active");
        $("#regEmail").val(obj.email);
        $("#first_name").val(obj.fname);
        $("#last_name").val(obj.lname);
      });
    }


    function btnSave(){
    $("#btnMenuSave").click(function(){
            var name = $("#createRestaurantName").val();
            var tel = $("#createRestaureantTel").val();
            var address = $("#createRestaureantAdde").val();
            var opTime = $("#createRestaureantOPTime").val();
            var clTime = $("#createRestaureantCLTime").val();
            var btnMenuSave = $("#btnMenuSave").text();
            $("#menuMissing").html("");
            console.log(btnMenuSave);
            var valid = true;
            if(name==""){
                $("#menuMissing").append("Section 1: Restaurant name<br>");
                valid=false;
            }
            if(tel==""){
                $("#menuMissing").append("Section 1: Restaurant Telphone<br>");
            }
            if(address==""){
                $("#menuMissing").append("Section 1: Restaurant Address<br>");
                valid=false;
            }

            if(clTime==""||opTime==""){
                $("#menuMissing").append("Section 1: Restaurant Open and close time<br>");
                valid=false;
            }
            if(address=="" || tel=="" || name=="" || clTime==""||opTime==""){
                $("#firstStep").addClass("red-text");
            }
            if($("#catalogTable tr").length<2){
                $("#menuMissing").append("Section 2: The Restaurant catalog<br>");
                valid=false;
                $("#secondStep").addClass("red-text");
            }
            if($("#resturantMenu tr").length<2){
                $("#menuMissing").append("Section 3: The Restaurant food<br>");
                valid=false;
                $("#stepThrid").addClass("red-text");
            }
            console.log(valid+" && " + btnMenuSave);
            if(valid && btnMenuSave=="Create"){
              var restName = $("#createRestaurantName").val();
              $("#restaurantMenu").append("<a href='#'><div class='col s12 m3 searchOPRest'><div class='card'><div class='card-image waves-effect waves-block waves-light'><img class='activator' src='images/default.jpg'></div><div class='card-content'><span class='card-title activator grey-text text-darken-4'>"+restName+"</span><a class='dropdown-trigger' href='#' data-target='dropdown"+time+"'><p>Modify</p></a></div><ul id='dropdown"+time+"' class='dropdown-content'><li><a href='#createRest' class='restEdit  modal-trigger' id='"+time+"'>Edit</a></li><li><a href='#warningModal' class='red-text restDel'>Delete</a></li></ul></div></div></div></a>");
              time++;
            $('.dropdown-trigger').dropdown();
                   $("#menuAlert").html("");
                   $("#menuMissing").siblings().html("System Message:");
                   $("#menuMissing").html("<h5>Saved</h5>");
                   $("#stepOneAlert").html("");
                   $("#menuCatAlert").html("");
                   $("#secondStep").removeClass("red-text");
                   $("#firstStep").removeClass("red-text");
                   $("#stepThrid").removeClass("red-text");
                   $("#createRestaurantName").val("");
                   $("#createRestaureantTel").val("");
                   $("#createRestaureantAdde").val("");
                   $("#createRestaurantSelect").val("");
                   $("#createRestaureantOPTime").val("");
                   $("#createRestaureantCLTime").val("");
                   $("#menuFoodName").val("");
                   $("#menuFoodPrice").val("");
                   if(  $("#menuMissing").text()=="Saved"){
                     $("#alertClose").click(function(){
                        $('.modal').modal('close');
                     });
                 }
                   $("#menuCatalog").html("<option value='' disabled selected>Choose your catalog</option>");
                   $("#catalogTable").html("<tr><th>Catalog Name</th><th>All Day</th><th>Start Time</th><th>Close Time</th><th>Hidden</th><th>Action</th></tr>");
                   $("#resturantMenu").html("<tr><th>Food Name</th><th>Catalog</th><th>Price</th><th>Display</th><th>Action</th></tr>");
                   $(".restDel").click(function(){
                       var restDel = $(this).parents(".col");
                       $(this).addClass("modal-trigger");
                       $("#wraningAlert").html('Do you want to delete " '+$(this).parents(".card-content").children().first().text()+'", you cannot recovery deleted menu.');
                        $("#closeMenu").click(function(){
                            restDel.hide();
                        });
                   });
            }else if(valid && btnMenuSave =="SAVE"){
                $("#menuMissing").siblings().html("System Message:");
                $("#menuMissing").html("<h5>Saved</h5>");
                if(  $("#menuMissing").text()=="Saved"){
                  $("#alertClose").click(function(){
                     $('#createRest').modal('close');
                     $("#alert").modal("close");
                  });
              }
            }

    });
    }

    function GetURLParameter(sParam) {
            var sPageURL = window.location.search.substring(1);
            var sURLVariables = sPageURL.split('&');
            for (var i = 0; i < sURLVariables.length; i++) {
                var sParameterName = sURLVariables[i].split('=');
                if (sParameterName[0] == sParam)
                    return sParameterName[1];
            }
        }
     function IsEmail(email) {
      var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
      if(!regex.test(email)) {
        return false;
      }else{
        return true;
      }
    }


    btnSave();
    $.deleteFav();
    $.clickFav();
    $.windowsChecksize();
      $('.chips-placeholder').chips({
        placeholder: 'The Food tag',
        secondaryPlaceholder: '+Tag',
    });
    $('.parallax').parallax();
    $('.modal').modal();
    $('.collapsible').collapsible();
    $('select').formSelect();
    $('.tabs').tabs();
    $('#createRestaurantSelect').formSelect();
});
