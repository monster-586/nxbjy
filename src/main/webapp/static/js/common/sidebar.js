let e = new Vue({
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
        },
        changePic:function () {
            axios({
                url: 'login/changePic',
                method: 'get',
            }).then(response => {
                this.loginUser = response.data;
            }).catch(function (error) {
                console.log(error)
            })
        },
        userDetail: function () {
            // console.log(this.loginUser.id);
                layer.uId = this.loginUser.id;
                let vm = layer.open({
                    type: 2,
                    title: "详细信息",
                    content: 'html/user_look.html',
                    area: ['60%', '80%'],
                    end: () => {
                        console.log("**********");
                    }
                })


        }

    },
    created: function () {
        this.getUser();
    }


})