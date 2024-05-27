var ZXXFILE = {
	fileInput: null,				//html file控件
	dragDrop: null,					//拖拽敏感区域
	upButton: null,					//提交按钮
	url: "toUp",						//ajax地址
	fileFilter: [],					//过滤后的文件数组
	filter: function(files) {		//选择文件组的过滤方法
		return files;	
	},
	onSelect: function() {},		//文件选择后
	onDelete: function() {},		//文件删除后
	onDragOver: function() {},		//文件拖拽到敏感区域时
	onDragLeave: function() {},	//文件离开到敏感区域时
	onProgress: function() {},		//文件上传进度
	onSuccess: function() {},		//文件上传成功时
	onFailure: function() {},		//文件上传失败时,
	onComplete: function() {
		
	},		//文件全部上传完毕时
	
	/* 开发参数和内置方法分界线 */
	
	//文件拖放
	funDragHover: function(e) {
		e.stopPropagation();
		e.preventDefault();
		this[e.type === "dragover"? "onDragOver": "onDragLeave"].call(e.target);
		return this;
	},
	//获取选择文件，file控件或拖放
	funGetFiles: function(e) {
		// 取消鼠标经过样式
		this.funDragHover(e);
				
		// 获取文件列表对象
		var files = e.target.files || e.dataTransfer.files;
		//继续添加文件
		this.fileFilter = this.fileFilter.concat(this.filter(files));
		this.funDealFiles();
		return this;
	},
	
	//选中文件的处理与回调
	funDealFiles: function() {
		for (var i = 0, file; file = this.fileFilter[i]; i++) {
			//增加唯一索引值
			file.index = i;
		}
		//执行选择回调
		this.onSelect(this.fileFilter);
		return this;
	},
	
	//删除对应的文件
	funDeleteFile: function(fileDelete) {
		
		var arrFile = [];
		//var j=0;
		//file
		for (var i = 0; file = this.fileFilter[i];i++) {
			    //删除时，将原来所有图片的原始索引，修改成
			//新索引
			if (file != fileDelete) {
				arrFile.push(file);
				//$(file).=j;
				//debugger;
//				arrFile[j]=file;
//				j++;
			} else {
				this.onDelete(fileDelete);	
			}
		}
		this.fileFilter = arrFile;
		return this;
	},
	
	//文件上传
	funUploadFile: function() {
		var self = this;	
		if (location.host.indexOf("sitepointstatic") >= 0) {
			//非站点服务器上运行
			return;	
		}
		var form = new FormData(); // FormData 对象
		var leng=this.fileFilter.length;
		for(var i=0;i<leng;i++){
			var fileObj = this.fileFilter[i];
   		 form.append("mf"+i, fileObj); // 文件对象
		}
		var xhr = new XMLHttpRequest();//创建上传对象
		xhr.open("post", self.url, true);
		xhr.onload = ZXXFILE.funDelete;//完成要执行的方法
		xhr.send(form);
		if (xhr.upload) {
			// 上传中
			xhr.upload.addEventListener("progress", function(e) {
				self.onProgress(file, e.loaded, e.total);
			}, false);
		}
			xhr.onreadystatechange = function(e) {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						debugger;
							self.onSuccess(file,xhr.responseText);
							self.onComplete();
						//self.funDeleteFile(file);
						/*
						if (!self.fileFilter.length) {
								//全部完毕
							   debugger;
							   self.onComplete();
							   debugger;
							    self.onSuccess();
							    
									
						}*/
					} else {
							self.onFailure(file, xhr.responseText);		
					}
				}
			};
      },
	
	init: function() {
		var self = this;
		
		if (this.dragDrop) {
			this.dragDrop.addEventListener("dragover", function(e) { self.funDragHover(e); }, false);
			this.dragDrop.addEventListener("dragleave", function(e) { self.funDragHover(e); }, false);
			this.dragDrop.addEventListener("drop", function(e) { self.funGetFiles(e); }, false);
		}
		
		//文件选择控件选择
		if (this.fileInput) {
			this.fileInput.addEventListener("change", function(e) { self.funGetFiles(e); }, false);	
		}
		
		//上传按钮提交
		if (this.upButton) {
			this.upButton.addEventListener("click", function(e) { self.funUploadFile(e); }, false);	
		} 
	},
	funDelete:function(){
		ZXXFILE.funDeleteFile(ZXXFILE.fileFilter);
	}
};



