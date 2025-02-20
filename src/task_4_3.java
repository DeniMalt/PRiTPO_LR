import java.util.*;



class Job {
    int s, t, id;

    public Job(int s, int t, int id) {
        this.s = s;
        this.t = t;
        this.id = id;
    }
}


public class task_4_3 {
    public static class MyFun implements Comparator<Job> {
        public int compare(Job a, Job b) {
            if (a.s * b.t == b.s * a.t) return a.id - b.id;
            return a.s * b.t - b.s * a.t;
        }
    }

    public static void main(String[] args) {
        Scanner con = new Scanner(System.in);
        int count_blocks = con.nextInt();
        boolean scanner_closed = false;

        while (!scanner_closed && con.hasNext()) {
            int n = con.nextInt();
            ArrayList<Job> jobs = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                int s = con.nextInt();
                int t = con.nextInt();
                jobs.add(new Job(s, t, i));
            }

            jobs.sort(new MyFun());

            for (int i = 0; i < n; i++)
                System.out.print(jobs.get(i).id + " ");
            System.out.println();

            count_blocks -= 1;
            if (count_blocks == 0) {
                con.close();
                scanner_closed = true;
            }
        }
    }
}