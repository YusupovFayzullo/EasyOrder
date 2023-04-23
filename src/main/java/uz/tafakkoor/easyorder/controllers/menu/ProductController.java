package uz.tafakkoor.easyorder.controllers.menu;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.tafakkoor.easyorder.domains.menu.Product;
import uz.tafakkoor.easyorder.dtos.menu.product.ProductCreateDTO;
import uz.tafakkoor.easyorder.dtos.menu.product.ProductUpdateDTO;
import uz.tafakkoor.easyorder.services.menu.ProductService;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
@Tag(name = "Product", description = "Product API")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "This API used for creating a product", responses = {
            @ApiResponse(responseCode = "201", description = "Product created"),
            @ApiResponse(responseCode = "400", description = "Product not created")})
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createProduct(@Valid ProductCreateDTO dto) {
        Product product = productService.createProduct(dto);
        return ResponseEntity.status(201).body(product);
    }


    @Operation(summary = "This API used for getting a product by id", responses = {
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found")})
    @GetMapping(produces = "application/json")
    public ResponseEntity<Product> getProductById(Long id, Long categoryID) {
        Product product = productService.getProductById(id, categoryID);
        return ResponseEntity.ok().body(product);
    }

    @Operation(summary = "This api get all products by category id", responses = {
            @ApiResponse(responseCode = "200", description = "Categories found"),
            @ApiResponse(responseCode = "404", description = "Products not found")})
    @GetMapping(value = "/{categoryID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable Long categoryID) {
        List<Product> products = productService.getAllProducts(categoryID);
        return ResponseEntity.ok().body(products);
    }


    @Operation(summary = "This API used for deleting a product by id", responses = {
            @ApiResponse(responseCode = "204", description = "Product deleted"),
            @ApiResponse(responseCode = "404", description = "Product not found")})
    @DeleteMapping(produces = "application/json")
    public ResponseEntity<Boolean> deleteProduct(Long id) {
        boolean productID = productService.deleteProduct(id);
        return ResponseEntity.status(204).body(productID);
    }


    @Operation(summary = "This API used for updating a product", responses = {
            @ApiResponse(responseCode = "204", description = "Product updated"),
            @ApiResponse(responseCode = "404", description = "Product not found")})
    @PutMapping(value = "/update", produces = "application/json")
    public ResponseEntity<Product> updateProduct(@Valid ProductUpdateDTO dto) {
        Product product = productService.updateProduct(dto);
        return ResponseEntity.status(204).body(product);
    }


}
