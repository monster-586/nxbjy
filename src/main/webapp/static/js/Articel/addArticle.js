let vm = new Vue({
    el: '#containermyBox',
    data: {
        map: {
            title: '',
            content: '',

        }
    },
    methods: {
        save: function () {
            console.log(this.map)
            axios({
                url: 'manager/article/save',
                method: 'post',
                data: this.map
            }).then(response => {
                layer.msg(response.data.msg);
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