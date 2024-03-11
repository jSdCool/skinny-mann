import java.util.Random;
class SimpleEntity extends Entity{
  public SimpleEntity(float x,float y,float z){
    setX(x);
    setY(y);
    setZ(z);
  }
  
  public Entity create(float x,float y,float z){
    return new SimpleEntity(x,y,z);
  }
  int to =0;
  MovementManager m  = new MovementManager(){
      int ax =0,az =0;
      Random r = new Random();
      boolean j=false;
      boolean left(){return ax==-1;}
      boolean right(){return ax==1;}
      boolean in(){return az == -1;}
      boolean out(){return az ==1;}
      boolean jump(){return j;}
      void reset(){
        ax = (int)(r.nextInt(-1,2));
        az = (int)(r.nextInt(-1,2));
        j = (int)(Math.random()*2)==1;
      };
    };
  
  public MovementManager getMovementmanager(){
    return m;
  }
  
  float x,y,z;
  float vVelcoity;
  
  public float getX(){
    return x;
  }
  public float getY(){
    return y;
  }
  public float getZ(){
    return z;
  }
  
  public Entity setX(float x){
    this.x=x;
    return this;
  }
  
  public Entity setY(float y){
    this.y=y;
    return this;
  }
  
  public Entity setZ(float z){
    this.z=z;
    return this;
  }
  
  public boolean collidesWithEntites(){
    return true;
  }
  
  public Collider3D getHitBox3D(float offsetX,float offsetY,float offsetZ){
    return Collider3D.createBoxHitBox(x+offsetX,y+offsetY,z+offsetZ,40,40,40);
  }
  
  public Collider2D getHitBox2D(float offsetX,float offsetY){
   return Collider2D.createRectHitbox(x+offsetX,y+offsetY,40,40); 
  }
  
  public boolean in3D(){
    return false;
  }
  
  public float getVerticalVelocity(){
    return vVelcoity;
  }
  
  public Entity setVerticalVelocity(float v){
    vVelcoity=v;
    return this;
  }
  
  public void draw(skiny_mann context){
    context.fill(40);
    context.rect(context.Scale*(x-context.drawCamPosX),context.Scale*(y+context.drawCamPosY),40*context.Scale,40*context.Scale);
    if(to ==0 ){
      to = 20;
      m.reset();
    }
    to--;
  }
  
  public void draw3D(skiny_mann context){
    context.fill(40);
    context.translate(x+20,y+20,z+20);
    context.box(40);
    context.translate(-x-20,-y-20,-z-20);
  }
}
