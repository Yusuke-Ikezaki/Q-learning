public class Agent{
  /* environment */
  private Environment env;
  /* discount rate */
  private static final double GAMMA = 0.9;
  /* learning rate */
  private static final double ALPHA = 0.1;
  /* number of search */
  private static final int TMAX = 1000000;
  /* Q value */
  private double[][] Q = {{0.0, 0.0},
                          {0.0, 0.0},
                          {0.0, 0.0},
                          {0.0, 0.0}};

  /* constructor */
  public Agent(Environment env){
    /* set envirionment */
    this.env = env;
  }

  /* learning agent */
  public void learn(){
    /* current state */
    int state = 0;
    /* act until TMAX */
    for(int t = 0; t < TMAX; t++){
      /* choose action */
      int action = select_action();
      /* observe next state */
      int next_state = env.observe_state(state, action);
      /* observe reward */
      int reward = env.observe_reward(state, action);
      /* max Q value at next state */
      double next_Q_max = max(Q[next_state]);
      /* update Q value */
      Q[state][action] = 
        (1 -ALPHA) * Q[state][action] + ALPHA * (reward + GAMMA * next_Q_max);
      /* transition to next state */
      state = next_state;
    }
  }
  /* choose action by epsilon-greedy method */
  private int select_action(){
    /* return 0 or 1 */
    return new java.util.Random().nextInt(2);
  }
  /* test agent */
  public void test(){
    /* total reward */
    int total_reward = 0;
    /* current state */
    int state = 0;
    /* act until TMAX */
    for(int t = 0; t < TMAX; t++){
      /* choose action */
      int action = argmax(Q[state]);
      /* observe next state */
      int next_state = env.observe_state(state, action);
      /* observe reward */
      int reward = env.observe_reward(state, action);
      /* transition to next state */
      state = next_state;
      /* add reward */
      total_reward += reward;
    }
    /* output reward */
    System.out.println("total reward");
    System.out.println(total_reward);
  }
  /* output Q value */
  public void print_Q(){
    System.out.println("Q Value");
    for(int i = 0; i < Q.length; i++){
      for(int j = 0; j < Q[i].length; j++)
        System.out.print(Q[i][j]+" ");
      System.out.println();
    }
  }
  /* find max value */
  public double max(double[] array){
    return array[argmax(array)];
  }
  /* find index of max value */
  public int argmax(double[] array){
    int max_index = 0;
    double max = array[0];
    for(int i = 0; i < array.length; i++)
      if(array[i] > max){
        max = array[i];
        max_index = i;
      }
    return max_index;
  }
}
