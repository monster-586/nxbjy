let en = new Vue({
    el: '#loginOutDiv',
    data: {
    },
    methods: {
        loginOut:function () {
            axios({
                url: 'login/loginOut',
                method: 'get',
            }).
            then(response => {
                location.href = 'static/index.html'
            }).
            catch(function (error) {
                console.log(error)
            })
        }
    },
    created: function () {

    }
})