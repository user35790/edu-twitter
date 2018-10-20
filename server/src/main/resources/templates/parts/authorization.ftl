<#include "security.ftl">

<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="form-group">
        <div class="row">
            <label for="inputName" class="col col-md-2">Username</label>
            <input type="text" class="col col-md-6" name="username" placeholder="Username" id="inputName"
                   aria-describedby="nameHelp">
        </div>
        <#if isRegisterForm>
            <small id="nameHelp" class="form-text text-muted ">Username must be uniq and more than 3 symbols.</small>
        </#if>
    </div>

    <div class="form-group">
        <div class="row">
            <label for="inputPassword" class="col col-md-2">Password</label>
            <input type="text" class="col col-md-6" name="password" placeholder="Password" id="inputPassword"
                   aria-describedby="passwordHelp">
        </div>
        <#if isRegisterForm>
            <small id="passwordHelp" class="form-text text-muted ">Password must be more 5 symbols.</small>
        </#if>
    </div>

    <#if isRegisterForm>
        <div class="form-group">

            <div class="row">
                <label for="inputEmail" class="col col-md-2">Email</label>
                <input type="email" class="col col-md-6" name="email" placeholder="example@example.com" id="inputEmail"
                       aria-describedby="emailHelper">
            </div>

            <#if isRegisterForm>
                <small id="emailHelp" class="form-text text-muted ">We'll never share your email with anyone else.
                </small>
            </#if>
        </div>
    </#if>

    <input type="hidden" name="_csrf" value="${_csrf.token}"/>

    <#if !isRegisterForm>
        <a class="btn btn-secondary" href="/registration">Registration</a>
    <#else>
        <a class="btn btn-secondary" href="/login">Back</a>
    </#if>

    <button class="btn btn-primary" type="submit">
        <#if isRegisterForm>
            Create
        <#else>
            Login
        </#if>
    </button>
</form>
</#macro>

<#macro btn_log>
    <#if know>
    <div class="nav-item dropdown mr-2">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown"
           aria-haspopup="true" aria-expanded="false">
            ${name}
        </a>
        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown2">
            <a class="dropdown-item" href="/user/edit/">Edit profile</a>
            <div class="dropdown-divider"></div>
            <form action="/logout" method="post" class="col align-items-right">
                <button class="btn btn-primary" type="submit">Logout</button>

                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </form>
        </div>
    </div>
    <#else>
    <form action="/login" method="get">
        <button class="btn btn-primary" type="submit">Login</button>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
    </#if>
</#macro>