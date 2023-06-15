/**
 * 
 */

 // sidebar 시작
$(document).ready(function(){
var currentPosition = parseInt($(".quickmenu").css("top"));
$(window).scroll(function() {
var position = $(window).scrollTop(); 
$(".quickmenu").stop().animate({"top":position+currentPosition+"px"},1000);
});
});
    // sidebar 종료

 // Carousel 시작  ( 지정 범위까지만 슬라이드 )
//  var emblaNode = document.querySelector(".home_list_container");
//         var options = { loop: false };
      
//         var embla = EmblaCarousel(emblaNode, options);

// Carousel 종료

 // Carousel 시작 ( 처음 부터 끝까지 돌고나면 다시 처음부터 이어서 )

        window.addEventListener('DOMContentLoaded', function() {
  var emblaNode = document.querySelector(".home_list_container");
  var options = { loop: true }; // loop 옵션을 true로 변경
  var embla = EmblaCarousel(emblaNode, options);
});
// Carousel 종료