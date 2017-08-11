<#macro layout>
    <!DOCTYPE html>
    <html lang="en">
      <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <@header/>
        <link rel="stylesheet" href="/css/bootstrap.min.css">
        <link rel="stylesheet" href="/css/jquery-ui.min.css">
        <link rel="stylesheet" href="/css/film.css">
        <link rel="stylesheet" href="/css/bootstrap-theme.min.css">
      </head>
      <body>
        <@breadcrumb/>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2 hidden-sm"></div>
                <div class="col-md-8 col-sm-12">
                    <@content/>
                </div>
            </div>
        </div>
        <script src="/js/jquery-3.2.1.min.js"></script>
        <script src="/js/jquery-ui.min.js"></script>
        <script src="/js/bootstrap.min.js"></script>
        <script src="/js/film.js"></script>
      </body>
    </html>
</#macro>