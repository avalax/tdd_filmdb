<#include "layout.ftl">
<#macro header>
  <title>Film list</title>
</#macro>

<#macro breadcrumb>
    <ol class="breadcrumb">
        <li class="active">Home</li>
    </ol>
</#macro>

<#macro content>
    <div class="panel panel-default">
        <div class="panel-heading">tdd_filmdb</div>
        <div class="panel-body">
           <p>Overview of all films</p>
        <div>
        <ul class="list-group">
            <#list films as film>
                <li class="list-group-item">
                    <a href="/film/${film.id.id}" class="btn btn-danger js-delete badge">Delete</a>
                    <a href="/film/${film.id.id}">${film.name}</a>
                    <#list 0..<film.rating as i><span class="glyphicon glyphicon-star" aria-hidden="true"></span></#list>
                </li>
            </#list>
        </ul>
        <div class="panel-footer">
            <#include "form.ftl">
        </div>
    </div>
</#macro>

<@layout/>