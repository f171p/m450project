package ch.tbz.recipe.planner.test;

import ch.tbz.recipe.planner.domain.Ingredient;
import ch.tbz.recipe.planner.domain.Recipe;
import ch.tbz.recipe.planner.domain.Unit;
import ch.tbz.recipe.planner.entities.IngredientEntity;
import ch.tbz.recipe.planner.entities.RecipeEntity;
import ch.tbz.recipe.planner.mapper.RecipeEntityMapper;
import ch.tbz.recipe.planner.repository.RecipeRepository;
import ch.tbz.recipe.planner.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

@SpringBootTest
public class RecipeServiceTest {
    @Mock
    private RecipeEntityMapper mapper;

    @Mock
    private RecipeRepository repository;

    @InjectMocks
    private RecipeService recipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRecipes() {
        List<IngredientEntity> ingredientEntities = List.of(
                new IngredientEntity(UUID.randomUUID(), "Cucumber", "Comment", Unit.PIECE, 12),
                new IngredientEntity(UUID.randomUUID(), "Tomato", "Comment", Unit.PIECE, 10)
        );
        List<Ingredient> ingredients = List.of(
                new Ingredient(UUID.randomUUID(), "Cucumber", "Comment", Unit.PIECE, 12),
                new Ingredient(UUID.randomUUID(), "Tomato", "Comment", Unit.PIECE, 10)
        );

        List<RecipeEntity> recipeEntities = List.of(
                new RecipeEntity(UUID.randomUUID(), "Recipe1", "Description1", "url", ingredientEntities),
                new RecipeEntity(UUID.randomUUID(), "Recipe2", "Description2", "url", ingredientEntities)
        );

        List<Recipe> expectedRecipes = List.of(
                new Recipe(UUID.randomUUID(), "Recipe1", "Description1", "url", ingredients),
                new Recipe(UUID.randomUUID(), "Recipe2", "Description2", "url", ingredients)
        );

        when(repository.findAll()).thenReturn(recipeEntities);
        when(mapper.entityToDomain(any(RecipeEntity.class)))
                .thenReturn(expectedRecipes.get(0), expectedRecipes.get(1));

        List<Recipe> actualRecipes = recipeService.getRecipes();

        assertEquals(expectedRecipes, actualRecipes);
        verify(repository, times(1)).findAll();
        verify(mapper, times(recipeEntities.size())).entityToDomain(any(RecipeEntity.class));
    }

    @Test
    void testGetRecipeById() {
        UUID recipeId = UUID.randomUUID();
        UUID ingredientId = UUID.randomUUID();
        RecipeEntity recipeEntity = new RecipeEntity(recipeId, "Recipe1", "Description1", "url", List.of(new IngredientEntity(ingredientId, "Cabbage", "Comment", Unit.PIECE, 5)));
        Recipe expectedRecipe = new Recipe(recipeId, "Recipe1", "Description1", "url", List.of(new Ingredient(ingredientId, "Cabbage", "Comment", Unit.PIECE, 5)));

        when(repository.findById(recipeId)).thenReturn(Optional.of(recipeEntity));
        when(mapper.entityToDomain(recipeEntity)).thenReturn(expectedRecipe);

        Recipe actualRecipe = recipeService.getRecipeById(recipeId);

        assertEquals(expectedRecipe, actualRecipe);
        verify(repository, times(1)).findById(recipeId);
        verify(mapper, times(1)).entityToDomain(recipeEntity);
    }

    @Test
    void testGetRecipes_EmptyList() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<Recipe> actualRecipes = recipeService.getRecipes();

        assertTrue(actualRecipes.isEmpty());
        verify(repository, times(1)).findAll();
        verify(mapper, never()).entityToDomain(any());
    }

    @Test
    void testGetRecipeById_NotFound() {
        UUID recipeId = UUID.randomUUID();

        when(repository.findById(recipeId)).thenReturn(Optional.empty());

        Recipe actualRecipe = recipeService.getRecipeById(recipeId);

        assertNull(actualRecipe);
        verify(repository, times(1)).findById(recipeId);
    }
    @Test
    void testGetRecipeById_NullId() {
        Recipe actualRecipe = recipeService.getRecipeById(null);
        assertNull(actualRecipe);
    }

    @Test
    void testAddRecipe() {
        UUID recipeId = UUID.randomUUID();
        List<Ingredient> ingredients = List.of(new Ingredient(UUID.randomUUID(), "Salad", "Comment", Unit.GRAMM, 100));
        List<IngredientEntity> ingredientEntities = List.of(new IngredientEntity(UUID.randomUUID(), "Salad", "Comment", Unit.GRAMM, 100));
        Recipe recipe = new Recipe(recipeId, "Recipe1", "Description1", "url", ingredients) ;
        RecipeEntity recipeEntity = new RecipeEntity(recipeId, "Recipe1", "Description1", "url", ingredientEntities);
        RecipeEntity savedEntity = new RecipeEntity(recipeId, "Recipe1", "Description1", "url", ingredientEntities);
        Recipe expectedRecipe = new Recipe(recipeId,"Recipe1", "Description1", "url", ingredients);

        when(mapper.domainToEntity(recipe)).thenReturn(recipeEntity);
        when(repository.save(recipeEntity)).thenReturn(savedEntity);
        when(mapper.entityToDomain(savedEntity)).thenReturn(expectedRecipe);

        Recipe actualRecipe = recipeService.addRecipe(recipe);

        assertEquals(expectedRecipe, actualRecipe);
        verify(mapper, times(1)).domainToEntity(recipe);
        verify(repository, times(1)).save(recipeEntity);
        verify(mapper, times(1)).entityToDomain(savedEntity);
    }
    @Test
    void testAddRecipe_NullRecipe() {
        assertNull(recipeService.addRecipe(null));
    }

    @Test
    void testAddRecipe_RepositorySaveFails() {
        Recipe recipe = new Recipe(UUID.randomUUID(), "Recipe1", "Description1", "url", List.of());
        RecipeEntity recipeEntity = new RecipeEntity(UUID.randomUUID(), "Recipe1", "Description1", "url", List.of());

        when(mapper.domainToEntity(recipe)).thenReturn(recipeEntity);
        when(repository.save(recipeEntity)).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> recipeService.addRecipe(recipe));
        verify(mapper, times(1)).domainToEntity(recipe);
        verify(repository, times(1)).save(recipeEntity);
    }
}
