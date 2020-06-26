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
  if (cartElemt != null){

  }else{
    let index = 0;
    var elemt = document.getElementsByTagName("ol")[0];
    for(var i = 0; i < elemt.childElementCount; i++){
        if(i == index){
        elemt.children[i].innerHTML += '<li id="cart"><a href="cart" class="selected"><span><div class="item-count">1</div> Cart</span></a></li>';
        }
    }
  }
}
