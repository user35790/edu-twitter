<#import "parts/common.ftl" as c>
<#import "parts/authorization.ftl" as l>

<@c.page>

    <@l.logout/>
    <a href="/">MAIN PAGE</a>

    <form action="/user/monitor/new" method="post">
        <div>Add new user</div>
        <input type="text" name="login" placeholder="Enter username here">
        <input type="text" name="password" placeholder="Enter password here">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Add user</button>
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