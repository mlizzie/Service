const general_url = './';

//Отправляет запрос для получения гифки.
function loadResultGif() {
    let code = $("#codes").val();
    $.ajax({
        url: general_url + 'getgif/' + code,
        method: 'GET',
        dataType: "json",
        complete: function (data) {
            let content = JSON.parse(data.responseText);
            let img = document.createElement("img");
            let gifName = document.createElement("p");
            gifName.textContent = content.data.title;
            let gifKey = document.createElement("p");
            gifKey.textContent = content.compareResult;
            img.src = content.data.images.original.url;
            let out = document.querySelector("#out");
            out.innerHTML = '';
            out.insertAdjacentElement("afterbegin", img);
            out.insertAdjacentElement("afterbegin", gifName);
            out.insertAdjacentElement("afterbegin", gifKey);
        }
    })
}