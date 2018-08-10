$(function () {
    $("#username").blur(function () {
        var userName=$("#username").val();
        if(userName==""){
            $("#message").html("用户名不能为空");
        }else{
            $("#message").html("  ");
            $.ajax({
                url:"/login?methodName=validateName",
                data:{
                    "userName":userName
                },
                dateType:"JSON",
                success:function (data) {
                    if(data.status==1){
                        $("#message").html("11111");
                    }else{
                        $("#message").html(data.message);
                    }
                }
            })
        }
    })
})