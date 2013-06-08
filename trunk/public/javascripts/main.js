require.config({
  paths: {
    jquery: 'https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min',
    jqueryui: 'http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min',
    underscore: '/assets/javascripts/underscore-min',
    backbone: '/assets/javascripts/backbone-min',
    bxslider: '/assets/javascripts/jquery.bxslider.min',
    verimail: '/assets/javascripts/verimail.jquery.min',
    swobject: "http://swfobject.googlecode.com/svn/trunk/swfobject/swfobject",
    templates: '../templates',
    lightboxme: '/assets/javascripts/jquery.lightbox_me',
    msgbox: '/assets/javascripts/jquery.msgBox',
    timepicker: '/assets/javascripts/jquery.timePicker.min',
    tokbox:'https://swww.tokbox.com/v1.1/js/TB.min'

  }

});

define([
  // Load our app module and pass it to our definition function
  'app',
  'jquery',
  'backbone',
  'underscore',
  'swobject',
  'tokbox'
], function(App){
  // The "app" dependency is passed in as "App"
  // Again, the other dependencies passed in are not "AMD" therefore don't pass a parameter to this function
  App.initialize();
});

