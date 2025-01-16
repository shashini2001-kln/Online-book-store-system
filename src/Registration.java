import  java.util.Scanner;
import java.sql.*;

public class Registration implements database{
        //declaring necessary variables
        String dir = System.getProperty("user.dir");

        String db_url_user = "jdbc:sqlite:%s\\src\\db\\user_info.db".formatted(dir);
        public String db_url_user1 = database.db_url_user;

        String[][] user_details = new String[7][2];
        String[] lit = {"Firstname", "Lastname", "Contact Number", "Home_address", "Email_address", "Username", "Password"};
        Connection My_con;
        //calling the constructor
        Registration() {

                for (int i = 0; i < 7; i++) {
                        Scanner obj = new Scanner(System.in);
                        System.out.print("Please Enter Your " + lit[i] + " : ");
                        user_details[i][0] = lit[i];
                        user_details[i][1] = obj.nextLine();
                }
                PreparedStatement pr3 = null;
                ResultSet rs3 = null;
                String validate = "SELECT * FROM User WHERE USER_NAME = ?";
                try {
                        pr3 = DriverManager.getConnection(db_url_user).prepareStatement(validate);
                        pr3.setString(1, user_details[5][1]);
                        rs3 = pr3.executeQuery();
                        if (rs3.next()){
                                System.out.println("User Name Already exists");
                                System.exit(1);
                        }
                        rs3.close();
                        pr3.close();

                }
                catch (Exception e){
                        System.out.println(e.getMessage());
                }
        }

        public void creat_db() {
                try {   //jdbc driver importing
                        Class.forName("org.sqlite.JDBC");
                        Connection c1 = DriverManager.getConnection(db_url_user);
                        Statement s1 = c1.createStatement();
                        c1.setAutoCommit(true);
                        String sql = "CREATE TABLE IF NOT EXISTS  User" +
                                "(ID INTEGER  PRIMARY KEY   NOT NULL," +
                                " First_NAME           TEXT    NOT NULL," +
                                " Last_NAME      TEXT    NOT NULL, " +
                                " CONTACT_NUMBER TEXT," +
                                " ADDRESS        CHAR(50), " +
                                " EMAIL_ADDRESS        CHAR(50), " +
                                " USER_NAME  TEXT     NOT NULL, " +
                                " PASSWORD        TEXT  NOT NULL) ";
                        s1.executeUpdate(sql);
                        s1.close();
                        c1.close();
                } catch (Exception e) {
                        System.out.println("Database error" + e.getMessage() + e.getClass());
                }
        }
                public void db_update() {
                        String db_update_text = "INSERT INTO User(First_NAME, Last_NAME, CONTACT_NUMBER, ADDRESS, EMAIL_ADDRESS, USER_NAME, PASSWORD) VALUES(?,?,?,?,?,?,?)";
                        try {
                                Connection con = DriverManager.getConnection(db_url_user);
                                PreparedStatement pr4 = con.prepareStatement(db_update_text);
                                //user for loop to iterate
                                for (int i = 0 ; i < 7 ; i++){
                                        pr4.setString((i+1), user_details[i][1]);
                                }
//                                pr.setString(1, user_details[0][1]);
//                                pr.setString(2, user_details[1][1]);
//                                pr.setString(3, user_details[2][1]);
//                                pr.setString(4, user_details[3][1]);
//                                pr.setString(5, user_details[4][1]);
//                                pr.setString(6, user_details[5][1]);
//                                pr.setString(7, user_details[6][1]);
                                pr4.executeUpdate();
                                pr4.close();
                                con.close();

                        } catch (Exception e) {
                                System.out.println(e.getMessage());

                        }

                }
                public void Create_new_account() {
                        System.out.println("Account Created Successfully ");
                        System.out.println("Your Username is " + user_details[5][1]);
                }
        }

