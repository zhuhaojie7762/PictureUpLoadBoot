<%@page contentType="text/html;charset=UTF-8"%>
<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/jspView/common/include.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<!-- 引入分页样式 -->
<link rel="stylesheet" href="${ctxStatic}/css/pagination.css" />

<!-- 页码div及样式 -->
    <div id="Pagination" class="pagination"></div>
    <!-- 跳转到及总页数信息 
    <div id="other" class="pagination">
       <span id="currentPage"></span>
       /
       <span id="totalPages"></span>
       
         跳转到第: <input type="text"  id="pageNumber"/>  页
                   <span onclick="toJump()">确定</span>
       每页显示<input type="number" min="1" id="pageSize"/>
    </div>
    -->
<script src="${ctxStatic}/js/common/page/jquery.pagination.js?version=${version}"></script>
