$(function(){
    const appendWorkTime = function(data){
        var worktimeCode = '<td>'+ data.station + '</td>';
        var time = data.time;
        worktimeCode += '<td>';
        if(time.includes(".09.17")){
            worktimeCode += time.slice(time.indexOf(".09.17")+6,time.indexOf(","));
            time = time.slice(time.indexOf(",")+1,time.length);
        }
        worktimeCode += '</td><td>';
        if (time.includes(".09.19")){
            worktimeCode +=time.slice(time.indexOf(".09.19")+6,time.indexOf(","));
            time = time.slice(time.indexOf(",")+1,time.length);
        }
        worktimeCode += '</td><td>';
        if (time.includes(".09.21")){
            worktimeCode +=time.slice(time.indexOf(".09.21")+6,time.length);
        }
        worktimeCode += '</td>';
        $("#worktimes").append('<tr>'+worktimeCode+'</tr>');
    };
    $.get('/times', function (response) {
        for(i in response){
            appendWorkTime(response[i]);
        }
    });
    $("#postButton").click(function() {
        var id = document.getElementById('id').value;
        $.ajax({
            method:"GET",
            url:'/times/'+id,
            success:function(response){
                var wt = '<h2 class="workTime">'+ response.station +'  '+response.time + '</h2>' ;
                $("#oneSt").append(wt);
            },
            error: function (response) {
                if(response.status==404){
                    alert("Нет такой станции");
                }
            }
        })
    });
    $(document).on("dblclick", ".workTime", function() {
        $(".workTime").remove();
    });
});

