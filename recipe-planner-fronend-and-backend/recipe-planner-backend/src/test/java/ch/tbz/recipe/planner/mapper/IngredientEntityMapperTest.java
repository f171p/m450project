package ch.tbz.recipe.planner.mapper;


import ch.tbz.recipe.planner.domain.Ingredient;
import ch.tbz.recipe.planner.domain.Unit;
import ch.tbz.recipe.planner.entities.IngredientEntity;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class IngredientEntityMapperTest {

    @Autowired
    IngredientEntityMapper mapper;

    SoftAssertions softly = new SoftAssertions();

    @Test
    void IngredientDomainToEntity() {
        UUID id = UUID.randomUUID();
        Ingredient ingredient = new Ingredient(id, "Ingredient 1", "comment 1", Unit.LITRE, 32);
        IngredientEntity expectedIngredient = mapper.domainToEntity(ingredient);
        softly.assertThat(expectedIngredient.getId()).isEqualTo(ingredient.getId());
        softly.assertThat(expectedIngredient.getName()).isEqualTo(ingredient.getName());
        softly.assertThat(expectedIngredient.getComment()).isEqualTo(ingredient.getComment());
        softly.assertThat(expectedIngredient.getUnit()).isEqualTo(ingredient.getUnit());
        softly.assertThat(expectedIngredient.getAmount()).isEqualTo(ingredient.getAmount());
    }

    @Test
    void IngredientEntityToDomain() {
        UUID id = UUID.randomUUID();
        IngredientEntity ingredient = new IngredientEntity(id, "Ingredient", "Comment", Unit.LITRE, 32);
        Ingredient expectedIngredient = mapper.entityToDomain(ingredient);
        softly.assertThat(expectedIngredient.getId()).isEqualTo(ingredient.getId());
        softly.assertThat(expectedIngredient.getName()).isEqualTo(ingredient.getName());
        softly.assertThat(expectedIngredient.getComment()).isEqualTo(ingredient.getComment());
        softly.assertThat(expectedIngredient.getUnit()).isEqualTo(ingredient.getUnit());
        softly.assertThat(expectedIngredient.getAmount()).isEqualTo(ingredient.getAmount());
    }

    @Test
    void IngredientDomainsToEntities() {
        List<Ingredient> ingredients = List.of(
                new Ingredient(UUID.randomUUID(), "Ingredient 1", "comment 1", Unit.LITRE, 32),
                new Ingredient(UUID.randomUUID(), "Ingredient 2", "comment 2", Unit.GRAMM, 64),
                new Ingredient(UUID.randomUUID(), "Ingredient 3", "comment 3", Unit.PIECE, 128)
        );
        List<IngredientEntity> expectedIngredients = mapper.domainsToEntities(ingredients);
        softly.assertThat(expectedIngredients.size()).isEqualTo(ingredients.size());
        softly.assertThat(expectedIngredients.get(0).getName()).isEqualTo(ingredients.get(0).getName());
        softly.assertThat(expectedIngredients.get(1).getComment()).isEqualTo(ingredients.get(1).getComment());
        softly.assertThat(expectedIngredients.get(2).getAmount()).isEqualTo(ingredients.get(2).getAmount());
    }

    @Test
    void IngredientEntitiesToDomains() {
        List<IngredientEntity> ingredients = List.of(
                new IngredientEntity(UUID.randomUUID(), "Ingredient 1", "comment 1", Unit.LITRE, 32),
                new IngredientEntity(UUID.randomUUID(), "Ingredient 2", "comment 2", Unit.GRAMM, 64),
                new IngredientEntity(UUID.randomUUID(), "Ingredient 3", "comment 3", Unit.PIECE, 128)
        );
        List<Ingredient> expectedIngredients = mapper.entitiesToDomains(ingredients);
        softly.assertThat(expectedIngredients.size()).isEqualTo(ingredients.size());
        softly.assertThat(expectedIngredients.get(0).getName()).isEqualTo(ingredients.get(0).getName());
        softly.assertThat(expectedIngredients.get(1).getComment()).isEqualTo(ingredients.get(1).getComment());
        softly.assertThat(expectedIngredients.get(2).getAmount()).isEqualTo(ingredients.get(2).getAmount());
    }
}
