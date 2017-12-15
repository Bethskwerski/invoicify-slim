$(function () {

    $('#create-flat-fee-form').submit(function (e) {
        e.preventDefault();

        let flatFee = {
            amount: $('#flat-rate-amount').val(),
            description: $('#flat-rate-description').val(),
            client: { id: $('#flat-fee-clientId').val() }
        };

        let headers = {
            'X-CSRF-TOKEN': $('#flat-fee-csrf').val()
        };
        let settings = {
            url: '/api/flatfees',
            headers: headers,
            data: JSON.stringify(flatFee),
            contentType: 'application/json'
        };

        $.post(settings)
            .done(function (data) {
                $('#billing-table')
                    .append(`
                        <tr>
                            <td>${data.createdBy.username}</td>
                            <td>${data.description}</td>
                            <td>${data.client.name}</td>
                            <td>$${data.amount}</td>
                            <td>$ -- </td>
                            <td> -- </td>
                            <td>$${data.total}</td>
                        </tr>
                `);
                $('#flat-rate-description').val('');
                $('#flat-rate-amount').val('');
                $('#flat-fee-clientId').val('');
            });
    });

    $('#create-rate-based-form').submit(function (e) {
        e.preventDefault();

        let rateBased = {
            rate: $('#rate-amount').val(),
            description: $('#rate-description').val(),
            client: {
                id: $('#rate-based-clientId').val()
            },
            quantity: $('#rate-based-quantity').val(),

        };
        let headers = {
            'X-CSRF-TOKEN': $('#rate-based-csrf').val()
        };
        let settings = {
            url: '/api/ratefees',
            headers: headers,
            data: JSON.stringify(rateBased),
            contentType: 'application/json'
        };
        $.post(settings)
            .done(function (data) {
                $('#billing-table')
                    .append(`
                        <tr>
                            <td>${data.createdBy.username}</td>
                            <td>${data.description}</td>
                            <td>${data.client.name}</td>
                            <td> -- </td>
                            <td>$${data.rate}</td>
                            <td>${data.quantity}</td>
                            <td>$${data.total}</td>
                        </tr>
                `);
                
                $('#rate-amount').val('');
                $('#rate-description').val('');
                $('#rate-based-clientId').val('');
                $('#rate-based-quantity').val('');
                
            });
    });
});