import processing.core.*;
import processing.data.*;
import java.util.ArrayList;

class SetVisibility extends LogicOutputComponent {
  
  public static final Identifier ID = new Identifier("set_visable");
  
  int groupNumber=0;
  boolean reText = false;
  SetVisibility(LogicCompoentnPlacementContext context) {
    super(context.getX(), context.getY(), "set visable", context.getLogicBoard());
    button.setText("  visibility of "+source.level.groupNames.get(groupNumber));
  }

  SetVisibility(JSONObject data) {
    super(data.getFloat("x"), data.getFloat("y"), "set visable", data.getJSONArray("connections"));
    groupNumber=data.getInt("group number");
    reText = true;
  }
  
  public SetVisibility(SerialIterator iterator){
    super(iterator);
    groupNumber = iterator.getInt();
  }
  
  void tick() {
    if (inputTerminal1) {
      source.level.groups.get(groupNumber).visable=true;
    }
    if (inputTerminal2) {
      source.level.groups.get(groupNumber).visable=false;
    }
  }
  JSONObject save() {
    JSONObject component=super.save();
    component.setInt("group number", groupNumber);
    return component;
  }
  void setData(int data) {
    groupNumber=data;
    button.setText("  visibility of "+source.level.groupNames.get(groupNumber));
  }
  int getData() {
    return groupNumber;
  }

  void draw() {
    if(reText){
      button.setText("  visibility of "+source.level.groupNames.get(groupNumber));
      reText=false;
    }
    super.draw();
    source.fill(0);
    source.textSize(15*source.Scale);
    source.textAlign(source.LEFT, source.CENTER);
    source.text("true", (x+5-source.camPos)*source.Scale, (y+16-source.camPosY)*source.Scale);
    source.text("false", (x+5-source.camPos)*source.Scale, (y+56-source.camPosY)*source.Scale);
  }
  
  @Override
  public SerializedData serialize() {
    SerializedData data = new SerializedData(id());
    serialize(data);
    data.addInt(groupNumber);
    return data;
  }
  
  @Override
  public Identifier id() {
    return ID;
  }
}
