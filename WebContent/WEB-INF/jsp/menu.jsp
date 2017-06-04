<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="topNav clearfix">
			<ul>
			<s:if test="#session.existuser == null">
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
		<a href="${pageContext.request.contextPath }/user_loginPage">登录</a>|
	</li>
	<li id="headerRegister" class="headerRegister" style="display: list-item;">
				<a href="${pageContext.request.contextPath }/user_registPage">注册</a>|
			</li>
			</s:if>
			<s:else>
			<li id="headerLogin" class="headerLogin" style="display: list-item;">
			<s:property value="#session.existuser.name"/>
			|
			</li>
			<li id="headerRegister" class="headerRegister" style="display: list-item;">
				<a href="${pageContext.request.contextPath }/order_myorder?page=1">个人中心</a>|
			</li>
			<li id="headerRegister" class="headerRegister" style="display: list-item;">
				<a href="${pageContext.request.contextPath }/user_quit">退出</a>|
			</li>
			</s:else>
			<li id="headerUsername" class="headerUsername"></li>
			<li id="headerLogout" class="headerLogout">
				<a>[退出]</a>|
			</li>
					<li>
						<a>会员中心</a>
						|
					</li>
					<li>
						<a>购物指南</a>
						|
					</li>
					<li>
						<a>关于我们</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
	<ul class="mainNav">
				<li><a href="/shop/index">首页</a>|</li>
			<s:iterator value="#session.categoryList" id="c">
				<li><a href="${pageContext.request.contextPath}/product_findByCidPage?cid=<s:property value="#c.cid"/>&page=1"><s:property value="#c.cname"/></a>|</li>
			</s:iterator>
	</ul>
</div>
	