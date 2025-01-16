import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static Boolean log_ok = false;
    static int sum = 0;
    static HashMap<String, Integer> oder_list = new HashMap<>();
    public static String[] data_arry = new String[2];

    public static void main(String[] args) throws Exception {
        Scanner obj2 = new Scanner(System.in);
        //Use Long string
        String text = """ 
                --------------------------------------------------------
                        1 . Create New Account
                        2 . Sign in To Account
                        3 . Forgot password 
                ---------------------------------------------------------
                """;
        System.out.println(text);
        System.out.print("Your Choice : ");
        switch (obj2.nextInt()) {
            case 1:
                System.out.println("Please Register !!!");
                Registration per1 = new Registration();
                per1.creat_db();
                per1.db_update();
                per1.Create_new_account();
                break;

            case 2:
                System.out.println("Pleaser Enter Your Details");
                System.out.print("User Name : ");
                Scanner scn = new Scanner(System.in);
                data_arry[0] = scn.nextLine();
                System.out.print("Password : ");
                data_arry[1] = scn.nextLine();
                Login login = new Login();
                Main.log_ok = login.sing_in(data_arry[0], data_arry[1]);
                if (log_ok) {
                    System.out.println("Logging Successfully");
                    System.out.println(log_ok);
                } else {
                    System.out.println("Logging Fail !!!!. Please check Your credentials");
                    System.exit(1);
                }
                break;

            case 3:
                Login login2 = new Login();
                login2.forget_password();
                System.exit(0);
        }
        if (!log_ok) {
            System.out.println("Please sign in");
            System.exit(0);
        }

            Login login3 = new Login();
            ResultSet dataset = login3.USER_page(data_arry[0]);
            //use string formatted methode
            String Welcome = """
                    --------------------------------------------
                                   User Details
                    -------------------------------------------
                    Name            :- %s %s
                    Contact Number  :- %s
                    Address         :- %s
                    Email           :- %s
                    --------------------------------------------
                    """.formatted(dataset.getString(2), dataset.getString(3), dataset.getString(4), dataset.getString(5), dataset.getString(6));
            System.out.println(Welcome);

            if (!Main.log_ok) {
                System.exit(0);
            }
        System.out.println(" 1. Sell a Book");
            System.out.println(" 2. Buy a Book");
            System.out.print(" Choice : ");
            Scanner scn3 = new Scanner(System.in);
            switch (scn3.nextInt()) {
                case 1:
                    System.out.println("Please Enter Details Of Your Book (PLEASE SEPARATE DETAILS USING COMMA)");
                    System.out.println("Ex : Title  Author Edition  ISBN_number Category Price");
                    System.out.print("Book Details : ");
                    Scanner scn4 = new Scanner(System.in);
                    String[] data_set = scn4.nextLine().split(",");
//                    System.out.println(Arrays.toString(data_set));
                    Book book_obj1 = new Book();
                    book_obj1.add_new_book(data_set[0], data_set[1], data_set[2], data_set[3], data_set[4], data_set[5]);
                    break;

                case 2:
                    int t = 0;
                    while (t != 2) {
                        System.out.print("Enter The Book Name or Search Keyword : ");
                        Scanner scn5 = new Scanner(System.in);
                        String book_name = scn5.nextLine();
                        Oders oder_obj1 = new Oders();
                        ResultSet rst;
                        if(book_name != null){
                             rst = oder_obj1.Availble_books();
                        }
                        else {
                             rst = oder_obj1.Availble_books(book_name);
                        }

//                        System.out.println(rst);
                        System.out.println("ID\tBook Name\t\tAuthor\t\t\t\tEdition\t\tISBN Number\t\t\tCategory\tPrice");
                        while (rst.next()) {
                            String text_bk = "%d\t%s\t%s\t%s\t\t%s\t\t%s\t%d".formatted(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getInt(7));
                            System.out.println(text_bk);
                        }
                        System.out.print("Enter The Number of the book you want :");
                        Scanner scn6 = new Scanner(System.in);
                        ResultSet rst2 = oder_obj1.select_book(scn6.nextInt());
                        String book_test = """
                                Book Name        : %s
                                Author           : %s
                                Price            : %d
                                Available Status : ok
                                """.formatted(rst2.getString(2), rst2.getString(3), rst2.getInt(7));
                        oder_list.put(rst2.getString(2), rst2.getInt(7));
//                        System.out.println(book_test);
                        System.out.print("Do You Want select another book (1)    \nor Go to the payment page (2) : ");
                        Scanner scn7 = new Scanner(System.in);
                        t = scn7.nextInt();
//                        System.out.println(t);
                    }
                    Payment payment = new Payment();
                    String destination = payment.destination_address(data_arry[0]);
                    sum = payment.No_of_books(oder_list);
                    payment.paying(sum);
                    break;


                default:
                    throw new IllegalStateException("Unexpected value: " + scn3.nextInt() + "Enter The correct number");
            }
            Feedback feedback = new Feedback();
            if(sum != 0){
                feedback.Oder_status();
            }

            feedback.complain();
            System.out.println("Nice to do business with you !!! Have a nice day");
        }

    }
