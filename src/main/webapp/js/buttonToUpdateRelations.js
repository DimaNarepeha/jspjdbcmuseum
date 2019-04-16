

function proceed () {
	var ids="";
    var form = document.createElement('form');
   	var table = document.getElementById("in");
var r=0;
while(row=table.rows[r++])
{
  ids+=row.id+" ";
}
    form.setAttribute('method', 'post');
    form.setAttribute('action', '');
    var x = document.createElement("INPUT");
  x.setAttribute("type", "text");
  x.setAttribute("name", "id");
  x.setAttribute("value", document.getElementsByTagName("body")[0].id);
  x.style.display='none';
  form.appendChild(x);
  x = document.createElement("INPUT");
  x.setAttribute("type", "text");
  x.setAttribute("name", "idsToUpdate");
  x.setAttribute("value", ids);
  x.style.display='none';
  form.appendChild(x);
    form.style.display = 'hidden';
    document.body.appendChild(form);
    form.submit();
}