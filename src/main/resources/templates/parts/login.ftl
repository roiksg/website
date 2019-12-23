<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group ">
            <label for="user" class="col-sm-2 col-form-label">User Name</label>
            <div class="col-sm-5">
            <input type="text" class="form-control" id="user"
                   aria-describedby="userHalp" placeholder="Login email" name="username">
            </div>
        </div>
        <div class="form-group">
            <label for="InputPassword" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-5">
            <input type="password" class="form-control" id="InputPassword"
                   placeholder="Password" name="password">
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <#if !isRegisterForm> <a href="/registration">Add new user</a></#if>
        <button type="submit" class="btn btn-primary">
            <#if isRegisterForm>
                Create
                <#else>
                    Submit
            </#if>
        </button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Sign Out</button>
    </form>
</#macro>