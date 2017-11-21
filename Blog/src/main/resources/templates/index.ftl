<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
<script src="/bootstrap/js/jquery-1.11.2.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/css/index.css">


<title>博客主页</title>

<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
	background-color: #F5F5F5;
	font-family: microsoft yahei;
	font-size: 14px;
    line-height: 1.42857143;
    color: #333;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<a style="text-decoration: none;"><h1>
						<strong>杜威的博客</strong>
					</h1></a>
			</div>
			<div class="col-md-8">
				<iframe style="float: right" width="420" scrolling="no" height="60"
					frameborder="0" allowtransparency="true"
					src="http://i.tianqi.com/index.php?c=code&id=12&icon=1&num=3&site=12">
				</iframe>
			</div>
		</div>
		
		<!-- 导航栏 -->
		<div class="row" style="margin-top: 20px">
			<div class="col-md-12">
				<nav class="navbar navbar-default">
					<div class="container-fluid">
						<div class="collapse navbar-collapse"
							id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav">
								<li><a href="#">博客首页<span class="sr-only">(current)</span></a></li>
								<li><a href="#">关于博主</a></li>
								<li><a href="#">我的相册</a></li>
								<li><a href="#">资源小站</a></li>
								<li><a href="#">我的CSDN</a></li>
							</ul>

							<form class="navbar-form navbar-right">
								<div class="form-group">
									<input type="text" class="form-control" placeholder="请输入要搜索的关键字">
								</div>
								<button type="submit" class="btn btn-default">搜索</button>
							</form>
						</div>
					</div>
				</nav>
			</div>
		</div>
		
		<!-- 主题 -->
		<div class="row">
			<div class="col-md-9">
				<div class="data_list">
				  <div class="data_list_title">
				  	<span class="glyphicon glyphicon glyphicon-th-list"></span>&nbsp;最新博客
				  </div>
				  <div class="datas">
				    <ul>
						<li style="margin-bottom: 30px">
						  	<span class="title">
						  		<img alt="文章类型" src="/static/userImages/yuan.jpg">
						  		<a href="#">这是一篇测试博客</a>
						  	</span>
						  	<span class="summary">摘要: 这是用来测试的静态数据，为了多搞一点数据，于是我开始喋喋不休的说一些废话，包括我很帅之类的，虽然我一直强调自己要低调，但不知为何，自己的容颜非得和内心背道而驰....</span>
						  	<span class="img">
						  		
							  		<a href="#"><img src="/static/userImages/dog.jpg" title="dog" alt="dog.jpg" width="823" height="489" style="width: 823px; height: 489px;"></a>
							  		<a href="#"><img src="/static/userImages/dog.jpg" title="dog" alt="dog.jpg" width="823" height="489" style="width: 823px; height: 489px;"></a>
							  		<a href="#"><img src="/static/userImages/dog.jpg" title="dog" alt="dog.jpg" width="823" height="489" style="width: 823px; height: 489px;"></a>
							  		&nbsp;&nbsp;
						  		
						  	</span>
						  	<span class="info">
						  		<font color="#999">2016-07-03 10:39</font> &nbsp;&nbsp;
						  		<font color="#33a5ba"><a href="#">阅读</a><font color="#999">(404)</font>&nbsp;&nbsp;</font>
						  		<font color="#33a5ba"><a href="#">评论</a><font color="#999">(8)</font>&nbsp;&nbsp;</font>  	
						  	</span>
						</li>
						<hr style="height:5px;border:none;border-top:1px dashed gray;padding-bottom:10px;" />													
					</ul>
				  </div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="user_info_box">
					<div class="user_info">
						<span class="glyphicon glyphicon-user"></span>&nbsp;博主信息
					</div>
					<div class="user_pic">
						<img></img>
					</div>
					<img>
				</div>
			</div>
		</div>
		
		
	</div>
</body>
</html>