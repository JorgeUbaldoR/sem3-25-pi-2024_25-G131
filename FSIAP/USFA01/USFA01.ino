#include <Adafruit_Sensor.h>
#include <DHT.h>

// Definir pino de dados e o tipo de sensor
#define DHTPIN 22      // Pino de dados conectado ao GPIO 21
#define DHTTYPE DHT11  // Tipo do sensor DHT

DHT dht(DHTPIN, DHTTYPE);

void setup() {
  Serial.begin(9600);  // Inicia a comunicação serial
  Serial.println("Iniciando o sensor DHT11...");
  dht.begin();         // Inicializa o sensor DHT11
}

void loop() {
  // Lê a umidade
  float humidade = dht.readHumidity();
  // Lê a temperatura em Celsius
  float temperatura = dht.readTemperature();

  // Verifica se as leituras falharam
  if (isnan(humidade) || isnan(temperatura)) {
    Serial.println("Falha na leitura do sensor DHT11");
    return;
  }

  // Exibe as leituras no Monitor Serial
  Serial.print("Humidade: ");
  Serial.print(humidade);
  Serial.print(" %\t");
  Serial.print("Temperatura: ");
  Serial.print(temperatura);
  Serial.println(" *C");

  delay(2000);  // Aguarda 2 segundos para a próxima leitura
}
