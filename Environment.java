public class Environment{
    /* number of state */
    public int state_n;
    /* number of action */
    public  int action_n;
    /* start state */
    public int start;
    /* goal state */
    public int goal;
    /* time limit */
    public int tmax;
    /* reward */
    private int[][] r; // r[state][action]
    /* transition probability */
    private double[][] p; // p[state][action]
    /* next state */
    private int[][] t; // t[state][action]
    
    /* constructor */
    public Environment(){
    }
    
    /* reset environment */
    public int reset(){
	return start;
    }
    /* observe action */
    public int[] observe_action(int state){
	return r[state];
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
	else return t[state][new java.util.Random().nextInt(action_n)];
    }
}
