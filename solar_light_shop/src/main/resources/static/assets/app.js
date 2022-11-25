class App{
    static DOMAIN_SERVER = "http://localhost:8080";
    static AUTH_URL = this.DOMAIN_SERVER + "/api/auth";
    static ADMIN_API = this.DOMAIN_SERVER + "/api/admins";



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
                title: 'OK',
                position: 'topLeft',
                timeout: 2500,
                message: m
            });
        }

        static showSuccessAlertRight(m) {
            iziToast.success({
                title: 'OK',
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

