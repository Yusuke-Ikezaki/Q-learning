public class Qlearning{
  public static void main(String[] args){
    /* create environment */
    Environment env = new Environment();
    /* create agent */
    Agent agent = new Agent(env);
    /* learning */
    agent.learn();
    /* test */
    agent.test();
  }
}    