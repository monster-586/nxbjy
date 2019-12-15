let vm = new Vue({
    el: '#containermyBox',
    data: {
        email: '',
        map: {
            account: '',
            password: '',
            code:''
        }
    },
    methods: {
        updatePassword:function () {
            axios({
                url: 'login/forget',
                method: 'post',
                data: this.map
            }).then(response => {
                layer.msg(response.data.msg)
            }).catch(function (error) {
                console.log(error)
            })
        },
        sendCode:function () {
            console.log(this.email)
            axios({
                url: 'email/send',
                method: 'get',
                params:{
                    email:this.email
                }
            }).then(response=>{
                layer.msg(response.data.msg)
            }).catch(function (error) {
                console.log(error)
            })
        },
        cancle: function () {
            let index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    },
    created: function () {
       this.map.account = parent.layer.account;
       console.log(this.map.account);
    },
    mounted: function () {

    }

});