function initMap(){
	  		
	const map = new google.maps.Map(
				document.getElementById("hiddenmap"),{
                    center : {lat:37.5400456, lng:126.9921017},
					zoom : 15
				}
			)
	const service = new google.maps.places.PlacesService(map);
	
	let spots = document.querySelectorAll(".spotcard_img");
	
	spots.forEach( (spot) => {
		let city = spot.getAttribute("alt");
		let src;
		
		let request = {
	        query : city,
	    }
	    
	    service.textSearch(request,searchimg); 	
 
		function searchimg(results, status){
            if (status == google.maps.places.PlacesServiceStatus.OK) {
                var place = results[0];
				if(place.photos == null){
					src = "../img/noimage.png"
					hotel.setAttribute("src", src);
				}else{
					src = place.photos[0].getUrl({maxWidth: 250, maxHeight: 250});
					spot.setAttribute("src", src);
				}
            }
        }
	});
	
}
 
function gotoTicket(finishpoint){
	location.href = '/reservation/plane?finishpoint='+finishpoint
}

function gotoHotel(finishpoint){
	location.href = '/reservation/hotel?finishpoint='+finishpoint
}