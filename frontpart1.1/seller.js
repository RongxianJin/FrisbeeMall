const API_BASE_URL = 'http://localhost:8080';
let quill;

// 初始化页面
async function initPage() {
    setupQuillEditor();
    await loadCategories();
    loadProducts();
    loadCustomers();
    setupEventListeners();
}

// 设置Quill编辑器
function setupQuillEditor() {
    quill = new Quill('#editor', {
        theme: 'snow'
    });
}

// 加载类别
async function loadCategories() {
    try {
        const response = await axios.get(`${API_BASE_URL}/category/all`);
        const categories = response.data.data;
        const categorySelect = document.getElementById('categorySelect');
        const parentCategorySelect = document.getElementById('parentCategorySelect');
        categorySelect.innerHTML = '';
        parentCategorySelect.innerHTML = '<option value="">无父类别</option>';
        categories.forEach(category => {
            if (!category.parentId) {
                categorySelect.innerHTML += `<option value="${category.id}">${category.name}</option>`;
                parentCategorySelect.innerHTML += `<option value="${category.id}">${category.name}</option>`;
            }
        });
    } catch (error) {
        console.error('加载类别失败:', error);
    }
}

// 加载商品
async function loadProducts() {
    try {
        const response = await axios.get(`${API_BASE_URL}/product/all`);
        const products = response.data.data;
        const productList = document.getElementById('productList');
        productList.innerHTML = '';
        products.forEach(product => {
            const productElement = document.createElement('div');
            productElement.className = 'product-card';
            productElement.innerHTML = `
                <h3>${product.name}</h3>
                <p>类别: ${product.categoryName}</p>
                <p>价格: ${product.price}</p>
                <p>库存: ${product.stock}</p>
                <p>状态: ${getStatusText(product.status)}</p>
                <button onclick="editProduct(${product.id})">编辑</button>
                <button onclick="deleteProduct(${product.id})">删除</button>
            `;
            productList.appendChild(productElement);
        });
    } catch (error) {
        console.error('加载商品失败:', error);
    }
}

// 加载客户
async function loadCustomers() {
    try {
        const response = await axios.get(`${API_BASE_URL}/user/all`);
        const customers = response.data.data;
        const customerList = document.getElementById('customerList');
        customerList.innerHTML = '';
        customers.forEach(customer => {
            const customerElement = document.createElement('div');
            customerElement.innerHTML = `
                <h3>${customer.username}</h3>
                <p>电话: ${customer.phone}</p>
                <p>地址: ${customer.location}</p>
                <button onclick="viewCustomerOrders(${customer.id})">查看订单</button>
            `;
            customerList.appendChild(customerElement);
        });
    } catch (error) {
        console.error('加载客户失败:', error);
    }
}

// 设置事件监听器
function setupEventListeners() {
    document.getElementById('productForm').addEventListener('submit', addProduct);
    document.getElementById('categoryForm').addEventListener('submit', addCategory);
    document.getElementById('passwordForm').addEventListener('submit', updatePassword);
}

// 添加商品
async function addProduct(e) {
    e.preventDefault();
    const formData = new FormData(e.target);
    formData.set('description', quill.root.innerHTML);
    try {
        await axios.post(`${API_BASE_URL}/product/add`, formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        });
        alert('商品添加成功');
        loadProducts();
    } catch (error) {
        console.error('添加商品失败:', error);
        alert('添加商品失败');
    }
}

// 添加类别
async function addCategory(e) {
    e.preventDefault();
    const name = document.getElementById('categoryName').value;
    const parentId = document.getElementById('parentCategorySelect').value;
    try {
        await axios.post(`${API_BASE_URL}/category/add`, { name, parentId });
        alert('类别添加成功');
        loadCategories();
    } catch (error) {
        console.error('添加类别失败:', error);
        alert('添加类别失败');
    }
}

// 更新密码
async function updatePassword(e) {
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
}

// 编辑商品
function editProduct(id) {
    // 实现编辑商品的逻辑
}

// 删除商品
async function deleteProduct(id) {
    if (confirm('确定要删除这个商品吗？')) {
        try {
            await axios.delete(`${API_BASE_URL}/product/delete/${id}`);
            alert('商品删除成功');
            loadProducts();
        } catch (error) {
            console.error('删除商品失败:', error);
            alert('删除商品失败');
        }
    }
}

// 查看客户订单
async function viewCustomerOrders(userId) {
    try {
        const response = await axios.get(`${API_BASE_URL}/order/user/${userId}`);
        const orders = response.data.data;
        // 显示订单信息的逻辑
    } catch (error) {
        console.error('加载客户订单失败:', error);
        alert('加载客户订单失败');
    }
}

// 获取状态文本
function getStatusText(status) {
    switch (status) {
        case 0: return '正常';
        case 1: return '已下架';
        default: return '未知';
    }
}

// 页面加载时初始化
window.onload = initPage;