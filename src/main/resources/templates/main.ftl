<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as login>

<@common.page>
    <div>
        <@login.logout/>
        <span><a href="/user">User list</a> </span>
    </div>
    <a href="/createstory">Add story</a>
    <div>
        <form method="post">
            <input type="text" name="text" placeholder="Введите сообщение">
            <input type="text" name="tag" placeholder="Тэг">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Добавить</button>
        </form>
    </div>
    <div>Список сообщений</div>
    <form method="get" action="/main">
        <input type="text" name="filter">
        <button type="submit">Найти</button>
    </form>
    <#list messages as message>
    <div>
        <span>${message.text}</span>
        <i>${message.tag}</i>
    </div>
        <#else >
        No message
    </#list>
</@common.page>