<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<ul class="admin-menu">
		<li><a href="${pageContext.request.contextPath}/${blog.blogId}/admin/basic">기본설정</a></li>
		<li class="selected">카테고리</li>
		<li><a href="${pageContext.request.contextPath}/${blog.blogId}/admin/write">글작성</a></li>
	</ul>
</div>