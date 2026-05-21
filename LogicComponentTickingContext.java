import processing.core.*;
import java.util.ArrayList;
/**Context for ticking logic compoenents
*/
public class LogicComponentTickingContext implements ContextBase{
  /**Create context for ticking a logic component
  @param variables The current level's varaiables
  @param groupProvider The group provider from the current level
  @param clientIn3DMode If the client is currently in 3D mode
  @param set3DMode A function to be able to set the current state of 3D mode on this client
  @param stageSounds A funcion to get a stage sound
  */
  public LogicComponentTickingContext(ArrayList<Boolean> variables,  DynamicProvider<ArrayList<Group>> groupProvider, boolean clientIn3DMode, DynamicModifier<Boolean> set3DMode,DynamicGetter<StageSound,String> stageSounds){
    this.variables = variables;
    this.groupProvider = groupProvider;
    this.clientIn3DMode = clientIn3DMode;
    this.set3DMode = set3DMode;
    this.stageSounds = stageSounds;
  }
  
  /**The current level's variables
  */
  private ArrayList<Boolean> variables;
  /**The current level's group provider
  */
  private DynamicProvider<ArrayList<Group>> groupProvider;
  /**The current state of 3D mode
  */
  private boolean clientIn3DMode;
  /**A function to set 3D mode
  */
  private DynamicModifier<Boolean> set3DMode;
  /**Access to the stage sounds
  */
  private DynamicGetter<StageSound,String> stageSounds;
  
  
  
  /**Get the varaibles of the current level
  @return The level's variables
  */
  public ArrayList<Boolean> getVariables(){
    return variables;
  }
  /**Get the current level's group provider
  @return The group provider of the current level
  */
  public DynamicProvider<ArrayList<Group>> getGroupProvider(){
    return groupProvider;
  }
  /**Get the current state of 3D mode
  @return true if the client is currently in 3D mode
  */
  public boolean get3DMode(){
    return clientIn3DMode;
  }
  
  /**Set the state of 3D mode
  @param e3D The new state of 3D mode
  */
  public void set3DMode(boolean e3D){
    set3DMode.set(e3D);
  }
  
  /**Get the stage sound associated with the provided sound key
  @param soundKey The key name of the sound
  @return The stage sound corresponding to the key
  */
  public StageSound getStageSound(String soundKey){
    return stageSounds.get(soundKey);
  }
}
