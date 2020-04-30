<%--
  Created by IntelliJ IDEA.
  User: 4
  Date: 2020/4/24
  Time: 17:57
  To change this template use File | Settings | File Templates.
  pageContext.setAttribute("APP_PATH",request.getContextPath());
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>starBlog</title>
<%--    <jsp:forward page="/blogs"/>--%>
    <%
        pageContext.setAttribute("APP_PATH",request.getContextPath());
    %>


    <%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">--%>
    <%--    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>--%>
    <%--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>--%>
    <link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="${APP_PATH}/static/js/jquery-3.1.1.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>


<body>


<div class="container">
    <%--        标题--%>
    <div class="row">
        <div class="col-md-12">
            <h1>博客</h1>
        </div>
    </div>
    <%--    按钮--%>
    <div class="row" >
        <div class="col-md-4 col-md-4 col-md-offset-8">
            <button class="btn btn-primary" id="blog_add_modal_btn">新增</button>
            <button class="btn btn-danger">删除</button>
        </div>
    </div>
    <%--    表格--%>
    <div class="row">
        <table class="table table-hover" id = "blogs_table">
            <thead>
            <tr>
                <th>#</th>
                <th>userid</th>
                <th>publishtime</th>
                <th>content</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
    <%--    分页信息--%>
    <div class="row">
        <!--分页文字信息-->
        <div class="col-md-6" id="page_info_area"></div>
        <!--分页条-->
        <div class="col-ma-6" id="page_nav_area">

        </div>
    </div>
    <script type="text/javascript">

        var totalRecord;
        $(function(){
            alert(1);
            to_page(1);
        });

        function to_page(pn) {
            $.ajax({
                url:"${APP_PATH}/blogs",
                data:"pn="+pn,
                type:"GET",
                success:function (result) {

                    build_blogs_table(result);

                    build_page_info(result);

                    build_page_nav(result);

                }
            });

        }

        function build_blogs_table(result) {

            $("#blogs_table tbody").empty();
            var blogs = result.extend.pageInfo.list;
            //var blogs = ${requestScope.pageInfo}.list;
            $.each(blogs,function (index,item) {

                var blogIdTd = $("<td></td>").append(item.blogid);
                var useridTd = $("<td></td>").append(item.userid);
                var publishtimeTd = $("<td></td>").append(item.publishtime);
                var contentTd = $("<td></td>").append(item.content);
                var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm").append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
                var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm").append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
                var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
                $("<tr></tr>").append(blogIdTd)
                    .append(useridTd)
                    .append(publishtimeTd)
                    .append(contentTd)
                    .append(btnTd)
                    .appendTo("#blogs_table tbody");

            })
        }

        //分页信息
        function build_page_info(result) {
            $("#page_info_area").empty();
            $("#page_info_area").append("当前"+result.extend.pageInfo.pageNum +"页,总"
                +result.extend.pageInfo.pages+"页，总共"
                +result.extend.pageInfo.total+"条记录");

            totalRecord = result.extend.pageInfo.total;



        }

        //分页条
        function build_page_nav(result) {
            $("#page_nav_area").empty();
            var ul = $("<ul></ul>").addClass("pagination");

            var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
            var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
            if(result.extend.pageInfo.hasPreviousPage==false){
                firstPageLi.addClass("disabled");
                prePageLi.addClass("disabled");
            }else{
                firstPageLi.click(function () {
                    to_page(1);

                });
                prePageLi.click(function () {
                    to_page(result.extend.pageInfo.pageNum-1);

                });
            }



            var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
            var lastPageLi = $("<li></li>").append($("<a></a>").append("尾页").attr("href","#"));

            if(result.extend.pageInfo.hasNextPage==false){
                nextPageLi.addClass("disabled");
                lastPageLi.addClass("disabled");
            }else{
                nextPageLi.click(function () {
                    to_page(result.extend.pageInfo.pageNum+1);
                });

                lastPageLi.click(function () {
                    to_page(result.extend.pageInfo.pages);
                });
            }



            ul.append(firstPageLi).append(prePageLi);

            $.each(result.extend.pageInfo.navigatepageNums,function (index,item) {

                var numLi = $("<li></li>").append($("<a></a>").append(item));
                if(result.extend.pageInfo.pageNum == item){
                    numLi.addClass("active");
                }
                numLi.click(function () {
                    to_page(item);

                });
                ul.append(numLi);
            });

            ul.append(nextPageLi).append(lastPageLi);

            var navEle = $("<nav></nav>").append(ul);
            navEle.appendTo("#page_nav_area");

        }







    </script>
</div>


</body>
</html>
