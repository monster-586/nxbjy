let vm = new Vue({
    el: '#containermyBox',
    data: {
        map: {
            title: '',
            pageNum: 1,
            pageSize: 3,
        },
        pageInfo: {},

    },
    methods: {
        getFavoriteByCondition: function (pageNum, pageSize) {
            this.map.pageNum = pageNum;
            this.map.pageSize = pageSize;
            console.log(this.map)
            axios({
                url: 'manager/article/myFavorite',
                method: 'post',
                data: this.map
            }).then(response => {
                this.pageInfo = response.data;
                console.log(response.data);
            }).catch(function (error) {
                console.log(error)
            })
        },
        articleDetail: function (articelId) {
            axios({
                url: 'manager/article/articleDetail',
                method: 'get',
                params:{
                    aId:articelId
                }
            }).then(response => {
                layer.article = response.data.article;
                let vm = layer.open({
                    type: 2,
                    title: "文章详情",
                    content: 'html/article_detail.html',
                    area: ['70%', '90%'],
                    end: () => {
                        console.log("**********");
                    }
                })
            }).catch(function (error) {
                console.log(error)
            })
        }

    },
    created: function () {
        this.getFavoriteByCondition(this.map.pageNum, this.map.pageSize);
    },
    mounted: function () {

    }

});