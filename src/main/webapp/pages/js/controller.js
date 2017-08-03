angular.module("myController", [])
	//登陆
	.controller('loginController', ['$http', '$scope', 'commonHttpService', 'locals', 'navService', '$location', function($http, $scope, commonHttpService, locals, navService, $location) {
		sessionStorage.clear();
		var myurl = "auth/";
		var myurlregister = "auth/register";
		
		var loginInfo = {
			username: '',
			password: ''
		};
		$scope.login = function() {
			loginInfo.username = $scope.userCode;
			loginInfo.password = $scope.passWord;
			commonHttpService.request(myurl, loginInfo)
				.then(function(res) {
//					alert(res.data.token);
					console.log(res.data.token);
//					if(res.data.rtnCode == 0) {
						locals.set("token", res.data.token);
						$location.path('/main/department');
						
						//alert(444);
//						locals.set("position", res.data.data.position);
//						navService.set(res.data.data.pageInfoList);
//						var nav = res.data.data.pageInfoList;
//						locals.setObject("nav", nav)
//					} else {
//						alert(res.data.errMsg);
//						return;
//					}
				})
		};
		$scope.register = function() {
			loginInfo.username = $scope.userCode;
			loginInfo.password = $scope.passWord;
			commonHttpService.request(myurlregister, loginInfo)
				.then(function(res) {
					alert(res.data.username);
					console.log(res);
//					if(res.data.rtnCode == 0) {
//						$location.path('/main/department');
//						locals.set("userName", res.data.data.roleName);
//						locals.set("position", res.data.data.position)
//						navService.set(res.data.data.pageInfoList);
//						var nav = res.data.data.pageInfoList;
//						locals.setObject("nav", nav)
//					} else {
//						alert(res.data.errMsg);
//						return;
//					}
				})
		}
	}])
	.controller("MainController", ['$scope', '$http', '$location', '$rootScope', 'navService', 'locals', '$window', 'commonHttpService', function($scope, $http, $location, $rootScope, navService, locals, $window, commonHttpService) {
		if(locals.get('n')) {
			var a = locals.get('n');
			switch(a) {
				case '1':
					$scope.bg_1 = true;
					$scope.bg_2 = false;
					$scope.bg_3 = false;
					$scope.tip_1 = true;
					$scope.tip_2 = false;
					$scope.tip_3 = false;
					break;
				case '2':
					$scope.bg_2 = true;
					$scope.bg_1 = false;
					$scope.bg_3 = false;
					$scope.tip_1 = false;
					$scope.tip_2 = true;
					$scope.tip_3 = false;
					break;
				case '3':
					$scope.bg_3 = true;
					$scope.bg_2 = false;
					$scope.bg_1 = false;
					$scope.tip_1 = false;
					$scope.tip_2 = false;
					$scope.tip_3 = true;
					break;
				default:
					console.log("aaaaaaaaaa");
			}
		} else {
			$scope.bg_1 = true;
			$scope.tip_1 = true;
		};
		//切换主题色
		$scope.tag_bg = function(n) {
				locals.set('n', n)
				switch(n) {
					case 1:
						$scope.bg_1 = true;
						$scope.bg_2 = false;
						$scope.bg_3 = false;
						locals.set('FromSelf', 'jedateblue')
							//$scope.$broadcast("FromSelf","jedateblue");
						$window.location.reload();
						break;
					case 2:
						$scope.bg_2 = true;
						$scope.bg_1 = false;
						$scope.bg_3 = false;
						locals.set('FromSelf', 'jedatered')
							//$scope.$broadcast("FromSelf","jedatered");
						$window.location.reload();
						break;
					case 3:
						$scope.bg_3 = true;
						$scope.bg_2 = false;
						$scope.bg_1 = false;
						locals.set('FromSelf', 'jedategreen');
						//$scope.$broadcast("FromSelf","jedategreen");
						$window.location.reload();
						break;
				}
			}
			//获取导航菜单
		$scope.img_onoff = 2;
		$scope.img_onoff_1 = 1;
		$scope.nav = locals.getObject("nav");
		$scope.userName = locals.get("userName");
		$scope.position = locals.get("position");
		//点击退出
		$scope.quit = function() {
			var myurl = "bsAdverseCopy/loginOut";
			commonHttpService.request(myurl)
				.then(function(res) {
					sessionStorage.clear();
					$location.path("/login");
				})
		};
		//默认选中左侧菜单的第一条 添加对应的样式
		var navIndex = locals.get('navIndex') ? navIndex = locals.get('navIndex') : 0;
		$scope.selectedRow = navIndex;
		//监听路由的变化
		$rootScope.$on('$stateChangeSuccess',
			function(event, toState, toParams, fromState, fromParams) {
				if(toState.name == '/main.desktop') {
					$scope.selectedRow = 0;
				} else if(toState.name == '/main.query') {
					$scope.selectedRow = 1;
				}
			});

		//首页上的提示标签开关
		$scope.isActive = true;

		//tab 导航菜单默认选中第一条，添加对应的类名
		$scope.active = 0;
		//登陆注册的样式的开关
		$scope.log_regActive = false;
		//点击登陆切换的开关
		$scope.logTag = function() {
				$scope.log_regActive = false;
			}
			//点击注册切换的开关
		$scope.regTag = function() {
			$scope.log_regActive = true;
		}

		//点击左侧菜单刷新tab菜单
		$scope.tabNav = function(index) {
				//$rootScope.$broadcast('on', true);
				//默认tab导航菜单选择第一条，添加对应的样式
				//$scope.active = 0;
				//给当前点击的这一项添加对应的样式
				$scope.selectedRow = index;
				//locals.set('navIndex', index)
			}
			//左侧菜单
		$(function() {
			var Accordion = function(el, multiple) {
				this.el = el || {};
				this.multiple = multiple || false;
				var links = this.el.find('.link');
				links.on('click', {
					el: this.el,
					multiple: this.multiple
				}, this.dropdown)
			}

			Accordion.prototype.dropdown = function(e) {
				var $el = e.data.el;
				$this = $(this),
					$next = $this.next(); //找同辈元素
				$next.slideToggle(); //以滑动效果在显示隐藏之间切换
				$this.parent().toggleClass('open'); //给他的父元素添加或者一处属性
				if(!e.data.multiple) {
					//找到当前文档下的所有.submenu元素滑动隐藏，并且移除父元素属性
					$el.find('.submenu').not($next).slideUp().parent().removeClass('open');
				};
			}

			var accordion = new Accordion($('#accordion'), false);
		});

	}])

//临床科室管理
.controller('ClinicalCtrl', ['$http', '$scope', '$state', '$stateParams', 'commonHttpService', 'jeDate', 'locals', function($http, $scope, $state, $stateParams, commonHttpService, jeDate, locals) {
		var b = locals.get('FromSelf');
		//初始化日期控件
		jeDate.datefun(b)
		var myUrl;
		var obj = {};
		obj.pageNo = $stateParams.pageNo || null;
		$scope.get_data = function() {
			myUrl = "bsAdverseCopy/queryAdverseEventInfo"
			commonHttpService.request(myUrl, obj)
				.then(function(res) {
					$scope.res = res.data.data;
					$scope.allpage = res.data.pageCount;
					$scope.allCase = res.data.recordCount;
					if($scope.res == null || $scope.res == '') {
						alert("当前搜索条件下没有案件！")
					}
				});
		};
		$scope.get_data();
		$scope.formData = {};
		//搜索
		$scope.search = function() {
			$scope.formData.startTime = $("#date_start").val();
			$scope.formData.endTime = $("#date_end").val();
			obj = $scope.formData;
			$scope.get_data();
			pag_num = 1;
			$scope.num = pag_num;
		};
		//分页
		var pag_num = $stateParams.pageNo || 1;
		$scope.num = pag_num;
		$scope.pageGet = function(n) {
			switch(n) {
				case 1:
					pag_num = n;
					break;
				case 2:
					pag_num--;
					break;
				case 3:
					pag_num++;
					break;
				case 4:
					pag_num = $scope.allpage;
					break;
			}
			if(pag_num > $scope.allpage) {
				alert("已到最后一页了！");
				pag_num = $scope.allpage;
				return;
			} else if(pag_num <= 0) {
				alert("已经是第一页了！");
				pag_num = 1;
				return;
			}
			$scope.num = pag_num;
			obj.pageNo = pag_num;
			$scope.get_data(myUrl, obj);
		};
		//选中
		$scope.selected = [];
		$scope.selectedTags = [];
		var updateSelected = function(action, id, name) {
			if(action == 'add' && $scope.selected.indexOf(id) == -1) {
				$scope.selected.push(id);
				$scope.selectedTags.push(name);
			}
			if(action == 'remove' && $scope.selected.indexOf(id) != -1) {
				var idx = $scope.selected.indexOf(id);
				$scope.selected.splice(idx, 1);
				$scope.selectedTags.splice(idx, 1);
			}

		};
		//声明变量控制修改时的数据
		var num
		$scope.updateSelection = function($event, id, index) {
			num = index;
			var checkbox = $event.target;
			var action = (checkbox.checked ? 'add' : 'remove');
			updateSelected(action, id, checkbox.name);
		};
		$scope.isSelected = function(id) {
			return $scope.selected.indexOf(id) >= 0;
		};
		//审核与取消审核
		$scope.review = function(n) {
			myUrl = "bsAdverseCopy/deptAuditingEvent";
			obj.uuidList = $scope.selected;
			obj.operateFlag = n;
			commonHttpService.request(myUrl, obj)
				.then(function(res) {
					switch(res.data.rtnCode) {
						case 1:
							alert(res.data.errMsg);
							$scope.selected = [];
							break;
						case 0:
							alert("审核成功！");
							$scope.selected = [];
							$scope.get_data();
							break;
					}
				});
		};
		//查看
		$scope.detail = function(id) {
			$state.go("/main.details", {
				uuid: id,
				pageNo: pag_num,
				typ: 1
			})
		};
	}])
	//查看详情
	.controller('DetailsCtrl', ['$http', '$scope', '$state', '$stateParams', 'commonHttpService', 'jeDate', 'locals', function($http, $scope, $state, $stateParams, commonHttpService, jeDate, locals) {
		var myUrl, obj = {};
		obj.uuid = $stateParams.uuid;
		//查找数据
		$scope.get_data = function() {
			myUrl = "bsAdverseCopy/queryAdverseEventById"
			commonHttpService.request(myUrl, obj)
				.then(function(res) {
					$scope.res = res.data.data;
				});
		};
		$scope.get_data();
		//返回
		$scope.back = function() {
			switch($stateParams.typ) {
				case 1:
					$state.go("/main.clinical", {
						pageNo: $stateParams.pageNo
					});
					break;
				case 2:
					$state.go("/main.social", {
						pageNo: $stateParams.pageNo
					});
					break;
			}
		};
	}])
	//不良事件录入
	.controller('TypCtrl', ['$http', '$scope', '$state', '$stateParams', 'commonHttpService', 'jeDate', 'locals','fileService', function($http, $scope, $state, $stateParams, commonHttpService, jeDate, locals,fileService) {
		var b = locals.get('FromSelf');
		jeDate.datefun(b);
		//初始化数据
		var myUrl;
		var obj = {};
		obj.pageNo = $stateParams.pageNo || null;
		$scope.get_data = function() {
			myUrl = "bsAdverseCopy/queryAdverseEventInfo"
			commonHttpService.request(myUrl, obj)
				.then(function(res) {
					$scope.res = res.data.data;
					$scope.allpage = res.data.pageCount;
					$scope.allCase = res.data.recordCount;
					if($scope.res == null || $scope.res == '') {
						alert("当前搜索条件下没有案件！")
					}
				});
		};
		$scope.get_data();
		$scope.formData = {};
		//搜索
		$scope.search = function() {
			$scope.formData.startTime = $("#date_start").val();
			$scope.formData.endTime = $("#date_end").val();	
			obj = $scope.formData;
			$scope.get_data();
			pag_num = 1;
			$scope.num = pag_num;
		};
		//分页
		var pag_num = $stateParams.pageNo || 1;
		$scope.num = pag_num;
		$scope.pageGet = function(n) {
			switch(n) {
				case 1:
					pag_num = n;
					break;
				case 2:
					pag_num--;
					break;
				case 3:
					pag_num++;
					break;
				case 4:
					pag_num = $scope.allpage;
					break;
			}
			if(pag_num > $scope.allpage) {
				alert("已到最后一页了！");
				pag_num = $scope.allpage;
				return;
			} else if(pag_num <= 0) {
				alert("已经是第一页了！");
				pag_num = 1;
				return;
			}
			$scope.num = pag_num;
			obj.pageNo = pag_num;
			$scope.get_data(myUrl, obj);
		};
		//选中
		$scope.selected = [];
		$scope.selectedTags = [];
		var updateSelected = function(action, id, name) {
			if(action == 'add' && $scope.selected.indexOf(id) == -1) {
				$scope.selected.push(id);
				$scope.selectedTags.push(name);
			}
			if(action == 'remove' && $scope.selected.indexOf(id) != -1) {
				var idx = $scope.selected.indexOf(id);
				$scope.selected.splice(idx, 1);
				$scope.selectedTags.splice(idx, 1);
			}

		};
		//声明变量控制修改时的数据
		var num
		$scope.updateSelection = function($event, id, index) {
			num = index;
			var checkbox = $event.target;
			var action = (checkbox.checked ? 'add' : 'remove');
			updateSelected(action, id, checkbox.name);
		};
		$scope.isSelected = function(id) {
			return $scope.selected.indexOf(id) >= 0;
		};
		//$scope.timepickerTest = "2016-12-16";
		//新增
		$scope.reg_obj = {};
		$scope.backdrop = true;
		$scope.reg = false;
		$scope.addpre = function() {
			$scope.backdrop = false;
			$scope.reg = false;
		};
		//关闭
		$scope.close = function() {
			$scope.backdrop = true;
		};
		//选择类别登记
		$scope.class_reg = function(n) {
			$scope.eventType = n;
			$scope.reg_obj.eventType = n;
			$scope.reg = true;
			switch(n){
				case 1:$scope.reg_name = "诊疗类新增";break;
				case 2:$scope.reg_name = "药品类新增";break;
				case 3:$scope.reg_name = "医疗器械类新增";break;
				case 4:$scope.reg_name = "输血类新增";break;
				case 5:$scope.reg_name = "护理类新增";break;
				case 6:$scope.reg_name = "非诊疗类新增";break;
				case 7:$scope.reg_name = "医患沟通类新增";break;
			};
			myUrl = "bsAdverseCopy/loadZdData";
			commonHttpService.request(myUrl,$scope.reg_obj)
				.then(function(res) {
					$scope.pat_sex = res.data.dicMap[39];
					$scope.pat_age = res.data.dicMap[40];
					$scope.brylTypeNm = res.data.dicMap[41];
					$scope.deptName =  res.data.dicMap[98];
					$scope.eventName =  res.data.nameList;
					$scope.patStatus = res.data.dicMap[42];
					$scope.patFun = res.data.dicMap[43];
					$scope.patCd = res.data.dicMap[44];
					$scope.eventLv = res.data.dicMap[45];
				});
		};
		//保存
		$scope.save = function(){
			myUrl = "bsAdverseCopy/save";
			fileService.request(myUrl)
				.then(function(res) {
					$scope.backdrop = true;
					switch(res.data.rtnCode){
						case 0:alert('保存成功！');break;
						case 1:alert(res.data.errMsg);break;
					}
				});
		};
		

	}])
	//职能科室管理
	.controller('DepartmentCtrl', ['$http', '$scope', '$location', 'jeDate', 'locals', 'commonHttpService', function($http, $scope, $location, jeDate, locals, commonHttpService) {
		var b = locals.get('FromSelf');
		var token = locals.get("token");
		console.log(token)
		//初始化日期控件
		jeDate.datefun(b);
		var myUrl = "bsAdverseCopy/queryAdverseEventInfo";
		var obj = {};
		$scope.get_data = function() {
			//alert(token);
			commonHttpService.request(myUrl, obj,'',token)
				.then(function(res) {
					$scope.res = res.data.data;
					$scope.allpage = res.data.pageCount;
					$scope.allCase = res.data.recordCount;
					if($scope.res == null || $scope.res == '') {
						alert("当前搜索条件下没有案件！")
					}
				})
		}
		//$scope.get_data();
		$scope.formData = {};
		//搜索
		$scope.search = function() {
				$scope.formData.startTime = $("#date_start").val();
				$scope.formData.endTime = $("#date_end").val();
				obj = $scope.formData;
				$scope.get_data();
			}
			//分页
		var pag_num = 1;
		var obj = {};
		$scope.num = pag_num;
		$scope.pageGet = function(n) {
			switch(n) {
				case 1:
					pag_num = n;
					break;
				case 2:
					pag_num--;
					break;
				case 3:
					pag_num++;
					break;
				case 4:
					pag_num = $scope.allpage;
					break;
			}
			if(pag_num > $scope.allpage) {
				alert("已到最后一页了！");
				pag_num = $scope.allpage;
				return;
			} else if(pag_num <= 0) {
				alert("已经是第一页了！");
				pag_num = 1;
				return;
			}
			$scope.num = pag_num;
			obj.pageNo = pag_num;
			$scope.get_data(myUrl, obj);
		};

	}])
	//社工部
	.controller('SocialCtrl', ['$http', '$scope', '$state', '$stateParams', 'commonHttpService', 'jeDate', 'locals','fileService','noNum', function($http, $scope, $state, $stateParams, commonHttpService, jeDate, locals,fileService,noNum) {
		//限制只能输入数字
		$scope.clearNoNum = function(ev){
			var b = noNum.clearNoNum(ev.key).a;
			if(b == ''){
				$scope.bgTel = b;
			}else if(ev.target.value.length == 15){
				alert("已到达最大输入限制！")
			}
		}
		var b = locals.get('FromSelf');
		//初始化日期控件
		jeDate.datefun(b);
		//初始化数据
		var myUrl;
		var obj = {};
		obj.pageNo = $stateParams.pageNo || null;
		$scope.get_data = function() {
			myUrl = "bsAdverseCopy/queryAdverseEventInfo";
			commonHttpService.request(myUrl, obj)
				.then(function(res) {
					$scope.res = res.data.data;
					$scope.allpage = res.data.pageCount;
					$scope.allCase = res.data.recordCount;
					if($scope.res == null || $scope.res == '') {
						alert("当前搜索条件下没有案件！")
					}
				})
		}
		$scope.get_data();
		$scope.formData = {};
		//搜索
		$scope.search = function() {
				$scope.formData.startTime = $("#date_start").val();
				$scope.formData.endTime = $("#date_end").val();
				obj = $scope.formData;
				$scope.get_data();
				pag_num = 1;
				$scope.num = pag_num;
			}
			//分页
		var pag_num = $stateParams.pageNo || 1;
		$scope.num = pag_num;
		$scope.pageGet = function(n) {
			switch(n) {
				case 1:
					pag_num = n;
					break;
				case 2:
					pag_num--;
					break;
				case 3:
					pag_num++;
					break;
				case 4:
					pag_num = $scope.allpage;
					break;
			}
			if(pag_num > $scope.allpage) {
				alert("已到最后一页了！");
				pag_num = $scope.allpage;
				return;
			} else if(pag_num <= 0) {
				alert("已经是第一页了！");
				pag_num = 1;
				return;
			}
			$scope.num = pag_num;
			obj.pageNo = pag_num;
			$scope.get_data(myUrl, obj);
		};
		//选中
		$scope.selected = [];
		$scope.selectedTags = [];
		var updateSelected = function(action, id, name) {
			if(action == 'add' && $scope.selected.indexOf(id) == -1) {
				$scope.selected.push(id);
				$scope.selectedTags.push(name);
			}
			if(action == 'remove' && $scope.selected.indexOf(id) != -1) {
				var idx = $scope.selected.indexOf(id);
				$scope.selected.splice(idx, 1);
				$scope.selectedTags.splice(idx, 1);
			}

		};
		//声明变量控制修改时的数据
		var num
		$scope.updateSelection = function($event, id, index) {
			num = index;
			var checkbox = $event.target;
			var action = (checkbox.checked ? 'add' : 'remove');
			updateSelected(action, id, checkbox.name);
		};
		$scope.isSelected = function(id) {
			return $scope.selected.indexOf(id) >= 0;
		};
		//审核与取消审核
		$scope.review = function(n) {
			myUrl = "bsAdverseCopy/auditingAdverseEvent";
			obj.uuidList = $scope.selected;
			obj.operateFlag = n;
			commonHttpService.request(myUrl, obj)
				.then(function(res) {
					switch(res.data.rtnCode) {
						case 1:
							alert(res.data.errMsg);
							$scope.selected = [];
							break;
						case 0:
							switch(n) {
								case 0:
									alert("审核成功！");
									break;
								case 1:
									alert("取消审核成功！");
									break;
							};
							$scope.selected = [];
							$scope.get_data();
							break;
					}
				});
		};
		//查看
		$scope.detail = function(id) {
			$state.go("/main.details", {
				uuid: id,
				pageNo: pag_num,
				typ: 2
			})
		};
		$scope.del = function(id){
			var obj = {};
			obj.uuid = id;
			myUrl = "bsAdverseCopy/deleteAdverseEventById";
			commonHttpService.request(myUrl,obj)
				.then(function(res) {
					switch(res.data.rtnCode){
						case 0:
						alert("删除成功！");
						$scope.get_data();
						break;
						case 1:
						alert("删除失败！");
						break;
					}
				});
		}
		//新增
		$scope.reg_obj = {};
		$scope.backdrop = true;
		$scope.reg = false;
		$scope.addpre = function() {
			$scope.backdrop = false;
			$scope.reg = false;
		};
		//关闭
		$scope.close = function() {
			$scope.backdrop = true;
		};
		//选择类别登记
		$scope.class_reg = function(n) {
			$scope.eventType = n;
			$scope.reg_obj.eventType = n;
			$scope.reg = true;
			switch(n){
				case 1:$scope.reg_name = "诊疗类新增";break;
				case 2:$scope.reg_name = "药品类新增";break;
				case 3:$scope.reg_name = "医疗器械类新增";break;
				case 4:$scope.reg_name = "输血类新增";break;
				case 5:$scope.reg_name = "护理类新增";break;
				case 6:$scope.reg_name = "非诊疗类新增";break;
				case 7:$scope.reg_name = "医患沟通类新增";break;
			};
			myUrl = "bsAdverseCopy/loadZdData";
			commonHttpService.request(myUrl,$scope.reg_obj)
				.then(function(res) {
					$scope.pat_sex = res.data.dicMap[39];
					$scope.pat_age = res.data.dicMap[40];
					$scope.brylTypeNm = res.data.dicMap[41];
					$scope.deptName =  res.data.dicMap[98];
					$scope.eventName =  res.data.nameList;
					$scope.patStatus = res.data.dicMap[42];
					$scope.patFun = res.data.dicMap[43];
					$scope.patCd = res.data.dicMap[44];
					$scope.eventLv = res.data.dicMap[45];
				});
		};
		//保存
		$scope.save = function(){
			myUrl = "bsAdverseCopy/save";
			fileService.request(myUrl)
				.then(function(res) {
					$scope.backdrop = true;
					switch(res.data.rtnCode){
						case 0:alert('保存成功！');break;
						case 1:alert(res.data.errMsg);break;
					}
				});
		};
		//发布与取消发布
		$scope.issued = function(n) {
			myUrl = "bsAdverseCopy/publishAdverseEvent";
			obj.uuidList = $scope.selected;
			obj.operateFlag = n;
			commonHttpService.request(myUrl, obj)
				.then(function(res) {
					switch(res.data.rtnCode) {
						case 1:
							alert(res.data.errMsg);
							$scope.selected = [];
							break;
						case 0:
							switch(n) {
								case 0:
									alert("发布成功！");
									break;
								case 1:
									alert("取消发布成功！");
									break;
							};
							$scope.selected = [];
							$scope.get_data();
							break;
					}
				});
		};
		//作废与取消作废
		$scope.invalid = function(n) {
			myUrl = "bsAdverseCopy/cancelAdverseEvent";
			obj.uuidList = $scope.selected;
			obj.operateFlag = n;
			commonHttpService.request(myUrl, obj)
				.then(function(res) {
					switch(res.data.rtnCode) {
						case 1:
							alert(res.data.errMsg);
							$scope.selected = [];
							break;
						case 0:
							switch(n) {
								case 0:
									alert("作废成功！");
									break;
								case 1:
									alert("取消作废成功！");
									break;
							};
							$scope.selected = [];
							$scope.get_data();
							break;
					}
				});
		};
	}]);