import processing.core.*;
import processing.data.*;
import java.util.ArrayList;

class HoloTriangle extends StageComponent implements Rotatable,Resizeable{//non colliding sloap component

  public static final Identifier ID = new Identifier("holoTriangle");
  
  //all this shit is related to rotation
  private PMatrix3D transfomration = new PMatrix3D(), rotation =  new PMatrix3D(),tmpMat = new PMatrix3D();
  private float rx,ry,rz;
  PVector verticies[] = new PVector[]{new PVector(),new PVector(),new PVector(),new PVector(),new PVector(),new PVector()};//6 long
  PVector verticies2D[] = new PVector[]{new PVector(),new PVector(),new PVector(),new PVector(),new PVector(),new PVector()};//6 long
  PVector center = new PVector();
  PVector prevoursGroupPos = new PVector();
  
  private final PVector F_XNORM = new PVector(1,0,0), F_YNORM = new PVector(0,1,0), F_ZNORM = new PVector(0,0,1);
  //end of rotation vars
  
  int direction;
  HoloTriangle(JSONObject data) {
    type="holoTriangle";
    x=data.getFloat("x1");
    y=data.getFloat("y1");
    dx=data.getFloat("x2");
    dy=data.getFloat("y2");
    ccolor=data.getInt("color");
    boolean stage_3D = data.getBoolean("s3d");
    if (stage_3D && !data.isNull("z")) {
      z=data.getFloat("z");
      dz=data.getFloat("dz");
    }
    direction=data.getInt("rotation");
    if (!data.isNull("group")) {
      group=data.getInt("group");
    }
    if(!data.isNull("rotateX")){
      rx = data.getFloat("rotateX");
    }
    if(!data.isNull("rotateY")){
      ry = data.getFloat("rotateY");
    }
    if(!data.isNull("rotateZ")){
      rz = data.getFloat("rotateZ");
    }
    updateVerticies();
  }
  
  public HoloTriangle(StageComponentDragPlacementContext context){
    type="holoTriangle";
    x = context.getX();
    y = context.getY();
    dx = x+context.getDX();
    dy = y+context.getDY();
    ccolor = context.getColor();
    if(context.has3D()){
      z = context.getZ();
      dz = z+context.getDZ();
    }
    direction = context.getRotation();//note this is actually the triangle mode
    updateVerticies();
  }
  
  public HoloTriangle(SerialIterator iterator){
    deserial(iterator);
    direction = iterator.getInt();
    rx = iterator.getFloat();
    ry = iterator.getFloat();
    rz = iterator.getFloat();
    updateVerticies();
  }
  
  public StageComponent copy() {
    return new HoloTriangle(new StageComponentDragPlacementContext(x, y, getWidth(), getHeight(), ccolor, direction));
  }
  
  public StageComponent copy(float offsetX,float offsetY){
    return new HoloTriangle(new StageComponentDragPlacementContext(x+offsetX,y+offsetY,getWidth(),getHeight(),ccolor,direction));
  }
  
  public StageComponent copy(float offsetX,float offsetY,float offsetZ){
    return new HoloTriangle(new StageComponentDragPlacementContext(x+offsetX,y+offsetY,z+offsetZ,getWidth(),getHeight(),getDepth(),ccolor,direction));
  }

  public JSONObject save(boolean stage_3D) {
    JSONObject part=new JSONObject();
    part.setFloat("x1", x);
    part.setFloat("y1", y);
    part.setFloat("x2", dx);
    part.setFloat("y2", dy);
    part.setInt("color", ccolor);
    part.setString("type", type);
    part.setInt("rotation", direction);
    part.setInt("group", group);
    part.setFloat("rotateX",rx);
    part.setFloat("rotateY",ry);
    part.setFloat("rotateZ",rz);
    part.setFloat("z",z);
    part.setFloat("dz",dz);
    return part;
  }

  public void draw(PGraphics render) {
    Group group=getGroup();
    if (!group.visable)
      return;
    
    //if the group has been modified then recalculate the verticies
    if(group.xOffset != prevoursGroupPos.x || group.yOffset!=prevoursGroupPos.y || group.zOffset!=prevoursGroupPos.z){
      updateVerticies();
      prevoursGroupPos.x = group.xOffset;
      prevoursGroupPos.y = group.yOffset;
      prevoursGroupPos.z = group.zOffset;
    }
    
    render.fill(ccolor);
    if(!isRotated()){
      if (direction==0) {
        render.triangle(source.Scale*((x+group.xOffset)-source.drawCamPosX), source.Scale*((y+group.yOffset)+source.drawCamPosY), source.Scale*((dx+group.xOffset)-source.drawCamPosX), source.Scale*((dy+group.yOffset)+source.drawCamPosY), source.Scale*((dx+group.xOffset)-source.drawCamPosX), source.Scale*((y+group.yOffset)+source.drawCamPosY));
      }
      if (direction==1) {
        render.triangle(source.Scale*((x+group.xOffset)-source.drawCamPosX), source.Scale*((y+group.yOffset)+source.drawCamPosY), source.Scale*((x+group.xOffset)-source.drawCamPosX), source.Scale*((dy+group.yOffset)+source.drawCamPosY), source.Scale*((dx+group.xOffset)-source.drawCamPosX), source.Scale*((y+group.yOffset)+source.drawCamPosY));
      }
      if (direction==2) {
        render.triangle(source.Scale*((x+group.xOffset)-source.drawCamPosX), source.Scale*((y+group.yOffset)+source.drawCamPosY), source.Scale*((dx+group.xOffset)-source.drawCamPosX), source.Scale*((dy+group.yOffset)+source.drawCamPosY), source.Scale*((x+group.xOffset)-source.drawCamPosX), source.Scale*((dy+group.yOffset)+source.drawCamPosY));
      }
      if (direction==3) {
        render.triangle(source.Scale*((x+group.xOffset)-source.drawCamPosX), source.Scale*((dy+group.yOffset)+source.drawCamPosY), source.Scale*((dx+group.xOffset)-source.drawCamPosX), source.Scale*((dy+group.yOffset)+source.drawCamPosY), source.Scale*((dx+group.xOffset)-source.drawCamPosX), source.Scale*((y+group.yOffset)+source.drawCamPosY));
      }
    }else{
      verts2Tri(render,verticies2D,-source.drawCamPosX,source.drawCamPosY,source.Scale);
    }
  }

  public void draw3D(PGraphics render) {
    Group group=getGroup();
    if (!group.visable)
      return;
    
    //if the group has been modified then recalculate the verticies
    if(group.xOffset != prevoursGroupPos.x || group.yOffset!=prevoursGroupPos.y || group.zOffset!=prevoursGroupPos.z){
      updateVerticies();
      prevoursGroupPos.x = group.xOffset;
      prevoursGroupPos.y = group.yOffset;
      prevoursGroupPos.z = group.zOffset;
    }
    render.fill(ccolor);
    verts2Tri(render,verticies,0,0,1);
  }

  public boolean colide(float x, float y, boolean c) {
    Group group=getGroup();
    if (!group.visable)
      return false;
    if (c) {
      Collider2D hv = collide2Dimplm();
      Collider2D mp = new Collider2D(new PVector[]{new PVector(x-0.5f,y-0.5f),new PVector(x+0.5f,y-0.5f),new PVector(x+0.5f,y+0.5f),new PVector(x-0.5f,y+0.5f)});
      if (CollisionDetection.collide2D(hv,mp)) {
        return true;
      }
      return false;
    }
    return false;
  }
  
  public boolean colide(float x, float y,float z, boolean c) {
    Group group=getGroup();
    if (!group.visable)
      return false;
     Collider3D hv =new Collider3D(verticies);
     Collider3D mp = new Collider3D(new PVector[]{new PVector(x-0.5f,y-0.5f,z-0.5f),new PVector(x+0.5f,y-0.5f,z-0.5f),new PVector(x+0.5f,y+0.5f,z-0.5f),new PVector(x-0.5f,y+0.5f,z-0.5f),
       new PVector(x-0.5f,y-0.5f,z+0.5f),new PVector(x+0.5f,y-0.5f,z+0.5f),new PVector(x+0.5f,y+0.5f,z+0.5f),new PVector(x-0.5f,y+0.5f,z+0.5f)
     });
    if (CollisionDetection.collide3D(hv,mp)) {
      return true;
    }
    return false;
  }
  
  public Collider2D getCollider2D(){
    return null;
  }
  
  private Collider2D collide2Dimplm(){
    if(!isRotated3D()){
      Group group=getGroup();
      //draw a non rotated rect
      int rot=direction;
      switch(rot){
        case 0:
          return new Collider2D(new PVector[]{
            new PVector(x+group.xOffset, y+group.yOffset),
            new PVector(dx+group.xOffset, dy+group.yOffset),
            new PVector(dx+group.xOffset, y+group.yOffset)
          });
        case 1:
          return new Collider2D(new PVector[]{
            new PVector(x+group.xOffset, y+group.yOffset),
            new PVector(x+group.xOffset, dy+group.yOffset),
            new PVector(dx+group.xOffset, y+group.yOffset)
          });
        case 2:
          return new Collider2D(new PVector[]{
            new PVector(x+group.xOffset, y+group.yOffset),
            new PVector(dx+group.xOffset, dy+group.yOffset),
            new PVector(x+group.xOffset, dy+group.yOffset)
          });
        case 3:
          return new Collider2D(new PVector[]{
            new PVector(x+group.xOffset, dy+group.yOffset),
            new PVector(dx+group.xOffset, dy+group.yOffset),
            new PVector(dx+group.xOffset, y+group.yOffset)
          });
        default:
          System.err.println("Attempted to get collider for 2D tirangle with triangle mdoe of: "+rot);
          return null;
      }
    }else {
      //draw the 2D version of the 2D roatated box
      return new Collider2D(verticies2D);
    }
  }
  
  public Collider3D getCollider3D() {
    Group group=getGroup();
    if (!group.visable)
        return null;
    return new Collider3D(verticies);
  }
  
  @Override
  public SerializedData serialize() {
    SerializedData data = new SerializedData(id());
    serialize(data);
    data.addInt(direction);
    data.addFloat(rx);
    data.addFloat(ry);
    data.addFloat(rz);
    return data;
  }
  
  @Override
  public Identifier id() {
    return ID;
  }
  
  //overides for methods defined in StageComponenet
  @Override
  public float getX(){
    return this.x;
  }
  
  @Override
  public float getY(){
    return this.y;
  }
  
  @Override
  public float getZ(){
    return this.z;
  }
  
  @Override
  public float getWidth(){
    return dx-x;
  }
  
  @Override
  public float getHeight(){
    return dy-y;
  }
  
  @Override
  public float getDepth(){
    return dz-z;
  }
  
  final float EPSILON = 0.00001f;
  
  @Override
  public void setX(float x){
    dx = x + getWidth();
    this.x=x;
    updateVerticies();
  }
  @Override
  public void setY(float y){
    dy = y + getHeight();
    this.y=y;
    updateVerticies();
  }
  @Override
  public void setZ(float z){
    dz = z + getDepth();
    this.z=z;
    updateVerticies();
  }
  
  @Override
  public void setWidth(float w){
    dx=this.x+w;
    updateVerticies();
  }
  
  @Override
  public void setHeight(float h){
    dy=this.y+h;
    updateVerticies();
  }
  
  @Override
  public void setDepth(float d){
    dz=this.z+d;
    updateVerticies();
  }
  
  @Override
  public void resetRotate(){
    rx=0;
    ry=0;
    rz=0;
  }
  
  @Override
  public void rotateX(float x){
    if(Float.isNaN(x)){
      return;
    }
    rx=x;
    updateVerticies();
  }
  @Override
  public void rotateY(float y){
    if(Float.isNaN(y)){
      return;
    }
    ry=y;
    updateVerticies();
  }
  @Override
  public void rotateZ(float z){
    if(Float.isNaN(z)){
      return;
    }
    rz=z;
    updateVerticies();
  }
  
  @Override
  public void updateVerticies(){
    Group group=getGroup();
    transfomration.reset();
    center.x = getX()+getWidth()/2+group.xOffset;
    center.y = getY()+getHeight()/2+group.yOffset;
    center.z = getZ()+getDepth()/2+group.zOffset;
    //add the offset translation
    transfomration.translate(center.x,center.y,center.z);
    rotation.reset();
    //get the current rotaiton matix
    Util.rotateXYZ(rx,ry,rz,rotation);
    
    transfomration.apply(rotation);
    //reset the vrticies
    //TODO figure out what theese verticies should be per the rotation value
    
    float hdx = getWidth()/2;
    float hdy = getHeight()/2;
    float hdz = getDepth()/2;
    
    int j = 0;
    switch(direction){
      case 0:
        verticies[j].x = -hdx;
        verticies[j].y = -hdy;
        verticies[j].z = hdz;
        j++;
        verticies[j].x = hdx;
        verticies[j].y = hdy;
        verticies[j].z = hdz;
        j++;
        verticies[j].x = hdx;
        verticies[j].y = -hdy;
        verticies[j].z = hdz;
        j++;
        verticies[j].x = -hdx;
        verticies[j].y = -hdy;
        verticies[j].z = -hdz;
        j++;
        verticies[j].x = hdx;
        verticies[j].y = -hdy;
        verticies[j].z = -hdz;
        j++;
        verticies[j].x = hdx;
        verticies[j].y = hdy;
        verticies[j].z = -hdz;
        break;
      case 1:
        verticies[j].x = -hdx;
        verticies[j].y = -hdy;
        verticies[j].z = hdz;
        j++;
        verticies[j].x = -hdx;
        verticies[j].y = hdy;
        verticies[j].z = hdz;
        j++;
        verticies[j].x = hdx;
        verticies[j].y = -hdy;
        verticies[j].z = hdz;
        j++;
        verticies[j].x = -hdx;
        verticies[j].y = -hdy;
        verticies[j].z = -hdz;
        j++;
        verticies[j].x = hdx;
        verticies[j].y = -hdy;
        verticies[j].z = -hdz;
        j++;
        verticies[j].x = -hdx;
        verticies[j].y = hdy;
        verticies[j].z = -hdz;
        break;
      case 2:
        verticies[j].x = -hdx;
        verticies[j].y = -hdy;
        verticies[j].z = hdz;
        j++;
        verticies[j].x = -hdx;
        verticies[j].y = hdy;
        verticies[j].z = hdz;
        j++;
        verticies[j].x = hdx;
        verticies[j].y = hdy;
        verticies[j].z = hdz;
        j++;
        verticies[j].x = -hdx;
        verticies[j].y = -hdy;
        verticies[j].z = -hdz;
        j++;
        verticies[j].x = hdx;
        verticies[j].y = hdy;
        verticies[j].z = -hdz;
        j++;
        verticies[j].x = -hdx;
        verticies[j].y = hdy;
        verticies[j].z = -hdz;
        break;
      case 3:
        verticies[j].x = -hdx;
        verticies[j].y = hdy;
        verticies[j].z = hdz;
        j++;
        verticies[j].x = hdx;
        verticies[j].y = hdy;
        verticies[j].z = hdz;
        j++;
        verticies[j].x = hdx;
        verticies[j].y = -hdy;
        verticies[j].z = hdz;
        j++;
        verticies[j].x = -hdx;
        verticies[j].y = hdy;
        verticies[j].z = -hdz;
        j++;
        verticies[j].x = hdx;
        verticies[j].y = -hdy;
        verticies[j].z = -hdz;
        j++;
        verticies[j].x = hdx;
        verticies[j].y = hdy;
        verticies[j].z = -hdz;
        break;
    }
    //transform all the verticies
    Util.transform4Vert(transfomration,verticies[0],verticies[1],verticies[2],verticies[3],tmpMat);
    Util.transform4Vert(transfomration,verticies[4],verticies[5],verticies[4],verticies[5],tmpMat);//the repetishen of theese verticies may pose an issue
    
    for(int i=0;i<verticies.length;i++){
      //copy the 3D verticies to a diffrent array
      verticies2D[i].set(verticies[i]);
      //nuke the z coordinate
      verticies2D[i].z=0;
    }
  }
  
  @Override
  public float getRotateX(){
    return rx;
  }
  @Override
  public float getRotateY(){
    return ry;
  }
  @Override
  public float getRotateZ(){
    return rz;
  }
  @Override
  public PVector getXLocal(){
    PVector norm = new PVector();
    rotation.mult(F_XNORM,norm);
    return norm;
  }
  @Override
  public PVector getYLocal(){
    PVector norm = new PVector();
    rotation.mult(F_YNORM,norm);
    return norm;
  }
  @Override
  public PVector getZLocal(){
    PVector norm = new PVector();
    rotation.mult(F_ZNORM,norm);
    return norm;
  }
  
  @Override
  public PVector getXRotationAxis(){
    return getXLocal();
  }
  @Override
  public PVector getYRotationAxis(){
    tmpMat.reset();
    Util.rotateXYZ(0,0,rz,tmpMat);
    PVector result = new PVector();
    tmpMat.mult(F_YNORM, result);
    return result;
  }
  @Override
  public PVector getZRotationAxis(){
    return new PVector(0,0,1);
  }
  @Override
  public boolean isRotated(){
    return Math.abs(rx) > EPSILON || Math.abs(ry) > EPSILON || Math.abs(rz) > EPSILON;
  }
  @Override
  public boolean isRotated3D(){
    return Math.abs(rx) > EPSILON || Math.abs(ry) > EPSILON;
  }
  
  private void verts2Tri(PGraphics render, PVector[] verts,float offsetX, float offsetY, float scale){
    //3 quads (faces)
    render.beginShape(PConstants.QUAD);

    Util.shapeVertex(render,verts[0], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[3], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[4], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[2], offsetX, offsetY, scale);

    Util.shapeVertex(render,verts[1], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[2], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[4], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[5], offsetX, offsetY, scale);
    
    Util.shapeVertex(render,verts[0], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[1], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[5], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[3], offsetX, offsetY, scale);
    render.endShape();
    
    //2 triangles
    render.beginShape(PConstants.TRIANGLES);

    Util.shapeVertex(render,verts[0], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[2], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[1], offsetX, offsetY, scale);

    Util.shapeVertex(render,verts[3], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[5], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[4], offsetX, offsetY, scale);
    render.endShape();
  }
}
