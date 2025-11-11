import java.io.*;
import java.util.Scanner;

public class sjfNP {
    public static void main(String[] args){
        int burst_time[],arrival_time[],process[],waiting_time[],tat[],i,j,n,total=0,pos,temp;
        float wait_avg,tat_avg;

        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of processes :");
        n=sc.nextInt();

        process=new int[n];
        burst_time=new int[n];
        arrival_time=new int[n];
        waiting_time=new int[n];
        tat=new int[n];
        // System.out.println("Enter burst Time : ");
        for(i=0;i<n;i++){
            System.out.println("Enter Arrival time for processes P"+(i+1)+": ");
            arrival_time[i]=sc.nextInt();
            System.out.println("Enter Burst time for processes P"+(i+1)+": ");
            burst_time[i]=sc.nextInt();
            process[i]=i+1;
        }

        //Sorting
        for(i=0;i<n;i++){
            pos=i;
            for(j=i+1;j<n;j++){
                if(burst_time[j]<burst_time[pos])
                    pos=j;
            }
            temp=burst_time[i];
            burst_time[i]=burst_time[pos];
            burst_time[pos]=temp;

            temp=process[i];
            process[i]=process[pos];
            process[pos]=temp;
        }

        //First process has 0 waiting time 
        waiting_time[0]=0;

        //Calculate waiting time 

        for(i=0;i<n;i++){
            waiting_time[i]=0;
            for(j=0;j<i;j++){
                waiting_time[i]+=burst_time[j];
            }
            total+=waiting_time[i];
        }
        wait_avg=(float)total/n;
        total=0;

        for(i=0;i<n;i++){
            tat[i]=burst_time[i]+waiting_time[i];
            total+=tat[i];
        }
        tat_avg=(float)total/n;
        total=0;

        System.out.println("Process\tArrival time\tBurst Time\tWaiting time\tTurn around time");

        for(i=0;i<n;i++){
            System.out.println("P"+(i+1)+"\t"+arrival_time[i]+"\t\t"+burst_time[i]+"\t\t"+waiting_time[i]+"\t\t"+tat[i]);
        }

        System.out.println("\nAverage Waiting time : "+wait_avg);
        System.out.println("\nAverage Turn around time : "+tat_avg);
    }
}
