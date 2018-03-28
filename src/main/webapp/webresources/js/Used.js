//数组
var array = {
    var arr = [1, 'a', 'a', 'b', 'd', 'e', 'e', 1, 0];
    test = function test() {
        for (var i = 0; i < arr.length; i++) {
            for (var j = i + 1; j < arr.length; j++) {
                if (arr[i] === arr[j]) arr.splice(j, 1); // 如果前一个值与后一个值相等，那么就去掉后一个值，splice()可以修改原数组
            }
        }
        return arr;
    };
}

//闭包
var cierres = {
    //闭包测试一
    function A() {
        var text = "hello world";
        console.info('Aconsole');

        function B() {
            console.log('B ' + text);
        }
        return B;
    }
    A();
    console.log('A');
    var c = A();
    c();
    //闭包测试二
    function countFn() {
        var count = 1;
        return function () { //函数嵌套函数
            count++;
            console.log(count);
        }
    }
    var y = countFn(); //外部函数赋给变量y;
    y(); //2      //y函数调用一次，结果为2，相当于countFn()()
    y(); //3     //y函数调用第二次，结果为3，因为上一次调用的count还保存在内存中，没有被销毁，所以实现了累加
    y = null; //垃圾回收,释放内存
    y(); // y is not a function
    //循环使用闭包
    for (var i = 0; i < 10; i++) {
        var a = function () {
            console.log(i)
        }
        a() //依次为0--9
    }
    //循环闭包二
    //为setTimeout的一种机制，setTimeout是从任务队列结束的时候开始计时的，如果前面有进程没有结束，那么它就等到它结束再开始计时
    for (var i = 0; i < 10; i++) {
        setTimeout(function () {
            console.log(i); //10次10
        }, 1000);
    }
    //
    for (var i = 0; i < 10; i++) {
        var a = function (e) {
            return function () {
                console.log(e); //依次输入0--9
            }
        }
        setTimeout(a(i), 1000);
    }
    //
    for (var i = 0; i < 10; i++) {
        (function (e) {
            setTimeout(function () {
                console.log(e); //依次打印出0-9
            }, 0);
        })(i);
    }
    //
    //<ul id = "test" >
    //<li > 第一个 < /li> 
    //<li > 第二个 < /li> 
    //<li > 第三个 < /li> 
    //<li > 第四个 < /li> 
    //</ul>

    var nodes = document.getElementsByTagName("li");
    for (i = 0, len = nodes.length; i < len; i++) {
        nodes[i].onclick = function () {
            alert(i); //值全是4
        };
    }
    //
    var nodes = document.getElementsByTagName("li");
    for (var i = 0; i < nodes.length; i++) {
        (function (e) {
            nodes[i].onclick = function () {
                alert(e);
            };
        })(i)
    }
    //
    var nodes = document.getElementsByTagName('li');
    for (var i = 0; i < nodes.length; i++) {
        (function () {
            var temp = i;
            nodes[i].onclick = function () {
                alert(temp);
            }
        })();
    }
}

var jquery = {
    ready =
}


/**
 * jq ready 相当于在文档dom载入完成后就运行
 */
$(document).ready(function () {
    //获取第0位子元素，并添加class='active'
    $(".navbar").children().eq(0).addClass('active');
    $(".menu-second").eq(0).children().eq(0).children().addClass('active');
    //获取第0位子元素的第一位子元素，并添加css display:block
    $(".menu-first").children().eq(0).children().eq(1).css({
        'display': 'block'
    });
    //获取href的值，---attr('href','www.baidu.com')这样为修改href为百度
    var url = $(".menu-second").eq(0).children().eq(0).children().attr('href');
    //$("#ifr_right",parent.document),父窗口的id,这个js是写在一个iframe里。
    //通过这样可以获取父iframe里的元素
    $("#ifr_right", parent.document).attr("src", url);
});

/**
 * live('',function(){}) 这样可以保证使用ajax后面添加的元素，也能触发js
 * 从 jQuery 1.7 开始，不再建议使用 .live() 方法。请使用 .on() 来添加事件处理。使用旧版本的用户，应该优先使用 .delegate() 来替代 .live()。
 * on方法只能使用在页面上已有的标签；想获取未来元素，需要绑定在他的父元素上，具体写法如下：
 */
//1
$(".menu-first li").live('click', function () {
    var index = $(this).index();
    $(".navbar").children(".active").removeAttr("class");
    $(".navbar").children().eq(index).addClass('active');
})
//2  on方法只能使用在页面上已有的标签；想获取未来元素   无法点击完成事件
// 在这个上点击事件 <td><a class="topic_a" href="#creat"  name='${data.context }'>选择</a></td>///
$("td").on("click", "a", function () {
    alert("Aha!");
});
//要改为这样
$(document).on('click', 'td a', function () {
    alert("Aha!");
});

/**
 * AJAX 异步刷新页面
 * @param pageNo
 * @returns
 */
function querySmUser(pageNo) {
    var queryParam = {
        "queryParam.loginid": loginId,
        "queryParam.name": name
    };
    $.ajax({
        type: "POST",
        url: "qy/user/smuserlisttopage.action?pageNo=" + pageNo + "&&isAjax=0",
        data: queryParam,
        dataType: "html",
        success: function (data) {
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
function queryUserBase(e) {
    var num = [];
    //name=staff 的多选框情况
    $('input:checkbox[name="staff"]:checked').each(function () {
        num.push($(this).val());
    });
}

/**
 * 单选框全选
 * @param e
 * @returns
 */
function checkAll(e) {
    //获取class 的长度
    var length = $(".labelCheck").length;
    //循环选择
    for (var i = 0; i < length; i++) {
        $(".labelCheck")[i].checked = e.checked;
    }
}

/**
 *商品全选
 **/
$(document).on('click', '.selectAll_shop', function () {
    var chknum = $("#list :checkbox").size(); //选项总个数
    var chk = 0; //选中的商品个数
    //将选定的id使用逗号隔开
    $("#list :checkbox").each(function () {
        if ($(this).prop("checked") == true) {
            chk++;
        }
    });
    if (chknum == chk) { //全不选
        $('.notice_goodsUl li .aui-checkbox').prop("checked", false);
        $('.yiSelected span').html(0);
    } else { //全选
        $('.notice_goodsUl li .aui-checkbox').prop("checked", true);
        $('.yiSelected span').html($('.notice_goodsUl li').length);
    }
});
/**
 *
 * @param e
 * @returns
 */
function checkAll(e) {
    //查找每个 p 元素的所有类名为 "selected" 的所有同胞元素
    //查看w3c 遍历
    $("p").siblings(".selected");
    //对设置和移除所有 <p> 元素的 "main" 类进行切换
    //toggleClass() 对设置或移除被选元素的一个或多个类进行切换。
    $("button").click(function () {
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
    var idcard = $("#staffIdCard").val();
    var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    if (reg.test(idcard) === false) {
        $("#mess").text("身份证不合法");
        $(e).attr('href', '#tishiModal');
        return false;
    };
}

//返回yyyy-MM-dd HH:mm:ss
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var strHours = date.getHours();
    var strMin = date.getMinutes();
    var strSec = date.getSeconds();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (strHours >= 0 && strHours <= 9) {
        strHours = "0" + strHours;
    }
    if (strMin >= 0 && strMin <= 9) {
        strMin = "0" + strMin;
    }
    if (strSec >= 0 && strSec <= 9) {
        strSec = "0" + strSec;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate +
        " " + strHours + seperator2 + strMin + seperator2 + strSec;
    return currentdate;
}

$(".labelName").select2({
    tags: true,
    ajax: {
        url: "customerLabelController.do?select2Json",
        dataType: 'json',
        delay: 250,
        data: function (params) {
            return {
                q: params.term,
            };
        },
        processResults: function (data) {
            return {
                results: data
            };
        },
        cache: true
    },
});

function testPass(str) {
    var rC = {
        lW: '[a-z]',
        uW: '[A-Z]',
        nW: '[0-9]',
        sW: '[\\u0020-\\u002F\\u003A-\\u0040\\u005B-\\u0060\\u007B-\\u007E]'
    };

    function Reg(str, rStr) {
        var reg = new RegExp(rStr);
        if (reg.test(str)) return true;
        else return false;
    }
    if (str.length < 6) {
        //document.title = '您的密码长度太短';  
        alert('您的密码长度太短');
        return false;
    } else {
        var tR = {
            l: Reg(str, rC.lW),
            u: Reg(str, rC.uW),
            n: Reg(str, rC.nW),
            s: Reg(str, rC.sW)
        };
        if ((tR.l && tR.u && tR.n) || (tR.l && tR.u && tR.s) || (tR.s && tR.u && tR.n) || (tR.s && tR.l && tR.n)) {
            //document.title = '密码符合要求';  
            alert('密码符合要求');
            return true;
        } else {
            alert('您的密码必须含有“小写字母”、“大写字母”、“数字”、“特殊符号”中的任意三种');
            //document.title = '您的密码必须含有“小写字母”、“大写字母”、“数字”、“特殊符号”中的任意三种';  
            return false;
        }
    }
}

<!--select2 -->
$(".labelName").change(function () {
    var checkValue = ""; //获取value,多值使用逗号隔开
    var checkText = "";
    var selected = $(".labelName").select2('data'); //选择的值
    for (var i = 0; i < selected.length; i++) {
        checkValue += selected[i].id + ",";
        checkText += selected[i].text + ",";
    }
    alert(checkValue + "--" + checkText);
    $("#labelName").val(checkText);
    $("#labelId").val(checkValue);
});

<!--选择下拉框选中的值 -->
var id = $("#selectId option:selected").val();


// 闭包 第一种 自调用
/*var addc =(function () {
        var counter =0;
        var funb =function () {
			return counter+=10;
        }
        var func = function () {
            funb();
            return counter+=1;
        };
        return func;
    })();*/
//闭包 第二种 ，手动调用一次
function addc() {
    var counter =0;
    var funb =function () {
        return counter+=10;
    }
    var func = function () {
        funb();
        return counter+=1;
    };
    return func;
};
var addcf = addc();
// addcf();
function con() {
    console.info(addcf());
}
