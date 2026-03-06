Genial, ya con estos 3 ficheros puedo darte una review bastante clara de tu **capa application** (input port \+ DTO \+ orquestación).

## **✅ 1\) `PriceResponse` (DTO)**

Tu DTO está bien definido y tiene exactamente los campos que pide el enunciado (product, brand, tarifa, fechas, precio y moneda).

### **Lo que mejoraría para “prueba técnica top”**

**A) Convertirlo a `record` (recomendado)**  
Aquí es donde más suma usar Java moderno sin meter riesgo:

public record PriceResponse(  
 Long productId,  
 Integer brandId,  
 Integer priceList,  
 LocalDateTime startDate,  
 LocalDateTime endDate,  
 BigDecimal price,  
 String curr  
) {}

Beneficios:

* Inmutabilidad  
* Menos ruido (sin Lombok)  
* Más “clean” para entrevistas

**B) Si mantienes clase \+ Lombok**

* `@Data` genera `equals/hashCode/toString` y setters; para DTO de salida no es “peligroso”, pero en entrevistas a veces prefieren `@Value` (inmutable).  
* `@Builder` está bien si lo construyes manualmente en el mapper, pero si cambias a `record` ya no lo necesitas.

## **✅ 2\) `GetPriceUseCase` (Input Port)**

Muy correcto: interfaz en application, devuelve `Optional` y recibe los parámetros del caso de uso.

### **Matices que pueden mejorar la nota**

**A) Orden de parámetros**  
En el enunciado el orden lógico suele ser:  
`applicationDate, productId, brandId`

TEST JAVA 2020

No es obligatorio, pero ayuda a que “huela” alineado con el requisito.

**B) Usar un `record` para la request del caso de uso (opcional, pro)**  
Esto evita *method signature* larga y te permite validar mejor:

public record GetPriceQuery(Integer brandId, Long productId, LocalDateTime applicationDate) {}  
Optional\<PriceResponse\> execute(GetPriceQuery query);

Suma puntos si lo mantienes simple.

**C) Validación**  
Ahora mismo el contrato acepta nulls. Idealmente:

* o validas en controller con `@RequestParam @NotNull`  
* o dentro del use case con `Objects.requireNonNull(...)`

No hace falta complicar, pero al menos una estrategia clara.

## **✅ 3\) `GetPriceUseCaseImpl` (Orquestación)**

Esto está muy bien: no tiene Spring annotations, depende del port y hace mapping a DTO.

### **Lo que está perfecto**

* **Core limpio** (sin `@Service`)  
* **Optional pipeline** simple  
* **No hay lógica de negocio mezclada**: orquesta, delega, mapea.

### **Pequeño detalle: el mapper en application**

Ahora `GetPriceUseCaseImpl` depende de `com.inditex.marketdata.mapper.PriceMapper`.

Esto puede estar bien si `mapper` es una librería “neutral”, pero en hexagonal estricta hay 2 opciones más limpias:

1. **Mapping a DTO dentro del controller** (application devuelve dominio)  
   * Use case devuelve `Optional<Price>` (dominio)  
   * Controller usa mapper a `PriceResponse`  
   * Pro: application no conoce DTOs  
   * Contra: controller se “engorda” un pelín  
2. **Mantener tu enfoque (válido en esta prueba)**  
   * Use case devuelve DTO ya listo  
   * Pro: controller súper simple  
   * Contra: application acoplada a DTO (pero DTO es de la app, no de infra)

