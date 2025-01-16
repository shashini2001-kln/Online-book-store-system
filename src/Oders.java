import java.sql.*;

public class Oders {
    String dir = System.getProperty("user.dir");

    String book_db_url = "jdbc:sqlite:%s\\src\\db\\books.db".formatted(dir);
    public ResultSet Availble_books(){
        try {
            Connection conn = DriverManager.getConnection(book_db_url);
            String sql_Query = "SELECT * FROM books ";
//            System.out.println(sql_Query);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql_Query);
            return rs;



        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;

    }
    public ResultSet Availble_books(String Book_name){
        try {
            Connection conn = DriverManager.getConnection(book_db_url);
            String sql_Query = "SELECT * FROM books WHERE Title = ?";
//            System.out.println(sql_Query);
            PreparedStatement stmt = conn.prepareStatement(sql_Query);
            stmt.setString(1, Book_name);
            ResultSet rs = stmt.executeQuery(sql_Query);
            return rs;



        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }
    public ResultSet select_book(int id){
        try {
                Connection conn = DriverManager.getConnection(book_db_url);
                String sql_Query = "SELECT * FROM books WHERE ID = ?";
                System.out.println(sql_Query);
                PreparedStatement pstmt = conn.prepareStatement(sql_Query);
                pstmt.setInt(1, id);
                return pstmt.executeQuery();
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }
}

