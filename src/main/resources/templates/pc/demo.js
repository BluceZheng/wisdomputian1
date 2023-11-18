for (var i = 0; i < checkbox.length; i++) {
    var idx = checkbox[i].value;
    /** 主表物料类型赋值为子表物料类型的集合 **/
    var objValue = document.getElementById('field_8a8acea860905fb70160a67cbed70660_' + idx).value;
    if (objValue == '8a8ad0a03a691636013a692b3c020059') {
        isbcp = true;
    }
    if (objValue == '8a8ad0a03a691636013a692ac1630051') {
        iscp = true;
    }
    if (typeArray.indexOf(objValue) == -1) {
        typeArray.push(objValue);
    }
    var IV_MATNR = document.getElementById('field_8a8acea860905fb70160a67cbe4d065c_' + idx).value;
    //物料类型
    var wllx = document.getElementById('field_8a8acea860905fb70160a67cbed70660_' + idx).value;
    var IV_WERKS = document.getElementById('field_8a8acea860c05a0e0160c13ee99c0001').value;
    //郑界华  按单物料
    var isbyorder = false;
    var colorno;
    if (IV_MATNR.substring(0, 3) == '598' && IV_MATNR.split('_').length > 1) {
        var IV_MATNRs = IV_MATNR;
        IV_MATNR = IV_MATNRs.split('_')[0];
        colorno = IV_MATNRs.split('_')[1];
        isbyorder = true;
    }//郑界华  按单物料   --结束
    var IV_WERKS;//工厂
    //半成品  原材料  的时候 修改 生产工厂 的值  --郑界华
    changeProductVal();
    //获取不同工厂  郑界华  --开始--
    var postcompany = document.getElementById('field_8a8acea860c05a0e0160c13ee99c0001').value;//过账工厂
    var productioncompany = document.getElementById('field_8a8acee277d3121e0177d36daf0c145c').value;//生产工厂
    //判断是否是公司间的业务 是-8a8acee26d7bc114016d7fac00cd083a
    if ('8a8acee26d7bc114016d7fac00cd083a' == intercompany) {//是公司间业务
        IV_WERKS = productioncompany;//取值工厂值为生成工厂
    } else {
        IV_WERKS = postcompany;//取值工厂值为过账工厂
    }





    /** 重新获取价格防止价格有变动 **/
    if (IV_MATNR && IV_WERKS) {
        DWREngine.setAsync(false);
        SAPQuerySyncService.getSapReturnParam('ZOA_MATNR_PRICE', 'IV_MATNR,IV_WERKS', IV_MATNR + ',' + IV_WERKS, {
            callback: function (data) {
                debugger;
                if (data) {
                    var EV_SUBRC = data.EV_SUBRC == null ? '' : data.EV_SUBRC;
                    var EV_VPRSV = data.EV_VPRSV == null ? '' : data.EV_VPRSV;
                    var EV_PRICE = 0;
                    var EV_KHCDPRICE=0;
                    if (data.EV_PRICE) {
                        EV_PRICE = data.EV_PRICE;
                    }
                    document.getElementById('field_8a8acea8615fdd0101616010c96000eb_' + idx).value = EV_VPRSV;
                    var nums = document.getElementById('field_8a8acea860905fb70160a67cbf500664_' + idx).value;
                    //郑界华  按单物料
                    if (isbyorder) {
                        DWREngine.setAsync(false);
                        //获取基漆价额配置表信息   判断工厂  物料编号  状态为有效的信息可以被获取
                        var parintsql = 'select   materialprice  from uf_basepaintprice  where  materialno = \'' + IV_MATNR + '\' and compnany = \'' + IV_WERKS + '\' and materialstatus = \'8a8acee278ac67230178ac81369f0d9d\'';
                        DataService.getValues(parintsql, {
                            callback: function (pdata) {
                                if (pdata && pdata.length > 0) {
                                    EV_PRICE = pdata[0].materialprice;
                                    DWREngine.setAsync(false);
                                    var sql = 'select ubi.* from uf_bom_info ubi where ubi.bomid = \'' + IV_MATNRs + '\'';//查找物料信息  8a8ad0a039e82acc0139fc4d34a61172---水性   8a8ad0a039e82acc0139fc4d34a61173--油性  bgstype
                                    DataService.getValues(sql, {
                                        callback: function (data) {
                                            if (data && data.length > 0) {
                                                var retndata = data[0];
                                                var bgstype = retndata.bgstype;//产品特性
                                                if (bgstype == '' || bgstype == null) {
                                                    alert(IV_MATNRs+'产品特性,为空不能获取到物料价格');
                                                    return false;
                                                }
                                                DWREngine.setAsync(false);
                                                var jz = retndata.nw;//净重
                                                var sql2 = 'select ubc.* from uf_bom_colorinfo ubc where ubc.colorno = \'' + colorno + '\'';//获取色号价格
                                                DataService.getValues(sql2, {
                                                    callback: function (data2) {
                                                        if (data2 && data2.length > 0) {
                                                            var retndata2 = data2[0];
                                                            var sumPrice = 0;
                                                            var sumkhcdPrice = 0;
                                                            var mzsumkhcdPrice=0;
                                                            if (bgstype == '8a8ad0a039e82acc0139fc4d34a61172') {//水性
                                                                //判断控制当获取的水性色号价格为空  或者 没有填 默认为0  add by 郑界华  20220-09-26
                                                                var wprice = retndata2.wprice;
                                                                if(wprice == null || wprice =='null' || wprice == undefined || wprice == 'undefined' ||wprice == '' ){
                                                                    wprice = 0;
                                                                }
                                                                //单价
                                                                console.log('EV_PRICE'+EV_PRICE)
                                                                console.log('retndata2.wprice'+retndata2.wprice)
                                                                //查询是否美妆配置，是的话取配置价格，否的话按原逻辑取
                                                                mzsumkhcdPrice=setMzprice(IV_MATNRs,idx);
                                                                if(mzsumkhcdPrice!=0){
                                                                    sumPrice=mzsumkhcdPrice;
                                                                }else{
                                                                    sumPrice = (parseFloat(EV_PRICE) + parseFloat(wprice) * jz).toFixed(3);
                                                                }
                                                                //sumPrice = (parseFloat(EV_PRICE) + parseFloat(wprice) * jz).toFixed(3);
                                                                console.log('251'+sumPrice)


                                                                document.getElementById('field_8a8acea860905fb70160a67f90a8066a_' + idx).value = sumPrice;
                                                                document.getElementById('field_8a8acea860905fb70160a67f90a8066a_' + idx + 'span').innerHTML = sumPrice;
                                                                //客户不承担设置默认单价，承担取基价
                                                                if(sfkhcd=='8a8ace588601abb501860c3191664123'){//是
                                                                    sumkhcdPrice=setKhcdprice(IV_MATNR,idx);
                                                                }else if(sfkhcd=='8a8ace588601abb501860c3191664124'){//否
                                                                    document.getElementById('field_8a8ace588601abb501860c3a31444420_' + idx).value = sumPrice;
                                                                    document.getElementById('field_8a8ace588601abb501860c3a31444420_' + idx + 'span').innerHTML = sumPrice;
                                                                }else{
                                                                    document.getElementById('field_8a8ace588601abb501860c3a31444420_' + idx).value = '';
                                                                    document.getElementById('field_8a8ace588601abb501860c3a31444420_' + idx + 'span').innerHTML = '';
                                                                }
                                                            } else if (bgstype == '8a8ad0a039e82acc0139fc4d34a61173') {//油性
                                                                //判断控制当获取的油性色号价格为空  或者 没有填 默认为0   add by 郑界华  20220-09-26
                                                                var oprice = retndata2.oprice;
                                                                if(oprice == null || oprice =='null' || oprice == undefined || oprice == 'undefined' || oprice == ''){
                                                                    oprice = 0;
                                                                }
                                                                //单价
                                                                if(mzsumkhcdPrice!=0){
                                                                    sumPrice=mzsumkhcdPrice;
                                                                }else{
                                                                    sumPrice = (parseFloat(EV_PRICE) + parseFloat(oprice) * jz).toFixed(3);
                                                                }
                                                                //sumPrice = (parseFloat(EV_PRICE) + parseFloat(oprice) * jz).toFixed(3);
                                                                console.log('259'+sumPrice)
                                                                document.getElementById('field_8a8acea860905fb70160a67f90a8066a_' + idx).value = sumPrice;
                                                                document.getElementById('field_8a8acea860905fb70160a67f90a8066a_' + idx + 'span').innerHTML = sumPrice;
                                                                //客户不承担设置默认单价，承担取基价
                                                                if(sfkhcd=='8a8ace588601abb501860c3191664123'){//是
                                                                    sumkhcdPrice=setKhcdprice(IV_MATNR,idx);
                                                                }else if(sfkhcd=='8a8ace588601abb501860c3191664124'){//否
                                                                    document.getElementById('field_8a8ace588601abb501860c3a31444420_' + idx).value = sumPrice;
                                                                    document.getElementById('field_8a8ace588601abb501860c3a31444420_' + idx + 'span').innerHTML = sumPrice;
                                                                }else{
                                                                    document.getElementById('field_8a8ace588601abb501860c3a31444420_' + idx).value = '';
                                                                    document.getElementById('field_8a8ace588601abb501860c3a31444420_' + idx + 'span').innerHTML = '';
                                                                }
                                                            }
                                                            //判断是否是美妆且是公司间，加价3倍
                                                            var iscompany = $('#field_8a8acee271c12e700171c465a0816706').val();
                                                            var objname = $('#field_8a8acee277d3121e0177d36daece145a').val();
                                                            if(iscompany=='8a8acee26d7bc114016d7fac00cd083a'&&objname=='7200'){
                                                                if(mzsumkhcdPrice!=0){
                                                                    sumPrice=mzsumkhcdPrice;
                                                                }else{
                                                                    sumPrice=(sumPrice*3).toFixed(2);
                                                                }
                                                                //sumPrice=(sumPrice*3).toFixed(2);
                                                                console.log('268'+sumPrice)
                                                                document.getElementById('field_8a8acea860905fb70160a67f90a8066a_' + idx).value=sumPrice
                                                                document.getElementById('field_8a8acea860905fb70160a67f90a8066a_' + idx+'span').innerHTML=sumPrice
                                                                //客户不承担设置默认单价，承担取基价
                                                                if(sfkhcd=='8a8ace588601abb501860c3191664123'){//是
                                                                    sumkhcdPrice=setKhcdprice(IV_MATNR,idx);
                                                                }else if(sfkhcd=='8a8ace588601abb501860c3191664124'){//否
                                                                    document.getElementById('field_8a8ace588601abb501860c3a31444420_' + idx).value = sumPrice;
                                                                    document.getElementById('field_8a8ace588601abb501860c3a31444420_' + idx + 'span').innerHTML = sumPrice;
                                                                }else{
                                                                    document.getElementById('field_8a8ace588601abb501860c3a31444420_' + idx).value = '';
                                                                    document.getElementById('field_8a8ace588601abb501860c3a31444420_' + idx + 'span').innerHTML = '';
                                                                }
                                                            }
                                                            document.getElementById('field_8a8acea860905fb70160a67f90f1066c_' + idx).value = sumPrice * nums * 1.00;
                                                            document.getElementById('field_8a8acea860905fb70160a67f90f1066c_' + idx + 'span').innerHTML = sumPrice * nums * 1.00;
                                                            //客户不承担设置默认单价，承担取基价
                                                            if(sfkhcd=='8a8ace588601abb501860c3191664123'){//是
                                                                sumkhcdPrice=setKhcdprice(IV_MATNR,idx);
                                                            }else if(sfkhcd=='8a8ace588601abb501860c3191664124'){//否
                                                                document.getElementById('field_8a8ace588601abb501860c3a31634422_' + idx).value = sumPrice * nums * 1.00;
                                                                document.getElementById('field_8a8ace588601abb501860c3a31634422_' + idx + 'span').innerHTML = sumPrice * nums * 1.00;
                                                            }else{
                                                                document.getElementById('field_8a8ace588601abb501860c3a31634422_' + idx).value = '';
                                                                document.getElementById('field_8a8ace588601abb501860c3a31634422_' + idx + 'span').innerHTML = '';
                                                            }

                                                            //计算总金额
                                                            totalMoney += sumPrice * nums * 1.00
                                                            totalkhcdMoney+= sumkhcdPrice * nums * 1.00
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    });
                                } else {
                                    alert('按单物料基漆价格还没有维护，请点“常见问题”联系对应工厂财务维护价格后才可以提交');
                                }
                            }
                        });
                    } else {
                        //单价
                        console.log('289'+EV_PRICE)
                        //查询是否美妆配置，是的话取配置价格，否的话按原逻辑取
                        var mzsumkhcdPrice=setMzprice(IV_MATNR,idx);
                        if(mzsumkhcdPrice!=0){
                            EV_PRICE=mzsumkhcdPrice;
                        }
                        document.getElementById('field_8a8acea860905fb70160a67f90a8066a_' + idx).value = EV_PRICE;
                        document.getElementById('field_8a8acea860905fb70160a67f90a8066a_' + idx + 'span').innerHTML = EV_PRICE;
                        //客户不承担设置默认单价，承担取基价
                        var sumkhcdPrices=0;
                        if(sfkhcd=='8a8ace588601abb501860c3191664123'){//是
                            sumkhcdPrices=setKhcdprice(IV_MATNR,idx);
                        }else if(sfkhcd=='8a8ace588601abb501860c3191664124'){//否
                            document.getElementById('field_8a8ace588601abb501860c3a31444420_' + idx).value = EV_PRICE;
                            document.getElementById('field_8a8ace588601abb501860c3a31444420_' + idx + 'span').innerHTML = EV_PRICE;
                        }else{
                            document.getElementById('field_8a8ace588601abb501860c3a31444420_' + idx).value = '';
                            document.getElementById('field_8a8ace588601abb501860c3a31444420_' + idx + 'span').innerHTML = '';
                        }
                        //判断是否是美妆且是公司间，加价3倍
                        var iscompany = $('#field_8a8acee271c12e700171c465a0816706').val();
                        var objname = $('#field_8a8acee277d3121e0177d36daece145a').val();
                        if(iscompany=='8a8acee26d7bc114016d7fac00cd083a'&&objname=='7200'){
                            if(mzsumkhcdPrice!=0){
                                EV_PRICE=mzsumkhcdPrice;
                            }else{
                                EV_PRICE = (EV_PRICE*3).toFixed(2);
                            }

                            console.log('29'+EV_PRICE)
                            document.getElementById('field_8a8acea860905fb70160a67f90a8066a_' + idx).value = EV_PRICE;
                            document.getElementById('field_8a8acea860905fb70160a67f90a8066a_' + idx + 'span').innerHTML = EV_PRICE;
                            //客户不承担设置默认单价，承担取基价
                            if(sfkhcd=='8a8ace588601abb501860c3191664123'){//是
                                sumkhcdPrices=setKhcdprice(IV_MATNR,idx);
                            }else if(sfkhcd=='8a8ace588601abb501860c3191664124'){//否
                                document.getElementById('field_8a8ace588601abb501860c3a31444420_' + idx).value = EV_PRICE;
                                document.getElementById('field_8a8ace588601abb501860c3a31444420_' + idx + 'span').innerHTML = EV_PRICE;
                            }else{
                                document.getElementById('field_8a8ace588601abb501860c3a31444420_' + idx).value = '';
                                document.getElementById('field_8a8ace588601abb501860c3a31444420_' + idx + 'span').innerHTML = '';
                            }
                        }
                        document.getElementById('field_8a8acea860905fb70160a67f90f1066c_' + idx).value = EV_PRICE * nums * 1.00;
                        document.getElementById('field_8a8acea860905fb70160a67f90f1066c_' + idx + 'span').innerHTML = EV_PRICE * nums * 1.00;
                        //客户不承担设置默认单价，承担取基价
                        if(sfkhcd=='8a8ace588601abb501860c3191664123'){//是
                            sumkhcdPrices=setKhcdprice(IV_MATNR,idx);
                        }else if(sfkhcd=='8a8ace588601abb501860c3191664124'){//否
                            document.getElementById('field_8a8ace588601abb501860c3a31444420_' + idx).value = EV_PRICE * nums * 1.00;
                            document.getElementById('field_8a8ace588601abb501860c3a31444420_' + idx + 'span').innerHTML = EV_PRICE * nums * 1.00;
                        }else{
                            document.getElementById('field_8a8ace588601abb501860c3a31634422_' + idx).value = '';
                            document.getElementById('field_8a8ace588601abb501860c3a31634422_' + idx + 'span').innerHTML = '';
                        }
                        //计算总金额
                        totalMoney += EV_PRICE * nums * 1.00
                        totalkhcdMoney+= sumkhcdPrices * nums * 1.00

                    }//郑界华  按单物料   --结束
                    document.getElementById('field_8a8acea8615fdd010161d7e8bea80c8f_' + idx).value = EV_VPRSV;
                    var pid = document.getElementById('field_8a8ad0de6219b9240162220bf0d5455c_' + idx).value;
                    var mno = document.getElementById('field_8a8acea860905fb70160a67cbe4d065c_' + idx + 'span').innerText;

                    // totalMoney += EV_PRICE * nums * 1.00;

                    //return true;//郑界华测试数据
                    if (issave == 0 && EV_PRICE == '0') {
                        //    if ( EV_PRICE == '0') {
                        if (gsjyw != '8a8acee26d7bc114016d7fac00cd083a') {
                            alert('第' + pid + '行（物料号：' + mno + ',工厂：' + gcname + '）单价不能为0，请点击页面左上角“常见问题“中问题二查询对应处理方法。');
                            flag = false;
                        }
                    }
                    if (!flag) {
                        return flag;
                    }

                    /** 所属区域**/
                    var areaDept = document.getElementById('field_8a8acea860905fb70160a659096b0633').value;
                    var areaArray = ['8a8ad02570d72eac0170dc01ea186718', '8a8ad02570d72eac0170dc0298b5672a', '8a8ad02570d72eac0170dc02de3f6730', '8a8ad02570d72eac0170dc03204469c6', '8a8ad045540371680154582f46cc1515', '8a8ad0b367f31a520168eedd302612b2', '8a8ad0b367f31a520168eedd891e12b8', '8a8ad0c370e53a3d0170e7b5f0a339b5', '8a8ad0c36f369aed016f41900635671f'];

                    /**莆田工厂、天津工厂、河南工厂、四川工厂 判断EV_VPRSV=S时默认947**/
                        // if (EV_VPRSV.toUpperCase() == 'S' && reqDeptArray.indexOf(reqDept) > -1 && areaArray.indexOf(areaDept) <= -1) {
                        //成本中心类型
                    var costtypeval = $('#field_8a8acee27c7e9f25017c82c1d6597f02').val();
                    /**莆田工厂、天津工厂、河南工厂、四川工厂 判断成本中心类型 ：F/K 时默认947**/
                    if(costtypeval == 'F' || costtypeval == 'K'){
                        /*document.getElementById('field_8a8acea860905fb70160a6635f00063d').value = '辅助物料领用';
                        document.getElementById('field_8a8acea860905fb70160a6635f00063dspan').innerHTML = '辅助物料领用';
                        //李静 20180716
                        document.getElementById('field_8a8acea86493c8dd0164a0cdf4140a89').value = SQL('select requestid from uf_budgetmemu a where a.memuname = \'辅助物料领用\'  and a.memucode like \'A%\' ');
                        document.getElementById('field_8a8acea86493c8dd0164a0cdf4140a89span').innerHTML = '辅助物料领用';   */

                        /** 申请用途 **/
                        /* var useFor = document.getElementById('field_8a8acea860905fb70160a66b07590649').value;
                         document.getElementById('field_8a8ad0f862a439470162a5c54cd56ce7').value = '947';
                         var sql = 'select a.subject from uf_sapsubject a where a.reqtype=\''+useFor+'\'  and a.iscanuse =\'8a8acee278ac67230178ac81369f0d9d\' and thetype=\'947\'';
                         objname = SQL(sql);   */
                        /** 申请用途 **/
                        var useFor = document.getElementById('field_8a8acea860905fb70160a66b07590649').value;
                        //是否驻外人员
                        var zwry = document.getElementById('field_8a8acea860905fb70160a6590a020637').value;
                        /** 申请用途 **/
                        var useFor = document.getElementById('field_8a8acea860905fb70160a66b07590649').value;
                        if (useFor == '8a8acee27b3d735b017b3d8305fc0575') {//部门团建活动物料领用
                            if (zwry == '8a8ad02047ba48ad0147c2fa46d11af9') {//是驻外人员  会议费-会议物料领用
                                document.getElementById('field_8a8ad0f862a439470162a5c54cd56ce7').value = '947';  //设置移动类型
                                objname = '会议费-会议物料领用';
                            } else if (zwry == '8a8ad02047ba48ad0147c2fa46d11af8') {//总部/分厂人员  福利费-团建福利
                                document.getElementById('field_8a8ad0f862a439470162a5c54cd56ce7').value = '947';  //设置移动类型
                                objname = '福利费-团建福利';
                            }
                        } else {
                            document.getElementById('field_8a8ad0f862a439470162a5c54cd56ce7').value = '947';
                            var sql = 'select a.subject from uf_sapsubject a where a.reqtype=\'' + useFor + '\'  and a.iscanuse =\'8a8acee278ac67230178ac81369f0d9d\' and thetype=\'947\'';
                            objname = SQL(sql);
                        }


                        document.getElementById('field_8a8acea860905fb70160a6635f00063d').value = objname;
                        document.getElementById('field_8a8acea860905fb70160a6635f00063dspan').innerHTML = objname;
                        document.getElementById('field_8a8acea86493c8dd0164a0cdf4140a89').value = SQL('select requestid from uf_budgetmemu a where a.memuname = \'' + objname + '\'  and a.memucode like \'A%\' ');
                        document.getElementById('field_8a8acea86493c8dd0164a0cdf4140a89span').innerHTML = objname;
                    }
                    /**莆田工厂、天津工厂、河南工厂、四川工厂 价格类型必须一致**/
                    /*if (reqDeptArray.indexOf(reqDept) > -1 && EV_VPRSV_tmp && EV_VPRSV) {
                        if (EV_VPRSV_tmp != EV_VPRSV.toUpperCase()) {
                            //alert('价格类型不一致');
                            //  flag = false;
                        }
                    }*/
                    if(EV_VPRSV!=undefined){
                        EV_VPRSV_tmp = EV_VPRSV.toUpperCase();
                    }

                }
            }
        });

    }






    var newwerk='';
    if ('8a8acee26d7bc114016d7fac00cd083a' == intercompany) {//公司间价格取值逻辑调整物料类型=原材料/半成品若4201、6101工厂无价格，增加获取4200、6100工厂价格。
        if(document.getElementById('field_8a8acea860905fb70160a67f90a8066a_' + idx).value=='0'){
            if(wllx=='8a8ad0a03a691636013a692b3c020059'||wllx=='8a8ad0a03a691636013a692bbcda0061'){
                if(IV_WERKS=='4200'||IV_WERKS=='4201'||IV_WERKS=='6101'||IV_WERKS=='6100'){
                    if(IV_WERKS=='4200'){
                        newwerk='4201';
                    }else if(IV_WERKS=='4201'){
                        newwerk='4200';
                    }else if(IV_WERKS=='6101'){
                        newwerk='6100';
                    }else{
                        newwerk='6101';
                    }
                    setSubFields(idx,newwerk);//重新查询价格
                }
            }
        }
    }
}