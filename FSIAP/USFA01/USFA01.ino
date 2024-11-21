#include <DHT.h>

#define DHTPIN 15        // Define o pino do sensor DHT11 (GPIO 15)
#define DHTTYPE DHT11    // Define o tipo do sensor como DHT11

#define TEMP_LED_PIN 16  // Pino do LED de temperatura (GPIO 16)
#define HUM_LED_PIN 14   // Pino do LED de umidade (GPIO 14)

DHT dht(DHTPIN, DHTTYPE);

float initialTemperature;
float initialHumidity;
unsigned long startTime;

// Variáveis para armazenar o estado anterior dos LEDs
bool prevTempLedStatus = false;
bool prevHumLedStatus = false;

void setup() {
  Serial.begin(9600);
  dht.begin();
  Serial.println("Iniciando leitura de temperatura e umidade...");

  pinMode(TEMP_LED_PIN, OUTPUT);  // Define o pino do LED de temperatura como saída
  pinMode(HUM_LED_PIN, OUTPUT);   // Define o pino do LED de umidade como saída

  // Realiza leituras iniciais para definir os valores iniciais de temperatura e umidade
  delay(2000); // Aguarda o sensor estabilizar
  initialTemperature = dht.readTemperature();
  initialHumidity = dht.readHumidity();
  startTime = millis();

  Serial.print("Temperatura inicial: ");
  Serial.print(initialTemperature);
  Serial.println(" *C");

  Serial.print("Umidade inicial: ");
  Serial.print(initialHumidity);
  Serial.println(" %");

  // Cabeçalho para o registro
  Serial.println("Tempo (s), Temperatura (*C), Umidade (%), LED Temp, LED Hum, Mensagem");
}

void loop() {
  // Aguarde 1 segundo entre as leituras
  delay(1000);

  // Leitura da temperatura e umidade
  float currentTemperature = dht.readTemperature();
  float currentHumidity = dht.readHumidity();

  // Verificação de erro nas leituras
  if (isnan(currentHumidity) || isnan(currentTemperature)) {
    Serial.println("Falha ao ler do sensor DHT11!");
    return;
  }

  // Calcula o tempo decorrido em segundos
  unsigned long elapsedTime = (millis() - startTime) / 1000;

  // Verifica se a temperatura está 5 ºC acima do valor inicial
  bool tempLedStatus = currentTemperature >= initialTemperature + 5;
  if (tempLedStatus != prevTempLedStatus) {
    digitalWrite(TEMP_LED_PIN, tempLedStatus ? HIGH : LOW);
  }

  // Verifica se a umidade está 5% acima do valor inicial
  bool humLedStatus = currentHumidity >= initialHumidity + (initialHumidity * 0.05);
  if (humLedStatus != prevHumLedStatus) {
    digitalWrite(HUM_LED_PIN, humLedStatus ? HIGH : LOW);
  }

  // Registro no Serial Monitor
  Serial.print(elapsedTime);
  Serial.print(" s, ");
  Serial.print(currentTemperature);
  Serial.print(" *C, ");
  Serial.print(currentHumidity);
  Serial.print(" %, ");
  Serial.print(tempLedStatus ? "ON" : "OFF");
  Serial.print(", ");
  Serial.print(humLedStatus ? "ON" : "OFF");

  // Adiciona mensagem caso o estado dos LEDs tenha mudado
  if (tempLedStatus != prevTempLedStatus) {
    Serial.print(tempLedStatus ? " | LED de temperatura acendeu" : " | LED de temperatura apagou");
  }
  if (humLedStatus != prevHumLedStatus) {
    Serial.print(humLedStatus ? " | LED de umidade acendeu" : " | LED de umidade apagou");
  }

  Serial.println();

  // Atualiza o estado anterior dos LEDs
  prevTempLedStatus = tempLedStatus;
  prevHumLedStatus = humLedStatus;
}
