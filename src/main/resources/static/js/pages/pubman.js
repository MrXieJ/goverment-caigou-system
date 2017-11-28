layui.use('element', function(){
  var element = layui.element;


});

layui.use('form', function(){
  var form = layui.form;

});

layui.use('table', function(){
    var table = layui.table;
    //执行渲染
    table.render({
    elem: '#pub_table', //指定原始表格元素选择器（推荐id选择器）
    height: 440, //容器高度
    page: true,
    cols: [[{ field: 'id', title: '序号', width: 100 },
           { field: 'name', title: '标题', width: 600 },
           { field: 'gender', title: '分类', width: 120 },
           { field: 'age', title: '浏览量', width: 120 },
           { field: 'phone', title: '发布时间', width: 160 },
           { field: 'idno', title: '操作', width: 100 }
           ]], //设置表头

});

});