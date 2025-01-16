import java.sql.*;
import java.util.Scanner;

public class Login {
    Connection con;
    public String db_url_user = "jdbc:sqlite:D:\\Java_project\\prgct1\\src\\db\\user_info.db";
    //Make a function to validate login
    public boolean sing_in(String input_username, String input_password) throws Exception {
        PreparedStatement pr1 = null;
        ResultSet rs1 = null;
        String validate = "SELECT * FROM User WHERE USER_NAME = ? AND PASSWORD = ?"; //sql Query
        try {
            pr1 = DriverManager.getConnection(db_url_user).prepareStatement(validate);
            pr1.setString(1 , input_username);
            pr1.setString(2, input_password);
            //Logic
            rs1 = pr1.executeQuery();
            if (rs1.next()){
                return true;
            }
            return false;
        }
        //Handle the errors
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        finally {
            pr1.close();
            rs1.close();
        }
    }
    public void forget_password() {
        System.out.print("Enter Your UserName : ");
        Scanner sc = new Scanner(System.in);
        String  user_nm    =   sc.nextLine();
        //declaring variables before try block more easy to understand the code
        PreparedStatement pr2 = null;
        ResultSet rs2 = null;
        String validate = "SELECT * FROM User WHERE USER_NAME = ?";
        try {
            //pr2,rs2 are variables anyone can easily understand the logic
            pr2 = DriverManager.getConnection(db_url_user).prepareStatement(validate);
            pr2.setString(1 , user_nm);
            rs2 = pr2.executeQuery();
            if (rs2.next()){
                System.out.println("Password reset link sent your email");
                System.out.println("After a creat new password, Please login Again");
                System.exit(0);
            }
            else {
                System.out.println("We can't Find username You provided");
            }
            //we must close the all connections
            rs2.close();
            pr2.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    public ResultSet USER_page(String user_name) {

        String text_user_data = "SELECT * FROM User WHERE USER_NAME = ?";
        try {
            Class.forName("org.sqlite.JDBC");
            Connection cc = DriverManager.getConnection(db_url_user);
            PreparedStatement pr2 = cc.prepareStatement(text_user_data);
            pr2.setString(1 ,user_name);
            return pr2.executeQuery();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    return null;
    }
}