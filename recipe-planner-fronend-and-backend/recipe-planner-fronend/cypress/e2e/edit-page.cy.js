describe('Edit Recipe Page Test', () => {
  it('should navigate to edit page and check if fields are filled correctly', () => {
      // Visit the main page where recipes are listed
      cy.visit('http://localhost:3000'); // Adjust the URL to match your app's start point

      // Click on the edit details button for a random recipe
      cy.get('[data-testid="edit-button"]').first().click(); // Adjust selector if needed

      // Verify that we are on the edit page
      cy.url().should('include', '/edit-details/');

      // Check if the recipe name is filled
      cy.get('[data-testid="recipe-name"]').should('have.value', 'Lasagne al Forno');

      // Check if the description is filled
      cy.get('[data-testid="recipe-description"]').should(
          'have.value',
          'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.'
      );

      // Check if the image URL is filled
      cy.get('[data-testid="recipe-image"]').should(
          'have.value',
          'https://www.deliciousmagazine.co.uk/wp-content/uploads/2020/01/lasagne.jpg'
      );

      // Check the ingredient name
      cy.get('[data-testid="ingredient-name"]').first().should('have.value', 'Tomato');

      // Check the ingredient unit
      cy.get('[data-testid="ingredient-unit"]').first().should('have.value', 'PIECE');

      // Check the ingredient amount
      cy.get('[data-testid="ingredient-amount"]').first().should('have.value', '5');
  });
});
