import processing.core.PGraphics;
import processing.core.PVector;
import java.util.ArrayList;

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
  */
  public StageComponentRenderContext(PGraphics render, float screenScale, int camX, int camY, int multyplayerMode, ArrayList<Boolean> variables, Player currentPlayer, DynamicModifier<String> displayTextSetter, DynamicModifier<Integer> displayTextTimeupdate,
  boolean usePressed,DynamicModifier<Boolean> resetUsePressedButton, int stageIndex, DynamicModifier<Integer> setStageIndexFuncion,DynamicModifier<PVector> setPlayerPositionFunction,DynamicModifier<Integer> updateGlitchEffectTimeFunction, DynamicAction resetSelectionFuncion){
    this.render = render;
    scale = screenScale;
    cameraOffset = new PVector(camX,camY);
    this.multyplayerMode = multyplayerMode;
    this.variables = variables;
    player2DHitbox = currentPlayer.getHitBox2D(0,0);
    player3DHitbox = currentPlayer.getHitBox3D(0,0,0);
    this.displayTextSetter = displayTextSetter;
    this.displayTextTimeupdate = displayTextTimeupdate;
    this.usePressed = usePressed;
    this.resetUsePressedButton = resetUsePressedButton;
    this.stageIndex = stageIndex;
    this.setStageIndexFuncion = setStageIndexFuncion;
    this.setPlayerPositionFunction = setPlayerPositionFunction;
    this.updateGlitchEffectTimeFunction = updateGlitchEffectTimeFunction;
    this.resetSelectionFuncion = resetSelectionFuncion;
  }
  /**Create a new stage component render context from another with a diffrent renderer
  @param render The new renderer to use
  @param source The source of all other required data
  */
  public StageComponentRenderContext(PGraphics render, StageComponentRenderContext source){
    this.render = render;
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
  /*
  make assets functions static
  */
  
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
    return new PVector((x-cameraX())*scale(),(y-cameraY())*scale());
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
}
