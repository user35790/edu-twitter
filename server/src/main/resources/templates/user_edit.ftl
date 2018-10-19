<#import "parts/common.ftl" as c>
<#import "parts/authorization.ftl" as l>

<@c.page>

<@l.logout/>
<a href="/">MAIN PAGE</a>

<form action="/user/edit" method="post">

    <label>
        USERNAME
        <input type="text" name="username" value="${user.username}">
        Input your new username (more 3 symb)
    </label>
    <div>
        Role
        <label><input type="checkbox" name="admin_role" <#if func_admin>checked="checked"</#if>>ADMIN_r</label>
        <label><input type="checkbox" name="user_role" <#if func_user>checked="checked"</#if>USER_r</label>
        Check your role
    </div>
    <input type="hidden" value="${user.id}" name="userId">
    <input type="hidden" name="_csrf" value="${_csrf.token}">

    <button type="submit">Save</button>
</form>

</@c.page>