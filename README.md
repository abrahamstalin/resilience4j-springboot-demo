# resilience4j-springboot-demo

Este es un proyecto de demostración creado por **Abraham Aguilar**, con más de 20 años de experiencia en desarrollo de software empresarial, que muestra cómo usar **Resilience4j** en proyectos Spring Boot para mejorar la resiliencia ante fallas en servicios de terceros.

## 🚀 Objetivo

Probar de forma didáctica las capacidades de **Resilience4j** integradas a un proyecto Spring Boot 3.4.4 usando Maven, incluyendo:

- Circuit Breaker
- Retry
- Fallback automático
- Health Check con Actuator

---

## 🛠 Requisitos

- Java 17
- Maven 3.8+
- IDE como Eclipse, IntelliJ o VS Code

---

## ⚙️ Cómo ejecutar

1. Clona el proyecto:
   ```bash
   git clone https://github.com/abrahamstalin/resilience4j-springboot-demo.git
   cd resilience4j-springboot-demo
   ```

2. Ejecuta con Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

   o desde tu IDE como una aplicación Spring Boot.

3. La app estará disponible en `http://localhost:8080`

---

## 📡 Endpoints disponibles

| Endpoint                  | Descripción                                                  |
|---------------------------|--------------------------------------------------------------|
| `/joke`                   | Llama a un servicio externo que siempre falla.               |
| `/actuator/health`        | Muestra el estado del Circuit Breaker y otros componentes.   |
| `/actuator/health/circuitBreakers` | (si está habilitado) Verifica estado detallado.       |

---

## 💥 Cómo forzar fallas y ver la resiliencia

El servicio llama a una URL no válida: `http://10.255.255.1/random_joke`, lo cual genera un `timeout`.

### ¿Qué sucede?

- Se intenta 3 veces (Retry)
- Al fallar, entra en modo **Circuit Breaker**
- Se ejecuta el método `fallbackJoke`

### Resultado esperado:

```json
{
  "id": 0,
  "type": "fallback",
  "setup": "No se pudo obtener el chiste.",
  "punchline": "El servicio externo está caído."
}
```

Y el estado del circuito en `/actuator/health`:

```json
"jokeService": {
  "status": "OPEN"
}
```

---

## 🧠 Conclusiones

- **Resilience4j** permite desacoplar la lógica de resiliencia de tu negocio.
- Puedes proteger tu backend ante caídas de servicios externos.
- Ideal para microservicios, gateways, sidecars o servicios API de dominio.

Este demo es la base para entender conceptos que pueden escalarse a arquitecturas complejas, incluyendo WebFlux, RateLimiter, Bulkhead y más.

---

## 📅 Fecha de creación

2025-04-09

---

## 🔗 Más sobre mí

**Abraham Aguilar**  
CEO de [Ajal Technology](https://ajaltechnology.com)  
Especialista en Java, arquitecturas empresariales y automatización con IA.
