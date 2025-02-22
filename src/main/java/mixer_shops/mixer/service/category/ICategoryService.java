package mixer_shops.mixer.service.category;

import java.util.List;

import mixer_shops.mixer.dto.CategoryDto;
import mixer_shops.mixer.request.AddCategoryRequest;
import mixer_shops.mixer.request.UpdateCategoryRequest;

public interface ICategoryService {
	CategoryDto getCategoryById(Long id);
	CategoryDto getCategoryByName(String name);
	CategoryDto addCategory(AddCategoryRequest request);
	CategoryDto updateCategory(UpdateCategoryRequest request, Long categoryId);
	List<CategoryDto> getAllCategories();
	void deleteCategoryById(Long id);
}
