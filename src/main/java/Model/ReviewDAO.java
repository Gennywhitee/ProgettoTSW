package Model;

import java.sql.*;
import java.util.ArrayList;

public class ReviewDAO {

    public void doSave(ReviewBean review) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Recensioni (idUtente,idProduct,stars,comment) VALUES (?,?,?,?)");
            ps.setInt(1, review.getIdUtente());
            ps.setInt(2, review.getIdProduct());
            ps.setInt(3, review.getStars());
            ps.setString(4, review.getComment());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ReviewBean searchReviewByUserProduct(int id_user, int id_product) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM RECENSIONI WHERE ID_Utente = " + id_user + "ID_Prodotto = " + id_product);
            ReviewBean reviewBean = (ReviewBean) ps.executeQuery();

            return reviewBean;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ReviewBean> searchByProduct(int id_product) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM RECENSIONI WHERE ID_Prodotto = " + id_product);
            ArrayList<ReviewBean> reviewList = new ArrayList<>();
            ReviewBean reviewBean = new ReviewBean();

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                reviewBean.setIdProduct(id_product);
                reviewBean.setIdUtente(rs.getInt(2));
                reviewBean.setStars(rs.getInt(3));
                reviewBean.setComment(rs.getString(4));
                reviewList.add(reviewBean);
            }
            return reviewList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
