<%@ page import="com.hotel_mg.entity.UserDO" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charest=UTF8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="base_path" value="${pageContext.request.contextPath }"></c:set>
<%
    UserDO userDO = (UserDO) request.getSession().getAttribute("employee");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>酒店管理系统</title>
    <link rel="stylesheet" type="text/css"
          href="../../css/default.css" />·1
    <link rel="stylesheet" type="text/css"
          href="../../easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css"
          href="../../easyui/themes/icon.css" />
    <script type="text/javascript" src="../../easyui/jquery.min.js"></script>
    <script type="text/javascript"
            src="../../easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="../../easyui/locale/easyui-lang-zh_CN.js"></script>

   <%-- <script type="text/javascript" src='../../easyui/init.js'></script>--%>
    <script type="text/javascript">
    </script>

</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
<div region="north" split="true" border="false" style="overflow: hidden; height: 30px;background: url(../../images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
    <span style="float:right; padding-right:20px;" class="head">欢迎:<%--<% userDO.getEmployeeName();%>--%>  <a href="#" id="loginOut">注销</a></span>
    <span style="padding-left:10px; font-size: 16px; ">
        <img src="../../images/blocks.gif" width="20" height="20"
                                                            align="absmiddle"/>Hotel management</span>
</div>
<div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
    <div class="footer">By yuanyn666@163.com</div>
</div>
<div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
    <div id="nav" class="easyui-accordion" fit="true" border="false">
        <div title="前台接待" data-options="iconCls:'icon ${parent_menu.resourceIcon}'">
            <ul>
                <li>
                    <div><a href="javascript:void(0)" ref="${child_menu.id }" href="#"
                            rel="${base_path}/${child_menu.resourceUrl} ">
                        <span class="icon ${child_menu.resourceIcon}"
                              title="${child_menu.resourceIcon}">&nbsp;</span><span class="nav">实时房态</span></a>
                    </div>
                </li>

                <li>
                    <div><a href="javascript:void(0)" ref="${child_menu.id }" href="#"
                            rel="${base_path}/${child_menu.resourceUrl} ">
                        <span class="icon ${child_menu.resourceIcon}"
                              title="${child_menu.resourceIcon}">&nbsp;</span><span class="nav">预定</span></a>
                    </div>
                </li>

                <li>
                    <div><a href="javascript:void(0)" ref="${child_menu.id }" href="#"
                            rel="${base_path}/${child_menu.resourceUrl} ">
                        <span class="icon ${child_menu.resourceIcon}"
                              title="${child_menu.resourceIcon}">&nbsp;</span><span class="nav">入住</span></a>
                    </div>
                </li>

                <li>
                    <div><a href="javascript:void(0)" ref="${child_menu.id }" href="#"
                            rel="${base_path}/${child_menu.resourceUrl} ">
                        <span class="icon ${child_menu.resourceIcon}"
                              title="${child_menu.resourceIcon}">&nbsp;</span><span class="nav">结算</span></a>
                    </div>
                </li>
            </ul>
        </div>

        <div title="客户" data-options="iconCls:'icon ${parent_menu.resourceIcon}'">
            <ul>
                <li>
                    <div><a href="javascript:void(0)" ref="${child_menu.id }" href="#"
                            rel="${base_path}/${child_menu.resourceUrl} ">
                        <span class="icon ${child_menu.resourceIcon}"
                              title="${child_menu.resourceIcon}">&nbsp;</span><span class="nav">散客</span></a>
                    </div>
                </li>

                <li>
                    <div><a href="javascript:void(0)" ref="${child_menu.id }" href="#"
                            rel="${base_path}/${child_menu.resourceUrl} ">
                        <span class="icon ${child_menu.resourceIcon}"
                              title="${child_menu.resourceIcon}">&nbsp;</span><span class="nav">会员</span></a>
                    </div>
                </li>
            </ul>
        </div>
        <div title="报表" data-options="iconCls:'icon ${parent_menu.resourceIcon}'">
            <ul>
                <li>
                    <div><a href="javascript:void(0)" ref="${child_menu.id }" href="#"
                            rel="${base_path}/${child_menu.resourceUrl} ">
                        <span class="icon ${child_menu.resourceIcon}"
                              title="${child_menu.resourceIcon}">&nbsp;</span><span class="nav">房间报表</span></a>
                    </div>
                </li>

                <li>
                    <div><a href="javascript:void(0)" ref="${child_menu.id }" href="#"
                            rel="${base_path}/${child_menu.resourceUrl} ">
                        <span class="icon ${child_menu.resourceIcon}"
                              title="${child_menu.resourceIcon}">&nbsp;</span><span class="nav">单据报表</span></a>
                    </div>
                </li>

                <li>
                    <div><a href="javascript:void(0)" ref="${child_menu.id }" href="#"
                            rel="${base_path}/${child_menu.resourceUrl} ">
                        <span class="icon ${child_menu.resourceIcon}"
                              title="${child_menu.resourceIcon}">&nbsp;</span><span class="nav">单据报表</span></a>
                    </div>
                </li>
            </ul>
        </div>

        <div title="基础信息" data-options="iconCls:'icon ${parent_menu.resourceIcon}'">
            <ul>
                <li>
                    <div><a href="javascript:void(0)" ref="${child_menu.id }" href="#"
                            rel="${base_path}/${child_menu.resourceUrl} ">
                        <span class="icon ${child_menu.resourceIcon}"
                              title="${child_menu.resourceIcon}">&nbsp;</span><span class="nav">客房信息</span></a>
                    </div>
                </li>

                <li>
                    <div><a href="javascript:void(0)" ref="${child_menu.id }" href="#"
                            rel="${base_path}/${child_menu.resourceUrl} ">
                        <span class="icon ${child_menu.resourceIcon}"
                              title="${child_menu.resourceIcon}">&nbsp;</span><span class="nav">员工信息</span></a>
                    </div>
                </li>

                <li>
                    <div><a href="javascript:void(0)" ref="${child_menu.id }" href="#"
                            rel="${base_path}/${child_menu.resourceUrl} ">
                        <span class="icon ${child_menu.resourceIcon}"
                              title="${child_menu.resourceIcon}">&nbsp;</span><span class="nav">门店信息</span></a>
                    </div>
                </li>

                <li>
                    <div><a href="javascript:void(0)" ref="${child_menu.id }" href="#"
                            rel="${base_path}/${child_menu.resourceUrl} ">
                        <span class="icon ${child_menu.resourceIcon}"
                              title="${child_menu.resourceIcon}">&nbsp;</span><span class="nav">设备信息</span></a>
                    </div>
                </li>
            </ul>
        </div>

    </div>

</div>
<div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
    <div id="tabs" class="easyui-tabs" fit="true" border="false">
        <div title="欢迎使用" style="overflow:hidden; color:red;text-align:center; padding-top:200px; ">
            <img src="../../images/logo.png" alt="" width="200px"/>
        </div>
    </div>
</div>
</body>
</html>