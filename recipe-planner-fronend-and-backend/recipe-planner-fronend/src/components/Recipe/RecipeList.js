import React, { useState, useEffect } from 'react';
import Recipe from './Recipe';  // Import Recipe component
import axios from 'axios';

function RecipeList() {
    const [recipes, setRecipes] = useState([]);

    useEffect(() => {
        // Fetching recipes from backend API
        axios.get('http://localhost:8080/api/recipes')
            .then(response => {
                setRecipes(response.data);  // Set the data fetched from the backend
            })
            .catch(error => {
                console.error("There was an error fetching the recipes:", error);
            });
    }, []);  // The empty dependency array means this runs once when the component mounts

    return (
        <div>
            <h1>Recipes List</h1>
            {recipes.length > 0 ? (
                recipes.map((recipe) => (
                    <Recipe
                        key={recipe.id}
                        id={recipe.id}
                        title={recipe.name}
                        description={recipe.description}
                        image={recipe.image}
                    />
                ))
            ) : (
                <p>Loading recipes...</p>
            )}
        </div>
    );
}

export default RecipeList;
