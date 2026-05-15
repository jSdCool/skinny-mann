import processing.core.PGraphics;
import processing.core.PVector;
import java.util.ArrayList;
import java.util.HashMap;

/**Context infomration used by stage compnents to render content to the screene
*/
public class StageComponentRenderContext implements ContextBase{
  
  /**Create a new component render context
  @param render The buffer that components render to
  @param screenScale The render scale of the screen
  @param camX The 2D camera x position
  @param camY The 2D camera y position
  @param multyplayerMode The current level's multyplayer mode
  @param variables The current boolean variables for the current level
  @param currentPlayer The current player of this client
  @param displayTextSetter Function to set the current display test
  @param displayTextTimeupdate Function to update the display text until varaible to millis() + inputTime
  @param usePressed The state of the player's use action
  @param resetUsePressedButton A function to set the value of use pressed (E Pressed)
  @param stageIndex The index of the stage the player is currently on
  @param setStageIndexFuncion A funcion to change the stage index
  @param setPlayerPositionFunction A function to change the position of the current player
  @param updateGlitchEffectTimeFunction A function to update the glitch effect milliscond variable to millis() + inputTime
  @param resetSelectionFuncion A function to reset the level creator selection
  @param setRespawnPosFuncion A function to set where the player respawns
  @param setRespawnStageFunction A function to set what stage the player respawns in
  @param setCheckpointIn3DstageFuncion A function to set if the player respawns in 3D mode
  @param editingBlueprint If currently editing a blueprint
  @param coins The coins in the current level
  @param selectingBlueprint If currently selecting a blueprint for placement
  @param inLevelCreator If currenly in the level creator
  @param coinIncrementer A function to increase the number of collected coins
  @param levelComplete The current state of the level complete varaible
  @param setLevelCompleteFunction A function to set the level complete variable
  @param setEndReached A funcion to set the end reached variable
  @param levelCompleteLogicBoard The logic board in this level that is used for level complete functions
  @param soundHandler The sound handler used to play sounds
  @param levelSounds All the sounds loaded by the current level
  @param set3DMode A function to set if the player is in 3D mode
  @param setViewingItemContentFuncion A funcion to set if the player is viewing item content
  @param currentStageIndex The value of current stage index
  @param coinRotation The rotation of the 3D coins
  @param reset3DMovement A function to set WPressed and SPressed to false
  */
  public StageComponentRenderContext(PGraphics render, float screenScale, int camX, int camY, int multyplayerMode, ArrayList<Boolean> variables, Player currentPlayer, DynamicModifier<String> displayTextSetter, DynamicModifier<Integer> displayTextTimeupdate,
  boolean usePressed,DynamicModifier<Boolean> resetUsePressedButton, int stageIndex, DynamicModifier<Integer> setStageIndexFuncion,DynamicModifier<PVector> setPlayerPositionFunction,DynamicModifier<Integer> updateGlitchEffectTimeFunction, DynamicAction resetSelectionFuncion,
  DynamicModifier<PVector> setRespawnPosFuncion, DynamicModifier<Integer> setRespawnStageFunction, DynamicModifier<Boolean> setCheckpointIn3DstageFuncion, boolean editingBlueprint, ArrayList<Boolean> coins, boolean selectingBlueprint,boolean inLevelCreator, DynamicAction coinIncrementer,
  boolean levelComplete, DynamicModifier<Boolean> setLevelCompleteFunction, DynamicModifier<Boolean> setEndReached, LogicBoard levelCompleteLogicBoard, SoundHandler soundHandler, HashMap<String, StageSound> levelSounds, DynamicModifier<Boolean> set3DMode,
  DynamicModifier<Boolean> setViewingItemContentFuncion, int currentStageIndex, int coinRotation, DynamicAction reset3DMovement){
    this.render = render;
    scale = screenScale;
    cameraOffset = new PVector(camX,camY);
    this.multyplayerMode = multyplayerMode;
    this.variables = variables;
    player2DHitbox = currentPlayer.getHitBox2D(0,0);
    player3DHitbox = currentPlayer.getHitBox3D(0,0,0);
    this.player = currentPlayer;
    this.displayTextSetter = displayTextSetter;
    this.displayTextTimeupdate = displayTextTimeupdate;
    this.usePressed = usePressed;
    this.resetUsePressedButton = resetUsePressedButton;
    this.stageIndex = stageIndex;
    this.setStageIndexFuncion = setStageIndexFuncion;
    this.setPlayerPositionFunction = setPlayerPositionFunction;
    this.updateGlitchEffectTimeFunction = updateGlitchEffectTimeFunction;
    this.resetSelectionFuncion = resetSelectionFuncion;
    this.setRespawnPosFuncion = setRespawnPosFuncion;
    this.setRespawnStageFunction = setRespawnStageFunction;
    this.setCheckpointIn3DstageFuncion = setCheckpointIn3DstageFuncion;
    this.editingBlueprint = editingBlueprint;
    this.coins = coins;
    this.selectingBlueprint = selectingBlueprint;
    this.inLevelCreator = inLevelCreator;
    this.coinIncrementer = coinIncrementer;
    this.levelComplete = levelComplete;
    this.setLevelCompleteFunction = setLevelCompleteFunction;
    this.setEndReached = setEndReached;
    this.levelCompleteLogicBoard = levelCompleteLogicBoard;
    this.soundHandler = soundHandler;
    this.levelSounds = levelSounds;
    this.set3DMode = set3DMode;
    this.setViewingItemContentFuncion = setViewingItemContentFuncion;
    this.currentStageIndex = currentStageIndex;
    this.coinRotation = coinRotation;
    this.reset3DMovement = reset3DMovement;
  }
  /**Create a new stage component render context from another with a diffrent renderer
  @param render The new renderer to use
  @param source The source of all other required data
  */
  public StageComponentRenderContext(PGraphics render, StageComponentRenderContext source){
    this.render = render;
    //copy every field from the passed in context to this context
    scale = source.scale;
    cameraOffset = source.cameraOffset;
    this.multyplayerMode = source.multyplayerMode;
    this.variables = source.variables;
    player2DHitbox = source.player2DHitbox;
    player3DHitbox = source.player3DHitbox;
    this.displayTextSetter = source.displayTextSetter;
    this.displayTextTimeupdate = source.displayTextTimeupdate;
    this.usePressed = source.usePressed;
    this.resetUsePressedButton = source.resetUsePressedButton;
    this.stageIndex = source.stageIndex;
    this.setStageIndexFuncion = source.setStageIndexFuncion;
    this.setPlayerPositionFunction = source.setPlayerPositionFunction;
    this.updateGlitchEffectTimeFunction = source.updateGlitchEffectTimeFunction;
    this.resetSelectionFuncion = source.resetSelectionFuncion;
    this.setRespawnPosFuncion = source.setRespawnPosFuncion;
    this.setRespawnStageFunction = source.setRespawnStageFunction;
    this.setCheckpointIn3DstageFuncion = source.setCheckpointIn3DstageFuncion;
    this.editingBlueprint = source.editingBlueprint;
    this.coins = source.coins;
    this.selectingBlueprint = source.selectingBlueprint;
    this.inLevelCreator = source.inLevelCreator;
    this.coinIncrementer = source.coinIncrementer;
    this.levelComplete = source.levelComplete;
    this.setLevelCompleteFunction = source.setLevelCompleteFunction;
    this.setEndReached = source.setEndReached;
    this.levelCompleteLogicBoard = source.levelCompleteLogicBoard;
    this.soundHandler = source.soundHandler;
    this.levelSounds = source.levelSounds;
    this.player = source.player;
    this.set3DMode = source.set3DMode;
    this.setViewingItemContentFuncion = source.setViewingItemContentFuncion;
    this.coinRotation = source.coinRotation;
    this.reset3DMovement = source.reset3DMovement;
  }
  
  /**The buffer that components render to
  */
  public final PGraphics render;
  /**The scale objects should be scaled to
  */
  public final float scale;
  /**The position of the 2D camera. values have integer persision
  */
  private PVector cameraOffset;
  /**The multyplayer mode of the current level
  */
  private int multyplayerMode;
  /**The level's boolean varibles
  */
  private ArrayList<Boolean> variables;
  /**The current 2D hitbox of the plauer
  */
  private Collider2D player2DHitbox;
  /**The current 3D hitbox of the player
  */
  private Collider3D player3DHitbox;
  /**Function that sets the display text in the main class
  */
  private DynamicModifier<String> displayTextSetter;
  /**Function that sets the main classes display untill varibale to <imput time> from now
  */
  private DynamicModifier<Integer> displayTextTimeupdate;
  /**The value of the use input (E PRESSED)
  */
  private boolean usePressed;
  /**Funciton to reset use pressed once it has been used up
  */
  private DynamicModifier<Boolean> resetUsePressedButton;
  /**The index of the current stage
  */
  private int stageIndex;
  /**Function to set the index of the current stage
  */
  private DynamicModifier<Integer> setStageIndexFuncion;
  /**A fuction to reset the current level creator selction
  */
  private DynamicAction resetSelectionFuncion;
  /**A funcion to set the position of the current player
  */
  private DynamicModifier<PVector> setPlayerPositionFunction;
  /**A function that updates when the glitch effect should run to
  */
  private DynamicModifier<Integer> updateGlitchEffectTimeFunction;
  /**Funciton that sets the respawn position for the player
  */
  private DynamicModifier<PVector> setRespawnPosFuncion;
  /**Funcion that sets the index of the stage the player will respawn in
  */
  private DynamicModifier<Integer> setRespawnStageFunction;
  /**Function that sets if the player will respawn in 3D mode
  */
  private DynamicModifier<Boolean> setCheckpointIn3DstageFuncion;
  /**if currently editing a blueprint not a stage
  */
  private boolean editingBlueprint;
  /**The current level coin
  */
  private ArrayList<Boolean> coins;
  /**If currently in the process of palceing a blueprint
  */
  private boolean selectingBlueprint;
  /**If currently in the level creator
  */
  private boolean inLevelCreator;
  /**Function that increase the number of coins the player has by 1
  */
  private DynamicAction coinIncrementer;
  /**Wether the current level has been completed
  */
  private boolean levelComplete;
  /**Set the level compelet status function
  */
  private DynamicModifier<Boolean> setLevelCompleteFunction;
  /**Set end reached function
  */
  private DynamicModifier<Boolean> setEndReached;
  /**The current level's logic complete board
  */
  private LogicBoard levelCompleteLogicBoard;
  /**A refrence to the sound halder
  */
  private SoundHandler soundHandler;
  /**Refrence to all the sounds held by the level
  */
  private HashMap<String, StageSound> levelSounds;
  /**The current player
  */
  private Player player;
  /**Function that sets if the player is in 3D mode
  */
  private DynamicModifier<Boolean> set3DMode;
  /**Function to set if the player is currently viewing an item content
  */
  private DynamicModifier<Boolean> setViewingItemContentFuncion;
  /**not sure what the diffrence between this and stage index is but they are siginificantly diffrent
  */
  private int currentStageIndex;
  /**The cunber of degress the 3D coins are rotated in the Y axis
  */
  private int coinRotation;
  /**Resets the state of W and S pressed
  */
  private DynamicAction reset3DMovement;

  /**Get the render buffer;
  @return the graphics instance to render to
  */
  public PGraphics getRender(){
    return render;
  }
  /**Get the current scale of the UI
  @return The current scale of the ui
  */
  public float scale(){
    return scale;
  }
  /**Scale a value by the ui scale, used for 2D rendering
  @param x the value to scale
  @return the input value scaled by the scale value
  */
  public float scale(float x){
    return x*scale;
  }
  /**Get the x offset of teh 2D camera
  @return the 2D camera x value
  */
  public float cameraX(){
    return cameraOffset.x;
  }
  /**Get the y offset of teh 2D camera
  @return the 2D camera y value
  */
  public float cameraY(){
    return cameraOffset.y;
  }
  
  /**Scale a screen coordinate using both the camera offset and ui scale.
  Coodinate passed in will be transformed into the correct onscreen posiiton with the 2D camera taken into account.<br>
  The result of this funcion can directly be used in 2D rendering.
  @param x The x coord to scale
  @param y The y coord to scale
  @return The input coordinates scaled to their screen position.
  */
  public PVector scaleCoord(float x, float y){
    return new PVector((x-cameraX())*scale(),(y+cameraY())*scale());
  }
  
  /**Scale a screen coordinate using both the camera offset and ui scale.
  Coodinate passed in will be transformed into the correct onscreen posiiton with the 2D camera taken into account.<br>
  The result of this funcion can directly be used in 2D rendering.
  @param v the coordinate to scale;
  @return The input coordinates scaled to their screen position.
  */
  public PVector scaleCoord(PVector v){
    return scaleCoord(v.x,v.y);
  }
  /**Get the multyplayer mode of the current level. 1 = speed run(normal). 2 = co op
  @return The current multyplayer mode
  */
  public int getMultyplayerMode(){
    return multyplayerMode;
  }
  /**Get the logic boolean varibales for the current level.
  @return The list of boolean varaibles associated with the current level
  */
  public ArrayList<Boolean> getVariables(){
    return variables;
  }
  /**Get the current 2D hitbox of the current player
  @return The current hitbox of the player
  */
  public Collider2D get2DPlayerHitbox(){
    return player2DHitbox;
  }
  /**Get the current 3D hitbox of the current player
  @return The current hitbox of the player
  */
  public Collider3D get3DPlayerHitbox(){
    return player3DHitbox;
  }
  /**Set and display the display text
  @param text The text to display
  @param time how long to display it for
  */
  public void displayText(String text, int time){
    displayTextSetter.set(text);
    displayTextTimeupdate.set(time);
  }
  /**Get if the player is pressing the use button.<br>
  (E pressed)
  @return if used is pressed
  */
  public boolean usePressed(){
    return usePressed;
  }
  /**Set used pressed to false
  */
  public void resetUsedPressed(){
    usePressed = false;
    resetUsePressedButton.set(false);
  }
  /**Get the index of the stage the player is currently in
  @return the index of the current stage
  */
  public int getStageIndex(){
    return stageIndex;
  }
  /**Set the stage the player is currently in
  @param newIndex the new stage index
  */
  public void setStageIndex(int newIndex){
    setStageIndexFuncion.set(newIndex);
  }
  /**Clear what is currently selected in the level creator
  */
  public void resetSelection(){
    resetSelectionFuncion.go();
  }
  /**Set the position of the current player
  @param x The new x position of the player
  @param y The new y position of the player
  @param z The new z position of the player
  */
  public void setPlayerPosition(float x,float y,float z){
    setPlayerPositionFunction.set(new PVector(x,y,z));
  }
  
  /**Set the position of the current player
  @param v The new position of the player
  */
  public void setPlayerPosition(PVector v){
    setPlayerPositionFunction.set(v);
  }
  /**Display the glitch effect on the screen for a time
  @param time How long to display the effect for
  */
  public void glitchEffect(int time){
    updateGlitchEffectTimeFunction.set(time);
  }
  /**Set the position the player will respawn at
  @param x The x position to respawn
  @param y The y position to respawn
  @param z The z position to respawn
  */
  public void setRespawnPosition(float x,float y,float z){
    setRespawnPosFuncion.set(new PVector(x,y,z));
  }
  /**Set the stage the player will respawn in
  @param stage The stage to respawn in
  */
  public void setRespawnStage(int stage){
    setRespawnStageFunction.set(stage);
  }
  
  /**Set if the checkpoint was activated in 3D mode
  @param in3D if 3D mode is currenly active
  */
  public void setCheckpointIn3D(boolean in3D){
    setCheckpointIn3DstageFuncion.set(in3D);
  }
  /**Get if the user is currenly editing a blueprint
  @return true if editing a blueprint
  */
  public boolean isEditingBlueprint(){
    return editingBlueprint;
  }
  /**Get the current level coins
  @return the state of all the coins in the current level
  */
  public ArrayList<Boolean> coins(){
    return coins;
  }
  /**Get if the user is currently in the process of placing a blueprint
  @return true if the user is in the process of placing a blueprint
  */
  public boolean isSelcetingBlueprint(){
    return selectingBlueprint;
  }
  /**Get if the user is in the level creator
  @return true if the level creator is active
  */
  public boolean inLevelCreator(){
    return inLevelCreator;
  }
  /**Increase the number of collected coins by 1
  */
  public void incrementCoins(){
    coinIncrementer.go();
  }
  /**Get the current state of level complete
  @return If the current level has been completetd
  */
  public boolean getLevelComplete(){
    return levelComplete;
  }
  
  /**Sets level complete to true imedialy ending the level and allowing the player to move on
  */
  public void completeLevel(){
    setLevelCompleteFunction.set(true);
  }
  
  /**Sets end reached to true. This is used in multyplayer to indicate that this player has reached the finish line and may be waiting for other players to also finish the level.<br>
  Do not get this confused with level complete wich automaticaly ends the level when it is set.
  */
  public void setEndReached(){
    setEndReached.set(true);
  }
  /**Get the level complete logic board associated with the current level
  @return A logic board that is intened to be run on level completeion 
  */
  public LogicBoard getLevelCompleteLogicBoard(){
    return levelCompleteLogicBoard;
  }
  /**Gets the sound handler
  @return A refrence to the sound handler
  */
  public SoundHandler getSoundHandler(){
    return soundHandler;
  }
  /**Get a sound from the level
  @param key The sound key that identifies a sound
  @return A refrence to a sound to play.
  */
  public StageSound getLevelSound(String key){
    return levelSounds.get(key);
  }
  /**Set the player's z position
  @param newz The new z position of the player
  */
  public void setPlayerZpos(float newz){
    player.setZ(newz);
  }
  /**Set if the player is in 3D mode
  @param in3D true if the player should see 3D mode
  */
  public void set3DMode(boolean in3D){
    set3DMode.set(in3D);
  }
  /**Set if the player is viewing item content
  @param viewing true if the player is viewing item content
  */
  public void setViewingItemContent(boolean viewing){
    setViewingItemContentFuncion.set(viewing);
  }
  /**Get the value of current stage index
  @return no idea what the diffrence between this and stage index is but there is a functional diffrence
  */
  public int getCurrentStageIndex(){
    return currentStageIndex;
  }
  /**Get the current roation of the 3D coins
  @return the number of degrees the 3D coins are rotated in the y axis
  */
  public int getCoinRotation(){
    return coinRotation;
  }
  /**Sets w pressed and s pressed to false
  */
  public void reset3DMovement(){
    reset3DMovement.go();
  }
}
