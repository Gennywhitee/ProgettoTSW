const info_string = /^([a-zA-Z\xE0\xE8\xE9\xF9\xF2\xEC\x27]\s?){2,20}$/;
const email_string = /^[a-zA-Z\d._%-]+@[a-zA-Z\d.-]+\.[a-zA-Z]{2,20}$/;
const password_string = /^[a-zA-Z\d\-\xE0\xE8\xE9\xF9\xF2\xEC\x27]{6,16}/;
const phone_string = /^\d{10}$/;
const province_string = /^([a-zA-Z]{2})$/;
const postalcode_string = /^\d{5}$/;
const address_string = /^([a-zA-Z\d\xE0\xE8\xE9\xF9\xF2\xEC\x27\x2C]\s?){2,20}$/;
const number_string = /^\d+$/;
const double_string = /^(\d+(?:[.,]\d{2})?)$/;
const holder_string = /^([a-zA-Z\xE0\xE8\xE9\xF9\xF2\xEC\x27]\s?){2,255}$/;

function controlName(){
    let name = document.getElementById("nome").value; //inizializzo la variabile name, con il valore che ha id=nome
    if (info_string.test(name)){ //controllo stringa con regex
        $("#name").css("border-color","#E5E5E5"); //caso nome supera test
        return true;
    } else{
        $("#name").css("border-color","#C92403"); //caso in cui nome NON supera test
        return false;
    }
}
function controlCognome(){
    let cognome = document.getElementById("cognome").value;
    if (info_string.test(cognome)){
        $("#cognome").css("border-color","#E5E5E5");
        return true;
    } else{
        $("#cognome").css("border-color","#C92403");
        return false;
    }
}
function controlDdNascita() {

        let dDNascita = new Date(document.getElementById("dDNascita").value);
        let today = new Date();

        if (today > dDNascita) {
            $("#dDNascita").css("border-color", "#E5E5E5");
            return true;
        } else {
            $("#dDNascita").css("border-color", "#C92403");
            return false;
        }
}
function controlEmail(){
    let email = document.getElementById("email").value;
    if (email_string.test(email)){
        $("#email").css("border-color","#E5E5E5");
        return true;
    } else{
        $("#email").css("border-color","#C92403");
        return false;
    }
}
function controlPasswd(){
    let password = document.getElementById("password").value;
    if (password_string.test(password)){
        $("#password").css("border-color","#E5E5E5");
        return true;
    } else{
        $("#password").css("border-color","#C92403");
        return false;
    }
}
function controlTelefono(){
    let telefono = document.getElementById("telefono").value;
    if (phone_string.test(telefono)){
        $("#telefono").css("border-color","#E5E5E5");
        return true;
    } else{
        $("#telefono").css("border-color","#C92403");
        return false;
    }
}
function controlCitta(){
    let citta = document.getElementById("citta").value;
    if (info_string.test(citta)){
        $("#citta").css("border-color","#E5E5E5");
        return true
    } else{
        $("#citta").css("border-color","#C92403");
        return false;
    }
}

function controlProvince(){
    let provincia = document.getElementById("provincia").value;
    if (province_string.test(provincia)){
        $("#provincia").css("border-color","#E5E5E5");
        return true;
    } else{
        $("#provincia").css("border-color","#C92403");
        return false;
    }
}
function controlIndirizzo(){
    let indirizzo = document.getElementById("indirizzo").value;
    if (address_string.test(indirizzo)){
        $("#indirizzo").css("border-color","#E5E5E5");
        return true;
    } else{
        $("#indirizzo").css("border-color","#C92403");
        return false;
    }
}
function controlCAP(){
    let cap = document.getElementById("cap").value;
    if (postalcode_string.test(cap)){
        $("#cap").css("border-color","#E5E5E5");
        return true;
    } else{
        $("#cap").css("border-color","#C92403");
        return false;
    }
}

function validateRegistration(){
    if(controlName() && controlCognome() && controlDdNascita() && controlEmail() && controlPasswd() &&
        controlTelefono() && controlCitta() && controlProvince() && controlIndirizzo() && controlCAP()){
        document.getElementById("loginForm").submit();
        return true;
    }

}

function validateLogin() {

    if (controlEmail() && controlPasswd()){
        document.getElementById("loginForm").submit();
        return true;
    }
    return false;

}

function validateEditProfile(){
    if(controlName() && controlCognome() && controlDdNascita() && controlEmail() && controlPasswd() &&
        controlTelefono() && controlCitta() && controlProvince() && controlIndirizzo() && controlCAP()){
        document.getElementById("edit-profile").submit();
        return true;
    }
}

function validateProdName(){
    let prodName = document.getElementById("name").value
    if (info_string.test(prodName)){
        $("#name").css("border-color","#E5E5E5");
        return true;
    } else{
        $("#name").css("border-color","#C92403");
        return false;
    }
}

function validateProdPrice(){
    let prodPrice = document.getElementById("price").value
    if (double_string.test(prodPrice)){
        $("#price").css("border-color","#E5E5E5");
        return true;
    } else{
        $("#price").css("border-color","#C92403");
        return false;
    }
}

function validateProdQuantity(){
    let prodQuantity = document.getElementById("quantity").value
    if (number_string.test(prodQuantity)){
        $("#quantity").css("border-color","#E5E5E5");
        return true;
    } else{
        $("#quantity").css("border-color","#C92403");
        return false;
    }
}

function validateProdDescription(){
    let prodDesc = document.getElementById("description").value
    if (holder_string.test(prodDesc)){
        $("#description").css("border-color","#E5E5E5");
        return true;
    } else{
        $("#description").css("border-color","#C92403");
        return false;
    }
}

function validateImage() {

    if ($(".preview").attr("src") === "") {
        $("label#imageUpload").css("color", "#C92403");
        return false;
    }
    else {
        $("label#imageUpload").css("color", "#E5E5E5");
        return true;
    }
}
function validateCategoryProduct() {

    let category = document.getElementById("category").value;

    if (category.length > 0 && category.length <= 20) {
        $("#category").css("border-color", "#E5E5E5");
        return true;
    }
    else {
        $("#category").css("border-color", "#C92403");
        return false;
    }
}


function validateAddProduct(){
    if(validateProdName() && validateProdPrice() && validateProdQuantity() && validateProdDescription() &&
        validateImage() && validateCategoryProduct()){
        document.getElementById("add-product-form").submit();
        return true;
    }
    return false;
}



