<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="head.jsp"></jsp:include>
<form method="post" action="" id="listform">
    <div class="panel admin-panel">
        <div class="panel-head"><strong class="icon-reorder"> 门店信息</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
        <div class="padding border-bottom">
            <ul class="search" style="padding-left:10px;">
                <li> <a class="button border-main icon-plus-square-o" href="addWarehouse.jsp"> 开设门店</a> </li>
                <li>搜索：</li>
                <li>门店编码<input type="text" placeholder="请输入编码" id="warehouseCode" name="warehouseCode" class="input" style="width:100px; line-height:17px;display:inline-block" /></li>
                <li>门店名称<input type="text" placeholder="请输入门店名" id="warehouseName" name="warehouseName" class="input" style="width:105px; line-height:17px;display:inline-block" /></li>
                <li>星级<select name="score" id="score"  class="input"  style="width:60px; line-height:17px; display:inline-block">
                    <option value="">选择</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select></li>
                <li>日期 <input id="txtBeginDate" name="createBeginDate" style="width:100px;padding:7px 10px;border:1px solid #ccc;margin-right:10px;"/>
                    <input id="txtEndDate" name="createEndDate" style="width:100px;padding:7px 10px;border:1px solid #ccc;" /></li>
                <li><a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()" > 搜索</a></li>
            </ul>
        </div>
        <table class="table">
          <tr>
                <th width="100" style="text-align:left; padding-left:20px;">序号</th>
                <th>门店编码</th>
                <th>门店名称</th>
                <th>地址</th>
                <th>负责人</th>
                <th>星级</th>
                <th>电话</th>
                <th width="10%">创建时间</th>
                <th width="310">操作</th>
            </tr>
            <tbody id="vo">

            </tbody>
                <tr>
                    <%--<td colspan="8"><div class="pagelist"> <a href="">上一页</a> <span class="current">1</span><a href="">2</a><a href="">3</a><a href="">下一页</a><a href="">尾页</a> </div></td>--%>
                    <td colspan="8"><div class="pagelist"><ul class="page" maxshowpageitem="5" pagelistcount="10"  id="page"></ul></div></td>
                </tr>
        </table>
    </div>
</form>
<script type="text/javascript">

    $(document).ready(function(){
        queryWarehouse();
        //加载查询列表
       // guestRoomType();
       // guestRoomStatus();
        $("#txtBeginDate").calendar({
            controlId: "divDate",                                 // 弹出的日期控件ID，默认: $(this).attr("id") + "Calendar"
            speed: 200,                                           // 三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000),默认：200
            complement: true,                                     // 是否显示日期或年空白处的前后月的补充,默认：true
            readonly: true,                                       // 目标对象是否设为只读，默认：true
            upperLimit: new Date(),                               // 日期上限，默认：NaN(不限制)
            lowerLimit: new Date("2011/01/01"),                   // 日期下限，默认：NaN(不限制)
            callback: function () {                               // 点击选择日期后的回调函数
            }
        });
        $("#txtEndDate").calendar();
    });

    //查询门店基础信息
    function queryWarehouse(mm) {
        var index;
        if(mm==""||mm==0||mm==null||mm==undefined){
            index = 0;
        }else{
            index =mm-1;
        }
        $.ajax({
            url:"${pageContext.request.contextPath}/warehouse/queryWarehouse.json",
            data:{"index":index,"warehouseCode":$('#warehouseCode').val(),"warehouseName":$('#warehouseName').val(),"score":$('#score').children('option:selected').val(),"createBeginDate":$("#txtBeginDate").val(),"createEndDate":$("#txtBeginDate").val()},
            dataType:'json',
            type:'post',
            success:function (data) {
                if(data.status==true){
                   // $("#page").initPage(71,1,GG.kk);
                    //渲染页面
                    showRoomInfo(data.result.list);
                    $("#page").initPage(data.result.totalNum,data.result.index+1,GG.kk);
                }else{
                    alertError(data.msg);
                }
            }
        });
    }
    var GG = {
        "kk":function(mm){
            queryGuestRoom(mm);
        }
    }
    //数据渲染
    function showRoomInfo(data){
        $("#vo").empty();
        if(data.length>0){
            for(var i=0;i<data.length;i++) {
                var count = i + 1;
                    $("#vo").append('<tr>' +
                        '<td style="text-align:left; padding-left:20px;">' +
                        '<input type="checkbox" name="id[]" value="" />' + count + '</td>' +
                        '<td>' + data[i].warehouseCode + '</td>' +
                        '<td>' + data[i].warehouseName + '</td>' +
                        '<td>' + data[i].address + '</td>' +
                        '<td>' + data[i].warehouseMg + '</td>' +
                        '<td>' + data[i].score + '</td>' +
                        '<td>' + data[i].tel + '</td>' +
                        '<td>' + data[i].createData + '</td>' +
                        '<td><div class="button-group"><a class="button border-red" href="javascript:void(0)" onclick="return del(' +data[i].status+','+ data[i].roomNum + ')"><span class="icon-trash-o"></span> 关闭</a>' +
                        ' </div></td>' +
                        '</tr>');
            }
        }
    }

    //搜索
    function changesearch(){
        queryWarehouse();
    }
    //删除
    function remove(obj){
        $.ajax({
            url:'${pageContext.request.contextPath}/guestroom/remove.json',
            data:{'roomNum':obj},
            type:'post',
            dataType:'json',
            success:function (data) {
               if (data==true){
                   alertSuccess("删除成功！");
                   location.href="guestRoom.jsp";
               }
               if(data==false){
                   alertError(data.msg);
               }
            }
        });
    }

    //单个删除
    function del(status,roomNum){
        if(status=-1){
            alertMsg("该房间已经停用！");
            return;
        }
        if(confirm("您确定要删除吗?")){
            remove(roomNum);
        }
    }

    //全选
    $("#checkall").click(function(){
        $("input[name='id[]']").each(function(){
            if (this.checked) {
                this.checked = false;
            }
            else {
                this.checked = true;
            }
        });
    })

    //批量删除
    function DelSelect(){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){
            var t=confirm("您确认要删除选中的内容吗？");
            if (t==false) return false;
            $("#listform").submit();
        }
        else{
            alertMsg("请选择您要删除的内容!");
            return false;
        }
    }

    //批量排序
    function sorts(){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){

            $("#listform").submit();
        }
        else{
            alert("请选择要操作的内容!");
            return false;
        }
    }


    //批量首页显示
    function changeishome(o){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){

            $("#listform").submit();
        }
        else{
            alert("请选择要操作的内容!");

            return false;
        }
    }

    //批量推荐
    function changeisvouch(o){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){


            $("#listform").submit();
        }
        else{
            alert("请选择要操作的内容!");

            return false;
        }
    }

    //批量置顶
    function changeistop(o){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){

            $("#listform").submit();
        }
        else{
            alert("请选择要操作的内容!");

            return false;
        }
    }


    //批量移动
    function changecate(o){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){

            $("#listform").submit();
        }
        else{
            alert("请选择要操作的内容!");

            return false;
        }
    }

    //批量复制
    function changecopy(o){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){
            var i = 0;
            $("input[name='id[]']").each(function(){
                if (this.checked==true) {
                    i++;
                }
            });
            if(i>1){
                alert("只能选择一条信息!");
                $(o).find("option:first").prop("selected","selected");
            }else{

                $("#listform").submit();
            }
        }
        else{
            alert("请选择要复制的内容!");
            $(o).find("option:first").prop("selected","selected");
            return false;
        }
    }
    //警告弹框
    function alertMsg(data){
        var txt=  data;
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning);
    }

    //成功弹框
    function alertSuccess(data) {
        var txt=  data;
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success);
    }

    function alertError(data) {
        var txt=  data;
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
    }

</script>
</body>
</html>