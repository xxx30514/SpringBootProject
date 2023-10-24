 const app  = Vue.createApp({
      data() {
        	return {
          		message: 'Hello Vue!',
         		dataList: [],
         		empty: null,
         		length: '',
         		formData:{empGender:'男'},
         		//emp:{empName:'老五',empGender:'男'},
         		emp:{empName:'老五'},
         		headerImg:'',
         		file:'',
         		image:'',
         		loginForm:{},
         		checkForm:{},
         		userInfo:'',
         		sessionInfo:'',
         		queryInfo:'',
         		pagination: {//分頁相關資料
                     currentPage: 1,//當前頁
                     pageSize:10,//每頁顯示幾筆
                     total:0, //資料總筆數
                     pages:0,//總頁數
                     empName:'',
                     empAge:'',
                     empGender:''
                 },
               	categoryForm:{type:1,name:'套牌',sort:1},
               	imageUrl:'',
               	fileForm: new FormData(),
               	categoryList: [],
        	}
      },
      //頁面載入完成Vue後執行
      mounted(){
    	  		//查詢所有
    	  		this.getAll();
    	  		//const user = sessionStorage.getItem('test')
	  			//if(user){
	  			//this.user=JSON.parse(user)
	  			//}
    	  		//console.log(user);
    	  		
    	  		
    	  		
      },
      //頁面渲染前執行
      created(){
	  			const userInfo = window.localStorage.getItem('userInfo')
	  			if(userInfo){
	  			this.userInfo=JSON.parse(userInfo);	  			
	  			}
	  			const sessionInfo = sessionStorage.getItem('test');
	  			if(sessionInfo){
	  			this.sessionInfo=JSON.parse(sessionInfo);
	  			}
	  			this.getCategoryList();
	  			//重整頁面時保存當前頁面 不會因為重整回到第一頁
	  			this.storePageNum();		
	  			const queryInfo = window.sessionStorage.getItem('name');
	  			if(queryInfo){
	  				this.pagination.empName = queryInfo;  			
// 	  			this.squeryInfo=JSON.parse(queryInfo);
// 	  			this.squeryInfo=JSON.stringify(queryInfo);
	  			}
	 },
	 beforeRouteLeave(to,from){
		  window.sessionStorage.setItem('currentPage',1)
// 		  next();	  
	 },
	 watch:{
		 'pagination.empName'(newValue,oldValue) {
// 			 window.sessionStorage.setItem('currentPage',1)	
// 			 this.pagination.currentPage=1;
			console.log("new:",newValue);
			console.log("old:",oldValue);
			if(newValue){
// 				 this.pagination.currentPage=1;
// 				window.sessionStorage.setItem('currentPage',1)	
// 				this.storePageNum();
			}
// 			 this.storePageNum();
// 			 if(newValue){
// 				 this.pagination.empName= newValue;
// 			 }
		 }
	 },
      methods:{
    	  getAll() {//查詢所有資料並進行分頁
    		  param="?query"
    		  param+="&empName="+this.pagination.empName;
    		  param+="&empAge="+this.pagination.empAge;
    		  param+="&empGender="+this.pagination.empGender;
    		  		axios.get("/emps/"+this.pagination.currentPage+"/"+this.pagination.pageSize+param).then((response)=> {//成功時執行的回調函數
  		 		      console.log(response.data);
  		 		      console.log(response.data.data.records.length);
  		 		      console.log(response.data.data.records[0]);
  		 		      console.log(response.data.data);
  		 		      console.log(response.data.data.records);
  		 		   	  this.pagination.currentPage=response.data.data.current;
  		 		   	  this.pagination.pageSize=response.data.data.size;
  		 		   	  this.pagination.total=response.data.data.total;
  		 		   	  this.pagination.pages=response.data.data.pages;
    		  		  this.dataList = response.data.data.records;
		 			  this.length = response.data.data.length;
// 		 			  this.param=param;
		 			  window.sessionStorage.setItem('name',this.pagination.empName);		 	
  		 		  });
    	  },
    	  search() {//查詢所有資料並進行分頁
    		  param="?query"
    		  param+="&empName="+this.pagination.empName;
    		  param+="&empAge="+this.pagination.empAge;
    		  param+="&empGender="+this.pagination.empGender;
    		  		axios.get("/emps/1"+"/"+this.pagination.pageSize+param).then((response)=> {//成功時執行的回調函數
  		 		      console.log(response.data);
  		 		      console.log(response.data.data.records.length);
  		 		      console.log(response.data.data.records[0]);
  		 		      console.log(response.data.data);
  		 		      console.log(response.data.data.records); 		 		   
  		 		   	  this.pagination.pageSize=response.data.data.size;
  		 		   	  this.pagination.total=response.data.data.total;
  		 		   	  this.pagination.pages=response.data.data.pages;
    		  		  this.dataList = response.data.data.records;
		 			  this.length = response.data.data.length;
		 			  window.sessionStorage.setItem('name',this.pagination.empName);
		 			  window.sessionStorage.setItem('currentPage',1)
		 			  this.storePageNum();
  		 		  });
    	  },
    	  changePage(currentPage){
    		  	this.pagination.currentPage=currentPage;
    		  	window.sessionStorage.setItem('currentPage',currentPage);
    		  	this.getAll();
    	  },
    	  addEmp() {
		  			axios.post("/emps",this.formData).then((response)=> {//成功時執行的回調函數
		  			  //判斷操作是否成功
		  			  if (response.data.flag) {
		  				
		  				//this.$refs.Close.click();
// 		  				Swal.fire({
// 	       	  				  position: 'top',
// 	       	  				  icon: 'success',
// 	       	  				  title: '新增成功',
// 	       	  				  showConfirmButton: false,
// 	       	  				  timer: 3000
// 	       	  				})		
		  				Toast.fire({
		  	                icon: 'success', title: '資料新增成功',
		  	                timer: 3000
		  	            });
					  }else {
						  Toast.fire({
			  	                icon: 'error', title: response.data.msg
			  	            });
					  }
	 		  		}).finally(()=>{	
	 		  		   //新增完成後再查詢一次，重整資料 	
		  			   this.getAll();			  
	 		  		});
	 	  },
	 	  addEmpWithFile(e) {
	 		 		//event.preventDefault();
	 		  		console.log(e)
	 		  	 	
	 		  		const file = document.querySelector('#fileupload');
	 		 		const options = {
	 				headers: //{"Content-Type": "application/x-www-form-urlencoded"}
	 				{"Content-Type":"multipart/form-data"}
	 		 		//contentType:false,
	 		 		//processData:false
	 				}
	 		 		let fileForm2 = new FormData();
	 		 		//fileForm2.append("emp",new Blob([JSON.stringify({"empName": "小黑",
 		 				//"empAge": 26,"empGender":"男"})],{type:"application/json"}));
	 		 		//fileForm2.append("file",file[0]);
	 		 		console.log(fileForm2.get("empName"));
	 		 		var empdata = 
	 		 		{"empName": this.emp.empName,
	 		 				"empAge": 26,"empGender":"男"}
//	 		 		fileForm2.append("emp",JSON.stringify(empdata));	
	 		 		fileForm2.append('file',file.files[0]);
	 		 		fileForm2.append("emp",new Blob([JSON.stringify(empdata)],{type:"application/json"}));	
// 	 		 		fileForm2.append("emp",empdata);	
	 		 		console.log(fileForm2);
	 		 		console.log(fileForm2.get("emp"));
	 		 		//fileForm2.append("emp",JSON.stringify(emp));
	 		 		//fileForm2.append("emp",emp);
	 		 		//fileForm2.append("emp",{"empName": "小王","empAge": 26,"empGender":"男"});
	 		 		
	 		 		
	  			axios.post("/emps/newfile",fileForm2,options).then((response)=> {//成功時執行的回調函數
	  			  //判斷操作是否成功
	  			  	console.log("回應"+response);
	  			  if (response.data.flag) {
	  				
	  				//this.$refs.Close.click();
//	  				Swal.fire({
//      	  				  position: 'top',
//      	  				  icon: 'success',
//      	  				  title: '新增成功',
//      	  				  showConfirmButton: false,
//      	  				  timer: 3000
//      	  				})		
	  				Toast.fire({
	  	                icon: 'success', title: '資料新增成功',
	  	                timer: 3000
	  	            });
				  }else {
					  Toast.fire({
		  	                icon: 'error', title: response.data.msg
		  	            });
				  }
		  		}).finally(()=>{	
		  		   //新增完成後再查詢一次，重整資料 	
	  			   this.getAll();			  
		  		});
	  	  },
	 	  removeEmp(empId){		  	　
	  		   	   Swal.fire({
                   		title: "確定刪除此項資料(序號:" + empId + ")?",
                   		text:"刪除後將無法復原，請謹慎操作！",
                   		icon: "warning",
                   		showCancelButton: true,
                   		closeOnConfirm: false, //false=確認時關閉彈出框
                   		showLoaderOnConfirm: true,
                   		showCloseButton: true, //右上角x
                   		confirmButtonClass: "btn-danger",
                   	 	confirmButtonColor: '#d33',
                   		cancelButtonText: "取消",
                   		confirmButtonText: "刪除",
                   	 	//reverseButtons: true //反轉按鈕位置讓確定在右側
               	   }).then((result)=>{
               		   if (result.isConfirmed) {			
               		 axios.delete("/emps/"+empId).then((response)=> {//成功時執行的回調函數
       	  			  //判斷操作是否成功
       	  			  if (response.data.flag) {
       	  				Swal.fire({
       	  				  position: 'top',
       	  				  icon: 'success',
       	  				  title: '資料刪除成功',
       	  				  showConfirmButton: false,
       	  				  timer: 3000
       	  				})				
       				  }else {
       					Swal.fire({
         	  				  position: 'top',
         	  				  icon: 'error',
         	  				  title: '資料刪除失敗，請重新嘗試',
         	  				  showConfirmButton: false,
         	  				  timer: 3000
         	  				})	
       				  }
       		  		}).finally(()=>{
       		  		   //刪除完成後再查詢 	
       	  			   this.getAll();
       		  		});  
               		 
					}		   
               	   });

	      },
	      openUpdateForm(empId) {
	  			axios.get("/emps/"+empId).then((response)=> {//成功時執行的回調函數
	  				//判斷操作是否成功
					 
	  			  if (response.data.flag && response.data.data!=null ) {
	  			  		this.formData = response.data.data;	 
				  }else {
//					  	bootstrap.Modal.getOrCreateInstance(document.getElementById('updateMoadl')).hide()
// 					  	this.hideModal = new bootstrap.Modal(document.getElementById('updateMoadl'));
// 					  	this.hideModal.hide();
						Swal.fire({
       	  				  position: 'top',
       	  				  icon: 'error',
       	  				  title: '資料獲取失敗，請重新嘗試',
       	  				  showConfirmButton: false,
       	  				  timer: 3000
       	  				})	
					  	
				  }
		  		}).finally(()=>{
		  			
		  		   //新增完成後再查詢 	
	  			   this.getAll();
		  		});
	  	  },
	  	 　updateEmp() {
	  			axios.put("/emps",this.formData).then((response)=> {//成功時執行的回調函數
	  			  //判斷操作是否成功
	  			  if (response.data.flag) {
	  				 //console.log(this.formData.empId);
	  				Toast.fire({
	  	                icon: 'success', title: '資料修改成功'
	  	            });
				  }else {
					  Toast.fire({
		  	                icon: 'error', title: '資料修改失敗，請重新嘗試'
		  	            });
				  }
		  		}).finally(()=>{	
		  		   //新增完成後再查詢一次，重整資料 	
	  			   this.getAll();			  
		  		});
	      },
	  	  //清空表單
          resetForm() {
              this.formData = {empGender:'男' ,check:false};          
             
          },
          userLogin(){
        	 const config ={
        		  withcredentials : true
        	  }
        	  axios.post("/users/newlogin2",this.loginForm,config).then((response)=> {//成功時執行的回調函數
	  			  //判斷操作是否成功
	  			  if (response.data.code=="001") {	
	  				Toast.fire({
	  	                icon: 'success', title: '登入成功'
	  	            });
	  				//let xx =JSON.parse(sessionStorage.getItem("loginUser")); 
	  				//console.log("session:"+xx);
	  				localStorage.setItem('userInfo',JSON.stringify(response.data.data));
	  				const userInfo = window.localStorage.getItem('userInfo')
		  			if(userInfo){
		  			this.userInfo=JSON.parse(userInfo);
		  			sessionStorage.setItem('test',userInfo);
		  			const sessionInfo =sessionStorage.getItem('test');
	  			    this.sessionInfo=JSON.parse(sessionInfo);
		  			}
	  				//window.location.href='/index.html';
				  }else {
					  Toast.fire({
		  	                icon: 'error', title: response.data.msg
		  	            });
				  }
 		  		}).finally(()=>{	
 		  		   //新增完成後再查詢一次，重整資料 	
	  			   this.getAll();			  
 		  		});    	  
          },
          userLogout(){
        	  axios.post("/users/logout").then((response)=> {//成功時執行的回調函數
	  			  //判斷操作是否成功
	  			  if (response.data.flag) {	
	  				Toast.fire({
	  	                icon: 'success', title: '登出成功'
	  	            });
	  				localStorage.removeItem('userInfo');
	  				localStorage.removeItem('loginUser');
	  				sessionStorage.removeItem('test');
	  				//window.location.href='/index.html';
	  				setTimeout("location.href='/index.html'",2000);//2秒後跳轉
				  }else {
					  Toast.fire({
		  	                icon: 'error', title: response.data.msg
		  	            });
				  }
 		  		}).finally(()=>{	
 		  		   //新增完成後再查詢一次，重整資料 	
	  			   //this.getAll();			  
 		  		});    	  
          },
         　updateStatus(item) {
        	   //this.empName=item.empName;
	  			axios.put("/emps",{empId:item.empId,empName:item.empName.length>2?"張三":"老李"}).then((response)=> {//成功時執行的回調函數
	  			  //對應的欄位名:數值 修改權限測試  三元運算判斷測試 姓名長度>2更新為張三 反之老李
	  			  //判斷操作是否成功
	  			  if (response.data.flag) {
	  				Toast.fire({
	  	                icon: 'success', title: '權限修改成功'
	  	            });
				  }else {
					  Toast.fire({
		  	                icon: 'error', title: '修改失敗，請重新嘗試'
		  	            });
				  }
		  		}).finally(()=>{	
		  		   //新增完成後再查詢一次，重整資料 	
	  			   this.getAll();			  
		  		});
	      },
	      addCategory() {
	  			axios.post("/categorys",this.categoryForm).then((response)=> {//成功時執行的回調函數
	  			  //判斷操作是否成功
	  			  if (response.data.flag) {	  				
	  				//this.$refs.Close.click();
//	  				Swal.fire({
//      	  				  position: 'top',
//      	  				  icon: 'success',
//      	  				  title: '新增成功',
//      	  				  showConfirmButton: false,
//      	  				  timer: 3000
//      	  				})		
	  				Toast.fire({
	  	                icon: 'success', title: '資料新增成功'
	  	            });
		 		  }else {
		 			  Toast.fire({
		  	                icon: 'error', title: response.data.msg
		  	            });
				  }
		  		}).finally(()=>{	
		  		   //新增完成後再查詢一次，重整資料 	
	  			   this.getAll();	  		  		  			 	
		  		});
	  	 },
	  	 removeCategory(categoryId){		  	　
		   	   Swal.fire({
          		title: "確定刪除此項資料(序號:" + categoryId + ")?",
          		text:"刪除後將無法復原，請謹慎操作！",
          		icon: "warning",
          		showCancelButton: true,
          		closeOnConfirm: false, //false=確認時關閉彈出框
          		showLoaderOnConfirm: true,
          		showCloseButton: true, //右上角x
          		confirmButtonClass: "btn-danger",
          	 	confirmButtonColor: '#d33',
          		cancelButtonText: "取消",
          		confirmButtonText: "刪除",
          	 	//reverseButtons: true //反轉按鈕位置讓確定在右側
      	   	   }).then((result)=>{
      		    if (result.isConfirmed) {			
      		     axios.delete("/categorys/"+categoryId).then((response)=> {//成功時執行的回調函數
	  			  //判斷操作是否成功
	  			  if (response.data.flag) {
	  				Swal.fire({
	  				  position: 'top',
	  				  icon: 'success',
	  				  title: '資料刪除成功',
	  				  showConfirmButton: false,
	  				  timer: 3000
	  				})				
				  }else {
					Swal.fire({
	  				  position: 'top',
	  				  icon: 'error',
	  				  title: response.data.msg,
	  				  showConfirmButton: false,
	  				  timer: 3000
	  				})	
				  }
		  		}).finally(()=>{
		  		   //刪除完成後再查詢 	
	  			   this.getAll();
		  		});  
      		 
			}		   
      	   });

 		  },
    	  testFile(){	//先調用上傳 上傳成功後處理url
		 		axios.post("/commons/upload").then(function (response) {
		 			//成功時執行的回調函數
		 			 //this.imageUrl='/commons/download/'+response.data.msg
		 		      console.log(response.data.msg);
		 		  });
	  	  },
	  	  testDownload(){	//先調用上傳 上傳成功後處理url
		 		axios.post("/commons/upload",this.fileForm).then(function (response) {
		 			//成功時執行的回調函數
		 			 this.imageUrl="/commons/download/"+response.data.msg
		 		      console.log(response);
		 		  });
	  	  },
	  	  fileChange(e) {
	            this.fileForm.append('file', e.target.files[0]) //放進上傳的檔案
	            console.log(e.target.files[0]);
	      },
	      fileSelected(e) {
	            const file = e.target.files.item(0);
	            const reader = new FileReader();
	            reader.addEventListener('load', this.imageLoaded);
	            reader.readAsDataURL(file);
	            this.fileChange(e);
	      },
	      imageLoaded(e) {
	            this.image = e.target.result;
	      },
	      getCategoryList(){
	    	    //get請求傳參數使用params {params:{type:1}} 或是/categorys/list?type=1拼接
    		  		axios.get("/categorys/list",{params:{type:1}}).then((response)=> {//成功時執行的回調函數
  		 		    if (response.data.flag) {	
  		 		        console.log(response.data);  		 		    
  		 		        console.log(response.data.data);   		 			
  		 		     	this.categoryList = response.data.data;
				   }else {
// 						Toast.fire({
// 			  	           icon: 'error', title: response.data.msg
// 			  	        });
					}
	 		  	  }).finally(()=>{	
		  
	 		  		});
	      },  
	      userCheck(){
        	  axios.post("/users/check",this.checkForm).then((response)=> {//成功時執行的回調函數
	  			  //判斷操作是否成功
	  			  console.log(response)
	  			  if (response.data.code==001) {	
	  				Toast.fire({
	  	                icon: 'success', title: response.data.msg
	  	            });
				  }else {
					  Toast.fire({
		  	                icon: 'error', title: response.data.msg
		  	            });
				  }
 		  		}).finally(()=>{	
 		  		   //新增完成後再查詢一次，重整資料 	
	  			   this.getAll();			  
 		  		});    	  
          },
    	  testAjax(){	
    		 		axios.post(
    		 			"/CardstoreSSM/test/ajax?id=1001",
    		 			{username:"admin",password:"123456"}		
    		 		).then(function (response) {//成功時執行的回調函數
    		 		      console.log(response);
    		 		  });
    	  },
    	  testRequestBody(){
    		  	axios.post(
    		  		 "/CardstoreSSM/test/RequestBody/json",
    		  		{username:"admin",password:"123456",age:23,gender:"男"}
    		   ).then(function (response) {//成功時執行的回調函數
		 		      console.log(response);
		 		  });
    	  },
    	  testResponseBody(){
    		  axios.post(
     		  		 "/CardstoreSSM/test/ResponseBody/json"
     		   ).then(function (response) {//成功時執行的回調函數
 		 		      console.log(response);
 		 		  });
    	  },
    	  storePageNum(){ //重整頁面時保存當前頁碼 不會因為重整回到第一頁
    		  let pageNum =Number(window.sessionStorage.getItem('currentPage')) || 1;
//     	  	  let query = window.sessionStorage.getItem('query')||"";
//     	  	  this.param=query;
    		  this.pagination.currentPage=pageNum;
//     		  if(this.pagination.empName!=''||this.pagination.empAge!=''||this.pagination.empGender!=''){
//     			  this.pagination.currentPage = 1;
//     		  }
    			  
    	  },
      }, //methods
      computed:{
    	  navpages:function(){
    	  	var current = this.pagination.currentPage
    	  	var total = this.pagination.pages
    	  	var page = []
    	    for (var i = 1; i <= total; i++) {
    	    	page.push(i)
    	        }
    	        var l = page.length
    	        if (l <= 10) { //總頁數<=10 顯示所有頁碼
    	          return page
    	        } else if (l > 10 && current <= 5) { // 總頁數>10 && 當前頁<=5
    	          return [1, 2, 3, 4, 5,6, '...', total] 
    	        } else if (l > 10 && current > 5 && current < l - 3) { // 總頁數 > 10 && 當前頁 > 5 && 當前頁 < 總頁數 - 3
    	          return [1, '...', current - 2, current - 1, current , current +1, current + 2, '...', total]  
    	        } else { // 總頁數 > 10 && 當前頁 > 5 && 當前頁 > 總頁數 - 3
    	          return [1, '...', total - 4, total - 3, total - 2, total - 1, total] 
    	        }   		  
    	  },         
      },      
    });
	app.component('my-component1',{template: '<h1>my-component1</h1>'});
    app.mount('#app')