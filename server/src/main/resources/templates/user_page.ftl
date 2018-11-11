<#import "parts/common.ftl" as c>
<#import "parts/part.ftl" as l>

<@c.page>

    <#if message??>
        <div class="alert alert-warning" role="alert">
            ${message}
        </div>
    </#if>

<@l.user user isAuth/>
    <@l.tw tweets isAuth/>

</@c.page>