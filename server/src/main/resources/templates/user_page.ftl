<#import "parts/common.ftl" as c>

<@c.page>

    <#if message??>
        <div class="alert alert-warning" role="alert">
            ${message}
        </div>
    </#if>

    <div class="card">
        <div class="card-body">
            <h5 class="card-title"><#if ((userInfo.name)?? && userInfo.name != "")>${userInfo.name}<#else>Name</#if></h5>
            <div class="card-text">
                <pre><#if ((userInfo.about)?? && userInfo.about != "")>${userInfo.about}<#else>No about info.</#if></pre>
            </div>

            <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button"
               aria-expanded="false"
               aria-controls="collapseExample">Actions</a>

            <a class="btn btn-primary" href="/user/edit/0" role="button">Edit</a>
            <div class="collapse" id="collapseExample">
                <div class="card border-primary my-3">
                    <div class="card-header text-center">Add tweet</div>
                    <div class="card-body text-primary">
                        <form action="/tweet/add" method="post" enctype="multipart/form-data">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Your tweet</span>
                                </div>
                                <textarea class="form-control" aria-label="With textarea" name="text"></textarea>
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

                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                            <button class="btn btn-primary" type="submit">Public tweet</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="card-columns">
        <#list tweets as tweet>
            <div class="card my-3">
                    <#if tweet.filename??>
                        <img style="width: 100px" class="card-img-top" src="/img/${tweet.filename}">
                    </#if>
                <div class="card-body">
                    <h5 class="card-title">
                        ${tweet.id}
                    </h5>
                    <div class="card-text">
                        ${tweet.text}
                    </div>
                </div>
            </div>
        <#else>
            <div class="alert alert-warning" role="alert">No tweets</div>
        </#list>
    </div>
</@c.page>