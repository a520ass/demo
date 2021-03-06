<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>	
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>注册页面</title>

    <!-- Bootstrap Core CSS -->
    <link href="${ctxStatic}/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${ctxStatic}/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${ctxStatic}/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${ctxStatic}/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">请注册</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" action="${ctx}/register" method="post" id="registerForm">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="用户名" name="username" id="username" type="text" autofocus required>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="显示名" name="name" type="text">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="密码" name="password" id="password" type="password" value="" required>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="再输入一次密码" name="rpassword" type="password">
                                </div>
                                <!-- <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">记住我
                                    </label>
                                </div> -->
                                <!-- Change this to a button or input when using this as a form -->
                                <button href="#" type="submit" class="btn btn-lg btn-success btn-block">确认注册</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="${ctxStatic}/bower_components/jquery/dist/jquery.min.js"></script>
    
    <script src="${ctxStatic}/bower_components/jquery-validation/dist/jquery.validate.min.js"></script>
    
    <script src="${ctxStatic}/bower_components/jquery-validation/src/localization/messages_zh.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${ctxStatic}/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${ctxStatic}/bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${ctxStatic}/dist/js/sb-admin-2.js"></script>
    
    <script type="text/javascript">
    	$("#registerForm").validate({
    		rules:{
    			username:{
    				required:true,
    				minlength:5,
    				remote:{                                          //验证用户名是否存在
    		               type:"get",
    		               url:"${ctx}/register/check",             //servlet
    		               data:{
    		                 username:function(){
    		                	 return $("#username").val();
    		                 }
    		        	   } 
    				}
    			},
    			password: {
    		        required: true,
    		        minlength: 5
    		    },
    		    rpassword:{
    		    	equalTo:"#password"
    		    }
    		},
    		 messages: {
    		      username: {
    		        remote:"该用户名已经被注册！"
    		      }
    		      
    		}
    	});
    </script>

</body>

</html>
