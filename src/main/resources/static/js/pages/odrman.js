layui.use('element', function(){
  var element = layui.element;


});

layui.use('form', function(){
  var form = layui.form;

});

layui.use('laydate', function(){
    var laydate = layui.laydate;

    //执行一个laydate实例
    laydate.render({
        elem: '#time1', //指定元素
        type: 'datetime',
        format: 'y-MM-dd HH:mm:ss'
    });

    laydate.render({
            elem: '#time2', //指定元素
            type: 'datetime',
            format: 'y-MM-dd HH:mm:ss'
    });
});

var table;
layui.use('table', function(){
    table = layui.table;
    //执行渲染
    table.on('tool(odr)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值
        var tr = obj.tr; //获得当前行 tr 的DOM对象

        if(layEvent === 'detail'){

            console.log(data);

            $('#purchased_time').html(data.purchased_time);
            $('#indent_number').html(data.indent_number);
            $('#indent_status').html(data.indent_status);
            $('#doctor_name').html(data.doctor_name);
            $('#patient_name').html(data.patient_name);
            $('#patient_phone').html(data.patient_phone);
            $('#service_name').html(data.name);
            $('#service_duration').html(data.duration);
            $('#sum_count').html(data.sum_count);
            $('#left_count').html(data.left_count);
            $('#price').html('￥' + data.price);
            $('#pay').html('￥' + data.price);

            $('#service_start_time').html(data.purchased_time);

            var dt = data.purchased_time.replace('-', '/');
            var t = new Date(new Date(dt).valueOf() + data.duration * 24 * 60 * 60 * 1000);

            $('#service_end_time').html(t.Format("yyyy-MM-dd hh:mm:ss"));

            $('#modal').removeAttr('hidden');
        }
    });

});

var search = function(){

    var option = {
        where: {}
    };

    if ( $('input[name="status"]:checked').val() != -1 ) {
        option.where['status'] = $('input[name="status"]:checked').val()
    }

    if ( $('input[name="name"]').val() != '') {
            option.where['name'] = $('input[name="name"]').val()
        }

    if ( $('input[name="doc_name"]').val() != '') {
        option.where['docName'] = $('input[name="doc_name"]').val()
    }

    if ( $('input[name="pat_name"]').val() != '') {
        option.where['patName'] = $('input[name="pat_name"]').val()
    }

    if ( $('#time1').val() != '') {
        option.where['startTime'] = $('#time1').val()
    }

    if ( $('#time2').val() != '') {
        option.where['endTime'] = $('#time2').val()
    }



    table.reload('odr_table', option);
};