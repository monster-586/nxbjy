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
            }).catch(function (error) {
                console.log(error)
            })
        },
        dpUser: function (dpId) {
            axios({
                url: 'manager/dept/deptUser',
                method: 'get',
           params:{
                    deptId:dpId
           }
            }).then(response => {
                this.deptUser = response.data;
            }).catch(function (error) {
                console.log(error)
            })
        },



    },
    created: function () {
        this.listDept(this.map.pageNum, this.map.pageSize);
        this.dpUser();
    },
    mounted: function () {

    }

});