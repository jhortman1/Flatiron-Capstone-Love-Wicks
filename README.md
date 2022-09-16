﻿# Flatiron-Capstone-Love-Wicks
Postgres Commands to start DB:
Run Postgres
	docker run --name postgres --network labnetwork -e POSTGRES_PASSWORD=mysecretpassword -e POSTGRES_DB=db_Love_Wicks -p 5432:5432 -d ubuntu/postgres:14-22.04_beta
Check Status
	docker ps -a
Remote into container
	docker exec -it postgres /bin/bash
	root@9d7a283e5eb0:/# psql -U postgres
List of Tables
	postgres=# \l
Connect to DB
			   \c db_Love_Wicks
Get List of Table names
			   \dt
         
  To use the app :
    Click Sign-up
      Enter User information
      Click Register - Routes you to Signin
    Use email and pw just crested with Register
    Click Admin
      Click Candles
        Click add Candles
          Enter Candle information
      Will be able to view candle
      Click Details to view/edit/delete(Out of Stock) Candle Information (Bug: Page requires refresh to view updated candle list after delete)
    To Create User:
      Click Admin
        Click Users
          Click Add Users
            Enter User Information
              Click Save
            Click Show Details to view/update/delete(Deactivate) Customer Information
    To Create an order after creating candles
      Click shop
        Click Add To Cart button for desired candles
          Click Cart
            Click Submit Order to save order
            Click Empty Cart to Empty Cart
    To View orders:
      Click Admin
        Click Orders
          Click Details to view/delete(Close) order (Bug: Page requires refresh to see details for different orders)
          
            
        
            
      
