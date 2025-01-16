import java.util.Scanner;

public class Feedback {
    public void Oder_status(){
        System.out.println("Your Oder Will Deliver with in 7 days from You purchase");
    }
    public void complain(){
        System.out.println("If You Have any complain Please tell us....");
        System.out.print("Please Give us your feedback : ");
        Scanner scn = new Scanner(System.in);
        scn.nextLine();

    }
}
