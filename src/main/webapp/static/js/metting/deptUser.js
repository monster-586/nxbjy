let vm = new Vue({
    el: '#containermyBox',
    data: {
        map: {
            pageNum: 1,
            pageSize: 3,
        },
        pageInfo: {},
        deptUser:{},

    },
    methods: {
        listDept: function (pageNum, pageSize) {
            this.map.pageNum = pageNum;
            this.map.pageSize = pageSize;
            axios({
                url: 'manager/dept/list',
                method: 'post',
                data: this.map
            }).then(response => {
                this.pageInfo = response.data;
                console.log(response.data)
            }).catch(function (error) {
                console.log(error)
            })
        },
        userDetail: function (uid) {
            axios({
                url: 'manager/user/userDetail',
                method: 'get',
                params: {
                    uId: uid
                }
            }).then(response => {
                if (response.data.sysuser.isSecret == 0) {
                    layer.msg(response.data.msg)
                } else {
                    layer.user = response.data.sysuser;
                    let vm = layer.open({
                        type: 2,
                        title: "详细信息",
                        content: 'html/user_detail.html',
                        area: ['60%', '80%'],
                        end: () => {
                            console.log("**********");
                        }
                    })
                }
                // this.user = response.data;
            }).catch(function (error) {
                console.log(error)
            })
        },




    },
    created: function () {
        this.listDept(this.map.pageNum, this.map.pageSize);
        // this.dpUser();
    },
    mounted: function () {

    }

});