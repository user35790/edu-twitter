<#import "parts/common.ftl" as c>
<#import "parts/authorization.ftl" as l>

<@c.page>
<div class="mb-1">Add new author</div>
${message?ifExists}
<@l.login "/registration" true/>
</@c.page>