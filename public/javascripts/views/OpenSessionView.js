var apiKey = "23104692",sessionId,tokenid,session,publisher ;  
define([
  'jquery',
  'jqueryui',
  'underscore',
  'backbone',
  'text!templates/openSessionTemplate.html',
  'msgbox',
  'timepicker',
  'tokbox'
], function($,jqueryUI,_, Backbone,openSessionTemplate,MsgBox,Timepicker,Tokbox){
  var OpenSessionView = Backbone.View.extend({
    el: $(".toppart"),
/*    events: {'click #generateToken' : 'generateToken',
        "change #donate_modal_form :input" :"changedForm2"} ,
*/    render: function(sessionNo){
	
		this.sessionId = sessionNo;//.substring(1,sessionNo.length-1);
		var me = this;
		this.apiKey = "23104692";
    	this.fadeIn(openSessionTemplate, this.$el);
   		 $('.containsAll').onAvailable(function(){
			 $.ajax({
			        type: "GET",
			        url: "/openSession?session="+me.sessionId,
			        success: function(data){
			        	console.log(data.sessionid);
			        	sessionId = data.sessionid;
			        	tokenid=data.token;
			        	me.startSession();
			        }
		    });
			
		 });
    		
    },
    startSession: function(){
    	TB.setLogLevel(TB.DEBUG);
        session = TB.initSession(sessionId);
        session.addEventListener('sessionConnected', sessionConnectedHandler);
        session.addEventListener('streamCreated', streamCreatedHandler);
        session.addEventListener("resize", publisherResizeHandler);
        session.connect(apiKey, tokenid);
    }
  });

  return OpenSessionView;
});

function sessionConnectedHandler(event) {
	var publisherProps = {width: 200, height: 150};
    var publisher = TB.initPublisher(apiKey, 'myself',publisherProps);
    session.publish(publisher);

    // Subscribe to streams that were in the session when we connected
    subscribeToStreams(event.streams);
  }

function streamCreatedHandler(event) {
    // Subscribe to any new streams that are created
    subscribeToStreams(event.streams);
  }

function subscribeToStreams(streams) {
    for (var i = 0; i < streams.length; i++) {
      // Make sure we don't subscribe to ourself
      if (streams[i].connection.connectionId == session.connection.connectionId) {
        return;
      }
      // Subscribe to the stream
  	var subscriberProps = {width: 800, height: 600};

      session.subscribe(streams[i], 'other',subscriberProps);
    }
  }
function publisherResizeHandler(event) {
    newWidth = event.widthTo;
    newHeight = event.heightTo;
    // Modify the UI based on the new dimensions of the publisher 
}