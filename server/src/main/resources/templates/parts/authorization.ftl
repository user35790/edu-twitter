<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> User Name :  </label>
        <div class="col-sm-6">
            <input type="text" name="username" placeholder="User name">
        </div>
    </div>

    <div class="form-group row">
    <label class="col-sm-2 col-form-label"> Password :  </label>
    <div class="col-sm-6">
        <input type="text" name="password" placeholder="Password">
    </div>
</div>

    <#if isRegisterForm>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Email :  </label>
        <div class="col-sm-6">
            <input type="email" name="email" placeholder="example@example.com">
        </div>
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

<#macro logout>
<form action="/logout" method="post">
    <button class="btn btn-primary" type="submit">Sign out</button>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
</form>
</#macro>