
class StageComponentDragPlacementContext{
  
  private float x, y, z, dx, dy, dz;
  private boolean has3D = false;
  private int color, rotation;
  
  public StageComponentDragPlacementContext(float x, float y, float dx, float dy, int color){
    this.x=x;
    this.y=y;
    this.dx=dx;
    this.dy=dy;
    this.color = color;
  }
  
  public StageComponentDragPlacementContext(float x, float y, float z, float dx, float dy, float dz, int color){
    this.x=x;
    this.y=y;
    this.z=z;
    this.dx=dx;
    this.dy=dy;
    this.dz=dz;
    this.color = color;
    has3D=true;
  }
  
  public StageComponentDragPlacementContext(float x, float y, float dx, float dy, int color, int rotate){
    this.x=x;
    this.y=y;
    this.dx=dx;
    this.dy=dy;
    this.color = color;
    rotation = rotate;
  }
  
  public StageComponentDragPlacementContext(float x, float y, float z, float dx, float dy, float dz, int color, int rotate){
    this.x=x;
    this.y=y;
    this.z=z;
    this.dx=dx;
    this.dy=dy;
    this.dz=dz;
    this.color = color;
    rotation = rotate;
    has3D=true;
  }
  
  public float getX(){
    return x;
  }
  
  public float getY(){
    return y;
  }
  
  public float getZ(){
    return z;
  }
  
  public float getDX(){
    return dx;
  }
  
  public float getDY(){
    return dy;
  }
  
  public float getDZ(){
    return dz;
  }
  
  public boolean has3D(){
    return has3D;
  }
  
  public int getColor(){
    return color;
  }
  
  public int getRotation(){
    return rotation;
  }
  
}
