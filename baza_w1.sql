DROP DATABASE Biblioteczka;

CREATE DATABASE Biblioteczka;
go

USE Biblioteczka;

CREATE TABLE Authors(
   AuthorID INT IDENTITY,
   FirstName VARCHAR(255) NOT NULL,
   LastName VARCHAR(255) NOT NULL,
   PRIMARY KEY (AuthorID)
) 

CREATE TABLE Publishers(
   PublisherId INT IDENTITY,
   Name VARCHAR(255) NOT NULL,
   PRIMARY KEY (PublisherID)
) 

CREATE TABLE Gendre(
	GendreId INT IDENTITY,
	Gendre VARCHAR(255) NOT NULL,
	PRIMARY KEY (GendreId)
)

CREATE TABLE Books(
   BookID INT IDENTITY,
   ISBN VARCHAR(255) NOT NULL,
   Title VARCHAR(255) NOT NULL,
   AuthorID INT NOT NULL,
   GendreId INT NOT NULL,
   PublisherID INT NOT NULL,
   PublishingYear INT NOT NULL,
   Price FLOAT NOT NULL,
   PRIMARY KEY (BookID),
   FOREIGN KEY (AuthorID) REFERENCES Authors(AuthorID),
   FOREIGN KEY (PublisherId) REFERENCES Publishers(PublisherId),
   FOREIGN KEY (GendreId) REFERENCES Gendre(GendreId)
) 

CREATE TABLE Customers(
   CustomerID INT NOT NULL,
   FirstName VARCHAR(255) NOT NULL,
   LastName VARCHAR(255) NOT NULL,
   CustomerAddress VARCHAR(255) NOT NULL,
   PhoneNumber NUMERIC(9,0),
   Email VARCHAR(255),
   DateFrom  DATETIME DEFAULT GETDATE(),
   PRIMARY KEY (CustomerID)
)

CREATE TABLE Borrow(
   BorrowID INT NOT NULL,
   BookID INT NOT NULL,
   BorrowDate DATETIME DEFAULT GETDATE(),
   CustomerID INT NOT NULL DEFAULT 0,
   PRIMARY KEY (BorrowID),
   FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
   FOREIGN KEY (BookID) REFERENCES Books(BookID), 
) 

go
