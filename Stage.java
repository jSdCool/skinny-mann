import processing.core.*;
import processing.data.*;
import java.util.ArrayList;
import java.util.function.Function;

class Stage implements Serialization {
  
  public static final Identifier ID = new Identifier("Stage");
  
  static transient skiny_mann source;
  public ArrayList<StageComponent> parts = new ArrayList<>(), interactables=new ArrayList<>();
  public ArrayList<StageEntity> entities = new ArrayList<>();
  public boolean is3D=false;
  public String type, name;
  public int stageID, skyColor=-9131009;
  Stage(JSONArray file) {//single varible instance for a stage
    load(file);
  }
  
  Stage(String Name, String Type) {
    name=Name;
    type=Type;
    is3D=type.equals("3Dstage")||type.equals("3D blueprint");
  }
  
  public Stage(SerialIterator iterator){
    parts = iterator.getArrayList();
    entities = iterator.getArrayList();
    is3D = iterator.getBoolean();
    type = iterator.getString();
    name = iterator.getString();
    stageID = iterator.getInt();
    skyColor = iterator.getInt();
  }


  void load(JSONArray file) {
    type=file.getJSONObject(0).getString("type");
    name=file.getJSONObject(0).getString("name");
    try {
      skyColor=file.getJSONObject(0).getInt("sky color");
    }
    catch(Throwable e) {
    }

    if (type.equals("stage")||type.equals("3Dstage")||type.equals("blueprint")||type.equals("3D blueprint")) {
      is3D=type.equals("3Dstage")||type.equals("3D blueprint");
      for (int i=1; i<file.size(); i++) {
        //try {
          JSONObject ob=file.getJSONObject(i);
          String otype=Identifier.convertToId(ob.getString("type"));
          ob.setBoolean("s3d",is3D);
          Identifier typeID = new Identifier(otype);
          Function<JSONObject, StageComponent> constructor = StageComponentRegistry.getJsonConstructor(typeID);
          if(constructor == null){
            //check if it is an entity
            //if the current thing is an entity, load it
            //StageEntity ent = source.entityRegistry.get(otype);
            if(/*ent!=null*/false){
              //entities.add(ent.create(ob,this));
            }else{
              System.err.println("No constructor found for idntifier: "+typeID);
              throw new RuntimeException("No constructor found for identifier: "+typeID);
            }
            continue;
          }
          
          StageComponent component = constructor.apply(ob);
          add(component);
      }
    }
  }
  
  void add(StageComponent component){
    parts.add(component);
    if(component instanceof Interactable){
      interactables.add(component);
    }
  }

  String save() {
    JSONArray staeg = new JSONArray();
    JSONObject head=new JSONObject();
    head.setString("name", name);
    head.setString("type", type);
    head.setInt("sky color", skyColor);
    staeg.setJSONObject(0, head);
    for (int i=0; i<parts.size(); i++) {
      staeg.append(parts.get(i).save(is3D));
    }
    for(int i=0;i<entities.size();i++){
      staeg.append(entities.get(i).save());
    }
    source.saveJSONArray(staeg, source.rootPath+"/"+name+".json");
    return "/"+name+".json";
  }
  
  void respawnEntities(){
    for(StageEntity se : entities){
      se.respawn();
    }
  }
  
  @Override
  public SerializedData serialize() {
    SerializedData data = new SerializedData(id());
    data.addObject(SerializedData.ofArrayList(parts,new Identifier("StageComponent")));
    data.addObject(SerializedData.ofArrayList(entities,new Identifier("StageEntity")));
    data.addBool(is3D);
    data.addObject(SerializedData.ofString(type));
    data.addObject(SerializedData.ofString(name));
    data.addInt(stageID);
    data.addInt(skyColor);
    return data;
  }
  
  @Override
  public Identifier id() {
    return ID;
  }
  
  public boolean equals(Object o){
    if(o instanceof Stage){
      Stage s = (Stage)o;
      if(!s.name.equals(name)){
        return false;
      }
      if(!s.type.equals(type)){
        return false;
      }
      if(stageID!=s.stageID){
        return false;
      }
      if(skyColor!=s.skyColor){
        return false;
      }
      if(parts.size()!=s.parts.size()){
        return false;
      }
      
      return true;
    }
    return false;
  }
}
