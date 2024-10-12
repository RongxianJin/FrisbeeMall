const API_BASE_URL = 'http://localhost:8080';

// 加载当前商品
async function loadCurrentProduct() {
    try {
        const response = await axios.get(`${API_BASE_URL}/product/history`);
        const products = response.data.data;
        const currentProduct = products.find(p => p.status === 0);
        
        const productDetails = document.getElementById('productDetails');
        if (currentProduct) {
            productDetails.innerHTML = `
                <h3>${currentProduct.name}</h3>
                <img src="${currentProduct.imageUrl}" alt="${currentProduct.name}">
                <p>${currentProduct.description}</p>
                <p>价格: ${currentProduct.price}</p>
                <button onclick="showBuyerForm(${currentProduct.id})">我要购买</button>
            `;
        } else {
            productDetails.innerHTML = '<p>当前没有可购买的商品</p>';
        }
    } catch (error) {
        console.error('加载当前商品失败:', error);
    }
}

// 显示买家表单
function showBuyerForm(productId) {
    document.getElementById('buyerForm').style.display = 'block';
    document.getElementById('submitForm').onsubmit = (e) => submitBuyerInfo(e, productId);
}

// 提交买家信息
async function submitBuyerInfo(e, productId) {
    e.preventDefault();
    const buyerInfo = {
        name: document.getElementById('buyerName').value,
        phone: document.getElementById('buyerPhone').value,
        status: 0,
        productId: productId
    };

    try {
        await axios.post(`${API_BASE_URL}/user/submit`, buyerInfo);
        alert('购买信息已提交，请等待卖家联系');
        document.getElementById('buyerForm').style.display = 'none';
        loadCurrentProduct();
    } catch (error) {
        console.error('提交购买信息失败:', error);
        alert('提交购买信息失败');
    }
}

// 页面加载时执行
window.onload = loadCurrentProduct;
