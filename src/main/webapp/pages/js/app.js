/**
 * 定义整个APP路由
 */
angular.module('mdis', ['ui.router', 'myController', 'myService', 'module'])
.value("httpConfig",{
	'ip': 'localhost',
	'port': '9001',
	'serviceName': 'mdis',
	'METHOD_CREATE': 'POST',
	'METHOD_DELETE': 'DELETE',
	'METHOD_UPDATE': 'PUT',
	'METHOD_GET': 'GET'
})
.config(function($stateProvider, $urlRouterProvider, $httpProvider) {
	$stateProvider
	//登陆
		.state('/login', {
			url: '/login',
			templateUrl: './templates/login.html',
			controller: 'loginController'
		})
		//主页面
		.state('/main', {
			url: '/main',
			templateUrl: './templates/main.html',
			controller: 'MainController'
		})
		//临床科室
		.state('/main.clinical',{
			params: {
				pageNo:null
			},
			url:'/clinical',
			templateUrl:'./templates/clinical.html',
			controller:'ClinicalCtrl'
		})
		//不良事件录入
		.state('/main.adverseTyp',{
			url:'/adverseTyp',
			templateUrl:'./templates/adverseTyp.html',
			controller:'TypCtrl'
		})
		//职能科室管理
		.state('/main.department',{
			url:'/department',
			templateUrl:'./templates/department.html',
			controller:'DepartmentCtrl'
		})
		//社工部管理
		.state('/main.social',{
			params: {
				pageNo:null
			},
			url:'/social',
			templateUrl:'./templates/social.html',
			controller:'SocialCtrl'
		})
		//查看案件详情
		.state('/main.details',{
			params: {
				uuid: null,
				pageNo:null,
				typ:null
			},
			url:'/details',
			templateUrl:'./templates/details.html',
			controller:'DetailsCtrl'
		})
	$urlRouterProvider.otherwise('/login');
	//$http AJAX 拦截器 处理
	$httpProvider.interceptors.push(['$rootScope', '$q', '$location', '$timeout','$window',
		function($rootScope, $q, $location, $timeout,$window) {
			return {
				'request': function(config) {
					config.headers['X-Requested-With'] = 'XMLHttpRequest';
					if($window.sessionStorage.length>0){
						var token = $window.sessionStorage.token;
						console.log(token)
						config.headers['Authorization'] = 'Bearer '+token ;
					};
					return config || $q.when(config);
				},
				'requestError': function(rejection) {
					return rejection;
				},
				'response': function(response) {
					return response || $q.when(response);
				},
				'responseError': function(response) {
					console.log('responseError:' + response);
					if(response.status == 401 || response.status == 403) {
						alert('系统登陆超时,请您重新登陆');
						$location.path('/login');
						return false;
					} else if(response.status == 500) {
						$location.path('/login');
						return false;
					}
					return $q.reject(response);
				}
			};
		}
	]);
})

.directive("edit", function() {
	return {
		restrict: "E",
		link: function(scope, element) {
			element.bind("click", function(e) {
				alert("I am clicked for editing");
			});
		}
	}
})

.directive("update", function($document) {
	return {
		restrict: 'AE',
		require: 'ngModel',
		link: function(scope, element, attrs, ngModel) {
			element.bind("click", function() {
				alert(ngModel.$modelValue + " is updated, Update your value here.");
				var id = "txt_name_" + ngModel.$modelValue.id;
				var obj = $("#" + id);
				obj.removeClass("active");
				obj.addClass("inactive");
				obj.attr("readOnly", true);
				scope.$apply(function() {
					scope.showEdit = true;
				})
			})
		}
	}
})

.directive("cancel", function($document) {
	return {
		restrict: 'AE',
		require: 'ngModel',
		link: function(scope, element, attrs, ngModel) {
			element.bind("click", function() {
				scope.$apply(function() {
					angular.copy(scope.master, ngModel.$modelValue);
					//console.log(ngModel.$modelValue);  
				})

				var id = "txt_name_" + ngModel.$modelValue.id;
				var obj = $("#" + id);
				obj.removeClass("active");
				obj.addClass("inactive");
				obj.prop("readOnly", true);
				scope.$apply(function() {
					scope.showEdit = true;
				})
			})
		}
	}
})

.directive("delete", function($document) {
	return {
		restrict: 'AE',
		require: 'ngModel',
		link: function(scope, element, attrs, ngModel) {
			element.bind("click", function() {
				var id = ngModel.$modelValue.id;
				alert("delete item where employee id:=" + id);
				scope.$apply(function() {
					for(var i = 0; i < scope.employees.length; i++) {
						if(scope.employees[i].id == id) {
							console.log(scope.employees[i])
							scope.employees.splice(i, 1);
						}
					}
					console.log(scope.employees);
				})
			})
		}
	}
})
.directive('ngTime', function() {
    return {
        restrict : 'A',
        require : '?ngModel',
        link : function($scope, $element, $attrs, $ngModel) {
            if (!$ngModel) {
                return;
            }
            $('.form_datetime').datetimepicker({
                weekStart: 1,
                todayBtn:  1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                forceParse: 0,
                showMeridian: 1
            });
            $('.form_date').datetimepicker({
                //language:  'fr',
                weekStart: 1,
                todayBtn:  1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0
            });
            $('.form_time').datetimepicker({
                //language:  'fr',
                weekStart: 1,
                todayBtn:  1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 1,
                minView: 0,
                maxView: 1,
                forceParse: 0
            });
        },
    };
});