import java.rmi.*;
import java.util.Scanner;

public class MyRMIClient {
    public static void main(String args[]){
        try{
            MyRMIInterface obj = (MyRMIInterface) Naming.lookup("dpd");
            Scanner sc = new Scanner(System.in);
            int r,c;
            System.out.print("\nEnter number of rows of the matrix: ");
            r = sc.nextInt();
            System.out.print("\nEnter number of columns of the matrix: ");
            c = sc.nextInt();
            int[][] matrix = new int[r][c],transposedMatrix;
            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){
                    System.out.print("\nEnter element [" + i + "][" + j + "] : ");
                    matrix[i][j] = sc.nextInt();
                }
            }

            System.out.println("\nThe original matrix is:");
            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }

            System.out.println("\nThe transposed matrix is:");


            transposedMatrix = obj.getTranspose(matrix,r,c);

            for(int i=0;i<c;i++){
                for(int j=0;j<r;j++){
                    System.out.print(transposedMatrix[i][j] + " ");
                }
                System.out.println();
            }
    } catch(Exception e){
            e.printStackTrace();
        }
    }
}
