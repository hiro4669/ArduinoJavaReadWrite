byte data[10];

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);

  int i;
  for (i = 0; i < 10; i++) {
    data[i] = 0x61 + i;
  }
}

void loop() {
  // put your main code here, to run repeatedly:

  Serial.write(data, 10);
  int i, rd;
  if (Serial.available() > 0) {
    rd = Serial.read();
    data[0] = rd & 0xff;
  }

  //Serial.println("Hello World!!!");
  delay(1000);

}
