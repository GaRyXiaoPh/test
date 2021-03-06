

//对象初始化
var Bill = {
    id: "UserBillTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

//初始化表格的列
Bill.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '用户ID', field: 'userId', visible: true, align: 'center', valign: 'middle'},
        {title: '用户名', field: 'userName', visible: true, align: 'center', valign: 'middle'},
        {title: '币名', field: 'coinName', visible: true, align: 'center', valign: 'middle'},
        {title: '资金变化原因', field: 'reasonStr', visible: true, align: 'center', valign: 'middle'},
        {title: '数额', field: 'changeAmount', visible: true, align: 'center', valign: 'middle'},
        {title: '备注', field: 'comment', visible: true, align: 'center', valign: 'middle'},
        {title: '时间', field: 'lastTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

//查询管理员登录日志列表
Bill.search = function () {
    var queryData = {};
    queryData['types'] = $("#types").val();
    queryData['userName'] = $("#userName").val();
    Bill.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Bill.initColumn();
    var table = new BSTable(Bill.id, "/full/bill/list", defaultColunms);
    //table.setPaginationType("client");
    Bill.table = table.init();
});
