import java.util.Scanner;

public class Main {

    static int c[] = {500, 200, 100, 50, 20, 10};


    public static void main(String[] args) {

        Scanner con = new Scanner(System.in);

        int n = con.nextInt();


        int res = 0;
        int tmp = 0;
        for (int i = 0; i < 6; i++) {
            tmp  = res;
            res += n / c[i];
            if (tmp != res) {
                for (int j = 0 ; j < n/c[i]; j ++)
                System.out.println(c[i]);

            }
           // System.out.println(c[i] +" " + res );
            n = n % c[i];

        }

        if (n > 0)

            System.out.println("-1");

        else

            System.out.println(res);

        con.close();

    }

}  