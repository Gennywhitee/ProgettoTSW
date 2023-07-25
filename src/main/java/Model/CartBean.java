package Model;

import java.util.ArrayList;

public class CartBean {
    private int user;
    private int numberObject;
    private ArrayList<ProductCartBean> cartList;

    public CartBean() {
        cartList = new ArrayList<>();
        numberObject = 0;
    }
    public CartBean(int idUser){
        user = idUser;
        cartList = new ArrayList<>();
        numberObject = 0;
    }


    public int getNumberObject() {
        return numberObject;
    }

    public void setNumberObject(int number) {

        this.numberObject = number;
    }

    public ArrayList<ProductCartBean> getCartList() {

        return cartList;
    }
    public int getCart(){
        return user;
    }


    public void setCartList(ArrayList<ProductCartBean> cartList) {
        this.cartList = cartList;

        for (ProductCartBean productCartBean: cartList)
            this.numberObject += productCartBean.getQuantity();
    }

    public void addProduct(int id, int quantity) {

        numberObject += quantity;
        boolean isOn = false;
        for (ProductCartBean product : cartList) {

            if (product.getId() == id) {

                isOn = true;
                int newQuantity = (product.getQuantity() + quantity);
                product.setQuantity(newQuantity);
            }
        }

        if (!isOn) {

            ProductCartBean product = new ProductCartBean();
            product.setId(id);
            product.setQuantity(quantity);
            cartList.add(product);
        }
    }

    public void removeProduct(int id) {

        int position = 0;

        for (ProductCartBean product : cartList) {
            position++;

            if(product.getId() == id) {
                numberObject -= product.getQuantity();
                break;
            }
        }

        cartList.remove(position - 1);
    }
}