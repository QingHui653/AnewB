<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/web/common/headjsp.jsp"%>
<html>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<h3>h3. 这是一套可视化布局系统.</h3>
				<nav class="navbar navbar-default" role="navigation">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span><span
								class="icon-bar"></span><span class="icon-bar"></span><span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">Brand</a>
					</div>
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li class="active"><a href="#">Link</a></li>
							<li><a href="#">Link</a></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">Dropdown<strong class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a href="#">Action</a></li>
									<li><a href="#">Another action</a></li>
									<li><a href="#">Something else here</a></li>
									<li class="divider"></li>
									<li><a href="#">Separated link</a></li>
									<li class="divider"></li>
									<li><a href="#">One more separated link</a></li>
								</ul></li>
						</ul>
						<form class="navbar-form navbar-left" role="search">
							<div class="form-group">
								<input type="text" class="form-control" />
							</div>
							<button type="submit" class="btn btn-default">Submit</button>
						</form>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="#">Link</a></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">Dropdown<strong class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a href="#">Action</a></li>
									<li><a href="#">Another action</a></li>
									<li><a href="#">Something else here</a></li>
									<li class="divider"></li>
									<li><a href="#">Separated link</a></li>
								</ul></li>
						</ul>
					</div>
				</nav>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-2 column">
				<ol>
					<li>Lorem ipsum dolor sit amet</li>
					<li>Consectetur adipiscing elit</li>
					<li>Integer molestie lorem at massa</li>
					<li>Facilisis in pretium nisl aliquet</li>
					<li>Nulla volutpat aliquam velit</li>
					<li>Faucibus porta lacus fringilla vel</li>
					<li>Aenean sit amet erat nunc</li>
					<li>Eget porttitor lorem</li>
				</ol>
			</div>
			<div class="col-md-6 column">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Group</th>
							<th>Number</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${repList}" var="rep">
							<tr>
								<td><a href="project/${rep.result }">${rep.result }</td>
								<td>${rep.count }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-md-4 column">
				<dl>
					<dt>Description lists</dt>
					<dd>A description list is perfect for defining terms.</dd>
					<dt>Euismod</dt>
					<dd>Vestibulum id ligula porta felis euismod semper eget
						lacinia odio sem nec elit.</dd>
					<dd>Donec id elit non mi porta gravida at eget metus.</dd>
					<dt>Malesuada porta</dt>
					<dd>Etiam porta sem malesuada magna mollis euismod.</dd>
					<dt>Felis euismod semper eget lacinia</dt>
					<dd>Fusce dapibus, tellus ac cursus commodo, tortor mauris
						condimentum nibh, ut fermentum massa justo sit amet risus.</dd>
				</dl>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-2 column"></div>
			<div class="col-md-6 column">
				<div class="progress">
					<div class="progress-bar progress-success"></div>
				</div>
			</div>
			<div class="col-md-4 column"></div>
		</div>
	</div>
</body>