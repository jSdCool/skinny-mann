//NOrGate
import processing.core.*;
import processing.data.*;
import java.util.ArrayList;

class NOrGate extends LogicComponent {
  
  public static final Identifier ID = new Identifier("NorGate");
  
  NOrGate(LogicCompoentnPlacementContext context) {
    super(context.getX(), context.getY(), "NOR", context.getLogicBoard());
  }
  NOrGate(JSONObject data) {
    super(data.getFloat("x"), data.getFloat("y"), "NOR", data.getJSONArray("connections"));
  }
  
  public NOrGate(SerialIterator iterator){
    super(iterator);
  }

  void tick() {
    outputTerminal=!(inputTerminal1||inputTerminal2);
  }
  
  //
  @Override
  public SerializedData serialize() {
    SerializedData data = new SerializedData(id());
    serialize(data);
    return data;
  }
  
  @Override
  public Identifier id() {
    return ID;
  }
}
