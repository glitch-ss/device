
var  getUrlStr = function(name){
        /**
         * 获取地址栏参数
         */
        let reg = new RegExp("(^|\\?|&)" + name + "=([^&]*)(\\s|&|$)","i");
        if(reg.test(window.location.href)){
            return unescape(RegExp.$2.replace(/\+/g," "))
        }
        return undefined
    };
console.log($("#update").text());
var machine = getUrlStr("machine").replace("#/", "");
$("#update").click(function(){
	console.log('test');
	$.ajax({
		  type: 'POST',
		  url: 'http://10.182.95.143:7456/Device/updatemachine',
		  data: {machine: machine},
		  success: function(){
		  	console.log("success")
		  	window.location.reload()
		  },
		});
});


