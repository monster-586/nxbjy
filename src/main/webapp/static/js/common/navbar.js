let em = new Vue({
    el: '#loginOutDiv',
    data: {
    },
    methods: {
        loginOut:function () {
            axios({
                url: 'login/loginOut',
                method: 'get',
            }).
            then().
            catch(function (error) {
                console.log(error)
            })
        }
    },
    created: function () {

    }
})