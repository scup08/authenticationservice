//直接点击关闭浏览器的时候清除本地存储
// window.onbeforeunload=function(){
//	   //会弹窗提示是否要关掉页面
//	  return "真的要关掉页面吗"; 		 		 
// };
//定义控制器
angular.module('myService', [])
	.factory("commonHttpService", function($http, httpConfig) {
		return {
			request: function(url, data, method) {
				var token = localStorage.getItem('token');
//				var requestUrl = 'http://' + httpConfig.ip + ':' + httpConfig.port + '/' + httpConfig.serviceName + '/app/' + url + '?random=' + Math.random() + '&token=' + token;
				var requestUrl = 'http://' + httpConfig.ip + ':' + httpConfig.port + '/'  + url + '?random=' + Math.random();
				
				var promise = $http({
						method: httpConfig.METHOD_CREATE,
						url: requestUrl,
						headers: {
							'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
						},
						data: data,
						//params: params,
						processData: false,
						contentType: false,
						xhrFields: {
							withCredentials: true
						}
					})
					//				.success(function(response) {
					//					console.log(response)
					//					
					//				})
					//				.error(function(response) {
					//
					//					alert("error");
					//
					//				})
				return promise;
			}
		};
	})
	.factory("fileService", function($http, httpConfig) {
		return {
			request: function(url, data, method) {
				var formData = new FormData(document.getElementById("reg"));
				var token = localStorage.getItem('token');
				var requestUrl = 'http://' + httpConfig.ip + ':' + httpConfig.port + '/' + httpConfig.serviceName + '/app/' + url + '?random=' + Math.random() + '&token=' + token;
				var promise = $http({
					method: httpConfig.METHOD_CREATE,
					url: requestUrl,
					data: formData,
					headers: {
						'Content-Type': undefined
					},
					transformRequest: angular.identity,
					processData: false,
					contentType: false,
				})
				return promise;
			}
		};
	})
	//判断输入的字符
	.factory('noNum', ['$window', function($window) {
		return {
			clearNoNum: function(key) {
				var p = /[a-z]/i; //匹配字母
				var b = p.test(key); //true,说明有英文字母
				var result = /[\u4e00-\u9fa5]+/g; //匹配中文
				var c = result.test(key); //true,说明有中文
				var obj = {};
				if(b == true || c == true) {
					var a = key.replace(/\D/g, '');
					var d = key.replace(/[\u4e00-\u9fa5]+/g, '');
					//obj.d = d;
					obj.a = a ;
					alert("只能填写数字！");
				}else{
					obj.a = key;
				}
				return obj;
			}
		}
	}])
	//主页导航栏数据服务
	.factory('navService', function() {
		//定义factory返回对象
		var myServices = {};
		//定义参数对象
		var myObject = " ";

		/**
		 * 定义传递数据的set函数
		 * @param {type} xxx
		 * @returns {*}
		 * @private
		 */
		var _set = function(data) {
			myObject = data;
		};

		/**
		 * 定义获取数据的get函数
		 * @param {type} xxx
		 * @returns {*}
		 * @private
		 */
		var _get = function() {
			return myObject;
		};

		// Public APIs
		myServices.set = _set;
		myServices.get = _get;

		// 在controller中通过调set()和get()方法可实现提交或获取参数的功能
		return myServices;
	})

.factory('locals', ['$window', function($window) {
		return { //存储单个属性
			set: function(key, value) {
				$window.sessionStorage[key] = value;
			}, //读取单个属性
			get: function(key, defaultValue) {
				return $window.sessionStorage[key] || defaultValue;
			}, //存储对象，以JSON格式存储
			setObject: function(key, value) {
				$window.sessionStorage[key] = JSON.stringify(value);
			}, //读取对象
			getObject: function(key) {
				return JSON.parse($window.sessionStorage[key] || '{}');
			},
			//存储单个属性
			_set: function(key, value) {
				console.log(value)
				$window.localStorage[key] = value;
			}, //读取单个属性
			_get: function(key, defaultValue) {
				return $window.localStorage[key] || defaultValue;
			}, //存储对象，以JSON格式存储
			_setObject: function(key, value) {
				$window.localStorage[key] = JSON.stringify(value);
			}, //读取对象
			_getObject: function(key) {
				return JSON.parse($window.localStorage[key] || '{}');
			}

		}
	}])
	//弹框滚动到头部
	.factory('myScroll', ['$window', function($window) {
		return { //存储单个属性
			scrollWindow: function() {
				var _childEl = document.getElementById("chat_history");
				_childEl.scrollTop = 0;
			}
		}
	}])
	//日期控件服务
	.factory('jeDate', function() {
		return { //存储单个属性
			datefun: function dp(jedatebg) {
				var bg = jedatebg || 'jedateblue';
				$(".datainp").jeDate({
					skinCell: bg,
					//isinitVal: true,//是否初始化日期
					//festival:true,//(是否显示农历)
					ishmsVal: false, //是否限制时分秒输入框输入，默认可以直接输入时间
					minDate: '1900-01-01',
					maxDate: $.nowDate({
						DD: 0
					}),
					format: "YYYY-MM-DD", //格式化日期
					zIndex: 3000, //弹出层的高度
					choosefun: function(val) {
						//console.log(val.val())
					}
				});
			}
		}
	});
//定义参数请求化
angular.module('module', [], function($httpProvider) {

	$httpProvider.defaults.headers.post[{
		'languageColumn': 'name_eu'
	}, {
		'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
	}];
	var param = function(obj) {
		var query = '',
			name, value, fullSubName, subName, subValue, innerObj, i;

		for(name in obj) {
			value = obj[name];

			if(value instanceof Array) {
				for(i = 0; i < value.length; ++i) {
					subValue = value[i];
					fullSubName = name + '[' + i + ']';
					innerObj = {};
					innerObj[fullSubName] = subValue;
					query += param(innerObj) + '&';
				}
			} else if(value instanceof Object) {
				for(subName in value) {
					subValue = value[subName];
					fullSubName = name + '[' + subName + ']';
					innerObj = {};
					innerObj[fullSubName] = subValue;
					query += param(innerObj) + '&';
				}
			} else if(value !== undefined && value !== null)
				query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
		}

		return query.length ? query.substr(0, query.length - 1) : query;
	};

	$httpProvider.defaults.transformRequest = [function(data) {
		return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
	}];
});