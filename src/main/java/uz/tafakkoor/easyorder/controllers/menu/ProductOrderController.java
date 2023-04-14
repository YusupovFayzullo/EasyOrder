package uz.tafakkoor.easyorder.controllers.menu;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.tafakkoor.easyorder.domains.menu.ProductOrder;
import uz.tafakkoor.easyorder.dtos.menu.ProductOrderCreateDTO;
import uz.tafakkoor.easyorder.dtos.menu.ProductOrderUpdateDTO;
import uz.tafakkoor.easyorder.services.menu.ProductOrderService;


@RestController
@RequestMapping("/api/v1/product-order")
@RequiredArgsConstructor
@Tag(
        name = "Product Order Controller",
        description = "This controller created for playing with ProductOrder entity"
)
public class ProductOrderController {
    private final ProductOrderService productOrderService;

    @Operation(summary = "This API used for creating a ProductOrder",
            description = "This endpoint was designed for creating a new ProductOrder")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ProductOrder Successfully Created",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ProductOrder.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    })
    @PostMapping("/create")
    public ResponseEntity<ProductOrder> create(@RequestBody ProductOrderCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productOrderService.create(dto));
    }

    @Operation(summary = "This API used for updating a ProductOrder",
            description = "This endpoint was designed for updating an existing ProductOrder")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ProductOrder Successfully Updated",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ProductOrder.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    })
    @PutMapping("/update")
    public ResponseEntity<ProductOrder> update(@RequestBody ProductOrderUpdateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productOrderService.update(dto));
    }

    @Operation(summary = "This API used for get an existing ProductOrder",
            description = "This endpoint was designed for getting an existing ProductOrder")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ProductOrder.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    })
    @GetMapping("/get/{id:.*}")
    public ResponseEntity<ProductOrder> get(@PathVariable Long id) {
        return ResponseEntity.ok(productOrderService.getById(id));
    }

    @Operation(summary = "This API used for deleting an existing ProductOrder",
            description = "This endpoint was designed for deleting an existing ProductOrder")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ProductOrder Successfully Deleted",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ProductOrder.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    })
    @DeleteMapping("/delete/{id:.*}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productOrderService.delete(id);
        return ResponseEntity.ok().build();
    }

}
