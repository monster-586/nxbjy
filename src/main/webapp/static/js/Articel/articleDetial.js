let vm = new Vue({
    el: '#containermyBox',
    data: {
        article: {},

    },
    methods: {
        changeFavorite: function (aId) {
            axios({
                url: 'manager/article/changeFavorite',
                method: 'get',
                params: {
                    articleId: aId
                }
            }).then(response => {
                layer.msg(response.data.msg);

            }).catch(function (error) {
                this.userDetail();
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
                if(response.data.sysuser.isSecret==0){
                    layer.msg("对方已私密")
                }else {
                    layer.user = response.data.sysuser;
                    let vm = layer.open({
                        type: 2,
                        title: "详细信息",
                        content: 'html/user_detail.html',
                        area: ['100%', '100%'],
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

    },
    mounted: function () {
        this.article = parent.layer.article;
        console.log(this.article)
    }

});