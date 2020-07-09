import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Evo extends PApplet {

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

public void setup(){
  
}

public void draw() {
  background(256,256,256);
  frameRate(60);
  
  panou1();
  panou2();
}

public void panou1() {
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
public void panou2() {
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

public void genereaza_populatie() {
  
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

public void analiza(animal k) {
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

public void simuleaza() {
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

public void selectie() {
  int i;
  
  for(i=a.size()-1;i>=0;i--) 
    if(a.get(i).scor<sc[90]) a.remove(i);
}

public void plante() {
  //b.add(genereaza_planta());
  actualizare_plante();
}

public planta genereaza_planta() {
  planta k=new planta(PApplet.parseInt(random(0,n)),PApplet.parseInt(random(0,n)));
  return k;
}

public void actualizare_plante() {
  int i;
  planta k;
  
  nrp=b.size();
  clean(m,-1);
  
  for(i=nrp-1;i>=0;i--) {
    k=b.get(i);
    m[k.x][k.y]=i;
  }
}

public void clean(int[][] m, int x) {
  int i,j;
  
  for(i=0;i<n;i++)
    for(j=0;j<n;j++) m[i][j]=x;
}

public int trans(int x) {
  if(x<0) return x+n;
  if(x>=n) return x-n;
  return x;
}

public void vezi(animal k) {
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

public void initial() {
  int i;
 
  clean(m,-1);
  b.clear();
  for(i=1;i<=n*n/10;i++) b.add(genereaza_planta());
}

public void gen() {
  int ng=a.size(),p1,p2;
  
  if(a.size()!=100) gen++; 
  while(a.size()<100) {
    p1=(int)random(0,ng);
    p2=(int)random(0,ng);
    a.add(cross(a.get(p1),a.get(p2)));
  }
}

public animal cross(animal x, animal y) {
  int j,q;
  
  animal w=new animal();
  
  animal k=new animal();
  if(0.01f>=random(0,1)) w.bi=random(-1,1);
  else {
    if(0.5f<=random(0,1)) w.bi=x.bi;
    else w.bi=y.bi;
  }
  
    
  for(j=1;j<=nr1;j++)
    for(q=1;q<=nr2;q++) 
      if(0.01f>=random(0,1)) w.c.w1[j][q]=random(-1,1);
      else {
        if(0.5f<=random(0,1)) w.c.w1[j][q]=x.c.w1[j][q];
        else w.c.w1[j][q]=y.c.w1[j][q];
      }
  for(j=1;j<=nr2;j++)
    for(q=1;q<=nr3;q++) 
      if(0.01f>=random(0,1)) w.c.w2[j][q]=random(-1,1);
      else {
        if(0.5f<=random(0,1)) w.c.w2[j][q]=x.c.w2[j][q];
        else w.c.w2[j][q]=y.c.w2[j][q];
      }
  
  return w;
} 

public void auto() {
  
  simuleaza();
  selectie();
  gen();
}

public void eco() {
 
}
//creier-input: 1-ultimul input vizual; 2-actualul input vizual; 3-ultima directie; 4-hp/100; 5-bias
int nr1=5,nr2=10,nr3=5;

class animal {
  int x,y,gen,dir;
  float hp,bi,scor;
  creier c=new creier(nr1,nr2,nr3);
  int[] dx={-1,0,1,0},dy={0,-1,0,1};
  
  public void traieste() {
    c.in[1]=c.in[2];
    c.in[2]=m[trans(x+dx[dir])][trans(y+dy[dir])];
    c.in[3]=dir/3;
    c.in[4]=hp/100;
    c.in[5]=bi;
    
    c.output();
    
    hp=hp-0.25f;
    
    if(c.out[1]!=1) { //sta
      if(c.out[2]==1) { //rotire dreapta
        dir++;
        if(dir==4) dir=0;
      }
      if(c.out[3]==1) { //rotire stanga
        dir--;
        if(dir==-1) dir=3;
      }
      if(c.out[4]==1) { //merge
        hp-=0.25f;
        x+=dx[dir]; y+=dy[dir];
        x=trans(x); y=trans(y);
      }
      if(c.out[5]==1) { //mananca
        actualizare_plante();
        if(m[x][y]>=0) {
          hp+=20;
          if(hp>100) hp=100;
          b.remove(m[x][y]);
          actualizare_plante();
        }
      }
    }
  }
  
  public void show() {
    fill(256,0,0);
    rect(x*l,y*l,l,l);
    fill(0);
    line(x*l+l/2,y*l+l/2,(x+dx[dir])*l+l/2,(y+dy[dir])*l+l/2);
  }
  
  
}
int lb=150,hb=50;

class buton {
  float x,y;
  String a;
  
  buton(int x_,int y_, String a_) {
    x=x_; y=y_; a=a_; 
  }
  
  public boolean over() {
    if(mouseX>=x&&mouseX<=x+lb&&mouseY>=y&&mouseY<=y+hb) return true;
    else return false;
  }
  
  public boolean click() {
    if(over()&&mousePressed) return true;
    else return false;
  }
  
  public void show() {
    if(over()) fill(100);
    else fill(150);
    rect(x,y,lb,hb);
    
    fill(0);
    textAlign(CENTER,CENTER);
    textSize(16);
    text(a,x,y,lb,hb);
  }
}
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
  
  public void calculh() {
    int i,j;
    
    for(i=1;i<=nr2;i++) h[i]=0;
    
    for(i=1;i<=nr1;i++) 
      for(j=1;j<=nr2;j++) 
        h[j]+=in[i]*w1[i][j];
  }
  
  public void output() {
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
class planta{
  int x,y,id;
  
  planta(int x_, int y_) {
    x=x_; y=y_;
  }
  
  public void show() {
    fill(0,256,0);
    rect(x*l,y*l,l,l);
  }
}
  public void settings() {  size(1300,900); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Evo" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
