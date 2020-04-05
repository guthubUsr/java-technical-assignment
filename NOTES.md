# Notes

Please add here any notes, assumptions and design decisions that might help up understand your though process.

Design decisions:
- an Item in the bag can be discounted only once
- an Item should be with it's own product details, when the product is changed the future
- it would be quite straight forward to extract more details about the total price (original price, discount, ...)
    - consider adding originalPrice to the Items class
the basket contains the product details on the time of the purchase.
- 2 main types of discounts
    - Single product discount
        - Applied on addition of an item to the bag (adds a bit complexity on adding elements in the bag --> possible performance issue)
        - User friendlier --> a discount is visible immediately in the subtotal (Tesco and Sainsbury's as an example)
        - I implemented Buy 1 get 1 for free
        - if implemented logic for removing an item from the bag --> it should remove the applied discount.
    - Multiple product discounts
        - applied when calculating the total amount
- consider refactoring and keep only 1 Product (remove ProductByUnit and ProductByWeight)
- consider refactoring and keep only 1 Item (remove ItemByUnit and ItemByWeight)

Questions:
- If a product is configured with multiple discounts, should the calculation be made to find the maximun discount?
- Consider adding grouping in the products, which will allow easier maintaining more complex discount (Tesco meal deal: 3 pounds for: 1 product from Main meal, 1 product from selected drinks, 1 Snack OR 1 Dessert )