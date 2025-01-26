import React, { useEffect, useState } from 'react';
import './Browse.css';
import axios from 'axios';
import Recipe from '../Recipe/Recipe';
import { Col, Row } from 'react-bootstrap';

const baseURL = 'http://localhost:8080/api/recipes';

const Browse = () => {
    const [post, setPost] = useState(null);

    useEffect(() => {
        axios.get(baseURL)
            .then((response) => {
                setPost(response.data); 
            })
            .catch((error) => {
                console.error('Error fetching recipes:', error);
            });
    }, []);

    if (!post) return <p>Loading...</p>;  

    return (
        <>
            <Row>
                {post.map((recipe) => (
                    <Col sm={12} md={6} lg={4} xl={3} key={recipe.id}>  
                        <Recipe 
                            id={recipe.id} 
                            title={recipe.name} 
                            description={recipe.description} 
                            image={recipe.imageUrl} 
                        />
                    </Col>
                ))}
            </Row>
        </>
    );
};

export default Browse;
