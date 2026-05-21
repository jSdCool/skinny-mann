//Pulse
import processing.core.*;
import processing.data.*;
import java.util.ArrayList;
/**Logic components that converts an incoming singla into a single tick pulse
*/
public class Pulse extends LogicComponent {
  
  public static final Identifier ID = new Identifier("pulse");
  
  boolean prevousState=false;
  /**Place a new pulse logic component
  @param context The context for the placement
  */
  public Pulse(LogicCompoentnPlacementContext context) {
    super(context.getX(), context.getY(), "pulse", context.getLogicBoard());
  }
  /**Create a new pulse logic component from saved json data
  @param data The saved json data
  */
  public Pulse(JSONObject data) {
    super(data.getFloat("x"), data.getFloat("y"), "pulse", data.getJSONArray("connections"));
  }
  /**Create a pulse component from serialized binarry data
  @param iterator The source of the data
  */
  public Pulse(SerialIterator iterator){
    super(iterator);
  }
  /**renders the logic component a long with its I/O terminals
  */
  @Override
  public void draw(LogicComponentRenderContext context) {
    super.draw(context);
    context.render.fill(0);
    context.render.textSize(context.scale(15));
    context.render.textAlign(PConstants.LEFT, PConstants.CENTER);
    PVector inputPos = context.scaleCoord(x+5,y+16);
    context.render.text("input", inputPos.x, inputPos.y);
    PVector invertPos = context.scaleCoord(x+5,y+56);
    context.render.text("invert", invertPos.x,invertPos.y);
  }
  /**The function where the logic/functionality of this component is execuated
  */
  @Override
  public void tick(LogicComponentTickingContext context) {
    if (inputTerminal2) {//invert terminal
      outputTerminal =! (inputTerminal1 && !prevousState);//if invert is activated then set the output high nles a pulse comes in
    } else {
      outputTerminal = inputTerminal1 && !prevousState;//if the invert is deactivated then set the output low untill a pulse comes
    }
    prevousState=inputTerminal1;
  }
  /**Convert this component to a byte representation that can be sent over the network or saved to a file.<br>
  @return This component as a binarry representation
  */
  @Override
  public SerializedData serialize() {
    SerializedData data = new SerializedData(id());
    serialize(data);
    return data;
  }
  /**Get the id of this objet
  @return The Identifier representing this object
  */
  @Override
  public Identifier id() {
    return ID;
  }
}
