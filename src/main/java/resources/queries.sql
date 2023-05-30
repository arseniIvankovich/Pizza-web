--Address

--SQL_SELECT_BY_STREET_HOUSE_ENTRANCE_FLAT:

SELECT "AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber"
FROM public."Address"
WHERE "StreetName" = ? AND "HouseNumber" = ? AND "Entrance" = ? AND "FlatNumber" = ? LIMIT 1;


--SQL_SELECT_BY_ID:

SELECT "AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber"
FROM public."Address"
HERE "AddressID" = ?;


--SQL_DELETE_BY_STREET:

DELETE FROM public."Address" WHERE "StreetName" = ?;


--SQL_DELETE_BY_ID:

DELETE FROM public."Address" WHERE "AddressID" = ?;


--SQL_CREATE_ADDRESS:

INSERT INTO public."Address"("StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (?, ?, ?, ?);


--SQL_UPDATE:

UPDATE public."Address" SET "StreetName"=?, "HouseNumber"=?, "Entrance"=?, "FlatNumber"=? WHERE "AddressID" = ?;

------------------------------------------------------------------------------------------------------------------------

--BaseAddress

--SQL_SELECT_STREET:

SELECT "AddressId", "Street"
FROM public."Addresses"
WHERE "Street" = ?;

------------------------------------------------------------------------------------------------------------------------

--Drink

--SQL_SELECT_BY_ID:

SELECT "DrinkID", "Name", "Capacity", "Price"
FROM public."Drink"
WHERE "DrinkID" = ?;


--SQL_DELETE_BY_ID:

DELETE FROM public."Drink" WHERE "Name" = ?;


--SQL_DELETE_BY_ID:

DELETE FROM public."Drink" WHERE "DrinkID" = ?;


--SQL_CREATE_DRINK:

INSERT INTO public."Drink"("Name", "Capacity", "Price") VALUES (?, ?, ?);


--SQL_UPDATE:

UPDATE public."Drink" SET "Name"=?, "Capacity"=?, "Price"=? WHERE "DrinkID" = ?;

------------------------------------------------------------------------------------------------------------------------

--Order

--SQL_SELECT_BY_ID:

SELECT "OrderID", "Status", "DeliveryDate", "PaymentMethod"
FROM public."Order"
WHERE "OrderID" = ?;


--SQL_INNER_1:

SELECT "DrinkID", "NumberOfDrinks"
FROM public."Drink_order" WHERE "OrderID" = ?;


--SQL_INNER_2:

SELECT "PizzaID", "NumberOfPizzas"
FROM public."Pizza_Order" WHERE "OrderID" = ?;


--SQL_DELETE_BY_ID:

DELETE FROM public."Order" WHERE "OrderID" = ?;


--SQL_CREATE_ORDER:

INSERT INTO public."Order"("Status", "DeliveryDate", "PaymentMethod") VALUES (?, ?, ?);


--SQL_UPDATE:

UPDATE public."Order" SET "Status"=?, "DeliveryDate"=?, "PaymentMethod"=? WHERE "OrderID" = ?;


--SQL_LAST_ID:

SELECT "OrderID"
FROM public."Order"
WHERE "Status" = ? AND "DeliveryDate" = ? AND "PaymentMethod" = ?
ORDER BY "OrderID" DESC
LIMIT 1;


--SQL_INNER_DRINK:

INSERT INTO public."Drink_order"("OrderID", "DrinkID", "NumberOfDrinks") VALUES (?, ?, ?);


--SQL_INNER_PIZZA:

INSERT INTO public."Pizza_Order"("PizzaID", "OrderID", "NumberOfPizzas") VALUES (?, ?, ?);

------------------------------------------------------------------------------------------------------------------------

--Pizza

--SQL_SELECT_BY_ID:

SELECT "PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size"
FROM public."Pizza"
WHERE "PizzaID" = ?;


--SQL_DELETE_BY_ID:

DELETE FROM public."Pizza" WHERE "Name" = ?;


--SQL_DELETE_BY_ID:

DELETE FROM public."Pizza" WHERE "PizzaID" = ?;


--SQL_CREATE_PIZZA:

INSERT INTO public."Pizza"("Name","Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (?, ?, ?, ?, ?, ?);


--SQL_UPDATE:

UPDATE public."Pizza" SET "Name"=?, "Ingredients"=?, "TypeDrough"=?, "BasicWeight"=?, "Price"=?, "Size"=? WHERE "PizzaID"=?;

------------------------------------------------------------------------------------------------------------------------

--VacancyDao

--SELECT_by_ID:

SELECT "VacancyID", "Salary", "Trial", "Name" FROM public."Vacancy" WHERE = ?;


--SQL_INNER:

SELECT "UserID" FROM public."User_Vacancy" WHERE "VacancyID" = ?;


--SQL_DELETE_BY_NAME:

DELETE FROM public."Vacancy" WHERE "Name" = ?;


--SQL_DELETE_BY_ID:

DELETE FROM public."Vacancy" WHERE "VacancyID" = ?;


--SQL_CREATE_ADDRESS:

INSERT INTO public."Vacancy"("Salary", "Trial", "Name") VALUES (?, ?, ?);


--SQL_INNER:

INSERT INTO public."User_Vacancy"("VacancyID", "UserID") VALUES (?, ?);


--SQL_UPDATE:

UPDATE public."Vacancy" SET "Salary"=?, "Trial"=?, "Name"=? WHERE "VacancyID" = ?;


--SQL_SELECT_BY_NAME_TYPE_SIZE:

SELECT "VacancyID", "Salary", "Trial", "Name" FROM public."Vacancy" WHERE "Name" = ?;


--SQL_LAST_ID:

INSERT INTO public."User_Vacancy"("VacancyID", "UserID") VALUES (?, ?);

------------------------------------------------------------------------------------------------------------------------

--UserDao

--SQL_SELECT_ALL:

SELECT "UserID", "Role_id", "First_SecondName", "Password", "Email", "Phone_number", "Address_id", "Order_id" FROM public."User";


--SQL_SELECT_BY_ID:

SELECT "UserID", "Role_id", "First_SecondName", "Password", "Email", "Phone_number", "Address_id", "Order_id" FROM public."User" WHERE "UserID" = ?;


--SQL_DELETE_BY_ID:

--DELETE FROM public."User" WHERE "Email" = ?;


--SQL_DELETE_BY_ID:

DELETE FROM public."User" WHERE "UserID" = ?;


--SQL_CREATE_USER:

INSERT INTO public."User"("Role_id", "First_SecondName", "Password", "Email", "Phone_number", "Address_id") VALUES (?, ?, ?, ?, ?, ?);


--SQL_UPDATE:

UPDATE public."User" SET "First_SecondName"=?, "Email"=?, "Phone_number"=?, "Address_id"=? WHERE "UserID" = ?;


--SQL_UPDATE:

UPDATE public."User" SET "Order_id"=? WHERE "UserID"=?;


--SQL_SELECT_ALL:

SELECT "UserID", "Role_id", "First_SecondName", "Password", "Phone_number", "Address_id", "Order_id", "Email" FROM public."User" WHERE "Order_id" IS NOT NULL;


--getAllNotAdmin:

SELECT "UserID", "Role_id", "First_SecondName", "Password", "Phone_number", "Address_id", "Order_id", "Email" FROM public."User" WHERE "Role_id" != 1;


--findByEmail:

SELECT "UserID", "Role_id", "First_SecondName", "Password", "Email", "Phone_number", "Address_id", "Order_id" FROM public."User" WHERE "Email" = ?;


--checkUserByEmail:

SELECT "UserID", "Role_id", "First_SecondName", "Password", "Email", "Phone_number", "Address_id", "Order_id" FROM public."User" WHERE "Email" = ?;

------------------------------------------------------------------------------------------------------------------------

--RoleDao

--findEntityById:

SELECT "RoleID", "Name" FROM public."Role" WHERE "RoleID" = ?;


--SQL_DELETE_BY_ROLE:

DELETE FROM public."Role" WHERE "Name" = ?;


--delete_by_id:

DELETE FROM public."Role" WHERE "RoleID" = ?;


--SQL_CREATE_ADDRESS:

INSERT INTO public."Role"("Name") VALUES (?);


--SQL_UPDATE:

UPDATE public."Role" SET "Name"=? WHERE "RoleID" = ?;
