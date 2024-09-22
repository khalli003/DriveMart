document.getElementById('orderForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const category = document.getElementById('category').value;
    const amount = document.getElementById('amount').value;
    const companyId = document.getElementById('companyId').value;

    fetch('/orders', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            category: category,
            amount: amount,
            companyId: companyId
        })
    })
        .then(response => response.json())
        .then(data => {
            document.getElementById('response').innerText = `Заказ создан с ID: ${data.id}`;
            // Очистка формы
            document.getElementById('orderForm').reset();
        })
        .catch(error => {
            console.error('Ошибка:', error);
            document.getElementById('response').innerText = 'Ошибка при создании заказа';
        });
});

document.getElementById('routeForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const orderId = document.getElementById('orderId').value;
    const points = document.getElementById('points').value.split(',');

    fetch('/routes', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            orderId: orderId,
            points: points
        })
    })
        .then(response => response.json())
        .then(data => {
            document.getElementById('response').innerText = `Маршрут создан с ID: ${data.id}`;
            // Очистка формы
            document.getElementById('routeForm').reset();
        })
        .catch(error => {
            console.error('Ошибка:', error);
            document.getElementById('response').innerText = 'Ошибка при создании маршрута';
        });
});
