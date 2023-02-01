class App{
    static DOMAIN_SERVER = "http://localhost:8080";
    static AUTH_URL = this.DOMAIN_SERVER + "/api/auth";
    static ADMIN_API = this.DOMAIN_SERVER + "/api/admins";
    static PROVINCE_URL = "https://vapi.vnappmob.com/api/province/";
    static CUSTOMER_API = this.DOMAIN_SERVER + "/api/customers";
    static PRODUCT_API = this.DOMAIN_SERVER + "/api/products";
    static USER_API = this.DOMAIN_SERVER + "/api/users";
    static ERROR_URL = this.DOMAIN_SERVER + "/error/";
    static CUSTOMER_AVATAR_URL = this.DOMAIN_SERVER + "/api/customer-avatars";
    static REGISTER_USER = this.DOMAIN_SERVER + "/api/auth/register";
    static LOGIN_USER = this.DOMAIN_SERVER + "/api/auth/login";


    static BASE_URL_CLOUD_IMAGE = "https://res.cloudinary.com/dv51vpfep/image/upload";
    static SCALE_IMAGE_W100_H80_Q100 = "c_limit,w_100,h_80,q_100";
    static SCALE_IMAGE_W600_H650_Q100 = "c_limit,w_600,h_650,q_100";
    static SCALE_IMAGE_W288_H216_Q100 = "c_limit,w_288,h_216,q_100";
    static SCALE_IMAGE_W200_H250_Q100 = "c_limit,w_200,h_250,q_100";



    static SweetAlert = class {
        static showDeactivateConfirmDialog() {
            return Swal.fire({
                icon: 'warning',
                text: 'Bạn có muốn xóa khách hàng này không ?',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Có. hãy xóa ngay !',
                cancelButtonText: 'Hủy',
            })
        }

        static showSuccessAlert(t) {
            Swal.fire({
                icon: 'success',
                title: t,
                position: 'top-end',
                showConfirmButton: false,
                timer: 1500
            })
        }

        static showErrorAlert(t) {
            Swal.fire({
                icon: 'error',
                title: 'Lỗi',
                text: t,
            })
        }

        static showError401() {
            Swal.fire({
                icon: 'error',
                title: 'Access Denied',
                text: 'Invalid credentials!',
            })
        }

        static showError403() {
            Swal.fire({
                icon: 'error',
                title: 'Access Denied',
                text: 'Bạn không được phép thực hiện chức năng này!',
            })
        }

        static showError500() {
            Swal.fire({
                icon: 'error',
                title: 'Internal Server Error',
                text: 'Hệ thống Server đang có vấn đề hoặc không truy cập được.',
            })
        }

        static redirectPage(message1, message2, timer, url) {
            let timerInterval;
            Swal.fire({
                icon: 'success',
                title: "<br>" + message1,
                html: message2,
                timer: timer,
                timerProgressBar: true,
                didOpen: () => {
                    Swal.showLoading()
                    const b = Swal.getHtmlContainer().querySelector('b')
                    timerInterval = setInterval(() => {
                        b.textContent = Swal.getTimerLeft()
                    }, 3000)
                },
                willClose: () => {
                    clearInterval(timerInterval);
                    // window.location.href = url;
                }
            }).then((result) => {
                /* Read more about handling dismissals below */
                if (result.dismiss === Swal.DismissReason.timer) {
                    console.log('I was closed by the timer')
                }
            })
        }

    }

    static IziToast = class {
        static showSuccessAlertLeft(m) {
            iziToast.success({
                title: '',
                position: 'topLeft',
                timeout: 2500,
                message: m
            });
        }

        static showSuccessAlertRight(m) {
            iziToast.success({
                title: '',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }

        static showErrorAlertLeft(m) {
            iziToast.error({
                title: 'Error',
                position: 'topLeft',
                timeout: 2500,
                message: m
            });
        }

        static showErrorAlertRight(m) {
            iziToast.error({
                title: 'Error',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }

    }

    static Notify = class {
        static showSuccessAlert(m) {
            $.notify(m, "success");
        }

        static showErrorAlert(m) {
            $.notify(m, "error");
        }
    }
}
class User{
    constructor(id, createAt, createBy, deleted, updateAt, updateBy, fullName, passWord, userName, roleId ) {
        this.id = id;
        this.crateAt = createAt;
        this.createBy = createBy;
        this.deleted = deleted;
        this.updateAt = updateAt;
        this.updateBy = updateBy;
        this.fullName = fullName;
        this.passWord = passWord;
        this.userName = userName;
        this.roleId = roleId;
    }
}
class LocationRegion {
    constructor(id, provinceId, provinceName, districtId, districtName, wardId, wardName, address) {
        this.id = id;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.wardId = wardId;
        this.wardName = wardName;
        this.address = address;
    }

}
class Customer {
    constructor(id, fullName, email, phone, deleted, locationRegion) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.deleted = deleted;
        this.locationRegion = locationRegion;
    }
}

class CustomerAvatar{
    constructor(id, fileName, fileFolder, fileUrl, fileType, cloudId, ts, customer) {
        this.id = id;
        this.fileName = fileName;
        this.fileFolder = fileFolder;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
        this.cloudId = cloudId;
        this.ts = ts;
        this.customer = customer;
    }
}

class ProductAvatar {
    constructor(id, fileName, fileFolder, fileUrl, fileType, cloudId, ts) {
        this.id = id;
        this.fileName = fileName;
        this.fileFolder = fileFolder;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
        this.cloudId = cloudId;
        this.ts = ts;
    }
}

class Product {
    constructor(id, productName, price, quantity, description, productAvatar) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.productAvatar = productAvatar;
    }
}