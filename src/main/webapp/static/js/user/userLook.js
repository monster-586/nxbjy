let vm = new Vue({
    el: '#containermyBox',
    data: {
        user: {},
        uid: '',

    },
    methods: {
        myDetail: function () {
            axios({
                url: 'manager/user/userDetail',
                method: 'get',
                params: {
                    uId:this.uid
                }

            }).then(response => {
                this.user = response.data.sysuser;
                console.log(response.data.sysuser)
            }).catch(function (error) {
                console.log(error)
            })
        },
        save:function () {
            axios({
                url: 'manager/user/updateUser',
                method: 'post',
                data:this.user
            }).then(response => {

// layer.msg(response.data.msg)
            }).catch(function (error) {
                console.log(error)
            })
        },
        check:function () {
            this.user.isSecret=!this.user.isSecret;
        }


    },
    created: function () {
        this.uid = parent.layer.uId;
        this.myDetail();
    },
    mounted: function () {

    }

});