<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %> 
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

    <title>后台管理-<sitemesh:title/></title>

    <!-- Bootstrap Core CSS -->
    <link href="${ctxStatic}/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${ctxStatic}/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="${ctxStatic}/dist/css/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${ctxStatic}/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="${ctxStatic}/bower_components/morrisjs/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${ctxStatic}/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	<sitemesh:head/>
</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${ctx }/sys/f">科睿特后台管理 v1.0</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                
                
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> 用户资料</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="${ctx}/logout"><i class="fa fa-sign-out fa-fw"></i> 退出登陆</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse" id="div_menu">
                    
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
        	<sitemesh:body/>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="${ctxStatic}/bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${ctxStatic}/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
    $(function() {
    	
    	//menu_list为json数据
        //parent为要组合成html的容器
        function showall(menu_list, parent) {
            for (var i=0;i<menu_list.length;i++) {
                //如果有子节点，则遍历该子节点
                if (menu_list[i].childMenus.length > 0) {
                    //创建一个子节点li
                    var li = $("<li></li>");
                    //将li的文本设置好，并马上添加一个空白的ul子节点，并且将这个li添加到父亲节点中
                    $(li).append("<a href=\"${ctx}"+menu_list[i].href+ "\"> "+ menu_list[i].name+" <span class=\"fa arrow\"></span></a>").append("<ul class=\"nav nav-"+menu_list[i].level+"-level collapse\" ></ul>").appendTo(parent);
                    //将空白的ul作为下一个递归遍历的父亲节点传入
                    showall(menu_list[i].childMenus, $(li).children().eq(1));
                }
                //如果该节点没有子节点，则直接将该节点li以及文本创建好直接添加到父亲节点中
                else {
                   $("<li></li>").append("<a href=\"${ctx}"+menu_list[i].href+ "\">"+menu_list[i].name+"</a>").appendTo(parent);
                }
            }
        }
    	$.ajax({
            url: "${ctx}/menu/navigation/get",
            type: "get",  
            async: false,
		     success: function(data) {
		    	 //var showlist = $("<ul></ul>");
		    	 var showlist = $("<ul class=\"nav in\" id=\"side-menu\"></ul>");
		    	 showall(data['childMenus'],showlist);
		    	 $("#div_menu").append(showlist);
		    	 //$("#side-menu")
            },
            error: function() {
                alert("系统发生异常，请稍候再试！\n\n有任何疑问，请联系系统管理员！");
            }
        });
	});
    </script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${ctxStatic}/bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript 
    <script src="${ctxStatic}/bower_components/raphael/raphael-min.js"></script>
    <script src="${ctxStatic}/bower_components/morrisjs/morris.min.js"></script>
    <script src="${ctxStatic}/js/morris-data.js"></script>-->

    <!-- Custom Theme JavaScript -->
    <script src="${ctxStatic}/dist/js/sb-admin-2.js"></script>
    
    <script src="${ctxStatic}/treeTable/jquery.treeTable.js"></script>
    <script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
    
    

</body>

</html>
