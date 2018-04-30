<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="head.jsp"></jsp:include>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span id="sp" class="icon-pencil-square-o">添加客房</span></strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="">

      <div class="form-group">
        <div class="label">
          <label>房间号：</label>
        </div>
        <div class="field">
          <input id="roomNum" type="text" class="input w50" value="${param.roomNum}" name="roomNum" <%--data-validate="required:请输入房间号"--%> />
          <div class="tips"></div>
        </div>
      </div>

      <div class="form-group">
        <div class="label">
          <label>房间类型：</label>
        </div>
        <div class="field">
          <select id="roomTypeSelect" name="roomType" class="input w50">
            <option value="">请选择类型</option>
          </select>
           <div class="tips"></div>
         </div>
      </div>

      <div class="form-group">
        <div class="label">
          <label>所在楼层：</label>
        </div>
        <div class="field" style="padding-top:8px;">
          1 <input name="floorNum" value="1"  type="radio" />
          2 <input name="floorNum" value="2"  type="radio" />
          3 <input name="floorNum" value="3"  type="radio" />
          4 <input name="floorNum" value="4"  type="radio" />
          5 <input name="floorNum" value="5"  type="radio" />
          6 <input name="floorNum" value="6"  type="radio" />
          7 <input name="floorNum" value="7"  type="radio" />
          8 <input name="floorNum" value="8"  type="radio" />
        </div>
      </div>

      <div class="form-group">
        <div class="label">
          <label>价格：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" id="price" name="privice"/>
          <div class="tips"></div>
        </div>
      </div>

      <div class="form-group">
        <div class="label">
          <label>所属门店：</label>
        </div>
        <div class="field">
          <select id="warehouse" name="warehouseCode" class="input w50">
            <option value="">请选择门店</option>
          </select>
          <div class="tips"></div>
        </div>
      </div>

      <div class="form-group">
        <div class="label">
          <label>面积：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" id="roomArea" name="roomArea" plachholder="单位为㎡" /><span id="uint"></span>
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
          <label>设备：</label>
        </div>
        <div class="field" style="padding-top:8px;">
          <span>有窗</span> <input  name="roomDevice" value="1" type="checkbox" />
          <span>大床(1.5m)</span> <input name="roomDevice" value="2"  type="checkbox" />
          <span>双床</span> <input  name="roomDevice" value="3" type="checkbox" />
          <span>无线</span> <input  name="roomDevice" value="4" type="checkbox" />
          <span>浴室</span> <input  name="roomDevice" value="5" type="checkbox" />
          <span>液晶电视</span> <input  name="roomDevice" value="6" type="checkbox" />
          <span>零食</span> <input  name="roomDevice"  value="7" type="checkbox" />
        </div>
      </div>

      <div class="form-group">
        <div class="label">
          <label>描述：</label>
        </div>
        <div class="field">
          <textarea class="input" id="roomDescribe" name="roomDescribe" style=" height:90px;"></textarea>
          <div class="tips"></div>
        </div>
      </div>

      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" id="modify" type="button"> 提交</button>
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
       queryByRoomNum();

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
            //检查房间号
            $("#roomNum").blur(function(){checkRoomNum(this)});
            //初始化房间类型
            $('#roomTypeSelect').change(function () {selectVal("请选择房间类型")});
            //选择门店
            $('#warehouse').change(function () {selectVal1("请选择门店")});
            //验证price
            $('#price').blur(function(){ validatePrice(this)});
            //验证面积
            $('#roomArea').blur(function () { validateArea(this)});
            //验证描述
            $('#roomDescribe').blur(function () {validateDescribe(this) });
            //重置
            $("#reset").click(function () {resetVal()});
            //返回
            $("#return").click(function () { location.href="guestRoom.jsp"});
            //发送请求
            $("#modify").click(function () {alert("0"); submit()});
        }

        function checkRoomNum(obj){
            if($('#roomNum').val()==''||$('#roomNum').val()==null){
                addValidate(obj,"请输入房间号");
                $('#roomNum').focus();
                return false;
            }else{
                removeValidate(obj,"");
                return true;
            }
            /*
            $.ajax({

                data:{"roomNum":$('#roomNum').val()},
                dateType:"json",
                success:function(data){
                    if(data.status==true){
                        removeValidate(obj,"");
                    }if (data.status==false){
                        addValidate(obj,"该房间号已经存在!");
                        $('#roomNum').focus();
                    }

                }
            });*/
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
        //验证下拉框是否有值
        function selectVal(msg){
            if($('#roomTypeSelect').children('option:selected').val()==''||$('#roomTypeSelect').children('option:selected').val()==null){
                removeValidate($('#roomTypeSelect'),"");
                addValidate($('#roomTypeSelect'),msg);
                return false;
            }else{
                removeValidate($('#roomTypeSelect'),"");
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

        //验证radio是否有值
        function radioVal(){
            var val=$('input:radio[name="floorNum"]:checked').val();
            if(val==null||val==''){
                alertMsg("请选择楼层");
                return false;
            }else{
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
            $.ajax({
                url:"${pageContext.request.contextPath}/guestroom/update.json",
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