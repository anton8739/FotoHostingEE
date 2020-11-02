fotoEditFoto=document.querySelector("#fotoEditSubmit");
fotoEditFoto.addEventListener('click', editFotoCommand.bind(null));

function editFotoCommand() {
    var request= new XMLHttpRequest();
    var form=document.forms.editFotoForm;
    var title=form.elements.title.value;
    var description=form.elements.description.value;
    var body="command=edit-foto&title="+title+"&description="+description+"&fotoId="+jFotoId;
    console.log(body);
    request.open('POST', 'controller',true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.send(body);
    request.addEventListener("readystatechange", serverAnswer.bind(null));
    function serverAnswer(){
        if (request.readyState === 4 && request.status === 200) {
            var message = {
                title : "",
                description:""
            }
            var Answer=request.responseText;
            message=JSON.parse(Answer,message);
            document.getElementById("fotoTitl").textContent=message.title;
            document.getElementById("fotoDes").textContent=message.description;
            $("#fotoEditModel").modal("hide");

        }
    }
}
