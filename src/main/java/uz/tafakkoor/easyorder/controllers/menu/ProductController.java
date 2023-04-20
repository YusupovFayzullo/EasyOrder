package uz.tafakkoor.easyorder.controllers.menu;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.tafakkoor.easyorder.domains.menu.Product;
import uz.tafakkoor.easyorder.dtos.menu.product.ProductCreateDTO;
import uz.tafakkoor.easyorder.dtos.menu.product.ProductUpdateDTO;
import uz.tafakkoor.easyorder.services.menu.ProductService;

import java.util.Collection;
import java.util.List;

@RestController
@ParameterObject
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
@Tag(name = "Product", description = "Product API")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "This API used for creating a product", responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Product created"), @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Product not created")})
    @PostMapping(value = "/create", produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> createProduct(@ModelAttribute ProductCreateDTO dto, @RequestParam("file") List<MultipartFile> files) {
        Product product = productService.createProduct(dto, files);
        return ResponseEntity.status(201).body(product);
    }


    @Operation(summary = "This API used for getting a product by id", responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Product found"), @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found")})
    @GetMapping(produces = "application/json")
    public ResponseEntity<Product> getProductById(Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok().body(product);
    }

    @Operation(summary = "This api get all products by category id", responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Categories found"), @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Products not found")})
    @GetMapping(value = "/{categoryID}", produces = "application/json")
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable Long categoryID) {
        List<Product> products = productService.getAllProducts(categoryID);
        return ResponseEntity.ok().body(products);
    }


    @Operation(summary = "This API used for deleting a product by id", responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Product deleted"), @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found")})
    @DeleteMapping(produces = "application/json")
    public ResponseEntity<Boolean> deleteProduct(Long id) {
        boolean productID = productService.deleteProduct(id);
        return ResponseEntity.status(202).body(productID);
    }


    @Operation(summary = "This API used for updating a product", responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Product updated"), @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found")})
    @PutMapping(value = "/update", produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> updateProduct(@ModelAttribute ProductUpdateDTO dto, @RequestParam Collection<MultipartFile> imageFiles, Long productID) {
        Product product = productService.updateProduct(dto, imageFiles, productID);
        return ResponseEntity.status(200).body(product);
    }


}
