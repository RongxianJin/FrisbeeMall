const API_BASE_URL = 'http://localhost:8080';

// 添加商品
document.getElementById('productForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const product = {
        name: document.getElementById('productName').value,
        description: document.getElementById('productDescription').value,
        imageUrl: document.getElementById('productImageUrl').value,
        price: document.getElementById('productPrice').value,
        status: 0
    };

    try {
        const response = await axios.post(`${API_BASE_URL}/product`, product);
        alert('商品添加成功');
        loadProductHistory();
    } catch (error) {
        console.error('添加商品失败:', error);
        alert('添加商品失败');
    }
});

// 加载商品历史
async function loadProductHistory() {
    try {
        const response = await axios.get(`${API_BASE_URL}/product/history`);
        const productList = document.getElementById('productList');
        productList.innerHTML = '';
        response.data.data.forEach(product => {
            const productCard = document.createElement('div');
            productCard.className = 'product-card';
            productCard.innerHTML = `
                <img src="${product.imageUrl}" alt="${product.name}">
                <div class="product-info">
                    <h3>${product.name}</h3>
                    <p>价格: ${product.price}</p>
                    <p>状态: ${getStatusText(product.status)}</p>
                    <div class="button-group">
                        <button onclick="freezeProduct(${product.id})">冻结</button>
                        <button onclick="unfreezeProduct(${product.id})">解冻</button>
                        <button onclick="sellProduct(${product.id})">售出</button>
                    </div>
                </div>
            `;
            productList.appendChild(productCard);
        });
    } catch (error) {
        console.error('加载商品历史失败:', error);
    }
}

// 冻结商品
async function freezeProduct(id) {
    try {
        await axios.post(`${API_BASE_URL}/product/freeze/${id}`);
        alert('商品已冻结');
        loadProductHistory();
    } catch (error) {
        console.error('冻结商品失败:', error);
        alert('冻结商品失败');
    }
}

// 解冻商品
async function unfreezeProduct(id) {
    try {
        await axios.post(`${API_BASE_URL}/product/unfreeze/${id}`);
        alert('商品已解冻');
        loadProductHistory();
    } catch (error) {
        console.error('解冻商品失败:', error);
        alert('解冻商品失败');
    }
}

// 售出商品
async function sellProduct(id) {
    try {
        await axios.post(`${API_BASE_URL}/product/sell/${id}`);
        alert('商品已售出');
        loadProductHistory();
    } catch (error) {
        console.error('售出商品失败:', error);
        alert('售出商品失败');
    }
}

// 更新密码
document.getElementById('passwordForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const newPassword = document.getElementById('newPassword').value;
    const sellerId = 1; // 假设卖家ID为1

    try {
        await axios.post(`${API_BASE_URL}/seller/update/${sellerId}/${newPassword}`);
        alert('密码修改成功');
    } catch (error) {
        console.error('密码修改失败:', error);
        alert('密码修改失败');
    }
});

// 加载意向购买人信息
async function loadUserInfo() {
    try {
        const response = await axios.get(`${API_BASE_URL}/user/show`);
        const userList = document.getElementById('userList');
        userList.innerHTML = '';
        response.data.data.forEach(user => {
            const li = document.createElement('li');
            li.innerHTML = `
                <p>姓名: ${user.name}</p>
                <p>电话: ${user.phone}</p>
                <p>状态: ${getUserStatusText(user.status)}</p>
                <button onclick="chooseUser(${user.id})">选择买家</button>
            `;
            userList.appendChild(li);
        });
    } catch (error) {
        console.error('加载意向购买人信息失败:', error);
    }
}

// 选择买家
async function chooseUser(id) {
    try {
        await axios.put(`${API_BASE_URL}/user/choose/${id}`);
        alert('已选择买家');
        loadUserInfo();
    } catch (error) {
        console.error('选择买家失败:', error);
        alert('选择买家失败');
    }
}

// 辅助函数：获取商品状态文本
function getStatusText(status) {
    switch (status) {
        case 0: return '上架';
        case 1: return '冻结';
        case 2: return '已售';
        default: return '未知';
    }
}

// 辅助函数：获取用户状态文本
function getUserStatusText(status) {
    switch (status) {
        case 0: return '待处理';
        case 1: return '已选择';
        default: return '未知';
    }
}

// 页面加载时执行
window.onload = () => {
    loadProductHistory();
    loadUserInfo();
};
