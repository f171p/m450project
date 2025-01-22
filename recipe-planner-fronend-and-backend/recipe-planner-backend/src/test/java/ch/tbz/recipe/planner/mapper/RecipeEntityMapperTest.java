package ch.tbz.recipe.planner.mapper;


import ch.tbz.recipe.planner.domain.Recipe;
import ch.tbz.recipe.planner.entities.RecipeEntity;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class RecipeEntityMapperTest {

    @Autowired
    RecipeEntityMapper mapper;

    SoftAssertions softly = new SoftAssertions();

    @Test
    void RecipeEntityToDomain() {
        final UUID id = UUID.randomUUID();
        final RecipeEntity recipeEntity = new RecipeEntity(id, "Recipe", "Description", "url", List.of());
        Recipe expectedRecipe = mapper.entityToDomain(recipeEntity);
        softly.assertThat(recipeEntity.getId()).isEqualTo(expectedRecipe.getId());
        softly.assertThat(recipeEntity.getName()).isEqualTo(expectedRecipe.getName());
        softly.assertThat(recipeEntity.getDescription()).isEqualTo(expectedRecipe.getDescription());
    }

    @Test
    void RecipeDomainToEntity() {
        final UUID id = UUID.randomUUID();
        final Recipe recipe = new Recipe(id, "Recipe", "Description", "url", List.of());
        RecipeEntity expectedRecipe = mapper.domainToEntity(recipe);
        softly.assertThat(recipe.getId()).isEqualTo(expectedRecipe.getId());
        softly.assertThat(recipe.getName()).isEqualTo(expectedRecipe.getName());
        softly.assertThat(recipe.getDescription()).isEqualTo(expectedRecipe.getDescription());
    }
}
