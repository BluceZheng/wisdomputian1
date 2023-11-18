function showLocation(form,province, city, town) {

    var loc = new Location();

    var title = ['省份', '地级市', '市、县、区'];

    $.each(title, function (k, v) {
        title[k] = '<option value="">' + v + '</option>';
    })

    $('#loc_province').append(title[0]);
    $('#loc_city').append(title[1]);
    $('#loc_town').append(title[2]);

    //省监听
    form.on('select(province)', function(data){
        $('#loc_city').empty();
        $('#loc_city').append(title[1]);
        loc.fillOption('loc_city', '0,' + data.value);
        $('#loc_city').change();
        form.render('select');
    });

    //市监听
    form.on('select(city)', function(data){
        $('#loc_town').empty();
        $('#loc_town').append(title[2]);
        loc.fillOption('loc_town', '0,' + $('#loc_province').val() + ',' + data.value);
        form.render('select');
    });

    //区监听
    form.on('select(area)', function(data){
        console.log(data.value); //得到被选中的值
       // form.render('select');
    });


    if (province) {
        loc.fillOption('loc_province', '0', province);
        if (city) {
            loc.fillOption('loc_city', '0,' + province, city);

            if (town) {
                loc.fillOption('loc_town', '0,' + province + ',' + city, town);
            }
        }
    } else {
        loc.fillOption('loc_province', '0');
    }

}

function  loadLocation(form,province, city, area) {
    var loc = new Location();
    loc.fillAndSelectOption(form,province, city, area);


}