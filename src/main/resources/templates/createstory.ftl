<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as login>

<@common.page>
    <div>
        <@login.logout/>
    </div>
    ${message}
    <div>
        <form action="/createstory" method="post">
            <input type="text" name="title" placeholder="Введите название рассказа">
            <input type="text" name="cover" placeholder="ссылка на обложку">
            <textarea type="text" name="description" placeholder="Описание"></textarea>
            <input type="text" name="genre" placeholder="Введите жанр текста">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Добавить</button>
        </form>
    </div>
    <div>Список сообщений</div>
    <form method="post" action="filterstory">
        <input type="text" name="filter">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Найти</button>
    </form>
    <#list story as storys>
    <div>
        <span>${storys.title}</span>
        <span>${storys.genre}</span>
        <i>${storys.author}</i>
    </div>
    </#list>
</@common.page>