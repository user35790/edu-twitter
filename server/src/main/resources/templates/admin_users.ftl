<#import "parts/common.ftl" as c>
<#import "parts/part.ftl" as l>

<@c.page>

    <form action="/user/monitor/new" method="post">
        <div>Add new user</div>
        <input type="text" name="login" class="form-control my-1" placeholder="Enter username here">
        <input type="text" name="password" class="form-control my-1" placeholder="Enter password here">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button class="btn btn-primary" type="submit">Add user</button>
    </form>

    <@l.usr users/>

</@c.page>