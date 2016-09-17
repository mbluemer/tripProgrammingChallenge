import java.util.*;

class Main {
  public static void main (String[] args) {

  }
}

class Scheduler {
  private Queue<Job> _pq;

  public Scheduler () {
    this._pq = new PriorityQueue<Job>();
  }

  public void parseInput() {
    Scanner in = new Scanner(System.in);
    if (in.next.equals("job")) {

    }
  }
}

class Job {
  public int tasks;
  public int taskTime;
  public int canBeginTime;
  public int priority;

  public Job (int tasks, int taskTime, int canBeginTime, int priority) {
    this.tasks = tasks;
    this.taskTime = taskTime;
    this.canBeginTime = canBeginTime;
    this.priority = priority;
  }
}

class JobComparator implements Comparator<Job> {
  @Override
  public int compare(Job x, Job y) {
    return x.priority - y.priority;
  }
}
