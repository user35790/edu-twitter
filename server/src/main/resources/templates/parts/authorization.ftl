<#include "security.ftl">

<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="form-group">
        <label class="col-sm-2 col-form-label"> User Name : </label>
        <input type="text" name="username" placeholder="User name">
    <#if isRegisterForm>
        <small id="emailHelp" class="form-text text-muted">Username must be more 3 symbols!</small>
    </#if>
    </div>

    <div class="form-group">
        <label class="col-sm-2 col-form-label"> Password : </label>
        <input type="text" name="password" placeholder="Password">
    </div>

    <#if isRegisterForm>
    <div class="form-group">
        <label class="col-sm-2 col-form-label"> Email : </label>
        <input type="email" name="email" placeholder="example@example.com">
    </div>
    </#if>

    <input type="hidden" name="_csrf" value="${_csrf.token}"/>

    <#if !isRegisterForm>
        <a href="/registration">Add new author</a>
    </#if>

    <button class="btn btn-primary" type="submit">
        <#if isRegisterForm>
            Create
        <#else>
            Sign in
        </#if>
    </button>
</form>
</#macro>

<#macro log>
    <#if know>
<form action="/logout" method="post">
    <button class="btn btn-primary" type="submit">Sign out</button>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
</form>
    <#else>
    <form action="/login" method="get">
        <button class="btn btn-primary" type="submit">Sign in</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
    </#if>

</#macro>