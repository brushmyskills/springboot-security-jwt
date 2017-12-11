# Spring Boot Security using JWT (Json Web Token)

This Spring Boot Project uses JWT to secure the REST endpoints.

The following are the REST end points available in the this example.


## UnSecure Rest End Points:

### 1) /api/unsecure/generateJwtToken
- Generates the JWT token based on the JSON payload sent. 
Its a POST request which expects the JSON: { "userName": "name", "id": 123, "role": "ADMIN"}

### 2) /api/unsecure/all/
- Its a GET request which returns unsecure resoruces from server to end user

---------------------------------------------------------------------------------------------------------------------------

## Secure Rest End Points:

### 3) /api/secure/all/ 
- Its a GET request which returns secure resoruces from server to end user,
Requires a JWT Token with Header key - "Authorization" and value - "Token:JWT_Token_Value"

### 4) /api/secure/all/admin 
- Its a GET request which returns secure resoruces from server to end user having ADMIN role,
Requires a JWT Token with Header key - "Authorization" and value - "Token:JWT_Token_Value"
