function Car(name, price, img) {
    this.name = name;
    this.price = price;
    this.img = img;
}

var cars = []
const key_data = 'car_data';

function getData(key) {
    return JSON.parse(localStorage.getItem(key));
}

function setData(key, data) {
    localStorage.setItem(key, JSON.stringify(data));
}

function init() {
    if (getData(key_data) == null) {
        cars = [
            new Car("VinFast Lux A2.0", 800000000, "img/4.png"),
            new Car("VinFast SA 2.0", 1500000000, "img/0.png"),
            new Car("VinFast Fadil", 600000000, "img/1.png"),
            new Car("Mazda CX-5", 1400000000, "img/test.png"),
            new Car("Toyota Cross 2022", 1300000000, "img/2.png"),
            new Car("Toyota bZ4X Consideration", 3400000000, "img/5.png"),
            new Car("Kia-K8", 1000000000, "img/3.png"),
            new Car("Maserati", 18000000000, "img/6.png"),
        ]
        setData(key_data, cars);
    } else {
        cars = getData(key_data);
    }
}
 
function Rendercontent(data) {
    let htmls = data.map(function (car, index) {
        return `
            <ul class="products">
                <li>
                    <div class="product-item">
                        <div class="product-top">
                        
                                <a href="" class="product-img">
                                    <img src="${car.img}" alt="" style="height:200px">
                                </a>
                       
                            <div class="acction">
                                <button class="edit-product" onclick="Edit(${index})">Sửa</button>
                                <button cass="delete-product" onclick="Remove(${index})">Xóa</button>
                            </div>
                        </div>
                        <div class="product-info">
                            <a href="" class="product-name">
                                ${car.name}
                            </a>
                            <div class="product-price">
                                ${formatCurrency(car.price)}
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        `
    });
    document.querySelector('#content').innerHTML = htmls.join("");
}


function addProduct() {
    document.querySelector(".form-add-products").classListr.remove("add-none");
    document.querySelector('.form-edit-products').classList.add("edit-none");
}

function Create() {
    let addName = document.querySelector('#addName').value.trim();
    let addPrice = document.querySelector('#addPrice').value;
    let addImage = document.querySelector('#addImage').value.trim();
    if (addName == null || addName == '') {
        alert('Tên không được để trống, hãy nhập tên!');
        return;
    }
    if (addPrice == null || addPrice == '') {
        alert('Hãy nhập giá của sản phẩm');
        return;
    }
    if (addImage == null || addImage == '') {
        alert('Hãy dán link ảnh sản phẩm.');
        return;
    }
    cars.unshift(new Car(addName, addPrice, addImage));
    setData(key_data, cars);
    Rendercontent(cars);
    resetForm();
}

function clearFormAdd() {
    document.querySelector(".form-add-products").classList.add("add-none");
    resetForm();
}

function clearFormEdit() {
    document.querySelector('.form-edit-products').classList.add('edit-none');
    document.getElementById('indexCar').value = 0;
    resetFormEdit();
}

function resetForm() {
    document.querySelector('#addName').value = '';
    document.querySelector('#addPrice').value = '';
    document.querySelector('#addImage').value = '';
}

function resetFormEdit() {
    document.querySelector('#editName').value = '';
    document.querySelector('#editPrice').value = '';
    document.querySelector('#editImage').value = '';
}

function formatCurrency(number) {
    return number.toLocaleString('vi', { style: 'currency', currency: 'VND' });
}

function Remove(index) {
    let confirmed = window.confirm('Bạn có chắc chắn xóa sản phẩm này không?');
    if (confirmed) {
        cars.splice(index, 1);
        setData(key_data, cars);
        Rendercontent(cars);
    }
}


function Edit(index) {
    document.querySelector(".form-add-products").classList.add("add-none");
    document.querySelector('.form-edit-products').classList.remove("edit-none");
    document.getElementById('indexCar').value = index;
    let car = cars[index];
    document.querySelector('#editName').value = car.name;
    document.querySelector('#editPrice').value = car.price;
    document.querySelector('#editImage').value = car.img;

}

function UpdateEdit() {
    let editName = document.querySelector('#editName').value.trim();
    let editPrice = document.querySelector('#editPrice').value;
    let editImage = document.querySelector('#editImage').value.trim();
    if (editName === '') {
        alert('Tên không được để trống, hãy nhập tên!');
        return;
    }
    if (editPrice == null || editPrice == '') {
        alert('Hãy nhập giá của sản phẩm');
        return;
    }
    if (editImage == null || editImage == '') {
        alert('Hãy dán link ảnh sản phẩm.');
        return;
    }
    let index = document.getElementById('indexCar').value;
    cars[index] = new Car(editName, editPrice, editImage);
    setData(key_data, cars);
    clearFormEdit();
    Rendercontent(cars);

}

function search() {
    let keywork = document.querySelector('#searchinput').value;
    let result = cars.filter(function (car) {
        return car.name.toLowerCase().indexOf(keywork.toLowerCase()) != -1;
    })
    Rendercontent(result);
}

function sort(direct) {
    if (direct == "sort_asc") {
        cars.sort(function (car, index) {
            return car.price - index.price;
        });
    } else {
        cars.sort(function (car, index) {
            return index.price - car.price;
        });
    }
    Rendercontent(cars);
}

function Run() {
    init();
    Rendercontent(cars);
}
Run();