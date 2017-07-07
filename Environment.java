public class Environment{
  /* reward */
  private int[][] r; // r[state][action]
  /* transition probability */
  private double[][] p; // p[state][action]
  /* next state */
  private int[][] t; // t[state][action]
  /* number of state */
  private int s_num;
  /* number of action */
  private int a_num;

  /* constructor */
  public Environment(int[][] r){
  }

  /* observe reward */
  public int observe_reward(int state, int action){
    return r[state][action];
  }
  /* observe next state */
  public int observe_state(int state, int action){
    /* random number between 0.0 and 1.0 */
    double rand = Math.random();
    /* success act */
    if(rand < p[state][action]) return t[state][action];
    /* failure act */
    else return t[state][new java.util.Random().nextInt(a_num)];
  }
}