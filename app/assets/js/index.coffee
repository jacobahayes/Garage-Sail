$ ->
  $.get "/person", (persons) ->
    $.each persons, (index, person) ->
      $('#person').append $("<li>").text person.name