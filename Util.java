import processing.core.PMatrix3D;
import processing.core.PVector;

public class Util{
  /** apply the given transformation to 4 verticies at the same time
  the values of the input verticies will be modified by this function
  @param transform the transformation to apply
  @param a the first vertex to transform
  @param b the second vertex to transform
  @param c the third vertex to transform
  @param d the forth vertex to transform
  */
  public static void transform4Vert(PMatrix3D transform, PVector a, PVector b, PVector c, PVector d){
    transform4Vert(transform,a,b,c,d,new PMatrix3D());
  }
  
  /** apply the given transformation to 4 verticies at the same time
  the values of the input verticies will be modified by this function
  NOTE: supplying and reusing a value for tmpMat will improve preformace when calling repetidly
  @param transform the transformation to apply
  @param a the first vertex to transform
  @param b the second vertex to transform
  @param c the third vertex to transform
  @param d the forth vertex to transform
  @param tmpMat temportaty matrix used for transformations, reuse in each call for imporved preformace
  */
  public static void transform4Vert(PMatrix3D transform, PVector a, PVector b, PVector c, PVector d,PMatrix3D tmpMat){
    if(tmpMat == null){
      //just in case
      tmpMat = new PMatrix3D();
    }
    //verify all verticies are present
    if(a==null || b == null || c == null || d == null){
      PVector tmpVec = new PVector();
      if(a == null){
        a = tmpVec;
      }
      if(b == null){
        b = tmpVec;
      }
      if(c == null){
        c = tmpVec;
      }
      if(d == null){
        d = tmpVec;
      }
    }
    //combine the verticies into a single matrix
    tmpMat.set(a.x,b.x,c.x,d.x,
               a.y,b.y,c.y,d.y,
               a.z,b.z,c.z,d.z,
               1,  1,  1,  1);
    //apply the transformation 
    tmpMat.preApply(transform);
    
    //extract each verticy from the matrix
    a.x = tmpMat.m00;
    a.y = tmpMat.m10;
    a.z = tmpMat.m20;
    b.x = tmpMat.m01;
    b.y = tmpMat.m11;
    b.z = tmpMat.m21;
    c.x = tmpMat.m02;
    c.y = tmpMat.m12;
    c.z = tmpMat.m22;
    d.x = tmpMat.m03;
    d.y = tmpMat.m13;
    d.z = tmpMat.m23;
    
  }
  
  /**creates a 3D rotation matix for a rotation in all 3 axsis
  @param x the angle in the x-axis to rotate (in radians)
  @param y the angle in the y-axis to rotate (in radians)
  @param z the angle in the z-axis to rotate (in radians)
  @returns a matrix containing the requirested 3D roation
  */
  public static PMatrix3D rotateXYZ(float x, float y,float z){
    return rotateXYZ(x, y,z,null);
  }
  
  /**applies a 3D rotation to the input transformation matix
  @param x the angle in the x-axis to rotate (in radians)
  @param y the angle in the y-axis to rotate (in radians)
  @param z the angle in the z-axis to rotate (in radians)
  @param currentTransform the current transformation to apply the rotation to
  @returns the new transforamtion including the rotation, note the input matrix is also updateing with this transformation
  */
  public static PMatrix3D rotateXYZ(float x, float y,float z,PMatrix3D currentTransform){
    if(currentTransform == null){
      currentTransform = new PMatrix3D();
    }
    
    float cosz = (float)Math.cos(z);
    float cosy = (float)Math.cos(y);
    float cosx = (float)Math.cos(x);
    float sinz = (float)Math.sin(z);
    float siny = (float)Math.sin(y);
    float sinx = (float)Math.sin(x);
    
    currentTransform.apply(cosz*cosy, cosz*siny*sinx - sinz*cosx, cosz*siny*cosx + sinz*sinx, 0,
                           sinz*cosy, sinz*siny*sinx + cosz*cosx, sinz*siny*cosx - cosz*sinx, 0,
                           -siny,     cosy*sinx,                  cosy*cosx,                  0,
                           0,         0,                          0,                          1);
    
    return currentTransform;
    
  }
  
  public static PVector projectToPlane(PVector point, PVector center,PVector normal){
    PVector work = new PVector();
    // P - ((((P-C) dot N )/ (N dot N)) dot N)
    return PVector.sub(point,PVector.mult(normal,((PVector.sub(point,center,work).dot(normal))/(normal.dot(normal))),work),work);
  }
  
  public static PVector intersectPlaneAndLine(PVector lineA,PVector lineB, PVector planePoint,PVector planeNormal){
    float t = planeNormal.x*(planePoint.x-lineA.x) + planeNormal.y*(planePoint.y-lineA.y) + planeNormal.z*(planePoint.z-lineA.z);
    t /= planeNormal.x*(lineB.x-lineA.x) + planeNormal.y*(lineB.y-lineA.y) + planeNormal.z*(lineB.z-lineA.z);
    
    PVector reslut = new PVector(lineA.x+t*(lineB.x-lineA.x),lineA.y+t*(lineB.y-lineA.y),lineA.z+t*(lineB.z-lineA.z));
    return reslut;
  }
}
