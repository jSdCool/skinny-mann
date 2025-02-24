 class StageComponentPlacementContext{
   
   private float x,y,z;
   private boolean has3D = false;
   private int index = -1;
   
   public StageComponentPlacementContext(float x, float y){
     this.x=x;
     this.y=y;
     System.out.println(1);
   }
   
   public StageComponentPlacementContext(float x, float y, float z){
     has3D = true;
     this.x=x;
     this.y=y;
     this.z=z;
     System.out.println(2);
   }
   
   public StageComponentPlacementContext(float x, float y, int index){
     this.x=x;
     this.y=y;
     this.index = index;
     System.out.println(3);
   }
   
   public StageComponentPlacementContext(float x, float y, float z, int index){
     has3D = true;
     this.x=x;
     this.y=y;
     this.z=z;
     this.index = index;
     System.out.println(4);
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
   
   public boolean has3D(){
     return has3D;
   }
   
   public int index(){
     return index;
   }
 }
