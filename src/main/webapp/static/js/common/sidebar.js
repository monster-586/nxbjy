let em = new Vue({
    el: '#loginSidebar',
    data: {
        loginUser:{}
    },
    methods: {
        getUser:function () {
            axios({
                url: 'login/sidebarUser',
                method: 'get',
            }).then(response => {
                this.loginUser = response.data;
            }).catch(function (error) {
                console.log(error)
            })
        }

    },
    created: function () {
        this.getUser();
    }


})