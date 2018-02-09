#include <Servo.h>

Servo ser1;

int home_ser1 = 90;
int pos = 0;

void setup() {
  // put your setup code here, to run once:
  ser1.attach(5);
  ser1.write(home_ser1);
  Serial.begin(9600);
}
////
void loop() {
  // put your main code here, to run repeatedly:
  Serial.println(" -- START " + ser1.read());
  // Serial.println(ser1.read());
  
  delay(200);
  
  for (pos = 0; pos <= 180; pos += 1) { // goes from 0 degrees to 180 degrees
    // in steps of 1 degree
    ser1.write(pos);              // tell servo to go to position in variable 'pos'
    delay(15);                       // waits 15ms for the servo to reach the position
    Serial.println(ser1.read());
  }
  for (pos = 180; pos >= 0; pos -= 1) { // goes from 180 degrees to 0 degrees
    ser1.write(pos);              // tell servo to go to position in variable 'pos'
    delay(15);                       // waits 15ms for the servo to reach the position
    Serial.println(ser1.read());
  }
  
  delay(200);
  Serial.println(" -- END " + ser1.read());
  
}

