const apiUrl = "http://localhost:8080/api";  // Spring Boot的API地址

// 通过API获取商品
function fetchProduct() {
    fetch(`${apiUrl}/product`)
        .then(response => response.json())
        .then(data => {
            product = data;
            loadProduct();
        })
        .catch(error => console.error('Error fetching product:', error));
}

// 更新商品信息
function updateProduct(newProduct) {
    fetch(`${apiUrl}/product`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newProduct)
    })
    .then(response => response.json())
    .then(data => {
        product = data;
        loadProduct();
    })
    .catch(error => console.error('Error updating product:', error));
}

// 冻结商品
function freezeProduct() {
    fetch(`${apiUrl}/product/freeze`, {
        method: 'POST'
    })
    .then(() => {
        product.frozen = true;
        loadProduct();
    })
    .catch(error => console.error('Error freezing product:', error));
}

// 解冻商品
function unfreezeProduct() {
    fetch(`${apiUrl}/product/unfreeze`, {
        method: 'POST'
    })
    .then(() => {
        product.frozen = false;
        loadProduct();
    })
    .catch(error => console.error('Error unfreezing product:', error));
}

// 添加购买者信息
function addBuyer(buyer) {
    fetch(`${apiUrl}/buy`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(buyer)
    })
    .then(response => response.json())
    .then(data => {
        buyers.push(data);
        loadBuyers();
        alert('购买请求已提交，请等待卖家确认');
    })
    .catch(error => console.error('Error adding buyer:', error));
}

// 获取买家信息
function loadBuyers() {
    fetch(`${apiUrl}/buyers`)
        .then(response => response.json())
        .then(data => {
            buyers = data;
            const buyersList = document.getElementById('buyers-list');
            buyersList.innerHTML = '';
            buyers.forEach((buyer, index) => {
                buyersList.innerHTML += `<li>${index + 1}. ${buyer.name} (${buyer.email}, ${buyer.phone})</li>`;
            });
        })
        .catch(error => console.error('Error fetching buyers:', error));
}

// 获取历史商品
function loadHistory() {
    fetch(`${apiUrl}/history`)
        .then(response => response.json())
        .then(data => {
            history = data;
            const historyList = document.getElementById('history-list');
            historyList.innerHTML = '';
            history.forEach((item, index) => {
                historyList.innerHTML += `<li>${index + 1}. ${item.name} - ¥${item.price}</li>`;
            });
        })
        .catch(error => console.error('Error fetching history:', error));
}

// 发布新商品
document.getElementById('product-form').addEventListener('submit', function (e) {
    e.preventDefault();
    const name = document.getElementById('product-name').value;
    const desc = document.getElementById('product-desc').value;
    const price = document.getElementById('product-price').value;
    const image = document.getElementById('product-image').value;

    const newProduct = {
        name,
        desc,
        price,
        image,
        frozen: false,
        available: true
    };

    updateProduct(newProduct);
});

//卖家更改密码
document.getElementById('account-form').addEventListener('submit', function(e) {
    e.preventDefault();
    const newUsername = document.getElementById('new-username').value;
    const newPassword = document.getElementById('new-password').value;

    fetch('http://localhost:8080/api/updateAccount', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username: newUsername, password: newPassword })
    })
    .then(response => {
        if (response.ok) {
            alert('账号密码已成功修改');
        } else {
            alert('修改失败，请重试');
        }
    })
    .catch(error => {
        console.error('Update account error:', error);
    });
});

// 初始化加载商品和买家信息
fetchProduct();
loadBuyers();
loadHistory();
