 function calenderCtrl($scope) {
	// kendo ui scheduler
		
	 $scope.schedulerOptions = {
	            date: new Date("2013/6/13"),
	            startTime: new Date("2013/6/13 07:00 AM"),
	            height: 600,
	            views: [
	                "day",
	                "week",
	                "month",
	            ],
	            timezone: "Etc/UTC",
	            dataSource: {
	                batch: true,
	                transport: {
	                    read: {
	                        url: "https://demos.telerik.com/kendo-ui/service/tasks",
	                        dataType: "jsonp"
	                    },
	                    update: {
	                        url: "https://demos.telerik.com/kendo-ui/service/tasks/update",
	                        dataType: "jsonp"
	                    },
	                    create: {
	                        url: "https://demos.telerik.com/kendo-ui/service/tasks/create",
	                        dataType: "jsonp"
	                    },
	                    destroy: {
	                        url: "https://demos.telerik.com/kendo-ui/service/tasks/destroy",
	                        dataType: "jsonp"
	                    },
	                    parameterMap: function(options, operation) {
	                        if (operation !== "read" && options.models) {
	                            return {models: kendo.stringify(options.models)};
	                        }
	                    }
	                },
	                schema: {
	                    model: {
	                        id: "taskId",
	                        fields: {
	                            taskId: { from: "TaskID", type: "number" },
	                            title: { from: "Title", defaultValue: "No title", validation: { required: true } },
	                            ownerId: { from: "OwnerID", defaultValue: 1 },
	                           /* patientId: { from: "PatientId", defaultValue: 1 },*/
	                            start: { type: "date", from: "Start" },
	                            end: { type: "date", from: "End" },
	                            startTimezone: { from: "StartTimezone" },
	                            endTimezone: { from: "EndTimezone" },
	                            //description: { from: "Description" },
	                            recurrenceId: { from: "RecurrenceID" },
	                            recurrenceRule: { from: "RecurrenceRule" },
	                            recurrenceException: { from: "RecurrenceException" },
	                           
	                            isAllDay: { type: "boolean", from: "IsAllDay" }
	                        }
	                    }
	                },
	                filter: {
	                    logic: "or",
	                    filters: [
	                        { field: "ownerId", operator: "eq", value: 1 },
	                        { field: "ownerId", operator: "eq", value: 2 }
	                    ]
	                }
	               /* filter: {
	                    logic: "or",
	                    filters: [
	                        { field: "patientId", operator: "eq", value: 1 },
	                        { field: "patientId", operator: "eq", value: 2 }
	                    ]
	                }*/
	            },
	            resources: [
	                {
	                    field: "ownerId",
	                    title: "Therapist",
	                    dataSource: [
	                        { text: "Alex", value: 1, color: "#f8a398" },
	                        { text: "Bob", value: 2, color: "#51a0ed" },
	                        { text: "Charlie", value: 3, color: "#56ca85" }
	                    ]
	                }
	                /*{
	                    field: "patientId",
	                    title: "Patient Name",
	                    dataSource: [
	                        { text: "test", value: 1, color: "#f8a398" },
	                        { text: "Bob", value: 2, color: "#51a0ed" },
	                        { text: "Charlie", value: 3, color: "#56ca85" }
	                    ]
	                }*/
	          
	            ]
	        };


  };

angular.module("HealthApplication").controller("calenderCtrl", calenderCtrl);
