<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>网上商城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css">

<script>
	function saveCart(){
		document.getElementById("cartform").submit();
	}
</script>

</head>
<body>

<div class="container header">
	<div class="span5">
		<div class="logo">
			<a>
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/amazon.png" width="180" height="60" />
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
					<img src="image\r___________renleipic_01/header.jpg" alt="正品保障" title="正品保障" height="50" width="320"/>
</div>	</div>
	
	<%@ include file="menu.jsp" %>
</div><div class="container productContent">
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
			
			<div class="productimage">
					<a title="" style="outline-style: none; text-decoration: none;" id="zoom" href="http://image/r___________renleipic_01/bigpic1ea8f1c9-8b8e-4262-8ca9-690912434692.jpg" rel="gallery">
						<div class="zoompad"><img style="opacity: 1;" title="" class="medium" src="<s:property value="model.image"/>"><div style="display: block; top: 0px; left: 162px; width: 0px; height: 0px; position: absolute; border-width: 1px;" class="zoompup"></div><div style="position: absolute; z-index: 5001; left: 312px; top: 0px; display: block;" class="zoomwindow"><div style="width: 368px;" class="zoomwrapper"><div style="width: 100%; position: absolute; display: none;" class="zoomwrappertitle"></div><div style="width: 0%; height: 0px;" class="zoomwrapperimage"><img src="%e5%b0%9a%e9%83%bd%e6%af%94%e6%8b%89%e5%a5%b3%e8%a3%852013%e5%a4%8f%e8%a3%85%e6%96%b0%e6%ac%be%e8%95%be%e4%b8%9d%e8%bf%9e%e8%a1%a3%e8%a3%99%20%e9%9f%a9%e7%89%88%e4%bf%ae%e8%ba%ab%e9%9b%aa%e7%ba%ba%e6%89%93%e5%ba%95%e8%a3%99%e5%ad%90%20%e6%98%a5%e6%ac%be%20-%20powered%20by%20mango%20team_files/6d53c211-2325-41ed-8696-d8fbceb1c199-large.jpg" style="position: absolute; border: 0px none; display: block; left: -432px; top: 0px;"></div></div></div><div style="visibility: hidden; top: 129.5px; left: 106px; position: absolute;" class="zoompreload">loading zoom</div></div>
					</a>
				
			</div>
			<div class="name"><s:property value="model.pname"/></div>
			<div class="sn">
				<div>编号：<s:property value="model.pid"/></div>
			</div>
			<div class="info">
				<dl>
					<dt>商城价:</dt>
					<dd>
						<strong>￥：<s:property value="model.shop_price"/>元</strong>
							参 考 价：
							<del>￥<s:property value="model.market_price"/>元</del>
					</dd>
				</dl>
					<dl>
						<dt>促销:</dt>
						<dd>
								<a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)">限时抢购</a>
						</dd>
					</dl>
					<dl>
						<dt>    </dt>
						<dd>
							<span>    </span>
						</dd>
					</dl>
			</div>
			<form id="cartform" action="${pageContext.request.contextPath }/cart_addCart" method="post">
				<input type="hidden" name="pid" value="<s:property value="model.pid"/>"/>
				<div class="action">
						<dl class="quantity">
							<dt>购买数量:</dt>
							<dd>
								<input id="count" name="count" value="1" maxlength="4" onpaste="return false;" type="text"/>
								<div>
									<span id="increase" class="increase">&nbsp;</span>
									<span id="decrease" class="decrease">&nbsp;</span>
								</div>
							</dd>
							<dd>
								件
							</dd>
						</dl>
					<div class="buy">
							<input id="addcart" class="addcart" value="加入购物车" type="button" onclick="saveCart()"/>
				
					</div>
				</div>
			</form>
			<div id="bar" class="bar">
				<ul>
						<li id="introductiontab">
							<a href="#introduction">商品介绍</a>
						</li>
						
				</ul>
			</div>
				
				<div id="introduction" name="introduction" class="introduction">
					<div class="title">
						<strong><s:property value="model.pdesc"/></strong>
					</div>
					<div>
					<img src="<s:property value="model.image"/>">
					</div>
				</div>
				
				
				
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerad">
					<img src="image\r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950">
</div>
</div>
	<div class="span24">
		<ul class="bottomnav">
					<li>
						<a href="#">关于我们</a>
						|
					</li>
					<li>
						<a href="#">联系我们</a>
						|
					</li>
					<li>
						<a href="#">诚聘英才</a>
						|
					</li>
					<li>
						<a href="#">法律声明</a>
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
						<a target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >shop++官网</a>
						|
					</li>
					<li>
						<a>shop++论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">copyright © 2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body>
</html>	