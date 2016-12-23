	
	/**
	 * jq ready 相当于在文档dom载入完成后就运行
	 */
	$(document).ready(function(){
			//获取第0位子元素，并添加class='active'
    		$(".navbar").children().eq(0).addClass('active');
    		$(".menu-second").eq(0).children().eq(0).children().addClass('active');
    		//获取第0位子元素的第一位子元素，并添加css display:block
    		$(".menu-first").children().eq(0).children().eq(1).css({'display':'block'});
    		//获取href的值，---attr('href','www.baidu.com')这样为修改href为百度
    		var url=$(".menu-second").eq(0).children().eq(0).children().attr('href');
    		//$("#ifr_right",parent.document),父窗口的id,这个js是写在一个iframe里。
    		//通过这样可以获取父iframe里的元素
    		$("#ifr_right",parent.document).attr("src",url);
    	});
	
	/**
	 * live('',function(){}) 这样可以保证使用ajax后面添加的元素，也能触发js
	 * 从 jQuery 1.7 开始，不再建议使用 .live() 方法。请使用 .on() 来添加事件处理。使用旧版本的用户，应该优先使用 .delegate() 来替代 .live()。
	 * on方法只能使用在页面上已有的标签；想获取未来元素，只能用delegate方法了，具体写法如下：
	 */
	//1
	$(".menu-first li").live('click',function(){
    	var index=$(this).index();
    	$(".navbar").children(".active").removeAttr("class");
    	$(".navbar").children().eq(index).addClass('active');
    })
    //2  on方法只能使用在页面上已有的标签；想获取未来元素   无法点击完成事件
    // 在这个上点击事件 <td><a class="topic_a" href="#creat"  name='${data.context }'>选择</a></td>///
    $("td").on("click","a",function(){
    	alert("Aha!");
    });
    //要改为这样
    $(document).on('click', 'td a', function() {
        alert("Aha!");
      });
    
    /**
     * AJAX 异步刷新页面
     * @param pageNo
     * @returns
     */
    function querySmUser(pageNo){
		var queryParam={"queryParam.loginid":loginId,"queryParam.name":name};
		$.ajax({
			type: "POST",
			url: "qy/user/smuserlisttopage.action?pageNo="+pageNo+"&&isAjax=0",
			data:queryParam,
			dataType:"html",
			success: function(data){
				//ajax返回的数据为jsp页面(jsp页面片段)
				//empty 清空id下的数据，然后，把jsp页面写入进，皆可实现不刷新替换页面
				$("#userTable").empty();
				$("#userTable").html(data);
			}
		})
	}
    
    /**
     *  获取多选框的选择情况
     * @param e
     * @returns
     */
    function queryUserBase(e){
		var num =[];
		//name=staff 的多选框情况
		$('input:checkbox[name="staff"]:checked').each(function(){    
			num.push($(this).val());    
		});
	}
    
    /**
     * 单选框全选
     * @param e
     * @returns
     */
    function checkAll(e){
    	//获取class 的长度
		var length = $(".labelCheck").length;
		//循环选择
		for(var i = 0; i < length;i++){
			$(".labelCheck")[i].checked = e.checked;
		}
	}
    /**
     * 
     * @param e
     * @returns
     */
    function checkAll(e){
    	//查找每个 p 元素的所有类名为 "selected" 的所有同胞元素
    	//查看w3c 遍历
    	$("p").siblings(".selected");
    	//对设置和移除所有 <p> 元素的 "main" 类进行切换
    	//toggleClass() 对设置或移除被选元素的一个或多个类进行切换。
    	$("button").click(function(){
    		  $("p").toggleClass("main");
    		});
    }
    /**
     * 正则表达式 表达式去
     * regexUtil 工具类中找
     * @param s
     * @returns
     */
    function reg(s) { 
    	var idcard=$("#staffIdCard").val();
        var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        if (reg.test(idcard) === false) {
        	$("#mess").text("身份证不合法");
			$(e).attr('href','#tishiModal');
            return false;};
	}
    
    
    
    