var dashboardView = Backbone.View.extend({
	
	 events: { } ,

	 initialize: function() {
	     Backbone.Validation.bind(this,{forceUpdate: true});
			$('#slides').slides({
				preload: true,
				play: 3000,
				pause: 4000,
				container: 'slides_container',
				hoverPause: true,
				generateNextPrev: false,
				pagination: false,
				generatePagination: false,
				slideSpeed: 3000,
				effect: 'slide, fade'
				
			});
				$('#highlights_slides').slides({
					preload: true,
					play: 3000,
					pause: 4000,
					hoverPause: true,
					generateNextPrev: false,
					container: 'highlights_slides_container',
					pagination: false,
					generatePagination: false,
					slideSpeed: 3000,
					effect: 'slide, fade',
					animationStart: function(current){
						$('.caption').animate({
							bottom:-35
						},100);
					},
					animationComplete: function(current){
						$('.caption').animate({
							bottom:50
						},200);
					},
					slidesLoaded: function() {
						$('.caption').animate({
							bottom:50
						},200);
					}
					
					});
	 },

	uploadDialog: function(data) {
		 $("#overlayEffect").fadeIn("slow");
		 $("#popupdetails").fadeIn("slow");

  	}
});
var dashboardview = new dashboardView();



