let vm = new Vue({
    el: '#containermyBox',
    data: {
        map: {
            account: '',
            password: '',
            email: '',
            // comfpassword:''
        }
    },
    methods: {
        register: function () {
            console.log(this.map)
            axios({
                url: 'login/register',
                method: 'post',
                data: this.map
            }).then(response => {
                layer.msg(response.data.msg);
                // if (response.data.type == 1) {
                //     location.href = 'static/index.html';
                // }
            }).catch(function (error) {
                console.log(error)
            })
        },
        cancle: function () {
            let index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }

    },
    created: function () {

    },
    mounted: function () {

    }

});