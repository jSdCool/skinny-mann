import processing.core.*;
import java.util.ArrayList;
/**Context for loading a level
*/
public class LevelLoadContext implements ContextBase{
  
  /**
  @param author A funciton to set the author
  @param setStageIndex A funcion to set the current stage index
  @param players The current players
  @param setRespawnLocation A function to set the respawn location
  @param setRespawnStage A function to set the respawn stage
  @param gameVersionCompatabilityCheck The game version check function
  @param render The main renderer
  @param scale The current ui scale
  @param initCoins A function to initialize the levels coins
  @param coins Dynamic access to the level's coins
  @param currentPlayer The index of the current player
  @param set3DMode  A function to set the 3D mode
  */
  public LevelLoadContext(DynamicModifier<String> author, DynamicModifier<Integer> setStageIndex, Player[] players, DynamicModifier<PVector> setRespawnLocation, DynamicModifier<Integer> setRespawnStage, DynamicGetter<Boolean,String> gameVersionCompatabilityCheck, PGraphics render, float scale, DynamicModifier<Integer> initCoins, DynamicProvider<ArrayList<Boolean>> coins, int currentPlayer, DynamicModifier<Boolean> set3DMode){
    this.author = author;
    this.setStageIndex = setStageIndex;
    this.players = players;
    this.setRespawnLocation = setRespawnLocation;
    this.setRespawnStage = setRespawnStage;
    this.gameVersionCompatabilityCheck = gameVersionCompatabilityCheck;
    this.render = render;
    this.scale = scale;
    this.initCoins = initCoins;
    this.coins = coins;
    this.currentPlayer = currentPlayer;
    this.set3DMode = set3DMode;
  }
  /**A function to set the current author
  */
  private DynamicModifier<String> author;
  /**A function to set the stage index
  */
  private DynamicModifier<Integer> setStageIndex;
  /**The players
  */
  private Player[] players;
  /**A funcion to set the respawn location
  */
  private DynamicModifier<PVector> setRespawnLocation;
  /**A funcion to set the repawn stage
  */
  private DynamicModifier<Integer> setRespawnStage;
  /**The function that checks this level is comatable with this game version
  */
  private DynamicGetter<Boolean,String> gameVersionCompatabilityCheck;
  /**The main window
  */
  public final PGraphics render;
  /**The current Ui scale
  */
  private float scale;
  /**A function to initialize the coins for the level
  */
  private DynamicModifier<Integer> initCoins;
  /**A function to get the up to date coins for a level
  */
  private DynamicProvider<ArrayList<Boolean>> coins;
  /**The index of the current player
  */
  private int currentPlayer;
  /**A function to set the state of 3D mode
  */
  private DynamicModifier<Boolean> set3DMode;
  
  /**Get the currently set author
  @param bewAuthor The name of the author
  */
  public void setAuthor(String newAuthor){
    author.set(newAuthor);
  }
  /**Set the current stage index
  @param index The index of the current stage
  */
  public void setStageIndex(int index){
    setStageIndex.set(index);
  }
  /**Get the current players
  @return The game's players
  */
  public Player[] getPlayers(){
    return players;
  }
  /**Set the initial respawn location
  @param x The x position to respawn
  @param y The y position to respawn
  @param stage The index of the stage to repsawn in
  */
  public void setRespawnLocation(float x, float y, int stage){
    setRespawnLocation.set(new PVector(x,y));
    setRespawnStage.set(stage);
  }
  /**Chcek that this level is compatable with this game version
  @param version The level's game version
  @return true if the level is compatable
  */
  public boolean gameVersionCompatabilityCheck(String version){
    return gameVersionCompatabilityCheck.get(version);
  }
  /**Get the current Ui scale
  */
  public float scale(){
    return scale;
  }
  /**scale a value by the current Ui scale
  @param f The value to scale
  @return f scaled by the Ui scale
  */
  public float scale(float f){
    return scale * f;
  }
  /**Initialize the coins for the level
  @param numberOfCoins The number of coins in this level
  */
  public void initCoins(int numberOfCoins){
    initCoins.set(numberOfCoins);
  }
  /**Get the current array of coins in the level
  @return The list of coins states
  */
  public DynamicProvider<ArrayList<Boolean>> getCoins(){
    return coins;
  }
  /**Get the index of the current player for this client
  @return the current player index
  */
  public int getCurrentPlayer(){
    return currentPlayer;
  }
  /**Set the current state of 3D mode for this client
  @param mode the new 3D mode state of this client
  */
  public void set3DMode(boolean mode){
    set3DMode.set(mode);
  }
}
