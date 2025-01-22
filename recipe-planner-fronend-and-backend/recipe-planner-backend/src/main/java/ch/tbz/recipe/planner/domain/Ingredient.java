package ch.tbz.recipe.planner.domain;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Ingredient {

    private UUID id;
    private String name;
    private String comment;
    private Unit unit;
    private int amount;

}
