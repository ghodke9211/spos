import java.util.*;
public class worstfit {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter number of memory blocks : ");
        int nb=sc.nextInt();
        int[] blockSize=new int[nb];

        System.out.println("Enter sizes of memory blocks : ");
        for(int i=0;i<nb;i++){
            blockSize[i]=sc.nextInt();
        }
        System.out.println("Enter number of processes : ");
        int np=sc.nextInt();
        int[] processSize=new int[np];
        int[] allocation=new int[np];
        System.out.println("Enter sizes of processes: ");
        for(int i=0;i<np;i++){
            processSize[i]=sc.nextInt();
            allocation[i]=-1;
        }

        //
        for(int i=0;i<np;i++){
            int worstInd=-1;
            for(int j=0;j<nb;j++){
                if(blockSize[j]>=processSize[i]){
                    if(worstInd==-1 || blockSize[j]>blockSize[worstInd]){
                        worstInd=j;
                    }
                }
            }
            if(worstInd!=-1){
                allocation[i]=worstInd;
                blockSize[worstInd]-=processSize[i];
            }
        }
        System.out.println("Process no\tProcess size\tBlock no");
        for(int i=0;i<np;i++){
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
