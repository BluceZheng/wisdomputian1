var code; //在全局 定义验证码
        function createCode() { //创建验证码函数
            code = "";
            var codeLength = 4;//验证码的长度
            var selectChar = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B',
                    'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                    'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');//所有候选组成验证码的字符，当然也可以用中文的
            for (var i = 0; i < codeLength; i++) {
                var charIndex = Math.floor(Math.random() * 36);
                code += selectChar[charIndex];
            }
            // 设置验证码的显示样式，并显示
            document.getElementById("discode").style.fontFamily = "黑体"; //设置字体
            document.getElementById("discode").style.letterSpacing = "10px"; //字体间距
            document.getElementById("discode").style.color = "red"; //字体颜色
            document.getElementById("discode").innerHTML = code; // 显示
        }
        function but() {//验证验证码输入是否正确
        	localStorage.clear();
            var val1 = document.getElementById("t1").value;
            var val2 = code;
            val1=val1.toLowerCase();
            val2=val2.toLowerCase();
            if (val1 != val2) {
        	    alert("验证码错误");
        	    createCode();
                document.getElementById('t1').value = "";
            }else{up()}
        }