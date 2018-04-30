<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="head.jsp"></jsp:include>
<form method="post" action="" id="listform">
    <div class="panel admin-panel">
        <div class="panel-head"><strong class="icon-reorder"> 客房信息</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
        <div class="padding border-bottom">
            <ul class="search" style="padding-left:10px;">
                <li> <a class="button border-main icon-plus-square-o" href="add.jsp"> 新增客房</a> </li>
                <li>搜索：</li>
                <li>客房类型
                    <select name="roomType" id="roomType"  class="input"  style="width:90px; line-height:17px; display:inline-block">
                        <option value="">选择</option>
                    </select>
                    &nbsp;&nbsp;
                    客房状态
                    <select name="roomStatus" id="roomStatus" class="input"   style="width:60px; line-height:17px;display:inline-block">
                        <option value="">选择</option>
                    </select>
                    &nbsp;&nbsp;

                <li>
                        房间号<input type="text" placeholder="请输入房间号" id="roomNum" name="roomNum" class="input" style="width:250px; line-height:17px;display:inline-block" />
                    <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()" > 搜索</a></li>
            </ul>
        </div>
        <table class="table">
          <tr>
                <th width="100" style="text-align:left; padding-left:20px;">序号</th>
                <th>房号</th>
                <th>房类型</th>
                <th>楼层</th>
                <th>设备</th>
                <th>价格</th>
                <th>门店</th>
                <th>面积</th>
                <th>描述</th>
                <th>状态</th>
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
        queryGuestRoom();
        //加载查询列表
        guestRoomType();
        guestRoomStatus();
    });
    
    //查询客房基础信息
    function queryGuestRoom(mm) {
        var index;
        if(mm==""||mm==0||mm==null||mm==undefined){
            index = 0;
        }else{
            index =mm-1;
        }
        $.ajax({
            url:"${pageContext.request.contextPath}/guestroom/queryGuestRoom.json",
            data:{"index":index,"roomType":$('#roomType').children('option:selected').val(),"status":$('#roomStatus').children('option:selected').val(),"roomNum":$('#roomNum').val()},
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
    //加载房间类型
    function guestRoomType(){
        $.ajax({
            url:'${pageContext.request.contextPath}/enum/roomTypeEnum.json',
            dataType:'json',
            type:'post',
            success:function (data) {
                for (var opt=0; opt<data.length;opt++){
                    $('#roomType').append("<option value='"+data[opt].key+"'>"+data[opt].value+"</option>");
                }
            }
        });
    }
    //加载房间状态
    function guestRoomStatus(){
        $.ajax({
            url:'${pageContext.request.contextPath}/enum/roomStatusEnum.json',
            dataType:'json',
            type:'post',
            success:function (data) {
                for (var opt=0; opt<data.length;opt++){
                    $('#roomStatus').append("<option value='"+data[opt].key+"'>"+data[opt].value+"</option>");
                }
            }
        });
    }
    //数据渲染
    function showRoomInfo(data){
        $("#vo")    .empty();
        if(data.length>0){
            for(var i=0;i<data.length;i++) {
                var count = i + 1;
                if (data[i].status == -1) {
                    $("#vo").append('<tr>' +
                        '<td style="text-align:left; padding-left:20px;">' +
                        '<input type="checkbox" name="id[]" value="" />' + count + '</td>' +
                        '<td>' + data[i].roomNum + '</td>' +
                        '<td>' + data[i].roomTypeText + '</td>' +
                        '<td>' + data[i].floorNum + '</td>' +
                        '<td>' + data[i].roomDevice + '</td>' +
                        '<td>' + data[i].privice + '</td>' +
                        '<td>' + data[i].warehouseName + '</td>' +
                        '<td>' + data[i].roomArea + '' + data[i].uint + '</td>' +
                        '<td>' + data[i].roomDescribe + '</td>' +
                        '<td><font color="red">' + data[i].statusText + '</font></td>' +
                        '<td>' + data[i].createDate + '</td>' +
                        '<td><div class="button-group"> <a class="button border-main" href="add.jsp?roomNum=' + data[i].roomNum + '"><span class="icon-edit"></span> 修改</a>' +
                        '                               <a class="button border-red" href="javascript:void(0)" onclick="return del(' +data[i].status+','+ data[i].roomNum + ')"><span class="icon-trash-o"></span> 停用</a>' +
                        ' </div></td>' +
                        '</tr>');

                } else if(data[i].status != -1) {
                    $("#vo").append('<tr>' +
                        '<td style="text-align:left; padding-left:20px;">' +
                        '<input type="checkbox" name="id[]" value="" />' + count + '</td>' +
                        '<td>' + data[i].roomNum + '</td>' +
                        '<td>' + data[i].roomTypeText + '</td>' +
                        '<td>' + data[i].floorNum + '</td>' +
                        '<td>' + data[i].roomDevice + '</td>' +
                        '<td>' + data[i].privice + '</td>' +
                        '<td>' + data[i].warehouseName + '</td>' +
                        '<td>' + data[i].roomArea + '' + data[i].uint + '</td>' +
                        '<td>' + data[i].roomDescribe + '</td>' +
                        '<td><font color="#00CC99">' + data[i].statusText + '</font></td>' +
                        '<td>' + data[i].createDate + '</td>' +
                        '<td><div class="button-group"> <a class="button border-main" href="add.jsp?roomNum=' + data[i].roomNum + '"><span class="icon-edit"></span> 修改</a>' +
                        '                               <a class="button border-red" href="javascript:void(0)" onclick="del(' +data[i].status+','+data[i].roomNum + ')"><span class="icon-trash-o"></span> 删除</a>' +
                        ' </div></td>' +
                        '</tr>');
                }
            }
        }
    }

    //搜索
    function changesearch(){
        queryGuestRoom();
    }
    //删除
    function remove(obj){
        $.ajax({
            url:'${pageContext.request.contextPath}/guestroom/remove.json',
            data:{'roomNum':obj},
            type:'post',
            dataType:'json',
            success:function (data) {
               if (data.status==true){
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
        if(status==-1){
            alertMsg("该房间已经停用！");
            return;
        }
        else if(confirm("您确定要删除吗?")){
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