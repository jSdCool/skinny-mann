import processing.core.*;
import processing.data.*;
import java.util.ArrayList;

class Ground extends StageComponent implements Rotatable,Resizeable{//ground component

  public static final Identifier ID = new Identifier("ground");
  
  private PMatrix3D transfomration = new PMatrix3D(), rotation =  new PMatrix3D(),tmpMat = new PMatrix3D();
  private float rx,ry,rz;
  PVector verticies[] = new PVector[]{new PVector(),new PVector(),new PVector(),new PVector(),new PVector(),new PVector(),new PVector(),new PVector()};//8 long
  PVector verticies2D[] = new PVector[]{new PVector(),new PVector(),new PVector(),new PVector(),new PVector(),new PVector(),new PVector(),new PVector()};//8 long
  PVector center = new PVector();
  PVector prevoursGroupPos = new PVector();
  
  private final PVector F_XNORM = new PVector(1,0,0), F_YNORM = new PVector(0,1,0), F_ZNORM = new PVector(0,0,1);

  Ground(JSONObject data) {
    type="ground";
    x=data.getFloat("x");
    y=data.getFloat("y");
    dx=data.getFloat("dx");
    dy=data.getFloat("dy");
    ccolor=data.getInt("color");
    boolean stage_3D = data.getBoolean("s3d");
    if (stage_3D) {
      z=data.getFloat("z");
      dz=data.getFloat("dz");
    }
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
  
  public Ground(StageComponentDragPlacementContext context){
    type="ground";
    x = context.getX();
    y = context.getY();
    dx = context.getDX();
    dy = context.getDY();
    ccolor = context.getColor();
    if(context.has3D()){
      z = context.getZ();
      dz = context.getDZ();
    }
    updateVerticies();
  }
  
  public Ground(SerialIterator iterator){
    deserial(iterator);
    rx = iterator.getFloat();
    ry = iterator.getFloat();
    rz = iterator.getFloat();
    updateVerticies();
  }
  
  public StageComponent copy() {
    return new Ground(new StageComponentDragPlacementContext(x,y,z,dx,dy,dz,ccolor));
  }
  
  public StageComponent copy(float offsetX,float offsetY){
    return new Ground(new StageComponentDragPlacementContext(x+offsetX,y+offsetY,dx,dy,ccolor));
  }
  
  public StageComponent copy(float offsetX,float offsetY,float offsetZ){
    return new Ground(new StageComponentDragPlacementContext(x+offsetX,y+offsetY,z+offsetZ,dx,dy,dz,ccolor));
  }

  public JSONObject save(boolean stage_3D) {
    JSONObject part=new JSONObject();
    part.setFloat("x", x);
    part.setFloat("y", y);
    part.setFloat("dx", dx);
    part.setFloat("dy", dy);
    if (stage_3D) {
      part.setFloat("z", z);
      part.setFloat("dz", dz);
    }
    part.setInt("color", ccolor);
    part.setString("type", type);
    part.setInt("group", group);
    part.setFloat("rotateX",rx);
    part.setFloat("rotateY",ry);
    part.setFloat("rotateZ",rz);
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
      //draw a non rotated rect
      render.rect(source.Scale*((x+group.xOffset)-source.drawCamPosX)-0.02f, source.Scale*((y+group.yOffset)+source.drawCamPosY)-0.02f, source.Scale*dx+0.04f, source.Scale*dy+0.04f);
    }else if(!isRotated3D()){
      //draw a 2D rotated rect
      render.beginShape(PConstants.QUAD);
      Util.shapeVertex(render,verticies2D[0],-source.drawCamPosX,source.drawCamPosY,source.Scale);
      Util.shapeVertex(render,verticies2D[7],-source.drawCamPosX,source.drawCamPosY,source.Scale);
      Util.shapeVertex(render,verticies2D[6],-source.drawCamPosX,source.drawCamPosY,source.Scale);
      Util.shapeVertex(render,verticies2D[1],-source.drawCamPosX,source.drawCamPosY,source.Scale);
      render.endShape();
    }else{
      //draw the 2D version of the 2D roatated box
      verts2Box(render,verticies2D,-source.drawCamPosX,source.drawCamPosY,source.Scale);
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
    if(!isRotated()){
      render.translate((x+group.xOffset)+dx/2, (y+group.yOffset)+dy/2, (z+group.zOffset)+dz/2);
      render.box(dx, dy, dz);
      render.translate(-1*((x+group.xOffset)+dx/2), -1*((y+group.yOffset)+dy/2), -1*((z+group.zOffset)+dz/2));
    }else{
      verts2Box(render,verticies,0,0,1);
    }
  }

  public boolean colide(float x, float y, boolean c) {
    Group group=getGroup();
    if (!group.visable)
      return false;
    Collider2D hv = collide2Dimplm();
    Collider2D mp = new Collider2D(new PVector[]{new PVector(x-0.5f,y-0.5f),new PVector(x+0.5f,y-0.5f),new PVector(x+0.5f,y+0.5f),new PVector(x-0.5f,y+0.5f)});
    if (CollisionDetection.collide2D(hv,mp)) {
      return true;
    }
    return false;
  }

  public boolean colide(float x, float y, float z, boolean c) {
    Group group=getGroup();
    if (!group.visable)
      return false;
     Collider3D hv =new Collider3D(verticies);
     Collider3D mp = new Collider3D(new PVector[]{new PVector(x-0.5f,y-0.5f,z-0.5f),new PVector(x+0.5f,y-0.5f,z-0.5f),new PVector(x+0.5f,y+0.5f,z-0.5f),new PVector(x-0.5f,y+0.5f,z-0.5f),
   new PVector(x-0.5f,y-0.5f,z+0.5f),new PVector(x+0.5f,y-0.5f,z+0.5f),new PVector(x+0.5f,y+0.5f,z+0.5f),new PVector(x-0.5f,y+0.5f,z+0.5f)});
    if (CollisionDetection.collide3D(hv,mp)) {
      return true;
    }
    return false;
  }
  
  public Collider2D getCollider2D() {
    Group group=getGroup();
    if (!group.visable)
        return null;
    return collide2Dimplm();
    
  }
  
  private Collider2D collide2Dimplm(){
    if(!isRotated3D()){
      //draw a non rotated rect
      return new Collider2D(new PVector[]{verticies2D[0],verticies2D[7],verticies2D[6],verticies2D[1]});
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
    data.addFloat(rx);
    data.addFloat(ry);
    data.addFloat(rz);
    return data;
  }
  
  @Override
  public Identifier id() {
    return ID;
  }
  
  final float EPSILON = 0.00001f;
  
  public void setX(float x){
    this.x=x;
    updateVerticies();
  }
  public void setY(float y){
    this.y=y;
    updateVerticies();
  }
  public void setZ(float z){
    this.z=z;
    updateVerticies();
  }
  
  public void setWidth(float w){
    dx=w;
    updateVerticies();
  }
  
  public void setHeight(float h){
    dy=h;
    updateVerticies();
  }
  
  public void setDepth(float d){
    dz=d;
    updateVerticies();
  }
  
  public void resetRotate(){
    rx=0;
    ry=0;
    rz=0;
  }
  public void rotateX(float x){
    if(Float.isNaN(x)){
      return;
    }
    rx=x;
    updateVerticies();
  }
  public void rotateY(float y){
    if(Float.isNaN(y)){
      return;
    }
    ry=y;
    updateVerticies();
  }
  public void rotateZ(float z){
    if(Float.isNaN(z)){
      return;
    }
    rz=z;
    updateVerticies();
  }
  public void updateVerticies(){
    Group group=getGroup();
    transfomration.reset();
    center.x = x+dx/2+group.xOffset;
    center.y = y+dy/2+group.yOffset;
    center.z = z+dz/2+group.zOffset;
    //add the offset translation
    transfomration.translate(center.x,center.y,center.z);
    rotation.reset();
    //get the current rotaiton matix
    Util.rotateXYZ(rx,ry,rz,rotation);
    
    transfomration.apply(rotation);
    
    
    float hdx = dx/2;
    float hdy = dy/2;
    float hdz = dz/2;
    //reset the vrticies
    verticies[0].x = -hdx;
    verticies[0].y = -hdy;
    verticies[0].z = -hdz;
    verticies[1].x = hdx;
    verticies[1].y = -hdy;
    verticies[1].z = -hdz;
    verticies[2].x = hdx;
    verticies[2].y = -hdy;
    verticies[2].z = hdz;
    verticies[3].x = -hdx;
    verticies[3].y = -hdy;
    verticies[3].z = hdz;
    verticies[4].x = -hdx;
    verticies[4].y = hdy;
    verticies[4].z = hdz;
    verticies[5].x = hdx;
    verticies[5].y = hdy;
    verticies[5].z = hdz;
    verticies[6].x = hdx;
    verticies[6].y = hdy;
    verticies[6].z = -hdz;
    verticies[7].x = -hdx;
    verticies[7].y = hdy;
    verticies[7].z = -hdz;
    //transform all the verticies
    Util.transform4Vert(transfomration,verticies[0],verticies[1],verticies[2],verticies[3],tmpMat);
    Util.transform4Vert(transfomration,verticies[4],verticies[5],verticies[6],verticies[7],tmpMat);
    
    for(int i=0;i<verticies.length;i++){
      //copy the 3D verticies to a diffrent array
      verticies2D[i].set(verticies[i]);
      //nuke the z coordinate
      verticies2D[i].z=0;
    }
  }
  
  public float getRotateX(){
    return rx;
  }
  public float getRotateY(){
    return ry;
  }
  public float getRotateZ(){
    return rz;
  }
  
  public PVector getXLocal(){
    PVector norm = new PVector();
    rotation.mult(F_XNORM,norm);
    return norm;
  }
  public PVector getYLocal(){
    PVector norm = new PVector();
    rotation.mult(F_YNORM,norm);
    return norm;
  }
  public PVector getZLocal(){
    PVector norm = new PVector();
    rotation.mult(F_ZNORM,norm);
    return norm;
  }
  
  public PVector getXRotationAxis(){
    return getXLocal();
  }
  public PVector getYRotationAxis(){
    tmpMat.reset();
    Util.rotateXYZ(0,0,rz,tmpMat);
    PVector result = new PVector();
    tmpMat.mult(F_YNORM, result);
    return result;
  }
  public PVector getZRotationAxis(){
    return new PVector(0,0,1);
  }
  
  public boolean isRotated(){
    return Math.abs(rx) > EPSILON || Math.abs(ry) > EPSILON || Math.abs(rz) > EPSILON;
  }
  
  public boolean isRotated3D(){
    return Math.abs(rx) > EPSILON || Math.abs(ry) > EPSILON;
  }
  
  private void verts2Box(PGraphics render, PVector[] verts,float offsetX, float offsetY, float scale){
    render.beginShape(PConstants.QUAD);
    Util.shapeVertex(render,verts[0], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[1], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[2], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[3], offsetX, offsetY, scale);
    
    Util.shapeVertex(render,verts[0], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[7], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[6], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[1], offsetX, offsetY, scale);
    
    Util.shapeVertex(render,verts[4], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[5], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[6], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[7], offsetX, offsetY, scale);
    
    Util.shapeVertex(render,verts[2], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[5], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[4], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[3], offsetX, offsetY, scale);
    
    Util.shapeVertex(render,verts[1], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[6], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[5], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[2], offsetX, offsetY, scale);
    
    Util.shapeVertex(render,verts[0], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[3], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[4], offsetX, offsetY, scale);
    Util.shapeVertex(render,verts[7], offsetX, offsetY, scale);
    render.endShape();
  }
}
