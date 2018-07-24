<#include "layout.ftl">
<#macro header>
  <title>Film</title>
</#macro>

<#macro breadcrumb>
    <ol class="breadcrumb">
        <li><a href="/">Home</a></li>
        <li class="active">${film.name!}</li>
    </ol>
</#macro>

<#macro content>
    <div class="panel panel-default">
        <div class="panel-heading hideOverflowFull">${film.name!}</div>
        <div class="panel-body">
           <#include "form.ftl">
        </div>
    </div>
</#macro>

<@layout/>