$(function(){
    //responsie
    var height = $(window).height();
    $(".img-background").css("height",height);
    $(window).resize(function() {
        height = $(window).height();
        if (height > 564) {
            $(".img-background").css("height",height);
        } else {
            $(".img-background").css("height",635);
        }
    })
    //end_reponsive
    //action when click or enter
    function onSumbit() {
        alert('Đăng nhập thất bại!');
    }

    $(".input-login").keypress(function(event){
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if(keycode == '13'){
            onSumbit();
        }
    })
    $(".btn-login .btn").click(function() {
        onSumbit();
    })
})