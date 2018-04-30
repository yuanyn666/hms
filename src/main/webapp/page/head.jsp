<%--
  Created by IntelliJ IDEA.
  User: wb-wyh270612
  Date: 2017/4/17
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="../css/pintuer.css">
    <link rel="stylesheet" href="../css/admin.css">
    <link rel="stylesheet" href="../css/page.css">
    <link rel="stylesheet" type="text/css" href="../css/xcConfirm.css"/>
    <link rel="stylesheet" href="../css/lyz.calendar.css">
    <link href="http://www.jq22.com/jquery/bootstrap-3.3.4.css" rel="stylesheet">
    <script src="../js/jquery.js"></script>
    <script src="../js/pintuer.js"></script>
    <script src="../js/page.js"></script>
    <script src="../js/distpicker.data.js"></script>
    <script src="../js/distpicker.js"></script>
    <script src="../js/main.js"></script>
    <script src="../js/lyz.calendar.min.js"></script>
    <script src="../js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
        $(function () {
            function alertMsg(data){
                var txt=  data;
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning);
            }
        })
    </script>
</head>
<body>
