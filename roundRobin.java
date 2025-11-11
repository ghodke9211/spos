import java.util.Scanner;

public class roundRobin {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter no. of processes : ");
        int n=sc.nextInt();

        int[] arrivalTime=new int[n];
        int[] burstTime=new int[n];
        int[] remainingTime=new int[n];
        int[] waitingTime=new int[n];
        int[] tat=new int[n];

        System.out.println("Enter Time quantum : ");
        int quantum=sc.nextInt();

         for(int i=0;i<n;i++){
            System.out.println("Enter arrival time for process "+(i+1));
            arrivalTime[i]=sc.nextInt();
            System.out.println("Enter burst time for process "+(i+1));
            burstTime[i]=sc.nextInt();
            remainingTime[i]=burstTime[i];

        }

        int currentTime=0;
        boolean done;

        do{
            done=true;
            boolean executedInThisCycle=false;

            for(int i=0;i<n;i++){
                if(arrivalTime[i]<=currentTime && remainingTime[i]>0){
                    done=false;
                    executedInThisCycle=true;

                    if(remainingTime[i]>quantum){
                        currentTime+=quantum;
                        remainingTime[i]-=quantum;

                    }
                    else{
                        currentTime+=remainingTime[i];
                        waitingTime[i]=currentTime-arrivalTime[i]-burstTime[i];
                        tat[i]=waitingTime[i]+burstTime[i];
                        remainingTime[i]=0;
                    }
                }
            }
            if(!executedInThisCycle){
                currentTime++;
            }
        }while(!done);

        System.out.println("Process\tArrival time\tBurst time\tWaiting time");
        float totalWaitingTime=0;
        float totalTATTime=0;
        for(int i=0;i<n;i++){
            totalWaitingTime+=waitingTime[i];
            totalTATTime+=tat[i];
            System.out.println("P"+(i+1)+"\t"+arrivalTime[i]+"\t\t"+burstTime[i]+"\t\t"+waitingTime[i]);
        }
        System.out.println("Average Waiting time is : "+totalWaitingTime/n);
        System.out.println("Average turnaround time is : "+totalTATTime/n);
    }
}