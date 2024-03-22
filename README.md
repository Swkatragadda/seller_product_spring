#Product Seller Spring Project
This Project is a back end of a webapplication built using spring framwork for sellers and products.
#Features
*addseller
*add product
*add price to a product
*update product 
*delete product

#Technologies used
Java,Spring boot,H2,PostMan,HTTP

#Collaberators
Swathi Katragadda
Benny Tavarez

#Instructions to run 
INSERT PRODUCT
1. Always post a seller first using localhost:9001/seller

	In postman use the following JSON:
		{	"sellerName":"SELLERNAMEHERE"}
	
2. Post Product here localhost:9001/products
	In post man use the following JSON. Fill values with your own.
		{
			"productName": "PRODUCTNAMEHERE",
			"price": 0,
			"sellerName": "SELLERNAMEHERE"
		}

3.Update Product
  1. go to postman and input following URI: localhost:9001/products/PRODUCTIDHERE

  2. 
	{
		"productName": "PRODUCTNAMEHERE",
		"price": 0.00,
		"sellerName": "SELLERNAMEHERE"
	}
4.DELETE PRODUCT
This will delete the product using the product ID
  1. Go to postman and input following URI: localhost:9001/products/PRODUCTIDHERE






