<#import "parts/common.ftl" as c>
<#import "parts/authorization.ftl" as l>

<@c.page>

    <@l.logout/>
    <a href="/">MAIN PAGE</a>

<div>Tweet list</div>
<form method="get" action="/tweet/monitor">
    <div>filter by author</div>
    <input type="text" name="filter"/>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit">Confirm</button>
</form>

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