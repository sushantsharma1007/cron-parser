# cron-parser
Deliveroo coding exercise

# How to Run
  ## Pre-requisite
      Java 8
      Maven 3.X
   
  ## Build Code
     Clone the code and perform mvn clean install in root of the folder where pom.xml exists
     
  ## Run the code
     Execute the below command
     java -jar cron-parser-1.0-SNAPSHOT.jar "* 0 1-15 * 1,5 /usr/bin/find"
     
     Sample Output:
      Minute        0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59
      Hour          0
      Day Of Month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
      Month         1 2 3 4 5 6 7 8 9 10 11 12
      Day Of Week   1 5
      Command       /usr/bin/find
