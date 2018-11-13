<#include "security.ftl">

<#macro tw tweets isAuth>
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
                    <#if isAuth>
                    <a class="btn btn-primary" href="/tweet/edit/${tweet.id}">Edit tweet</a>
                    </#if>
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

<#macro user userInfo isAuth>
    <div class="card">
        <div class="card-body">
            <h5 class="card-title"><#if ((userInfo.name)?? && userInfo.name != "")>${userInfo.name}<#else>
                Name</#if></h5>
            <div class="card-text">
                <pre><#if ((userInfo.about)?? && userInfo.about != "")>${userInfo.about}<#else>No about info.</#if></pre>
            </div>


            <div class="row">
                <div class="col">
                    <div class="card m-2">
                        <div class="card-body">
                            <div class="card-title">
                                <h4>Subscriptions</h4>
                            </div>
                            <div class="card-text">
                                <a href="/user/subscriptions/${userInfo.id}"> ${userInfo.getFriendOf()?size}</a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col">
                    <div class="card m-2">
                        <div class="card-body">
                            <div class="card-title">
                                <h4>Subscribers</h4>
                            </div>
                            <div class="card-text">
                                <a href="/user/subscribers/${userInfo.id}"> ${userInfo.getFriends()?size}</a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <a class="btn btn-primary" href="/user/edit/0" role="button">Edit</a>


            <#if isAuth>
            <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button"
               aria-expanded="false"
               aria-controls="collapseExample">Actions</a>

            <div class="collapse" id="collapseExample">
                <div class="card border-primary my-3">
                    <div class="card-header text-center">Add tweet</div>
                    <div class="card-body text-primary">

                        <form action="/tweet/add" method="post" enctype="multipart/form-data">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Your tweet</span>
                                </div>
                                <textarea class="form-control" aria-label="With textarea" name="text"></textarea>
                            </div>

                            <div class="input-group my-2">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
                                </div>
                                <div class="custom-file">
                                    <input type="file" class="custom-file-input my-2 " id="inputGroupFile01"
                                           aria-describedby="inputGroupFileAddon01" name="file">
                                    <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
                                </div>
                            </div>

                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                            <button class="btn btn-primary" type="submit">Public tweet</button>
                        </form>
                    </div>
                </div>
            </div>
            <#else>

            <#if !isSubscriber>
                <form action="/user/subscribe" method="post">
                    <input type="hidden" name="userSubscrId" value="${userInfo.id}"/>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button class="btn btn-primary" type="submit">Subscribe</button>
                </form>
            <#else>
            <form action="/user/unsubscribe" method="post">
                <input type="hidden" name="userSubscrId" value="${userInfo.id}"/>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-primary" type="submit">Unsubscribe</button>
            </form>
            </#if>

            </#if>
        </div>
    </div>
</#macro>


<#macro subscr users isSubscriptions>

<div class="card">

    <div class="card-body">
        <div class="card-title">
    <#if isSubscriptions>
        Subscriptions
    <#else>
        Subscribers
    </#if>
        </div>


    <#list users as user>
            <div class="card my-3">
                <div class="card-body">
                    <a class="" href="/user/${user.id}"><h5>@${user.username}</h5></a>
                </div>
            </div>
    <#else>
    <div class="card-text">
        No users
    </div>
    </#list>

    </div>
</div>

</#macro>