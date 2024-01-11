angular.module("HealthApplication").directive("required", function() {
   return {
       restrict: 'A', //only want it triggered for attributes
       compile: function(element) {
          //could add a check to make sure it's an input element if need be
           element.prepend("<span class='required'>*</span>");
       }
   }
});