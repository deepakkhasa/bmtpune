var indexview = Backbone.View.extend({
	  el: $("#maincontainer"),
	//  template: _.template($('#test').html()),
	 events: {'click #loginbutton' : 'showloginframe' ,'click #schedulelogin' : 'showloginframesch','click #aboutus' : 'aboutus' ,
		 'click #contactus' : 'contactus'  ,'click #toolbardashboard' : 'toolbardashboard','click #knowledge' : 'knowledge',
		 'click #videos': 'videos', 'click #show-documents':'showDocuments', 'click .control':'navigate'} ,

	 initialize: function() {
		// rssfeedsetup();
	//        google.setOnLoadCallback(createPicker);
	//        google.load('picker', '1');

		 //$('body').jAlert('Welcome to jAlert Demo Page', "success");
		// this.render();
		// document.getElementById('maincontent').src = "data:text/html;charset=utf-8," + escape(");
	  		$('#loginframe').load("/login");

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
  		$('#loginframe').css({top:'40px',left:'1100px'});
  		
  	},
  	showloginframesch: function(){  
  		if($('.fixednav').length){
  			$('.mainpart').html("<div id='calendar'></div>");
  			
  			showCalendar(docData,docId);
  		}else{
  	  		$('#loginframe').css({top: '177px',left:'200px'});
  	  		$('#loginframe').show();
  		}
  		//$('.uparrowdiv').toggle();
  	},
  	aboutus: function(){
  		$('#loginframe').hide();
  		var template= _.template($('#aboutus-template').html()) ;
  		$('.mainpart').html(template);
  		$('.toppart').animate({height:'500px'}, 500);
  	},
  	contactus: function(){
  		$('#loginframe').hide();
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
  		$(".background").mask("Loading...");
  		var template= _.template($('#knowledge-template').html()) ;
  		$('.mainpart').html(template);
  		$('.toppart').animate({height:'800px'}, 500);
  		//picker.setVisible(true);
		 $.ajax({
		        type: "GET",
		        url: "/knowledgecenter",
		        success: function(data){
		        	var documentNames="<div class=\"knowledgediv\"><h1>Featured</h1></div><table class=\"knowledgetable\">";
		        	var row=0;
		        	var totalrows=1;
		        	for(var i=0;i< data.length;i++){
		        		if(row ==0){
		        			documentNames = documentNames+"<tr>";
		        		}	
		        		documentNames = documentNames+'<td width="15%">'+'<a href="#"  onClick="loadDocument('+data[i].documentId+',\''+data[i].accessKey+'\')">'+'<img src="'+data[i].thumbNailURI+'"></a></td><td width="35%"><div><h2><a href="#"  onClick="loadDocument('+data[i].documentId+',\''+data[i].accessKey+'\')">'+data[i].title+'</a></h2><div>'+data[i].description+'</div></div></td>';
		        		row=row+1;
		        		if(row == 2){
		        			documentNames = documentNames+"</tr>";
		        			row=0;
		        			totalrows=totalrows+1;
		        		}
		        		
		        	}
		        	documentNames = documentNames+'</table>';		        	
		        	totalrows=totalrows*180;
		        	$('.toppart').animate({height:totalrows+'px'}, 500);
		        	$('#nav-menu').html(documentNames);
		        	$(".background").unmask();
		        }
		    });
  	},
  	videos: function(){
		var template= _.template($('#videos-template').html()) ;
  		$('.mainpart').html(template);
  		$('.toppart').animate({height:'800px'}, 500);
  		$("#playContainer").css('padding-left', '40%');
  		//window.open("http://gdata.youtube.com/feeds/users/bmtpune/uploads?alt=json-in-script&callback=showMyVideos2&max-results=30","playerContainer");
  	},
  	showDocuments: function(){
  		$('#nav-menu').show();
  		$('#show-documents').hide();
  		$('#documentdisplay').hide();
  	},
  	navigate: function(e){
  		var x = e.pageX - $(e.currentTarget).offset().left;
  		var y = e.pageY - $(e.currentTarget).offset().top;
  		if(x > 0 && x< 100){
  			if(y >0 && y < 70){
  				this.showloginframesch();
  			$(".leftarrowdiv").css({top:'180px',left:'198px','z-index':'999'});
  				$(".leftarrowdiv").show();
  			}
  			if(y >70 && y < 150){
  				if(!userType){
  	  				this.videos();
  	  				$('#loginframe').hide();
  				}
  			$(".leftarrowdiv").css({top:'255px',left:'198px','z-index':'999'});
  				$(".leftarrowdiv").show();
  			}
  			if(y >150 && y < 240){
  				if(!userType){
  	  				this.knowledge(); 
  	  				$('#loginframe').hide();
  				}
  			$(".leftarrowdiv").css({top:'340px',left:'198px','z-index':'999'});
  				$(".leftarrowdiv").show();
  			}
  			if(y >240 && y < 330){
  				if(!userType){
	      		 $.msgBox({
					    title:"Yashoda Clinic",
					    content:"Coming soon!",
					    type:"info"
					});
	      		 
  				$('#loginframe').hide();
  				}
  				if(userType){
  					this.knowledge(); 
  				}
  			$(".leftarrowdiv").css({top:'425px',left:'198px','z-index':'999'});
  				$(".leftarrowdiv").show();
  			}
  			if(y >330 && y < 430){
/*  				$('.toppart').animate({height:'600px'}, 500);
  				$('.mainpart').animate({height:'600px'}, 500);
  		  		$('.mainpart').html('<div class="prevdiv"></div><image class="testimony" src="/assets/images/Testimony_1.jpg"><div class="nextdiv">');
  		  		$('.prevdiv').click(function(){});
  		  	$('.nextdiv').click(function(){});
*/
  				if(!userType){
					var template= _.template($('#testimonial-template').html()) ;
			  		$('.mainpart').html(template);
		  			$('#loginframe').hide();
  				}
 				if(userType){
  					this.videos(); 
  				}
  			$(".leftarrowdiv").css({top:'515px',left:'198px','z-index':'999'});
  				$(".leftarrowdiv").show();
  				
  			}
  			if(y >430 && y < 530){
//				this.knowledge();
				$('#loginframe').hide();
	      		 $.msgBox({
					    title:"Yashoda Clinic",
					    content:"Coming soon!",
					    type:"info"
					});

			$(".leftarrowdiv").css({top:'600px',left:'198px','z-index':'999'});
				$(".leftarrowdiv").show();
			}

  			/*  			if(y >470 && y < 570){
  				//this.knowledge();
  				$('#loginframe').hide();
  			$(".leftarrowdiv").css({top:'650px',left:'175px','z-index':'999'});
  				$(".leftarrowdiv").show();
  			}
*/
  		}
  	}
});
var indexview = new indexview();
function loadDocument(docId,accessKey){
	$('#nav-menu').hide();
	$('#show-documents').show();
	$('#documentdisplay').show();
	 var scribd_doc = scribd.Document.getDoc(docId, accessKey);
	 scribd_doc.write('documentdisplay');
}
 var picker =null;
  
/*// Create and render a Picker object for searching images.
function createPicker() {
  var view = new google.picker.View(google.picker.ViewId.DOCS);
  var docview=new google.picker.DocsView();
  docview.setMode(google.picker.DocsViewMode.LIST);
  docview.setOwnedByMe(false);
 // view.setMimeTypes("image/png,image/jpeg,image/jpg");    
   picker = new google.picker.PickerBuilder()
      .enableFeature(google.picker.Feature.NAV_HIDDEN)
  //    .enableFeature(google.picker.Feature.MULTISELECT_ENABLED)
      .setAppId("AIzaSyAo6RTgWtNVDfM-P6XoITkFg3oVzPuKrA4")
      .setOAuthToken("ya29.AHES6ZST7HuyH1fBapao2O-iVAs_GKnsMm_P55Qk6-ARywk") //Optional: The auth token used in the current Drive API session.
     // .addView(view)
      .addView(docview)
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
}*/

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
	var feedcontainer="";
	var feedurl="http://www.medicalnewstoday.com/rss/blood.xml";
	var feedlimit=10;
	var rssoutput="<ul>";


	function rssfeedsetup(){
		var feedpointer=new google.feeds.Feed(feedurl);
		feedpointer.setNumEntries(feedlimit) ;
		feedpointer.load(displayfeed) ;
	}

	function displayfeed(result){
		if (!result.error){
			var thefeeds=result.feed.entries;
			var text;
			for (var i=0; i<thefeeds.length; i++){
				text =thefeeds[i].title.length> 52?thefeeds[i].title.slice(0,50)+"..":thefeeds[i].title;
				rssoutput+="<li><a target=\"_blank\" href='" + thefeeds[i].link + "' title=\""+thefeeds[i].title+"\">" + text + "</a></li>";
			}
				
			rssoutput+="</ul>";
			feedcontainer=feedcontainer+rssoutput;
			$.jGrowl(feedcontainer, { header: '<a href="#" onclick="minimize();">Latest Medical News</a>', sticky: true,position:'bottom-right' },{ beforeClose: function(e,m) {return;}});
			//$(".jGrowl-header").click(function(){alert();});
		}
	}
function minimize(){
	$(".jGrowl-message").toggle();
}
	window.onload=function(){
		if(!userName)
		rssfeedsetup();
		else
			rssfeedright();
		}

var userType;