import React from 'react';
import { Button, Col, Form, Row } from 'react-bootstrap';

const AddIngredient = ({ ingredient, updateIngredient, removeIngredient }) => {
    const handleInputChange = (field, value) => {
        updateIngredient({ ...ingredient, [field]: value });
    };

    return (
        <Row>
            <Col>
                <Form.Group className="mb-1" controlId="formBasicName">
                    <Form.Control
                        data-testid="ingredient-name"
                        placeholder="Name"
                        value={ingredient.name || ''}
                        onChange={(e) => handleInputChange('name', e.target.value)}
                    />
                </Form.Group>
            </Col>
            <Col>
                <Form.Group className="mb-1" controlId="formBasicUnit">
                    <Form.Select
                        data-testid="ingredient-unit"
                        value={ingredient.unit || ''}
                        onChange={(e) => handleInputChange('unit', e.target.value)}
                    >
                        <option>PIECE</option>
                        <option>GRAMM</option>
                        <option>KILOGRAMM</option>
                        <option>LITRE</option>
                        <option>DECILITRE</option>
                    </Form.Select>
                </Form.Group>
            </Col>
            <Col>
                <Form.Group className="mb-1" controlId="formBasicAmount">
                    <Form.Control
                        data-testid="ingredient-amount"
                        placeholder="Amount"
                        value={ingredient.amount || ''}
                        onChange={(e) => handleInputChange('amount', e.target.value)}
                    />
                </Form.Group>
            </Col>
            <Col xs={1}>
                <Button
                    onClick={() => removeIngredient(ingredient)}
                    variant="outline-dark"
                    className="mb-1"
                >
                    x
                </Button>
            </Col>
        </Row>
    );
};

export default AddIngredient;
