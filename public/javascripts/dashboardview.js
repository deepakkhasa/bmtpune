var dashboardView = Backbone.View.extend({
	
	 events: {  } ,

	 initialize: function() {
	     Backbone.Validation.bind(this,{forceUpdate: true});
	     		var date = new Date();
	     		var d = date.getDate();
	     		var m = date.getMonth();
	     		var y = date.getFullYear();
	     		$('#calendar').fullCalendar({
	     			theme: true,
	     			header: {
	     				left: 'prev,next today',
	     				center: 'title',
	     				right: 'month,agendaWeek,agendaDay'
	     			},
	     			editable: true,
	     			events: [
	     				{
	     					title: 'All Day Event',
	     					start: new Date(y, m, 1)
	     				}
	     			]
	     		});
	     		$(".fc-button-content").click(function() {
	     			if($("#emailid").val() == ""){
	     				$("#errorMsgemailid").text("Email cannot be blank");
	     				$("#errorMsgemailid").css({
	     					"color": "red"
	     				});
	     			}else{
	     				 $.ajax({
	     			        type: "POST",
	     			        url: "/updateDetails",				        
	     			        data: {emailid: $("#emailid").val(), phonenumber: $("#phonenumber").val(), usertype: $("#usertype").val()},
	     			        success: function(data){
	     			        	if(data == "success"){
	     			        		$("#modal_window").trigger('close');
	     			        		alert("Thank you. We have updated your details.");
	     			        	}
     			        	}
	     			    });

	     			}
	     				
	     		});
	     		
	 }
});
var dashboardview = new dashboardView();



$(function(){
	$("#modal_window").lightbox_me({
		centered: true, 
		onLoad: function() {
			$("#modal_window").find("input:first").focus();
		}
	});

});
