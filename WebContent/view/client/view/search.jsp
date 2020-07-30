<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="col-md-3 filter-by-block md-margin-bottom-60">
	<h1>Tìm kiếm</h1>
	<div class="panel-group" id="accordion">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
						Tên Món Ăn<i class="fa fa-angle-down"></i>
					</a>
				</h2>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in">
				<div class="panel-body">
					<ul class="list-unstyled checkbox-list">
						<li><form action="${pageContext.request.contextPath }/product/search" method="get">
							<input type="text" name="name" />
							<input type="submit" value="Tìm Kiếm" >
						</form></li>
					</ul>	
				</div>
			</div>
		</div>
	</div>
	<!--/end panel group-->
	
	<div class="panel-group" id="accordion-v2">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion-v2" href="#collapseTwo">
						Phân Loại <i class="fa fa-angle-down"></i>
					</a>
				</h2>
			</div>

			<div id="collapseTwo" class="panel-collapse collapse in">
				<div class="panel-body">
					<ul class="list-unstyled checkbox-list">
						<c:forEach items="${categoryList }" var="c">
							<li><a href="${pageContext.request.contextPath }/product/category?cate_id=${c.id}">${c.name }</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!--/end panel group-->

	<div class="panel-group" id="accordion-v2">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion-v2" href="#collapseTwo">
						Thương Hiệu <i class="fa fa-angle-down"></i>
					</a>
				</h2>
			</div>
			<div id="collapseTwo" class="panel-collapse collapse in">
				<div class="panel-body">
					<ul class="list-unstyled checkbox-list">
						<c:forEach items="${stallList }" var="s">
							<li><a href="${pageContext.request.contextPath }/product/stall?stall_id=${s.id}">${s.name }</a><small>(${s.item})</small></li>
						</c:forEach>	
					</ul>
				</div>
			</div>
		</div>
	</div>
	
	<!--/end panel group-->

	<div class="panel-group" id="accordion-v4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion-v4" href="#collapseFour">
						Giá Tiền <i class="fa fa-angle-down"></i>
					</a>
				</h2>
			</div>
			<div id="collapseFour" class="panel-collapse collapse in">
				<div class="panel-body">
					<div class="slider-snap"></div>
					<p class="slider-snap-text">
						<span class="slider-snap-value-lower"></span>
						<span class="slider-snap-value-upper"></span>
					</p>
				</div>
			</div>
		</div>
	</div>
	<!--/end panel group-->

	<div class="panel-group margin-bottom-30" id="accordion-v5">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion-v5" href="#collapseFive">
						Đánh Giá<i class="fa fa-angle-down"></i>
					</a>
				</h2>
			</div>
			<div id="collapseFive" class="panel-collapse collapse in">
				<div class="panel-body">
					<div class="stars-ratings stars-ratings-label">
						<input type="radio" name="stars-rating" id="stars-rating-5">
						<label for="stars-rating-5"><i class="fa fa-star"></i></label>
						<input type="radio" name="stars-rating" id="stars-rating-4">
						<label for="stars-rating-4"><i class="fa fa-star"></i></label>
						<input type="radio" name="stars-rating" id="stars-rating-3">
						<label for="stars-rating-3"><i class="fa fa-star"></i></label>
						<input type="radio" name="stars-rating" id="stars-rating-2">
						<label for="stars-rating-2"><i class="fa fa-star"></i></label>
						<input type="radio" name="stars-rating" id="stars-rating-1">
						<label for="stars-rating-1"><i class="fa fa-star"></i></label>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--/end panel group-->
	<button type="button" class="btn-u btn-brd btn-brd-hover btn-u-lg btn-u-sea-shop btn-block">Đặt Lại</button>
</div>