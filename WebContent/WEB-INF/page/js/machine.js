
var data = {
        number:10,
    };

var  getUrlStr = function(name){
        /**
         * 获取地址栏参数
         */
        let reg = new RegExp("(^|\\?|&)" + name + "=([^&]*)(\\s|&|$)","i");
        if(reg.test(window.location.href)){
            return unescape(RegExp.$2.replace(/\+/g," "))
        }
        return undefined
    }

const router = new VueRouter({})
 
var vue = new Vue({
	router,
	el:'#app',
	data:data,
	mounted:function(){
		this.$router.onReady(() => {
		console.log(this.$route.query)
 		})
		var machine = getUrlStr("machine").replace("#/", "")
		console.log(machine)
		axios
	      .post('http://192.168.1.22:7456/Device/getmachinedata', {machine: machine})
	      .then(response => (console.log(response)))
	      .catch(function (error) { // 请求失败处理
	        console.log(error);
	      });
	},
	methods:{
		update:function(event){
			editslot = event.currentTarget.parentElement.parentElement
			editslotid = editslot.id
			slotid = editslotid.replace("edit", "")
		    $("#"+editslotid).hide()
		    $("#"+slotid).show()
		    
		},
		edit:function(event){
			slot = event.currentTarget.parentElement.parentElement
			slotid = slot.id
			editslot = "#edit" + slotid
		    $(editslot).show()
		    $("#"+slotid).hide()
		    
		},
		cancel:function(event){
			editslot = event.currentTarget.parentElement.parentElement
			editslotid = editslot.id
			slotid = editslotid.replace("edit", "")
		    $("#"+editslotid).hide()
		    $("#"+slotid).show()
		},
		del:function(event){
			event.currentTarget.parentElement.parentElement
		}
	}
	
})
