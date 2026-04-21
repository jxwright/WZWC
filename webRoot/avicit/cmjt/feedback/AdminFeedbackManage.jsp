<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>反馈管理</title>
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
</head>
<body>
<h2>管理员问题处理页面</h2>
<div>
    <label>管理员ID：<input id="adminId"/></label>
    <label>管理员姓名：<input id="adminName"/></label>
    <button onclick="loadIssues()">刷新列表</button>
</div>
<table border="1" cellspacing="0" cellpadding="6" width="100%">
    <thead>
    <tr>
        <th>问题ID</th>
        <th>用户</th>
        <th>标题</th>
        <th>问题内容</th>
        <th>状态</th>
        <th>管理员回复</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody id="issueBody"></tbody>
</table>

<script>
    var baseUrl = '<%=request.getContextPath()%>/avicit/cmjt/feedback/cmjtFeedbackIssueController/operation';

    function loadIssues() {
        $.get(baseUrl + '/list', {}, function (res) {
            if (res.flag !== 'success') {
                alert('加载失败：' + (res.msg || ''));
                return;
            }
            var html = '';
            $.each(res.rows || [], function (_, item) {
                html += '<tr>' +
                    '<td>' + item.id + '</td>' +
                    '<td>' + (item.userName || item.userId || '') + '</td>' +
                    '<td>' + (item.title || '') + '</td>' +
                    '<td>' + (item.questionContent || '') + '</td>' +
                    '<td>' + (item.status || '') + '</td>' +
                    '<td><textarea id="reply_' + item.id + '" rows="3" cols="25">' + (item.adminReply || '') + '</textarea></td>' +
                    '<td>' +
                    '<button onclick="replyIssue(\'' + item.id + '\')">回复</button> ' +
                    '<button onclick="closeIssue(\'' + item.id + '\')">关闭</button>' +
                    '</td>' +
                    '</tr>';
            });
            $('#issueBody').html(html);
        });
    }

    function replyIssue(id) {
        $.ajax({
            url: baseUrl + '/reply',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                id: id,
                replyContent: $('#reply_' + id).val(),
                adminId: $('#adminId').val(),
                adminName: $('#adminName').val()
            }),
            success: function (res) {
                alert(res.flag === 'success' ? '回复成功' : ('回复失败：' + (res.msg || '')));
                loadIssues();
            }
        });
    }

    function closeIssue(id) {
        $.ajax({
            url: baseUrl + '/close',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                id: id,
                adminId: $('#adminId').val(),
                adminName: $('#adminName').val()
            }),
            success: function (res) {
                alert(res.flag === 'success' ? '关闭成功' : ('关闭失败：' + (res.msg || '')));
                loadIssues();
            }
        });
    }

    loadIssues();
</script>
</body>
</html>
