****** description *****

this project contains two parts

//// part one :

this part illustrates using REST API , SOAP , and multithreading by producing and consuming from a queue.

//// HOW TO USE :

run Demoapplication

log into a browser or use POSTMAN

to display the contents of the queue , use the following url -> localhost:8080/display //METHOD = GET

to add into the queue , using POSTMAN

A- set method to POST B- use the following url -> localhost:8080/add C- using raw body option , content type = application/json , this is a sample body

{
    "type": "espresso",
    "id": 1
}
5- to consume the queue , use the following url -> localhost:8080/consume //METHOD = GET

6- to search for a specific object using id , check the following url sample : localhost:8080/getById?id=1 // change the value of id

//// workflow :

consuming : when a consume request is sent , a method that creates 6 threads is called each thread polls from the queue , then checks if the queue is empty or not , if not then it will poll again

soap : Soap is implemented in searching for an object by its id , first a REST request is sent to the server , then a SOAP request is generated auomatically using the parameters obtained through the REST request , the SOAP request is sent and the response is obtained and then the information gatherd from the soap enveleope body is outputted on the screen .

//// Part Two :

this part illustrates the usage of aerospike database together with REST api

//// HOW TO USE :

1- run the aerospike vm server using the following command in the command prompt : VAGRANT UP

2- to display the contetns of the record , use the following url -> localhost:8080/displayDB

3- to add an entry to the record , using POSTMAN .

A- set method to POST B- use the following url -> localhost:8080/addDB C- using raw body option , content type = application/json , this is a sample body

{
    "type": "espresso",
    "id": 1
}
4- to delete an entry , check the following sample url -> localhost:8080/DeleteFromDB?id=1 //change the value of id

5- to search for an entry , check the following sample url -> localhost:8080/getDB?id=1 // change the value of id

/-----------------------------------------/ DONE :)
