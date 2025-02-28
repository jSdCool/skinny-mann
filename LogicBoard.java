import processing.core.*;
import processing.data.*;
import java.util.ArrayList;
import java.util.function.Function;
class LogicBoard implements Serialization {//stores all the logic components

  public static final Identifier ID = new Identifier("LogicBoard");

  static transient skiny_mann source;
  public String name="eee";//temp name
  public ArrayList<LogicComponent> components=new ArrayList<>();
  LogicBoard(JSONArray file, Level level) {
    JSONObject head=file.getJSONObject(0);
    name=head.getString("name");
    for (int i=1; i<file.size(); i++) {
      JSONObject component=file.getJSONObject(i);
      String type = Identifier.convertToId(component.getString("type"));
      Identifier typeId = new Identifier(type);
      Function<JSONObject, LogicComponent> constructor = LogicComponentRegistry.getJsonConstructor(typeId);
      if(constructor == null){
        System.err.println("No constructor found for idntifier: "+typeId);
        throw new RuntimeException("No constructor found for identifier: "+typeId);
      }
      LogicComponent comp = constructor.apply(component);
      comp.setLogicBoard(this);
      components.add(comp);
    }
  }
  
  LogicBoard(String name) {
    this.name=name;
  }

  public LogicBoard(SerialIterator iterator){
    name = iterator.getString();
    components = iterator.getArrayList();
  }
  
  String save() {
    JSONArray logicComponents=new JSONArray();
    JSONObject head=new JSONObject();
    head.setString("name", name);
    logicComponents.setJSONObject(0, head);
    for (int i=0; i<components.size(); i++) {
      logicComponents.setJSONObject(i+1, components.get(i).save());
    }
    source.saveJSONArray(logicComponents, source.rootPath+"/"+name+".json");
    return "/"+name+".json";
  }

  void remove(int index) {
    if (components.size()<=index||index<0)//check if the porvided index is valid
      return;
    components.remove(index);//remove the object
    for (int i=0; i<components.size(); i++) {//make shure all connects still point to the correct components and remove connects that went to the deleted one
      LogicComponent component=components.get(i);
      for (int j=0; j<component.connections.size(); j++) {
        if (component.connections.get(j)[0]==index) {
          component.connections.remove(j);
          j--;
          continue;
        }
        if (component.connections.get(j)[0]>index)
          component.connections.get(j)[0]--;
      }
    }
  }

  void tick() {//tick each component once
    for (int i=0; i<components.size(); i++) {
      components.get(i).tick();
    }
    for (int i=0; i<components.size(); i++) {
      components.get(i).sendOut();
    }
    for (int i=0; i<components.size(); i++) {
      components.get(i).flushBuffer();
    }
  }
  
  void superTick() {//ticked the logic board 256 times with no delay inbetween ticks
    for (int i=0; i<256; i++) {
      tick();
    }
  }
  
  @Override
  public SerializedData serialize() {
    SerializedData data = new SerializedData(id());
    data.addObject(SerializedData.ofString(name));
    data.addObject(SerializedData.ofArrayList(components,new Identifier("LogicComponent")));
    return data;
  }
  
  @Override
  public Identifier id() {
    return ID;
  }
}
