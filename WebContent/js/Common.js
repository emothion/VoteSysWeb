/**
 * 
 */
function logout(url) {
	if(confirm('您确定要退出系统吗?')){
		window.location.href=url+"/userOperate/userLogout.do";
	}
}

function sleep(numberMillis) {
    var now = new Date();
    var exitTime = now.getTime() + numberMillis;
    while (true) {
        now = new Date();
        if (now.getTime() > exitTime)
            return;
    }
}
