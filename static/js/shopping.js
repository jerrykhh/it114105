function checkQty(id){
  var qty = document.getElementById(id).value;
  if(qty <= 0){
    document.getElementById(id).value = 1;
    document.getElementsByClassName('message')[0].innerHTML = '<div class="bs-callout bs-callout-danger"><h4>Error Callout</h4>Product quantity must not small than 1</div>';

    if(document.getElementsByClassName("payment-button")[0] != null){
      console.log("test")
      document.getElementsByClassName("payment-button")[0].childNodes[1].childNodes[0].style.marginTop = "66px";
    }
  }else{
    document.getElementsByClassName('message')[0].innerHTML = "";
    if(document.getElementsByClassName("payment-button")[0] != null){
      console.log("test")
      document.getElementsByClassName("payment-button")[0].childNodes[1].childNodes[0].style.marginTop = "135px";
    }
  }

}

function addToCart(storeId, productId){
  var cartElemt = document.getElementById("cart");
  var productQty = document.getElementById(storeId + "-" + productId).value;

  console.log("test1");
    console.log("test");
    var xhr = new XMLHttpRequest();
    var url = "addToCart";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var json = JSON.parse(xhr.responseText);
            if(json.statue == true){
              let index = 0;
              var elemt = document.getElementsByTagName("ol")[0];
              for(var i = 0; i < elemt.childElementCount; i++){
                  if(i == index){
                    console.log("index" + i);
                    if (cartElemt != null){
                      document.getElementsByClassName("item-count")[0].innerHTML = json.itemCount;
                    }else{
                      console.log("bot");
                      elemt.children[i].innerHTML += '<li id="cart"><a href="cart" class="selected"><span><div class="item-count">' + json.itemCount +'</div> Cart</span></a></li>';
                    }
                  }
              }
            }
        }
    };
    console.log(storeId + " " + productId + " " + productQty);
    var data = JSON.stringify({"storeId": storeId, "productId": productId, "qty": productQty});
    xhr.send(data);
}

function removeInCart(productId){
  var xhr = new XMLHttpRequest();
  var url = "removeInCart";
  xhr.open("POST", url, true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.onreadystatechange = function () {
      if (xhr.readyState === 4 && xhr.status === 200) {
          var json = JSON.parse(xhr.responseText);
          var message = document.getElementsByClassName("message")[0].innerHTML;
          if(json.statue && json.itemCount < 1){
            location.replace("home?cart");
          }else if(json.statue){
            location.reload();
          }else{
            message = '<div class="bs-callout bs-callout-danger"><h4>System Error</h4>Remove Item Fail</div>';
          }
      }
  };
  var data = JSON.stringify({"productId": productId});
  xhr.send(data);
}
