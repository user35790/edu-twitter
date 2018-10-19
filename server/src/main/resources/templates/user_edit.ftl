<#import "parts/common.ftl" as c>

<@c.page>

<form action="/user/edit" method="post">

    <label>
        USERNAME
        <input type="text" name="username" value="${user.username}">
        Input your new username (more 3 symb)
    </label>
    <div>
        Role
        <label><input type="checkbox" name="admin_role" <#if func_admin>checked="checked"</#if>>ADMIN</label>
        <label><input type="checkbox" name="user_role" <#if func_user>checked="checked"</#if>>USER</label>
    </div>
    <input type="hidden" value="${user.id}" name="userId">
    <input type="hidden" name="_csrf" value="${_csrf.token}">

    <button type="submit">Save</button>
</form>

</@c.page>