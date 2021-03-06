# InventoryTracking
A backend that allows inventory tracking for a logistics company

# Running the application
(must be on a windows computer)
1. Clone the repository
2. Open a command line inside the newly cloned repository
3. run the command ```java -jar InventoryTracking-0.0.1-SNAPSHOT.jar```

# Endpoints
## "/addProduct"
### Description
Allows the addition of a new product to the inventory.
### Parameters
| Field | Type | Description |
| :---: | :---: | :---: |
| file | file | A picture of the product (png, jpg or jpeg only) |
| name | String | The name of the product |
| price | double | The price of the product  ($) |
| description | String | A description of the product |
## "/getAllProducts"
### Description
No paramters. Returns a list of all the product currently in the inventory.
## "/deleteProduct/{id}"
### Description 
Allows the deletion of a product given it's id.
### Parameters
| Field | Type | Description |
| :---: | :---: | :---: |
| id | int | id of the product to be deleted |
## "/editProduct"
### Description
Allows users to edit a product.
### Parameters
| Field | Type | Description |
| :---: | :---: | :---: |
| id | int | The id of the product to be edited |
| name | String | The new name of the product |
| price | double | The new price of the product  ($) |
| description | String | The new description of the product |
