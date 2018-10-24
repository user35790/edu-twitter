<#import "parts/common.ftl" as c>

<@c.page>

    <#if message??>
        <div class="alert alert-warning" role="alert">
            ${message}
        </div>
    </#if>

<form action="/user/edit" method="post">
    <div class="alert alert-primary text-center" role="alert">Edit profile</div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Name</label>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="name" value="<#if user.name??>${user.name}</#if>"
                   placeholder="Name">
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label">About</label>
        <div class="col-sm-10">
            <textarea class="form-control" aria-label="With textarea"
                      name="about"><#if user.about??>${user.about}<#else></#if></textarea>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Role</label>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios1" value="admin_role"
               <#if func_admin>checked="checked"</#if> >
            <label class="form-check-label" for="exampleRadios1">
                Admin
            </label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios1" value="option1"
               <#if func_user>checked="checked"</#if>>
            <label class="form-check-label" for="exampleRadios1">
                User
            </label>
        </div>
    </div>


    <input type="hidden" value="${user.id}" name="userId">
    <input type="hidden" name="_csrf" value="${_csrf.token}">

    <button type="submit" class="btn btn-primary">Save</button>
</form>

</@c.page>