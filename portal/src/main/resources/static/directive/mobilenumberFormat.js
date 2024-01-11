angular
		.module("HealthApplication")
		.directive(
				'phonenumberDirective',
				[
						'$filter',
						function($filter) {
							
							function link(scope, element, attributes) {

								scope.inputValue = scope.phonenumberModel;

								scope.$watch('inputValue', function(value,
										oldValue) {

									value = String(value);
									var number = value.replace(/[^0-9]+/g, '');
									scope.phonenumberModel = number;
									scope.inputValue = $filter('phonenumber')(
											number);
								});
							}

							return {
								link : link,
								restrict : 'AE',
								scope : {
									phonenumberPlaceholder : '=placeholder',
									phonenumberModel : '=model',
								},
								template : '<input ng-model="inputValue" type="tel" class="phonenumber" placeholder="{{phonenumberPlaceholder}}" title="Phonenumber (Format: (999) 9999-9999)">',
							};
						} ])

		.filter('phonenumber', function() {
			
			return function(number) {
				if (!number) {
					return '';
				}
				number = String(number);

				var formattedNumber = number;

				var c = (number[0] == '1') ? '1 ' : '';
				number = number[0] == '1' ? number.slice(1) : number;

				var area1 = number.substring(0, 5);
				var end1 = number.substring(5, 10);

				if (end1) {
					formattedNumber = (area1 + " " + end1);

				}
				return formattedNumber;
			};
		});