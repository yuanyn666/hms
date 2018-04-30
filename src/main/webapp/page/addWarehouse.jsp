<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="head.jsp"></jsp:include>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span id="sp" class="icon-pencil-square-o">门店信息</span></strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="">

      <div class="form-group">
        <div class="label">
          <label>门店编码：</label>
        </div>
        <div class="field">
          <input id="warehouseCode" type="text" class="input w50" value="${param.warehouseCode}" name="warehouseCode" <%--data-validate="required:请输入房间号"--%> />
          <div class="tips"></div>
        </div>
      </div>

      <div class="form-group">
        <div class="label">
          <label>门店名称：</label>
        </div>
        <div class="field">
          <input id="warehouseName" type="text" class="input w50" name="warehouseName" <%--data-validate="required:请输入房间号"--%> />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>地址：</label>
        </div>
        <div class="field">
            <div id="distpicker1">
              <label class="sr-only"  for="province4">Province</label>
              <select class="form-control"   style="width:90px; line-height:17px; display:inline-block"  id="province4"></select>
              <label class="sr-only"    for="city4">City</label>
              <select class="form-control" style="width:90px; line-height:17px; display:inline-block" id="city4"></select>
              <label class="sr-only" for="district4">District</label>
              <select class="form-control"  style="width:90px; line-height:17px; display:inline-block" id="district4"></select>
            </div>
          <input id="address" type="text" class="input w50"  plachholder="详细地址，街道" name="address" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>星级：</label>
        </div>
        <div class="field">
            <div class="revinp">
				<span class="level">
					<i class="level_solid" cjmark=""></i>
					<i class="level_solid" cjmark=""></i>
					<i class="level_solid" cjmark=""></i>
					<i class="level_solid" cjmark=""></i>
					<i class="level_solid" cjmark=""></i>
				</span>
              <span class="revgrade"></span>
            </div>
           <div class="tips"></div>
         </div>
      </div>

      <div class="form-group">
        <div class="label">
          <label>负责人：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" id="warehouseMg" name="warehouseMg"/>
          <div class="tips"></div>
        </div>
      </div>

      <div class="form-group">
        <div class="label">
          <label>联系方式：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" id="roomArea" name="roomArea" plachholder="tel" /><span id="tel"></span>
          <div class="tips"></div>
        </div>
      </div>

     <%-- <div class="form-group">
        <div class="label">
          <label>实体图片：</label>
        </div>
        <div class="field">
          <input type="text" id="url2" name="img" class="input tips" style="width:25%; float:left;"  value=""  data-toggle="hover" data-place="right" data-image="" />
          <input type="button" class="button bg-blue margin-left" id="image2" value="+ 浏览上传"  style="float:left;">
          <div class="tipss">图片尺寸：500*500</div>
        </div>
      </div>--%>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" id="submit" type="button"> 提交</button>
          <button class="button bg-main icon-ban-circle" id="reset" type="button"> 重置</button>
          <button class="button bg-main icon-reply" id="return" type="button"> 返回</button>
        </div>
      </div>
    </form>
  </div>
</div>
<script type="text/javascript">
    $(function(){
        initRoomType();
        initWarehouse();
        //初始化状态
        if ($('#roomNum').val()!=null&&$('#roomNum').val()!=""){
            queryByRoomNum();
            save();
        }else{
            resetVal();
            save();
        }

        //star
        var degree = ['','','','','','',''];
        $(document).on('mouseover','i[cjmark]',function(){
            var num = $(this).index();
            var list = $(this).parent().find('i');
            for(var i=0;i<=num;i++){
                list.eq(i).attr('class','level_solid');
            }
            for(var i=num+1,len=list.length-1;i<=len;i++){
                list.eq(i).attr('class','level_hollow');
            }
            $(this).parent().next().html(degree[num+1]);
        })
        //渲染页面
        function queryByRoomNum(){
            $('#sp').html('');
            $('#sp').html('修改客房');
            $.ajax({
                url:"${pageContext.request.contextPath}/guestroom/queryByRoomNum.json",
                data:{"roomNum":$('#roomNum').val()},
                dateType:"json",
                success:function(data){
                  if (data.status==true){
                      resetVal();
                      $('#roomNum').val(data.result.roomNum);
                      $("#roomTypeSelect").find("option[value="+data.result.roomType+"]").attr("selected",true);
                      $(":radio[name='floorNum'][value='" + data.result.floorNum + "']").prop("checked", "checked");
                      $("#price").val(data.result.privice);
                      $("#warehouse").find("option[value="+data.result.warehouseCode+"]").attr("selected",true);
                      $("#roomArea").val(data.result.roomArea);
                      $("#roomDescribe").val(data.result.roomDescribe);
                      var d = data.result.roomdevice;
                      /*var devices= d.split(",");
                      $("input[type='checkbox']").each(function(){
                          if(devices.concat(this.val))
                              this.checked=true;});*/
                   }
                }
        });
        }

        //添加操作
        function save(){
            //检查门店编码
            $("#warehouseCode").blur(function(){checkWarehouseCode(this)});
            //检查门店名称
            $("#warehouseName").blur(function(){checkWarehouseName(this)});
            //检查街道
            $("#address").blur(function(){checkAddress(this)});

            //重置
            $("#reset").click(function () {resetVal()});
            //返回
            $("#return").click(function () { location.href="guestRoom.jsp"});
            //发送请求
            $("#submit").click(function () { submit()});
        }

        function checkWarehouseCode(obj){
            if($('#warehouseCode').val()==''||$('#warehouseCode').val()==null){
                addValidate(obj,"请输入编码");
                $('#warehouseCode').focus();
                return false;
            }else{
                removeValidate(obj,"");
                return true;
            }
        }
        function checkWarehouseName(obj){
            if($('#warehouseName').val()==''||$('#warehouseName').val()==null){
                addValidate(obj,"请输入门店名");
                $('#warehouseName').focus();
                return false;
            }else{
                removeValidate(obj,"");
                return true;
            }
        }
        function checkAddress(obj){
            if($('#address').val()==''||$('#address').val()==null){
                addValidate(obj,"请输详细街道");
                $('#address').focus();
                return false;
            }else{
                removeValidate(obj,"");
                return true;
            }
        }

        function initRoomType() {
            $.ajax({
                url:"${pageContext.request.contextPath}/enum/roomTypeEnum.json",
                data:{},
                dateType:"json",
                success:function(data){
                    for (var opt=0; opt<data.length;opt++){
                        $('#roomTypeSelect').append("<option value='"+data[opt].key+"'>"+data[opt].value+"</option>");
                    }
                }
            });
        }

        function initWarehouse() {
            $.ajax({
                url:"${pageContext.request.contextPath}/warehouse/codeName.json",
                data:{},
                dateType:"json",
                success:function(data){
                    var list =data.result;
                    for (var opt=0; opt<list.length;opt++){
                        $('#warehouse').append("<option value='"+list[opt].code+"'>"+list[opt].value+"</option>");
                    }
                }
            });
        }

        function validatePrice(obj){
            if($('#price').val()==''||$('#price').val()==null){
                addValidate(obj,"请输入价格!");
                $('#price').focus();
                return false;
            }
            if (isNaN($('#price').val())){
               addValidate(obj,"请输入数字!");
               $('#price').focus();
               return false;
            }else{
                removeValidate(obj,"");
                return true;
            }
        }

        function validateArea(obj) {
            if($('#roomArea').val()==''||$('#roomArea').val()==null){
                addValidate(obj,"请输入房间大小!");
                $('#roomArea').focus();
                return false;
            }
            if (isNaN($('#roomArea').val())){
                addValidate(obj,"请输入数字!");
                $('#roomArea').focus();
                return false;
            }else{
                removeValidate(obj,"");
                return true;
            }
        }

        function validateDescribe(obj) {
            if($('#roomDescribe').val()==''||$('#roomDescribe').val()==null){
                addValidate(obj,"请填写描述");
                $('#roomDescribe').focus();
                return false;
            }else{
                removeValidate(obj,"");
                return true;
            }
        }

        function selectVal1(msg){
            if($('#warehouse').children('option:selected').val()==''||$('#warehouse').children('option:selected').val()==null){
                removeValidate($('#warehouse'),"");
                addValidate($('#warehouse'),msg);
                return false;
            }else{
                removeValidate($('#warehouse'),"");
                return true;
            }
        }



        //重置
        function resetVal(){
            $('#roomNum').val('');
            $("#roomTypeSelect option[value='']").attr("selected", "selected");
            $("input[type='radio']").removeAttr('checked');
            $("#price").val('');
            $("#warehouse option[value='']").attr("selected","selected");
            $("#roomArea").val('');
            $("input[type='checkbox']").each(function(){this.checked=false;});
            $("#roomDescribe").val('');
        }

        //提交
        function submit(){
            //验证房间类型
            var a =checkRoomNum($('#roomNum'));
            var b = selectVal("请选择房间类型");
            var c =radioVal();
            var d =validatePrice($('#price'));
            var e =validateArea($('#roomArea'));
            var f =selectVal1("请选择门店");
            var g =validateDescribe($('#roomDescribe'));
            if(a&&b&&c&&e&&f&&g&&d){
                saveRoom();
            }
        }


        function saveRoom(){
            var roomDevices="";
            $("input[name='roomDevice']:checkbox").each(function(){
                if (true == $(this).attr("checked")) {
                    roomDevices +="roomDevice"+":"+ $(this).attr('value')+',';
                }});
            var url;
            if($('#sp').html()=="修改客房"){
                url="${pageContext.request.contextPath}/guestroom/update.json";
            }else{
                url="${pageContext.request.contextPath}/guestroom/save.json";
            }
            $.ajax({
                url:url,
                type: "post",
                data: $("form").serialize(),
                dataType:"json",
                traditional:true,
                success:function (data) {
                         if(data.status==true){
                             alertSuccess("操作成功");
                             location.href="guestRoom.jsp";
                         }if(data.status==false){
                            alertError(data.msg);
                         }
                }
            });
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

        function addValidate(obj,msg){
            var e = $(obj);
            e.closest('.field').children(".input-help").remove();
            e.closest('.form-group').removeClass("check-success");
            e.closest('.form-group').addClass("check-error");
            e.closest('.field').append('<div class="input-help"><ul>'+msg+'</ul></div>');
        }

        function removeValidate(obj,msg){
            var e = $(obj);
            e.closest('.field').children(".input-help").remove();
            e.closest('.form-group').removeClass("check-error");
            e.closest('.form-group').addClass("check-success");
            e.closest('.field').append('<div class="input-help"><ul>'+msg+'</ul></div>');
        }
    });

</script>
<jsp:include page="foot.jsp"></jsp:include>