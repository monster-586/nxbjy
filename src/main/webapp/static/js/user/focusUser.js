let vm = new Vue({
    el: '#containermyBox',
    data: {
        map: {
            pageNum: 1,
            pageSize: 5,
        },
        pageInfo:{}

    },
    methods: {
        getFocus: function (pageNum, pageSize) {
            axios({
                url: 'manager/user/getFocus',
                method: 'post',
                data: this.map
            }).then(response => {
                this.pageInfo = response.data;
                console.log(this.pageInfo.list)
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
                layer.user = response.data;
                let vm = layer.open({
                    type: 2,
                    title: "详细信息",
                    content: 'html/user_detail.html',
                    area: ['60%', '80%'],
                    end: () => {
                        console.log("**********");
                    }
                })
                this.user = response.data;
            }).catch(function (error) {
                console.log(error)
            })
        },
    },
    created: function () {
        this.getFocus();
    },
    mounted: function () {
    }

});