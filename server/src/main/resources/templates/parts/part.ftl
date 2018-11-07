<#macro tw tweets>
<div class="card column m-5">
        <#list tweets as tweet>
            <div class="card m-5">
                    <#if tweet.filename??>
                        <img style="width: 100px" class="card-img-top" src="/img/${tweet.filename}">
                    </#if>
                <div class="card-body">
                    <h5 class="card-title">
                        <a class="" href="/user/${tweet.author.id}">@${tweet.author.username}</a>
                    </h5>
                    <div class="card-text">
                        <pre>${tweet.text}</pre>
                    </div>
                    <a class="btn btn-primary" href="/tweet/edit/${tweet.id}">Edit tweet</a>
                </div>
            </div>
        <#else>
            <div class="alert alert-warning" role="alert">No tweets</div>
        </#list>
</div>
</#macro>

<#macro usr users>
    <#list users as user>
    <div class="card my-3">
        <div class="card-body">
            <a class="" href="/user/${user.id}"><h5>@${user.username}</h5></a>
            <p class="card-text">id: ${user.id}</p>
            <a href="/user/edit/${user.id}" class="btn btn-primary">Edit profile</a>
        </div>
    </div>
    <#else>
        No users
    </#list>
</#macro>