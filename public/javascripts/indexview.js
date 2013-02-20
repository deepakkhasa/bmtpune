
var indexview = Backbone.View.extend({
	  el: $("#maincontainer"),
	//  template: _.template($('#test').html()),
	 events: {'click #loginbutton' : 'showloginframe' ,'click #schedulelogin' : 'showloginframesch','click #aboutus' : 'aboutus' ,
		 'click #contactus' : 'contactus'  ,'click #toolbardashboard' : 'toolbardashboard','click #knowledge' : 'knowledge',
		 'click #videos': 'videos'} ,

	 initialize: function() {
	        google.setOnLoadCallback(createPicker);
	        google.load('picker', '1');

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
	 },
  	knowledge: function(){
  		var template= _.template($('#knowledge-template').html()) ;
  		$('.mainpart').html(template);
  		$('.toppart').animate({height:'800px'}, 500);
  		picker.setVisible(true);
  	},
  	videos: function(){
		var template= _.template($('#videos-template').html()) ;
  		$('.mainpart').html(template);
  		$('.toppart').animate({height:'800px'}, 500);
  		$("#playContainer").css('padding-left', '40%');
  		//window.open("http://gdata.youtube.com/feeds/users/bmtpune/uploads?alt=json-in-script&callback=showMyVideos2&max-results=30","playerContainer");
  	}
});
var indexview = new indexview();

 var picker =null;
  
// Create and render a Picker object for searching images.
function createPicker() {
  var view = new google.picker.View(google.picker.ViewId.DOCS);
 // view.setMimeTypes("image/png,image/jpeg,image/jpg");    
   picker = new google.picker.PickerBuilder()
      .enableFeature(google.picker.Feature.NAV_HIDDEN)
      .enableFeature(google.picker.Feature.MULTISELECT_ENABLED)
      .setAppId("AIzaSyAo6RTgWtNVDfM-P6XoITkFg3oVzPuKrA4")
  //    .setOAuthToken(AUTH_TOKEN) //Optional: The auth token used in the current Drive API session.
      .addView(view)
      .addView(new google.picker.DocsUploadView())
      .setCallback(pickerCallback)
      .build();
   
}

// A simple callback implementation.
function pickerCallback(data) {
  if (data.action == google.picker.Action.PICKED) {
    //var fileId = data.docs[0].id;
	$('.toppart').animate({height:'1000px'}, 500);
	window.open(data.docs[0].embedUrl,"documentdisplay");
    //alert('The user selected: ' + fileId);
  }
}

function loadVideo(playerUrl, autoplay) {
	  swfobject.embedSWF(
	      playerUrl + '&rel=1&border=0&fs=1&autoplay=' +
	      (autoplay?1:0), 'player', '400', '320', '9.0.0', false,
	      false, {allowfullscreen: 'true'});
	}

	function showMyVideos2(data) {
	  var feed = data.feed;
	  var entries = feed.entry || [];
	  var html = ['<ul class="videos">'];
	  for (var i = 0; i < entries.length; i++) {
	    var entry = entries[i];
	    var title = entry.title.$t.substr(0, 20);
	    var thumbnailUrl = entries[i].media$group.media$thumbnail[0].url;
	    var playerUrl = entries[i].media$group.media$content[0].url;
	    html.push('<li onclick="loadVideo(\'', playerUrl, '\', true)">',
	              '<span class="titlec">', title, '...</span><br /><img src="',
	              thumbnailUrl, '" width="130" height="97"/>', '</span></li>');
	  }
	  html.push('</ul><br style="clear: left;"/>');
	  document.getElementById('videos2').innerHTML = html.join('');
	  if (entries.length > 0) {
	    loadVideo(entries[0].media$group.media$content[0].url, false);
	  }
	}