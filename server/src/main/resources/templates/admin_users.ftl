<#import "parts/common.ftl" as c>

<@c.page>

    <form action="/user/monitor/new" method="post">
        <div>Add new user</div>
        <input type="text" name="login" class="form-control my-1" placeholder="Enter username here">
        <input type="text" name="password" class="form-control my-1"  placeholder="Enter password here">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button class="btn btn-primary" type="submit">Add user</button>
    </form>

    <#list users as user>
    <div class="card my-3">
        <div class="card-body">
            <h5 class="card-title">${user.username}</h5>
            <p class="card-text">id: ${user.id}</p>
            <a href="/user/edit/${user.id}" class="btn btn-primary">Edit profile</a>
        </div>
    </div>
    <#else>
        No users
    </#list>


</@c.page>