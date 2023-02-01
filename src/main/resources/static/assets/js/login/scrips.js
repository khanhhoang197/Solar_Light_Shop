let options = document.querySelectorAll('.changeType');
let form = document.getElementById('form');
let bgactive = document.getElementById('bg-active');
var rotatedeg = 0;

options.forEach(val => {
    val.addEventListener('click', function(event){
        if(this.classList.contains('active')){
            return;
        }
        form.classList.remove('login');
        form.classList.remove('register');
        form.classList.add(this.id);
        bgactive.style.left = this.offsetLeft + 'px';
        options.forEach(item => {
            item.classList.remove('active');
        });
        this.classList.add('active');

        rotatedeg = rotatedeg + 200;
        document.getElementById('rotate').style.transform = 'translate(-50%) rotate(' + rotatedeg + 'deg)';
    })
});

function showPass() {
    let x = document.getElementById("pass-login");
    if (x.type == "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
    let y = document.querySelector(".fa-eye");
    y.classList.toggle("fa-eye-slash")
};
function showPassReg1() {
    let x = document.getElementById("pass-reg1");
    if (x.type == "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
};

