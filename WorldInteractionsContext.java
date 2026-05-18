import java.util.ArrayList;
/**Context for world interactions
*/
public class WorldInteractionsContext implements ContextBase{
  
  /**Create context of a world interaction
  @param multyplayerMode The current multyplayer mode
  @param groupProvider The current levels group provider
  @param currentNumberOfPlayers The current number of players in this session
  @param componentStageIndex The index of the stage this component is in
  @param players All the players
  @param variables The current level's varbales
  */
  public WorldInteractionsContext(int multyplayerMode, DynamicProvider<ArrayList<Group>> groupProvider, int currentNumberOfPlayers, int componentStageIndex, Player[] players, ArrayList<Boolean> variables){
    this.multyplayerMode = multyplayerMode;
    this.groupProvider = groupProvider;
    this.currentNumberOfPlayers = currentNumberOfPlayers;
    this.componentStageIndex = componentStageIndex;
    this.players = players;
    this.variables = variables;
  }
  
  /**The current level's multyplayer mode
  */
  private int multyplayerMode;
  /**Access to the groups of the current level
  */
  private DynamicProvider<ArrayList<Group>> groupProvider;
  /**The current number of players in this game
  */
  private int currentNumberOfPlayers;
  /**The index of the stage this component is in
  */
  private int componentStageIndex;
  /**The players
  */
  private Player[] players;
  /**The level's boolean varibles
  */
  private ArrayList<Boolean> variables;
  /**Get the current multyplayer mode
  @return The current multyplayer mode
  */
  public int getMultyPlayerMode(){
    return multyplayerMode;
  }
  
  /**Get the group forvider for thew current level
  @return The group provider for the current level
  */
  public DynamicProvider<ArrayList<Group>> getGroupProvider(){
    return groupProvider;
  }
  
  /**Get the current number of players in the game
  @return The number of players currently connected to the game
  */
  public int getCurrentNumberOfPlayers(){
    return currentNumberOfPlayers;
  }
  
  /**Get the index this component is in
  @return The index of the stage this component is in
  */
  public int getComponentStageIndex(){
    return componentStageIndex;
  }
  
  /**Get the players
  @return A refrence to the games current player list
  */
  public Player[] getPlayers(){
    return players;
  }
  
  /**Get the varaibles for the current level
  @return A refrence to the variables for the current level
  */
  public ArrayList<Boolean> getVariables(){
    return variables;
  }
}
