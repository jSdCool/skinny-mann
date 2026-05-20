import processing.core.*;
import processing.data.*;
import java.util.ArrayList;
/**A logic coponent to set the value of a level variable
*/
public class SetVariable extends LogicOutputComponent implements Configurable{
  
  public static final Identifier ID = new Identifier("set_var");
  
  int variableNumber=0;
  /**Place a new set varaible
  @param context The context for the placement
  */
  public SetVariable(LogicCompoentnPlacementContext context) {
    super(context.getX(), context.getY(), "set var", context.getLogicBoard());
  }
  /**Create a new set variable from saved json data
  @param data The saved json data
  */
  public SetVariable(JSONObject data) {
    super(data.getFloat("x"), data.getFloat("y"), "set var", data.getJSONArray("connections"));
    variableNumber=data.getInt("variable number");
  }
  @Override
  protected void commonInit(float uiScale, PGraphics render){
    super.commonInit(uiScale,render);
    button.setText("  Set var b"+variableNumber);
  }
  /**Create a set variable from serialized binarry data
  @param iterator The source of the data
  */
  public SetVariable(SerialIterator iterator){
    super(iterator);
    variableNumber = iterator.getInt();
  }
  /**The function where the logic/functionality of this component is execuated
  */
  public void tick() {
    if (inputTerminal2)
      source.level.variables.set(variableNumber, inputTerminal1);
  }
  /**renders the logic component a long with its I/O terminals
  */
  @Override
  public void draw(LogicComponentRenderContext context) {
    super.draw(context);
    context.render.fill(0);
    context.render.textSize(context.scale(15));
    context.render.textAlign(PConstants.LEFT, PConstants.CENTER);
    PVector dataPos = context.scaleCoord(x+5,y+16);
    context.render.text("data", dataPos.x, dataPos.y);
    PVector setPos = context.scaleCoord(x+5,y+56);
    context.render.text("set", setPos.x, setPos.y);
  }
  /**Get a JSONObject representation of this component that can be saved to a file
  @return JSONObject representation of this object
  */
  @Override
  public JSONObject save() {
    JSONObject component=super.save();
    component.setInt("variable number", variableNumber);
    return component;
  }
  /**Convert this component to a byte representation that can be sent over the network or saved to a file.<br>
  @return This component as a binarry representation
  */
  @Override
  public SerializedData serialize() {
    SerializedData data = new SerializedData(id());
    serialize(data);
    data.addInt(variableNumber);
    return data;
  }
  /**Get the id of this objet
  @return The Identifier representing this object
  */
  @Override
  public Identifier id() {
    return ID;
  }
  
  /**Get the properties that can be configured on this component
  @return An array of the properties that can be configured
  */
  @Override
  public Property[] getProperties(){
    return new Property[]{new BooleanVariableProperty(() -> variableNumber, (value) -> {variableNumber=value;button.setText("  Set var b"+variableNumber);;},"Current Variable")};
  }
}
