<#import "parts/common.ftl" as c>

<@c.page>

    <form action="/user/monitor/new" method="post">
        <div>Add new user</div>
        <input type="text" name="login" class="form-control"placeholder="Enter username here">
        <input type="text" name="password" placeholder="Enter password here">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button class="btn btn-primary" type="submit">Add user</button>
    </form>

<b>List of all users</b>
<dl>
    <#list users as user>
        <dt>${user.username} <a href="/user/edit/${user.id}">EDIT</a></dt>
        <dd>
            <ul>
                <li>ID: ${user.id}</li>
                <li>PASSWORD:${user.password}</li>
            </ul>
        </dd>
        <#else>
        No users
    </#list>
</dl>

</@c.page>