let vm = new Vue({
    el: '#containermyBox',
    data: {
        map: {
            realname: '',
            pageNum: 1,
            pageSize: 3,
        },
        uId: '',
        user: {},
        pageInfo: {},
        focusUser: {}
    },
    methods: {
        listuser: function (pageNum, pageSize) {
            this.map.pageNum = pageNum;
            this.map.pageSize = pageSize;
            // console.log(this.map)
            axios({
                url: 'manager/user/list',
                method: 'post',
                data: this.map
            }).then(response => {
                this.pageInfo = response.data;
                // console.log(this.pageInfo.list)
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
                // this.user = response.data;
            }).catch(function (error) {
                console.log(error)
            })
        },
        changeFocus: function (fUId) {
            axios({
                url: 'manager/user/changeFocus',
                method: 'get',
                params: {
                    FocusUid: fUId
                }
            }).then(response => {
                layer.msg(response.data.msg);
            }).catch(function (error) {
                console.log(error)
            })
        }

    },
    created: function () {
        this.listuser(this.map.pageNum, this.map.pageSize);
        this.getFocus();
    },
    mounted: function () {

    }

});