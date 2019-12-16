let vm = new Vue({
    el: '#containermyBox',
    data: {
        map: {
            title: '',
            status:'',
            pageNum: 1,
            pageSize: 3,
        },
        pageInfo: {},
        addArticle:{},
    },
    methods: {
        listMeeting: function (pageNum, pageSize) {
            this.map.pageNum = pageNum;
            this.map.pageSize = pageSize;
            console.log(this.map)
            axios({
                url: 'manager/meeting/list',
                method: 'post',
                data: this.map
            }).then(response => {
                this.pageInfo = response.data;
                console.log(this.pageInfo.list)
            }).catch(function (error) {
                console.log(error)
            })
        }

    },
    created: function () {
        this.listMeeting(this.map.pageNum, this.map.pageSize);
    },
    mounted: function () {

    }

});