create table FLIGHT (
              ID INT NOT NULL AUTO_INCREMENT,
              FLIGHT_NO VARCHAR(100) NOT NULL,
              CAPACITY INT NOT NULL,
              FROM_STATION VARCHAR(100) NOT NULL,
              TO_STATION VARCHAR(100) NOT NULL,
              FROM_CODE VARCHAR(100),
              TO_CODE VARCHAR(100),
              DEPARTURE TIMESTAMP ,
              ARRIVAL TIMESTAMP,
              PRICE DOUBLE,
              PRIMARY KEY ( ID ),
              UNIQUE (FLIGHT_NO)
);

CREATE TABLE passenger (
    passenger_id INT PRIMARY KEY,
    passenger_name VARCHAR(100) NOT NULL,
    passenger_age INT CHECK (passenger_age >= 0 AND passenger_age <= 98),
    passenger_phone_number VARCHAR(12) NOT NULL CHECK (LENGTH(passenger_phone_number) BETWEEN 10 AND 12),
    passenger_dob DATE NOT NULL CHECK (passenger_dob <= CURRENT_DATE)
   
    
);


CREATE TABLE TICKET (
    ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    ticket_number INT NOT NULL,
    flight_id INT NOT NULL,
    FOREIGN KEY (flight_id) REFERENCES FLIGHT(ID),
    UNIQUE (ticket_number)
 
);

CREATE TABLE ticket_passenger (
    ticket_id INT NOT NULL,
    passenger_id INT NOT NULL,
    PRIMARY KEY (ticket_id, passenger_id),
    FOREIGN KEY (ticket_id) REFERENCES ticket(ticket_id),
    FOREIGN KEY (passenger_id) REFERENCES passenger(passenger_id)
);




