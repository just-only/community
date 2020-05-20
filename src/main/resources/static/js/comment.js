function replay(id,name) {
        var commentor= name;
        var parent_id = id;
       $("div[id="+id+"]").each(function(){
            $(this).toggle();
        });
        $("#replay_name").html("@"+commentor);
        $("#parent_id").val(parent_id);
        $("#type_id").val(parent_id);
        console.log(parent_id);
}
function replay1(id,title,name) {
        var commentor= name;
        var parent_id = id;
        var type_id= title;
        $("#replay_name").html("@"+commentor);
        $("#parent_id").val(parent_id);
        $("#type_id").val(type_id);
        console.log(commentor);
        console.log(parent_id);
        console.log(type_id);
}
function replayALl() {
    var questionId = $("#question_id").val();
    var commentor   = $("#commentor").val();
    var commentText = $("#comment_text").val();
    var parentId  = $("#parent_id").val();
    var type_id = $("#type_id").val();
    $.ajax({
        type: "POST",
        url: "/comment/replay",
        contentType : "application/json",
        data: JSON.stringify({
            "questionId": questionId,
            "commentor": commentor,
            "commentText": commentText,
            "parentId": parentId,
            "type":type_id
        }),
        error: function(data){
            console.log(data);
            if(data.responseText=="fail"){
                alert("评论失败！");
            }else if(data.responseText=="success"){
                alert("评论成功！");
            }else{
                alert(data.responseText);
            }
        },
        dataType: "json"
    });
}


function questionGiveUp(name,title){
    var id = name;
    var user_id = title;
    if(user_id == '0'){
        alert("用户未登录，不能点赞哦！");
        return ;
    }
    $.ajax({
        type: "POST",
        url: "/question/giveup",
        data: {"id":id},
        success : function(){
           alert("问题点赞成功");
        }
    });
}
function comment1GiveUp(uid,name) {
    var id = name;
    var user_id = uid;
    if(user_id=='0'){
        alert("用户未登录，不能点赞哦！");
        return ;
    }
    $.ajax({
        type: "POST",
        url: "/comment/giveup",
        data: {"id":id},
        success : function(){
            alert("评论点赞成功");
        }
    });
}