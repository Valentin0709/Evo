class creier {
  int nr1,nr2,nr3;
  
  creier(int nr1_, int nr2_, int nr3_) {
    nr1=nr1_; nr2=nr2_; nr3=nr3_;
  }
  
  float[] in=new float[6];
  float[] h=new float[11];
  float[] out=new float[6];
  float[][] w1=new float[6][11];
  float[][] w2=new float[11][6];
  
  void calculh() {
    int i,j;
    
    for(i=1;i<=nr2;i++) h[i]=0;
    
    for(i=1;i<=nr1;i++) 
      for(j=1;j<=nr2;j++) 
        h[j]+=in[i]*w1[i][j];
  }
  
  void output() {
    int i,j;
    
    calculh();
    
    for(i=1;i<=nr3;i++) out[i]=0;
    
    for(i=1;i<=nr2;i++) 
      for(j=1;j<=nr3;j++) 
        out[j]+=h[i]*w2[i][j];
        
   for(i=1;i<=nr3;i++) 
     if(out[i]>=0) out[i]=1;
     else out[i]=-1;
  }
}