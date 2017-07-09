public class Agent{
    /* environment */
    private Environment env;
    /* Q value */
    private double[][] Q; // Q[state][action]
    
    /* constructor */
    public Agent(Environment env){
	/* set envirionment, parameters and initialize Q value */
	this.env = env;
	init_Q();
    }
    
    /* learning agent */
    public void learn(int epoch, double epsilon, double gamma, double alpha){
	/* reward per epoch */
	int[] rewards = new int[epoch];
	
	/* learn until epoch */
	for(int i = 0; i < epoch; i++){
	    /* total reward */
	    int total_reward = 0;
	    /* current state */
	    int state = env.reset();
	    
	    /* act until TMAX */
	    for(int t = 0; t < env.tmax; t++){
		/* choose action */
		int[] actions = env.observe_action(state);
		int action = choose_action(actions);
		/* observe next state */
		int next_state = env.observe_state(state, action);
		/* observe reward */
		int reward = env.observe_reward(state, action);
		/* max Q value at next state */
		double next_Q_max = max(Q[next_state]);
		/* update Q value */
		Q[state][action] =
		    (1 - alpha) * Q[state][action] + alpha * (reward + gamma * next_Q_max);
		if(state == env.goal){
		    /* record total reward */
		    rewards[epoch] = total_reward; break;
		} else{
		    /* transition to next state */
		    state = next_state;
		}
	    }
	}
    }
    /* initialize Q value */
    private void init_Q(){
	for(int i = 0; i < env.state_n; i++)
	    for(int j = 0; j < env.action_n; j++)
		Q[i][j] = 0.0;
    }
    /* choose action by epsilon-greedy method */
    private int choose_action(int[] actions){
	int action_index = new java.util.Random().nextInt(actions.length);
	return actions[action_index];
    }
    /* test agent */
    public void test(){
	/* total reward */
	int total_reward = 0;
	/* current state */
	int state = 0;
	/* act until TMAX */
	for(int t = 0; t < env.tmax; t++){
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
		System.out.print(Q[i][j] + " ");
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
