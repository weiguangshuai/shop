<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0048)http://localhost:8080/mango/product/list/1.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>网上商城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="http://localhost:8080/mango/">
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/amazon.png" width="180" height="60" />
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
					<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
</div>	</div>
	<%@ include file="menu.jsp" %>
</div>	
<div class="container productList">
		<div class="span6">
			<div class="hotProductCategory">
				<s:iterator value="#session.categoryList" id="c">
						<dl>
							<dt>
								<a href="${pageContext.request.contextPath}/product_findByCidPage?cid=<s:property value="#c.cid"/>&page=1"><s:property value="#c.cname"/></a>
							</dt>
								<s:iterator value="#c.categorySecond" id="cs">
									<dd>
										<a href="${pageContext.request.contextPath }/product_findByCsidPage?csid=<s:property value="#cs.csid"/>&page=1"><s:property value="#cs.csname"/></a>
									</dd>
								</s:iterator>
						</dl>
					</s:iterator>
			</div>
		</div>
		<div class="span18 last">
			
			<form id="productForm" action="${pageContext.request.contextPath}/image/蔬菜 - Powered By Mango Team.htm" method="get"/>
				<input type="hidden" id="brandId" name="brandId" value=""/>
				<input type="hidden" id="promotionId" name="promotionId" value=""/>
				<input type="hidden" id="orderType" name="orderType" value=""/>
				<input type="hidden" id="pageNumber" name="pageNumber" value="1"/>
				<input type="hidden" id="pageSize" name="pageSize" value="20"/>
					
				<div id="result" class="result table clearfix"/>
						<ul>
						<s:iterator value="pagebean.list" id="pl">
						<li>
										<a href="${pageContext.request.contextPath }/product_findByPid?pid=<s:property value="#pl.pid"/>">
											<img src="${pageContext.request.contextPath}/<s:property value="#pl.image"/>" width="170" height="170"  style="display: inline-block;">
											   
											<span style='color:green'>
											<s:property value="#pl.pname"/>
											</span>
											<span class="price">
												商城价： ￥<s:property value="#pl.shop_price"/>元
											</span>
											 
										</a>
									</li>
								</s:iterator>	
						</ul>
				</div>
	<div class="pagination">
	<s:if test="cid != null">
		第<s:property value="pagebean.page"/>/<s:property value="pagebean.total"/>页
		<s:if test="pagebean.page != 1">
			<a href="${pageContext.request.contextPath }/product_findByCidPage?cid=<s:property value="cid"/>&page=1" class="firstPage">&nbsp;</a>
			<a href="${pageContext.request.contextPath }/product_findByCidPage?cid=<s:property value="cid"/>&page=<s:property value="pagebean.page+1"/>" class="previousPage">&nbsp;</a>
		</s:if>
		<s:iterator begin="1" end="pagebean.total" id="i">
				<a href="${pageContext.request.contextPath }/product_findByCidPage?cid=<s:property value="cid"/>&page=<s:property value="#i"/>"><s:property value="#i"/></a>
		</s:iterator>
		<s:if test="pagebean.page !=pagebean.total">
			<a class="nextPage" href="${pageContext.request.contextPath }/product_findByCidPage?cid=<s:property value="cid"/>&page=<s:property value="pagebean.page+1"/>">&nbsp;</a>
			
			<a class="lastPage" href="${pageContext.request.contextPath }/product_findByCidPage?cid=<s:property value="cid"/>&page=<s:property value="pagebean.total"/>">&nbsp;</a>
		</s:if>
	</s:if>
	<s:if test="csid != null">
		第<s:property value="pagebean.page"/>/<s:property value="pagebean.total"/>页
		<s:if test="pagebean.page != 1">
			<a href="${pageContext.request.contextPath }/product_findByCsidPage?csid=<s:property value="csid"/>&page=1" class="firstPage">&nbsp;</a>
			<a href="${pageContext.request.contextPath }/product_findByCsidPage?csid=<s:property value="csid"/>&page=<s:property value="pagebean.page-1"/>" class="previousPage">&nbsp;</a>
		</s:if>
		<s:iterator begin="1" end="pagebean.total" id="i">
				<a href="${pageContext.request.contextPath }/product_findByCsidPage?cid=<s:property value="csid"/>&page=<s:property value="#i"/>"><s:property value="#i"/></a>
		</s:iterator>
		<s:if test="pagebean.page !=pagebean.total">
			<a class="nextPage" href="${pageContext.request.contextPath }/product_findByCsidPage?csid=<s:property value="csid"/>&page=<s:property value="pagebean.page+1"/>">&nbsp;</a>
			
			<a class="lastPage" href="${pageContext.request.contextPath }/product_findByCsidPage?csid=<s:property value="csid"/>&page=<s:property value="pagebean.total"/>">&nbsp;</a>
		</s:if>
	</s:if>
	</div>
			</form>
		</div>
	</div>
	
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="${pageContext.request.contextPath}/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势"/>
</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a >关于我们</a>
						|
					</li>
					<li>
						<a>联系我们</a>
						|
					</li>
					<li>
						<a >诚聘英才</a>
						|
					</li>
					<li>
						<a >法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a  target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >官网</a>
						|
					</li>
					<li>
						<a >论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright©2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body></html>