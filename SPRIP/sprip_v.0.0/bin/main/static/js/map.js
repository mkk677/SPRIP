
// 오늘 날짜, 오늘 날짜 String
let now = new Date();
let today = now.getFullYear()+"-"+(("0"+(now.getMonth()+1).toString()).slice(-2))+"-"+(("0"+now.getDate().toString()).slice(-2));
let weeklater = now.getFullYear()+"-"+(("0"+(now.getMonth()+1).toString()).slice(-2))+"-"+(("0"+(now.getDate()+6).toString()).slice(-2));

// 예약 시 오늘 날짜 이후로만 가능하게 처리
let dates = document.querySelectorAll(".hotel_date, .plane_date");
dates.forEach( (date) => {
    date.addEventListener('focus', dateFocus)
});
function dateFocus(e){
    this.type='date';
    this.setAttribute("min",today);
}

// 여행지 선택 박스 클릭시마다 값 비워주기 
let ticketSpots = document.querySelectorAll(".ticket_spot");
let startpoint = document.getElementById("startpoint");
let finishpoint = document.getElementById("finishpoint");

ticketSpots.forEach( (spot) => {
    spot.addEventListener('focus', function(){
		this.value = '';
	}) 
});

// 출발지와 도착지를 다르게
ticketSpots.forEach( (spot) => {
    spot.addEventListener('change', function(){
		if(startpoint.value == finishpoint.value){
			alert("출발지와 도착지는 동일할 수 없습니다.");
			this.value = '';
		}
	}) 
});

// 편도티켓 선택시 도착 날짜 선택을 막음
let roundOrNot = document.getElementById("roundornot");
let ticketDates = document.querySelectorAll(".plane_date");
let startDate = document.getElementById("startdate");
let finishDate = document.getElementById("finishdate");

if(roundOrNot.value != 'ROUND'){
	finishDate.setAttribute("readonly",true);
	finishDate.value = startDate.value;
}
	
roundOrNot.addEventListener("change",function(){
	if(roundOrNot.value != 'ROUND'){
		finishDate.setAttribute("readonly",true);
		finishDate.value = startDate.value;
	}else{
		finishDate.removeAttribute("readonly");
		finishDate.value = '';
	}
})

 if(startDate.value == null || startDate.value == ''){
	 startDate.value = today;
 }
 
 ticketDates.forEach( (date) => {
    date.addEventListener('change', function(){
		if(finishDate.value != null && finishDate.value != '' && startDate.value > finishDate.value){
			alert("오는 날짜는 가는 날짜보다 이전일 수 없습니다.");
			this.value = '';
		}
	})
});

function ticket_form_submit(airid, depart, duration, arrive, dayplus ){
	let form = document.getElementById("plane_form")	
	let airid_ = document.getElementById("airid")	
	let depart_ = document.getElementById("depart")	
	let duration_ = document.getElementById("duration")	
	let arrive_ = document.getElementById("arrive")	
	let dayplus_ = document.getElementById("dayplus")	
	
	airid_.value = airid;
	depart_.value = depart;
	duration_.value = duration;
	arrive_.value = arrive;
	dayplus_.value = dayplus;
	
	form.setAttribute("action","./payment")
	form.submit();
}



function initMap(){
    // 지도 생성 -------------------------
  		const map = new google.maps.Map(
  					document.getElementById("map"),{
                        center : {lat:37.5400456, lng:126.9921017},
  						zoom : 15
  					}
  				)
        const service = new google.maps.places.PlacesService(map);
        let infoWindow = new google.maps.InfoWindow();
        let request;
        let center;
        let bounds;
        let markers = [];
    // 검색상자 -------------------------
        const input = document.getElementById("pac-input")   
        const placeSearch = document.getElementById("placeSearch")   
        const searchBox = new google.maps.places.SearchBox(input);
        map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
        map.controls[google.maps.ControlPosition.TOP_RIGHT].push(placeSearch);
        map.addListener("bounds_changed", () => {
            searchBox.setBounds(map.getBounds());
        });      
        searchBox.addListener("places_changed", function(){
            const place = searchBox.getPlaces()[0];
            map.setCenter(place.geometry.location);
            //searchMarker(place);
            request = {
                query : 'lodging',
                fields : ['place_id', 'geometry'],
                radius : 50,
                location : place.geometry.location,
            }
            service.textSearch(request,searchHotels);
        })
        placeSearch.addEventListener("click",function(){
            request = {
                query : '숙소',
                fields : ['place_id', 'geometry'],
                radius : 50,
                location : map.getCenter(),
            }
            service.textSearch(request,searchHotels);
        })
    // 검색한 위치 주변 검색
        function searchHotels(results, status){
            if (status == google.maps.places.PlacesServiceStatus.OK) {
                bounds = new google.maps.LatLngBounds(); 
                for(var i =0; i<markers.length;i++){
                    markers[i].setMap(null);
                }
                for (var i = 0; i < results.length; i++) {
                    var place = results[i];
                    service.getDetails({placeId : place.place_id} ,function(place,status){
                        if (status !== google.maps.places.PlacesServiceStatus.OK) return;
                        hotelMarker(place,i);
                    })
                    bounds.extend(place.geometry.location)
                }
                map.fitBounds(bounds);
            }
        }
    // 검색한 위치 마커
        const svgMarker = {
            path: "M16 144a144 144 0 1 1 288 0A144 144 0 1 1 16 144zM160 80c8.8 0 16-7.2 16-16s-7.2-16-16-16c-53 0-96 43-96 96c0 8.8 7.2 16 16 16s16-7.2 16-16c0-35.3 28.7-64 64-64zM128 480V317.1c10.4 1.9 21.1 2.9 32 2.9s21.6-1 32-2.9V480c0 17.7-14.3 32-32 32s-32-14.3-32-32z",
            fillColor: "blue",
            fillOpacity: 1,
            strokeWeight: 0,
            rotation: 0,
            scale: 0.05,
            anchor: new google.maps.Point(0, 0),
        };
        function searchMarker(place, i){
            if (!place.geometry || !place.geometry.location) return;
            marker[i] = new google.maps.Marker({
                map,
                position: place.geometry.location,
                title: place.name,
                icon : svgMarker
            });
        }
    // 찾은 숙소 마커
        function hotelMarker(place,i){
            if (!place.geometry || !place.geometry.location) return;
            const marker = new google.maps.Marker({
                map,
                position: place.geometry.location,
                title: place.name,
            });
            markers.push(marker);
            marker.addListener("click",function(){
                infoWindow.setContent(createWindow(place));
                infoWindow.open({anchor : marker, map});
                window.onload=function(){
                    map.setCenter(infoWindow.getPosition());
                }
            })
        }
    // infoWindow 정보
        function createWindow(place){
            let url;
            if(place.photos == null){
                url = "../img/noimage.png"
            }else{
                url = place.photos[0].getUrl({maxWidth: 250, maxHeight: 250});
            }
            let name = place.name; // hotelname
            let addr = place.formatted_address; // addr
            let phone   = place.formatted_phone_number; // hotelphone
            let rate = place.rating; // 테이블에는 없음
            let id = place.place_id; // hotelplaceid 
            let percent = place.rating * 20;
            if(typeof phone == "undefined" || phone == null || phone == ""){
                phone="전화번호 정보 없음"
            } 
            let temp_html = 
                `<div class="infoWindow"><img src="${url}">
                    <div class="infoWindow_detail">
                        <h3>${name}</h3>
                        <p>${addr}</p><p>${phone}</p>
                        <div class="infoWindow_stars">
                            <p class="infoWindow_rate">평점 : ${rate}<p>
                            <div class="star-ratings">
                                <div class="star-ratings-fill space-x-2 text-lg" style="width:${percent}%">
                                    <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
                                </div>
                                <div class="star-ratings-base space-x-2 text-lg">
                                    <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
                                </div>
                            </div>
                        </div>
                        <button class="color_button" onclick="addCard('${name}','${url}','${addr}','${phone}','${id}')">
                        이 호텔 예약하기</button>
                    </div>
                </div>`
            return temp_html;
        }
        
		const urlParams = new URL(location.href).searchParams;
		const finishpoint = urlParams.get('finishpoint');
		if(finishpoint != null && finishpoint != ''){
			
			let paramrequest = {
	        	query : finishpoint,
	    	}
	    	service.textSearch(paramrequest,function(results, status){
			    if (status == google.maps.places.PlacesServiceStatus.OK) {
					var place = results[0];
			    	let request = {
						query : '숙소',
		                fields : ['place_id', 'geometry'],
		                radius : 50,
		                location : place.geometry.location,
					}
					service.textSearch(request,searchHotels);
			    }				
			});
		}
    }

function addCard(name,url,addr,phone,id){
    
    let carddiv = document.getElementById("hotel_card");
    let reservenum = document.getElementById("reservenum_").value;
    let startdate = document.getElementById("startdate_").value;
    let finishdate = document.getElementById("finishdate_").value;
    let startdate_Date = new Date(startdate);
    let finishdate_Date = new Date(finishdate);
    let nights = (finishdate_Date - startdate_Date)/(1000*60*60*24);
    let room_type;
    let add_person = 0;
    let hotelprice;
    let paymentprice;

    if(reservenum == null || reservenum == ''){
        alert("예약 인원을 선택해 주세요.");
        return false;
    }
    if(startdate == null || startdate == '' || finishdate == null || finishdate == ''){
        alert("체크인 / 체크아웃 날짜를 선택해 주세요.");
        return false;
    }
    if(startdate >= finishdate){
        alert("최소 1박 이상으로 선택해 주세요.");
        return false;
    }

    if(reservenum < 4){
        room_type = '스탠다드 더블';
        if(reservenum > 2){
            add_person = reservenum -2;
        };
        hotelprice = 90000;
        paymentprice = (hotelprice + add_person * 20000) * nights;
    }else{
        room_type = '패밀리 디럭스';
        if(reservenum > 4){
            add_person = reservenum -4;
        }
        hotelprice = 150000;
        paymentprice = (hotelprice + add_person * 20000) * nights;
    }

    
    if(add_person != 0){
        carddiv.innerHTML = 
            `<div class="hotel_card">
                <img src="${url}">
                <div class="hotel_card_detail">
                    <p><label>장소 : </label><span>${name}</span></p>
                    <p><label>예약 인원 : </label><span>${reservenum}</span></p>
                    <p><label>체크인 : </label><span>${startdate}</span></p>
                    <p><label>체크아웃 : </label><span>${finishdate}</span></p>
                    <p><label>가격 : </label><span>${room_type}</span> <span>${hotelprice}</span> + 
                    <span>${add_person}인 추가 ${20000 * add_person}<span> / *${nights}박</span> </p>
                </div>
                <div class="hotel_card_price">
                    <p>결제 가격</p>
                    <h3><span style="color:#00BEA7;">${paymentprice.toLocaleString('ko-KR')}</span>원</h3><br>
	                <button class="color_button" onclick="reserveHotel('${addr}','${name}','${phone}','${id}',
	                '${hotelprice}','${reservenum}','${startdate}','${finishdate}','${paymentprice}')">
	                예약하기</button>
                </div>
            </div>`
    }else{
        carddiv.innerHTML = 
        `<div class="hotel_card">
            <img src="${url}">
            <div class="hotel_card_detail">
                <p><label>장소 : </label><span>${name}</span></p>
                <p><label>예약 인원 : </label><span>${reservenum}</span></p>
                <p><label>체크인 : </label><span>${startdate}</span></p>
                <p><label>체크아웃 : </label><span>${finishdate}</span></p>
                <p><label>가격 : </label><span>${room_type}</span> <span>${hotelprice}</span> / *${nights}박</span> </p>
            </div>
            <div class="hotel_card_price">
                <p>결제 가격</p>
                <h3><span style="color:#00BEA7;">${paymentprice.toLocaleString('ko-KR')}</span>원</h3><br>
                <button class="color_button" onclick="reserveHotel('${addr}','${name}','${phone}','${id}',
                '${hotelprice}','${reservenum}','${startdate}','${finishdate}','${paymentprice}')">
                예약하기</button>
            </div>
        </div>`
    }
}

// 호텔 hidden form에 값 넣어주고 submit
function reserveHotel(addr, hotelname, hotelphone, hotelplaceid,  
	hotelprice, reservenum, startdate, finishdate, paymentprice){
	const form = document.getElementById("hotel_form");
	let addr_form = document.getElementById("addr");
	let hotelname_form = document.getElementById("hotelname");
	let hotelprice_form = document.getElementById("hotelprice");
	let hotelphone_form = document.getElementById("hotelphone");
	let hotelplaceid_form = document.getElementById("hotelplaceid");
	let reservenum_form = document.getElementById("reservenum");
	let reservedate_form = document.getElementById("reservedate");
	let startdate_form = document.getElementById("startdate");
	let finishdate_form = document.getElementById("finishdate");
	let paymentprice_form = document.getElementById("paymentprice");
 	
 	console.log(reservenum, startdate,finishdate)
	addr_form.value = addr;
	hotelname_form.value = hotelname;
	hotelprice_form.value = hotelprice;
	hotelphone_form.value = hotelphone;
	hotelplaceid_form.value = hotelplaceid;
	reservenum_form.value = reservenum;
	reservedate_form.value = today;
	startdate_form.value = startdate;
	finishdate_form.value = finishdate;
	paymentprice_form.value = paymentprice;
	
	form.submit();
}

