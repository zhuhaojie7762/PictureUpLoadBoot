<%@ page contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ include file="/jspView/common/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- 引入公共页面 引入公共js -->
    <%@ include file="/jspView/common/common.jsp" %>
    <!-- page specific plugin styles -->
    <!-- 下拉框-->
    <link rel="stylesheet" href="${ctxStatic}/static/css/chosen.css"/>
    <!-- ace styles -->
    <link rel="stylesheet" href="${ctxStatic}/static/css/ace.min.css"/>
    <link rel="stylesheet" href="${ctxStatic}/static/css/ace-responsive.min.css"/>
    <link rel="stylesheet" href="${ctxStatic}/static/css/ace-skins.min.css"/>

    <link rel="stylesheet" href="${ctxStatic}/static/css/datepicker.css"/><!-- 日期框 -->

    <!-- 设置样式 -->
    <style type="text/css">
        .commitopacity {
            position: absolute;
            width: 100%;
            height: 100px;
            background: #7f7f7f;
            filter: alpha(opacity=50);
            -moz-opacity: 0.8;
            -khtml-opacity: 0.5;
            opacity: 0.5;
            top: 0px;
            z-index: 99999;
        }
    </style>
    <!--script type="text/javascript"
	src="${ctx}/resource/jquery/jquery-1.9.1.min.js"></script-->
    <!-- 即时通讯 -->
    <script type="text/javascript">var wimadress = "${pd.WIMIP}:${pd.WIMPORT}";</script>
    <script type="text/javascript">var oladress = "${pd.OLIP}:${pd.OLPORT}";</script>
    <link rel="stylesheet" type="text/css"
          href="${ctxStatic}/plugins/websocketInstantMsg/ext4/resources/css/ext-all.css">
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/plugins/websocketInstantMsg/css/websocket.css"/>
    <script type="text/javascript" src="${ctxStatic}/plugins/websocketInstantMsg/ext4/ext-all-debug.js"></script>
    <script type="text/javascript" src="${ctxStatic}/plugins/websocketInstantMsg/websocket.js"></script>
    <!-- 即时通讯 -->

</head>
<body>
<!-- 页面顶部¨ -->
<%@ include file="head.jsp" %>
<div id="websocket_button"></div>
<div class="container-fluid" id="main-container">
    <a href="#" id="menu-toggler"><span></span></a>
    <!-- menu toggler -->

    <!-- 左侧菜单 -->
    <%@ include file="left.jsp" %>

    <div id="main-content" class="clearfix">

        <div id="jzts" style="display:none; width:100%; position:fixed; z-index:99999999;">
            <div class="commitopacity" id="bkbgjz"></div>
            <div style="padding-left: 70%;padding-top: 1px;">
                <div style="float: left;margin-top: 3px;"><img src="${ctxStatic}/static/images/loadingi.gif"/></div>
                <div style="margin-top: 5px;"><h4 class="lighter block red">&nbsp;加载中 ...</h4></div>
            </div>
        </div>

        <div>
            <iframe name="mainFrame" id="mainFrame" frameborder="0" src="tab.do"
                    style="margin:0 auto;width:100%;height:100%;"></iframe>
        </div>

        <!-- 换肤 -->
        <div id="ace-settings-container">
            <div class="btn btn-app btn-mini btn-warning" id="ace-settings-btn">
                <i class="icon-cog"></i>
            </div>
            <div id="ace-settings-box">
                <div>
                    <div class="pull-left">
                        <select id="skin-colorpicker" class="hidden">
                            <option data-class="default" value="#438EB9"
                                    <c:if test="${user.SKIN =='default' }">selected</c:if>>#438EB9
                            </option>
                            <option data-class="skin-1" value="#222A2D"
                                    <c:if test="${user.SKIN =='skin-1' }">selected</c:if>>#222A2D
                            </option>
                            <option data-class="skin-2" value="#C6487E"
                                    <c:if test="${user.SKIN =='skin-2' }">selected</c:if>>#C6487E
                            </option>
                            <option data-class="skin-3" value="#D0D0D0"
                                    <c:if test="${user.SKIN =='skin-3' }">selected</c:if>>#D0D0D0
                            </option>
                        </select>
                    </div>
                    <span>&nbsp; 选择皮肤</span>
                </div>
                <div>
                    <label><input type='checkbox' name='menusf' id="menusf"
                                  onclick="menusf();"/><span class="lbl" style="padding-top: 5px;">菜单缩放</span></label>
                </div>
            </div>
        </div>
        <!--/#ace-settings-container-->

    </div>
    <!-- #main-content -->
</div>

<!-- script src="static/js/bootstrap.min.js"></script-->
<script src="${ctxStatic}/static/js/ace-elements.min.js"></script>
<script src="${ctxStatic}/static/js/ace.min.js"></script>
<!--引入弹窗组件start-->
<script type="text/javascript" src="${ctxStatic}/plugins/attention/zDialog/zDrag.js"></script>
<script type="text/javascript" src="${ctxStatic}/plugins/attention/zDialog/zDialog.js"></script>
<!-- 引入 -->
<script type="text/javascript" src="${ctxStatic}/static/js/myjs/menusf.js"></script>
<!--引入属于此页面的js -->
<script type="text/javascript" src="${ctxStatic}/js/index.js"></script>

</body>
</html>