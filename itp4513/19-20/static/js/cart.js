var storeId = document.getElementById("storeId").value;
var xhr = new XMLHttpRequest();
var url = "viewCart";
var json;
xhr.open("GET", url);
xhr.send();
xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
        json = JSON.parse(xhr.responseText);
        console.log(json);
        if(json.statue && json.storeId != null && json.storeId != storeId){
          var size = window.location.href.search("#");
          location.replace("store?id=" + storeId + "#wraning-modal");
        }else if (json.statue && json.storeId == storeId) {
          let index = 0;
          var elemt = document.getElementsByTagName("ol")[0];
          for(var i = 0; i < elemt.childElementCount; i++){
              if(i == index && json.itemCount > 0){
                elemt.children[i].innerHTML += '<li id="cart"><a href="cart" class="selected"><span><div class="item-count">' + json.itemCount +'</div> Cart</span></a></li>';
              }
          }
        }
    }
};

function closeModal(){
  location.replace("home#");
}
