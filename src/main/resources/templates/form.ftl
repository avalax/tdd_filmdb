<form action="/film<#if film??>/${film.id.id}</#if>" method="post">
  <div class="form-group">
    <label for="name">Name</label>
    <input required name="name" type="text" class="form-control" id="name" placeholder="Name" value="<#if film??>${film.name!}</#if>">
  </div>
  <div class="form-group">
    <label for="genre">Genre</label>
    <input required name="genre" type="text" class="form-control" id="genre" placeholder="Genre" value="<#if film??>${film.genre!}</#if>">
  </div>
    <div class="form-group">
      <label for="year">Year</label>
      <input required pattern="[0-9]*" name="year" class="form-control js-year" id="year" placeholder="Year" value="<#if film??>${film.year?string.computer!}</#if>">
    </div>
  <div class="form-group">
    <label for="rating">Rating</label>
    <select id="year" required type="radio" name="rating" class="form-control">
        <option value="1"<#if film?? && film.rating==1> selected="selected"</#if>>1</option>
        <option value="2"<#if film?? && film.rating==2> selected="selected"</#if>>2</option>
        <option value="3"<#if film?? && film.rating==3> selected="selected"</#if>>3</option>
    </select>
  </div>
  <button type="submit" class="btn btn-default">Submit</button>
</form>