<#import "parts/common.ftl" as c>

<@c.page>

<div>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add tweet
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form action="/tweet/add" method="post" enctype="multipart/form-data">
                <input type="text" class="form-control" name="text"/>

                <label for="inputFile">Choose file</label>
                <input type="file" id="inputFile" name="file">

                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <button class="btn btn-primary" type="submit">Add</button>
            </form>
        </div>
    </div>
</div>

<div class="card-columns">
    <#list tweets as tweet>
        <div class="card my-3">
                <#if tweet.filename??>
                    <img class="card-img-top" src="/img/${tweet.filename}">
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
    No tweets
    </#list>
</div>
</@c.page>