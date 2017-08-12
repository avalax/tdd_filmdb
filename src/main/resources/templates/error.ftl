<#include "layout.ftl">
<#macro header>
  <title>Film list</title>
</#macro>

<#macro breadcrumb>
    <ol class="breadcrumb">
         <li><a href="/">Home</a></li>
          <li class="active">${status} ${error}</li>
    </ol>
</#macro>

<#macro content>
    <div class="jumbotron">
        <h1>Error ${status}</h1>
        <p>${error}</p>
        <p>${message}</p>
    </div>
</#macro>

<@layout/>