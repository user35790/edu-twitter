<#import "parts/common.ftl" as c>
<#import "parts/authorization.ftl" as l>

<@c.page>

    <@l.logout/>
    <a href="/">MAIN PAGE</a>

<div>
    <div>
        <a href="/user/edit/">EDIT PROFILE</a>
    </div>

    <div>Add <b>new tweet</b></div>

    <form action="/tweet/add" method="post" enctype="multipart/form-data">
        <input type="text" name="text"/>
        <input type="file" name="file">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Add tweet</button>
    </form>
</div>

<div>
    <div>My tweets</div>
    <#list tweets as tweet>
        <div>
            <i>${tweet.id}</i>
            <b>${tweet.text}</b>
            <div>
                <#if tweet.filename??>
                <img src="/img/${tweet.filename}">
                </#if>
            </div>
        </div>
    <#else>
    No tweets
    </#list>
</div>

</@c.page>