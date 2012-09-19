var takeappointmentView = Backbone.View.extend({
	
	 events: { } ,

	 initialize: function() {
	    Backbone.Validation.bind(this,{forceUpdate: true});
		 $.ajax({
		        type: "GET",
		        url: "/getDoctors",
		        success: function(data){
//		        	for(var i in data){
//		        		alert(data.length + " "+ i);
//		        	}
		        	if(data.length == 1){
		        		$("#heading").text("For booking appointment with " + data[0].name + " please click on the date when you want it.");
		        		getCalendarEvents(data[0].id);
		        	}
		        }
		    });

	 }
	 });
var takeappointmentview = new takeappointmentView();

function getCalendarEvents(doctorId){
	 $.ajax({
	        type: "GET",
	        url: "/getDoctorAppointments?doctorId=" + doctorId,
	        success: function(data){
	        	showCalendar(data,doctorId);
	        }
	    });

}
function showDoctorPopUp(doctorList){
    $("#modal_window_doc").lightbox_me({centered: true, onLoad: function() {
		$("#modal_window_doc").find("select:first").focus();
		}, onClose: function(){
			alert("You will now be taken to calendar for "+ $("#doctorname").text() +" Speciality: "+ $("#speciality").val());
	
		}
    });
    
}

function showCalendar(data,doctorId){
 		$("#doctorId").val(doctorId);
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
 			aid: 1,
 			header: {
 				left: 'prev,next today',
 				center: 'title',
 				right: 'month,agendaWeek,agendaDay'
 			},
 			editable: true,
 			disableDragging: true,
 			events: event
 		});
  		 $('[class="fc-event-inner fc-event-skin"]').click(function() {
				alert();
			});


}


