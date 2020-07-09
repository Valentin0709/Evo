int ok=0,ok2=0,ok3=0,ok4=0,oka=1;
int pop=100;
int n=45,l=20;
int gen=1,nrp;
float med;
animal k,k2;

ArrayList<animal> a=new ArrayList();
ArrayList<planta> b=new ArrayList();

int[][] m=new int[n][n];
float[] sc=new float[100];

void setup(){
  size(1300,900);
}

void draw() {
  background(256,256,256);
  frameRate(60);
  
  panou1();
  panou2();
}

void panou1() {
  int i;
  
  if(ok3==1) {
    fill(156,256,156);
    rect(0,0,900,900);
  
  if(ok==1) {
    fill(0);
    textSize(16);
    textAlign(CENTER,CENTER);
    text("Generatia:"+gen+"  Medie: "+med+"  Maxim: "+sc[a.size()-1]+"   Minim:"+sc[0],400,20);
    
    for(i=0;i<a.size();i++) {
       fill(0,0,256);
       rect(75*(i/10+1),75*(i%10+1),20,20);
       if(mouseX>=75*(i/10+1)&&mouseX<=75*(i/10+1)+20&&mouseY>=75*(i%10+1)&&mouseY<=75*(i%10+1)+20&&mousePressed) {
         k=a.get(i); 
         ok2=1;
       }
    }
  }
  }
  else if(ok4==1) vezi(k2);
}
void panou2() {
  fill(156,156,256);
  rect(900,0,1300,900);
  rect(925,400,350,450);
  
  fill(0);
  textSize(32);
  textAlign(CENTER,CENTER);
  text("Panou de control",900,0,400,100);
  
  if(ok==0) {
    buton b=new buton(950,100,"Genereaza Populatie");
    b.show();
    if(b.click()) genereaza_populatie();
  }
  else {
    buton b1=new buton(950,100,"Generatie noua");
    b1.show();
    if(b1.click()) gen();
    
    buton b2=new buton(950,175,"Selecite Naturala");
    b2.show();
    if(b2.click()) selectie();
    
    buton b3=new buton(950,250,"Simuleaza");
    b3.show();
    if(b3.click()) simuleaza();
    
    buton b4=new buton(1120,100,"Auto");
    b4.show();
    if(b4.click()) auto();
    
    buton b5=new buton(1120,175,"Ecosistem");
    b5.show();
    if(b5.click()) eco();
  }
  
  //if(oka==-1) auto();
  
  if(ok2==1) analiza(k); 
}

void genereaza_populatie() {
  
  int i,j,q;
  
  ok=1; ok3=1;
  
  for(i=1;i<=pop;i++) {
    animal k=new animal();
    k.hp=100; k.bi=random(-1,1);
    
    for(j=1;j<=nr1;j++)
      for(q=1;q<=nr2;q++) k.c.w1[j][q]=random(-1,1);     
    for(j=1;j<=nr2;j++)
      for(q=1;q<=nr3;q++) k.c.w2[j][q]=random(-1,1);
   
    a.add(k);
  }
}

void analiza(animal k) {
  int i,j;
  
  fill(0);
  textSize(16);
  textAlign(CENTER,CENTER);
  if(ok3==0) text("Viata: "+(int)k.hp,1000,375);
  else text("Scor: "+(int)k.scor,1000,375);
  
  if(ok4==0) {
    buton b=new buton(1050,335,"Vezi");
    b.show();
    if(b.click()) {k2=k; ok4=1; ok3=0; k2.x=(int)random(0,n); k2.y=(int)random(0,n); k2.hp=100; k2.scor=0; initial(); vezi(k2);}
  }
  
  for(i=1;i<=nr1;i++) {
    fill(256,0,0);
    ellipse(950,450+i*50,20,20);
    fill(0);
    text(k.c.in[i],950,450+i*50);
    if(dist(mouseX,mouseY,950,450+i*50)<=20&&mousePressed) {
      for(j=1;j<=nr2;j++) {
        stroke(1);
        line(950,450+i*50,1100,400+j*40);
        text(k.c.w1[i][j],(950+1100)/2,(450+i*50+400+j*40)/2);
      }
    }
  }
  for(i=1;i<=nr2;i++) {
    fill(256,0,0);
    ellipse(1100,400+i*40,20,20);
    fill(0);
    text(k.c.h[i],1100,400+i*40);
    if(dist(mouseX,mouseY,1100,400+i*40)<=20&&mousePressed) {
      for(j=1;j<=nr3;j++) {
        stroke(1);
        line(1100,400+i*40,1250,450+j*50);
        text(k.c.w2[i][j],(1100+1250)/2,(400+i*40+450+j*50)/2);
      }
    }
  }
  for(i=1;i<=nr3;i++) {
    fill(256,0,0);
    ellipse(1250,450+i*50,20,20);
    fill(0);
    text(k.c.out[i],1250,450+i*50);
  }
}

void simuleaza() {
  int j,k=1;
  
  med=0;
  
  for(animal i:a) {
    initial();
    i.scor=0; i.hp=100; i.x=(int)random(0,n); i.y=(int)random(0,n);
    while(i.hp>0) {
      i.traieste();
      plante();
      i.scor++;
    }
    i.scor=i.scor/60;
    med+=i.scor;
    sc[k-1]=i.scor;
    println(k);
    k++;
  }
  med=med/a.size();
  
  sc=sort(sc);
}

void selectie() {
  int i;
  
  for(i=a.size()-1;i>=0;i--) 
    if(a.get(i).scor<sc[90]) a.remove(i);
}

void plante() {
  //b.add(genereaza_planta());
  actualizare_plante();
}

planta genereaza_planta() {
  planta k=new planta(int(random(0,n)),int(random(0,n)));
  return k;
}

void actualizare_plante() {
  int i;
  planta k;
  
  nrp=b.size();
  clean(m,-1);
  
  for(i=nrp-1;i>=0;i--) {
    k=b.get(i);
    m[k.x][k.y]=i;
  }
}

void clean(int[][] m, int x) {
  int i,j;
  
  for(i=0;i<n;i++)
    for(j=0;j<n;j++) m[i][j]=x;
}

int trans(int x) {
  if(x<0) return x+n;
  if(x>=n) return x-n;
  return x;
}

void vezi(animal k) {
  int i,j;
  
  for(i=0;i<n;i++)
    for(j=0;j<n;j++) {
      fill(256,256,256);
      rect(i*l,j*l,l,l);
    }
    
   for(planta p:b) p.show();
   if(k.hp>0) {k.show(); k.scor+=(float)1/60;}
   else {ok4=0; ok3=1;}
   k.traieste();
   plante();
   println(k.hp);
}

void initial() {
  int i;
 
  clean(m,-1);
  b.clear();
  for(i=1;i<=n*n/10;i++) b.add(genereaza_planta());
}

void gen() {
  int ng=a.size(),p1,p2;
  
  if(a.size()!=100) gen++; 
  while(a.size()<100) {
    p1=(int)random(0,ng);
    p2=(int)random(0,ng);
    a.add(cross(a.get(p1),a.get(p2)));
  }
}

animal cross(animal x, animal y) {
  int j,q;
  
  animal w=new animal();
  
  animal k=new animal();
  if(0.01>=random(0,1)) w.bi=random(-1,1);
  else {
    if(0.5<=random(0,1)) w.bi=x.bi;
    else w.bi=y.bi;
  }
  
    
  for(j=1;j<=nr1;j++)
    for(q=1;q<=nr2;q++) 
      if(0.01>=random(0,1)) w.c.w1[j][q]=random(-1,1);
      else {
        if(0.5<=random(0,1)) w.c.w1[j][q]=x.c.w1[j][q];
        else w.c.w1[j][q]=y.c.w1[j][q];
      }
  for(j=1;j<=nr2;j++)
    for(q=1;q<=nr3;q++) 
      if(0.01>=random(0,1)) w.c.w2[j][q]=random(-1,1);
      else {
        if(0.5<=random(0,1)) w.c.w2[j][q]=x.c.w2[j][q];
        else w.c.w2[j][q]=y.c.w2[j][q];
      }
  
  return w;
} 

void auto() {
  
  simuleaza();
  selectie();
  gen();
}

void eco() {
 
}