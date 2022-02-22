$(document).ready(function() {
  $.ajaxSetup({ cache: false });
    $.LoadFav = function(){
        $("#navBookMark").click(function(){
        $("#favModal").html("");
        $("#modal_box").html("");
        $("#modal_box").html("<div class='modal-content' id='favModal'><h4>Favorite Restaurant</h4></div><div class='modal-footer'><a href='#!' class='modal-action modal-close waves-effect waves-red btn'>Close</a></div>");
        $.getJSON("../jsonDB/"+sessionStorage.getItem("username")+".json", function(obj){
				try{
						$.getJSON("../jsonDB/restaurants.json", function(result){
                            for(var i=0; i<obj.favorite.length; i++)
							 $.each(result.restaurants, function(index,name){
								if(obj.favorite[i] == name.id){
									$("#favModal").append("<ul class='collection' id='delcollFav"+name.id+"'><li class='collection-item avatar'><img src='../"+name.image+"' id='favImg'class='circle'><span class='title' id='favTitle'>"+name.name+"</span><p id='favTag"+obj.favorite[i] +"'></p><a href='#' class='secondary-content deleteFav' id='Delfav"+name.id+"' ><i class='material-icons red-text' >delete</i></a></li></ul>");
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
               $.getJSON("../jsonDB/"+sessionStorage.getItem("username")+".json", function(obj){
                     $.each(obj.favorite, function(favIndex, favName){
                             if(favVal == favName){

                                obj.favorite.splice(favIndex, 1);
                                 console.log(obj);
                                 $.ajax({
                                    url: "../jsonDB/update.php",
                                    type: "POST",
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
        $("#slide-out").append("<li><div class='user-view'><div class='background'><img src='../images/office-1.jpg'></div><a href='#user'><img class='circle' id='navIcon' src='../images/icon/yuna.jpg'></a><a href='#name'><span class='white-text name' id='navName'></span></a><a href='#email'><span class='white-text email' id='navEmail'></span></a> </div></li><li><a href='../operatorIndex.html'><i class='material-icons'>people</i>Restaurant Management</a></li><li><a href='../Info.html'><i class='material-icons'>settings</i>Personal Information</a></li><li><a href='#!' id='navLogout'>Log Out</a></li>");
        $("#btnBack").attr("href","../operatorIndex.html");
      }else if (sessionStorage.getItem("rule")=="admin"){
          $("#slide-out").append("<li><div class='user-view'><div class='background'><img src='../images/office-1.jpg'></div><a href='#user'><img class='circle' id='navIcon' src='../images/icon/yuna.jpg'></a><a href='#name'><span class='white-text name' id='navName'></span></a><a href='#email'><span class='white-text email' id='navEmail'></span></a> </div></li><li><a href='../admin.html'><i class='material-icons'>people</i>User Management</a></li><li><a href='../Info.html'><i class='material-icons'>settings</i>Personal Information</a></li><li><a href='#!' id='navLogout'>Log Out</a></li>");
      }else{
          $("#slide-out").append("<li><div class='user-view'><div class='background'><img src='../images/office-1.jpg'></div><a href='#user'><img class='circle' id='navIcon' src='../images/icon/yuna.jpg'></a><a href='#name'><span class='white-text name' id='navName'></span></a><a href='#email'><span class='white-text email' id='navEmail'></span></a> </div></li><li><a href='#modal_box' id='navBookMark' class='modal-trigger'><i class='material-icons'>bookmark</i>favorite restaurant</a></li><li><a href='../Info.html'><i class='material-icons'>settings</i>Personal Information</a></li><li><a href='#!' id='navLogout'>Log Out</a></li>");
      }
			$("#navName").append(sessionStorage.getItem("name"));
			$("#navEmail").append(sessionStorage.getItem("username"));
			$("#navLogout").click(function(){
							sessionStorage.removeItem("username");
							sessionStorage.removeItem("name");
              window.location.replace("../index.html");
			});
			$("#modal_box").html("<div class='modal-content' id='favModal'><h4>Favorite Restaurant</h4></div><div class='modal-footer'><a href='#!' class='modal-action modal-close waves-effect waves-red btn'>Close</a></div>");
            $.LoadFav();

		}


        $("#btnLogin").click(function() {
            $('#modal1').modal('open');
        });
    }

    $.getRestanauent = function(){
         $.getJSON("../jsonDB/restaurants.json", function(result) {
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
                $.getJSON("../jsonDB/"+sessionStorage.getItem("username")+".json", function(obj){
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
                    $.getJSON("../jsonDB/"+sessionStorage.getItem("username")+".json", function(obj){
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
                            url: "../jsonDB/update.php",
                            type: "POST",
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
			$.getJSON("../jsonDb/adminCustomer.json",function(result){
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
		var valid = false;
		if(pwd=="" && pwdCheck=="" && email=="" && fname=="" && lname==""){
			$("#pwdAlert").append("<div class='row'><div class='col s12'><div class='card-panel red lighten-1 left' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>You need input the all field!</font></div></div></div>");
			valid = true;
			$("#pwdAlert").effect("shake");
			return 0;
		}

		$.getJSON("../jsonDB/adminCustomer.json", function(obj){
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
			$.getJSON("../jsonDB/adminCustomer.json", function(obj){
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
						rule: "user"
					}
				obj.customers.push(customer);
				$.ajax({
					url: "../jsonDB/update.php",
					type: "POST",
					dataType: "json",
					data: {dataAdmin: JSON.stringify(obj), dataCus: JSON.stringify(customer), dataEmail: email}
				});
				$("#changeLogin a").click();
				$("#loginAlert").append("<div class='row'><div class='col s12'><div class='card-panel green lighten-1' style=' width: 100%; line-height: 0px; text-align: left'><font size='2' class='white-text'>Your Account Created.</font></div></div></div>");
				$("#loginAlert").effect("slide");
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
            }
    }

    $("#btnAddCatalog").click(function(){
            $("#menuCatAlert").html();
            var cataHidd = $("#catalogHidden").prop("checked");
            var catalog = $("#catalog").val();
            var cataAllDay = $("#catalogAllDay").prop("checked");
            var invalid = true;
            if(!cataAllDay){
                var startTime = $("#catalogTo").val();
                var closeTime = $("#catalogCl").val();
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
                $("#catalog").text();
                $("#secondStep i").removeClass("red-text");
                $(".btnCataChange").click(function(){
                    var disVal = $(this).parents("tr").children("td:eq(4)").html();
                    alert(disVal);
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

    function btnSave(){
    $("#btnMenuSave").click(function(){
            var name = $("#createRestaurantName").val();
            var tel = $("#createRestaureantTel").val();
            var address = $("#createRestaureantAdde").val();
            var opTime = $("#createRestaureantOPTime").val();
            var clTime = $("#createRestaureantCLTime").val();

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
            if(valid){
                $(this).removeClass("modal-trigger");
                   $("#menuAlert").html("");
                   $("#stepOneAlert").html("");
                   $("#menuCatAlert").html("");
                   $("#secondStep").removeClass("red-text");
                   $("#firstStep").removeClass("red-text");
                   $("#stepThrid").removeClass("red-text");
                   $("#createRestaurantName").val("");
                   $("#createRestaureantTel").val("");
                   $("#createRestaureantAdde").text("");
                   $("#createRestaurantSelect").val("");
                   $("#createRestaureantOPTime").val("");
                   $("#createRestaureantCLTime").val("");
                   $("#catalogTable").html("<tr><th>Catalog Name</th><th>All Day</th><th>Start Time</th><th>Close Time</th><th>Hidden</th><th>Action</th></tr>");
                   $("#resturantMenu").html("<tr><th>Food Name</th><th>Catalog</th><th>Price</th><th>Display</th><th>Action</th></tr>");
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
});// JavaScript Document
