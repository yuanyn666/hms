$(function(){
    //检查房间号
    $("#roomNum").blur(function(){checkRoomNum()});
   //初始化房间类型
    initRoomType();

    function checkRoomNum(){
        $.ajax({
            url:"${pageContext.request.contextPath}/guestroom/checkRoomNum.json",
            data:{"roomNum":$('#roomNum').val()},
            dateType:"json",
            success:function(data){
                if(data.status==true){
                    return true;
                }if (data.status==false){
                    alertMsg(data.msg);
                    return false;
                }

            }
        });
    }

    function initRoomType() {
        $.ajax({
            url:"${pageContext.request.contextPath}/enum/roomTypeEnum.json",
            data:{},
            dateType:"json",
            success:function(data){
                for (var opt=0; opt<data.length;opt++){
                    $('#roomTypeSelect').options.add(new Option(data[opt].value,data[opt].key));
                }
            }
        });
    }
});
