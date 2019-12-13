let em = new Vue({
    el: '',
    data: {
        user:{}
    },
    methods: {
        getUser:function () {
            axios({
                url: 'login/loginUser',
                method: 'get',
            }).then(response => {
                this.user = response.data;
            }).catch(function (error) {
                console.log(error)
            })
        },
        loginOut:function () {

        }
    },
    created: function () {
        this.getUser()
    }


})