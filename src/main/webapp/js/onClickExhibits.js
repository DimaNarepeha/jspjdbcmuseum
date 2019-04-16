function proceed (clicked_id) {
    var form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', '');
    var x = document.createElement("INPUT");
  x.setAttribute("type", "text");
  x.setAttribute("name", "id");
  x.setAttribute("value", clicked_id);
  x.style.display='none';
  form.appendChild(x);
    form.style.display = 'hidden';
    document.body.appendChild(form);
    form.submit();
}