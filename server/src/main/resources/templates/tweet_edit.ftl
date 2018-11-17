<#import "parts/common.ftl" as c>

<@c.page>

<form action="/tweet/edit" method="post" enctype="multipart/form-data">

    <div class="alert alert-primary text-center" role="alert">Edit tweet</div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Text</label>
        <div class="col-sm-10">
            <textarea class="form-control" type="text" name="text">${tweet.text}</textarea>
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label">File</label>
        <div class="col-sm-10">
            <div class="card w-25">
                <#if tweet.filename??>
                    <img src="/img/${tweet.filename}" class="mw-100">
                </#if>
            </div>
            <div class="input-group my-2">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
                </div>
                <div class="custom-file">
                    <input type="file" class="custom-file-input my-2 " id="inputGroupFile01"
                           aria-describedby="inputGroupFileAddon01" name="file">
                    <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
                </div>
            </div>
        </div>
    </div>

    <input type="hidden" value="${tweet.id}" name="id">
    <input type="hidden" name="_csrf" value="${_csrf.token}">

    <button type="submit" class="btn btn-primary">Save</button>
</form>

    <form action="/tweet/delete/${tweet.id}" method="post">
        <button type="submit" class="btn btn-danger mt-5">Delete</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>

</@c.page>