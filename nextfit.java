import java.util.Scanner;

public class nextfit {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        System.out.println("\nEnter no. of memory blocks : ");
        int m=sc.nextInt();

        int[] blockSize=new int[m];

        System.out.println("Enter size of each block ");
        for(int i=0;i<m;i++){
            blockSize[i]=sc.nextInt();

        }

        System.out.println("Enter No. of processes ");
        int n=sc.nextInt();

        int[] processSize=new int[n];
        int[] allocation=new int[n];

        System.out.println("Enter size of each process ");
        for(int i=0;i<n;i++){
            processSize[i]=sc.nextInt();
            allocation[i]=-1;
        }

        //Worst fit

        int j=0;
        for(int i=0;i<n;i++){
            int count=0;
            boolean allocated=false;
            while(count<m){
                if(blockSize[j]>=processSize[i]){
                    allocation[i]=j;
                    blockSize[j]-=processSize[i];
                    allocated=true;
                    break;
                }
                j=(j+1)%m;
                count++;
            }
            if(!allocated){
                allocation[i]=-1;
            }
            else{
                j=(j+1)%m;
            }
        }

        System.out.println("Process no\tProcess size\tBlock no");
        for(int i=0;i<n;i++){
            System.out.print((i+1)+"\t\t"+processSize[i]+"\t\t");
            if(allocation[i]!=-1){
                System.out.println(allocation[i]+1);
            }
            else{
                System.out.println("Not Allocated ");
            }
        }
    }    
}
