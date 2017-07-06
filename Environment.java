public class Environment{
  /* reward */
  /* r[state][action] */
  private int[][] r;
  /* transition probability */
  /* p[state][action] */
  private double[][] p;
  /* next state */
  /* t[state][action] */
  private int[][] t;

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