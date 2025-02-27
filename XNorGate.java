//XNorGate
import processing.core.*;
import processing.data.*;
import java.util.ArrayList;

class XNorGate extends LogicComponent {
  
  public static final Identifier ID = new Identifier("XnorGate");
  
  XNorGate(LogicCompoentnPlacementContext context) {
    super(context.getX(), context.getY(), "XNOR", context.getLogicBoard());
  }

  XNorGate(JSONObject data) {
    super(data.getFloat("x"), data.getFloat("y"), "XNOR", data.getJSONArray("connections"));
  }
  
  public XNorGate(SerialIterator iterator){
    super(iterator);
  }

  void tick() {
    outputTerminal=!(inputTerminal1!=inputTerminal2);
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
