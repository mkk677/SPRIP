let aircb = document.querySelectorAll(".air_checkbox");
let planecards = document.querySelectorAll(".plane_selected_card");

window.onpageshow = function(event) {
    if ( event.persisted || (window.performance && window.performance.navigation.type == 2)) {
        aircb.forEach((cb)=>{
			cb.checked = true;
		})
    }
}
// 카드 airid, 체크박스 airid 비교해서 항공사 필터
aircb.forEach((cb)=>{
	cb.addEventListener('change', function(){
		planecards.forEach((card)=>{
			if(cb.value == card.getAttribute("data-airid")){
				if (card.style.display === "none") {
				    card.style.display = "block";
				} else {
				    card.style.display = "none";
				}
			}
		});
	});
});

// 항공기 timepicker
$(document).ready(function () {
	$('input.planelist_time').timepicker({
            timeFormat: 'HH:mm',
            interval: 30,
            startTime: '00:00',
            dynamic : false,
            change : timefilter
        });
}); 

let db = $('#depart_begin');	// 출발시각 db 이후
let de = $('#depart_end');		// 출발시각 de 이전
let ab = $('#arrive_begin');	// 도착시각 ab 이후
let ae = $('#arrive_end');		// 도착시각 ae 이전 

function timefilter(){
	// db가 de보다 작아야 함
	if( db.val() > de.val() ){
		de.val('');
	};
	// ab가 ae보다 작아야 함
	if( ab.val() > ae.val() ){
		ae.val('');
	};
	// String time으로 변경해서 card, input 비교 처리 
	planecards.forEach((card)=>{
		let card_dt = Date.parse("2020/01/01 " + card.getAttribute("data-depart") + ":00");
		let card_at = Date.parse("2020/01/01 " + card.getAttribute("data-arrive") + ":00");
		let db_time = Date.parse("2020/01/01 " + db.val() + ":00");
		let de_time = Date.parse("2020/01/01 " + de.val() + ":00");
		let ab_time = Date.parse("2020/01/01 " + ab.val() + ":00");
		let ae_time = Date.parse("2020/01/01 " + ae.val() + ":00");
		if(card_dt < db_time || card_dt > de_time || card_at < ab_time || card_at > ae_time ){
		    card.style.display = "none";
		}else{
			card.style.display = "block";
		} 
	});
}