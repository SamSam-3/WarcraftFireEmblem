package Tests;

import plateau.Plateau;

public class TestRange {

    public static void main(String[] args) {

        draw(5);
    }

    public static void draw(int n) {
        n+=1;

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n-i;j++){
                System.out.print("   ");
            }

            for(int j=1;j<=i*2-1;j++){
                System.out.print(" * ");
            }

            System.out.println();

        }
        for(int i=n-1;i>0;i--){
            for(int j=1;j<=n-i;j++){
                System.out.print("   ");
            }

            for(int j=1;j<=i*2-1;j++){
                System.out.print(" * ");
            }

            System.out.println();
        }
    }
}
