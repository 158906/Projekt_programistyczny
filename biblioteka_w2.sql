DROP DATABASE Biblioteczka;

CREATE DATABASE Biblioteczka;

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

CREATE TABLE Books(
   BookID INT IDENTITY,
   ISBN VARCHAR(255) NOT NULL,
   Title VARCHAR(255) NOT NULL,
   AuthorID INT NOT NULL,
   PublisherID INT NOT NULL,
   PublishingYear INT NOT NULL,
   Price FLOAT NOT NULL,
   PRIMARY KEY (BookID),
   FOREIGN KEY (AuthorID) REFERENCES Authors(AuthorID),
   FOREIGN KEY (PublisherId) REFERENCES Publishers(PublisherId)
) 

CREATE TABLE Gendres(
   BookID INT NOT NULL,
   Gendre VARCHAR(255) NOT NULL,
   CHECK (Gendre IN ('Audiobook','Biografia i dokument', 'Dla dzieci', 'Encyklopedie i leksykony', 
   'Kuchnia', 'Literatura', 'Podreczniki szkolne', 'Poradniki', 'Sztuka', 'Wakacje i podroze')),
   FOREIGN KEY (BookID) REFERENCES Books(BookID)
) 

CREATE TABLE AvailabilityCheck(
   BookID INT NOT NULL,
   QuantOfAvBooks INT NOT NULL,
   FOREIGN KEY (BookID) REFERENCES Books(BookID)
)

CREATE TABLE Clients(
   ClientID INT IDENTITY,
   FirstName VARCHAR(255) NOT NULL,
   LastName VARCHAR(255) NOT NULL,
   PhoneNumber BIGINT NOT NULL,
   Addres VARCHAR(255) NOT NULL 
   PRIMARY KEY (ClientID)
) 

---select * from BorrowDet;

/*CREATE TABLE Borrow(
   BorrowID INT IDENTITY,
   BookID INT NOT NULL,
   ClientID INT NOT NULL,
   BorrowDate DATE DEFAULT GETDATE(),
   ReturnDate DATE,
   FOREIGN KEY (BookID) REFERENCES Books(BookID),
   FOREIGN KEY (ClientID) REFERENCES Clients(ClientID),
   CHECK (DATEDIFF(day, BorrowDate, GETDATE()) <= 0)
) */

drop table Borrow;
CREATE TABLE Borrow(
   BorrowID INT IDENTITY,
   BookID INT NOT NULL,
   ClientID INT NOT NULL,
   BorrowDate DATE DEFAULT GETDATE(),
   ReturnDate DATE DEFAULT NULL,
   FOREIGN KEY (BookID) REFERENCES Books(BookID),
   FOREIGN KEY (ClientID) REFERENCES Clients(ClientID),
   ---CHECK (DATEDIFF(day, BorrowDate, GETDATE()) <= 0)
)
--drop view BorrowDet
CREATE VIEW BorrowDet AS
SELECT 
BorrowID,ClientID, 
Books.ISBN ISBN, BorrowDate [Borrow's Date], ReturnDate [Return's Date]
FROM Borrow, Books
WHERE Books.BookID = Borrow.BookID;

SELECT * FROM BorrowDet;
SELECT * FROM Borrow;

---delete from BorrowDet where borrowId = 4;

/*
DROP VIEW BorrowDet;
CREATE VIEW BorrowDet AS
SELECT 
Clients.FirstName [Clients's Name],Clients.LastName [Clients's Last Name], 
Books.ISBN ISBN, BorrowDate [Borrow's Date], ReturnDate [Return's Date]
FROM Clients,Borrow, Books
WHERE Books.BookID = Borrow.BookID
AND Clients.ClientID = Borrow.ClientID;*/

CREATE VIEW BooksDet AS
SELECT ISBN, Title, Price, Authors.FirstName [Author's Name],Authors.LastName [Author's Last Name], 
Publishers.Name Publisher, Books.PublishingYear [Publication Year], Gendre, 
AvailabilityCheck.QuantOfAvBooks [Quantity]
FROM Books, Authors, Publishers, AvailabilityCheck,Gendres
WHERE Books.BookID = AvailabilityCheck.BookID
	AND Books.BookID=Gendres.BookID AND 
	Books.AuthorID=Authors.AuthorID AND
	Books.PublisherID=Publishers.PublisherId
go

CREATE TRIGGER BooksIn
ON BooksDet
INSTEAD OF INSERT
AS
BEGIN
	DECLARE BIN CURSOR FOR
		SELECT *
		FROM inserted
	OPEN BIN
		DECLARE @title VARCHAR(255)
		DECLARE @isbn VARCHAR(255)
		DECLARE @aName VARCHAR(255)
		DECLARE @aLName VARCHAR(255)
		DECLARE @publisher VARCHAR(255)
		DECLARE @price FLOAT
		DECLARE @year INT
		DECLARE @gendre VARCHAR(255)
		DECLARE @quantity INT ---trzeba dorobiæ, bo inaczej nie mo¿na dodaæ kilku sztuk ksi¹¿ki
		FETCH NEXT FROM BIN INTO @isbn, @title, @price, @aName, @aLName, @publisher, @year, @gendre, @quantity
		WHILE @@FETCH_STATUS = 0
		BEGIN
			DECLARE @tmpAName VARCHAR(255) = NULL
			DECLARE @tmpALName VARCHAR(255) = NULL
			SELECT @tmpAName = FirstName, @tmpALName = LastName
			FROM Authors
			WHERE FirstName = @aName AND LastName=@aLName
			IF @tmpAName IS NULL AND @tmpALName IS NULL
			BEGIN
				INSERT INTO Authors
				VALUES (@aName, @aLName)
			END
			DECLARE @tmpPublisher VARCHAR(255) = NULL
			SELECT @tmpPublisher = Name
			FROM Publishers
			WHERE Name = @publisher
			IF @tmpPublisher IS NULL
			BEGIN
				INSERT INTO Publishers
				VALUES (@publisher)
			END
			IF EXISTS (
				SELECT *
				FROM BooksDet
				WHERE ISBN = @isbn
					AND Title = @title
					AND Price = @price
					AND [Author's Name] = @aName
					AND [Author's Last Name] = @aLName
					AND Publisher = @publisher
					AND [Publication Year] = @year
					AND Gendre = @gendre
			)
			BEGIN
				RAISERROR('Ta ksiazka jest juz wprowadzona!', 10, 1) BREAK
			END
			
			IF EXISTS (SELECT * FROM BooksDet WHERE ISBN=@isbn)
			BEGIN
				RAISERROR('Ten numer ISBN zostal juz wprowadzony!', 10, 1) BREAK
			END
			
			DECLARE @tmpAID INT = NULL
			DECLARE @tmpPID INT = NULL
			SELECT @tmpAID=AuthorID FROM Authors WHERE FirstName=@aName AND LastName=@aLName
			SELECT @tmpPID=PublisherID FROM Publishers WHERE Name=@publisher
			INSERT INTO Books
			VALUES (@isbn, @title, @tmpAID, @tmpPID, @year, @price)
			
			DECLARE @tmpBID INT = NULL
			SELECT @tmpBID=BookID FROM Books WHERE ISBN=@isbn AND 
			Title=@title AND AuthorID=@tmpAID AND PublisherID=@tmpPID
			AND PublishingYear=@year AND Price=@price
			
			INSERT INTO Gendres
			VALUES (@tmpBID, @gendre)
			
			INSERT INTO AvailabilityCheck
			VALUES (@tmpBID, @quantity)
			
			FETCH NEXT FROM BIN INTO @isbn, @title, @price, @aName, @aLName, @publisher, @year, @gendre, @quantity
		END
	CLOSE BIN
	DEALLOCATE BIN
END
GO

CREATE TRIGGER BooksDel
ON BooksDet
INSTEAD OF DELETE
AS
BEGIN
	DECLARE BDEL CURSOR FOR
		SELECT *
		FROM deleted
	OPEN BDEL
		DECLARE @title VARCHAR(255)
		DECLARE @isbn VARCHAR(255)
		DECLARE @aName VARCHAR(255)
		DECLARE @aLName VARCHAR(255)
		DECLARE @publisher VARCHAR(255)
		DECLARE @price FLOAT
		DECLARE @year INT
		DECLARE @gendre VARCHAR(255)
		DECLARE @quantity INT
		FETCH NEXT FROM BDEL INTO @isbn, @title, @price, @aName, @aLName, @publisher, @year, @gendre, @quantity
		WHILE @@FETCH_STATUS = 0
		
		BEGIN
			
			DECLARE @tmpBID INT = NULL
			DECLARE @tmpAID INT = NULL
			DECLARE @tmpPID INT = NULL
			SELECT @tmpAID=AuthorID FROM Authors WHERE FirstName=@aName AND LastName=@aLName
			SELECT @tmpPID=PublisherID FROM Publishers WHERE Name=@publisher
			
			SELECT @tmpBID=BookID FROM Books WHERE ISBN=@isbn AND 
			Title=@title AND AuthorID=@tmpAID AND PublisherID=@tmpPID

			
			DELETE FROM Gendres WHERE BookID = @tmpBID
			DELETE FROM AvailabilityCheck WHERE BookID = @tmpBID
			DELETE FROM Books WHERE BookID = @tmpBID
			
			IF NOT EXISTS (
				SELECT * FROM Books
				WHERE AuthorID = @tmpAID
			) BEGIN
				DELETE FROM Authors
				WHERE AuthorID = @tmpAID
			END
			IF NOT EXISTS (
				SELECT * FROM Books
				WHERE PublisherID = @tmpPID
			) BEGIN
			DELETE FROM Publishers
				WHERE PublisherID = @tmpPID
			END
			FETCH NEXT FROM BDEL INTO @isbn, @title, @price, @aName, @aLName, @publisher, @year, @gendre, @quantity
		END
	CLOSE BDEL
	DEALLOCATE BDEL
END

go

CREATE TRIGGER BooksUp
ON BooksDet
INSTEAD OF UPDATE
AS
BEGIN
	DECLARE BIN CURSOR FOR
		SELECT *
		FROM inserted
	DECLARE BDEL CURSOR FOR
		SELECT *
		FROM deleted
	OPEN BIN
	OPEN BDEL
		DECLARE @title VARCHAR(255)
		DECLARE @isbn VARCHAR(255)
		DECLARE @aName VARCHAR(255)
		DECLARE @aLName VARCHAR(255)
		DECLARE @publisher VARCHAR(255)
		DECLARE @price FLOAT
		DECLARE @year INT
		DECLARE @gendre VARCHAR(255)
		DECLARE @quantity INT
		
		DECLARE @newtitle VARCHAR(255)
		DECLARE @newisbn VARCHAR(255)
		DECLARE @newaName VARCHAR(255)
		DECLARE @newaLName VARCHAR(255)
		DECLARE @newpublisher VARCHAR(255)
		DECLARE @newprice FLOAT
		DECLARE @newyear INT
		DECLARE @newgendre VARCHAR(255)
		DECLARE @newquantity INT
		
		FETCH NEXT FROM BIN INTO @newisbn, @newtitle, @newprice, @newaName, @newaLName, @newpublisher, @newyear, @newgendre, @newquantity
		FETCH NEXT FROM BDEL INTO @isbn, @title, @price, @aName, @aLName, @publisher, @year, @gendre, @quantity
		
		WHILE @@FETCH_STATUS = 0
		BEGIN
			DECLARE @tmpBID INT = NULL
			DECLARE @tmpAID INT = NULL
			DECLARE @tmpPID INT = NULL
			SELECT @tmpAID=AuthorID FROM Authors WHERE FirstName=@aName AND LastName=@aLName
			SELECT @tmpPID=PublisherID FROM Publishers WHERE Name=@publisher
			SELECT @tmpBID=BookID FROM Books WHERE ISBN=@isbn AND 
			Title=@title AND AuthorID=@tmpAID AND PublisherID=@tmpPID
		
			IF UPDATE (ISBN)
			BEGIN
				UPDATE Books
				SET ISBN = @newisbn
				WHERE BookID = @tmpBID
			END
			
			IF UPDATE (Title)
			BEGIN
				UPDATE Books
				SET Title = @newtitle
				WHERE BookID = @tmpBID
			END
			
			IF UPDATE (Price)
			BEGIN
				UPDATE Books
				SET Price = @newprice
				WHERE BookID = @tmpBID
			END
			
			IF UPDATE ([Publication Year])
			BEGIN
				UPDATE Books
				SET PublishingYear = @newyear
				WHERE BookID = @tmpBID
			END
			
			IF UPDATE (Gendre)
			BEGIN
				UPDATE Gendres
				SET Gendre = @newGendre
				WHERE BookID = @tmpBID
			END
			
			IF UPDATE (Quantity)
			BEGIN
				UPDATE AvailabilityCheck
				SET QuantOfAvBooks = @newquantity
				WHERE BookID = @tmpBID
			END
			
			IF UPDATE ([Author's Name])
			BEGIN
			
			DECLARE @tmpAName VARCHAR(255) = NULL
			DECLARE @tmpALName VARCHAR(255) = NULL
			SELECT @tmpAName = FirstName, @tmpALName = LastName
			FROM Authors
			WHERE FirstName = @newaName AND LastName=@newaLName
			IF @tmpAName IS NULL AND @tmpALName IS NULL
			BEGIN
				INSERT INTO Authors
				VALUES (@newaName, @newaLName)
			END
			
			SELECT @tmpAID=AuthorID FROM Authors WHERE FirstName=@newaName AND LastName=@newaLName
				UPDATE Books
				SET AuthorID = @tmpAID
				WHERE BookID = @tmpBID
				
			SELECT @tmpAID=AuthorID FROM Authors WHERE FirstName=@aName AND LastName=@aLName
			
			IF NOT EXISTS (
				SELECT * FROM Books
				WHERE AuthorID = @tmpAID
			) BEGIN
				DELETE FROM Authors
				WHERE AuthorID = @tmpAID
			END
			END
			
			IF UPDATE ([Author's Last Name])
			BEGIN
			
			DECLARE @tmpAName2 VARCHAR(255) = NULL
			DECLARE @tmpALName2 VARCHAR(255) = NULL
			SELECT @tmpAName2 = FirstName, @tmpALName2 = LastName
			FROM Authors
			WHERE FirstName = @newaName AND LastName=@newaLName
			IF @tmpAName2 IS NULL AND @tmpALName2 IS NULL
			BEGIN
				INSERT INTO Authors
				VALUES (@newaName, @newaLName)
			END

			SELECT @tmpAID=AuthorID FROM Authors WHERE FirstName=@newaName AND LastName=@newaLName
				UPDATE Books
				SET AuthorID = @tmpAID
				WHERE BookID = @tmpBID
				
			SELECT @tmpAID=AuthorID FROM Authors WHERE FirstName=@aName AND LastName=@aLName
			
			IF NOT EXISTS (
				SELECT * FROM Books
				WHERE AuthorID = @tmpAID
			) BEGIN
				DELETE FROM Authors
				WHERE AuthorID = @tmpAID
			END
			END
			
			IF UPDATE (Publisher)
			BEGIN
			
			DECLARE @tmpPublisher VARCHAR(255) = NULL
			SELECT @tmpPublisher = Name
			FROM Publishers
			WHERE Name = @newpublisher
			IF @tmpPublisher IS NULL
			BEGIN
				INSERT INTO Publishers
				VALUES (@newpublisher)
			END
			
				SELECT @tmpPID=PublisherID FROM Publishers WHERE Name=@newpublisher
				UPDATE Books
				SET PublisherID = @tmpPID
				WHERE BookID = @tmpBID
			
			
			SELECT @tmpPID=PublisherID FROM Publishers WHERE Name=@publisher
			IF NOT EXISTS (
				SELECT * FROM Books
				WHERE PublisherID = @tmpPID
			) BEGIN
			DELETE FROM Publishers
				WHERE PublisherID = @tmpPID
			END
			
			END
			FETCH NEXT FROM BIN INTO @newisbn, @newtitle, @newprice, @newaName, @newaLName, @newpublisher, @newyear, @newgendre, @newquantity
			FETCH NEXT FROM BDEL INTO @isbn, @title, @price, @aName, @aLName, @publisher, @year, @gendre, @quantity
		END
	CLOSE BIN
	CLOSE BDEL
	DEALLOCATE BIN
	DEALLOCATE BDEL
END

go


--drop trigger BorrowIn
/*CREATE TRIGGER BorrowIn
ON BorrowDet
INSTEAD OF INSERT
AS
BEGIN
	DECLARE BIN CURSOR FOR
		SELECT [Clients's Name], [Clients's Last Name], ISBN, [Borrow's Date], [Return's Date]	
		FROM inserted
	OPEN BIN
	
		DECLARE @clientName VARCHAR(255)
		DECLARE @clientLName VARCHAR(255)
		DECLARE @isbn VARCHAR(255)
		DECLARE @dateFrom DATE
		DECLARE @dateTo DATE

		FETCH NEXT FROM BIN INTO @clientName, @clientLName, @isbn, @dateFrom, @dateTo
		WHILE @@FETCH_STATUS = 0
		BEGIN
			
			IF NOT EXISTS (
				SELECT *
				FROM Books
				WHERE ISBN = @isbn
			)
			BEGIN
				RAISERROR('Brak ksiazki w bazie!', 10, 1) BREAK
			END
			
			
			DECLARE @tmpquantity INT = NULL
			DECLARE @bookid INT = NULL
			DECLARE @clientid INT = NULL
			SELECT @bookid=BookID FROM Books WHERE ISBN=@isbn
			SELECT @clientid=ClientID FROM Clients WHERE FirstName=@clientName and LastName = @clientLName
			----bêdzie trzeba poprawiæ z jakimœ peselem ¿eby jednoznacznie identyfikowaæ klienta
			SELECT @tmpquantity=QuantOfAvBooks FROM AvailabilityCheck WHERE BookID= @bookid
			
			IF @tmpquantity < 1 ---@quantity
			BEGIN
				RAISERROR('Ksiazka niedostepna!', 10, 1) BREAK
			END

			INSERT INTO Borrow
			VALUES (@bookid, @clientid, @dateFrom, @dateTo)
			UPDATE AvailabilityCheck SET QuantOfAvBooks=@tmpquantity-1 WHERE BookID=@bookid
			
			FETCH NEXT FROM BIN INTO @clientName, @clientLName, @isbn, @dateFrom, @dateTo
		END
	CLOSE BIN
	DEALLOCATE BIN
END
GO*/


drop trigger BorrowIn
CREATE TRIGGER BorrowIn
ON BorrowDet
INSTEAD OF INSERT
AS
BEGIN
	DECLARE BIN CURSOR FOR
		SELECT BorrowID, ClientID, ISBN, [Borrow's Date], [Return's Date]	
		FROM inserted
	OPEN BIN
	
		DECLARE @borrowID INT
		DECLARE @clientID INT
		DECLARE @isbn VARCHAR(255)
		DECLARE @dateFrom DATE
		DECLARE @dateTo DATE

		FETCH NEXT FROM BIN INTO @borrowID, @clientID, @isbn, @dateFrom, @dateTo
		WHILE @@FETCH_STATUS = 0
		BEGIN
			
			IF NOT EXISTS (
				SELECT *
				FROM Books
				WHERE ISBN = @isbn
			)
			BEGIN
				RAISERROR('Brak ksiazki w bazie!', 10, 1) BREAK
			END
			
			
			DECLARE @tmpquantity INT = NULL
			DECLARE @bookid INT = NULL
			SELECT @bookid=BookID FROM Books WHERE ISBN=@isbn
			SELECT @tmpquantity=QuantOfAvBooks FROM AvailabilityCheck WHERE BookID= @bookid
			
			IF @tmpquantity < 1 ---@quantity
			BEGIN
				RAISERROR('Ksiazka niedostepna!', 10, 1) BREAK
			END

			INSERT INTO Borrow
			VALUES (@bookid, @clientID, @dateFrom, @dateTo)
			UPDATE AvailabilityCheck SET QuantOfAvBooks=@tmpquantity-1 WHERE BookID=@bookid
			
			FETCH NEXT FROM BIN INTO @borrowID, @clientID, @isbn, @dateFrom, @dateTo
		END
	CLOSE BIN
	DEALLOCATE BIN
END
GO


CREATE TRIGGER BorrowDel
ON BorrowDet
INSTEAD OF DELETE
AS
BEGIN
	DECLARE BDEL CURSOR FOR
		SELECT *
		FROM deleted
	OPEN BDEL
		DECLARE @borrowID INT
		DECLARE @clientID INT
		DECLARE @isbn VARCHAR(255)
		DECLARE @dateFrom DATE
		DECLARE @dateTo DATE
		FETCH NEXT FROM BDEL INTO @borrowID, @clientID, @isbn, @dateFrom, @dateTo
		WHILE @@FETCH_STATUS = 0
		
		BEGIN

			DELETE FROM Borrow WHERE BorrowID = @borrowID
			
			FETCH NEXT FROM BDEL INTO @borrowID, @clientID, @isbn, @dateFrom, @dateTo
		END
	CLOSE BDEL
	DEALLOCATE BDEL
END

go



CREATE TRIGGER BorrowUp
ON BorrowDet
INSTEAD OF UPDATE
AS
BEGIN
	DECLARE BDEL CURSOR FOR
		SELECT *
		FROM inserted
	OPEN BDEL
		DECLARE @borrowID INT
		DECLARE @clientID INT
		DECLARE @isbn VARCHAR(255)
		DECLARE @dateFrom DATE
		DECLARE @dateTo DATE
		FETCH NEXT FROM BDEL INTO @borrowID, @clientID, @isbn, @dateFrom, @dateTo
		WHILE @@FETCH_STATUS = 0
		
		BEGIN
			DECLARE @bookid INT = NULL
			SELECT @bookid=BookID FROM Books WHERE ISBN=@isbn
			
			UPDATE Borrow SET ClientID = @clientID, bookID = @bookID, BorrowDate = @dateFrom, ReturnDate = @dateTo
			WHERE BorrowID = @borrowID
			
			FETCH NEXT FROM BDEL INTO @borrowID, @clientID, @isbn, @dateFrom, @dateTo
		END
	CLOSE BDEL
	DEALLOCATE BDEL
END

go

