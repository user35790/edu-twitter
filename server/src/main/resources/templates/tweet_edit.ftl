<#import "parts/common.ftl" as c>
<@c.page>

<form action="/tweet/edit/" method="post">
    <div class="alert alert-primary text-center" role="alert">Edit tweet</div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Text</label>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="text" value="${tweet.text}">
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label">File</label>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="file" value="${tweet.filename}">
        </div>
    </div>


    <input type="hidden" value="${tweet.id}" name="userId">
    <input type="hidden" name="_csrf" value="${_csrf.token}">

    <button type="submit" class="btn btn-primary">Save</button>
</form>

</@c.page>