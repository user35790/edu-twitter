<#import "parts/common.ftl" as c>
<#import "parts/authorization.ftl" as l>

<@c.page>

<@l.logout/>

    <div>
        <b>Admin Panel</b>
        <ul>
            <li><a href="/tweet/monitor">Tweets monitoring</a></li>
            <li><a href="/user/monitor">Users monitoring</a></li>
        </ul>

    </div>

    <div>
        <b>User page</b>
        <a href="/user">Home</a>
    </div>

</@c.page>