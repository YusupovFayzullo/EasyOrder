package uz.tafakkoor.easyorder.controllers.menu;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.tafakkoor.easyorder.domains.menu.Category;
import uz.tafakkoor.easyorder.dtos.menu.CategoryCreateDTO;
import uz.tafakkoor.easyorder.services.menu.CategoryService;


@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryCreateDTO dto) {
        Category category = categoryService.createCategory(dto);
        return ResponseEntity.status(201).body(category);
    }

}
