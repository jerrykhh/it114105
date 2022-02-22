var xhr = new XMLHttpRequest();
var url = "viewCart";
var json;
var storeId;
xhr.open("GET", url);
xhr.send();
xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
        json = JSON.parse(xhr.responseText);
        console.log(json);
        if(json.statue && json.itemCount > 0){
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

function viewStore(id){
  //location.replace("store?id=" + id);
  storeId = id;
  if(json.storeId != id && json.storeId != null){
    location.replace("home#wraning-modal");
  }else{
    location.replace("store?id=" + id);
  }
}

function closeModal(){
  location.replace("home#");
}

function clearCart(){
  var xhr = new XMLHttpRequest();
  var url = "removeInCart";
  xhr.open("POST", url, true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.onreadystatechange = function () {
      if (xhr.readyState === 4 && xhr.status === 200) {
          var json = JSON.parse(xhr.responseText);
          if(json.statue == true){
            location.replace("store?id=" + storeId);
          }
      }
  };
  var data = JSON.stringify({"action": "remove"});
  xhr.send(data);
}
