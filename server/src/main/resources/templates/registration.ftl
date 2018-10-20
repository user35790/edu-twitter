<#import "parts/common.ftl" as c>
<#import "parts/authorization.ftl" as l>

<@c.page>
    <#if message??>
        <div class="alert alert-warning" role="alert">
            ${message}
        </div>
    </#if>
<@l.login "/registration" true/>
</@c.page>