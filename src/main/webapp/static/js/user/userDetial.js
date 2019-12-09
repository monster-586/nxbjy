let vm = new Vue({
    el: '#containermyBox',
    data: {
        user:{}
    },
    methods: {},
    created: function () {

    },
    mounted: function () {
        this.user = parent.layer.user;
    }

});