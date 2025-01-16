import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class Payment {
    int Num_books;
    String dir = System.getProperty("user.dir");
    final public String db_url_user = "jdbc:sqlite:%s\\src\\db\\user_info.db".formatted(dir);

    String Destination;
    String pay_methode;

    public int No_of_books(HashMap<String, Integer> hashMap) {
        int sum = 0;
        for (int f : hashMap.values()) {
            sum += f;
        }
        String text = """
                Total Number Of Books : %d
                Total Price           : %d
                """.formatted(hashMap.size(), sum);
        System.out.println(text);
        return sum;
    }

    public String destination_address(String username) throws SQLException {
        PreparedStatement pr1 = null;
        ResultSet adr_rs = null;
        String validate = "SELECT * FROM User WHERE USER_NAME = ?";
        try {
            pr1 = DriverManager.getConnection(db_url_user).prepareStatement(validate);
            pr1.setString(1, username);
            adr_rs = pr1.executeQuery();
            if (adr_rs.next()) {
                return adr_rs.getString(4);
            }
            return null;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        } finally {
            pr1.close();
        }
    }

    public void paying(int sum) {
        String text = """
                --------------------Purchase Menu----------------------
                 1. Cash On Delivery
                 2. ByCredit or Debit Card
                -------------------------------------------------------
                """;
        System.out.println(text);
        System.out.print("Your Choice :");
        Scanner scn_new = new Scanner(System.in);
        switch (scn_new.nextInt()) {
            case 1 -> {
                System.out.println("Ok Get Ready To Pay %d When You received Your item".formatted(sum));
                System.out.println("Oder was placed successfully");
            }
            case 2 -> {
                System.out.println("Rs %s Money You need to pay".formatted(sum));
                System.out.println("Enter Your Card Details");
                System.out.print("Card Number : ");
                Scanner scn_new2 = new Scanner(System.in);
                scn_new2.nextLine();
                System.out.print("Expiry date : ");
                Scanner scn_new3 = new Scanner(System.in);
                scn_new3.nextLine();
                System.out.print("CVN number : ");
                Scanner scn_new4 = new Scanner(System.in);
                scn_new3.nextInt();
                System.out.println("Oder was placed successfully");
            }
        }

    }
}
