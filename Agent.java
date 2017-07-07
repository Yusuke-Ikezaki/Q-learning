public abstract class Agent{
  /* environment */
  private Environment env;
  /* discount rate */
  private double gamma;
  /* learning rate */
  private double alpha;
  /* number of action */
  private int tmax;
  /* number of learning */
  private int epoch;
  /* Q value */
  private double[][] Q; // Q[state][action]

  /* constructor */
  public Agent(Environment env, double gamma, double alpha, int tmax, int epoch){
    /* set envirionment and parameters */
    this.env = env;
    this.gamma = gamma;
    this.alpha = alpha;
    this.tmax = tmax;
    this.epoch = epoch;
  }

  /* learning agent */
  public void learn(){
    /* initialize Q value */
    init_Q();
    /* learn until epoch */
    for(int i = 0; i < epoch; i++){
      /* current state */
      int state = 0;
      /* act until TMAX */
      for(int t = 0; t < tmax; t++){
        /* choose action */
        int action = choose_action();
        /* observe next state */
        int next_state = env.observe_state(state, action);
        /* observe reward */
        int reward = env.observe_reward(state, action);
        /* max Q value at next state */
        double next_Q_max = max(Q[next_state]);
        /* update Q value */
        Q[state][action] = 
          (1 -alpha) * Q[state][action] + alpha * (reward + gamma * next_Q_max);
        /* transition to next state */
        state = next_state;
      }
    }
  }
  /* initialize Q value */
  private void init_Q(){
    for(int i = 0; i < env.s_num; i++)
      for(int j = 0; j < env.a_num; j++)
        Q[i][j] = 0.0;
  }
  /* choose action by epsilon-greedy method */
  private int choose_action(){
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
    for(int t = 0; t < tmax; t++){
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
