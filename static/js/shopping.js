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
  if (cartElemt != null){

  }else{
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
                  elemt.children[i].innerHTML += '<li id="cart"><a href="cart" class="selected"><span><div class="item-count">' + json.itemCount +'</div> Cart</span></a></li>';
                  }
              }
            }
        }
    };
    var data = JSON.stringify({"storeId": storeId, "productId": productId, "qty": productQty});
    xhr.send(data);

  }
}
