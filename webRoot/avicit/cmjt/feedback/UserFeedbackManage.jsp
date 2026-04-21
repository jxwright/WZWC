<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>问题反馈</title>
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
</head>
<body>
<h2>用户问题反馈</h2>
<div>
    <label>用户ID：<input id="userId"/></label>
    <label>用户姓名：<input id="userName"/></label><br/>
    <label>标题：<input id="title" style="width: 300px"/></label><br/>
    <label>问题描述：</label><br/>
    <textarea id="questionContent" rows="4" cols="80"></textarea><br/>
    <button onclick="submitIssue()">提交问题</button>
    <button onclick="loadIssues()">查询我的问题</button>
</div>
<hr/>
<table border="1" cellspacing="0" cellpadding="6" width="100%">
    <thead>
    <tr>
        <th>标题</th>
        <th>问题</th>
        <th>状态</th>
        <th>管理员回复</th>
        <th>回复时间</th>
        <th>关闭时间</th>
    </tr>
    </thead>
    <tbody id="issueBody"></tbody>
</table>

<script>
    var baseUrl = '<%=request.getContextPath()%>/avicit/cmjt/feedback/cmjtFeedbackIssueController/operation';

    function submitIssue() {
        var payload = {
            userId: $('#userId').val(),
            userName: $('#userName').val(),
            title: $('#title').val(),
            questionContent: $('#questionContent').val()
        };
        $.ajax({
            url: baseUrl + '/create',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(payload),
            success: function (res) {
                alert(res.flag === 'success' ? '提交成功' : ('提交失败：' + (res.msg || '')));
                loadIssues();
            }
        });
    }

    function loadIssues() {
        $.get(baseUrl + '/list', {userId: $('#userId').val()}, function (res) {
            if (res.flag !== 'success') {
                alert('查询失败：' + (res.msg || ''));
                return;
            }
            var html = '';
            $.each(res.rows || [], function (_, item) {
                html += '<tr>' +
                    '<td>' + (item.title || '') + '</td>' +
                    '<td>' + (item.questionContent || '') + '</td>' +
                    '<td>' + (item.status || '') + '</td>' +
                    '<td>' + (item.adminReply || '') + '</td>' +
                    '<td>' + (item.replyTime || '') + '</td>' +
                    '<td>' + (item.closeTime || '') + '</td>' +
                    '</tr>';
            });
            $('#issueBody').html(html);
        });
    }
</script>
</body>
</html>
