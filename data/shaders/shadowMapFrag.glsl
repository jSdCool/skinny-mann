#version 120

// Used a bigger poisson disk kernel than in the tutorial to get smoother results
const vec2 poissonDisk[9] = vec2[] (
   vec2(0.95581, -0.18159), vec2(0.50147, -0.35807), vec2(0.69607, 0.35559),
   vec2(-0.0036825, -0.59150), vec2(0.15930, 0.089750), vec2(-0.65031, 0.058189),
   vec2(0.11915, 0.78449), vec2(-0.34296, 0.51575), vec2(-0.60380, -0.41527)
);

// Unpack the 16bit depth float from the first two 8bit channels of the rgba vector
float unpackDepth(vec4 color) {
   return color.r + color.g / 255.0;
}

uniform sampler2D shadowMap;

varying vec4 vertColor;
varying vec4 shadowCoord;
varying float lightIntensity;

void main(void) {

   // Project shadow coords, needed for a perspective light matrix (spotlight)
   vec3 shadowCoordProj = shadowCoord.xyz / shadowCoord.w;

   // Only render shadow if fragment is facing the light
   if(lightIntensity > 0.5) {
       float visibility = 9.0;
	   //for each one of the edge points, increase the brightness if not in shaddow
	   //start the brightness at 50%
	   
	   //TODO: check if the projected coord is outside of the texture and render full brightness outside of the texture 

       
	   int n = 0;
	   //check if the mapped UV cord is outside of the texture without using branching 
	   n += int(min(1,int(shadowCoordProj.x > 1)+int(shadowCoordProj.y > 1)+int(shadowCoordProj.x < 0)+int(shadowCoordProj.y < 0)))*9;
	   //if n is 9 then make visibility imediatly max as we are skipping the for loop
	   visibility += n;
	   //this makes anything outside of the area renderd in the depth buffer automotiacly not in shadow
	   
	   // I used step() instead of branching, should be much faster this way
       for(; n < 9; ++n)
           visibility += step(shadowCoordProj.z, unpackDepth(texture2D(shadowMap, shadowCoordProj.xy + poissonDisk[n] / 512.0)));//edge dithering

       gl_FragColor = vec4(vertColor.rgb * min(visibility * 0.05556, lightIntensity), vertColor.a);
   } else
       gl_FragColor = vec4(vertColor.rgb * lightIntensity, vertColor.a);

}