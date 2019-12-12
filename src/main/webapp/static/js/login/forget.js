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
                url: 'email/updatePassword',
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
        }
    },
    created: function () {
       this.map.account = parent.layer.account;
       console.log(this.map.account);
    },
    mounted: function () {

    }

});