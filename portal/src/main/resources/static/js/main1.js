(function ($) {
 "use strict";
 

/*----------------------------
	navbar-collapse
------------------------------ */
	$(document).on('click', '.navbar-collapse.in', function (e) {
		if ($(e.target).is('a') && $(e.target).attr('class') != 'dropdown-toggle') {
			$(this).collapse('hide');
		}
	});
	$('body').scrollspy({
		target: '.navbar-collapse',
		offset: 195
	});	
 
/*----------------------------
 wow js active
------------------------------ */
 new WOW().init(); 
 
/*----------------------------
	Parallax
------------------------------ */
  var well_lax = $('.parallax-area');
     well_lax.parallax("50%", 0.4);
  var well_text = $('.parallax-text');
     well_text.parallax("50%", 0.6);
  
/*----------------------------
 mainSlider-slider
------------------------------ */  
  var owl = $('.slider-carousel');

  // Carousel initialization
  owl.owlCarousel({
	  loop:true,
      autoplay: false,
	  dots:false,
	  nav:true,
	  navSpeed:700,	  
      items : 1,
	  navText:["<i class='fa fa-angle-left'></i>","<i class='fa fa-angle-right'></i>"], 		  
  });  
  

 // add animate.css class(es) to the elements to be animated
  function setAnimation ( _elem, _InOut ) {
    // Store all animationend event name in a string.
    // cf animate.css documentation
    var animationEndEvent = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';

    _elem.each ( function () {
      var $elem = $(this);
      var $animationType = 'animated ' + $elem.data( 'animation-' + _InOut );

      $elem.addClass($animationType).one(animationEndEvent, function () {
        $elem.removeClass($animationType); // remove animate.css Class at the end of the animations
      });
    });
  }

// Fired before current slide change
  owl.on('change.owl.carousel', function(event) {
      var $currentItem = $('.owl-item', owl).eq(event.item.index);
      var $elemsToanim = $currentItem.find("[data-animation-out]");
      setAnimation ($elemsToanim, 'out');
  });

// Fired after current slide has been changed
  owl.on('changed.owl.carousel', function(event) {

      var $currentItem = $('.owl-item', owl).eq(event.item.index);
      var $elemsToanim = $currentItem.find("[data-animation-in]");
      setAnimation ($elemsToanim, 'in');
  })     
 
/*----------------------------
	testimonial-carousel
------------------------------ */  
	 $(".testimonial-carousel").owlCarousel({
		loop:true,
		nav:false,
		dots:true,
		autoplay:false,
	    dots:true,
        animateOut: 'slideOutDown',
        animateIn: 'flipInX',
		smartSpeed:2000,
		navText:["<i class='fa fa-angle-left'></i>", "<i class='fa fa-angle-right'></i>"],
		responsive:{
			0:{
				items:1
			},
			768:{
				items:1
			},
			1000:{
				items:1
			}
		}
	});

/*----------------------------
	team carousel
------------------------------ */  
  $(".team-carousel").owlCarousel({
      autoplay: false, 
	  dots:true,
	  nav:false,	  
      items : 4,
		responsive:{
			0:{
				items:1
			},
			768:{
				items:3
			},
			1000:{
				items:4
			}
		}
  });


/*---------------------
	Portfolio
--------------------- */
	
	$(' #da-thumbs > li ').each( function() { $(this).hoverdir(); } );

/*---------------------
	TOP Menu Stick
--------------------- */
	var s = $("#sticker");
	var pos = s.position();					   
	$(window).on('scroll',function() {
		var windowpos = $(window).scrollTop();
		if (windowpos > pos.top) {
		s.addClass("stick");
		} else {
			s.removeClass("stick");	
		}
	});
	
	
/*---------------------
	page scrolling
--------------------- */
	
	$('a.page-scroll').bind('click', function(event) {
		var $anchor = $(this);
		$('html, body').stop().animate({
			scrollTop: $($anchor.attr('href')).offset().top
		}, 1500, 'easeInOutExpo');
		event.preventDefault();
	});
	

/*---------------------
	counterUp
--------------------- */	
	$('.count1').counterUp({
		delay: 40,
		time: 3000
	});	

	$('.count2').counterUp({
		delay: 50,
		time: 4000
	});

	$('.count3').counterUp({
		delay: 60,
		time: 5000
	});

	$('.count4').counterUp({
		delay: 70,
		time: 6000
	});
		
/*--------------------------
	scrollUp
---------------------------- */	
	$.scrollUp({
        scrollText: '<i class="fa fa-angle-up"></i>',
        easingType: 'linear',
        scrollSpeed: 900,
        animation: 'fade'
    }); 
	
/*----------------------------
    Contact form
------------------------------ */
	$("#contactForm").on("submit", function (event) {
		if (event.isDefaultPrevented()) {
			formError();
			submitMSG(false, "Did you fill in the form properly?");
		} else {
			event.preventDefault();
			submitForm();
		}
	});
	function submitForm(){
		var name = $("#name").val();
		var email = $("#email").val();
		var msg_subject = $("#msg_subject").val();
		var message = $("#message").val();


		$.ajax({
			type: "POST",
			url: "assets/contact.php",
			data: "name=" + name + "&email=" + email + "&msg_subject=" + msg_subject + "&message=" + message,
			success : function(text){
				if (text === "success"){
					formSuccess();
				} else {
					formError();
					submitMSG(false,text);
				}
			}
		});
	}

	function formSuccess(){
		$("#contactForm")[0].reset();
		submitMSG(true, "Message Submitted!")
	}

	function formError(){
		$("#contactForm").removeClass().addClass('shake animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
			$(this).removeClass();
		});
	}

	function submitMSG(valid, msg){
		if(valid){
			var msgClasses = "h3 text-center tada animated text-success";
		} else {
			var msgClasses = "h3 text-center text-danger";
		}
		$("#msgSubmit").removeClass().addClass(msgClasses).text(msg);
	}

	
 
})(jQuery); 