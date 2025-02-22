package mixer_shops.mixer.service.category;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import mixer_shops.mixer.dto.CategoryDto;
import mixer_shops.mixer.exceptions.ResourcesException;
import mixer_shops.mixer.model.Category;
import mixer_shops.mixer.repository.CategoryRepository;
import mixer_shops.mixer.request.AddCategoryRequest;
import mixer_shops.mixer.request.UpdateCategoryRequest;

@Service
public class CategoryService implements ICategoryService{
	private final CategoryRepository categoryRepository;
	private final ModelMapper modelMapper;

	public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		super();
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public CategoryDto getCategoryById(Long id) {
		// TODO Auto-generated method stub
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourcesException("Category not found!"));
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryByName(String name) {
		// TODO Auto-generated method stub
		Category category = categoryRepository.findByName(name);
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto addCategory(AddCategoryRequest request) {
		// TODO Auto-generated method stub
		Category category = categoryRepository.findByName(request.getName());
	    if (category == null) {
	    	category = new Category();
	    	category.setName(request.getName());
	    	categoryRepository.save(category);
	    	categoryRepository.flush();
	    }
	    return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(UpdateCategoryRequest request, Long categoryId) {
		// TODO Auto-generated method stub
		Category updateCategory = categoryRepository.findById(categoryId)
				.map(existingCategory -> {
					existingCategory.setName(request.getName());
					return categoryRepository.save(existingCategory);
				}).orElseThrow(() -> new ResourcesException("Category not found!"));
		return modelMapper.map(updateCategory, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		// TODO Auto-generated method stub
		List<Category> categories = categoryRepository.findAll();
		return categories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).toList();
	}

	@Override
	public void deleteCategoryById(Long id) {
		// TODO Auto-generated method stub
		categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete, () -> { throw new ResourcesException("Category not found!");});
	}

}
