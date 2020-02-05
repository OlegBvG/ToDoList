$(function(){

    const appendDeal = function(data){
        var dealCode = '<a href="#" class="deal-link" data-id="' +
            data.id + '">' + data.name + '</a><br>';
        $('#deal-list')
            .append('<div>' + dealCode + '</div>');
    };

    //Loading deals on load page
//    $.get('/deals/', function(response)
//    {
//        for(i in response) {
//            appendDeal(response[i]);
//        }
//    });

    //Show adding deal form
    $('#show-add-deal-form').click(function(){
        $('#deal-form').css('display', 'flex');
    });

    //Closing adding deal form
    $('#deal-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });


    //Getting deal
    $(document).on('click', '.deal-link', function(){
        var link = $(this);
        var dealId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/deals/' + dealId,
            success: function(response)
            {
                var code = '<span>Плановая дата:' + response.date + '</span>';
                link.parent().append(code);
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Дело не найдено!');
                }
            }
        });
        return false;
    });

    //Adding deal
    $('#save-deal').click(function()
    {
        var data = $('#deal-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/deals/',
            data: data,
            success: function(response)
            {
                $('#deal-form').css('display', 'none');
                var deal = {};
                deal.id = response;
                var dataArray = $('#deal-form form').serializeArray();
                for(i in dataArray) {
                    deal[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendDeal(deal);
            }
        });
        return false;
    });

});