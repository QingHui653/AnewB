(function($){
	$.fn.tableCheck = function(allCheckboxClass){
		var allCheck = $(this).find("th").find(':checkbox');
		var checks = $(this).find('td').find(':checkbox');
		var defaults = {
			selectedRowClass:"active",
		}
		$(this).find(":checkbox").prop("checked",false);
		allCheck.click(function(){
			var set = $(this).parents('table').find('td').find(':checkbox');
			if($(this).prop("checked")){
				$.each(set,function(i,v){
					$(v).prop("checked",true);
				});
			}else{
				$.each(set,function(i,v){
					$(v).prop("checked",false);
				});
			}
		});

		checks.click(function(e){
			e.stopPropagation();
			var leng = $(this).parents("table").find('td').find(':checkbox:checked').length;
			if(leng == checks.length){
				allCheck.prop('checked',true);
			}else{
				allCheck.prop("checked",false);
			}
		});
	}
	/*$(".table_tab").tableCheck();*/
	/**
	 * 查找class为checkAll的全选按钮
	 */
	$(document).on("click",".checkAll",function(){
		var checkAll =$(this).parents('.table_tab').find(".checkAll");
		var table=$(this).parents('.table_tab');
	    if($(checkAll).prop("checked")){ 
	    	$(table).find(".checkSelect").prop('checked', true)
	    }else{ 
	    	$(table).find(".checkSelect").prop('checked', false)
	    } 
	});
	
	/**
	 * 查找class为checkSelect的多选按钮
	 */
	$(document).on("click",".checkSelect",function(){
		var allCheck = $(this).parents("table").find("th").find(':checkbox');
		var checksLeng=$(this).parents("table").find('td').find(':checkbox').length;
		var leng = $(this).parents("table").find('td').find(':checkbox:checked').length;
		if(leng == checksLeng){
			allCheck.prop('checked',true);
		}else{
			allCheck.prop('checked',false);
		}
	});
	
})(jQuery);