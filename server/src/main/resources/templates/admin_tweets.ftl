<#import "parts/common.ftl" as c>
<#import "parts/part.ftl" as l>

<@c.page>

<div class="form-row">
    <form method="get" action="/tweet/monitor" class="form-inline">
        <input type="text" name="filter" class="form-control" placeholder="Search by user"/>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

        <button class="btn btn-primary ml-3" type="submit">Search</button>
    </form>
</div>

    <@l.tw tweets/>


</@c.page>