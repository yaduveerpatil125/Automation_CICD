Feature: Purchase the order from Ecommerce website

Background:
Given I landed on Ecommerce page

@purchaseOrder
Scenario Outline: Positive test of submitting order
Given Login with <username> and <password>
When I add the product <productName> to cart
And checkout <productName> and submit the order
Then verify if "THANKYOU FOR THE ORDER." is displayed on the confirmation page
	
Examples:
|username|password|productName|
|admin125@gmail.com|Admin_1234|ZARA COAT 3|


