//XorGate
import processing.core.*;
import processing.data.*;
import java.util.ArrayList;

class XorGate extends LogicComponent {
  
  public static final Identifier ID = new Identifier("XOR");
  
  XorGate(LogicCompoentnPlacementContext context) {
    super(context.getX(), context.getY(), "XOR", context.getLogicBoard());
  }

  XorGate(JSONObject data) {
    super(data.getFloat("x"), data.getFloat("y"), "XOR", data.getJSONArray("connections"));
  }
  
  public XorGate(SerialIterator iterator){
    super(iterator);
  }

  void tick() {
    outputTerminal=inputTerminal1!=inputTerminal2;
  }
  
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
