import processing.core.*;
import processing.data.*;
import java.util.ArrayList;

class Ground extends StageComponent implements Rotatable{//ground component

  public static final Identifier ID = new Identifier("ground");
  
  private PMatrix3D transfomration = new PMatrix3D(), rotation =  new PMatrix3D(),tmpMat = new PMatrix3D();
  private float rx,ry,rz;
  PVector verticies[] = new PVector[]{new PVector(),new PVector(),new PVector(),new PVector(),new PVector(),new PVector(),new PVector(),new PVector()};//8 long
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
  }
  
  public Ground(SerialIterator iterator){
    deserial(iterator);
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
    return part;
  }

  public void draw(PGraphics render) {
    Group group=getGroup();
    if (!group.visable)
      return;
    render.fill(ccolor);
    render.rect(source.Scale*((x+group.xOffset)-source.drawCamPosX)-0.02f, source.Scale*((y+group.yOffset)+source.drawCamPosY)-0.02f, source.Scale*dx+0.04f, source.Scale*dy+0.04f);
  }

  public void draw3D(PGraphics render) {
    Group group=getGroup();
    if (!group.visable)
      return;
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
      render.beginShape(PConstants.QUAD);
      Util.shapeVertex(render,verticies[0]);
      Util.shapeVertex(render,verticies[1]);
      Util.shapeVertex(render,verticies[2]);
      Util.shapeVertex(render,verticies[3]);
      
      Util.shapeVertex(render,verticies[0]);
      Util.shapeVertex(render,verticies[7]);
      Util.shapeVertex(render,verticies[6]);
      Util.shapeVertex(render,verticies[1]);
      
      Util.shapeVertex(render,verticies[4]);
      Util.shapeVertex(render,verticies[5]);
      Util.shapeVertex(render,verticies[6]);
      Util.shapeVertex(render,verticies[7]);
      
      Util.shapeVertex(render,verticies[2]);
      Util.shapeVertex(render,verticies[5]);
      Util.shapeVertex(render,verticies[4]);
      Util.shapeVertex(render,verticies[3]);
      
      Util.shapeVertex(render,verticies[1]);
      Util.shapeVertex(render,verticies[6]);
      Util.shapeVertex(render,verticies[5]);
      Util.shapeVertex(render,verticies[2]);
      
      Util.shapeVertex(render,verticies[0]);
      Util.shapeVertex(render,verticies[3]);
      Util.shapeVertex(render,verticies[4]);
      Util.shapeVertex(render,verticies[7]);
      render.endShape();
    }
    for(PVector v: verticies){
      render.translate(v.x,v.y,v.z);
      render.fill(250,0,0);
      render.box(5);
      render.translate(-v.x,-v.y,-v.z);
    }
  }

  public boolean colide(float x, float y, boolean c) {
    Group group=getGroup();
    if (!group.visable)
      return false;
    float x2 = (this.x+group.xOffset)+dx, y2=(this.y+group.yOffset)+dy;
    if (x >= (this.x+group.xOffset) && x <= x2 && y >= (this.y+group.yOffset) && y <= y2/* terain hit box*/) {
      return true;
    }
    return false;
  }

  public boolean colide(float x, float y, float z, boolean c) {
    Group group=getGroup();
    if (!group.visable)
      return false;
    float x2 = (this.x+group.xOffset)+dx, y2=(this.y+group.yOffset)+dy, z2=(this.z+group.zOffset)+dz;
    if (x >= (this.x+group.xOffset) && x <= x2 && y >= (this.y+group.yOffset) && y <= y2 && z>=(this.z+group.zOffset) && z<=z2/* terain hit box*/) {
      return true;
    }
    return false;
  }
  
  public Collider2D getCollider2D() {
    Group group=getGroup();
    if (!group.visable)
        return null;
    return new Collider2D(new PVector[]{
      new PVector(x+group.xOffset, y+group.yOffset),
      new PVector(x+group.xOffset+dx, y+group.yOffset),
      new PVector(x+group.xOffset+dx, y+group.yOffset+dy),
      new PVector(x+group.xOffset, y+group.yOffset+dy)
      });
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
  
  public void setwidth(float w){
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
}
