import processing.core.*;
/**The UI definitions for editing compoent properties in the toolbox.<br>
NOTE: all sub classes are expected to effectivly be singltons where their only instance is created by the tool box when it opens
@param <T> The property that this ui is designed to configure
*/
public abstract class PropertyConfigUi<T extends Property<?>>{
  
  public static final int START_CONFIG_Y = 256;
  public static final int SLOT_HEIGHT = 100;
  public static final int TEXT_SIZE = 25;
  
  /**The surface that the UI will be renderd to
  */
  protected PGraphics render;
  /**The tool box instance that will be renderd to and the player will interact with. Note: this will hopefuly be removed in the future
  */
  protected skiny_mann.ToolBox toolbox;
  
  protected UiFrame ui;
  
  /**Create a new property config ui
  @param render The surface to render to
  @param toolbox the tool box window
  */
  public PropertyConfigUi(PGraphics render, skiny_mann.ToolBox toolbox, UiFrame toolBoxUi){
    this.render = render;
    this.ui = toolBoxUi;
  }
  
  /**Render the configuration for this propery at the given slot
  @param slotId The index of the slot to display this property
  @parma property The specific property that is being renderd
  */
  public abstract void draw(int slotId, T property);
  
  /**Process mouse clicks that happen while a property is being configured.
  @param slotId The index of the slot to display this property
  @parma property The specific property that is being procesed
  */
  public abstract void mouseClicked(int slotId, T property);
  
  /**Process mouse pressed events that happen while a property is being configured.
  @param slotId The index of the slot to display this property
  @parma property The specific property that is being procesed
  */
  public void mousePressed(int slotId, T property){}
  
  /**Process mouse released that happen while a property is being configured.
  @param slotId The index of the slot to display this property
  @parma property The specific property that is being procesed
  */
  public void mouseReleased(int slotId, T property){}
  
  /**Process key pressed events that happen while a property is being configured.
  @param slotId The index of the slot to display this property
  @parma property The specific property that is being procesed
  */
  public void keyPressed(int slotId, T property){}
  
  /**Process key released that happen while a property is being configured.
  @param slotId The index of the slot to display this property
  @parma property The specific property that is being procesed
  */
  public void keyReleased(int slotId, T property){}
  
  /**Process key typed events that happen while a property is being configured.
  @param slotId The index of the slot to display this property
  @parma property The specific property that is being procesed
  */
  public void keyTyped(int slotId, T property){}
  
  /**A functional interface that represents the constructor for a Property config ui.<br>
  used for where the constructor is needed to be passed in for registratcion
  */
  public interface PropConfigUiFactory{
    /**Create a new property config ui
    @param render The surface to render to
    @param toolbox the tool box window
    */
    PropertyConfigUi<?> create(PGraphics render, skiny_mann.ToolBox toolbox,UiFrame toolBoxUi);
  }
}
