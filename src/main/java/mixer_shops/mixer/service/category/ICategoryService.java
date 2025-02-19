package mixer_shops.mixer.service.category;

import java.util.List;

import mixer_shops.mixer.model.Category;

public interface ICategoryService {
	Category getCategoryById(Long id);
	Category getCategoryByName(String name);
	Category addCategory(Category category);
	Category updateCategory(Category category, Long categoryId);
	List<Category> getAllCategories();
	void deleteCategoryById(Long id);
}
