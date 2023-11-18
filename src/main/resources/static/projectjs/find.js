onload = function () {
    banner();

};
var banner = function () {
    /*1.自动无缝轮播*/
    var banner = document.querySelector(".find-banner");
    var width =  banner.offsetWidth;
    var imgbox = document.querySelector(".find-banner ul:first-child");
    // console.log(imgbox);
    var imgbox2 = document.querySelector(".find-banner ul:last-child");
    var points = document.querySelectorAll(".find-banner ul:last-child li");
    // console.log(points)
    var index =1;
    var stop = setInterval(function () {
        index++;
        /*加过渡*/
        imgbox.style.transaction = 'all 0.2s';
        imgbox.style.webkitTransaction = 'all 0.2s';
        /*做位移*/
        imgbox.style.transform = 'translateX('+(-index*width)+'px)';
        if (index>=9){
            index = 1;
            imgbox.style.transform = 'translateX('+(-index*width)+'px)';
        }else if (index<=0){
            index = 8;
            imgbox.style.transform = 'translateX('+(-index*width)+'px)';
        }
        setpoint();
    },2000);
    var setpoint = function () {
        // console.log("ok")
        for (var i =0;i<points.length;i++){
            var obj = points[i];
            // console.log(obj)
            obj.classList.remove("now");
        }
        points[index-1].classList.add('now');
    }
};

var downTiem = function () {

};
