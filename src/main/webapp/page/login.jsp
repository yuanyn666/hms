<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>登录</title>
    <link rel="stylesheet" href="../css/pintuer.css">
    <link rel="stylesheet" href="../css/admin.css">
    <script src="../js/jquery.js"></script>
    <script src="../js/pintuer.js"></script>
    <script type="text/javascript">
        $(function(){
        $("#login").click(function(){login()});
        function login() {
            if($('#u').val()==null||$('#u').val()==""){
               alert("登录账号不能为空！");
                return false;
            }else {
                if ($('#p').val() == "" || $('#p').val() == null) {
                    alert("登录密码不能为空！");
                    return false;
                } else {
                    if ($('#v').val() == "" || $('#v').val() == null){
                        alert("验证码不能为空！");
                        return false;
                    }else{
                    $.ajax({
                        url:"${pageContext.request.contextPath}/user/login.json",
                        data:{"accountNum":$('#u').val(),"password":$('#p').val(),"validate":$('#v').val()},
                        dataType:"json",
                        async:false,
                        success:function (data) {
                            if(data.status==true){
                                location.href="${pageContext.request.contextPath}/page/index.jsp";
                                $('#u').val("");
                                $('#p').val("");
                            }else{
                                alert(data.msg);
                                $('#u').val("");
                                $('#p').val("");
                                $('#v').val("");
                            }
                        }
                    });
                    }
                }
            }
        }
        });
    </script>
</head>
<body>
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">
            </div>
            <form action="index.jsp" method="post">
                <div class="panel loginbox">
                    <div class="text-center margin-big padding-big-top"><h1>Hotel Management</h1></div>
                    <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="text" id="u" class="input input-big" name="accountNum" placeholder="登录账号" data-validate="required:请填写账号" />
                                <span class="icon icon-user margin-small" id="umessage"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="password" id="p" class="input input-big" name="password" placeholder="登录密码" data-validate="required:请填写密码" />
                                <span class="icon icon-key margin-small" id="pmessage"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field">
                                <input type="text" id="v" class="input input-big" name="code" placeholder="填写右侧的验证码" data-validate="required:请填写右侧的验证码" />
                                <img src="${pageContext.request.contextPath}/validate/valid.json" alt="" width="100" height="32" class="passcode" style="height:43px;cursor:pointer;" onclick="this.src=this.src+Math.random();">

                            </div>
                        </div>
                    </div>
                    <div style="padding:30px;"><input type="button" id="login" class="button button-block bg-main text-big input-big" value="登录"></div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>