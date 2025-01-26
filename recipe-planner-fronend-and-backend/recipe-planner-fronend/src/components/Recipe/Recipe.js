import './Recipe.css';
import { Button, Card } from "react-bootstrap";
import React from "react";
import { useNavigate } from 'react-router-dom'; 

function Recipe(props) {
    const navigate = useNavigate(); 

    const handleEditClick = () => {
        navigate(`/edit-details/${props.id}`);
    };

    return (
        <Card style={{ width: '18rem' }}>
            <Card.Img variant="top" src={props.image} />
            <Card.Body>
                <Card.Title>{props.title}</Card.Title>
                {props.description}
                <Button variant="primary" data-testid="edit-button" onClick={handleEditClick}>Edit Details</Button>
            </Card.Body>
        </Card>
    );
}

export default Recipe;
