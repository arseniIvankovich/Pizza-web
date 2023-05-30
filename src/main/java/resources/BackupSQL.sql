--
-- PostgreSQL database dump
--

-- Dumped from database version 12.15 (Ubuntu 12.15-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.15 (Ubuntu 12.15-0ubuntu0.20.04.1)

-- Started on 2023-05-29 20:40:40 +03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3107 (class 1262 OID 16388)
-- Name: Pizza-web; Type: DATABASE; Schema: -; Owner: Teamlead
--

CREATE DATABASE "Pizza-web" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE "Pizza-web" OWNER TO "Teamlead";

\connect -reuse-previous=on "dbname='Pizza-web'"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 207 (class 1259 OID 16456)
-- Name: Address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Address" (
    "AddressID" integer NOT NULL,
    "StreetName" text NOT NULL,
    "HouseNumber" integer NOT NULL,
    "Entrance" integer,
    "FlatNumber" integer
);


ALTER TABLE public."Address" OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16454)
-- Name: Address_AddressID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Address_AddressID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Address_AddressID_seq" OWNER TO postgres;

--
-- TOC entry 3108 (class 0 OID 0)
-- Dependencies: 206
-- Name: Address_AddressID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Address_AddressID_seq" OWNED BY public."Address"."AddressID";


--
-- TOC entry 225 (class 1259 OID 16704)
-- Name: Addresses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Addresses" (
    "AddressId" integer NOT NULL,
    "Street" text
);


ALTER TABLE public."Addresses" OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16702)
-- Name: Addresses_AddressId_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Addresses_AddressId_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Addresses_AddressId_seq" OWNER TO postgres;

--
-- TOC entry 3109 (class 0 OID 0)
-- Dependencies: 224
-- Name: Addresses_AddressId_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Addresses_AddressId_seq" OWNED BY public."Addresses"."AddressId";


--
-- TOC entry 203 (class 1259 OID 16434)
-- Name: Drink; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Drink" (
    "DrinkID" bigint NOT NULL,
    "Name" text NOT NULL,
    "Capacity" numeric NOT NULL,
    "Price" numeric NOT NULL
);


ALTER TABLE public."Drink" OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16432)
-- Name: Drink_DrinkID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Drink_DrinkID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Drink_DrinkID_seq" OWNER TO postgres;

--
-- TOC entry 3110 (class 0 OID 0)
-- Dependencies: 202
-- Name: Drink_DrinkID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Drink_DrinkID_seq" OWNED BY public."Drink"."DrinkID";


--
-- TOC entry 217 (class 1259 OID 16514)
-- Name: Drink_order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Drink_order" (
    "Id" bigint NOT NULL,
    "OrderID" bigint NOT NULL,
    "DrinkID" bigint NOT NULL,
    "NumberOfDrinks" integer NOT NULL
);


ALTER TABLE public."Drink_order" OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16512)
-- Name: Drink_order_Id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Drink_order_Id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Drink_order_Id_seq" OWNER TO postgres;

--
-- TOC entry 3111 (class 0 OID 0)
-- Dependencies: 216
-- Name: Drink_order_Id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Drink_order_Id_seq" OWNED BY public."Drink_order"."Id";


--
-- TOC entry 211 (class 1259 OID 16478)
-- Name: Order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Order" (
    "OrderID" integer NOT NULL,
    "Status" boolean NOT NULL,
    "DeliveryDate" timestamp without time zone,
    "PaymentMethod" text NOT NULL
);


ALTER TABLE public."Order" OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16476)
-- Name: Order_OrderID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Order_OrderID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Order_OrderID_seq" OWNER TO postgres;

--
-- TOC entry 3112 (class 0 OID 0)
-- Dependencies: 210
-- Name: Order_OrderID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Order_OrderID_seq" OWNED BY public."Order"."OrderID";


--
-- TOC entry 209 (class 1259 OID 16467)
-- Name: Pizza; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Pizza" (
    "PizzaID" integer NOT NULL,
    "Name" text NOT NULL,
    "Ingredients" text,
    "TypeDrough" boolean NOT NULL,
    "BasicWeight" numeric NOT NULL,
    "Price" numeric NOT NULL,
    "Size" boolean NOT NULL
);


ALTER TABLE public."Pizza" OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16493)
-- Name: Pizza_Order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Pizza_Order" (
    "Id" bigint NOT NULL,
    "PizzaID" bigint NOT NULL,
    "OrderID" bigint NOT NULL,
    "NumberOfPizzas" integer NOT NULL
);


ALTER TABLE public."Pizza_Order" OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16487)
-- Name: Pizza_Order_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Pizza_Order_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Pizza_Order_ID_seq" OWNER TO postgres;

--
-- TOC entry 3113 (class 0 OID 0)
-- Dependencies: 212
-- Name: Pizza_Order_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Pizza_Order_ID_seq" OWNED BY public."Pizza_Order"."Id";


--
-- TOC entry 214 (class 1259 OID 16491)
-- Name: Pizza_Order_Order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Pizza_Order_Order_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Pizza_Order_Order_id_seq" OWNER TO postgres;

--
-- TOC entry 3114 (class 0 OID 0)
-- Dependencies: 214
-- Name: Pizza_Order_Order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Pizza_Order_Order_id_seq" OWNED BY public."Pizza_Order"."OrderID";


--
-- TOC entry 213 (class 1259 OID 16489)
-- Name: Pizza_Order_Pizza_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Pizza_Order_Pizza_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Pizza_Order_Pizza_id_seq" OWNER TO postgres;

--
-- TOC entry 3115 (class 0 OID 0)
-- Dependencies: 213
-- Name: Pizza_Order_Pizza_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Pizza_Order_Pizza_id_seq" OWNED BY public."Pizza_Order"."PizzaID";


--
-- TOC entry 208 (class 1259 OID 16465)
-- Name: Pizza_PizzaID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Pizza_PizzaID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Pizza_PizzaID_seq" OWNER TO postgres;

--
-- TOC entry 3116 (class 0 OID 0)
-- Dependencies: 208
-- Name: Pizza_PizzaID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Pizza_PizzaID_seq" OWNED BY public."Pizza"."PizzaID";


--
-- TOC entry 205 (class 1259 OID 16445)
-- Name: Role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Role" (
    "RoleID" integer NOT NULL,
    "Name" text NOT NULL
);


ALTER TABLE public."Role" OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16443)
-- Name: Role_RoleID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Role_RoleID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Role_RoleID_seq" OWNER TO postgres;

--
-- TOC entry 3117 (class 0 OID 0)
-- Dependencies: 204
-- Name: Role_RoleID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Role_RoleID_seq" OWNED BY public."Role"."RoleID";


--
-- TOC entry 221 (class 1259 OID 16562)
-- Name: User; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."User" (
    "UserID" bigint NOT NULL,
    "Role_id" bigint NOT NULL,
    "First_SecondName" text NOT NULL,
    "Password" text NOT NULL,
    "Phone_number" text,
    "Address_id" bigint,
    "Order_id" bigint,
    "Email" text NOT NULL
);


ALTER TABLE public."User" OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16560)
-- Name: User_UserID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."User_UserID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."User_UserID_seq" OWNER TO postgres;

--
-- TOC entry 3118 (class 0 OID 0)
-- Dependencies: 220
-- Name: User_UserID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."User_UserID_seq" OWNED BY public."User"."UserID";


--
-- TOC entry 223 (class 1259 OID 16588)
-- Name: User_Vacancy; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."User_Vacancy" (
    id bigint NOT NULL,
    "VacancyID" bigint,
    "UserID" bigint
);


ALTER TABLE public."User_Vacancy" OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16586)
-- Name: User_Vacancy_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."User_Vacancy_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."User_Vacancy_id_seq" OWNER TO postgres;

--
-- TOC entry 3119 (class 0 OID 0)
-- Dependencies: 222
-- Name: User_Vacancy_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."User_Vacancy_id_seq" OWNED BY public."User_Vacancy".id;


--
-- TOC entry 219 (class 1259 OID 16551)
-- Name: Vacancy; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Vacancy" (
    "VacancyID" bigint NOT NULL,
    "Salary" numeric NOT NULL,
    "Trial" integer NOT NULL,
    "Name" text NOT NULL
);


ALTER TABLE public."Vacancy" OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16549)
-- Name: Vacancy_VacancyID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Vacancy_VacancyID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Vacancy_VacancyID_seq" OWNER TO postgres;

--
-- TOC entry 3120 (class 0 OID 0)
-- Dependencies: 218
-- Name: Vacancy_VacancyID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Vacancy_VacancyID_seq" OWNED BY public."Vacancy"."VacancyID";


--
-- TOC entry 2906 (class 2604 OID 16459)
-- Name: Address AddressID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Address" ALTER COLUMN "AddressID" SET DEFAULT nextval('public."Address_AddressID_seq"'::regclass);


--
-- TOC entry 2916 (class 2604 OID 16707)
-- Name: Addresses AddressId; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Addresses" ALTER COLUMN "AddressId" SET DEFAULT nextval('public."Addresses_AddressId_seq"'::regclass);


--
-- TOC entry 2904 (class 2604 OID 16534)
-- Name: Drink DrinkID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Drink" ALTER COLUMN "DrinkID" SET DEFAULT nextval('public."Drink_DrinkID_seq"'::regclass);


--
-- TOC entry 2912 (class 2604 OID 16517)
-- Name: Drink_order Id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Drink_order" ALTER COLUMN "Id" SET DEFAULT nextval('public."Drink_order_Id_seq"'::regclass);


--
-- TOC entry 2908 (class 2604 OID 16481)
-- Name: Order OrderID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Order" ALTER COLUMN "OrderID" SET DEFAULT nextval('public."Order_OrderID_seq"'::regclass);


--
-- TOC entry 2907 (class 2604 OID 16470)
-- Name: Pizza PizzaID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Pizza" ALTER COLUMN "PizzaID" SET DEFAULT nextval('public."Pizza_PizzaID_seq"'::regclass);


--
-- TOC entry 2909 (class 2604 OID 16496)
-- Name: Pizza_Order Id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Pizza_Order" ALTER COLUMN "Id" SET DEFAULT nextval('public."Pizza_Order_ID_seq"'::regclass);


--
-- TOC entry 2910 (class 2604 OID 16497)
-- Name: Pizza_Order PizzaID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Pizza_Order" ALTER COLUMN "PizzaID" SET DEFAULT nextval('public."Pizza_Order_Pizza_id_seq"'::regclass);


--
-- TOC entry 2911 (class 2604 OID 16498)
-- Name: Pizza_Order OrderID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Pizza_Order" ALTER COLUMN "OrderID" SET DEFAULT nextval('public."Pizza_Order_Order_id_seq"'::regclass);


--
-- TOC entry 2905 (class 2604 OID 16448)
-- Name: Role RoleID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Role" ALTER COLUMN "RoleID" SET DEFAULT nextval('public."Role_RoleID_seq"'::regclass);


--
-- TOC entry 2914 (class 2604 OID 16565)
-- Name: User UserID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User" ALTER COLUMN "UserID" SET DEFAULT nextval('public."User_UserID_seq"'::regclass);


--
-- TOC entry 2915 (class 2604 OID 16591)
-- Name: User_Vacancy id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User_Vacancy" ALTER COLUMN id SET DEFAULT nextval('public."User_Vacancy_id_seq"'::regclass);


--
-- TOC entry 2913 (class 2604 OID 16554)
-- Name: Vacancy VacancyID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Vacancy" ALTER COLUMN "VacancyID" SET DEFAULT nextval('public."Vacancy_VacancyID_seq"'::regclass);


--
-- TOC entry 3083 (class 0 OID 16456)
-- Dependencies: 207
-- Data for Name: Address; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (1, 'Ленина', 1, 1, 15);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (2, 'Мира', 2, 2, 36);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (3, 'Гагарина', 3, 3, 82);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (4, 'Пушкина', 4, 4, 3);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (5, 'Жукова', 5, 5, 47);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (6, 'Лермонтова', 6, 2, 61);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (7, 'Кирова', 7, 3, 24);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (8, 'Советская', 8, 4, 57);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (9, 'Богдановича', 9, 5, 4);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (10, 'Карла Маркса', 10, 1, 38);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (11, 'Горького', 11, 2, 73);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (12, 'Красноармейская', 12, 3, 11);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (13, 'Фрунзе', 13, 4, 29);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (14, 'Коммунистическая', 14, 5, 52);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (15, 'Октябрьская', 15, 2, 81);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (16, 'Юбилейная', 16, 3, 2);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (17, 'Революционная', 17, 4, 49);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (18, 'Восточная', 18, 5, 68);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (19, 'Зеленая', 19, 1, 10);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (20, 'Колхозная', 20, 2, 39);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (21, 'Набережная', 21, 3, 76);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (22, 'Заречная', 22, 4, 14);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (23, 'Лесная', 23, 5, 30);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (24, 'Радиальная', 24, 1, 54);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (25, 'Первомайская', 25, 2, 87);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (26, 'Садовая', 26, 3, 5);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (27, 'Центральная', 27, 4, 42);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (28, 'Солнечная', 28, 5, 69);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (29, 'Клубная', 29, 1, 16);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (30, 'Спортивная', 30, 2, 31);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (31, 'Новая', 31, 3, 57);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (32, 'Садовый переулок', 32, 4, 84);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (33, 'Почтовая', 33, 5, 3);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (34, 'Комсомольская', 34, 1, 29);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (35, 'Кропоткина', 110, 1, 43);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (40, 'Ленина', 1, 1, 1);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (41, 'Ленина', 16, 5, 279);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (42, 'Ленина', 16, 5, 279);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (43, 'Ленина', 16, 5, 278);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (44, 'Ленина', 16, 5, 279);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (45, 'Ленина', 15, 5, 279);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (46, 'Ленина', 16, 5, 279);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (47, 'Мира', 16, 5, 279);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (48, 'Мира', 16, 5, 279);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (49, 'Мира', 16, 5, 279);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (53, 'Ленина', 1, 1, 1);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (54, 'Ленина', 23, 1, 31);
INSERT INTO public."Address" ("AddressID", "StreetName", "HouseNumber", "Entrance", "FlatNumber") VALUES (55, 'Ленина', 1, 1, 1);


--
-- TOC entry 3101 (class 0 OID 16704)
-- Dependencies: 225
-- Data for Name: Addresses; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (31, 'Проспект Независимости');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (32, 'Козлова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (33, 'Притыцкого');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (34, 'Сурганова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (35, 'Карла Маркса');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (36, 'Партизанский проспект');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (37, 'Комсомольская');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (38, 'Площадь Победы');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (39, 'Красная');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (40, 'Немига Гикало');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (41, 'Проспект Дзержинского');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (42, 'Максима Богдановича');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (43, 'Калиновского');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (44, 'Жукова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (45, 'Чичерина');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (46, 'Проспект Машерова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (47, 'Городской Вал');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (48, 'Тимирязева');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (49, 'Октябрьская');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (50, 'Фрунзе');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (51, 'Свердлова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (52, 'Проспект Победителей');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (53, 'Якуба Коласа');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (54, 'Мельникайте');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (55, 'Маяковского');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (56, 'Рокоссовского');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (57, 'Козлова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (58, 'Ленина');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (59, 'Петра Мстиславца Куйбышева');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (60, 'Грибоедова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (61, 'Мирошниченко');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (62, 'Скрыганова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (63, 'Фабрициуса');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (64, 'Тимошенко');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (65, 'Гурского');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (66, 'Волгоградская');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (67, 'Карастояновой');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (68, 'Нестерова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (69, 'Народная');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (70, 'Киркорова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (71, 'Восточная');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (72, 'Кнорина');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (73, 'Крупской');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (74, 'Белинского');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (75, 'Налибокская');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (76, 'Коллонтай');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (77, 'Аэродромная Клары Цеткин');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (78, 'Пушкина');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (79, 'Неманская');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (80, 'Московская');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (81, 'Партизанская');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (82, 'Революционная');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (83, 'Володарского');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (84, 'Долгобродская');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (85, 'Богдановича');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (86, 'Захарова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (87, 'Колотушкина');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (88, 'Бахаревича');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (89, 'Короля');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (90, 'Дружбы');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (91, 'Столетова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (92, 'Чайковского');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (93, 'Солтыса');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (94, 'Логойского');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (95, 'Краснозвездная');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (96, 'Семашко');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (97, 'Михалова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (98, 'Платонова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (99, 'Малинина');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (100, 'Степанова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (101, 'Шаранговича');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (102, 'Кирова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (103, 'Берута');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (104, 'Барыкина');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (105, 'Корженевского');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (106, 'Кнезы Миндовга');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (107, 'Космонавтов');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (108, 'Каменная Горка');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (109, 'Серова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (110, 'Терешковой');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (111, 'Фатина');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (112, 'Широкая');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (113, 'Змитрока Бядули');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (114, 'Сухаревская');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (115, 'Щорса');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (116, 'Семашко');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (117, 'Михалова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (118, 'Платонова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (119, 'Малинина');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (120, 'Степанова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (121, 'Шаранговича');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (122, 'Кирова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (123, 'Берута');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (124, 'Барыкина');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (125, 'Корженевского');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (126, 'Кнезы Миндовга');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (127, 'Космонавтов');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (128, 'Каменная Горка');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (129, 'Серова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (130, 'Терешковой');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (131, 'Фатина');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (132, 'Широкая');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (133, 'Змитрока Бядули');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (134, 'Сухаревская');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (135, 'Щорса');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (2, 'Мира');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (3, 'Гагарина');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (4, 'Пушкина');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (5, 'Жукова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (6, 'Лермонтова');
INSERT INTO public."Addresses" ("AddressId", "Street") VALUES (7, 'Кирова');


--
-- TOC entry 3079 (class 0 OID 16434)
-- Dependencies: 203
-- Data for Name: Drink; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Drink" ("DrinkID", "Name", "Capacity", "Price") VALUES (1, 'Coca-Cola', 0.5, 1.99);
INSERT INTO public."Drink" ("DrinkID", "Name", "Capacity", "Price") VALUES (2, 'Coca-Cola', 1, 2.99);
INSERT INTO public."Drink" ("DrinkID", "Name", "Capacity", "Price") VALUES (3, 'Coca-Cola Zero', 0.5, 1.99);
INSERT INTO public."Drink" ("DrinkID", "Name", "Capacity", "Price") VALUES (4, 'Coca-Cola Zero', 1, 2.99);
INSERT INTO public."Drink" ("DrinkID", "Name", "Capacity", "Price") VALUES (5, 'Fanta', 0.5, 1.99);
INSERT INTO public."Drink" ("DrinkID", "Name", "Capacity", "Price") VALUES (6, 'Fanta', 1, 2.99);
INSERT INTO public."Drink" ("DrinkID", "Name", "Capacity", "Price") VALUES (7, 'Sprite', 0.5, 1.99);
INSERT INTO public."Drink" ("DrinkID", "Name", "Capacity", "Price") VALUES (8, 'Sprite', 1, 2.99);
INSERT INTO public."Drink" ("DrinkID", "Name", "Capacity", "Price") VALUES (9, 'Ballentine''s', 0.5, 62.99);
INSERT INTO public."Drink" ("DrinkID", "Name", "Capacity", "Price") VALUES (10, 'Bonaqua', 0.5, 1.99);


--
-- TOC entry 3093 (class 0 OID 16514)
-- Dependencies: 217
-- Data for Name: Drink_order; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Drink_order" ("Id", "OrderID", "DrinkID", "NumberOfDrinks") VALUES (28, 24, 7, 2);
INSERT INTO public."Drink_order" ("Id", "OrderID", "DrinkID", "NumberOfDrinks") VALUES (29, 24, 9, 1);
INSERT INTO public."Drink_order" ("Id", "OrderID", "DrinkID", "NumberOfDrinks") VALUES (30, 25, 7, 2);
INSERT INTO public."Drink_order" ("Id", "OrderID", "DrinkID", "NumberOfDrinks") VALUES (31, 25, 9, 1);
INSERT INTO public."Drink_order" ("Id", "OrderID", "DrinkID", "NumberOfDrinks") VALUES (32, 26, 7, 2);
INSERT INTO public."Drink_order" ("Id", "OrderID", "DrinkID", "NumberOfDrinks") VALUES (33, 26, 9, 1);
INSERT INTO public."Drink_order" ("Id", "OrderID", "DrinkID", "NumberOfDrinks") VALUES (34, 27, 7, 2);
INSERT INTO public."Drink_order" ("Id", "OrderID", "DrinkID", "NumberOfDrinks") VALUES (35, 27, 9, 1);
INSERT INTO public."Drink_order" ("Id", "OrderID", "DrinkID", "NumberOfDrinks") VALUES (36, 28, 7, 2);
INSERT INTO public."Drink_order" ("Id", "OrderID", "DrinkID", "NumberOfDrinks") VALUES (37, 28, 9, 1);
INSERT INTO public."Drink_order" ("Id", "OrderID", "DrinkID", "NumberOfDrinks") VALUES (38, 42, 9, 1);
INSERT INTO public."Drink_order" ("Id", "OrderID", "DrinkID", "NumberOfDrinks") VALUES (39, 42, 10, 1);
INSERT INTO public."Drink_order" ("Id", "OrderID", "DrinkID", "NumberOfDrinks") VALUES (40, 42, 6, 1);
INSERT INTO public."Drink_order" ("Id", "OrderID", "DrinkID", "NumberOfDrinks") VALUES (41, 46, 4, 1);
INSERT INTO public."Drink_order" ("Id", "OrderID", "DrinkID", "NumberOfDrinks") VALUES (42, 55, 9, 2);


--
-- TOC entry 3087 (class 0 OID 16478)
-- Dependencies: 211
-- Data for Name: Order; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (25, false, '2023-05-09 00:00:00', 'Наличные');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (26, false, '2023-05-09 00:00:00', 'Наличные');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (27, false, '2023-05-09 00:00:00', 'Наличные');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (28, false, '2023-05-09 00:00:00', 'Наличные');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (29, false, '2023-05-10 00:00:00', 'Наличные');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (30, false, '2023-05-10 00:00:00', 'Наличные');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (31, false, '2023-05-10 00:00:00', 'Наличные');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (32, false, '2023-05-10 00:00:00', 'Наличные');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (33, false, '2023-05-10 00:00:00', 'Наличные');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (34, false, '2023-05-10 00:00:00', 'Наличные');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (35, false, '2023-05-10 00:00:00', 'Наличные');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (36, false, '2023-05-10 00:00:00', 'Наличные');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (37, false, '2023-05-10 00:00:00', 'Наличные');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (38, false, '2023-05-10 00:00:00', 'Наличные');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (39, false, '2023-05-10 00:00:00', 'Наличные');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (40, false, '2023-05-10 18:44:56.328', 'Наличные');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (41, false, '2023-05-10 20:36:31.722', 'Наличные');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (24, true, '2023-05-09 00:00:00', 'Наличные');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (42, false, '2023-05-12 14:26:01.054', 'Наличные');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (43, false, '2023-05-12 14:27:44.246', 'Наличные');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (44, false, '2023-05-12 14:30:23.744', 'Наличные');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (45, false, '2023-05-12 14:58:54.263', 'Наличные');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (46, false, '2023-05-12 22:24:57.033', 'Наличные');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (53, false, '2023-05-15 14:02:37.759', 'Наличными');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (54, false, '2023-05-17 11:12:20.177', 'Наличными');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (55, false, '2023-05-17 11:12:27.706', 'Наличными');
INSERT INTO public."Order" ("OrderID", "Status", "DeliveryDate", "PaymentMethod") VALUES (56, false, '2023-05-26 11:56:13.019', 'Наличными');


--
-- TOC entry 3085 (class 0 OID 16467)
-- Dependencies: 209
-- Data for Name: Pizza; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (1, 'Пепперони', 'пицца-соус, пепперони, сыр моцарелла, базилик', true, 500, 16.99, true);
INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (2, 'Пепперони', 'пицца-соус, пепперони, сыр моцарелла, базилик', false, 600, 18.99, true);
INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (3, 'Пепперони', 'пицца-соус, пепперони, сыр моцарелла, базилик', true, 650, 21.99, false);
INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (4, 'Пепперони', 'пицца-соус, пепперони, сыр моцарелла, базилик', false, 700, 23.99, false);
INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (5, '4 Сыра', 'сырный соус, сливочный сыр, сыр фета, сыр дорблю (может отличаться от изображения на сайте), сыр моцарелла, базилик', true, 500, 18.99, true);
INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (6, '4 Сыра', 'сырный соус, сливочный сыр, сыр фета, сыр дорблю (может отличаться от изображения на сайте), сыр моцарелла, базилик', false, 550, 20.99, true);
INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (7, '4 Сыра', 'сырный соус, сливочный сыр, сыр фета, сыр дорблю (может отличаться от изображения на сайте), сыр моцарелла, базилик', true, 650, 23.99, false);
INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (8, '4 Сыра', 'сырный соус, сливочный сыр, сыр фета, сыр дорблю (может отличаться от изображения на сайте), сыр моцарелла, базилик', false, 700, 25.99, false);
INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (9, 'Гавайская', 'сырный соус, ветчина, филе цыпленка, ананасы, сыр моцарелла, базилик', true, 500, 12.99, true);
INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (10, 'Гавайская', 'сырный соус, ветчина, филе цыпленка, ананасы, сыр моцарелла, базилик', false, 550, 14.99, true);
INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (11, 'Гавайская', 'сырный соус, ветчина, филе цыпленка, ананасы, сыр моцарелла, базилик', true, 650, 17.99, false);
INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (12, 'Гавайская', 'сырный соус, ветчина, филе цыпленка, ананасы, сыр моцарелла, базилик', false, 700, 19.99, false);
INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (13, 'Барбекю', 'пицца-соус, грудинка (свинина), филе цыпленка, свежий лук, соус барбекю, сыр моцарелла, базилик', true, 500, 16.99, true);
INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (14, 'Барбекю', 'пицца-соус, грудинка (свинина), филе цыпленка, свежий лук, соус барбекю, сыр моцарелла, базилик', false, 550, 18.99, true);
INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (15, 'Барбекю', 'пицца-соус, грудинка (свинина), филе цыпленка, свежий лук, соус барбекю, сыр моцарелла, базилик', true, 650, 21.99, false);
INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (16, 'Барбекю', 'пицца-соус, грудинка (свинина), филе цыпленка, свежий лук, соус барбекю, сыр моцарелла, базилик', false, 700, 23.99, false);
INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (17, 'Сальмоне', 'соус пармеджано, креветки, сливочный сыр, сыр моцарелла, базилик', true, 500, 14.99, true);
INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (18, 'Сальмоне', 'соус пармеджано, креветки, сливочный сыр, сыр моцарелла, базилик', false, 550, 16.99, true);
INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (19, 'Сальмоне', 'соус пармеджано, креветки, сливочный сыр, сыр моцарелла, базилик', true, 650, 19.99, false);
INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (20, 'Сальмоне', 'соус пармеджано, креветки, сливочный сыр, сыр моцарелла, базилик', false, 700, 21.99, false);
INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (21, 'Деревенская', 'американский соус ранч, грудинка (свинина), свежий лук, соленые огурцы, свежие шампиньоны, сыр моцарелла, базилик', true, 500, 18.99, true);
INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (22, 'Деревенская', 'американский соус ранч, грудинка (свинина), свежий лук, соленые огурцы, свежие шампиньоны, сыр моцарелла, базилик', false, 550, 20.99, true);
INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (23, 'Деревенская', 'американский соус ранч, грудинка (свинина), свежий лук, соленые огурцы, свежие шампиньоны, сыр моцарелла, базилик', true, 650, 23.99, false);
INSERT INTO public."Pizza" ("PizzaID", "Name", "Ingredients", "TypeDrough", "BasicWeight", "Price", "Size") VALUES (24, 'Деревенская', 'американский соус ранч, грудинка (свинина), свежий лук, соленые огурцы, свежие шампиньоны, сыр моцарелла, базилик', false, 700, 25.99, false);


--
-- TOC entry 3091 (class 0 OID 16493)
-- Dependencies: 215
-- Data for Name: Pizza_Order; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (16, 9, 24, 2);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (17, 21, 24, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (18, 9, 25, 2);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (19, 21, 25, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (20, 9, 26, 2);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (21, 21, 26, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (22, 9, 27, 2);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (23, 21, 27, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (24, 9, 28, 2);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (25, 21, 28, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (26, 9, 29, 2);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (27, 5, 30, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (28, 5, 31, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (29, 5, 32, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (30, 21, 33, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (31, 9, 34, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (32, 9, 35, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (33, 5, 36, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (34, 9, 37, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (35, 9, 38, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (36, 9, 39, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (37, 9, 40, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (38, 9, 41, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (39, 3, 42, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (40, 5, 42, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (41, 9, 42, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (42, 5, 43, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (43, 9, 43, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (44, 9, 44, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (45, 5, 45, 9);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (46, 5, 46, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (47, 9, 46, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (48, 9, 53, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (49, 5, 54, 1);
INSERT INTO public."Pizza_Order" ("Id", "PizzaID", "OrderID", "NumberOfPizzas") VALUES (50, 17, 56, 1);


--
-- TOC entry 3081 (class 0 OID 16445)
-- Dependencies: 205
-- Data for Name: Role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Role" ("RoleID", "Name") VALUES (1, 'Администратор');
INSERT INTO public."Role" ("RoleID", "Name") VALUES (2, 'Курьер');
INSERT INTO public."Role" ("RoleID", "Name") VALUES (3, 'Пользователь');


--
-- TOC entry 3097 (class 0 OID 16562)
-- Dependencies: 221
-- Data for Name: User; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."User" ("UserID", "Role_id", "First_SecondName", "Password", "Phone_number", "Address_id", "Order_id", "Email") VALUES (1, 1, 'Иванкович Арсений', 'password', '+375298086623', NULL, NULL, 'arseniivankovich0@gmail.com');
INSERT INTO public."User" ("UserID", "Role_id", "First_SecondName", "Password", "Phone_number", "Address_id", "Order_id", "Email") VALUES (7, 3, 'Алексей Поповй', 'password2', '+375298784564', 1, NULL, 'popov1@gmail.com');
INSERT INTO public."User" ("UserID", "Role_id", "First_SecondName", "Password", "Phone_number", "Address_id", "Order_id", "Email") VALUES (8, 3, 'Алексей Поповйе', 'password3', '+784548765978', 1, NULL, 'popov2@gmail.com');
INSERT INTO public."User" ("UserID", "Role_id", "First_SecondName", "Password", "Phone_number", "Address_id", "Order_id", "Email") VALUES (9, 3, 'Алексей Попов', 'password1', '+375298086623', 1, NULL, 'popov3@gmail.com');
INSERT INTO public."User" ("UserID", "Role_id", "First_SecondName", "Password", "Phone_number", "Address_id", "Order_id", "Email") VALUES (15, 3, 'Вася Пупкинй', '$2a$10$eQ.ww9lq4kLl6ofrdGXDLewX/4SPBQemDamuoRV7LjiYICNzR5YoS', '+375298086623', 1, NULL, 'pupkin1@gmail.com');
INSERT INTO public."User" ("UserID", "Role_id", "First_SecondName", "Password", "Phone_number", "Address_id", "Order_id", "Email") VALUES (16, 3, 'Александр Алексеев', '$2a$10$l0Y93miSi7va1FVM6ZJD4uryVDAtgRyUbbiNK8OxOmK7bj9PWoxIS', '+375298086623', 1, NULL, 'alexander@gmaul.com');
INSERT INTO public."User" ("UserID", "Role_id", "First_SecondName", "Password", "Phone_number", "Address_id", "Order_id", "Email") VALUES (17, 3, 'Алексей Треуголов', '$2a$10$evW3f7LjDLTpMFe7MZQ0buXc4Qv5SXKyS6mJob85RfMypkdI8Wu3S', '+3758945612', 2, NULL, 'try@gmail.com');
INSERT INTO public."User" ("UserID", "Role_id", "First_SecondName", "Password", "Phone_number", "Address_id", "Order_id", "Email") VALUES (19, 1, 'Иванкович Арсений', '$2a$10$oMC2SEW0Bi45YnsxkwhaxeHVaWnraGbtBu7gWFkA0la5qzLCjXNbu', '+375298086623', NULL, NULL, 'ivars7613@gmail.com');
INSERT INTO public."User" ("UserID", "Role_id", "First_SecondName", "Password", "Phone_number", "Address_id", "Order_id", "Email") VALUES (20, 2, 'курьер ', '$2a$10$oMC2SEW0Bi45YnsxkwhaxeHVaWnraGbtBu7gWFkA0la5qzLCjXNbu', '+545', NULL, NULL, '2');
INSERT INTO public."User" ("UserID", "Role_id", "First_SecondName", "Password", "Phone_number", "Address_id", "Order_id", "Email") VALUES (10, 3, 'Вася Пупкин', '$2a$10$7q.zjX7tFsnBunEo7bGBMOxB5mXzamkgrE2CB6aolLQ5UTPHuxCkq', '+789457454', 2, 24, 'pupkin@gmail.com');
INSERT INTO public."User" ("UserID", "Role_id", "First_SecondName", "Password", "Phone_number", "Address_id", "Order_id", "Email") VALUES (11, 3, 'Оырары ТРПЕ', '$2a$10$oe4bQgc0yr14FBRnr8PteuoPnra.JLe6fGF.AULbosXxP4hszc//i', '+456487', 2, 25, 'pa@skf.com');
INSERT INTO public."User" ("UserID", "Role_id", "First_SecondName", "Password", "Phone_number", "Address_id", "Order_id", "Email") VALUES (12, 3, 'Алексей Попов', '$2a$10$/4TFFir9lvFa.s2WnIZtfe3ItToufkX.sgIXH0/FmPPzSbhycJjqC', '+4', 2, 26, 'jbehkfg@gmail.com');
INSERT INTO public."User" ("UserID", "Role_id", "First_SecondName", "Password", "Phone_number", "Address_id", "Order_id", "Email") VALUES (13, 3, 'ытофм ОРЛОр', '$2a$10$S6wHMoNgs/njJPg3SYyC/Ozt1/tGi7CVAqvX0OYbXSdCrRpE9iHLi', '+45458798', 2, 27, 'лыорщгфырщгмпфг');
INSERT INTO public."User" ("UserID", "Role_id", "First_SecondName", "Password", "Phone_number", "Address_id", "Order_id", "Email") VALUES (14, 3, 'Алексей Попов', '$2a$10$7CJV.PZfBa1M2L2/wN/oaez6WEfOWD1/55STNCpIChwLQCve7AyxS', '+45977456487', 2, 28, 'ishks@djhsk.com');
INSERT INTO public."User" ("UserID", "Role_id", "First_SecondName", "Password", "Phone_number", "Address_id", "Order_id", "Email") VALUES (24, 3, 'Мария Мишина', '$2a$10$lLSQAdY/6LDFi9.2yTomnOL0iYQeBXi88U/cann9qq5SqtEC/8Jye', '11111', 47, 55, '1111@gmail.com');
INSERT INTO public."User" ("UserID", "Role_id", "First_SecondName", "Password", "Phone_number", "Address_id", "Order_id", "Email") VALUES (18, 3, 'Рпаа рраапer', '$2a$10$bkBa5488/zxTDRXt.JUG2OSVNFRFvkeff/Ci4c.Cv2GKnjAN.gT2i', '+111', 1, 46, '111');
INSERT INTO public."User" ("UserID", "Role_id", "First_SecondName", "Password", "Phone_number", "Address_id", "Order_id", "Email") VALUES (23, 3, 'Сенькин Алексей', '$2a$10$bHu.gTDlAgaPo0BaWym3Ruha7IC82rgvBpBcpK.vcQ3YB4qG5AZbe', '123', 40, NULL, 'alex@mail');
INSERT INTO public."User" ("UserID", "Role_id", "First_SecondName", "Password", "Phone_number", "Address_id", "Order_id", "Email") VALUES (26, 3, 'Анечка Синькевич', '$2a$10$8Y9Q2rY6wl/T7tFQgAF8AeR7gkwG1SATI14mv6R3OWPDXW6iQVOWW', '375295343458', 54, 56, 'chocolate.ann.com@gmail.com');
INSERT INTO public."User" ("UserID", "Role_id", "First_SecondName", "Password", "Phone_number", "Address_id", "Order_id", "Email") VALUES (28, 3, 'Алексей', '$2a$10$qn6kgEuaA4CxI1hVe6kuVutc7Rh8lb2a436dGTtcbYo2IkrCX.QTO', '+375298086623', 1, NULL, 'popov341@gmail.com');
INSERT INTO public."User" ("UserID", "Role_id", "First_SecondName", "Password", "Phone_number", "Address_id", "Order_id", "Email") VALUES (30, 3, 'Алексей Тотов', '$2a$10$CjmTeGdpmM7PmxCAz86QWuj1eGbOa7xszOsiOTAy3e35HDaCkzNra', '+375298086623', 1, NULL, 'popov3418@gmail.com');
INSERT INTO public."User" ("UserID", "Role_id", "First_SecondName", "Password", "Phone_number", "Address_id", "Order_id", "Email") VALUES (31, 3, 'Петр петр', '$2a$10$0rgobkusPXZyf1JKixGIBuMMBz4GG/h/51nEr4AR8qYM5qbzp4D4W', '123456', 40, NULL, 'qwerty@werh');


--
-- TOC entry 3099 (class 0 OID 16588)
-- Dependencies: 223
-- Data for Name: User_Vacancy; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."User_Vacancy" (id, "VacancyID", "UserID") VALUES (6, 2, 18);
INSERT INTO public."User_Vacancy" (id, "VacancyID", "UserID") VALUES (9, 3, 18);
INSERT INTO public."User_Vacancy" (id, "VacancyID", "UserID") VALUES (10, 2, 24);
INSERT INTO public."User_Vacancy" (id, "VacancyID", "UserID") VALUES (11, 2, 24);
INSERT INTO public."User_Vacancy" (id, "VacancyID", "UserID") VALUES (12, 2, 24);
INSERT INTO public."User_Vacancy" (id, "VacancyID", "UserID") VALUES (13, 2, 24);
INSERT INTO public."User_Vacancy" (id, "VacancyID", "UserID") VALUES (14, 2, 24);
INSERT INTO public."User_Vacancy" (id, "VacancyID", "UserID") VALUES (15, 1, 26);


--
-- TOC entry 3095 (class 0 OID 16551)
-- Dependencies: 219
-- Data for Name: Vacancy; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Vacancy" ("VacancyID", "Salary", "Trial", "Name") VALUES (1, 1000, 30, 'Пиццер');
INSERT INTO public."Vacancy" ("VacancyID", "Salary", "Trial", "Name") VALUES (2, 500, 60, 'Пеший курьер');
INSERT INTO public."Vacancy" ("VacancyID", "Salary", "Trial", "Name") VALUES (3, 750, 45, 'Водитель-курьер');


--
-- TOC entry 3121 (class 0 OID 0)
-- Dependencies: 206
-- Name: Address_AddressID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Address_AddressID_seq"', 55, true);


--
-- TOC entry 3122 (class 0 OID 0)
-- Dependencies: 224
-- Name: Addresses_AddressId_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Addresses_AddressId_seq"', 135, true);


--
-- TOC entry 3123 (class 0 OID 0)
-- Dependencies: 202
-- Name: Drink_DrinkID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Drink_DrinkID_seq"', 10, true);


--
-- TOC entry 3124 (class 0 OID 0)
-- Dependencies: 216
-- Name: Drink_order_Id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Drink_order_Id_seq"', 42, true);


--
-- TOC entry 3125 (class 0 OID 0)
-- Dependencies: 210
-- Name: Order_OrderID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Order_OrderID_seq"', 56, true);


--
-- TOC entry 3126 (class 0 OID 0)
-- Dependencies: 212
-- Name: Pizza_Order_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Pizza_Order_ID_seq"', 50, true);


--
-- TOC entry 3127 (class 0 OID 0)
-- Dependencies: 214
-- Name: Pizza_Order_Order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Pizza_Order_Order_id_seq"', 1, false);


--
-- TOC entry 3128 (class 0 OID 0)
-- Dependencies: 213
-- Name: Pizza_Order_Pizza_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Pizza_Order_Pizza_id_seq"', 1, false);


--
-- TOC entry 3129 (class 0 OID 0)
-- Dependencies: 208
-- Name: Pizza_PizzaID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Pizza_PizzaID_seq"', 24, true);


--
-- TOC entry 3130 (class 0 OID 0)
-- Dependencies: 204
-- Name: Role_RoleID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Role_RoleID_seq"', 6, true);


--
-- TOC entry 3131 (class 0 OID 0)
-- Dependencies: 220
-- Name: User_UserID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."User_UserID_seq"', 31, true);


--
-- TOC entry 3132 (class 0 OID 0)
-- Dependencies: 222
-- Name: User_Vacancy_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."User_Vacancy_id_seq"', 15, true);


--
-- TOC entry 3133 (class 0 OID 0)
-- Dependencies: 218
-- Name: Vacancy_VacancyID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Vacancy_VacancyID_seq"', 3, true);


--
-- TOC entry 2922 (class 2606 OID 16464)
-- Name: Address Address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Address"
    ADD CONSTRAINT "Address_pkey" PRIMARY KEY ("AddressID");


--
-- TOC entry 2942 (class 2606 OID 16712)
-- Name: Addresses Addresses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Addresses"
    ADD CONSTRAINT "Addresses_pkey" PRIMARY KEY ("AddressId");


--
-- TOC entry 2930 (class 2606 OID 16519)
-- Name: Drink_order Drink_order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Drink_order"
    ADD CONSTRAINT "Drink_order_pkey" PRIMARY KEY ("Id");


--
-- TOC entry 2918 (class 2606 OID 16536)
-- Name: Drink Drink_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Drink"
    ADD CONSTRAINT "Drink_pkey" PRIMARY KEY ("DrinkID");


--
-- TOC entry 2926 (class 2606 OID 16486)
-- Name: Order Order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Order"
    ADD CONSTRAINT "Order_pkey" PRIMARY KEY ("OrderID");


--
-- TOC entry 2928 (class 2606 OID 16500)
-- Name: Pizza_Order Pizza_Order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Pizza_Order"
    ADD CONSTRAINT "Pizza_Order_pkey" PRIMARY KEY ("Id");


--
-- TOC entry 2924 (class 2606 OID 16475)
-- Name: Pizza Pizza_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Pizza"
    ADD CONSTRAINT "Pizza_pkey" PRIMARY KEY ("PizzaID");


--
-- TOC entry 2920 (class 2606 OID 16453)
-- Name: Role Role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Role"
    ADD CONSTRAINT "Role_pkey" PRIMARY KEY ("RoleID");


--
-- TOC entry 2932 (class 2606 OID 16685)
-- Name: Drink_order Unique(Drink_Order); Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Drink_order"
    ADD CONSTRAINT "Unique(Drink_Order)" UNIQUE ("OrderID", "DrinkID");


--
-- TOC entry 2936 (class 2606 OID 16687)
-- Name: User Unique(Email); Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "Unique(Email)" UNIQUE ("Email");


--
-- TOC entry 2940 (class 2606 OID 16593)
-- Name: User_Vacancy User_Vacancy_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User_Vacancy"
    ADD CONSTRAINT "User_Vacancy_pkey" PRIMARY KEY (id);


--
-- TOC entry 2938 (class 2606 OID 16570)
-- Name: User User_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "User_pkey" PRIMARY KEY ("UserID");


--
-- TOC entry 2934 (class 2606 OID 16559)
-- Name: Vacancy Vacancy_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Vacancy"
    ADD CONSTRAINT "Vacancy_pkey" PRIMARY KEY ("VacancyID");


--
-- TOC entry 2946 (class 2606 OID 16665)
-- Name: Drink_order DrinkID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Drink_order"
    ADD CONSTRAINT "DrinkID" FOREIGN KEY ("DrinkID") REFERENCES public."Drink"("DrinkID") ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 2945 (class 2606 OID 16660)
-- Name: Drink_order OrderID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Drink_order"
    ADD CONSTRAINT "OrderID" FOREIGN KEY ("OrderID") REFERENCES public."Order"("OrderID") ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 2943 (class 2606 OID 16672)
-- Name: Pizza_Order OrderID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Pizza_Order"
    ADD CONSTRAINT "OrderID" FOREIGN KEY ("OrderID") REFERENCES public."Order"("OrderID") ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 2944 (class 2606 OID 16677)
-- Name: Pizza_Order PizzaID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Pizza_Order"
    ADD CONSTRAINT "PizzaID" FOREIGN KEY ("PizzaID") REFERENCES public."Pizza"("PizzaID") ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 2950 (class 2606 OID 16648)
-- Name: User_Vacancy UserId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User_Vacancy"
    ADD CONSTRAINT "UserId" FOREIGN KEY ("UserID") REFERENCES public."User"("UserID") ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 2947 (class 2606 OID 16631)
-- Name: User User_Adress; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "User_Adress" FOREIGN KEY ("Address_id") REFERENCES public."Address"("AddressID") ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 2948 (class 2606 OID 16636)
-- Name: User User_Order; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "User_Order" FOREIGN KEY ("Order_id") REFERENCES public."Order"("OrderID") ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 2949 (class 2606 OID 16641)
-- Name: User User_Role; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "User_Role" FOREIGN KEY ("Role_id") REFERENCES public."Role"("RoleID") ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 2951 (class 2606 OID 16653)
-- Name: User_Vacancy VacancyID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User_Vacancy"
    ADD CONSTRAINT "VacancyID" FOREIGN KEY ("VacancyID") REFERENCES public."Vacancy"("VacancyID") ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


-- Completed on 2023-05-29 20:40:40 +03

--
-- PostgreSQL database dump complete
--

