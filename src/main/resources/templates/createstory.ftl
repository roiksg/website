<#import "parts/common.ftl" as common>

<@common.page>
    <div class="form-row">
        <div class="form-group col-md-7">
            <form method="post" action="filterstory" class="form-inline">
                <input type="text" class="form-control" name="filter">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>
    ${message?if_exists}
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new story
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form action="/createstory" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" name="title" class="form-control" placeholder="Введите название рассказа">
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="cover" id="FormControlFile1">
                        <label for="FormControlFile1" class="custom-file-label">Choose file</label>
                    </div>
                </div>
                <div class="form-group">
                    <textarea type="text" name="description" class="form-control" placeholder="Описание"></textarea>
                </div>
                <div class="form-group">
                    <input type="text" name="genre" class="form-control" placeholder="Введите жанр текста">
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>
    <div class="card-columns">
        <#list story as storys>
            <div class="card my-3">
                <#if storys.cover??>
                    <img src="/img/${storys.cover}" class="card-img-top">;
                </#if>
                <div class="m-1">
                    <span>${storys.title}</span>
                </div>
                <div class="m-1">
                    <span>${storys.genre}</span>
                </div>
                <div class="m-1">
                    <span>${storys.description}</span>
                </div>
                <div class="card-footer text-muted">
                    ${storys.author}
                </div>
            </div>
        </#list>
    </div>
</@common.page>