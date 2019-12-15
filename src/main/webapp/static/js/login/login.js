let vm = new Vue({
    el: '#containermyBox',
    data: {
        remember:'',
        map: {
            account: '',
            password: '',
            code: '',
            remember:true
        }
    },
    methods: {
        changeCode: function (e) {
            // console.log(e.target.src)
            e.target.src = "getCode.jpg?" + new Date();
        },
        checkLogin: function () {
            console.log(this.map)
            axios({
                url: 'login/check',
                method: 'post',
                data: this.map
            }).then(response => {
                layer.msg(response.data.msg);
                if (response.data.sysuser != null) {
                    location.href = 'html/home.html'
                }

            }).catch(function (error) {
                console.log(error)
            })
        },
        toregister: function () {
            let upd = layer.open({
                type: 2,
                title: "注册",
                content: 'html/register.html',
                area: ['60%', '80%'],
                end: () => {
                    console.log("**********");
                }
            });
        },
        toforget: function () {
            layer.account=this.map.account;
            let upd = layer.open({
                type: 2,
                title: "忘记密码",
                content: 'html/forget.html',
                area: ['60%', '80%'],
                end: () => {
                    console.log("**********");
                }
            });
        },
        checkbox:function () {
            console.log(this.map)
            this.map.remember=!this.map.remember
        }

    },


});