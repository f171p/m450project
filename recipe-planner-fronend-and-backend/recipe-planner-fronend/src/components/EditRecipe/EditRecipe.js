import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import { Button, Col, Form, Row } from 'react-bootstrap';
import AddIngredient from '../AddIngredient/AddIngredient';

const EditRecipe = () => {
    const { id } = useParams();
    const [recipe, setRecipe] = useState({
        name: '',
        description: '',
        imageUrl: '',
        ingredients: [],
    });

    const [listId, setListId] = useState(1);

    useEffect(() => {
        if (id) {
            axios.get(`http://localhost:8080/api/recipes/recipe/${id}`).then((response) => {
                console.log(response.data.ingredients); // Debugging: Check ingredients
                const recipe = response.data;
                setRecipe({
                    name: recipe.name,
                    description: recipe.description,
                    imageUrl: recipe.imageUrl,
                    ingredients: recipe.ingredients.map((ingredient) => ({
                        name: ingredient.name,
                        unit: ingredient.unit,
                        amount: ingredient.amount, // Map amount field
                    })),
                });
            });
        }
    }, [id]);
    
    
            

    const handleInputChange = (field, value) => {
        setRecipe({ ...recipe, [field]: value });
    };

    const updateIngredient = (updatedIngredient) => {
        const updatedIngredients = recipe.ingredients.map((ingredient) =>
            ingredient.listId === updatedIngredient.listId ? updatedIngredient : ingredient
        );
        setRecipe({ ...recipe, ingredients: updatedIngredients });
    };

    const renderIngredients = recipe.ingredients.map((ingredient) => (
        <AddIngredient
            key={ingredient.listId}
            ingredient={ingredient}
            updateIngredient={updateIngredient}
        />
    ));

    return (
        <div className="bg">
            <div className="m-3">
                <h1 className="h3 bg-dark text-bg-primary mt-2">Edit Recipe</h1>
                <Form.Group className="mb-1" controlId="formBasicName">
                    <Form.Label>Recipe Name:</Form.Label>
                    <Form.Control
                        data-testid="recipe-name"
                        placeholder="Name"
                        value={recipe.name}
                        onChange={(e) => handleInputChange('name', e.target.value)}
                    />
                </Form.Group>
                <Form.Group className="mb-1" controlId="formBasicDescription">
                    <Form.Label>Description:</Form.Label>
                    <Form.Control
                        data-testid="recipe-description"
                        placeholder="Description"
                        value={recipe.description}
                        onChange={(e) => handleInputChange('description', e.target.value)}
                    />
                </Form.Group>
                <Form.Group className="mb-1 mb-5" controlId="formBasicImageUrl">
                    <Form.Label>Image URL:</Form.Label>
                    <Form.Control
                        data-testid="recipe-image"
                        placeholder="URL"
                        value={recipe.imageUrl}
                        onChange={(e) => handleInputChange('imageUrl', e.target.value)}
                    />
                </Form.Group>
                <Row>
                    <Col>Ingredient</Col>
                    <Col>Unit</Col>
                    <Col>Quantity</Col>
                    <Col xs={1}></Col>
                </Row>
                <hr />
                <Row>
                    <br />
                </Row>
                {renderIngredients}
                <Row>
                    <br />
                </Row>
                <Button variant="primary" type="submit" className="mb-5">
                    Save Changes
                </Button>
            </div>
        </div>
    );
};

export default EditRecipe;
