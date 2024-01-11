function mchartCtrl($scope, $state, mchartService,mchartLookupService, $rootScope,$stateParams, successMessageHandler) {
	
	$scope.patient=$stateParams.mchartObj;
	
	$scope.mchartObject={};
	
	$scope.addMchartForPatient = function() {
		$scope.mchartObject={
				"id":$scope.patient.id,
				"mchartLookup":$scope.mchartLookupList,
				"mchart":$scope.mchartList,
				"date":$scope.toady
		}
		mchartService.addMchartPatient($scope.mchartObject).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.CATEGORY_ADD_DATA_SUCCESS);
                $scope.getAllMcharts();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.getAllMcharts = function() {
		mchartLookupService.getAllMcharts().then(function(response) {
			$scope.mchartLookupList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.getAllMchartByPatientId = function() {
		mchartService.getAllMchartsByPatientId($scope.patient.id).then(function(response) {
			$scope.mchartList = response.data;
			$scope.getAllMchartByPatientIdResult();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.getAllMchartByPatientIdResult = function() {
		mchartService.getMchartCountResult($scope.patient.id).then(function(response) {
			$scope.mchartResult = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	

	$scope.gotoList = function() {
		$state.go('main.listMchartLookup');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addMchartLookup');
	};
	$scope.gotoback = function() {
		$state.go('main.listMchartLookup');
	};
	$scope.gotoupdate = function(mchart) {
		$state.go('main.updateMchartLookup',{
			mchartLookupObj:mchart
		});
	};
	//718 multi page pdf

	(function () {  
	    var  
	     form = $('.form'),  
	     cache_width = form.width(),  
	     a4 = [595.28, 841.89]; // for a4 size paper width and height  

	    $('#create_pdf').on('click', function () {  
	    	alert("from mchart ctrl");
	    	window.scrollTo(0, 0); 
	        createPDF();  
	    });  
	   
	    //create pdf  
	    function createPDF() { var quotes = document.getElementById('patientEvalutionReport');
	   html2canvas(quotes)
	  .then((canvas) => {
	        //! MAKE YOUR PDF
	        var pdf = new jsPDF('p', 'pt', 'letter');

	        for (var i = 0; i <= quotes.clientHeight/980; i++) {
	            //! This is all just html2canvas stuff
	            var srcImg  = canvas;
	            var sX      = 0;
	            var sY      = 980*i; // start 980 pixels down for every new page
	            var sWidth  = 900;
	            var sHeight = 980;
	            var dX      = 0;
	            var dY      = 0;
	            var dWidth  = 900;
	            var dHeight = 980;

	            window.onePageCanvas = document.createElement("canvas");
	            onePageCanvas.setAttribute('width', 900);
	            onePageCanvas.setAttribute('height', 980);
	            var ctx = onePageCanvas.getContext('2d');
	            // details on this usage of this function: 
	            // https://developer.mozilla.org/en-US/docs/Web/API/Canvas_API/Tutorial/Using_images#Slicing
	            ctx.drawImage(srcImg,sX,sY,sWidth,sHeight,dX,dY,dWidth,dHeight);

	            // document.body.appendChild(canvas);
	            var canvasDataURL = onePageCanvas.toDataURL("image/png", 1.0);

	            var width         = onePageCanvas.width;
	            var height        = onePageCanvas.clientHeight;

	            //! If we're on anything other than the first page,
	            // add another page
	            if (i > 0) {
	                pdf.addPage(612, 791); //8.5" x 11" in pts (in*72)
	            }
	            //! now we declare that we're working on that page
	            pdf.setPage(i+1);
	            //! now we add content to that page!
	            pdf.addImage(canvasDataURL, 'PNG', 20, 40, (width*.62), (height*.62));

	        }
	        //! after the for loop is finished running, we save the pdf.
	        pdf.save('Test.pdf');
	    }
	  );
	    }  

	    // create canvas object  
	    function getCanvas() {  
	        form.width((a4[0] * 1.33333) - 80).css('max-width', 'none');  
	        return html2canvas(form, {  
	            imageTimeout: 2000,  
	            removeContainer: true  
	        });  
	    }  

	}());  

	// next 

	(function ($) {  
	    $.fn.html2canvas = function (options) {  
	        var date = new Date(),  
	        $message = null,  
	        timeoutTimer = false,  
	        timer = date.getTime();  
	        html2canvas.logging = options && options.logging;  
	        html2canvas.Preload(this[0], $.extend({  
	            complete: function (images) {  
	                var queue = html2canvas.Parse(this[0], images, options),  
	                $canvas = $(html2canvas.Renderer(queue, options)),  
	                finishTime = new Date();  

	                $canvas.css({ position: 'absolute', left: 0, top: 0 }).appendTo(document.body);  
	                $canvas.siblings().toggle();  

	                $(window).click(function () {  
	                    if (!$canvas.is(':visible')) {  
	                        $canvas.toggle().siblings().toggle();  
	                        throwMessage("Canvas Render visible");  
	                    } else {  
	                        $canvas.siblings().toggle();  
	                        $canvas.toggle();  
	                        throwMessage("Canvas Render hidden");  
	                    }  
	                });  
	                throwMessage('Screenshot created in ' + ((finishTime.getTime() - timer) / 1000) + " seconds<br />", 4000);  
	            }  
	        }, options));  

	        function throwMessage(msg, duration) {  
	            window.clearTimeout(timeoutTimer);  
	            timeoutTimer = window.setTimeout(function () {  
	                $message.fadeOut(function () {  
	                    $message.remove();  
	                });  
	            }, duration || 2000);  
	            if ($message)  
	                $message.remove();  
	            $message = $('<div ></div>').html(msg).css({  
	                margin: 0,  
	                padding: 10,  
	                background: "#000",  
	                opacity: 0.7,  
	                position: "fixed",  
	                top: 10,  
	                right: 10,  
	                fontFamily: 'Tahoma',  
	                color: '#fff',  
	                fontSize: 12,  
	                borderRadius: 12,  
	                width: 'auto',  
	                height: 'auto',  
	                textAlign: 'center',  
	                textDecoration: 'none'  
	            }).hide().fadeIn().appendTo('body');  
	        }  
	    };  
	})(jQuery);  
	// 718 multi page pdf end
};
angular.module("HealthApplication")
		.controller("mchartCtrl", mchartCtrl);
