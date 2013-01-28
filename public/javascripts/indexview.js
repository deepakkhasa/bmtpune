var dashboardView = Backbone.View.extend({
	  el: $("#maincontainer"),
	//  template: _.template($('#test').html()),
	 events: {'click #loginbutton' : 'showloginframe' ,'click #schedulelogin' : 'showloginframesch','click #aboutus' : 'aboutus' ,
		 'click #contactus' : 'contactus'  ,'click #toolbardashboard' : 'toolbardashboard'   } ,

	 initialize: function() {
		 
		 //$('body').jAlert('Welcome to jAlert Demo Page', "success");
		// this.render();
		// document.getElementById('maincontent').src = "data:text/html;charset=utf-8," + escape(");
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
/*	 render: function(){
		 $(this.el).html(this.template());
		 return this;
		 
	 },*/

	uploadDialog: function(data) {
		 $("#overlayEffect").fadeIn("slow");
		 $("#popupdetails").fadeIn("slow");

  	},
  	showloginframe: function(){
  		$('#loginframe').show();
//  		$('.uparrowdiv').toggle();
  		$('#loginframe').css({top:'30px',left:'350px'});
  		
  	},
  	showloginframesch: function(){
  		$('#loginframe').show();
  		$('#loginframe').css({top: '200px',left:'400px'});
  		//$('.uparrowdiv').toggle();
  	},
  	aboutus: function(){
  		var template= _.template($('#aboutus-template').html()) ;
  		$('.mainpart').html(template);
  		$('.toppart').animate({height:'500px'}, 500);
  	},
  	contactus: function(){
  		var template= _.template($('#contactus-template').html()) ;
  		$('.mainpart').html(template);
  		$('.toppart').animate({height:'800px'}, 500);
  	},
	 toolbardashboard: function(){
		 	var template= _.template($('#dashboard-template').html()) ;
	  		$('.mainpart').html(template);
	  		$('.toppart').animate({height:'600px'}, 500);
	 }
});
var dashboardview = new dashboardView();




