import java.util.*;

class Main {
  public static void main (String[] args) {
    Scheduler test = new Scheduler();
    test.parseInput();
    test.run();
  }
}

class Scheduler {
  private Queue<Job> _pq;
  private List<Worker> _workers;

  public Scheduler () {
    JobComparator comp = new JobComparator();
    this._pq = new PriorityQueue<Job>(10, comp);
    this._workers = new ArrayList<Worker>();
  }

  public void run() {
    int currentTime = 0;
    while(!_pq.isEmpty()) {
      Job j = _pq.poll();
      //fast forward if necessary
      if (j.canBeginTime > currentTime) currentTime = j.canBeginTime;

      //Assign each worker to the current job at the same time
      for (Worker w : _workers)
        System.out.println(currentTime + " " + w.name + " " + j.name);

      // Calculate time it takes to complete with all bots on
      int tasksPerBot = (j.tasks % _workers.size() == 0) ? j.tasks / _workers.size() : j.tasks / _workers.size() + 1;
      int timeToComplete = tasksPerBot * j.taskTime;

      currentTime += timeToComplete;
    }
  }

  public void parseInput() {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      if (!in.hasNext()) break;
      String nextLine = in.next();
      if (nextLine.equals("job")) {
        String name = in.next();
        int tasks = in.nextInt();
        int taskTime = in.nextInt();
        int canBeginTime = in.nextInt();
        int priority = in.nextInt();
        _pq.add(new Job(name, tasks, taskTime, canBeginTime, priority));
      } else if (nextLine.equals("worker")) {
        String name = in.next();
        _workers.add(new Worker(name));
      }
    }
  }

  public void printJobs() {
    while(!_pq.isEmpty()) {
      Job j = _pq.poll();
      System.out.println("name: "+ j.name);
      System.out.println("tasks: "+ j.tasks);
      System.out.println("taskTime: "+ j.taskTime);
      System.out.println("canBeginTime: "+ j.canBeginTime);
      System.out.println("priority: "+ j.priority);
    }
  }

  public void printWorkers() {
    for (Worker w : _workers) {
      System.out.println(w.name);
    }
  }
}

class Job {
  public String name;
  public int tasks;
  public int taskTime;
  public int canBeginTime;
  public int priority;

  public Job (String name, int tasks, int taskTime, int canBeginTime, int priority) {
    this.name = name;
    this.tasks = tasks;
    this.taskTime = taskTime;
    this.canBeginTime = canBeginTime;
    this.priority = priority;
  }
}

class Worker {
  public String name;

  public Worker (String name) {
    this.name = name;
  }
}

class JobComparator implements Comparator<Job> {
  @Override
  public int compare(Job x, Job y) {
    // We're going to make this the lowest start time for now
    return x.canBeginTime - y.canBeginTime;
  }
}
