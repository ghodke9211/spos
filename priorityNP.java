import java.util.*;
class Process{
    int pid,priority,burstTime,waitingTime,turnaroundTime;

    Process(int pid,int burstTime,int priority){
        this.pid=pid;
        this.burstTime=burstTime;
        this.priority=priority;
    }
}

public class priorityNP {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter no of processes : ");
        int n=sc.nextInt();

        Process[] processes=new Process[n];

        for(int i=0;i<n;i++){
            System.out.println("Enter Burst Time and Priority for Process p"+(i+1));
            int bt=sc.nextInt();
            int pr=sc.nextInt();
            processes[i]=new Process(i+1,bt,pr);

        }

        Arrays.sort(processes,Comparator.comparingInt(p->p.priority));

        processes[0].waitingTime=0;
        processes[0].turnaroundTime=processes[0].burstTime;

        for(int i=1;i<n;i++){
            processes[i].waitingTime=processes[i-1].waitingTime+processes[i-1].burstTime;
            processes[i].turnaroundTime=processes[i].waitingTime+processes[i].burstTime;
        }
        int totalWT=0,totalTAT=0;
        System.out.println("PID\tBT\tPriority\tWT\tTAT");

        for(Process p:processes){
            totalWT+=p.waitingTime;
            totalTAT+=p.turnaroundTime;

            System.out.println(p.pid+"\t"+p.burstTime+"\t"+p.priority+"\t\t"+p.waitingTime+"\t"+p.turnaroundTime);
        }

        System.out.println("\nAverage waiting time : "+(float)totalWT/n);
        System.out.println("\nAverage Turn around time : "+(float)totalTAT/n);

    }

}
