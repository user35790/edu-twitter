<#include "security.ftl">

<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">User Name :</label>
        <div class="col-sm-6">
            <input type="text" name="username" value="<#if user??>${user.username}</#if>"
                   class="form-control ${(usernameError??)?string('is-invalid', '')}"
                   placeholder="User name"/>
            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
            </#if>
        </div>
    </div>


    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password :</label>
        <div class="col-sm-6">
            <input type="text" class="form-control ${(passwordError??)?string('is-invalid', '')}"
                   value="<#if user??>${user.password}</#if>"
                   name="password" placeholder="Password">
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
        </div>
    </div>

    <#if isRegisterForm>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email :</label>
            <div class="col-sm-6">
                <input type="email" class="form-control ${(emailError??)?string('is-invalid', '')}"
                       value="<#if user??>${user.email}</#if>"
                       name="email" placeholder="example@example.com">
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>
            </div>
        </div>
    </#if>

    <input type="hidden" name="_csrf" value="${_csrf.token}"/>

    <#if isRegisterForm>
        <div>
            <div class="g-recaptcha" data-sitekey="6LcLInoUAAAAAE8HzB82vdtkOOiuq-U7LPfl7hhG"></div>
                <#if captchaError??>
                    <div>
                        ${captchaError}
                    </div>
                </#if>
        </div>
        <a class="btn btn-secondary" href="/login">Back</a>
        <button class="btn btn-primary" type="submit">Create</button>
    <#else>
        <a class="btn btn-secondary" href="/registration">Registration</a>
        <button class="btn btn-primary" type="submit">Login</button>
    </#if>
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