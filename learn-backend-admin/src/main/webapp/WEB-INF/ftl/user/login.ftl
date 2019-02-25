<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <meta charset="utf-8">
        <title>Shiro Demo | 登录</title>
        <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
        <link   rel="icon" href="${basePath}/favicon.ico" type="image/x-icon" />
		<link   rel="shortcut icon" href="${basePath}/favicon.ico" />
        <!-- CSS -->
        <link rel="stylesheet" href="${basePath}/css/login/reset.css"/>
        <link rel="stylesheet" href="${basePath}/css/login/supersized.css"/>
        <link rel="stylesheet" href="${basePath}/css/login/style.css"/>
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="${basePath}/js/common/html5shiv.js"></script>
        <![endif]-->
		<style>
			canvas{position: fixed; top: 0px; left: 0px; }
		</style>
    </head>

    <body id="body">
        <div class="page-container">
            <h1>Login</h1>
            <h1>本项目请使用Tomcat，请勿使用Jetty</h1>
            <form id="_form" action="" method="post">
                <input type="text" name="account" class="username" placeholder="Account">
                <input type="password" name="password" class="password" placeholder="Password">
                <div style="text-align: left; margin-left: 10px;">
                <label><input type="checkbox" checked="checked"  id="rememberMe"style="width: 10px; height: 10px;">记住我</label>
                <p><b style='color:red;'>如果重启Tomcat，还是登录状态，是因为这里勾选了“记住我”。</b></p>
                </div>
                <button type="button" id="login">登录</button>
                <button type="button" id="register" class="register">Register</button>
                <div class="connect" >
	                <p>有问题请这样解决:</p>
	                <p>
	                    <a class="" style="width: auto; color: rgb(255, 255, 255);" target="_blank" href="http://www.sojson.com/shiro">点我看本项目介绍，<br><br>（登录密码请看这里。）</a>
	                    <a class="" style="width: auto; color: rgb(255, 255, 255);" target="_blank" href="http://jq.qq.com/?_wv=1027&k=YpqCNd">各种不会 QQ群：259217951</a>
	                </p>
	            </div>
                
                <div class="error"><span>+</span></div>
            </form>
        </div>
        <!-- Javascript -->
        <script  src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
        <script  src="${basePath}/js/common/MD5.js"></script>
        <script  src="${basePath}/js/common/supersized.3.2.7.min.js"></script>
        <script  src="${basePath}/js/common/supersized-init.js"></script>
		<script  src="${basePath}/js/common/layer/layer.js"></script>
		<script  src="${basePath}/js/user.login.js"></script>
    </body>

</html>

