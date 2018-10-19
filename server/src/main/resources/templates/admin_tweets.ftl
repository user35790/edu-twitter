<#import "parts/common.ftl" as c>

<@c.page>

<div class="form-row">
<form method="get" action="/tweet/monitor" class="form-inline">
    <input type="text" name="filter" class="form-control" placeholder="Search by user"/>

    <input type="hidden" name="_csrf" value="${_csrf.token}"/>

    <button class="btn btn-primary ml-3" type="submit">Search</button>
</form>
</div>

<dl>
<#list tweets as tweet>
    <dt><strong>${tweet.author.username}</strong></dt>
    <dd>
        <ul>
            <li>ID: ${tweet.id}</li>
            <li>TEXT:${tweet.text}</li>
        </ul>
    </dd>
    <#else>
    No tweets
</#list>
</dl>

</@c.page>