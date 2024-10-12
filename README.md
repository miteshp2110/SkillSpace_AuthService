# Skill Space - Authorization and Authentication Service in Spring Boot

## JWT controller
/jwtStatus - return the status of state of user (emailStatus , profileStatus , and if user is ready to access other services)
/refreshToken - issue the refresh token for the jwt if expired


## Auth Controller
/signup - accept the data and signup the user and return the initial jwt (emailStatus:false, profileStatus:false)
/login - accept ethe login data and return the jwt analysing the state of user from database

## Profile Controller

/completeProfile - accept the profile data and fill the database with profile data and return the status jwt

## Otp Controller 
/requestOtp - send the otp  email
/verifyOtp - verifyOtp and check if it is correct and return jwt


