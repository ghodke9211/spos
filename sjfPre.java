import java.util.Scanner;

public class sjfPre {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter no. of processes : ");
        int n=sc.nextInt();

        int[] process=new int[n];
        int[] arrival_time=new int[n];
        int[] burst_time=new int[n];
        int[] waiting_time=new int[n];
        int[] remaining_time=new int[n];
        int[] completion_time=new int[n];
        int[] tat=new int[n];

        for(int i=0;i<n;i++){
            System.out.println("Enter Arrival time for processes P"+(i+1)+": ");
            arrival_time[i]=sc.nextInt();
            System.out.println("Enter Burst time for processes P"+(i+1)+": ");
            burst_time[i]=sc.nextInt();
            process[i]=i+1;
            remaining_time[i]=burst_time[i];

        }

        int completed=0,currentTime=0,minIndex=-1;
        int minRemainingTime=Integer.MAX_VALUE;
        boolean foundProcess=false;
        
        while(completed<n){
            minRemainingTime=Integer.MAX_VALUE;
            foundProcess=false;

            for(int i=0;i<n;i++){
                if(arrival_time[i]<=currentTime && remaining_time[i]>0 && remaining_time[i]<minRemainingTime){
                    minRemainingTime=remaining_time[i];
                    minIndex=i;
                    foundProcess=true;
                }
            }
            if(!foundProcess){
                currentTime++;
                continue;
            }

            remaining_time[minIndex]--;
            currentTime++;

            if(remaining_time[minIndex]==0){
                completed++;
                completion_time[minIndex]=currentTime;
                tat[minIndex]=completion_time[minIndex]-arrival_time[minIndex];
                waiting_time[minIndex]=tat[minIndex]-burst_time[minIndex];
                if(remaining_time[minIndex]<0){
                    remaining_time[minIndex]=0;
                }
            }
        }
        float totalWaitingTime=0,totalTat=0;
        for(int i=0;i<n;i++){
            totalWaitingTime+=waiting_time[i];
            totalTat+=tat[i];
        }
        float avg_waitingTime=totalWaitingTime/n;
        float avg_TAT=totalTat/n;

        System.out.println("Process\tArrival time\tBurst Time\tCompletion time\tWaiting time\tTurn around time");

        for(int i=0;i<n;i++)
            System.out.println(process[i]+"\t\t"+arrival_time[i]+"\t\t"+burst_time[i]+"\t\t"+completion_time[i]+"\t\t"+waiting_time[i]+"\t\t"+tat[i]);

        System.out.println("\nAverage Waiting time : " +avg_waitingTime);
        System.out.println("\nAverage Turn around time : "+avg_TAT);

    }  
    
}

