const API_BASE_URL = 'http://localhost:8080';
let currentUser = null;
let currentPage = 1;
const itemsPerPage = 10;

// 初始化页面
async function initPage() {
    await loadCategories();
    await loadProducts();
    setupEventListeners();
}

// 加载类别
async function loadCategories() {
    try {
        const response = await axios.get(`${API_BASE_URL}/category/all`);
        const categories = response.data.data;
        const categoryNav = document.getElementById('categoryNav');
        categoryNav.innerHTML = '<a href="#" data-category-id="all">全部</a>';
        categories.forEach(category => {
            categoryNav.innerHTML += `<a href="#" data-category-id="${category.id}">${category.name}</a>`;
        });
    } catch (error) {
        console.error('加载类别失败:', error);
    }
}

// 加载商品
async function loadProducts(categoryId = 'all', searchTerm = '') {
    try {
        let url = `${API_BASE_URL}/product/list?page=${currentPage}&size=${itemsPerPage}`;
        if (categoryId !== 'all') {
            url += `&categoryId=${categoryId}`;
        }
        if (searchTerm) {
            url += `&search=${searchTerm}`;
        }
        const response = await axios.get(url);
        const products = response.data.data.content;
        const totalPages = response.data.data.totalPages;
        displayProducts(products);
        updatePagination(totalPages);
    } catch (error) {
        console.error('加载商品失败:', error);
    }
}

// 显示商品
function displayProducts(products) {
    const productList = document.getElementById('productList');
    productList.innerHTML = '';
    products.forEach(product => {
        const productElement = document.createElement('div');
        productElement.className = 'product-card';
        productElement.innerHTML = `
            <h3>${product.name}</h3>
            <img src="${product.imageUrl}" alt="${product.name}">
            <p>${product.description}</p>
            <p>价格: ${product.price}</p>
            <p>库存: ${product.stock}</p>
            <button onclick="addToCart(${product.id})">加入购物车</button>
        `;
        productList.appendChild(productElement);
    });
}

// 更新分页
function updatePagination(totalPages) {
    const pagination = document.getElementById('pagination');
    pagination.innerHTML = '';
    for (let i = 1; i <= totalPages; i++) {
        const pageLink = document.createElement('a');
        pageLink.href = '#';
        pageLink.textContent = i;
        pageLink.onclick = () => {
            currentPage = i;
            loadProducts();
        };
        pagination.appendChild(pageLink);
    }
}

// 设置事件监听器
function setupEventListeners() {
    document.getElementById('loginBtn').addEventListener('click', showLoginForm);
    document.getElementById('registerBtn').addEventListener('click', showRegisterForm);
    document.getElementById('loginFormElement').addEventListener('submit', login);
    document.getElementById('registerFormElement').addEventListener('submit', register);
    document.getElementById('searchBtn').addEventListener('click', search);
    document.getElementById('categoryNav').addEventListener('click', handleCategoryClick);
}

// 显示登录表单
function showLoginForm() {
    document.getElementById('loginForm').style.display = 'block';
    document.getElementById('registerForm').style.display = 'none';
}

// 显示注册表单
function showRegisterForm() {
    document.getElementById('registerForm').style.display = 'block';
    document.getElementById('loginForm').style.display = 'none';
}

// 登录
async function login(e) {
    e.preventDefault();
    const username = document.getElementById('loginUsername').value;
    const password = document.getElementById('loginPassword').value;
    try {
        const response = await axios.post(`${API_BASE_URL}/user/login`, { username, password });
        currentUser = response.data.data;
        updateUserInterface();
    } catch (error) {
        console.error('登录失败:', error);
        alert('登录失败，请检查用户名和密码');
    }
}

// 注册
async function register(e) {
    e.preventDefault();
    const username = document.getElementById('registerUsername').value;
    const password = document.getElementById('registerPassword').value;
    const phone = document.getElementById('registerPhone').value;
    const location = document.getElementById('registerLocation').value;
    try {
        await axios.post(`${API_BASE_URL}/user/register`, { username, password, phone, location });
        alert('注册成功，请登录');
        showLoginForm();
    } catch (error) {
        console.error('注册失败:', error);
        alert('注册失败，请稍后重试');
    }
}

// 更新用户界面
function updateUserInterface() {
    if (currentUser) {
        document.getElementById('userActions').style.display = 'none';
        document.getElementById('userProfile').style.display = 'block';
        document.getElementById('userInfo').textContent = `欢迎，${currentUser.username}`;
        loadOrderHistory();
    } else {
        document.getElementById('userActions').style.display = 'block';
        document.getElementById('userProfile').style.display = 'none';
    }
}

// 加载订单历史
async function loadOrderHistory() {
    try {
        const response = await axios.get(`${API_BASE_URL}/order/user/${currentUser.id}`);
        const orders = response.data.data;
        const orderHistory = document.getElementById('orderHistory');
        orderHistory.innerHTML = '';
        orders.forEach(order => {
            const orderElement = document.createElement('div');
            orderElement.innerHTML = `
                <p>订单ID: ${order.id}</p>
                <p>日期: ${new Date(order.orderDate).toLocaleString()}</p>
                <p>总金额: ${order.totalAmount}</p>
                <p>状态: ${order.status}</p>
            `;
            orderHistory.appendChild(orderElement);
        });
    } catch (error) {
        console.error('加载订单历史失败:', error);
    }
}

// 搜索
function search() {
    const searchTerm = document.getElementById('searchInput').value;
    currentPage = 1;
    loadProducts('all', searchTerm);
}

// 处理类别点击
function handleCategoryClick(e) {
    if (e.target.tagName === 'A') {
        e.preventDefault();
        const categoryId = e.target.dataset.categoryId;
        currentPage = 1;
        loadProducts(categoryId);
    }
}

// 加入购物车
async function addToCart(productId) {
    if (!currentUser) {
        alert('请先登录');
        return;
    }
    try {
        await axios.post(`${API_BASE_URL}/cart/add`, { userId: currentUser.id, productId });
        alert('已加入购物车');
    } catch (error) {
        console.error('加入购物车失败:', error);
        alert('加入购物车失败，请稍后重试');
    }
}

// 页面加载时初始化
window.onload = initPage;