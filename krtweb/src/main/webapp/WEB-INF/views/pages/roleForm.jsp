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

   

    <title>编辑角色</title>
	<link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css"/>

    
</head>

<body>

    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">编辑角色</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <c:if test="${not empty message}">
	<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                   	 编辑角色
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                	<div class="col-lg-4">
                		<form role="form" action="${ctx}/role/${action}" method="post" class="form-horizontal">
							<input id="roleid" type="hidden" name="id" value="${role.id}"/>
								<div class="form-group input-group">
									<span class="input-group-addon">角色类型：</span>
									<input type="text" value="${role.roleType}" name="roleType" class="form-control" />
								</div>
								<div class="form-group input-group">
									<span class="input-group-addon">角色名称：</span>
									<input type="text" value="${role.name}" name="name" class="form-control" />
								</div>
								
								<div class="form-group">
									<span>菜单权限：</span>
									<div id="ztree" class="ztree"></div>
								</div>
								
								
								<div class="form-actions">
									<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
									<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
								</div>
						</form>
                	</div>
                    
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
    
    <!-- jQuery -->
    <script src="${ctxStatic}/bower_components/jquery/dist/jquery.min.js"></script>
    <script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
    
    <script type="text/javascript">
    $(document).ready(function(){
    	var setting = {check:{enable:true,nocheckInherit:true},view:{selectedMulti:true},data:{simpleData:{enable:true,idKey:"id",pIdKey:"parentId",rootPId:'0'}},
				callback:{onClick:function(event, treeId, treeNode){
						var id = treeNode.id == '0' ? '' :treeNode.id;
						//$('#officeContent').attr("src","${ctx}/sys/user/list?office.id="+id+"&office.name="+treeNode.name);
					}
				}
			};
		/* var data =[
				{ id:1, pId:0, name:"SO"},
		         { id:11, pId:1, name:"SO-E1"},
		         { id:111, pId:11, name:"SO-E1-S1"},
		         { id:112, pId:11, name:"SO-E1-S2"},
		         { id:12, pId:1, name:"SO-E11"},
		         { id:121, pId:12, name:"SO-E11-S11"},
		         { id:122, pId:12, name:"SO-E11-S22"}
		      ] */
		var roleid=$("#roleid").value();
		$.getJSON("${ctx}/role/menu/get/"+roleid,function(data){
			$.fn.zTree.init($("#ztree"), setting, data).expandAll(true);
		});
    });
    </script>
</body>

</html>
