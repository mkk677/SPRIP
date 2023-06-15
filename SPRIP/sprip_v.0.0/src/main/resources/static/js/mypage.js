function initMap(){
	  		
	const map = new google.maps.Map(
				document.getElementById("hiddenmap"),{
                    center : {lat:37.5400456, lng:126.9921017},
					zoom : 15
				}
			)
	const service = new google.maps.places.PlacesService(map);
	
 	let hotels = document.querySelectorAll(".reservation_card_hotelimg");
 	
	hotels.forEach( (hotel) => {
		let id = hotel.getAttribute("alt");
		let src;
		service.getDetails({placeId : id} ,function(place,status){
			if (status !== google.maps.places.PlacesServiceStatus.OK) return;
			if(place.photos == null){
				src = "../img/noimage.png"
				hotel.setAttribute("src", src);
			}else{
				src = place.photos[0].getUrl({maxWidth: 250, maxHeight: 250});
				hotel.setAttribute("src", src);
			}
		})  
		
	});
	
}

function ticketCancel(ticketid){
	
    if(confirm("정말 예약을 취소하시겠습니까?")) {
        alert("예약을 취소합니다.");
		location.href = '/reservation/ticket/cancel/'+ticketid
    } else {
		return;
    }
} 

function hotelCancel(hotelid){
	
    if(confirm("정말 예약을 취소하시겠습니까?")) {
        alert("예약을 취소합니다.");
		location.href = '/reservation/hotel/cancel/'+hotelid
    } else {
		return;
    }
} 