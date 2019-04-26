$(document).ready(function () {
    //responsie
    let height = $(window).height();
    $(".img-background").css("height", height);
    $(window).resize(function () {
        height = $(window).height();
        if (height > 564) {
            $(".img-background").css("height", height);
        } else {
            $(".img-background").css("height", 635);
        }
    });
    $(".input-login").keypress(function (event) {
        let keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            onSubmit();
        }
    });
    $(".btn-login .btn").click(function (event) {
      //  onSubmit(event);
    })
});
//end_reponsive
//action when click or enter
function onSubmit(event) {
    const username = $("#username").val();
    const password = $("#password").val();
    if (username.length == 0 || password.length == 0) {
        alert("username or password is blank!");
        event.preventDefault(event);
        return;
    }
    console.log(username + " " + password);
    const loginForm = {
        "username": username,
        "password": password
    };

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/v1/public/login",
        data: JSON.stringify(loginForm),
        cache: false,
        timeout: 300000,
        success: function (status) {
            console.log(status);
            if (status == "fail"){
                alert("login fail");
                event.preventDefault(event);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('jqXHR:');
            console.log(jqXHR);
            console.log('textStatus:');
            console.log(textStatus);
            console.log('errorThrown:');
            console.log(errorThrown);
            alert("error");
            event.preventDefault(event);
        }
    });
}

