
// 구독 정보 보는 함수
document.querySelector("#subscribeBtn").onclick = (e) => {
  e.preventDefault();
  document.querySelector(".modal-follow").style.display = "flex";
  
  // ajax 통신 후에 json 리턴 -> javaScript 오브젝트 변경 => for문 돌면서 뿌리기
  
  let userId=$("#userId").val();
  
 $.ajax({
  url:`/user/${userId}/follow`,
 }).done((res)=>{
 
  $("#follow_list").empty();
  
  res.data.forEach((u)=>{
    console.log(2,u);
    let item=makeSubscribeInfo(u);
    $("#follow_list").append(item);
  })
 }).fail((error)=>{
  alert("오류 : "+error);
 });
 
 
};


function makeSubscribeInfo(u){
  let item=`<div class="follower__item" id="follow-${u.userId}">`;
  item+=`<div class="follower__img"><img src="/images/profile.jpeg" alt=""></div>`;
  item+=`<div class="follower__text">`;
  item+=`<h2>${u.username}</h2>`;
  item+=`</div>`;
  
  
  if(!u.equalState){
    if(u.followState){
      item+=`<div class="follower__btn"><button class="cta blue" onclick="followOrUnFollow(${u.userId})">구독취소</button></div>`;
    }else{
      item+=`<div class="follower__btn"><button class="cta" onclick="followOrUnFollow(${u.userId})">구독하기</button></div>`;
    }
  }
  item+=`</div>`;
  return item;
}


function followOrUnFollow(userId){

	let text=$(`#follow-${userId} button`).text();
	
	if(text==="구독취소"){
	
		$.ajax({
		type:"delete",
		url:"/follow/"+userId,
		dataType:"json"
	}).done((res)=>{
		$(`#follow-${userId} button`).toggleClass("blue");
		$(`#follow-${userId} button`).text("구독하기");
		
	});
	
	}else{
	
		$.ajax({
		type:"post",
		url:"/follow/"+userId,
		dataType:"json"
	}).done((res)=>{
		$(`#follow-${userId} button`).toggleClass("blue");
		$(`#follow-${userId} button`).text("구독취소");
		
	});
	
	}

	
}

function followOrUnFollow1(userId){

	let text=$(`#follow1-${userId} button`).text();
	
	
	
	if(text==="구독취소"){
	
		$.ajax({
		type:"delete",
		url:"/follow/"+userId,
		dataType:"json"
	}).done((res)=>{
		$(`#follow1-${userId} button`).toggleClass("blue");
		$(`#follow1-${userId} button`).text("구독하기");
		
	});
	
	}else{
	
		$.ajax({
		type:"post",
		url:"/follow/"+userId,
		dataType:"json"
	}).done((res)=>{
		$(`#follow1-${userId} button`).toggleClass("blue");
		$(`#follow1-${userId} button`).text("구독취소");
		
	});
	
	}

	
}




function closeFollow() {
	document.querySelector(".modal-follow").style.display = "none";
}
document.querySelector(".modal-follow").addEventListener("click", (e) => {
	if (e.target.tagName !== "BUTTON") {
		document.querySelector(".modal-follow").style.display = "none";
	}
});
function popup(obj) {
	console.log(obj);
	document.querySelector(obj).style.display = "flex";
}
function closePopup(obj) {
	console.log(2);
	document.querySelector(obj).style.display = "none";
}
document.querySelector(".modal-info").addEventListener("click", (e) => {
	if (e.target.tagName === "DIV") {
		document.querySelector(".modal-info").style.display = "none";
	}
});
document.querySelector(".modal-image").addEventListener("click", (e) => {
	if (e.target.tagName === "DIV") {
		document.querySelector(".modal-image").style.display = "none";
	}
});


/*function clickFollow(e) {
	console.log(e);
	let _btn = e;
	console.log(_btn.textContent);
	if (_btn.textContent === "구독취소") {
		_btn.textContent = "구독하기";
		_btn.style.backgroundColor = "#fff";
		_btn.style.color = "#000";
		_btn.style.border = "1px solid #ddd";
	} else {
		_btn.textContent = "구독취소";
		_btn.style.backgroundColor = "#0095f6";
		_btn.style.color = "#fff";
		_btn.style.border = "0";
	}
}
*/