# **Objetivo de la Opción A**

* `infrastructure` **no** depende de DTOs.

* El mapeo queda dividido por responsabilidades:

  * **Persistencia → Dominio** (infra/repository)

  * **Dominio → DTO** (infra/controller o infra/mapper-web)

Y todo con métodos `from(...)` para que quede limpio y consistente.

---

# **1\) Crear mapper de persistencia: `PriceEntityMapper`**

📍 `com.inditex.marketdata.infrastructure.repository.mapper` *(o `infrastructure.mapper` si prefieres plano)*

### **Nuevo fichero: `PriceEntityMapper.java`**

package com.inditex.marketdata.infrastructure.repository.mapper;

import com.inditex.marketdata.domain.model.Price;  
import com.inditex.marketdata.infrastructure.repository.PriceEntity;

import java.util.Objects;

public final class PriceEntityMapper {

   private PriceEntityMapper() {}

   public static Price from(PriceEntity entity) {  
       Objects.requireNonNull(entity, "PriceEntity must not be null");

       return new Price(  
               entity.getId(),  
               entity.getBrandId(),  
               entity.getStartDate(),  
               entity.getEndDate(),  
               entity.getPriceList(),  
               entity.getProductId(),  
               entity.getPriority(),  
               entity.getPrice(),  
               entity.getCurr()  
       );  
   }  
}

✅ Nota: aquí el mapper **solo** conoce `PriceEntity` y `Price` (core \+ infra).

---

# **2\) Crear mapper web: `PriceResponseMapper`**

📍 `com.inditex.marketdata.infrastructure.controller.mapper`

### **Nuevo fichero: `PriceResponseMapper.java`**

package com.inditex.marketdata.infrastructure.controller.mapper;

import com.inditex.marketdata.application.dto.PriceResponse;  
import com.inditex.marketdata.domain.model.Price;

import java.util.Objects;

public final class PriceResponseMapper {

   private PriceResponseMapper() {}

   public static PriceResponse from(Price price) {  
       Objects.requireNonNull(price, "Price must not be null");

       return new PriceResponse(  
               price.productId(),  
               price.brandId(),  
               price.priceList(),  
               price.startDate(),  
               price.endDate(),  
               price.price(),  
               price.curr()  
       );  
   }  
}

✅ Nota: aquí el mapper **solo** conoce `Price` y `PriceResponse` (core \+ web DTO).  
 ✅ No aparece `PriceEntity` aquí → separación correcta.

---

# **3\) Modificar `PriceRepositoryAdapter` para usar `PriceEntityMapper`**

Actualmente estás usando un mapper “global”. Cambiamos import y llamada.

### **Cambios en `PriceRepositoryAdapter.java`**

* Quitar import del mapper antiguo

* Importar `PriceEntityMapper`

* Mapear así:

return jpaPriceRepository  
       .findTopByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(  
               applicationDate, productId, brandId, applicationDateEnd  
       )  
       .map(PriceEntityMapper::from);

📌 Resultado: adapter solo transforma Entity → Domain. Nada de DTOs.

---

# **4\) Modificar `PriceController` para usar `PriceResponseMapper`**

En tu controller ahora mismo mapeas a DTO, perfecto; solo cambiaremos a usar el nuevo mapper:

.map(PriceResponseMapper::from)

✅ Controller queda limpio y el mapper queda ubicado “web”.

---

# **5\) Eliminar el mapper antiguo “global”**

Si ahora mismo tienes algo tipo:  
 `com.inditex.marketdata.infrastructure.mapper.PriceMapper` (mezclando Entity y DTO)

👉 Eliminarlo.

Y también eliminar el paquete viejo `com.inditex.marketdata.mapper` si aún existiera.

---

# **6\) Estructura final recomendada**

Te quedaría así:

infrastructure  
├─ controller  
│   ├─ PriceController  
│   └─ mapper  
│       └─ PriceResponseMapper  
├─ repository  
│   ├─ PriceEntity  
│   ├─ JpaPriceRepository  
│   ├─ PriceRepositoryAdapter  
│   └─ mapper  
│       └─ PriceEntityMapper  
└─ config  
    └─ PriceConfig  
---

# **7\) Tests: no deberían cambiar**

* Los tests de integración al endpoint no deberían requerir cambios (mismo output).

* Si alguno verifica JSON exacto, seguirá igual.

---

# **Bonus (opcional pero muy “senior”)**

Si quieres que quede aún más redondo:

* marca ambos mappers como `final` \+ constructor privado (ya lo puse)

* usa `Objects.requireNonNull` (ya lo puse)

* evita `@Component` en mappers (ya lo evitamos)

Esto demuestra intención: **son utilidades puras**, no dependencias del framework.

