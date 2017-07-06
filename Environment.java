public class Environment{
  /* reward */
  private int[][] r = {{-10, 2},
                       {-10, 2},
                       {-10, 2},
                       {100, 2}};
  /* transition probability */
  private double[][] p = {{0.8, 1.0},
                          {0.5, 1.0},
                          {0.8, 1.0},
                          {1.0, 1.0}};
  /* next state */
  private int[][] t = {{1, 0},
                       {2, 0},
                       {3, 1},
                       {3, 2}};

  /* observe reward */
  public int observe_reward(int state, int action){
    return r[state][action];
  }
  /* observe next state */
  public int observe_state(int state, int action){
    double rand = Math.random();
    /* policy 1 */
    if(rand < p[state][action]) return t[state][action];
    /* policy 2 */
    else return state;
  }
}