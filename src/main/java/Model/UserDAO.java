package Model;

import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    public void doSave(UserBean userBean) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Utente (Nome, Cognome, Data_Nascita, Email, Accesso, Telefono, Citta, Provincia, Codice_Postale, Indirizzo, Data_Registrazione, Stato, Amministratore) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, userBean.getNome());
            ps.setString(2, userBean.getCognome());
            ps.setString(3, userBean.getDdNascita());
            ps.setString(4, userBean.getEmail());
            ps.setString(5, userBean.getPassword());
            ps.setString(6, userBean.getTelefono());
            ps.setString(7, userBean.getCitta());
            ps.setString(8, userBean.getProvincia());
            ps.setString(9, userBean.getCap());
            ps.setString(10, userBean.getIndirizzo());
            ps.setString(11, userBean.getRegistrazione());
            ps.setString(12, userBean.getStato());
            ps.setString(13, userBean.isAdmin());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            userBean.setId(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserBean doRetriveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT *"+
                    "FROM Utente"+
                    " WHERE ID_UTENTE=?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
            UserBean userBean = new UserBean();
            userBean.setId(rs.getInt(1));
            userBean.setNome(rs.getString(2));
            userBean.setCognome(rs.getString(3));
            userBean.setDdNascita(rs.getString(4));
            userBean.setEmail(rs.getString(5));
            userBean.setPassword(rs.getString(6));
            userBean.setTelefono(rs.getString(7));
            userBean.setCitta(rs.getString(8));
            userBean.setProvincia(rs.getString(9));
            userBean.setCap(rs.getString(10));
            userBean.setIndirizzo(rs.getString(11));
            userBean.setRegistrazione(rs.getString(12));
            userBean.setStato(rs.getString(13));
            userBean.setAdmin(rs.getString(14));
            return userBean;
            }
            return null;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public UserBean doRetriveByEmailPasswd(String email, String passwd){
            try(Connection con = ConPool.getConnection()){
                PreparedStatement ps = con.prepareStatement(
                        "SELECT * " +
                                "FROM Utente " +
                                "WHERE Email=? AND Accesso=SHA1(?)");
                ps.setString(1,email);
                ps.setString(2,passwd);

                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    UserBean userBean = new UserBean();
                    userBean.setId(rs.getInt(1));
                    userBean.setNome(rs.getString(2));
                    userBean.setCognome(rs.getString(3));
                    userBean.setDdNascita(rs.getString(4));
                    userBean.setEmail(rs.getString(5));
                    userBean.setPassword(rs.getString(6));
                    userBean.setTelefono(rs.getString(7));
                    userBean.setCitta(rs.getString(8));
                    userBean.setProvincia(rs.getString(9));
                    userBean.setCap(rs.getString(10));
                    userBean.setIndirizzo(rs.getString(11));
                    userBean.setRegistrazione(rs.getString(12));
                    userBean.setStato(rs.getString(13));
                    userBean.setAdmin(rs.getString(14));
                    return userBean;
                }
                return null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
    public void updateUser(UserBean user){
        try(Connection con = ConPool.getConnection()){
            Statement statement = con.createStatement();
            String query = "UPDATE Utente SET Nome = '" + user.getNome()
                    + "', Cognome = '" + user.getCognome()
                    + "', Data_Nascita = '" + user.getDdNascita()
                    + "', Email = '" + user.getEmail()
                    + "', Accesso = '" + user.getPassword()
                    + "', Telefono = '" + user.getTelefono()
                    +  "', Citta = '" + user.getCitta()
                    + "', Provincia = '" + user.getProvincia()
                    + "', Codice_Postale = '" + user.getCap()
                    + "', Indirizzo = '" + user.getIndirizzo()
                    + "', Stato = '" + user.getStato()
                    + "', Amministratore = '" + user.isAdmin()
                    + "' WHERE Id_Utente = " + user.getId();
            statement.executeUpdate(query);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
}
        public void updateToAdmin(int id) {
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("UPDATE Utente SET Utente.Admin = true WHERE ID_Utente=? ");
            ps.setInt(1,id);

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void changeAdminStatus(boolean status,int id) {
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("UPDATE Utente SET Utente.Admin =? WHERE ID_Utente=? ");
            ps.setString(1,status+"");
            ps.setInt(2,id);

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void updateState(UserBean user) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE Utente SET Utente.Stato ='" +user.getStato() +"' WHERE ID_Utente=? ");
            ps.setInt(1,user.getId());
        }
    }
    public boolean isAlreadyRegistered(String email){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT email FROM Utente where Utente.email=?");
            ps.setString(1,email);
            ResultSet resultSet = ps.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<UserBean> doRetriveAllUsers() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Utente ORDER BY ID_Utente");

            ArrayList<UserBean> listUsers = new ArrayList<UserBean>();
            ResultSet rs = ps.executeQuery();
            while (!rs.next()) {
                UserBean user = new UserBean();
                user.setId(rs.getInt(1));
                user.setNome(rs.getString(2));
                user.setCognome(rs.getString(3));
                user.setDdNascita(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setPassword(rs.getString(6));
                user.setTelefono(rs.getString(7));
                user.setCitta(rs.getString(8));
                user.setProvincia(rs.getString(9));
                user.setCap(rs.getString(10));
                user.setIndirizzo(rs.getString(11));
                user.setStato(rs.getString(12));
                user.setAdmin(rs.getString(13));
                listUsers.add(user);
            }
            return listUsers;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
