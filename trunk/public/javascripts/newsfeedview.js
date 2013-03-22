var newsfeedView = Backbone.View.extend({
	 el: $(".mainpart"),
	 events: {'click #newsfeedarea a ' : 'showUserDetails'} ,
	 initialize: function() {

	        this.render();
	        
	 },
	 render: function () {
		 	this.template=_.template($('#myfeed-template').html());
		    $("#recentactivities").html(this.template);
	        $('#recentactivities').slimScroll({
	            height: '100%',
	            disableFadeOut: false,
	           // width:'75%',
	            railVisible: true,
	            alwaysVisible: false
	        });
	        $('#recentactivities').slimScroll().bind('slimscroll', function(event, pos){
	            if (pos == 'bottom') {
	               // alert();
	            }
	        }); 
	        var myProfile = {
	        	    "name": "Dr. Vijay Ramanan",
	        	    "image": "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-ash4/c170.50.621.621/s160x160/306264_10151378437603439_1351471632_n.jpg", 
	        	    "link": "http://vijay.cloudfoundry.com/", 
	        	    "bio": "Leading Hematologist in central india.",
	        	    "website": "http://vijay.cloudfoundry.com/", 
	        	    "email": "mvijayr@gmail.com"
	        	}
	        $('.doctor').hovercard({
	            showCustomCard: true, 
	            customCardJSON: myProfile
	        });
		    return this;
		},
	 showUserDetails: function(){
		 alert("hi");
			$("#modal_window").lightbox_me({
				centered: true, 
				onLoad: function() {
					$("#modal_window").find("input:first").focus();
				}
			});

	 }
	 
});
var newsfeedview = new newsfeedView();


function rssfeedright(){
	var feedpointer=new google.feeds.Feed(feedurl);
	feedpointer.setNumEntries(feedlimit) ;
	feedpointer.load(displayfeedright) ;
}

function displayfeedright(result){
	var rssoutput1="<ul>";
	if (!result.error){
		var thefeeds=result.feed.entries;
		var text;
		for (var i=0; i<thefeeds.length; i++){
			text =thefeeds[i].title.length> 52?thefeeds[i].title.slice(0,50)+"..":thefeeds[i].title;
			rssoutput1+="<li><a target=\"_blank\" href='" + thefeeds[i].link + "' title=\""+thefeeds[i].title+"\">" + text + "</a></li>";
		}
			
		rssoutput1+="</ul>";
		$('#rightnewsdiv').html(rssoutput1);

	}
}

function showAppointment(date){
	alert(date);
}