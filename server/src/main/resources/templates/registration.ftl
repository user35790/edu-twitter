<#import "parts/common.ftl" as c>
<#import "parts/authorization.ftl" as l>

<@c.page>
<b>Add new author</b>
${message?ifExists}
<@l.login "/registration" />
</@c.page>