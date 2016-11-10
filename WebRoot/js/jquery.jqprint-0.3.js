// -----------------------------------------------------------------------
// Eros Fratini - eros@recoding.it
// jqprint 0.3
//
// - 19/06/2009 - some new implementations, added Opera support
// - 11/05/2009 - first sketch
//
// Printing plug-in for jQuery, evolution of jPrintArea: http://plugins.jquery.com/project/jPrintArea
// requires jQuery 1.3.x
//
// Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.php
//------------------------------------------------------------------------

(function($) {
    var opt;

    $.fn.jqprint = function (options) {
        opt = $.extend({}, $.fn.jqprint.defaults, options);

        var $element = (this instanceof jQuery) ? this : $(this);

        var win=1140;  //1080

        var fullpagebodysize=18;

        var winpi =window.screen.deviceXDPI;

        var tableheight=$(this).innerHeight(); //1219

        var printbodyheight=$(opt.printbody).height();//59

        var extrabodysiez=Math.round((tableheight-win)/printbodyheight)-1;

        var pageNo = Math.ceil(extrabodysiez/fullpagebodysize);

        var nowPageNo = Math.ceil(extrabodysiez/fullpagebodysize);

        var printbody=$(opt.printbody).html();

        var bodysize=$(opt.printbody).length;

        var bodyhtml=new Array(bodysize);
        
        if (opt.operaSupport && $.browser.opera) 
        { 
            var tab = window.open("","jqPrint-preview");
            tab.document.open();

            var doc = tab.document;
        }
        else 
        {
            var $iframe = $("<iframe  />");
        
            if (!opt.debug) { $iframe.css({ position: "absolute", width: "0px", height: "0px", left: "-600px", top: "-600px" }); }

            $iframe.appendTo("body");
            var doc = $iframe[0].contentWindow.document;
        }
        
        if (opt.importCSS)
        {
            if ($("link[media=print]").length > 0) 
            {
                $("link[media=print]").each( function() {
                    doc.write("<link type='text/css' rel='stylesheet' href='" + $(this).attr("href") + "' media='print' />");
                });
            }
            else 
            {
                $("link").each( function() {
                    doc.write("<link type='text/css' rel='stylesheet' href='" + $(this).attr("href") + "' />");
                });
            }
        }

        //移除多余的元素信息,存放入数组中
        var i=0;
        while( i <extrabodysiez){
            var k=bodysize-i;
            var e=extrabodysiez-i;
            bodyhtml[i]=$(".printbody").eq(k-e).html();
             $(".printbody").eq(k-e).remove();
            i++;
        }
        //移除多余的元素信息,存放入数组中

        if (opt.printContainer) { doc.write($element.outer()); }
        else { $element.each( function() { doc.write($(this).html()); }); }

        //表头的判断,判断需要打印的页数,然后根据情况添加表头
        
        if(nowPageNo>=2){
            for (var i = 1; i < nowPageNo; i++) {
                doc.write(opt.printhead);
                    for (var j =(i*fullpagebodysize)-fullpagebodysize ; j < i*fullpagebodysize; j++) {
                        doc.write("<tr>");
                        doc.write(bodyhtml[j]);
                        doc.write("</tr>");
                    };
                doc.write("</table>");
            };
            doc.write("<br><br>");
        }
        
        if(nowPageNo>0){
                doc.write(opt.printhead);
                    for (var i =(nowPageNo-1)*fullpagebodysize; i < extrabodysiez; i++) {
                        doc.write("<tr>");
                        doc.write(bodyhtml[i]);
                        doc.write("</tr>");
                    };
                doc.write("</table>");
            };
        
        //表头的判断,判断需要打印的页数,然后根据情况添加表头
        doc.close();
    
        (opt.operaSupport && $.browser.opera ? tab : $iframe[0].contentWindow).focus();
        setTimeout( function() { (opt.operaSupport && $.browser.opera ? tab : $iframe[0].contentWindow).print(); if (tab) { tab.close(); } }, 1000);
    }
    
    $.fn.jqprint.defaults = {
		debug: false,
		importCSS: true, 
		printContainer: true,
		operaSupport: true,
        printbody:"", //重复数据的jq选择器
        printhead:""  //添加表头
	};

    // Thanks to 9__, found at http://users.livejournal.com/9__/380664.html
    jQuery.fn.outer = function() {
      return $($('<div></div>').html(this.clone())).html();
    } 
})(jQuery);