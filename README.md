# Description
  An spring boot, service to handle product related routes, like searching, fetching single product with an ID or getting all the products.
  Authentication would be required to access the products which are supported by the User Service, on different server or on different Port.
  User service will provide the jwt token, that will be used by product service to validate the active session and the request will be served.
