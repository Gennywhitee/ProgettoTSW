package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartDAO {

    public void doSave(int user, int product, int quantity) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Carrello VALUES (?,?,?)");

            ps.setInt(1, product);
            ps.setInt(2, quantity);
            ps.setInt(3, user);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int user) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM Carrello WHERE Utente=?");

            ps.setInt(1, user);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CartBean getCart(int user) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Carrello WHERE Utente=?");

            ps.setInt(1, user);
            ResultSet rs = ps.executeQuery();

            CartBean cartBean = new CartBean(rs.getInt(1));

            return cartBean;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ProductBean> getCartList(int user) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Carrello WHERE Utente=?");

            ps.setInt(1,user);
            ArrayList<ProductBean> productList = new ArrayList<>();

            ResultSet rs = ps.executeQuery();
            while(!rs.next()){
                ProductBean productBean = new ProductBean();
                productBean.setId(rs.getInt(1));
                productBean.setQuantity(rs.getInt(2));
                productList.add(productBean);
            }

            return productList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}