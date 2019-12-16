let vm = new Vue({
    el: '#containermyBox',
    data: {
        listDept: {},
        listUser: {},
        map: {
            title: '',
            deptId: '',
            makeUser: [],
            startTime: '',
            endTime: '',
            content: ''
        }

    },
    methods: {
        selectDept: function () {
            axios({
                url: 'manager/dept/selectAll',
                method: 'get',
            }).then(response => {
                this.listDept = response.data;
                // console.log(response.data)
            }).catch(function (error) {
                console.log(error)
            })
        },
        selectUser: function () {
            dId = $("#deptId").val();
            axios({
                url: 'manager/dept/selectUser',
                method: 'get',
                params: {
                    deptId: dId
                }
            }).then(response => {
                this.listUser = response.data;
                console.log(response.data)
            }).catch(function (error) {
                console.log(error)
            })
        },
        save: function () {
            this.map.title = $("#title").val();
            this.map.deptId = $("#deptId").val();
            this.map.makeUser = $("#userId").val();
            this.map.startTime = $("#startTime").val();
            this.map.endTime = $("#endTime").val();
            this.map.content = $("#content").val();
            console.log(this.map)
            axios({
                url: 'manager/dept/save',
                method: 'post',
                data: this.map
            }).then(response => {
                 layer.msg(response.data);
                console.log(response.data)
            }).catch(function (error) {
                console.log(error)
            })
        }

    },
    created: function () {
        this.selectDept();
        // $("#deptId").append('<option value="" selected>请选择部门</option>');

    },
    mounted: function () {

    },
    updated: function () {
        $("#deptId").selectpicker('refresh');
        $("#userId").selectpicker('refresh');
    }

});