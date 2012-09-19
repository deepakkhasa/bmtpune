var dashboardView = Backbone.View.extend({
	
	 events: {  } ,

	 initialize: function() {
	     Backbone.Validation.bind(this,{forceUpdate: true});
		 $.ajax({
		        type: "GET",
		        url: "/getMyAppointments",
		        success: function(data){
		        	showCalendar(data);
		   		 $('[class="fc-event-inner fc-event-skin"]').click(function() {
						//alert($(this).attr('id'));
		   			 	showDetails($(this).attr('id'));
					});

		        }
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

function showDetails(appointmentId){
	 $.ajax({
	        type: "GET",
	        url: "/getAppointmentDetails",
	        data:{id: appointmentId},
	        success: function(data){
	        	$("#appointmentdescription").val(data.appointmentHeadline);
	        	$("#appointmentcomments").val(data.appointmentComment);
	        	var date = new Date(data.dateOfAppointment);
	        	var month = date.getMonth() + 1;
	        	var day = date.getDate();
	        	var year =date.getFullYear(); 
	        	$("#appointmentdate").val(month+"/"+day+"/"+year);
	        	$("#appointmenttime").val(data.appointmentTime);
	        	$("#modal_window_appt").lightbox_me({
	        		centered: true, 
	        		onLoad: function() {
	        			$("#modal_window_appt").find("input:first").focus();
	        		}
	        	});

	        }
	    });
	
}
function showCalendar(data){
		var event =[];
		for(var i in data){
			var appment = data[i];
			var date = new Date(appment.dateOfAppointment);
	 		var d = date.getDate();
	 		var m = date.getMonth();
	 		var y = date.getFullYear();
	 		var endDate = new Date();
	 		var hour=((appment.appointmentTime.substring(0,2)*1) + (appment.appointmentDuration.substring(0,2)*1));
	 		var min=((appment.appointmentTime.substring(3,5)*1)+(appment.appointmentDuration.substring(3,5)*1));
	 		endDate.setHours(hour);
	 		endDate.setMinutes(min);
	 		event[i] = {title: appment.appointmentHeadline ,start: new Date(y,m,d,appment.appointmentTime.substring(0,2),appment.appointmentTime.substring(3,5)), end: new Date(y,m,d,endDate.getHours(),endDate.getMinutes()),allDay: false,aid: appment.id};		 		
		}
 		$('#calendar').fullCalendar({
 			theme: true,
 			header: {
 				left: 'prev,next today',
 				center: 'title',
 				right: 'month,agendaWeek,agendaDay'
 			},
 			editable: true,
 			disableDragging: true,
 			events: event
 		});

}

