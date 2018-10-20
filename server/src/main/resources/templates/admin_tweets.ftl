<#import "parts/common.ftl" as c>

<@c.page>

<div class="form-row">
    <form method="get" action="/tweet/monitor" class="form-inline">
        <input type="text" name="filter" class="form-control" placeholder="Search by user"/>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

        <button class="btn btn-primary ml-3" type="submit">Search</button>
    </form>
</div>

<ul class="list-group">
    <#list tweets as tweet>
        <li class="list-group-item my-2">
            <div class="card">

                <div class="card-body">
                    <h5 class="card-title">${tweet.author.username}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">id: ${tweet.id}</h6>
                    <p class="card-text">text: ${tweet.text}</p>
                <#if tweet.filename??>
                    <p class="card-text">file: <img style="width: 100px" class="card-img-top" src="/img/${tweet.filename}"></p>
                </#if>
                </div>
            </div>
        </li>
    <#else>
        <div class="alert alert-warning" role="alert">No tweets</div>
    </#list>

</@c.page>