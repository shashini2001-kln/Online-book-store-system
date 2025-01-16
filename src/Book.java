import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Book extends book_db{
    String dir = System.getProperty("user.dir");

    String book_db_url = "jdbc:sqlite:%s\\src\\db\\books.db".formatted(dir);

        Book(){
            try {
                Connection c2 = DriverManager.getConnection(book_db_url);
                Statement s2 = c2.createStatement();
                c2.setAutoCommit(true);
                String sql2 = "CREATE TABLE IF NOT EXISTS  Books" +
                        "(ID INTEGER PRIMARY KEY     NOT NULL," +
                        "Title  TEXT    NOT NULL, " +
                        "AUTHOR TEXT    NOT NULL, " +
                        "Edition TEXT    NOT NULL, " +
                        "ISBN_Number TEXT NOT NULL, "+
                        "Category TEXT    NOT NULL, " +
                        "PRICE INT NOT NULL)";
                s2.executeUpdate(sql2);
                c2.close();
            }
            catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        public void add_new_book(String Title, String Author, String Edition, String ISBN_number, String Category, String Price ){
           try {
               Connection c3 = DriverManager.getConnection(book_db_url);
               String book_data_text = "INSERT INTO books(Title, Author, Edition, ISBN_number, Category, Price) VALUES(?,?,?,?,?,?) ";
               PreparedStatement pr = c3.prepareStatement(book_data_text);
               pr.setString(1, Title);
               pr.setString(2, Author);
               pr.setString(3, Edition);
               pr.setString(4, ISBN_number);
               pr.setString(5, Category);
               pr.setInt(6, Integer.parseInt(Price));
               pr.executeUpdate();
               pr.close();
               c3.close();
               System.out.println("Your book was added to the system");
           }
           catch (Exception e){
               System.err.println(e);
           }
        }
}

