package mixer_shops.mixer.service.category;

import java.util.List;

import org.springframework.stereotype.Service;

import mixer_shops.mixer.exceptions.AlreadyExistsException;
import mixer_shops.mixer.exceptions.ResourcesException;
import mixer_shops.mixer.model.Category;
import mixer_shops.mixer.repository.CategoryRepository;

@Service
public class CategoryService implements ICategoryService{
	private final CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Category getCategoryById(Long id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id)
				.orElseThrow(() -> new ResourcesException("Category not found!"));
	}

	@Override
	public Category getCategoryByName(String name) {
		// TODO Auto-generated method stub
		return categoryRepository.findByName(name);
	}

	@Override
	public Category addCategory(Category category) {
		// TODO Auto-generated method stub
		Category existingCategory = categoryRepository.findByName(category.getName());
	    if (existingCategory != null) {
	        throw new AlreadyExistsException("Category already exists!");
	    }
	    return categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category, Long categoryId) {
		// TODO Auto-generated method stub
		Category updateCategory = categoryRepository.findById(categoryId)
				.map(existingCategory -> {
					existingCategory.setName(category.getName());
					return categoryRepository.save(existingCategory);
				}).orElseThrow(() -> new ResourcesException("Category not found!"));
		return updateCategory;
	}

	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	@Override
	public void deleteCategoryById(Long id) {
		// TODO Auto-generated method stub
		categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete, () -> { throw new ResourcesException("Category not found!");});
	}

}
